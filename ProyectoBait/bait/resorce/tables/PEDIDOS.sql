CREATE TABLE IF NOT EXISTS "PEDIDOS" (
	"idPedido"	varchar(25) NOT NULL,
	"unidades"	int,
	"total"	float,
	"fechaPedido"	varchar(25),
	"idCliente"	varchar(25) NOT NULL,
	"idProducto"	varchar(25) NOT NULL,
	FOREIGN KEY("idCliente") REFERENCES "Usuario"("dni") ON DELETE CASCADE,
	FOREIGN KEY("idProducto") REFERENCES "Producto"("idProducto"),
	PRIMARY KEY("idPedido")
);