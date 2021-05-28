CREATE TABLE IF NOT EXISTS "PRODUCTOS" (
	"idProducto"	TEXT NOT NULL,
	"nombre"	TEXT,
	"precio"	REAL,
	"descripcion"	TEXT,
	"stock"	INTEGER,
	"idCategoria"	TEXT NOT NULL,
	"idMarca"	TEXT NOT NULL,
	FOREIGN KEY("idCategoria") REFERENCES "Categoria"("idCategoria"),
	FOREIGN KEY("idMarca") REFERENCES "Marca"("idMarca"),
	PRIMARY KEY("idProducto")
);