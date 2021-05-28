CREATE TABLE IF NOT EXISTS "COMPRAS" (
	"idCompra"	TEXT NOT NULL,
	"idPedido"	TEXT NOT NULL,
	"totalCompra"	REAL,
	FOREIGN KEY("idPedido") REFERENCES "Pedido"("idPedido"),
	PRIMARY KEY("idCompra")
);