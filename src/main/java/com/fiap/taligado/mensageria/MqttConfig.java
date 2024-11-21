package com.fiap.taligado.mensageria;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.taligado.dto.AlertaDTO;
import com.fiap.taligado.dto.SensorDTO;
import com.fiap.taligado.service.AlertService;
import com.fiap.taligado.service.AlertaService;
import com.hivemq.client.mqtt.MqttClient;
import com.hivemq.client.mqtt.mqtt3.Mqtt3AsyncClient;
import com.hivemq.client.mqtt.mqtt3.message.publish.Mqtt3Publish;

@Component
public class MqttConfig {

	/*private final Mqtt3AsyncClient mqttClient;
	private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final AlertaService alertaService; // Injetar o serviço de alertas
	private final AlertService alertService; // Injetar o serviço para Toasts
	private String topic = "taligado/teste";

	public MqttConfig(AlertaService alertaService, AlertService alertService) {
		
		this.alertaService = alertaService;
		this.alertService = alertService;
		
		// Configurando o cliente MQTT
		mqttClient = MqttClient.builder().useMqttVersion3().identifier(UUID.randomUUID().toString())
				.serverHost("broker.hivemq.com").serverPort(1883).simpleAuth().username("usuario")
				.password("sua-senha".getBytes(StandardCharsets.UTF_8)).applySimpleAuth().buildAsync();

		connectAndTest();
	}

	private void connectAndTest() {
		mqttClient.connect().whenComplete((connAck, throwable) -> {
			if (throwable != null) {
				System.err.println("Erro ao conectar ao MQTT Broker: " + throwable.getMessage());
			} else {
				System.out.println("Conectado ao MQTT Broker!");
				// Inscrever-se no tópico
				subscribeToTopic();
				// Publicar mensagens no tópico
				publishMessage();
			}
		});
	}

	private void subscribeToTopic() {
		mqttClient.subscribeWith().topicFilter(topic).callback(this::handleMessage).send()
				.whenComplete((subAck, throwable) -> {
					if (throwable != null) {
						System.err.println("Erro ao se inscrever no tópico: " + throwable.getMessage());
					} else {
						System.out.println("Inscrito no tópico: " + topic);
					}
				});
	}

	private void publishMessage() {
		// Publicação de mensagem para o Sensor 1 (a cada 1 minuto)
		executorService.scheduleAtFixedRate(() -> {
			try {
				// Criar o SensorDTO para o primeiro sensor
				SensorDTO sensor1 = new SensorDTO();
				sensor1.setIdSensor(1L);
				sensor1.setTipo("Temperatura");
				sensor1.setDescricao("Sensor de temperatura ambiente");
				sensor1.setUnidade("°C");
				sensor1.setValorAtual(35.5); // Valor fictício
				sensor1.setTempoOperacao(120.0); // Tempo de operação em minutos
				sensor1.setDispositivoId(1001L); // ID do dispositivo relacionado

				// Serializar para JSON
				String sensorJson = objectMapper.writeValueAsString(sensor1);

				// Publicar no tópico MQTT
				mqttClient.publishWith().topic(topic) // Publicar no tópico configurado
						.payload(sensorJson.getBytes(StandardCharsets.UTF_8)).send().whenComplete((pub, ex) -> {
							if (ex != null) {
								System.err.println("Erro ao publicar mensagem (1 min): " + ex.getMessage());
							} else {
								System.out.println("Mensagem publicada (1 min): " + sensorJson);
							}
						});
			} catch (Exception e) {
				System.err.println("Erro ao gerar mensagem do Sensor 1 (1 min): " + e.getMessage());
			}
		}, 0, 1, TimeUnit.MINUTES);

		// Publicação de mensagem para o Sensor 2 (a cada 2 minutos)
		executorService.scheduleAtFixedRate(() -> {
			try {
				// Criar o SensorDTO para o segundo sensor
				SensorDTO sensor2 = new SensorDTO();
				sensor2.setIdSensor(2L);
				sensor2.setTipo("Umidade");
				sensor2.setDescricao("Sensor de umidade relativa");
				sensor2.setUnidade("%");
				sensor2.setValorAtual(60.0); 
				sensor2.setTempoOperacao(200.0); 
				sensor2.setDispositivoId(1002L); 

				// Serializar para JSON
				String sensorJson = objectMapper.writeValueAsString(sensor2);

				// Publicar no tópico MQTT
				mqttClient.publishWith().topic(topic) // Publicar no tópico configurado
						.payload(sensorJson.getBytes(StandardCharsets.UTF_8)).send().whenComplete((pub, ex) -> {
							if (ex != null) {
								System.err.println("Erro ao publicar mensagem (2 min): " + ex.getMessage());
							} else {
								System.out.println("Mensagem publicada (2 min): " + sensorJson);
							}
						});
			} catch (Exception e) {
				System.err.println("Erro ao gerar mensagem do Sensor 2 (2 min): " + e.getMessage());
			}
		}, 0, 2, TimeUnit.MINUTES);
	}

	private void handleMessage(Mqtt3Publish message) {
	    try {
	        String payload = new String(message.getPayloadAsBytes(), StandardCharsets.UTF_8);
	        SensorDTO sensorDTO = objectMapper.readValue(payload, SensorDTO.class);

	        System.out.println("Mensagem recebida no tópico [" + message.getTopic() + "]: " + sensorDTO);

	        double limite = getLimitePorTipo(sensorDTO.getTipo());
	        if (sensorDTO.getValorAtual() > limite) {
	            System.out.println("Valor acima do limite detectado! Criando alerta...");

	            AlertaDTO alertaDTO = new AlertaDTO();
	            alertaDTO.setDescricao("Valor crítico detectado: " + sensorDTO.getValorAtual() + " " + sensorDTO.getUnidade());
	            alertaDTO.setSeveridade("ALTA");
	            alertaDTO.setSensorId(sensorDTO.getIdSensor());

	            alertaService.criarAlerta(alertaDTO);
	            alertService.addAlerta("Alerta: " + alertaDTO.getDescricao());
	            System.out.println("Alerta registrado com sucesso!");
	        }
	    } catch (Exception e) {
	        System.err.println("Erro ao processar mensagem recebida: " + e.getMessage());
	    }
	}


	private double getLimitePorTipo(String tipo) {
		switch (tipo.toLowerCase()) {
			case "temperatura":
				return 30.0;
			case "umidade":
				return 70.0;
			default:
				return Double.MAX_VALUE;
		}
	}

	public void disconnect() {
		mqttClient.disconnect().whenComplete((disconnAck, throwable) -> {
			if (throwable != null) {
				System.err.println("Erro ao desconectar do MQTT Broker: " + throwable.getMessage());
			} else {
				System.out.println("Desconectado do MQTT Broker!");
			}
		});

		executorService.shutdown();
		try {
			if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			executorService.shutdownNow();
		}
	}*/
}
