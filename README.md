
# 🔋TáLigado - Monitoramento de Consumo Energético e Sustentabilidade

Descrição do Projeto
O TáLigado é um sistema inovador que integra tecnologias de IoT, aprendizado de máquina e visualização de dados para monitorar o consumo energético e as emissões de CO₂ em tempo real. O objetivo principal é fornecer às empresas ferramentas para reduzir custos, melhorar a eficiência energética e cumprir metas de sustentabilidade, promovendo a redução da pegada de carbono.

O sistema captura dados através de dispositivos IoT, analisa padrões de consumo utilizando algoritmos de machine learning e emite alertas e recomendações baseadas em insights acionáveis.



## Requisitos técnicos

O desenvolvimento do TáLigado atende aos seguintes requisitos:

-  **Configuração de Beans e Injeção de Dependências:** Utilização de anotações Spring (`@Component`, `@Service`, `@Repository`, `@Configuration`).

- **Modelagem de Dados:** Classes model e DTO com encapsulamento adequado.

- **Persistência de Dados:** Integração com Spring Data JPA e suporte a banco de dados relacional oracle.

- **Validação de Dados:** Utilização de Bean Validation (`@NotNull`, `@Size`, etc.).

- **Segurança:** Implementação de Spring Security para autenticação e autorização.

- **Tratamento de Erros:** Manipulação de exceções com `@ControllerAdvice` e respostas padronizadas.

- **Mensageria:** Uso de filas assíncronas com RabbitMQ para processamentos em background.

- **Inteligência Artificial:** Integração com Spring AI com a utilização do chat gpt da openai
## Arquitetura do Sistema

### Camadas Principais
- **Controladores (REST Controllers):** Gerenciam as requisições HTTP e delegam operações aos serviços.

- **DTO (Data Transfer Object):** A camada DTO é utilizada para transferir dados entre o cliente e o servidor de forma estruturada e segura. Ela permite isolar os detalhes internos do modelo de dados (entidades) e evita exposição direta das entidades da base de dados. 

- **Serviços (Service Layer):** Contêm a lógica de negócios e chamam os repositórios.

- **Repositórios (Data Access Layer):** Interface com o banco de dados utilizando Spring Data JPA.

- **Mensageria:** Processamento de mensagens assíncronas.

- **Inteligência Artificial:** Chat com o chat gpt

## 📂 Estrutura de Pastas
```tree
  src/main/java
└── com.fiap.taligado
    ├── config
    │   └── SecurityConfig.java
    ├── controller
    │   └── EmpresaController.java
    ├── dto
    │   └── EmpresaDTO.java
    ├── model
    │   └── Empresa.java
    ├── repository
    │   └── EmpresaRepository.java
    ├── service
    │   └── EmpresaService.java
    └── TaligadoApplication.java
```

## Estrutura do banco de dados

O banco de dados foi modelado seguindo as normas de normalização até a 3ª Forma Normal, garantindo integridade, consistência e minimização de redundâncias.

### Principais Tabelas
- **Empresa:** Dados cadastrais das empresas monitoradas.
- **Filial:** Relaciona-se com a tabela Empresa, representando suas unidades.
- **Endereço:** Detalhes geográficos associados às filiais.
- **Dispositivo:** Representa os dispositivos IoT instalados.
- **Sensor:** Sensores associados aos dispositivos, registrando dados de consumo.
- **Histórico:** Registros de consumo energético e emissões de carbono.
- **Regulação de Energia:** Dados sobre tarifas e bandeiras tarifárias vigentes.

### Diagrama Relacional
![image](https://github.com/user-attachments/assets/9f60aa4f-5298-4427-8af2-fc7a54a62e7a)

### Diagrama lógico
![image](https://github.com/user-attachments/assets/abd358ba-dbe7-4e56-a2c3-11233dc429f5)


## Principais funcionalidades

- **Monitoramento em Tempo Real:** Captura e análise contínua de consumo energético e emissões.

- **Previsão de Consumo e Emissões:** Algoritmos de aprendizado de máquina para identificar padrões.

- **Alertas Proativos:** Notificações em caso de desvios críticos no consumo ou emissões.

- **Relatórios Personalizados:** Geração de relatórios para compliance ambiental e decisões estratégicas.

- **Autenticação Segura:** Controle de acesso baseado em roles.

- **Processamento Assíncrono:** Uso de filas com broker client MQTT

# Vídeo
[link do video](https://www.youtube.com/watch?v=ZUj-5MNA3l4)


### Integrantes do grupo
<table>
  <tr>
        <td align="center">
      <a href="https://github.com/biancaroman">
        <img src="https://avatars.githubusercontent.com/u/128830935?v=4" width="100px;" border-radius='50%' alt="Bianca Román's photo on GitHub"/><br>
        <sub>
          <b>Bianca Román</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/charlenefialho">
        <img src="https://avatars.githubusercontent.com/u/94643076?v=4" width="100px;" border-radius='50%' alt="Charlene Aparecida's photo on GitHub"/><br>
        <sub>
          <b>Charlene Aparecida</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/laiscrz">
        <img src="https://avatars.githubusercontent.com/u/133046134?v=4" width="100px;" alt="Lais Alves's photo on GitHub"/><br>
        <sub>
          <b>Lais Alves</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/LuccaRaphael">
        <img src="https://avatars.githubusercontent.com/u/127765063?v=4" width="100px;" border-radius='50%' alt="Lucca Raphael's photo on GitHub"/><br>
        <sub>
          <b>Lucca Raphael</b>
        </sub>
      </a>
    </td>
     <td align="center">
      <a href="https://github.com/Fabs0602">
        <img src="https://avatars.githubusercontent.com/u/111320639?v=4" width="100px;" border-radius='50%' alt="Fabricio Torres's photo on GitHub"/><br>
        <sub>
          <b>Fabricio Torres</b>
        </sub>
      </a>
    </td>
  </tr>
</table>
