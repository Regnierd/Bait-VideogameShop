CREATE TABLE IF NOT EXISTS "PRODUCTOS" (
	"idProducto"	varchar(25) NOT NULL,
	"nombre"	varchar(25),
	"precio"	float,
	"descripcion"	varchar(25),
	"stock"	int,
	"idCategoria"	varchar(25) NOT NULL,
	"idMarca"	varchar(25) NOT NULL,
	FOREIGN KEY("idCategoria") REFERENCES "Categoria"("idCategoria"),
	FOREIGN KEY("idMarca") REFERENCES "Marca"("idMarca"),
	PRIMARY KEY("idProducto")
);