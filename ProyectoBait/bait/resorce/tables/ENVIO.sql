CREATE TABLE IF NOT EXISTS "Envio" (
	"idEnvio"	TEXT NOT NULL,
	"idPedido"	TEXT NOT NULL,
	"fechaEnvio"	TEXT,
	"estado"	TEXT,
	FOREIGN KEY("idPedido") REFERENCES "Pedido"("idPedido"),
	PRIMARY KEY("idEnvio")
);