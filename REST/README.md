# Web Service REST
## O que fazer:

Desenvolver um WebService REST com suporte a persistência de dados, aplicando algum framework de segurança

Tendo como base os conceitos de WebService REST e os demais assuntos estudados, desenvolva um servidor REST conforme especificado:

**a)** O servidor deve possuir conexão com o banco de dados

**b)** Deve haver pelo menos 2 tabelas que se relacionam, de modo que o relacionamento entre elas seja N-N, gerando assim uma terceira
tabela de normalização de dados

**c)** O projeto deve ser desenvolvido utilizando o padrão Data Access Object (DAO) e a arquitetura M(V)C

**d)** Para cada objeto deve-se fornecer os seguintes serviços em formato JSON: insert, delete, update, select by id, select all os quais
devem ser implementados utilizando framework de persistência de dados e devem ser validados, lançando exceções conforme os possíveis erros
previstos (lançar exceção com os códigos de erro HTTP)

**e)** Para cada objeto deve-se fornecer no mínimo 2 selects extras, além dos definidos na letra anterior

**f)** Além dos objetos já definidos, deve-se implementar serviços para gerenciar o usuário do sistema

**g)** Os serviços de insert e update só podem ser requisitados se o usuário tiver permissão e estiver autenticado no sistema, para isso,
deve-se implementar uma camada de segurança/autenticação
