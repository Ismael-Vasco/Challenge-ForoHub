create table respuestas(
    id bigint not null auto_increment,
    topico_id bigint not null,
    titulo varchar(500) not null,
    mensajes varchar(1000) not null,
    fecha_creacion datetime not null,
    autor_pregunta varchar(100) not null,
    autor_respuesta bigint not null,
    fecha_respuesta datetime not null,
    solucion varchar(1000) not null,

    primary key(id),

    constraint fk_respuestas_topico_id foreign key(topico_id) references topicos(id),
    constraint fk_respuestas_autor_respuesta foreign key(autor_respuesta) references autores(id)
);