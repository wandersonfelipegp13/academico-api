create table turma (
	id bigint not null auto_increment,
	turno varchar(255) not null,
	data_inicio datetime not null,
	data_fim datetime,
	horario varchar(255) not null,
	qtd_vagas bigint not null,
	
	primary key(id)
);
