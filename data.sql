CREATE DATABASE IF NOT EXISTS calificaciones;

CREATE TABLE IF NOT EXISTS alumno(
	NumeroLista 	int(10) auto_increment not null,
	Nombre 	char(50) not null,
	ApellidoPaterno 	char(50) not null,
    D1   int(10) not null,
    D2   int(10) not null,
    D3   int(10) not null,
    PromedioRedondeado   int(10) not null,
	CONSTRAINT pk_alumno PRIMARY KEY(NumeroLista)
)ENGINE=InnoDb;
