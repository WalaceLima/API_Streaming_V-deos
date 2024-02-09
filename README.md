# API_Streaming_Videos

API de gestão de eletrodomésticos tem como objetivo permitir o cadastro e gerenciamento web de streaming de videos utilizando Clean Architecture em camadas: Controller,Service, Use case e Repository.

### Videos de apresentação ao fim do Readme

### Ferramentas utitlizadas:
-	Spring Boot (Auxilia na criação de microserviços)
-	MongoDB Reactive (Banco não relacional para programação reativa)
-	Spring WebFlux (Estrutura reativa e sem bloqueio para aplicações modernas e não blocante)
-	Spring Data ( Integração com banco de dados)
-	Postman (Testes de Apis )
-	Junit (Testes automatizados)
-	Spring Web (Auxiliador de aplicações web)
-	Lombok (Gerador de encapsuldor focado em produtividade )
-	Validation (Validador de endpoints)
-	Maven (Gerenciador de pacotes)

### Endpoints: 
Utilizado Spring WebFluz para criação dos endpoints, Mono e Flux.
- /video
- /video/{id}
      
### Método HTTP:
- Post (save)
- Get (getAllVideos)
- Get (getVideoId)
- Update (update)
- Delete (delete)

### Descrição:
- POST, o endpoint é do tipo POST os parâmetros de solicitação são enviados no corpo da solicitação em formato JSON. Os parâmetros incluem descrição, título,url e data, que são utilizados para criar um novo video.
- GET,  o endpoint é do tipo GET devolvem todos os dados e videos disponíveis em banco de dados.
- GET /{id}, o endpoint é do tipo GET e busca um video especifico, apartir do envio de um ID.
- DELETE /{id}, o endpoint é do tipo GET e apaga um video especifico, apartir do envio de um ID.
- UPDATE /{id}, o endpoint é do tipo GET e atualiza um video especifico, apartir do envio de um ID.

### Parâmetros no corpo da solicitação (JSON):

![Picture1](https://s4.aconvert.com/convert/p3r68-cdx67/ai332-mzxrc.png)

Exemplo de solicitação:
- POST /video

#### Exemplo de solicitação:

![Picture2](https://s4.aconvert.com/convert/p3r68-cdx67/am6uw-02oso.png)

Formato de resposta:
Cabeçalho HTTP:

#### Corpo da resposta (JSON):

![Picture3](https://s4.aconvert.com/convert/p3r68-cdx67/asnob-n6fjs.png)

#### Lidando com Erros

A API possui um mecanismo para tratar os dados e fornece respostas adequadas para diferentes cenários. 

#### Corpo da solicitação.

![Picture4](https://s4.aconvert.com/convert/p3r68-cdx67/a2nug-vji87.png)

- Resposta 201 Created: Este código de status é retornado quando uma solicitação de criação de recurso é bem-sucedida.

#### Corpo da solicitação inválida:

![Picture5](https://s4.aconvert.com/convert/p3r68-cdx67/ak4zd-gdvji.png)

![Picture6](https://s4.aconvert.com/convert/p3r68-cdx67/a9rml-iqlud.png)

- 400 Bad Request: Este código de status é retornado quando ocorre um erro no lado do cliente, como uma solicitação inválida ou parâmetros ausentes ou inválidos.

### Repositório API:
- [API de Gestão de Eletrodomésticos](https://github.com/WalaceLima/APIGestaodeEletrodomesticosPOSFIAP.git).

.
Recebe as informações dos eletrônicos com os campos de nome, modelo, potência e outras informações relevantes.
Recebe as solicitações em formato HTTP POST.
As informações são validadas para garantir que elas estão no formato correto e que são válidas.
Caso haja algum erro, a API retorna uma mensagem de erro indicando o problema encontrado.
Uma vez validadas as informações, a API informa que está tudo ok e dar um retorno positivo ao usuário.



### Endpoint: 
- /eletrodomesticos
      
### Método HTTP:
- Post
- Get
- Update
- Delete

### Descrição:
Este endpoint cria um novo eletrodoméstico com base nos parâmetros fornecidos.
Neste exemplo, o endpoint é do tipo POST e os parâmetros de solicitação são enviados no corpo da solicitação em formato JSON. Os parâmetros incluem nome, marca e voltagem, que são utilizados para criar um novo eletrodoméstico.

### Parâmetros no corpo da solicitação (JSON):

![Picture1](https://s4.aconvert.com/convert/p3r68-cdx67/ai332-mzxrc.png)

Exemplo de solicitação:
- POST /eletrodomesticos

#### Exemplo de solicitação:

![Picture2](https://s4.aconvert.com/convert/p3r68-cdx67/am6uw-02oso.png)

Formato de resposta:
Cabeçalho HTTP:

#### Corpo da resposta (JSON):

![Picture3](https://s4.aconvert.com/convert/p3r68-cdx67/asnob-n6fjs.png)

#### Lidando com Erros

A API possui um mecanismo robusto para lidar com erros e fornece respostas adequadas para diferentes cenários. A seguir estão os possíveis códigos de status que podem ser retornados em diferentes situações:

#### Corpo da solicitação.

![Picture4](https://s4.aconvert.com/convert/p3r68-cdx67/a2nug-vji87.png)

- Resposta 201 Created: Este código de status é retornado quando uma solicitação de criação de recurso é bem-sucedida.

#### Corpo da solicitação inválida:

![Picture5](https://s4.aconvert.com/convert/p3r68-cdx67/ak4zd-gdvji.png)

![Picture6](https://s4.aconvert.com/convert/p3r68-cdx67/a9rml-iqlud.png)

- 400 Bad Request: Este código de status é retornado quando ocorre um erro no lado do cliente, como uma solicitação inválida ou parâmetros ausentes ou inválidos.


### Videos:
- - Video 1 - [https://youtu.be/dyg8dMMu5Pg](https://youtu.be/dyg8dMMu5Pg)
- - Video 2 - [https://youtu.be/82KIx7Fj0_I](https://youtu.be/82KIx7Fj0_I)
- - Video 3 - [https://youtu.be/tLOie_GpIjs](https://youtu.be/tLOie_GpIjs)
- - Video 4 - [https://youtu.be/4D2KTM8bEsk](https://youtu.be/4D2KTM8bEsk)
- - Video 5 - [https://youtu.be/8I0ePg745B0](https://youtu.be/8I0ePg745B0)

