#  LevelUp Arcade — Resumen del Proyecto

LevelUp Arcade es una aplicación de escritorio desarrollada en Java como proyecto del módulo DAM. El sistema simula la gestión de una tienda tipo arcade y permite administrar usuarios, productos, clientes y pedidos.

---

##  Qué hace el sistema

La aplicación permite:

- Inicio de sesión de usuarios con roles (ADMIN y EMPLEADO)
- Gestión de productos (crear, editar, eliminar y listar)
- Gestión de clientes
- Gestión de pedidos con cálculo automático
- Uso de IA (OpenRouter) para generar descripciones de productos

---

##  Cómo está hecho el proyecto

El proyecto sigue una arquitectura **MVC**:

- **Modelo** → clases como Usuario, Producto, Cliente y Pedido
- **Vista** → interfaces gráficas con Swing (LoginView, MainApp, etc.)
- **Controlador** → lógica del sistema
- **DAO** → acceso a base de datos con JDBC

---

##  Base de datos

Se utiliza MySQL/MariaDB con la base de datos:

levelup_arcade

Tablas principales:
- usuario
- cliente
- producto
- pedido
- detalle_pedido

---

##  Conexión y configuración

La conexión a la base de datos se gestiona con:

- DatabaseConnection → conexión JDBC
- ConfigLoader → carga del archivo config.properties

El archivo de configuración contiene:

db.url, db.user, db.password y configuración de OpenRouter.

---

##  Login y seguridad

El sistema valida usuarios contra la base de datos.

- ADMIN → acceso completo
- EMPLEADO → acceso limitado

---

##  Ejecución del programa

La aplicación se ejecuta desde un archivo JAR:

java -jar LevelUpArcade.jar

---

##  Tecnologías utilizadas

- Java
- Swing
- MySQL / MariaDB
- JDBC
- MVC
- OpenRouter API

---

## ‍ Estado del proyecto

El proyecto está completamente funcional e incluye:

- conexión a base de datos
- sistema de login
- CRUD completo
- integración con IA
- configuración externa con properties

---

##  Conclusión

LevelUp Arcade es una aplicación completa desarrollada en Java que combina base de datos, interfaz gráfica y servicios externos, aplicando arquitectura MVC y buenas prácticas de programación.