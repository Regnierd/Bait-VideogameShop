CREATE TABLE IF NOT EXISTS "COMPTAS" (
	"idCompra"	TEXT NOT NULL,
	"idPedido"	TEXT NOT NULL,
	"totalCompra"	REAL,
	FOREIGN KEY("idPedido") REFERENCES "Pedido"("idPedido"),
	PRIMARY KEY("idCompra")
);