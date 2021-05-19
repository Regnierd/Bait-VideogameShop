CREATE TABLE IF NOT EXISTS "Usuario" (
	"dni"	TEXT NOT NULL,
	"nombre"	TEXT,
	"apellidos"	TEXT,
	"email"	TEXT,
	"direccion"	TEXT,
	"telefono"	TEXT,
	"pais"	TEXT,
	"codigoPostal"	TEXT,
	"provincia"	TEXT,
	"nombreUsuario"	TEXT,
	"password"	TEXT,
	"rol"	TEXT,
	"saldo"	REAL,
	PRIMARY KEY("dni")
);