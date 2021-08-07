create table curso (
	id bigint not null auto_increment,
	nome varchar(60) not null,
	descricao text not null,
	preco decimal(10, 2) not null,
	nivel varchar(20) not null,
	status varchar(20) not null,
	professor_id bigint not null,
	
	primary key(id)
);

alter table curso add constraint fk_curso_professor foreign key (professor_id) references professor (id);