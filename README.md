# Desafio Técnico API REST - Pior Filme do Golden Raspberry Awards

- Essa API permite utilizar uma base importada de um arquivo CSV para gerar e disponibilizar dados
sobre indicados e vencedores do referido prêmio.

- Um dos principais objetivos dessa API é obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que
  obteve dois prêmios mais rápido. A aprensentação desses dados seque a especificação no arquivo src/main/resources/Especificação Backend.pdf.

# Características da API

- API desenvolvida usando Quarkus Framework na versão 3.3.2
  - A estrutura inicial do projeto foi gerada pelo START CODING do quarkus (https://code.quarkus.io/)
- Ao iniciar a aplicação, o arquivo CSV presente no diretório src/main/resources/movielist.csv é lido, processado e seus dados são armazenados no banco de dados.
- Utilização de SGBD embarcado H2
- Documentação com Swagger
- Disponibilização de rota para obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que
  obteve dois prêmios mais rápido.


# Tecnologias necessárias para execução do projeto

- Java - Versão 11. Disponível em: https://docs.aws.amazon.com/corretto/latest/corretto-11-ug/downloads-list.html
- Maven - Versão 3.9.4. Disponível em: https://dlcdn.apache.org/maven/maven-3/3.9.4/source/apache-maven-3.9.4-src.zip
- Se necessário, A SDK do java e o Maven usados para execução da aplicação estão disponíveis no link: https://drive.google.com/drive/folders/1YlBRBejkEQ1FW5CJEUrov1tkdmFIC4_p?usp=sharing

# Passos para executar o projeto utilizando linhas de comandos (Ambiente Windows)

- O primeiro passso aqui é ter o Java e o Maven configurados nas variavéis de ambiente do windows.
- Acesse as configurações de variáveis de ambiente
- ![img_9.png](src/main/resources/readme/img_9.png)
- ![img_10.png](src/main/resources/readme/img_10.png)
- Na parte de variáveis do sistema, clique em novos e configure os diretórios da seguinte forma:
- ![img_11.png](src/main/resources/readme/img_11.png)
- Faça uma edição no Path para configurar o bin:
- ![img_12.png](src/main/resources/readme/img_12.png)
- ![img_13.png](src/main/resources/readme/img_13.png)
- Clique em OK em todas as telas para encerrar a configuração
- Para testar a configuração do Java abra o cmd e digite: java -version
- ![img_14.png](src/main/resources/readme/img_14.png)
- Para testar a configuração do Maven abra o cmd e digite: mvn
- ![img_15.png](src/main/resources/readme/img_15.png)
- Agora vamos executar alguns comandos para executar a aplicação
- Abra o diretório raiz do projeto e abra um terminal do GitBash por exemplo. https://git-scm.com/downloads
- ![img.png](src/main/resources/readme/img-01.png)
- ![img_3.png](src/main/resources/readme/img_3-3.png)
- Execute o comando mvn clean install para gerar o build completo da aplicação, inclusive com os testes.
- ![img_2.png](src/main/resources/readme/img_2-2.png)
- Devemos ter o seguinte resultado:
- ![img_4.png](src/main/resources/readme/img_4-4.png)
- Para finalmente iniciar a aplicação. Execute o seguinte comando: ./mvnw compile quarkus:dev
- Temos como resultado:
- ![img_5.png](src/main/resources/readme/img_5-5.png)
- Utilize o seguinte link acessar o Dashboard do Quarkus: http://localhost:8080
- Link do Swagger http://localhost:8080/q/swagger-ui/
- ![img.png](src/main/resources/readme/img-10.png)
- ![img_8.png](src/main/resources/readme/img_8-8.png)

# Passos para executar o projeto utilizando IntelliJ IDEA no idioma inglês

- Clonar o projeto worst-movie-api em algum diretório na máquina. (Para essa aplicação foi usado Windows como ambiente de desenvolvimento)
- Baixar e instalar versão gratuita IntelliJ IDEA Ultimate por 30 dias. https://www.jetbrains.com/idea/download/?section=windows
- Ao iniciar a IDE, selecionar a Opção File -> Open
- ![img.png](src/main/resources/readme/img.png)
- Procure e selecione o diretório onde o projeto foi clonado:
- ![img_1.png](src/main/resources/readme/img_1.png)
- Para configurar o Java selecione a opção File -> Project Structure
- ![img_2.png](src/main/resources/readme/img_2.png)
- Na aba project, opção SDK procure e selecione a SDK do Java 11
- ![img_3.png](src/main/resources/readme/img_3.png)
- Para configurar o maven, selecione a opção File -> Settings
- ![img_4.png](src/main/resources/readme/img_4.png)
- Pesquisar por maven e configure conforme imagem
- ![img_6.png](src/main/resources/readme/img_6.png)
- Na aba do maven na parte superior da lateral direita, execute o clean e install para baixar as dependências do projeto.
- ![img_7.png](src/main/resources/readme/img_7.png)
- Após finalizar com sucesso essa ação. na parte superior da lateral direita, clique no botão para executar o projeto:
- ![img_8.png](src/main/resources/readme/img_8.png)
- Utilize o seguinte link acessar o Dashboard do Quarkus: http://localhost:8080 
- Link do Swagger http://localhost:8080/q/swagger-ui/
- ![img_1.png](src/main/resources/readme/img_1-11.png) 
- ![img_9.png](src/main/resources/readme/img_9-1.png)


# Passos para retorna faixa de prêmio entre os produtores (Teste com Swagger).

- O resultado do teste considera os dados importados do arquivo CSV presente no diretório src/main/resources/movielist.csv.
- Alterações de dados realizados após essa importação, através de manipulação de dados no banco deve ser feito com atenção, pois pode alterar a perspectiva do teste.
- Para gerar outros cenários de testes podem ser feitas várias alterações no arquivo CSV desde que não seja alterado a estruta adequada para um arquivo CSV e nem seja removido os títulos que representam as colunas do CSV. (year;title;studios;producers;winner). Para esses cenários de errros de importação, um log será exibido no console com a característica do erro.
- Acesse o Swagger através do link: http://localhost:8080/q/swagger-ui/
- Procure pelo menu Awards Range Resource
- O menu irá conter a esspecificação da rota a ser usada para retornar o objetivo em questão. Clique na opção "Try it out", conforme imagem:
- ![img.png](src/main/resources/readme/img-15.png)
- Agora clique em "Execute"
- ![img_1.png](src/main/resources/readme/img_1-16.png)
- O resultado é mostrado conforme imagem:
- ![img_2.png](src/main/resources/readme/img_2-17.png)

# Passos para retorna a faixa de prêmio entre os produtores (Teste com Postman).

- Outra maneira de testar o endpoint que retorna o objetivo em questão, é fazendo uma requisição usando o Postman. https://www.postman.com/downloads/
- Após a instalação da ferramenta, abra e crie uma Requisição GET para realizar o teste.
- Utilize a seguinte URL para acessar o Endpoint: http://localhost:8080/api/v1/awardsrange/producers
- Clique em Send para obter o resultado conforme abaixo
- ![img_3.png](src/main/resources/readme/img_3-18.png)

# Passos para executar os testes de integração através do Dashboard do Quarkus

- Acessando o link: http://localhost:8080
- ![img_5.png](src/main/resources/readme/img_5-20.png)
- Clique em "VISIT THE DEV UI"
- Selecione a aba "Continuos Testing" e clique em Star
- ![img_7.png](src/main/resources/readme/img_7-22.png)
- O resulto dos testes é apresentado conforme imagem
- ![img_8.png](src/main/resources/readme/img_8-23.png)
- O testeResponseOkReturnAwardsRangeProducer() tenta acessar o endpoint esperando que ele retorne código de 200 para requisição executada com sucesso.
- O teste testResponseMaxMinAwardsRangeDTO() carrega os dados do CSV, executa a rota que retorna a faixa de prêmio entre os produtores e compara sse o resultado está conforme o esperado no mock presente em com.worstmovie.api.ProducersResourceTest.getMockMaxMinAwardsRangeDTO. Importante reforçar que para esse teste é considerado os dados do CSV presente no diretório src/main/resources/movielist sem alterações. Caso os dados do CSV sejam alterados o mock de retorno de dados também deve ser alterado conforme o esperado:
- O mock presente em com.worstmovie.api.ProducersResourceTest.getMockMaxMinAwardsRangeDTO conforme imagem, simula o cenário onde o produtor JOEL SILVER obteve os dois prêmios mais rápido e o produtor MATTHEW VAUGH obteve maior intervalo entre dois prêmios consecutivos.
- ![img_9.png](src/main/resources/readme/img_9-24.png)
- Para executar o teste via Intelij IDE, entre na classe ProducersResourceTest e execute os testes conforme imagem:
- ![img_10.png](src/main/resources/readme/img_10-25.png)
- Para executar os testes via comando, abrindo o terminal do gitBash nas raiz do projeto execute o comando: mvn clean install -DskipUnitTests
- O resultado pode ser verificado conforma imagem:
- ![img_11.png](src/main/resources/readme/img_11-26.png)