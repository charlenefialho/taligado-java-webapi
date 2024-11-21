-- Tabela: empresa
INSERT INTO empresa (id_empresa, nome, email, cnpj, segmento, data_fundacao) VALUES (1, 'Empresa A', 'contato@empresaA.com', '12345678000101', 'Tecnologia', '2010-05-20'), (2, 'Empresa B', 'contato@empresaB.com', '98765432000109', 'Energia', '2015-08-10');

-- Tabela: endereco
INSERT INTO endereco (id_endereco, logradouro, cidade, estado, cep, pais) VALUES (1, 'Rua Principal, 123', 'São Paulo', 'SP', '01000-000', 'Brasil'), (2, 'Avenida Secundária, 456', 'Rio de Janeiro', 'RJ', '20000-000', 'Brasil');

-- Tabela: filial
--INSERT INTO filial (id_filial, nome, tipo, cnpj_filial, area_operacional, empresa_idempresa, endereco_idendereco) VALUES (1, 'Filial SP', 'Matriz', '11122233000101', 'Produção', 1, 1), (2, 'Filial RJ', 'Filial', '44455566000102', 'Manutenção', 1, 2);

-- Tabela: dispositivo
INSERT INTO dispositivo (id_disposito, nome, tipo, status, data_instalacao, potencia_nominal, filial_idfilial) VALUES (1, 'Dispositivo 1', 'Sensor', 'Ativo', '2023-01-10', 100.0, 1), (2, 'Dispositivo 2', 'Controlador', 'Inativo', '2023-02-15', 200.0, 2);

-- Tabela: sensor
INSERT INTO sensor (id_sensor, tipo, descricao, unidade, valor_atual, tempo_operacao, dispositivo_iddispositivo) VALUES (1, 'Temperatura', 'Sensor de Temperatura', '°C', 25.5, 1200.0, 1), (2, 'Umidade', 'Sensor de Umidade', '%', 60.0, 800.0, 1);

-- Tabela: alerta
--INSERT INTO alerta (id_alerta, descricao, severidade, data_alerta, sensor_idsensor) VALUES (1, 'Temperatura acima do limite', 'Alta', '2023-03-01', 1),(2, 'Umidade abaixo do esperado', 'Média', '2023-03-05', 2);

-- Tabela: regulacao_energia
INSERT INTO regulacao_energia (id_regulacao, tarifa_kwh, nome_bandeira, tarifa_adicional_bandeira, data_atualizacao) VALUES (1, 0.85, 'Verde', 0.0, '2023-03-10'), (2, 1.20, 'Vermelha', 0.35, '2023-03-12');

-- Tabela: historico
INSERT INTO historico (id_historico, data_criacao, valor_consumo_kwh, intensidade_carbono, custo_energia_estimado, regulacao_energia_idregulacao) VALUES (1, '2023-03-15', 500.0, 100.0, 425.0, 1), (2, '2023-03-16', 600.0, 120.0, 720.0, 2);

-- Tabela: historico_sensor (relacionamento entre sensor e historico)
INSERT INTO historico_sensor (historico_idhistorico, sensor_idsensor) VALUES (1, 1), (2, 2);
