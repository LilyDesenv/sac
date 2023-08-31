# Sistema de Atendimento ao Cliente - SAC

### API para controle de distribuição de atendimento de Clientes
Desafio proposto pela Ubots para a vaga de Desenvolvedor Full-Stack


### Tecnologias Utilizadas
* Spring Boot
* JPA
* Maven
* H2 Database

### Como Executar
Localmente
* Clonar repositório git - [acesse aqui](https://github.com/LilyDesenv/sac)
* Construir o projeto
```
./mvnw clean package
```
* Executar:
```
java -jar place-service/target/place-service-0.0.1-SNAPSHOT.jar
```
A API poderá ser acessada em [localhost:8080](http://localhost:8080)

### API Endpoints

Para fazer as requisições HTTP abaixo foi utilizado o aplicativo [Insomnia](https://insomnia.rest/download) 

Obs: Para facilitar os testes, a API já pré-cadastra alguns dados assim que é chamada pelo [localhost:8080/sac](http://localhost:8080/sac)
* POST /sac

A API já começa com 5 solicitações para os 2 atendentes de cartões
```
{
	"ClienteId": 7,
	"TiposolicitacaoId": 1,
	"descricao": "Descrição do Problema 1"
}
```
Após adicionada mais uma solicitação de atendimento para cartões, os atendentes ficam com 3 clientes cada
```
{
	"ClienteId": 8,
	"TiposolicitacaoId": 1,
	"descricao": "Descrição do Problema 2"
}
```
Ao tentar adicionar mais um a API informa que todos estão em atendimento e coloca a solicitação em Espera

A API verifica se já existe alguma solicitação do mesmo tipo para o mesmo cliente, se houver, uma mensagem é informada ao usuário
```
{
	"ClienteId": 1,
	"TiposolicitacaoId": 1,
	"descricao": "Descrição do Problema 1"
}
```
* GET /filaEspera

Requisição para vizualizar quem está na fila de espera  [localhost:8080/filaEspera](http://localhost:8080/filaEspera)

* POST /finalizar

Para retirar as solicitações da fila de espera e alocá-las para um atendente, é necessário finalizar um dos atendimentos em andamento  [localhost:8080/finalizar](http://localhost:8080/finalizar)
```
{
	"solicitacaoId": 1
}
```
Ao finalizar a solicitação de atendimento, o status dela muda para finalizado, assim liberando um atendente para atendimento.
Ao chamar novamente o [localhost:8080/sac](http://localhost:8080/sac) ele atualiza a lista de solicitações de atendimento realocando o primeiro da fila de espera para o atendente que está disponível

