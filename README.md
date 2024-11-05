Create table Estado (
ID_Estado Varchar2(64) primary key,
Estado Varchar2 (10)
);

Insert into Estado (ID_Estado, Estado) values ('aspasdasddsad','Completo');
Insert into Estado (ID_Estado, Estado) values ('sdaassdkuaskhd','Incompleto');
Insert into Estado (ID_Estado, Estado) values ('aspasdasddsadlsda','Pausa');
Commit
Create table Cliente (
ID_Cliente Varchar2 (64) Primary key,
Telefono Varchar2 (10),
Nombre Varchar2 (35)
);

Select * from Estado



Create table Problemas(
ID_Problema Varchar2(64) Primary key,
Problema Varchar2 (250),
Descripcion Varchar2 (250),
ID_Vehiculo Varchar2(64),
ID_Estado Varchar2(64),

FOREIGN KEY (ID_Vehiculo) REFERENCES Vehiculo(ID_Vehiculo),
FOREIGN KEY (ID_Estado) REFERENCES Estado(ID_Estado)

);

Create table Vehiculo(
ID_Vehiculo Varchar2(64) Primary key,
Marca Varchar2(20),
Modelo Varchar2(20),
Año Varchar2(10),
ID_Cliente Varchar2(64),

FOREIGN KEY (ID_Cliente) REFERENCES Cliente(ID_Cliente)

);

Create table Usuario (
ID_Usuario Varchar2(64) primary key,
Usuario Varchar2 (20),
Contraseña Varchar2 (64)
)

Select * from Vehiculo
