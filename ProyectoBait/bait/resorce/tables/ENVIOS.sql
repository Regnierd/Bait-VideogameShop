CREATE TABLE IF NOT EXISTS "ENVIOS" (
	"idEnvio"	varchar(25) NOT NULL,
	"idPedido"	varchar(25) NOT NULL,
	"fechaEnvio"	varchar(25),
	"estado"	varchar(25),
	FOREIGN KEY("idPedido") REFERENCES "Pedido"("idPedido") ON DELETE CASCADE,
	PRIMARY KEY("idEnvio")
);