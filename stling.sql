DROP TABLE produto;
CREATE TABLE produto ( id int NOT NULL AUTO_INCREMENT, descricao varchar(250), valor decimal(6,2), PRIMARY KEY (id) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE tipo_usuario;
CREATE TABLE tipo_usuario ( id int NOT NULL AUTO_INCREMENT, descricao varchar(255), PRIMARY KEY (id) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
DROP TABLE usuario;
CREATE TABLE usuario ( id int NOT NULL AUTO_INCREMENT, nome varchar(255), email varchar(255), senha varchar(255), idtipousuario int, PRIMARY KEY (id), INDEX usuario_tipo_usuario_fk (idtipousuario) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
ALTER TABLE usuario ADD CONSTRAINT usuario_tipo_usuario_fk FOREIGN KEY (idtipousuario) REFERENCES tipo_usuario (id);
