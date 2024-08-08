# Diagramas UML

O que fazer:

**a)** definir um problema: descreva textualmente um problema que você gostaria de
resolver por meio de um sistema, ou seja, o enunciado de um sistema que você imagina
desenvolver. Valide este enunciado com a professora.

**b)** Com base no enunciado, modelo o Diagrama de componentes contendo no mínimo
5 componentes. Esse diagrama deve representar uma parte do sistema, além de
relacionar os componentes que fornecem e os que consomem.

**c)** Com base no mesmo enunciado, modelo o Diagrama de implantação (com
componentes do diagrama de networking) representando como funciona a arquitetura
do sistema a nível de implantação (banco de dados, cliente-servidor, navegador,
bibliotecas, etc)

## Definindo o Problema

Imagine que um restaurante está expandindo seu sistema de delivery. Ele já utilizava uma plataforma geral para realizar os pedidos, mas agora ele quer
utilizar uma plataforma própria personalizada, assim evitando as taxas de empresas terceiras. Os clientes irão realizar seus pedidos através de um aplicativo
mobile. Neste aplicativo, eles precisarão realizar um cadastro com suas informações pessoais, de endereço e de pagamento.

O restaurante irá acessar o sistema de pedidos através de um aplicativo web, onde poderão acompanhar os pedidos em aberto. Nesse aplicativo também será
possível cadastrar novos produtos.

Todas as informações, tanto dos clientes quanto do restaurante e dos pedidos ficarão salvos em um servidor de banco de dados, que utilizará o SGBD Oracle.

Tudo isso será interligado por uma API REST em um servidor. A API será construída em Java utilizando o framework Spring Boot.
