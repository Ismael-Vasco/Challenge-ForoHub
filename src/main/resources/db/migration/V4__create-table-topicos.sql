create table topicos(
    id bigint not null auto_increment,
    titulo varchar(500) not null,
    mensajes varchar(1000) not null,
    fecha_creacion datetime not null,
    fecha_actualizacion datetime not null,
    status int not null,
    autor_id bigint not null,
    curso_id bigint not null,
    respuesta bigint,

    primary key(id),

    constraint fk_topicos_autor_id foreign key(autor_id) references autores(id),
    constraint fk_topicos_curso_id foreign key(curso_id) references cursos(id)

);