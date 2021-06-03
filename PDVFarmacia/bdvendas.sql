/***** Luis Henrique Barbosa Gomes Filho *****/

CREATE DATABASE BDVENDAS;


CREATE USER 'teste'@'%' IDENTIFIED BY '123';

GRANT ALL ON *.* TO 'teste'@'%' WITH GRANT OPTION;


flush privileges;


USE BDVENDAS;

/***** TABELA CLIENTES *****/
CREATE TABLE tb_clientes (
  id int auto_increment primary key,
  nome varchar(100),
  rg varchar (30),
  cpf varchar (20),
  email varchar(200),
  telefone varchar(30),
  celular varchar(30),
  cep varchar(100),
  endereco varchar (255),
  numero int,
  complemento varchar (200),
  bairro varchar (100),
  cidade varchar (100),
  estado varchar (2)
);
/*****************/

/***** TABELA FORNECEDORES *****/
CREATE TABLE tb_fornecedores (
  id int auto_increment primary key,
  nome varchar(100),
  cnpj varchar (100),
  email varchar(200),
  telefone varchar(30),
  celular varchar(30),
  cep varchar(100),
  endereco varchar (255),
  numero int,
  complemento varchar (200),
  bairro varchar (100),
  cidade varchar (100),
  estado varchar (2)
);
/*****************/

/***** TABELA FUNCIONARIOS *****/
CREATE TABLE tb_funcionarios (
  id int auto_increment primary key,
  nome varchar(100),
  rg varchar (30),
  cpf varchar (20),
  email varchar(200),
  senha varchar(10),
  cargo varchar(100),
  nivel_acesso varchar(50),
  telefone varchar(30),
  celular varchar(30),
  cep varchar(100),
  endereco varchar (255),
  numero int,
  complemento varchar (200),
  bairro varchar (100),
  cidade varchar (100),
  estado varchar (2)
);
/*****************/


/***** TABELA PRODUTOS *****/
CREATE TABLE tb_produtos (
  id int auto_increment primary key,
  descricao varchar(100),
  preco decimal (10,2),
  qtd_estoque int,
  for_id int,

  FOREIGN KEY (for_id) REFERENCES tb_fornecedores(id)
);
/*****************/

/***** TABELA VENDAS *****/
CREATE TABLE tb_vendas (
  id int auto_increment primary key,
  cliente_id int,
  data_venda datetime,
  total_venda decimal (10,2),
  observacoes text,

  FOREIGN KEY (cliente_id) REFERENCES tb_clientes(id)

);
/*****************/

/***** TABELA ITENS_VENDAS *****/
CREATE TABLE tb_itensvendas (
  id int auto_increment primary key,
  venda_id int,
  produto_id int,
  qtd int,
  subtotal decimal (10,2),

  FOREIGN KEY (venda_id) REFERENCES tb_vendas(id),
  FOREIGN KEY (produto_id) REFERENCES tb_produtos(id)
);
/*****************/


 insert into tb_funcionarios values (1,'admin','11.809.665-1','145.111.111-67'
,'admin@gmail.com','123','Gerente','Administrador','(22)3820-9096','(22)98503-6155','55555-148'
,'Rua J oito',12,'altos','Mar do Norte','Rio de Janeiro','RJ');


insert into tb_fornecedores values(4,'Bayer','41.376.344/0001-87','bayer@reconciliare.com.br','(22) 3999-9075','(22) 98855-7777','28898-300'
 ,'Rua Dourada',1,'proximo ao A','Centro','Itaperuna','RJ');
 
 insert into tb_fornecedores values(5,'Pfizer','41.376.344/0001-44','pfzier@reconciliare.com.br','(22) 3999-1111','(22) 91111-7777','28545-300'
 ,'Rua cinco',163,'proximo ao B','Centro','Campos dos Goytacazes','RJ');
 
insert into tb_produtos values(1,'bromazepam',45,4,4);
insert into tb_produtos values(2,'buscopam',15,10,5);
insert into tb_produtos values(3,'Dramin',10,15,5);