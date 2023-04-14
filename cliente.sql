Criação da table Cliente

create table cliente(
    cpf serial primary key NOT NULL,
    nome VARCHAR(50),
    email VARCHAR(50),
    telefone VARCHAR(50),
    senha VARCHAR(50),
    
    cep VARCHAR(50),
    logradouro VARCHAR(50),
    complemento VARCHAR(50),
    bairro VARCHAR(50),
    localidade VARCHAR(50),
    uf VARCHAR(50),
);

Alterando coluna de int para varchar

alter table cliente alter column cpf type varchar;
alter table cliente add column rg character varying ;
alter table cliente alter column rg set not null;


create table bank (
    ID_bank serial primary key not null,
    nome VARCHAR(50) not null,
    agencia INT not null,
    numeroConta bigint,
    saldo float,
    tipo VARCHAR(50) not null
   
);

create table endereco (
    id_endereco serial primary key not null,
    cep VARCHAR(50) not null,
    logradouro VARCHAR(100) not null,
    complemento VARCHAR(100),
    bairro VARCHAR(100) not null,
	localidade VARCHAR(100) not null,
	uf VARCHAR(5) not null
   
);

alter table bank alter column ID_bank type varchar;
alter table bank add FOREIGN KEY (ID_bank) references cliente (cpf);

alter table endereco alter column id_endereco type varchar;
alter table endereco add FOREIGN KEY (id_endereco) references cliente (cpf);

