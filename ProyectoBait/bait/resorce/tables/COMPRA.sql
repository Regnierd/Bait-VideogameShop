CREATE TABLE IF NOT EXISTS "Compra" (
	"idCompra"	TEXT NOT NULL,
	"idPedido"	TEXT NOT NULL,
	"totalCompra"	REAL,
	FOREIGN KEY("idPedido") REFERENCES "Pedido"("idPedido"),
	PRIMARY KEY("idCompra")
);