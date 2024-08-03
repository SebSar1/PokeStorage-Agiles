-- database: ../Data/bd.sqlite

DROP TABLE IF EXISTS Usuario;
DROP TABLE IF EXISTS UsuarioSexo;





CREATE TABLE UsuarioSexo (
    IdUsuarioSexo INTEGER PRIMARY KEY AUTOINCREMENT,
    Nombre TEXT NOT NULL UNIQUE,
    Estado         VARCHAR(1)  NOT NULL DEFAULT('A'),
    FechaCrea DATE DEFAULT CURRENT_TIMESTAMP,
    FechaModifica DATE
);





CREATE TABLE Usuario (
    IdUsuario           INTEGER PRIMARY KEY AUTOINCREMENT,
    Nombre          TEXT NOT NULL,
    Contraseña       TEXT NOT NULL,
    IdUsuarioSexo   INTEGER REFERENCES UsuarioSexo (IdUsuarioSexo),
    Edad            INTEGER NOT NULL,
    FechaCrea       DATE DEFAULT CURRENT_TIMESTAMP,
    FechaModifica       DATE
);






