CREATE TABLE IF NOT EXISTS "USUARIOS" (
	"dni"	varchar(25) NOT NULL,
	"nombre"	varchar(25),
	"apellidos"	varchar(25),
	"email"	varchar(25),
	"direccion"	varchar(25),
	"telefono"	varchar(25),
	"pais"	varchar(25),
	"codigoPostal"	varchar(25),
	"provincia"	varchar(25),
	"nombreUsuario"	varchar(25),
	"password"	varchar(25),
	"rol"	varchar(25),
	"saldo"	float,
	PRIMARY KEY("dni")
);