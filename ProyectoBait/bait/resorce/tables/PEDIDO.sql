CREATE TABLE IF NOT EXISTS "Pedido" (
	"idPedido"	TEXT NOT NULL,
	"unidades"	INTEGER,
	"total"	REAL,
	"fechaPedido"	TEXT,
	"dni"	TEXT NOT NULL,
	"idProducto"	TEXT NOT NULL,
	FOREIGN KEY("dni") REFERENCES "Usuario"("dni"),
	FOREIGN KEY("idProducto") REFERENCES "Producto"("idProducto"),
	PRIMARY KEY("idPedido")
);