CREATE TABLE IF NOT EXISTS "PEDIDOS" (
	"idPedido"	TEXT NOT NULL,
	"unidades"	INTEGER,
	"total"	REAL,
	"fechaPedido"	TEXT,
	"idCliente"	TEXT NOT NULL,
	"idProducto"	TEXT NOT NULL,
	FOREIGN KEY("idCliente") REFERENCES "Usuario"("dni"),
	FOREIGN KEY("idProducto") REFERENCES "Producto"("idProducto"),
	PRIMARY KEY("idPedido")
);