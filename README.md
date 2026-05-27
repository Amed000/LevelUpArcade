<div align="center">

<img src="https://img.shields.io/badge/Java-23-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
<img src="https://img.shields.io/badge/MySQL-8.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white"/>
<img src="https://img.shields.io/badge/Ubuntu_Server-24.04-E95420?style=for-the-badge&logo=ubuntu&logoColor=white"/>
<img src="https://img.shields.io/badge/OpenRouter-AI-6366f1?style=for-the-badge&logo=openai&logoColor=white"/>
<img src="https://img.shields.io/badge/Git-Control_Versiones-F05032?style=for-the-badge&logo=git&logoColor=white"/>

# 🎮 LevelUp Arcade — Sistema de Gestión

> **Aplicación de escritorio en Java para la gestión integral de inventario, clientes y proveedores, con integración de Inteligencia Artificial.**

</div>

---

## 📋 Índice

- [Descripción](#-descripción)
- [Características](#-características)
- [Tecnologías](#-tecnologías)
- [Arquitectura](#-arquitectura)
- [Base de datos](#-base-de-datos)
- [Instalación](#-instalación)
- [Uso](#-uso)
- [Inteligencia Artificial](#-inteligencia-artificial)
- [Seguridad](#-seguridad)
- [Pruebas](#-pruebas)
- [Estructura del proyecto](#-estructura-del-proyecto)
- [Memoria del proyecto](#-memoria-del-proyecto)
- [Equipo](#-equipo)

---

## 📖 Descripción

**LevelUp Arcade** es una empresa especializada en la venta de videojuegos y merchandising. Este proyecto nace ante la necesidad de modernizar su sistema de gestión, reemplazando hojas de cálculo desorganizadas y procesos manuales por una aplicación robusta, escalable y apoyada en IA.

El sistema permite gestionar de forma centralizada:
- 📦 Inventario de productos y categorías
- 👥 Clientes y proveedores
- 🤖 Generación de contenido con IA (descripciones, categorías sugeridas)
- 🔐 Control de acceso por roles de usuario

---

## ✨ Características

| Funcionalidad | Descripción |
|---|---|
| ✅ CRUD completo | Productos, categorías, clientes y proveedores |
| 🤖 IA integrada | Generación de descripciones y sugerencia de categorías vía OpenRouter |
| 🔐 Sistema de login | Roles diferenciados: Administrador y Empleado |
| 📝 Sistema de logs | Registro de actividad y errores con trazabilidad completa |
| 💾 Backups automáticos | Script de copia de seguridad con tarea cron en Ubuntu Server |
| 🖥️ GUI con Swing | Interfaz gráfica completa (Fase 10) |
| 🧪 Pruebas unitarias | Cobertura con JUnit |
| 📚 JavaDoc | Documentación completa del código |

---

## 🛠️ Tecnologías

- **Lenguaje:** Java 23
- **Base de datos:** MySQL 8.0
- **Servidor:** Ubuntu Server (VirtualBox)
- **API de IA:** [OpenRouter.ai](https://openrouter.ai)
- **Librerías:**
  - JDBC (conector MySQL)
  - Gson / Jackson (manejo de JSON)
  - JUnit (pruebas unitarias)
  - Java Swing (interfaz gráfica)
- **Control de versiones:** Git / GitHub
- **Gestión del proyecto:** Jira (Scrum + Kanban)
- **Modelado:** draw.io / StarUML

---

## 🏗️ Arquitectura

El proyecto sigue el patrón **MVC (Modelo-Vista-Controlador)**:

```
src/
├── model/          → Clases de datos y acceso a BD (JDBC)
├── view/           → Interfaz de consola y GUI (Swing)
├── controller/     → Lógica de negocio y coordinación
└── service/
    └── LlmService.java   → Integración con OpenRouter API
```

---

## 🗄️ Base de datos

El diseño de la base de datos está normalizado hasta **3FN** e incluye las siguientes entidades principales:

```
productos ──── categorias
    │
    ├──── proveedores
    │
clientes
    │
usuarios  (login con roles)
```

Los scripts SQL de creación están en:

```
database/
├── schema.sql       → Creación de tablas
└── data_sample.sql  → Datos de ejemplo
```

---

## 🚀 Instalación

### Requisitos previos

- Java 23 o superior
- MySQL 8.0 o superior
- Maven (opcional)

### Pasos

```bash
# 1. Clona el repositorio
git clone https://github.com/tu-usuario/levelup-arcade.git
cd levelup-arcade

# 2. Importa la base de datos
mysql -u root -p < database/schema.sql
mysql -u root -p levelup_arcade < database/data_sample.sql

# 3. Configura las credenciales
# Copia el archivo de ejemplo y edítalo
cp config/config.example.properties config/config.properties
# Edita: db.url, db.user, db.password, llm.api.key

# 4. Compila y ejecuta
javac -cp lib/* -d out src/**/*.java
java -cp out:lib/* Main
```

### Ejecutar el `.jar`

```bash
java -jar levelup-arcade.jar
```

---

## 💻 Uso

Al iniciar la aplicación se solicitan credenciales de acceso:

```
╔══════════════════════════════╗
║    🎮  LevelUp Arcade        ║
║    Sistema de Gestión        ║
╚══════════════════════════════╝

Usuario: admin
Contraseña: ********

[1] Gestión de Productos
[2] Gestión de Clientes
[3] Gestión de Proveedores
[4] Gestión de Categorías
[5] 🤖 Herramientas de IA
[6] Gestión de Usuarios
[0] Salir
```

**Roles disponibles:**
- `ADMIN` → acceso completo (CRUD + IA + gestión de usuarios)
- `EMPLEADO` → solo consulta (lectura)

---

## 🤖 Inteligencia Artificial

La clase `LlmService.java` gestiona la comunicación con la API de **OpenRouter**:

```java
// Ejemplo de uso
LlmService llm = new LlmService();

// Generar descripción de producto
String descripcion = llm.generarDescripcion("Nintendo Switch OLED");

// Sugerir categoría
String categoria = llm.sugerirCategoria("Funko Pop Zelda edición limitada");
```

> ⚠️ **La API Key nunca se incluye en el código fuente.** Se carga desde el archivo `config/config.properties`, que está en `.gitignore`.

---

## 🔐 Seguridad

- Las contraseñas se almacenan con **hash bcrypt**, nunca en texto plano
- Sistema de roles: `ADMIN` / `EMPLEADO`
- La API Key de OpenRouter se gestiona mediante variables de configuración externas
- Logs de actividad para auditoría

---

## 🧪 Pruebas

Las pruebas unitarias están implementadas con **JUnit** y cubren:

- Operaciones CRUD de todas las entidades
- Validación de datos de entrada
- Casos límite y situaciones de error
- Gestión de excepciones en operaciones con BD

```bash
# Ejecutar pruebas
java -cp out:lib/* org.junit.runner.JUnitCore TestSuite
```

---

## 📁 Estructura del proyecto

```
levelup-arcade/
├── src/
│   ├── model/
│   ├── view/
│   ├── controller/
│   └── service/
│       └── LlmService.java
├── test/
├── database/
│   ├── schema.sql
│   └── data_sample.sql
├── config/
│   └── config.example.properties
├── scripts/
│   └── backup.sh           ← Script de backup automático
├── docs/
│   └── javadoc/
├── memoria-web/            ← Fase 9: web de documentación
├── lib/                    ← Dependencias .jar
├── .gitignore
└── README.md
```

---

## 📚 Memoria del proyecto

La documentación completa del proyecto está disponible como web en la carpeta `memoria-web/`, construida con HTML, CSS y JavaScript.

Abre `memoria-web/index.html` en tu navegador para acceder a la memoria completa con todas las fases documentadas.

---

## 👥 Equipo

| Nombre | Rol |
|--------|-----|
| AHMED SALEM GHARAGA | Desarrollador / Analista |
| AHMED SALEM GHARAGA | Desarrollador / DBA |

---

<div align="center">

**Proyecto desarrollado como trabajo final de curso.**

*Java · MySQL · Ubuntu Server · OpenRouter AI*

</div>
