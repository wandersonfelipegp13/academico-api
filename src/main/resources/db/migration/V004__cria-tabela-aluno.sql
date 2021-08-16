create table aluno (
	id bigint not null auto_increment,
	nome varchar(60) not null,
	cpf varchar(20) not null,
	email varchar(255) not null,
	telefone varchar(20) not null,
	turma_id bigint not null,
		
	primary key(id)
);

alter table aluno add constraint fk_aluno_turma foreign key (turma_id) references turma (id);