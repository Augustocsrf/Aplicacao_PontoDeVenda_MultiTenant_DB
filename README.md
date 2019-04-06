# Aplicacao_PontoDeVenda_MultiTenant_DB
Aplicação feita para o processo seletivo para a posição de Desenvolvedor Back End GOA da Involves. 

Um código em Java que lê os dados de um arquivo csv e os adiciona a um banco de dados com tabelas multi-tenant e disponibiliza endpoints pra que eles sejam visualizados e pesquisados. 

Ferramentas utilizadas: Java, Maven, MySQL, JPA, Hibernate, Spring e Curl.

Alguns códigos foram utilizados como referência, estudo, base e tutoriais para a criação deste, mas foram alterados ao longo do periodo em que eu utilizei eles.

Por um quesito de facilidade na hora de visualizar os dados, eu mantive a acesibilidade aos dados mesmo sem um tenantID, já que o Firefox me permitia ver eles de uma forma extremamente organizada em JSON.

ENDPOINTS

localhost:8080/all - Expõe todos os valores do Banco de Dados

localhost:8080/nome/valor - Expõe todas as linhas com o nome equivalente a "valor"

localhost:8080/cidade/valor - Expõe todas as linhas com a cidade equivalente a "valor"

localhost:8080/cep/valor - Expõe todas as linhas com o cep equivalente a "valor"

localhost:8080/tenant/valor - Expõe todas as linhas com o tenant equivalente a "valor"

localhost:8080/upload - Le arquivos pelo comando curl -F data=@Path, copia o arquivo para a pasta data dentro do aplicativo e adiciona os dados ao Banco

NOTAS IMPORTANTES Devido a alguns problemas e a minha própria inexperiência, admito, não consegui fazer as linhas descritas na descrição do desafio funcionarem perfeitamente. O sistema de tenant que eu uso não irá funcionar usando -F tenant=, para criar um tenant, será necessário usar o comando -H "tenant:some-tenant-id", já que o código em que me baseei usa o header para identificar o tenant.

-F data=@pdv_list.csv também não funcionará perfeitamente, será necessário usar -F data=@path/pdv_list.csv, nesse caso path pode ser tanto o endereço completo do arquivo quanto o endereço relativo entre o local em que o cmd foi aberto e o local em que o arquivo está. -F data=@pdv_list.csv entretanto deve funcionar corretamente se o arquivo em questão já estiver na pasta data dentro da pasta do programa.

A pasta target inclui a aplicação em .jar empacotada usando o maven, caso seja necessário usar isso também.

É necessário pro MySQL funcionar que a porta 3306 esteja aberta e que um banco de dados chamado pdv tenha sido criado. Uma tabela chamada pontodevenda será criada vazia automaticamente quando o programa rodar pela primeira vez. A aplicação em si usa a porta 8080.
