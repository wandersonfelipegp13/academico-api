alter table turma ADD curso_id bigint not null AFTER qtd_vagas;
alter table turma add constraint fk_turma_curso foreign key (curso_id) references curso (id);