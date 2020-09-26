# Registro de turnos de empleados

_Apirest para asignar turnos de trabajo a empleados y consultar sus horarios_

## Comenzando 🚀

_Estas instrucciones te permitirán obtener una copia del proyecto en funcionamiento en tu máquina local para propósitos de desarrollo y pruebas._

Mira **Deployment** para conocer como desplegar el proyecto.


### Pre-requisitos 📋

_tener instalado mysql_

_instalar jdk 11_

_instalar maven_

_Tener un IDE que soporte springboot_

_Tener postman o cualquier otra software para probar apis rest_



### Instalación 🔧

_clonar el proyecto desde el repositorio_

```
git clone https://github.com/jetbill/Turnos-de-Empleados.git
```

_ejecutar el script database.sql para crear la base de datos_

_en el archivo application.properties, asignar el username y password de tu motor mysql_

```
spring.datasource.username=myuser
spring.datasource.password=mypassword
```

_Abrir el proyecto en tu editor de codigo_

_Ejecutarlo como una aplicacion springboot_

_por default se ejecuta en el puerto 8080_

## Probando la apirest⚙️

_Dentro del proyecto se encuentra un archivo import.sql que carga datos de prueba a la base de datos_

### Probar el endpoint de empleados🔩

_Listar empleados_

```
ingresa esta url en postman: http://localhost:8080/api/empleado y selecciona el metodo get
```
```
debera retornar una lista de empleados
```

_Buscar por id_

```
ingresa esta url en postman: http://localhost:8080/api/empleado/1  y selecciona el metodo get
```
```
debera retornar el empleado con id 1

{
    "id": 1,
    "nombre": "William",
    "apellido": "Renteria",
    "identificacion": 73196540
}
```

_Registar empleado_

```
ingresa esta url en postman: http://localhost:8080/api/empleado  y selecciona el metodo post
```

```
en el cuerpo del mensaje selecciona el formato json
y envia un json asi:
{
"nombre":"Jorge",
"apellido":"Gomez",
"identificacion":25154853

}
```
```
debera retornar los datos del nuevo empleado

{
    "empleado": {
        "id": 6,
        "nombre": "Jorge",
        "apellido": "Gomez",
        "identificacion": 25154853
    },
    "mensaje": "Se ha registrado exitosamente"
}
```

_Editar empleado_

```
ingresa esta url en postman: http://localhost:8080/api/empleado/6  y selecciona el metodo put
```

```
en el cuerpo del mensaje selecciona el formato json
y envia un json asi:
{
"nombre":"Jorge",
"apellido":"Morales",
"identificacion":25154853

}
```


```
debera retornar el empleado con el nuevo apellido
```

_Elimar empleado_

```
ingresa esta url en postman: http://localhost:8080/api/empleado/6  y selecciona el metodo delete
```




```
debera retornar un mensaje confirmando la eliminacion
```


### Probar el endpoint de turnos🔩


_Listar Turnos_

```
ingresa esta url en postman: http://localhost:8080/api/turnos y selecciona el metodo get
```
```
debera retornar una lista de turnos registrados
```

_Buscar por id_

```
ingresa esta url en postman: http://localhost:8080/api/turnos/1  y selecciona el metodo get
```
```
debera retornar el turno con id 1
```

_Registar turno_

```
ingresa esta url en postman: http://localhost:8080/api/turnos  y selecciona el metodo post
```

```
en el cuerpo del mensaje selecciona el formato json
y envia un json asi:
{
"hentrada": "2020-09-26 07:00:00",
"hsalida": "2020-09-26 17:00:00",
"dia": "Jueves",
"empleado":1

}
```
```
debera retornar los datos del nuevo turno
```

_Buscar turno por dia_

```
ingresa esta url en postman: http://localhost:8080/api/turnos/dias/lunes  y selecciona el metodo get
```


```
debera retornar los turnos del lunes
```

_Buscar turno del empleado_

```
ingresa esta url en postman: http://localhost:8080/api/turnos/cedula/73196540  y selecciona el metodo get
```


```
debera retornar los datos del empleado con identificacion 73196540
```

_Buscar por horas_

```
ingresa esta url en postman: http://localhost:8080/api/turnos/entradaysalida?hentrada=2020-09-26 07:00:00&hsalida=2020-09-26 17:00:00 y selecciona el metodo get
```


```
debera retornar los turnos en esas horas
```


## Construido con 🛠️

_Herramientas utlizadas_

*[Springboot](https://spring.io/projects/spring-boot) - Framewok de java para contruir aplicaciones empresariales
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/) - Editar de codigo version community
*[Postman](https://www.postman.com/) - Hacer peticiones a apis rest
*[Mysql](https://www.mysql.com/) - Motor de bases de datos



## Autor ✒️

_Menciona a todos aquellos que ayudaron a levantar el proyecto desde sus inicios_

* **William Renteria** - *Creador del proyecto* - [jetbill](https://github.com/jetbill/Turnos-de-Empleados) - (william.billmx@gmail.com)







⌨️ con ❤️ por[jetbill](https://github.com/jetbill)😊
