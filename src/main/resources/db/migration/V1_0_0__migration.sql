CREATE TABLE public.categoria (
	id serial NOT NULL,
	created_at timestamp NULL,
	creator varchar(255) NULL,
	updated_at timestamp NULL,
	updater varchar(255) NULL,
	nome varchar(255) NULL,
	CONSTRAINT categoria_pkey PRIMARY KEY (id)
);

CREATE TABLE public.cidade (
	id serial NOT NULL,
	created_at timestamp NULL,
	creator varchar(255) NULL,
	updated_at timestamp NULL,
	updater varchar(255) NULL,
	nome varchar(255) NULL,
	estado_id int4 NULL,
	CONSTRAINT cidade_pkey PRIMARY KEY (id)
);

CREATE TABLE public.cliente (
	id serial NOT NULL,
	created_at timestamp NULL,
	creator varchar(255) NULL,
	updated_at timestamp NULL,
	updater varchar(255) NULL,
	cpf_ou_cnpj varchar(255) NULL,
	email varchar(255) NULL,
	nome varchar(255) NULL,
	tipo int4 NULL,
	CONSTRAINT cliente_pkey PRIMARY KEY (id)
);

CREATE TABLE public.endereco (
	id serial NOT NULL,
	created_at timestamp NULL,
	creator varchar(255) NULL,
	updated_at timestamp NULL,
	updater varchar(255) NULL,
	bairro varchar(255) NULL,
	cep varchar(255) NULL,
	complemento varchar(255) NULL,
	logradouro varchar(255) NULL,
	numero varchar(255) NULL,
	cidade_id int4 NULL,
	cliente_id int4 NULL,
	CONSTRAINT endereco_pkey PRIMARY KEY (id)
);

CREATE TABLE public.estado (
	id serial NOT NULL,
	created_at timestamp NULL,
	creator varchar(255) NULL,
	updated_at timestamp NULL,
	updater varchar(255) NULL,
	nome varchar(255) NULL,
	CONSTRAINT estado_pkey PRIMARY KEY (id)
);

CREATE TABLE public.item_pedido (
	desconto float8 NULL,
	preco float8 NULL,
	quantidade int4 NULL,
	pedido_id int4 NOT NULL,
	produto_id int4 NOT NULL,
	CONSTRAINT item_pedido_pkey PRIMARY KEY (pedido_id, produto_id)
);

CREATE TABLE public.pagamento (
	pedido_id int4 NOT NULL,
	created_at timestamp NULL,
	creator varchar(255) NULL,
	updated_at timestamp NULL,
	updater varchar(255) NULL,
	estado int4 NULL,
	CONSTRAINT pagamento_pkey PRIMARY KEY (pedido_id)
);

CREATE TABLE public.pagamento_com_boleto (
	data_pagamento date NULL,
	data_vencimento date NULL,
	pedido_id int4 NOT NULL,
	CONSTRAINT pagamento_com_boleto_pkey PRIMARY KEY (pedido_id)
);

CREATE TABLE public.pagamento_com_cartao (
	numero_de_parcelas int4 NULL,
	pedido_id int4 NOT NULL,
	CONSTRAINT pagamento_com_cartao_pkey PRIMARY KEY (pedido_id)
);

CREATE TABLE public.pedido (
	id serial NOT NULL,
	created_at timestamp NULL,
	creator varchar(255) NULL,
	updated_at timestamp NULL,
	updater varchar(255) NULL,
	instante timestamp NULL,
	cliente_id int4 NULL,
	endereco_id int4 NULL,
	CONSTRAINT pedido_pkey PRIMARY KEY (id)
);

CREATE TABLE public.produto (
	id serial NOT NULL,
	created_at timestamp NULL,
	creator varchar(255) NULL,
	updated_at timestamp NULL,
	updater varchar(255) NULL,
	nome varchar(255) NULL,
	preco float8 NULL,
	CONSTRAINT produto_pkey PRIMARY KEY (id)
);

CREATE TABLE public.produto_categoria (
	produto_id int4 NOT NULL,
	categoria_id int4 NOT NULL
);

CREATE TABLE public.telefone (
	cliente_id integer not null, 
	telefones varchar(255)
);

ALTER TABLE public.cidade ADD CONSTRAINT fkkworrwk40xj58kevvh3evi500 FOREIGN KEY (estado_id) REFERENCES estado(id);
ALTER TABLE public.endereco ADD CONSTRAINT fk8b1kcb3wucapb8dejshyn5fsx FOREIGN KEY (cidade_id) REFERENCES cidade(id);
ALTER TABLE public.endereco ADD CONSTRAINT fk8s7ivtl4foyhrfam9xqom73n9 FOREIGN KEY (cliente_id) REFERENCES cliente(id);
ALTER TABLE public.item_pedido ADD CONSTRAINT fk60ym08cfoysa17wrn1swyiuda FOREIGN KEY (pedido_id) REFERENCES pedido(id);
ALTER TABLE public.item_pedido ADD CONSTRAINT fktk55mn6d6bvl5h0no5uagi3sf FOREIGN KEY (produto_id) REFERENCES produto(id);
ALTER TABLE public.pagamento ADD CONSTRAINT fkthad9tkw4188hb3qo1lm5ueb0 FOREIGN KEY (pedido_id) REFERENCES pedido(id);
ALTER TABLE public.pagamento_com_boleto ADD CONSTRAINT fkcr74vrxf8nfph0knq2bho8doo FOREIGN KEY (pedido_id) REFERENCES pagamento(pedido_id);
ALTER TABLE public.pagamento_com_cartao ADD CONSTRAINT fkta3cdnuuxclwfh52t4qi432ow FOREIGN KEY (pedido_id) REFERENCES pagamento(pedido_id);
ALTER TABLE public.pedido ADD CONSTRAINT fk30s8j2ktpay6of18lbyqn3632 FOREIGN KEY (cliente_id) REFERENCES cliente(id);
ALTER TABLE public.pedido ADD CONSTRAINT fksot2og0lvhm2t4kuu9s2obef3 FOREIGN KEY (endereco_id) REFERENCES endereco(id);
ALTER TABLE public.produto_categoria ADD CONSTRAINT fk1c0y58d3n6x3m6euv2j3h64vt FOREIGN KEY (produto_id) REFERENCES produto(id);
ALTER TABLE public.produto_categoria ADD CONSTRAINT fkq3g33tp7xk2juh53fbw6y4y57 FOREIGN KEY (categoria_id) REFERENCES categoria(id);
