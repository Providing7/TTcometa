CREATE DATABASE sistema;


CREATE TABLE endereco(
	id SERIAL PRIMARY KEY,
	rua VARCHAR(255) NOT NULL,
	numero VARCHAR(5) NOT NULL,
	cep VARCHAR(10),
	bairro VARCHAR(255)
);

CREATE TABLE cliente(
	id SERIAL PRIMARY KEY,
 	nome VARCHAR(255) NOT NULL,
	cpf VARCHAR(14) UNIQUE,
	cnpj VARCHAR(14) UNIQUE,
	endereco_id INT REFERENCES endereco(id)
);

CREATE TABLE mercadoria(
	id SERIAL PRIMARY KEY,
	nome VARCHAR(255) NOT NULL,
	peso DECIMAL(10,2),
	volume DECIMAL (10,2),
	valor DECIMAL(10,2) NOT NULL
);

CREATE TABLE cliente_endereco(
	id SERIAL PRIMARY KEY,
	cliente_id INT NOT NULL REFERENCES cliente(id) ON DELETE CASCADE,
	endereco_id INT NOT NULL REFERENCES endereco(id) ON DELETE CASCADE,
	tipo VARCHAR(70) NOT NULL
);

CREATE TABLE entrega(
  id SERIAL PRIMARY KEY,
  remetente_id INT REFERENCES cliente(id),
  destinatario_id INT REFERENCES cliente(id), 
  endereco_entrega_id INT REFERENCES endereco(id)
);

CREATE TABLE entrega_mercadoria(
    entrega_id INT NOT NULL REFERENCES entrega(id) ON DELETE CASCADE,
    mercadoria_id INT NOT NULL REFERENCES mercadoria(id) ON DELETE CASCADE,
    quantidade INT NOT NULL,
    PRIMARY KEY (entrega_id, mercadoria_id)
);
