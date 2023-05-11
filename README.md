# Cadastro de usuários
API Java com Spring Boot e banco de dados Postgresql utilizando Docker para cadastro de usuários

## Pré-requisitos
  ##### Git
  ##### JDK 17
  ##### Maven
  ##### Docker

## Baixando e rodando a aplicação
  Na pasta desejada efetue o seguinte comando:
  
    git clone https://github.com/ricardeck/usuarios.git

Para entrar na pasta docker execute o seguinte comando:
	
    cd cadastroDeUsuarios/docker/
  
Já dentro da pasta docker, efetue o seguinte comando:

    docker-compose up

Pronto, a aplicação já está no ar, para acessar o swagger entre no seguinte endereço:
  
    http://localhost:8080/swagger-ui/index.html#/
  
  ![image](https://github.com/ricardeck/cadastroDeUsuarios/assets/31116440/90e79372-dfd2-44de-a31d-df08a0d1777d)
  
## Exemplos de payloads

##### Sucesso:	
	{
	    "id": 1,
	    "nome": "jao da silva",
	    "email": "jao@silva.com",
	    "dataNascimento": "01-05-2003",
	    "endereco": "Rua das flores, 9. CEP: 415-02144.",
	    "habilidades": [
		"java",
		"bd",
		"spring"
	    ]
	}

##### Erro devido ao nome inválido:
	{
	    "id": 1,
	    "nome": "jao da silva 1",
	    "email": "jao@silva.com",
	    "dataNascimento": "01-05-2003",
	    "endereco": "Rua das flores, 9. CEP: 415-02144.",
	    "habilidades": [
		"java",
		"bd",
		"spring"
	    ]
	}

##### Erro devido ao email inválido:
	{
	    "id": 1,
	    "nome": "jao da silva",
	    "email": "jaosilva.com",
	    "dataNascimento": "01-05-2003",
	    "endereco": "Rua das flores, 9. CEP: 415-02144.",
	    "habilidades": [
		"java",
		"bd",
		"spring"
	    ]
	}
	
##### Erro devido a data de nascimento não permitida (menor de 18 anos):
	{
	    "id": 1,
	    "nome": "jao da silva",
	    "email": "jao@silva.com",
	    "dataNascimento": "01-05-2006",
	    "endereco": "Rua das flores, 9. CEP: 415-02144.",
	    "habilidades": [
		"java",
		"bd",
		"spring"
	    ]
	}





##### Erro devido ao endereço inválido:
	{
	    "id": 1,
	    "nome": "jao da silva",
	    "email": "jao@silva.com",
	    "dataNascimento": "01-05-2003",
	    "endereco": "Rua das flores, 9. CEP: 415-02144 $.",
	    "habilidades": [
		"java",
		"bd",
		"spring"
	    ]
	}
