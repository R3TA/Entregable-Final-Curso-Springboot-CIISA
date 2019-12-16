--Cliente --usuario --roles
--Pelicula --genero
--Detalle venta -- venta

INSERT INTO comida(nombre, precio) VALUES('Papas fritas', 1000)
INSERT INTO comida(nombre, precio) VALUES('Algodon de azucar', 500)
INSERT INTO comida(nombre, precio) VALUES('Pop corn', 1000)

INSERT INTO usuario (password, enabled, username) VALUES ('$2a$10$9I9r4BttoBIJL7p6m4ht0eIvVi7u9KyUNEIZFq5ZkgcJdlSk89Pj2',true,'martin');
INSERT INTO usuario (password, enabled, username) VALUES ('$2a$10$RmdEsvEfhI7Rcm9f/uZXPebZVCcPC7ZXZwV51efAvMAp1rIaRAfPK',true,'admin');

INSERT INTO cliente (apellidos, fecha_nac, nombres, id_usuario) VALUES('Washington', '1711-01-01', 'George', 1);
INSERT INTO cliente (apellidos, fecha_nac, nombres, id_usuario) VALUES('Lincoln', '1456-01-01', 'Abraham', 2);
--INSERT INTO cliente (nombres, apellidos, fecha_nac, id_usuario) VALUES('John', 'kennedy','2018-01-01', 3);
--INSERT INTO cliente (nombres, apellidos, fecha_nac, id_usuario) VALUES('George', 'Bush','2018-01-01', 4);
--INSERT INTO cliente (nombres, apellidos, fecha_nac, id_usuario) VALUES('Bill', 'Clinton','2018-01-01', 5);
--INSERT INTO cliente (nombres, apellidos, fecha_nac, id_usuario) VALUES('Barack', 'Obama','2018-01-01', 6);

INSERT INTO role (nombre) VALUES ('ROLE_USER');
INSERT INTO role (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuario_role (id_usuario, id_role) VALUES (1, 1);
INSERT INTO usuario_role (id_usuario, id_role) VALUES (2, 2);
INSERT INTO usuario_role (id_usuario, id_role) VALUES (2, 1);

INSERT INTO genero(nombre) VALUES ('Ciencia ficcion')
INSERT INTO genero(nombre) VALUES ('Artes marciales')
INSERT INTO genero(nombre) VALUES ('Comedia')
INSERT INTO genero(nombre) VALUES ('Suspenso')
INSERT INTO genero(nombre) VALUES ('Terror')
INSERT INTO genero(nombre) VALUES ('Documental')

INSERT INTO pelicula(nombre, resena, duracion, fecha_publicacion, url_portada, id_genero) VALUES ('Volver al futuro I', 'Marty Mc fly back to the future with the professor emeth brown for first date', 150, '1981-01-03' ,'www.backtothefutureI.com',1)
INSERT INTO pelicula(nombre, resena, duracion, fecha_publicacion, url_portada, id_genero) VALUES ('Volver al futuro II', 'Marty Mc fly back to the future with the professor emeth brown for second date', 151, '1982-01-03' ,'www.backtothefutureII.com',1)