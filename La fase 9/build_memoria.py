from __future__ import annotations

import html
import re
import shutil
from pathlib import Path

from pypdf import PdfReader


ROOT = Path(__file__).parent
PDF = ROOT / "la fase 9.pdf"
SOURCE_IMAGES = ROOT / "pdf_imagenes_extraidas"
OUT = ROOT / "memoria-proyecto"
ASSETS = OUT / "assets"
CAPTURES = ASSETS / "capturas"
CSS_DIR = OUT / "css"
JS_DIR = OUT / "js"


PHASES = {
    1: {
        "title": "Análisis del Sistema",
        "short": "Requisitos, actores, casos de uso, historias de usuario y backlog.",
        "icon": "SYS",
        "objective": "Definir el alcance funcional y tecnico de LevelUp Arcade antes de comenzar el desarrollo.",
        "decisions": [
            "Se plantea una aplicacion Java conectada a MySQL/MariaDB para centralizar la informacion.",
            "Se separan perfiles de Administrador y Empleado para controlar permisos desde el inicio.",
            "Se incluyen funciones de IA con OpenRouter para automatizar descripciones y categorias.",
            "El sistema queda orientado a operaciones CRUD sobre productos, clientes, proveedores y pedidos.",
        ],
        "results": [
            "Requisitos funcionales y no funcionales documentados.",
            "Actores principales identificados con sus permisos.",
            "Casos de uso e historias de usuario preparados para guiar el desarrollo.",
            "Backlog inicial del proyecto organizado en Jira.",
        ],
        "images": ["p05_01.png", "p07_01.png", "p07_02.png"],
    },
    2: {
        "title": "Diseño del Sistema",
        "short": "Modelo de datos, normalización, UML, flujos y mockups de interfaz.",
        "icon": "DB",
        "objective": "Transformar los requisitos en una arquitectura de datos, clases y pantallas lista para implementar.",
        "decisions": [
            "La base de datos se normaliza hasta tercera forma normal para reducir duplicidades.",
            "Se definen entidades independientes para usuarios, clientes, productos, categorias, proveedores y pedidos.",
            "Los diagramas de flujo cubren altas, modificaciones y eliminaciones de productos.",
            "Los mockups anticipan una interfaz de gestion por modulos con tablas y acciones rapidas.",
        ],
        "results": [
            "Modelo conceptual, logico y fisico de la base de datos.",
            "Script SQL documentado con tablas, claves primarias y relaciones.",
            "Diagrama UML de clases principales.",
            "Mockups de panel, productos, clientes y proveedores.",
        ],
        "images": [
            "p08_01.jpg",
            "p08_02.png",
            "p09_01.png",
            "p09_02.png",
            "p09_03.png",
            "p10_01.png",
            "p11_01.png",
            "p11_02.png",
            "p11_03.png",
            "p12_01.jpg",
            "p12_02.jpg",
            "p13_01.jpg",
            "p13_02.jpg",
            "p13_03.jpg",
        ],
    },
    3: {
        "title": "Infraestructura y Entorno",
        "short": "Servidor Ubuntu, direccionamiento IP, MariaDB y conexion remota.",
        "icon": "NET",
        "objective": "Preparar el entorno donde se alojara la base de datos y comprobar la conexion desde herramientas externas.",
        "decisions": [
            "Se utiliza Ubuntu Server como entorno para MariaDB.",
            "La red se configura para permitir acceso remoto al servidor.",
            "La administracion de datos se valida con herramientas graficas de conexion SQL.",
            "Los scripts SQL de la fase 2 se ejecutan sobre la base real del sistema.",
        ],
        "results": [
            "Direccionamiento IP configurado para que el servidor sea accesible.",
            "MariaDB instalado y preparado.",
            "Conexion remota probada correctamente.",
            "Base de datos levelup_arcade creada e importada.",
        ],
        "images": ["p14_01.png", "p14_02.png", "p15_01.png", "p15_02.png"],
    },
    4: {
        "title": "Codificación",
        "short": "Implementacion MVC, persistencia, logs, JavaDoc y control de versiones.",
        "icon": "MVC",
        "objective": "Construir la aplicacion Java con una estructura mantenible y conexion real a la base de datos.",
        "decisions": [
            "La aplicacion se organiza con patron MVC para separar modelo, vista y controlador.",
            "La persistencia se realiza con JDBC y clases de conexion dedicadas.",
            "Los logs registran actividad y errores para facilitar trazabilidad.",
            "Git se usa para versionar avances con commits frecuentes.",
        ],
        "results": [
            "Proyecto Java organizado por paquetes.",
            "Menu de consola y primeras operaciones funcionando.",
            "Clase de logs y conexion a base de datos implementadas.",
            "Documentacion JavaDoc y commits del proyecto registrados.",
        ],
        "images": ["p16_01.png", "p17_01.png", "p17_02.png", "p18_01.png", "p18_02.png"],
    },
    5: {
        "title": "Inteligencia Artificial",
        "short": "OpenRouter, LlmService y funciones IA para productos.",
        "icon": "LLM",
        "objective": "Integrar un servicio LLM que ayude a crear descripciones y sugerir categorias de productos.",
        "decisions": [
            "OpenRouter se usa como proveedor para acceder a modelos de lenguaje.",
            "La logica se encapsula en LlmService.java para aislar la llamada HTTP y el parseo JSON.",
            "Los prompts se especializan en productos de tienda arcade.",
            "La IA se integra como apoyo al administrador, sin sustituir la gestion manual.",
        ],
        "results": [
            "API Key creada en OpenRouter.",
            "Clase LlmService implementada con metodo de prompt.",
            "Creación de descripciones de producto con ayuda de IA.",
            "Sugerencia de categoría a partir del nombre o características del producto.",
        ],
        "images": ["p19_01.png", "p20_01.png", "p20_02.png", "p21_01.png", "p21_02.png"],
    },
    6: {
        "title": "Pruebas y Calidad",
        "short": "Casos de prueba, JUnit, depuracion y backups automaticos.",
        "icon": "QA",
        "objective": "Validar que las operaciones principales funcionan y preparar salvaguardas de la base de datos.",
        "decisions": [
            "Los casos de prueba cubren CRUD, validaciones, errores y situaciones limite.",
            "JUnit se usa para pruebas unitarias sobre la logica principal.",
            "La depuracion se realiza desde el entorno de desarrollo para localizar fallos.",
            "Los backups se automatizan en Ubuntu con script y tarea cron.",
        ],
        "results": [
            "Plan de pruebas definido por modulo.",
            "Pruebas unitarias implementadas y ejecutadas.",
            "Errores detectados y corregidos durante la depuracion.",
            "Sistema de copias de seguridad automatizado para la base de datos.",
        ],
        "images": [
            "p22_01.png",
            "p22_02.png",
            "p23_01.png",
            "p23_02.png",
            "p24_01.png",
            "p24_02.png",
            "p24_03.png",
            "p24_04.png",
            "p24_05.png",
        ],
    },
    7: {
        "title": "Seguridad",
        "short": "Login, roles, permisos y almacenamiento seguro de contrasenas.",
        "icon": "SEC",
        "objective": "Proteger el acceso a la aplicacion y limitar las acciones segun el rol del usuario.",
        "decisions": [
            "Se diferencian permisos completos para Administrador y permisos de lectura para Empleado.",
            "Las contrasenas se almacenan con hash, no en texto plano.",
            "La tabla de usuarios guarda nombre, password_hash y rol.",
            "La interfaz debe adaptar acciones disponibles segun permisos.",
        ],
        "results": [
            "Sistema de login incorporado.",
            "Control de roles ADMIN y EMPLEADO documentado.",
            "Estructura SQL preparada para hashes de contrasena.",
            "Codigo Java de validacion y seguridad implementado.",
        ],
        "images": ["p25_01.png", "p26_01.png", "p26_02.png"],
    },
    8: {
        "title": "Despliegue",
        "short": "Generación del JAR, pruebas externas y manual de usuario.",
        "icon": "JAR",
        "objective": "Preparar la aplicación para que pueda ejecutarse fuera del entorno de desarrollo y dejar explicado su uso básico.",
        "decisions": [
            "Se decidió exportar el proyecto como Runnable JAR para que el usuario no tenga que abrir Eclipse.",
            "El archivo config.properties se mantiene junto al ejecutable para separar la configuración del código.",
            "La base de datos debe estar importada previamente con el nombre levelup_arcade.",
            "La prueba final se realiza fuera del entorno de desarrollo, simulando el uso real del programa.",
        ],
        "results": [
            "Se generó el archivo LevelUpArcade.jar.",
            "Se comprobó que la aplicación arranca correctamente desde consola con java -jar LevelUpArcade.jar.",
            "Se documentaron los requisitos: Java instalado, MySQL/MariaDB y base de datos levelup_arcade.",
            "El manual recoge login, roles y funciones principales de productos, clientes y pedidos.",
        ],
        "images": ["p27_01.png", "p27_02.png"],
    },
    10: {
        "title": "Interfaz Gráfica (GUI)",
        "short": "Pantallas Swing finales para login, menu y gestion de datos.",
        "icon": "GUI",
        "objective": "Convertir la experiencia de consola en una interfaz grafica clara y usable para el usuario final.",
        "decisions": [
            "Java Swing permite entregar una aplicacion de escritorio sin depender de navegador.",
            "JFrame estructura las ventanas principales como login, menu y modulos de gestion.",
            "JTable muestra productos, clientes, pedidos y proveedores de forma tabular.",
            "JButton activa operaciones CRUD y funciones de IA integradas en productos.",
            "JDialog queda reservado para formularios o confirmaciones cuando se necesita interaccion modal.",
        ],
        "results": [
            "Ventana de login con usuario y contrasena.",
            "Menu principal con acceso a Productos, Clientes, Pedidos y Proveedores.",
            "Pantallas de gestion con tablas, campos y botones de accion.",
            "La ventana de productos integra acciones de IA para descripcion y categoria.",
            "La GUI mejora la navegacion frente al menu de consola mostrado en fases anteriores.",
        ],
        "images": ["p29_01.png", "p29_02.png", "p30_01.png", "p30_02.png", "p30_03.png", "p31_01.png"],
    },
}


CAPTIONS = {
    "p05_01.png": "Diagrama de casos de uso con administrador y empleado.",
    "p07_01.png": "Backlog de Jira con historias iniciales del sistema.",
    "p07_02.png": "Backlog de Jira con tareas de pruebas, seguridad y despliegue.",
    "p08_01.jpg": "Modelo conceptual entidad-relacion de LevelUp Arcade.",
    "p08_02.png": "Modelo logico con tablas y relaciones principales.",
    "p09_01.png": "Script SQL del modelo fisico, primera parte.",
    "p09_02.png": "Script SQL del modelo fisico, segunda parte.",
    "p09_03.png": "Script SQL con claves y relaciones finales.",
    "p10_01.png": "Diagrama UML de clases del sistema.",
    "p11_01.png": "Diagrama de flujo para alta de producto.",
    "p11_02.png": "Diagrama de flujo para eliminar producto.",
    "p11_03.png": "Diagrama de flujo para modificar producto.",
    "p12_01.jpg": "Exploracion visual inicial para la interfaz.",
    "p12_02.jpg": "Mockup del panel de control.",
    "p13_01.jpg": "Mockup de gestion de productos.",
    "p13_02.jpg": "Mockup de gestion de clientes.",
    "p13_03.jpg": "Mockup de gestion de proveedores.",
    "p14_01.png": "Configuracion de red en la maquina virtual Ubuntu.",
    "p14_02.png": "Terminal con comprobaciones de direccionamiento IP.",
    "p15_01.png": "Conexion remota validada con MySQL Workbench.",
    "p15_02.png": "Base de datos levelup_arcade preparada en el gestor SQL.",
    "p16_01.png": "Estructura del proyecto Java por paquetes.",
    "p17_01.png": "Ejecucion por consola con menu y consultas.",
    "p17_02.png": "Clase Log.java para registrar actividad y errores.",
    "p18_01.png": "Clase de conexion JDBC con MariaDB/MySQL.",
    "p18_02.png": "Commit de Git registrando avance del proyecto.",
    "p19_01.png": "Creacion de API Key en OpenRouter.",
    "p20_01.png": "Implementacion general de LlmService.java.",
    "p20_02.png": "Construccion de la peticion HTTP al modelo.",
    "p21_01.png": "Parseo de respuesta del servicio LLM.",
    "p21_02.png": "Metodos para descripcion y categoria de producto.",
    "p22_01.png": "Casos de prueba para operaciones CRUD.",
    "p22_02.png": "Casos de prueba para errores y situaciones limite.",
    "p23_01.png": "Pruebas unitarias JUnit en Eclipse.",
    "p23_02.png": "Depuracion y correccion de errores detectados.",
    "p24_01.png": "Creacion de carpeta de backups y permisos iniciales.",
    "p24_02.png": "Script backup_levelup.sh para copia de seguridad.",
    "p24_03.png": "Permisos de ejecucion del script de backup.",
    "p24_04.png": "Programacion de backup automatico con cron.",
    "p24_05.png": "Backup generado correctamente.",
    "p25_01.png": "Codigo Java para login y control de rol.",
    "p26_01.png": "Tabla usuarios con password_hash y rol.",
    "p26_02.png": "Codigo para almacenar contrasenas con hash.",
    "p27_01.png": "Exportación del proyecto como Runnable JAR.",
    "p27_02.png": "Archivo JAR generado y probado fuera de Eclipse.",
    "p29_01.png": "Ventana de login de LevelUp Arcade.",
    "p29_02.png": "Menu principal de la aplicacion.",
    "p30_01.png": "Gestion de productos con tabla, campos y acciones de IA.",
    "p30_02.png": "Gestion de clientes.",
    "p30_03.png": "Gestion de pedidos.",
    "p31_01.png": "Gestion de proveedores.",
}


PAGES = [
    ("index.html", "Inicio"),
    *[(f"fase{i}.html", f"Fase {i}") for i in range(1, 11)],
    ("presentacion.html", "Presentación"),
]


def ensure_dirs() -> None:
    for directory in (OUT, ASSETS, CAPTURES, CSS_DIR, JS_DIR):
        directory.mkdir(parents=True, exist_ok=True)


def copy_assets() -> None:
    for image in SOURCE_IMAGES.glob("p*_*.*"):
        if image.name == "contact_sheet.png":
            continue
        shutil.copy2(image, CAPTURES / image.name)
    if PDF.exists():
        shutil.copy2(PDF, ASSETS / "levelup-arcade-memoria.pdf")


def clean_text(text: str) -> str:
    text = text.replace("\x00", " ")
    lines = []
    for raw in text.splitlines():
        line = " ".join(raw.strip().split())
        if not line:
            lines.append("")
            continue
        if line.startswith("AHMED SALEM"):
            continue
        if re.fullmatch(r"\d+", line):
            continue
        lines.append(line)
    cleaned = "\n".join(lines)
    cleaned = re.sub(r"\n{3,}", "\n\n", cleaned)
    return cleaned.strip()


def read_pdf_pages() -> dict[int, str]:
    reader = PdfReader(str(PDF))
    return {i + 1: clean_text(page.extract_text() or "") for i, page in enumerate(reader.pages)}


def phase_pdf_text(pages: dict[int, str], phase: int) -> str:
    if phase == 1:
        return "\n\n".join(pages[i] for i in range(1, 8))
    if phase == 2:
        return "\n\n".join(pages[i] for i in range(8, 14))
    if phase == 3:
        return "\n\n".join(pages[i] for i in range(14, 16))
    if phase == 4:
        return "\n\n".join(pages[i] for i in range(16, 19))
    if phase == 5:
        return "\n\n".join(pages[i] for i in range(19, 22))
    if phase == 6:
        return "\n\n".join(pages[i] for i in range(22, 25))
    if phase == 7:
        return "\n\n".join(pages[i] for i in range(25, 27))
    if phase == 8:
        page_29_intro = pages[29].split("La fase 9 la vas a hacer tu")[0].strip()
        return "\n\n".join([pages[27], pages[28], page_29_intro]).strip()
    if phase == 10:
        marker = "FASE 10) INTERFAZ GRAFICA (OPCIONAL)"
        p29 = pages[29].replace("FASE 10) INTERFAZ GRÁFICA (OPCIONAL)", marker)
        text = p29.split(marker)[-1].strip() if marker in p29 else marker
        return (marker + "\n\n" + text).strip()
    return ""


def esc(value: str) -> str:
    return html.escape(value, quote=True)


def nav(active: str) -> str:
    links = []
    for href, label in PAGES:
        cls = "active" if href == active else ""
        links.append(f'<a class="{cls}" href="{href}">{label}</a>')
    return "\n".join(links)


def layout(title: str, active: str, main: str, body_class: str = "") -> str:
    page_title = f"{title} | LevelUp Arcade"
    return f"""<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>{esc(page_title)}</title>
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700;800&family=Orbitron:wght@500;700;900&family=Rajdhani:wght@500;600;700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="css/styles.css">
  <script defer src="https://cdn.jsdelivr.net/npm/tsparticles@3/tsparticles.bundle.min.js"></script>
  <script defer src="js/main.js"></script>
</head>
<body class="{esc(body_class)}">
  <div class="reading-progress" aria-hidden="true"><span></span></div>
  <div class="cursor-dot" aria-hidden="true"></div>
  <header class="site-header" data-nav>
    <a class="brand" href="index.html" aria-label="Inicio LevelUp Arcade">
      <span class="brand-mark">LU</span>
      <span class="brand-text">LevelUp Arcade</span>
    </a>
    <button class="menu-toggle" type="button" aria-label="Abrir menu" aria-expanded="false">
      <span></span><span></span><span></span>
    </button>
    <nav class="nav-links" aria-label="Navegacion principal">
      {nav(active)}
    </nav>
  </header>
  <main>
{main}
  </main>
  <footer class="site-footer">
    <div>
      <strong>LevelUp Arcade</strong>
      <p>Memoria web del proyecto Java, MySQL, Linux, Git y OpenRouter.</p>
    </div>
    <div class="footer-tech">
      <span>Java</span><span>MySQL</span><span>Ubuntu</span><span>Git</span><span>OpenRouter</span>
    </div>
  </footer>
</body>
</html>
"""


def pdf_transcript(text: str) -> str:
    if not text:
        text = "Esta fase no tenia texto adicional en la documentación original; se completa con las capturas y la explicación técnica de esta página."
    return f'<div class="pdf-transcript">{esc(text).replace(chr(10), "<br>")}</div>'


def phase8_development() -> str:
    return """
        <div class="human-copy">
          <p>En esta fase se preparó LevelUp Arcade para su entrega final. La aplicación ya no se plantea solo como un proyecto abierto en Eclipse, sino como un programa que puede ejecutarse desde un archivo <strong>LevelUpArcade.jar</strong>.</p>
          <p>Para que funcione correctamente, el equipo que lo ejecute debe tener Java instalado, una base de datos MySQL o MariaDB con la base <strong>levelup_arcade</strong> importada y el archivo <strong>config.properties</strong> en la misma carpeta que el JAR. En ese archivo se guardan los datos de conexión a la base de datos y la configuración necesaria para la integración con OpenRouter.</p>
          <p>La ejecución se realiza desde consola con el comando <strong>java -jar LevelUpArcade.jar</strong>. Después de arrancar, el usuario entra desde la pantalla de login y, según su rol, puede trabajar con las opciones disponibles. El administrador tiene acceso completo, mientras que el empleado queda limitado a funciones de consulta.</p>
          <p>También se dejó preparado el manual de usuario. En él se explica qué es el sistema, cómo se instala, cómo se ejecuta y qué operaciones principales permite realizar: gestionar productos, clientes y pedidos, además de usar la ayuda de IA para generar descripciones de productos.</p>
        </div>
        <div class="phase-note">
          <strong>Comprobación realizada:</strong>
          el proyecto se exportó como Runnable JAR y se probó fuera de Eclipse para confirmar que el despliegue no dependía del entorno de desarrollo.
        </div>
    """


def list_items(items: list[str]) -> str:
    return "\n".join(f"<li>{esc(item)}</li>" for item in items)


def gallery(images: list[str], title: str = "Galeria de capturas") -> str:
    if not images:
        return """
        <section class="content-section">
          <div class="section-kicker">Evidencias</div>
          <h2>Galeria de capturas</h2>
          <p>No hay capturas asociadas a esta fase. La documentación se completa con el texto y los resultados.</p>
        </section>
        """
    cards = []
    for image in images:
        caption = CAPTIONS.get(image, image)
        path = f"assets/capturas/{image}"
        cards.append(
            f"""
            <a class="gallery-item reveal" href="{path}" data-lightbox data-caption="{esc(caption)}">
              <img src="{path}" alt="{esc(caption)}" loading="lazy">
              <span>{esc(caption)}</span>
            </a>
            """
        )
    return f"""
        <section class="content-section">
          <div class="section-kicker">Evidencias</div>
          <h2>{esc(title)}</h2>
          <div class="gallery-grid">
            {''.join(cards)}
          </div>
        </section>
    """


def phase_hero(number: int, data: dict[str, object]) -> str:
    return f"""
      <section class="phase-hero">
        <div class="particle-layer" data-particles></div>
        <div class="hero-grid"></div>
        <div class="container hero-inner">
          <div class="phase-chip">Fase {number:02d}</div>
          <h1>Fase {number} - {esc(str(data["title"]))}</h1>
          <p>{esc(str(data["short"]))}</p>
        </div>
      </section>
    """


def bottom_nav(number: int) -> str:
    prev_html = '<span></span>'
    next_html = '<span></span>'
    if number > 1:
        prev_html = f'<a class="nav-card prev" href="fase{number - 1}.html">← Fase anterior</a>'
    else:
        prev_html = '<a class="nav-card prev" href="index.html">← Inicio</a>'
    if number < 10:
        next_html = f'<a class="nav-card next" href="fase{number + 1}.html">Fase siguiente →</a>'
    else:
        next_html = '<a class="nav-card next" href="presentacion.html">Presentación →</a>'
    return f'<section class="phase-nav container">{prev_html}{next_html}</section>'


def build_phase_page(number: int, data: dict[str, object], text: str) -> str:
    extra = ""
    development = phase8_development() if number == 8 else pdf_transcript(text)
    if number == 10:
        extra = """
        <section class="content-section">
          <div class="section-kicker">GUI Swing</div>
          <h2>Componentes utilizados</h2>
          <div class="feature-grid small">
            <article><strong>JFrame</strong><p>Ventanas principales: login, menu y modulos de gestion.</p></article>
            <article><strong>JTable</strong><p>Listados tabulares para productos, clientes, pedidos y proveedores.</p></article>
            <article><strong>JButton</strong><p>Acciones CRUD, navegacion y botones de IA en productos.</p></article>
            <article><strong>JDialog</strong><p>Formularios, avisos o confirmaciones modales cuando la accion lo requiere.</p></article>
          </div>
        </section>
        <section class="content-section">
          <div class="section-kicker">Comparativa</div>
          <h2>Consola vs interfaz grafica</h2>
          <p>La fase de codificacion muestra una ejecucion por consola con menus numericos. La GUI final reemplaza ese flujo por ventanas, tablas y botones, haciendo mas rapida la consulta de informacion y mas clara la gestion para usuarios no tecnicos.</p>
        </section>
        """
    body = f"""
    {phase_hero(number, data)}
    <div class="container phase-layout">
      <section class="content-section reveal">
        <div class="section-kicker">Objetivo</div>
        <h2>Objetivo de la fase</h2>
        <p>{esc(str(data["objective"]))}</p>
      </section>
      <section class="content-section reveal">
        <div class="section-kicker">Desarrollo</div>
        <h2>Desarrollo del trabajo realizado</h2>
        {development}
      </section>
      <section class="content-section reveal">
        <div class="section-kicker">Decisiones</div>
        <h2>Decisiones técnicas tomadas</h2>
        <ul class="neon-list">{list_items(data["decisions"])}
        </ul>
      </section>
      <section class="content-section reveal">
        <div class="section-kicker">Resultados</div>
        <h2>Resultados obtenidos</h2>
        <ul class="neon-list">{list_items(data["results"])}
        </ul>
      </section>
      {extra}
      {gallery(data["images"])}
    </div>
    {bottom_nav(number)}
    """
    return layout(f"Fase {number} - {data['title']}", f"fase{number}.html", body, "phase-page")


def build_phase9() -> str:
    screenshot_files = sorted(p.name for p in ASSETS.glob("web-*.png"))
    cards = []
    for shot in screenshot_files:
        caption = {
            "web-index.png": "Portada de la memoria web con hero animado y tarjetas de fases.",
            "web-fase1.png": "Página de fase con texto, apartados y galería.",
            "web-fase10.png": "Página de interfaz gráfica con capturas Swing.",
            "web-mobile-check.png": "Adaptación móvil de la portada con menú responsive.",
        }.get(shot, "Captura del resultado final de la web.")
        cards.append(
            f"""
            <a class="gallery-item reveal" href="assets/{shot}" data-lightbox data-caption="{esc(caption)}">
              <img src="assets/{shot}" alt="{esc(caption)}" loading="lazy">
              <span>{esc(caption)}</span>
            </a>
            """
        )
    gallery_html = (
        f"""
        <section class="content-section">
          <div class="section-kicker">Resultado</div>
          <h2>Capturas del resultado final</h2>
          <div class="gallery-grid">{''.join(cards)}</div>
        </section>
        """
        if cards
        else """
        <section class="content-section">
          <div class="section-kicker">Resultado</div>
          <h2>Capturas del resultado final</h2>
          <p>Las capturas se generan despues de la primera construccion de la web para documentar el resultado visual final.</p>
        </section>
        """
    )
    body = f"""
    <section class="phase-hero">
      <div class="particle-layer" data-particles></div>
      <div class="hero-grid"></div>
      <div class="container hero-inner">
        <div class="phase-chip">Fase 09</div>
        <h1>Fase 9 - Memoria Web del Proyecto</h1>
        <p>Esta fase explica cómo se preparó la web que presenta el proyecto LevelUp Arcade.</p>
      </div>
    </section>
    <div class="container phase-layout">
      <section class="content-section reveal">
        <div class="section-kicker">Objetivo</div>
        <h2>Objetivo de la fase</h2>
        <p>Crear una memoria web estática, moderna y fácil de navegar que reúna las fases 1 a 10 del proyecto LevelUp Arcade, integrando el contenido trabajado y sus capturas.</p>
      </section>
      <section class="content-section reveal">
        <div class="section-kicker">Tecnologias</div>
        <h2>Tecnologías usadas para crear esta web</h2>
        <div class="feature-grid small">
          <article><strong>HTML5</strong><p>Estructura semántica para portada, fases, galerías y presentación.</p></article>
          <article><strong>CSS3</strong><p>Diseño dark gaming, glassmorphism, glow neon, responsive y cursor personalizado.</p></article>
          <article><strong>JavaScript</strong><p>Menú móvil, progreso de lectura, lightbox, scroll reveal y partículas animadas.</p></article>
          <article><strong>Python</strong><p>Preparación del contenido y organización de las capturas de la memoria.</p></article>
        </div>
      </section>
      <section class="content-section reveal">
        <div class="section-kicker">Estructura</div>
        <h2>Estructura de archivos</h2>
        <pre class="code-block">memoria-proyecto/
├── index.html
├── fase1.html ... fase10.html
├── presentacion.html
├── css/styles.css
├── js/main.js
└── assets/
    └── capturas/</pre>
      </section>
      <section class="content-section reveal">
        <div class="section-kicker">Diseno</div>
        <h2>Decisiones de diseno</h2>
        <ul class="neon-list">
          <li>Se eligio una estetica dark gaming con fondo negro, cian electrico y morado neon.</li>
          <li>Orbitron se usa en titulares para reforzar el caracter arcade y tecnologico.</li>
          <li>Las tarjetas tienen efecto glassmorphism, borde luminoso y elevacion al pasar el cursor.</li>
          <li>Las galerias abren capturas en lightbox para revisar diagramas y pantallas sin salir de la pagina.</li>
          <li>La web no necesita servidor: funciona abriendo directamente los HTML con rutas relativas.</li>
        </ul>
      </section>
      {gallery_html}
    </div>
    {bottom_nav(9)}
    """
    return layout("Fase 9 - Memoria Web", "fase9.html", body, "phase-page")


def build_index() -> str:
    cards = []
    for number in range(1, 11):
        data = PHASES.get(number)
        if number == 9:
            data = {
                "title": "Memoria Web",
                "short": "La web que organiza y presenta todo el proyecto.",
                "icon": "WEB",
            }
        cards.append(
            f"""
            <a class="phase-card reveal" href="fase{number}.html">
              <span class="phase-number">{number:02d}</span>
              <span class="phase-icon">{esc(str(data["icon"]))}</span>
              <h3>{esc(str(data["title"]))}</h3>
              <p>{esc(str(data["short"]))}</p>
            </a>
            """
        )
    body = f"""
      <section class="home-hero">
        <div class="particle-layer" data-particles></div>
        <div class="hero-grid"></div>
        <div class="hero-scan"></div>
        <div class="container home-hero-inner">
          <div class="eyebrow">Proyecto Java + MySQL + IA</div>
          <h1>LevelUp Arcade</h1>
          <p>Memoria del proyecto de una aplicación de gestión para tienda gaming, con base de datos, seguridad, despliegue, GUI e integración LLM.</p>
          <div class="hero-actions">
            <a class="btn primary" href="fase1.html">Explorar fases</a>
            <a class="btn ghost" href="presentacion.html">Ver resumen ejecutivo</a>
          </div>
          <div class="hero-preview-strip" aria-label="Capturas destacadas">
            <img src="assets/capturas/p29_02.png" alt="Menu principal de LevelUp Arcade">
            <img src="assets/capturas/p30_01.png" alt="Gestion de productos">
            <img src="assets/capturas/p08_02.png" alt="Modelo logico de base de datos">
          </div>
        </div>
      </section>
      <section class="container intro-grid">
        <article class="content-section reveal">
          <div class="section-kicker">Descripcion general</div>
          <h2>Resumen general del proyecto</h2>
          <p>LevelUp Arcade centraliza la gestion de productos, clientes, proveedores y pedidos para una tienda de videojuegos y merchandising. La solucion sustituye hojas de calculo por una aplicacion Java conectada a MySQL/MariaDB, con roles de usuario y funciones de IA para acelerar la gestion de productos.</p>
        </article>
        <article class="content-section reveal">
          <div class="section-kicker">Objetivos</div>
          <h2>Objetivos principales</h2>
          <ul class="neon-list">
            <li>Digitalizar la gestion de inventario, clientes y proveedores.</li>
            <li>Reducir errores humanos y duplicidad de datos.</li>
            <li>Centralizar la informacion en una base de datos relacional.</li>
            <li>Automatizar tareas con IA mediante OpenRouter.</li>
          </ul>
        </article>
      </section>
      <section class="container">
        <div class="section-heading reveal">
          <div class="section-kicker">Roadmap</div>
          <h2>Fases del proyecto</h2>
        </div>
        <div class="phase-card-grid">
          {''.join(cards)}
          <a class="phase-card reveal presentation-card" href="presentacion.html">
            <span class="phase-number">P</span>
            <span class="phase-icon">SUM</span>
            <h3>Presentación</h3>
            <p>Resumen ejecutivo, logros, conclusiones y equipo del proyecto.</p>
          </a>
        </div>
      </section>
      <section class="container tech-section">
        <div class="section-heading reveal">
          <div class="section-kicker">Stack</div>
          <h2>Tecnologias utilizadas</h2>
        </div>
        <div class="tech-grid">
          <article class="reveal"><span>JAVA</span><strong>Java</strong><p>Aplicacion de escritorio y logica MVC.</p></article>
          <article class="reveal"><span>SQL</span><strong>MySQL/MariaDB</strong><p>Persistencia relacional y normalizada.</p></article>
          <article class="reveal"><span>LIN</span><strong>Ubuntu Server</strong><p>Servidor de base de datos y backups.</p></article>
          <article class="reveal"><span>GIT</span><strong>Git</strong><p>Control de versiones con commits.</p></article>
          <article class="reveal"><span>LLM</span><strong>OpenRouter</strong><p>IA para descripciones y categorias.</p></article>
        </div>
      </section>
      <section class="container timeline-section">
        <div class="section-heading reveal">
          <div class="section-kicker">Progreso</div>
          <h2>Timeline horizontal</h2>
        </div>
        <div class="timeline">
          {"".join(f'<a href="fase{i}.html"><span>{i:02d}</span><strong>{esc(PHASES[i]["title"] if i in PHASES else "Memoria Web")}</strong></a>' for i in range(1, 11))}
        </div>
      </section>
    """
    return layout("Inicio", "index.html", body, "home-page")


def build_presentation() -> str:
    body = """
      <section class="phase-hero">
        <div class="particle-layer" data-particles></div>
        <div class="hero-grid"></div>
        <div class="container hero-inner">
          <div class="phase-chip">Presentación</div>
          <h1>Resumen Ejecutivo</h1>
          <p>Visión completa del proyecto LevelUp Arcade, sus logros y conclusiones.</p>
        </div>
      </section>
      <div class="container phase-layout">
        <section class="content-section reveal">
          <div class="section-kicker">Proyecto</div>
          <h2>Resumen ejecutivo</h2>
          <p>LevelUp Arcade es una aplicación de escritorio desarrollada en Java para gestionar una tienda de videojuegos y merchandising. El proyecto recoge todo el proceso: análisis, diseño, infraestructura, codificación, inteligencia artificial, pruebas, seguridad, despliegue e interfaz gráfica.</p>
        </section>
        <section class="content-section reveal">
          <div class="section-kicker">Logros</div>
          <h2>Logros conseguidos</h2>
          <ul class="neon-list">
            <li>Modelo de datos normalizado y conectado a MySQL/MariaDB.</li>
            <li>Aplicación Java organizada con arquitectura MVC.</li>
            <li>Roles de usuario, login y contraseñas protegidas con hash.</li>
            <li>Integración con OpenRouter para funciones de IA.</li>
            <li>Backups automáticos, pruebas JUnit y despliegue como archivo JAR.</li>
            <li>Interfaz gráfica Swing para una experiencia más clara y profesional.</li>
          </ul>
        </section>
        <section class="content-section reveal">
          <div class="section-kicker">Equipo</div>
          <h2>Equipo del proyecto</h2>
          <div class="team-grid">
            <article><strong>Ahmed Salem</strong><span>Análisis, desarrollo, documentación, despliegue y presentación web del proyecto</span></article>
          </div>
        </section>
        <section class="content-section reveal">
          <div class="section-kicker">Conclusión</div>
          <h2>Conclusiones</h2>
          <p>El resultado final es una aplicación completa para gestionar la tienda de forma más ordenada, con datos centralizados, control de usuarios, copias de seguridad y una interfaz gráfica que facilita el trabajo diario.</p>
        </section>
      </div>
      <section class="phase-nav container">
        <a class="nav-card prev" href="fase10.html">← Fase 10</a>
        <a class="nav-card next" href="index.html">Volver al inicio →</a>
      </section>
    """
    return layout("Presentación", "presentacion.html", body, "phase-page")


CSS = r"""
:root {
  --bg: #08090d;
  --bg-2: #0e1017;
  --panel: rgba(16, 19, 31, 0.72);
  --panel-strong: rgba(18, 22, 36, 0.92);
  --text: #f6f7fb;
  --muted: #a8b0c3;
  --cyan: #00f5ff;
  --purple: #a855f7;
  --green: #7cff6b;
  --gold: #ffd166;
  --line: rgba(255, 255, 255, 0.13);
  --shadow: 0 0 28px rgba(0, 245, 255, 0.14), 0 0 42px rgba(168, 85, 247, 0.12);
  --radius: 8px;
}

* {
  box-sizing: border-box;
}

html {
  scroll-behavior: smooth;
}

body {
  margin: 0;
  min-height: 100vh;
  color: var(--text);
  background:
    linear-gradient(90deg, rgba(255,255,255,0.025) 1px, transparent 1px),
    linear-gradient(rgba(255,255,255,0.018) 1px, transparent 1px),
    radial-gradient(circle at top left, rgba(0, 245, 255, 0.10), transparent 32rem),
    linear-gradient(135deg, #050507 0%, #0c1017 46%, #09080f 100%);
  background-size: 42px 42px, 42px 42px, auto, auto;
  font-family: "Inter", system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
  overflow-x: hidden;
  cursor: none;
}

a {
  color: inherit;
  text-decoration: none;
}

img {
  display: block;
  max-width: 100%;
}

.container {
  width: min(1180px, calc(100% - 36px));
  margin: 0 auto;
}

.reading-progress {
  position: fixed;
  inset: 0 0 auto 0;
  height: 3px;
  z-index: 2000;
  background: rgba(255, 255, 255, 0.06);
}

.reading-progress span {
  display: block;
  height: 100%;
  width: 0;
  background: linear-gradient(90deg, var(--cyan), var(--purple), var(--green));
  box-shadow: 0 0 18px rgba(0, 245, 255, 0.7);
}

.site-header {
  position: fixed;
  top: 14px;
  left: 50%;
  transform: translateX(-50%);
  width: min(1220px, calc(100% - 24px));
  z-index: 1000;
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 18px;
  padding: 10px 12px;
  border: 1px solid rgba(255, 255, 255, 0.12);
  border-radius: var(--radius);
  background: rgba(5, 7, 12, 0.62);
  backdrop-filter: blur(18px);
  box-shadow: var(--shadow);
  transition: background 180ms ease, border-color 180ms ease, transform 180ms ease;
}

.site-header.is-scrolled {
  background: rgba(5, 7, 12, 0.92);
  border-color: rgba(0, 245, 255, 0.32);
}

.brand {
  display: inline-flex;
  align-items: center;
  gap: 10px;
  min-width: max-content;
  font-family: "Orbitron", sans-serif;
  font-weight: 800;
  letter-spacing: 0;
}

.brand-mark {
  display: grid;
  place-items: center;
  width: 38px;
  height: 38px;
  border: 1px solid rgba(0, 245, 255, 0.65);
  border-radius: 6px;
  color: var(--cyan);
  text-shadow: 0 0 10px rgba(0, 245, 255, 0.85);
  background: rgba(0, 245, 255, 0.08);
}

.brand-text {
  white-space: nowrap;
}

.nav-links {
  display: flex;
  align-items: center;
  justify-content: flex-end;
  gap: 4px;
  flex-wrap: wrap;
}

.nav-links a {
  padding: 8px 9px;
  border-radius: 6px;
  color: var(--muted);
  font-size: 0.82rem;
  font-weight: 700;
  transition: color 160ms ease, background 160ms ease, box-shadow 160ms ease;
}

.nav-links a:hover,
.nav-links a.active {
  color: var(--text);
  background: rgba(0, 245, 255, 0.09);
  box-shadow: inset 0 0 0 1px rgba(0, 245, 255, 0.28), 0 0 18px rgba(0, 245, 255, 0.16);
}

.menu-toggle {
  display: none;
  width: 42px;
  height: 42px;
  border: 1px solid rgba(255, 255, 255, 0.16);
  border-radius: 6px;
  background: rgba(255, 255, 255, 0.05);
  color: var(--text);
  padding: 10px;
}

.menu-toggle span {
  display: block;
  height: 2px;
  margin: 5px 0;
  background: var(--text);
  transition: transform 180ms ease, opacity 180ms ease;
}

.menu-toggle.is-open span:nth-child(1) {
  transform: translateY(7px) rotate(45deg);
}

.menu-toggle.is-open span:nth-child(2) {
  opacity: 0;
}

.menu-toggle.is-open span:nth-child(3) {
  transform: translateY(-7px) rotate(-45deg);
}

.home-hero,
.phase-hero {
  position: relative;
  isolation: isolate;
  overflow: hidden;
  min-height: 100vh;
  display: grid;
  align-items: center;
  padding: 110px 0 70px;
}

.phase-hero {
  min-height: 58vh;
  padding-bottom: 56px;
}

.particle-layer,
.hero-grid,
.hero-scan {
  position: absolute;
  inset: 0;
  z-index: -2;
}

.particle-layer canvas {
  width: 100%;
  height: 100%;
}

.hero-grid {
  z-index: -3;
  background:
    linear-gradient(90deg, rgba(0, 245, 255, 0.06) 1px, transparent 1px),
    linear-gradient(rgba(168, 85, 247, 0.05) 1px, transparent 1px);
  background-size: 64px 64px;
  mask-image: linear-gradient(to bottom, rgba(0,0,0,0.9), transparent 86%);
}

.hero-scan {
  z-index: -1;
  background: repeating-linear-gradient(180deg, rgba(255,255,255,0.03), rgba(255,255,255,0.03) 1px, transparent 1px, transparent 8px);
  opacity: 0.26;
  mix-blend-mode: screen;
}

.home-hero::after,
.phase-hero::after {
  content: "";
  position: absolute;
  inset: auto 0 0;
  height: 34%;
  background: linear-gradient(180deg, transparent, var(--bg));
  z-index: -1;
}

.home-hero-inner,
.hero-inner {
  position: relative;
  text-align: center;
}

.eyebrow,
.section-kicker,
.phase-chip {
  display: inline-flex;
  align-items: center;
  gap: 8px;
  color: var(--cyan);
  font-family: "Rajdhani", sans-serif;
  font-weight: 700;
  text-transform: uppercase;
  letter-spacing: 0;
}

.eyebrow::before,
.section-kicker::before,
.phase-chip::before {
  content: "";
  width: 28px;
  height: 2px;
  background: linear-gradient(90deg, var(--cyan), var(--purple));
  box-shadow: 0 0 12px rgba(0, 245, 255, 0.8);
}

h1,
h2,
h3 {
  font-family: "Orbitron", sans-serif;
  letter-spacing: 0;
  margin: 0;
}

.home-hero h1 {
  margin: 14px auto 16px;
  font-size: clamp(3.4rem, 10vw, 8.6rem);
  line-height: 0.94;
  text-transform: uppercase;
  text-shadow: 0 0 26px rgba(0, 245, 255, 0.52), 0 0 40px rgba(168, 85, 247, 0.36);
}

.home-hero p,
.phase-hero p {
  max-width: 820px;
  margin: 0 auto;
  color: var(--muted);
  font-size: clamp(1.08rem, 2vw, 1.35rem);
  line-height: 1.65;
}

.phase-hero h1 {
  max-width: 930px;
  margin: 16px auto;
  font-size: clamp(2.4rem, 6vw, 5.3rem);
  line-height: 1.04;
  text-shadow: 0 0 24px rgba(0, 245, 255, 0.38);
}

.hero-actions {
  display: flex;
  justify-content: center;
  gap: 14px;
  flex-wrap: wrap;
  margin-top: 30px;
}

.btn {
  display: inline-flex;
  min-height: 44px;
  align-items: center;
  justify-content: center;
  padding: 12px 18px;
  border-radius: 6px;
  font-weight: 800;
  border: 1px solid rgba(255,255,255,0.18);
  transition: transform 160ms ease, box-shadow 160ms ease, border-color 160ms ease;
}

.btn:hover {
  transform: translateY(-2px);
}

.btn.primary {
  color: #041015;
  background: linear-gradient(90deg, var(--cyan), var(--green));
  box-shadow: 0 0 24px rgba(0, 245, 255, 0.28);
}

.btn.ghost {
  background: rgba(255,255,255,0.06);
  color: var(--text);
  box-shadow: inset 0 0 0 1px rgba(168,85,247,0.28);
}

.hero-preview-strip {
  width: min(980px, 100%);
  margin: 46px auto 0;
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 14px;
  perspective: 900px;
}

.hero-preview-strip img {
  width: 100%;
  aspect-ratio: 16 / 9;
  object-fit: cover;
  border-radius: var(--radius);
  border: 1px solid rgba(0, 245, 255, 0.24);
  box-shadow: var(--shadow);
  opacity: 0.88;
}

.intro-grid {
  display: grid;
  grid-template-columns: minmax(0, 1.1fr) minmax(280px, 0.9fr);
  gap: 18px;
  padding: 70px 0 30px;
}

.content-section {
  border: 1px solid var(--line);
  border-radius: var(--radius);
  background: var(--panel);
  backdrop-filter: blur(16px);
  box-shadow: 0 16px 48px rgba(0, 0, 0, 0.26);
  padding: clamp(22px, 4vw, 38px);
  position: relative;
  overflow: hidden;
}

.content-section::before {
  content: "";
  position: absolute;
  inset: 0 0 auto;
  height: 1px;
  background: linear-gradient(90deg, transparent, rgba(0, 245, 255, 0.75), rgba(168, 85, 247, 0.6), transparent);
}

.content-section h2,
.section-heading h2 {
  margin-top: 10px;
  font-size: clamp(1.6rem, 3vw, 2.6rem);
}

.content-section p,
.content-section li,
.pdf-transcript {
  color: #d7ddec;
  font-size: 1rem;
  line-height: 1.75;
}

.section-heading {
  margin: 76px 0 22px;
}

.phase-card-grid {
  display: grid;
  grid-template-columns: repeat(4, minmax(0, 1fr));
  gap: 16px;
}

.phase-card,
.tech-grid article,
.feature-grid article,
.team-grid article,
.nav-card,
.gallery-item {
  position: relative;
  border: 1px solid rgba(255, 255, 255, 0.12);
  border-radius: var(--radius);
  background: linear-gradient(145deg, rgba(18, 23, 35, 0.78), rgba(9, 12, 19, 0.82));
  backdrop-filter: blur(16px);
  transition: transform 180ms ease, border-color 180ms ease, box-shadow 180ms ease;
}

.phase-card {
  min-height: 250px;
  padding: 20px;
  overflow: hidden;
}

.phase-card:hover,
.tech-grid article:hover,
.feature-grid article:hover,
.team-grid article:hover,
.nav-card:hover,
.gallery-item:hover {
  transform: translateY(-6px);
  border-color: rgba(0, 245, 255, 0.62);
  box-shadow: 0 0 30px rgba(0, 245, 255, 0.18), 0 0 42px rgba(168, 85, 247, 0.16);
}

.phase-number {
  color: rgba(255,255,255,0.30);
  font-family: "Orbitron", sans-serif;
  font-size: 0.95rem;
}

.phase-icon {
  position: absolute;
  right: 18px;
  top: 18px;
  display: grid;
  place-items: center;
  width: 48px;
  height: 48px;
  border-radius: 6px;
  border: 1px solid rgba(0, 245, 255, 0.32);
  color: var(--cyan);
  font-family: "Rajdhani", sans-serif;
  font-weight: 800;
  background: rgba(0, 245, 255, 0.06);
}

.phase-card h3 {
  margin: 54px 0 12px;
  font-size: 1.2rem;
}

.phase-card p {
  color: var(--muted);
  line-height: 1.55;
}

.presentation-card {
  grid-column: span 2;
}

.tech-section,
.timeline-section {
  padding-bottom: 26px;
}

.tech-grid,
.feature-grid,
.team-grid {
  display: grid;
  grid-template-columns: repeat(5, minmax(0, 1fr));
  gap: 14px;
}

.feature-grid.small {
  grid-template-columns: repeat(4, minmax(0, 1fr));
}

.tech-grid article,
.feature-grid article,
.team-grid article {
  padding: 20px;
}

.tech-grid span {
  display: grid;
  place-items: center;
  width: 54px;
  height: 54px;
  margin-bottom: 16px;
  color: var(--cyan);
  border: 1px solid rgba(0,245,255,0.35);
  border-radius: 6px;
  font-family: "Orbitron", sans-serif;
  font-size: 0.85rem;
}

.tech-grid strong,
.feature-grid strong,
.team-grid strong {
  display: block;
  margin-bottom: 8px;
  font-family: "Orbitron", sans-serif;
}

.tech-grid p,
.feature-grid p,
.team-grid span {
  color: var(--muted);
  line-height: 1.55;
}

.timeline {
  display: grid;
  grid-template-columns: repeat(10, minmax(140px, 1fr));
  gap: 10px;
  overflow-x: auto;
  padding: 8px 2px 18px;
}

.timeline a {
  min-height: 96px;
  padding: 16px;
  border-top: 2px solid var(--cyan);
  background: rgba(255,255,255,0.045);
  border-radius: var(--radius);
}

.timeline span {
  display: block;
  margin-bottom: 8px;
  color: var(--cyan);
  font-family: "Orbitron", sans-serif;
}

.timeline strong {
  display: block;
  color: var(--text);
  font-size: 0.88rem;
}

.phase-layout {
  display: grid;
  gap: 18px;
  padding: 54px 0 24px;
}

.pdf-transcript {
  max-height: 620px;
  overflow: auto;
  padding: 18px;
  border-radius: 6px;
  border: 1px solid rgba(255,255,255,0.12);
  background: rgba(0, 0, 0, 0.28);
  font-family: "Rajdhani", "Inter", sans-serif;
  font-weight: 600;
}

.human-copy {
  display: grid;
  gap: 14px;
}

.human-copy p {
  margin: 0;
}

.human-copy strong {
  color: var(--text);
  font-weight: 800;
}

.phase-note {
  margin-top: 18px;
  padding: 16px 18px;
  border-left: 3px solid var(--cyan);
  border-radius: 6px;
  background: rgba(0, 245, 255, 0.06);
  color: #d7ddec;
  line-height: 1.7;
}

.neon-list {
  margin: 18px 0 0;
  padding: 0;
  list-style: none;
}

.neon-list li {
  position: relative;
  padding-left: 24px;
  margin: 10px 0;
}

.neon-list li::before {
  content: "";
  position: absolute;
  left: 0;
  top: 0.75em;
  width: 8px;
  height: 8px;
  border-radius: 2px;
  background: var(--cyan);
  box-shadow: 0 0 14px rgba(0,245,255,0.86);
}

.gallery-grid {
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
  gap: 16px;
}

.gallery-item {
  overflow: hidden;
  background: rgba(255,255,255,0.05);
}

.gallery-item img {
  width: 100%;
  aspect-ratio: 16 / 10;
  object-fit: cover;
  background: #fff;
}

.gallery-item span {
  display: block;
  min-height: 68px;
  padding: 12px 14px 14px;
  color: #dfe6f7;
  line-height: 1.45;
}

.phase-nav {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 16px;
  padding: 20px 0 74px;
}

.nav-card {
  padding: 18px 20px;
  font-weight: 800;
}

.nav-card.next {
  text-align: right;
}

.code-block {
  overflow: auto;
  margin: 18px 0 0;
  padding: 18px;
  border: 1px solid rgba(0,245,255,0.2);
  border-radius: 6px;
  background: rgba(0, 0, 0, 0.36);
  color: #dffcff;
  line-height: 1.55;
}

.team-grid {
  grid-template-columns: minmax(0, 1fr);
}

.team-grid article {
  min-height: 120px;
}

.cta-section {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 22px;
}

.site-footer {
  width: min(1180px, calc(100% - 36px));
  margin: 0 auto 24px;
  padding: 24px;
  border: 1px solid var(--line);
  border-radius: var(--radius);
  background: rgba(5, 7, 12, 0.78);
  display: flex;
  justify-content: space-between;
  gap: 24px;
}

.site-footer strong {
  font-family: "Orbitron", sans-serif;
}

.site-footer p {
  margin: 8px 0 0;
  color: var(--muted);
}

.footer-tech {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
  justify-content: flex-end;
}

.footer-tech span {
  display: inline-flex;
  align-items: center;
  min-height: 32px;
  padding: 6px 10px;
  border-radius: 6px;
  color: var(--cyan);
  border: 1px solid rgba(0,245,255,0.22);
  background: rgba(0,245,255,0.05);
  font-size: 0.86rem;
  font-weight: 800;
}

.lightbox {
  position: fixed;
  inset: 0;
  display: grid;
  place-items: center;
  padding: 24px;
  background: rgba(0, 0, 0, 0.86);
  backdrop-filter: blur(12px);
  z-index: 3000;
  opacity: 0;
  pointer-events: none;
  transition: opacity 160ms ease;
}

.lightbox.is-open {
  opacity: 1;
  pointer-events: auto;
}

.lightbox figure {
  width: min(1120px, 100%);
  margin: 0;
}

.lightbox img {
  max-height: 78vh;
  margin: 0 auto;
  border-radius: var(--radius);
  border: 1px solid rgba(0,245,255,0.3);
  box-shadow: var(--shadow);
  background: #fff;
}

.lightbox figcaption {
  margin-top: 12px;
  color: var(--text);
  text-align: center;
}

.lightbox button {
  position: fixed;
  top: 18px;
  right: 18px;
  width: 44px;
  height: 44px;
  border: 1px solid rgba(255,255,255,0.2);
  border-radius: 6px;
  color: var(--text);
  background: rgba(255,255,255,0.08);
  font-size: 1.5rem;
}

.cursor-dot {
  position: fixed;
  width: 18px;
  height: 18px;
  margin: -9px 0 0 -9px;
  border: 1px solid rgba(0,245,255,0.72);
  border-radius: 50%;
  pointer-events: none;
  z-index: 4000;
  transform: translate3d(-100px, -100px, 0);
  box-shadow: 0 0 18px rgba(0,245,255,0.5);
}

.reveal {
  opacity: 0;
  transform: translateY(22px);
  transition: opacity 520ms ease, transform 520ms ease;
}

.reveal.is-visible {
  opacity: 1;
  transform: translateY(0);
}

@media (max-width: 1040px) {
  .nav-links {
    position: fixed;
    top: 70px;
    left: 12px;
    right: 12px;
    display: grid;
    grid-template-columns: repeat(3, minmax(0, 1fr));
    padding: 12px;
    border: 1px solid var(--line);
    border-radius: var(--radius);
    background: rgba(5, 7, 12, 0.96);
    box-shadow: var(--shadow);
    opacity: 0;
    pointer-events: none;
    transform: translateY(-8px);
    transition: opacity 160ms ease, transform 160ms ease;
  }

  .nav-links.is-open {
    opacity: 1;
    pointer-events: auto;
    transform: translateY(0);
  }

  .menu-toggle {
    display: block;
  }

  .phase-card-grid {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }

  .tech-grid,
  .feature-grid.small,
  .gallery-grid {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }

  .intro-grid {
    grid-template-columns: 1fr;
  }
}

@media (max-width: 720px) {
  body {
    cursor: auto;
  }

  .cursor-dot {
    display: none;
  }

  .brand-text {
    display: none;
  }

  .nav-links {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }

  .home-hero,
  .phase-hero {
    padding-top: 96px;
  }

  .home-hero h1 {
    font-size: clamp(2.1rem, 10vw, 4.2rem);
    line-height: 1;
    overflow-wrap: anywhere;
  }

  .phase-hero h1 {
    font-size: clamp(2.05rem, 10vw, 3.4rem);
    overflow-wrap: anywhere;
  }

  .home-hero p,
  .phase-hero p {
    max-width: min(100%, 320px);
    font-size: 1rem;
    overflow-wrap: break-word;
  }

  .hero-actions {
    flex-direction: column;
    align-items: center;
  }

  .hero-actions .btn {
    width: min(100%, 280px);
  }

  .hero-preview-strip,
  .phase-card-grid,
  .tech-grid,
  .feature-grid.small,
  .gallery-grid,
  .team-grid,
  .phase-nav {
    grid-template-columns: 1fr;
  }

  .presentation-card {
    grid-column: auto;
  }

  .site-footer,
  .cta-section {
    flex-direction: column;
    align-items: flex-start;
  }

  .footer-tech {
    justify-content: flex-start;
  }

  .nav-card.next {
    text-align: left;
  }
}

@media (prefers-reduced-motion: reduce) {
  *,
  *::before,
  *::after {
    animation-duration: 0.01ms !important;
    animation-iteration-count: 1 !important;
    scroll-behavior: auto !important;
    transition-duration: 0.01ms !important;
  }
}
"""


JS = r"""
(() => {
  const nav = document.querySelector("[data-nav]");
  const menuToggle = document.querySelector(".menu-toggle");
  const navLinks = document.querySelector(".nav-links");
  const progress = document.querySelector(".reading-progress span");
  const cursor = document.querySelector(".cursor-dot");

  const setNavState = () => {
    nav?.classList.toggle("is-scrolled", window.scrollY > 20);
    if (progress) {
      const doc = document.documentElement;
      const max = doc.scrollHeight - window.innerHeight;
      const value = max > 0 ? (window.scrollY / max) * 100 : 0;
      progress.style.width = `${value}%`;
    }
  };

  window.addEventListener("scroll", setNavState, { passive: true });
  setNavState();

  menuToggle?.addEventListener("click", () => {
    const open = !navLinks.classList.contains("is-open");
    navLinks.classList.toggle("is-open", open);
    menuToggle.classList.toggle("is-open", open);
    menuToggle.setAttribute("aria-expanded", String(open));
  });

  document.querySelectorAll(".nav-links a").forEach((link) => {
    link.addEventListener("click", () => {
      navLinks?.classList.remove("is-open");
      menuToggle?.classList.remove("is-open");
      menuToggle?.setAttribute("aria-expanded", "false");
    });
  });

  if (cursor && matchMedia("(pointer: fine)").matches) {
    window.addEventListener("pointermove", (event) => {
      cursor.style.transform = `translate3d(${event.clientX}px, ${event.clientY}px, 0)`;
    }, { passive: true });
  }

  const observer = new IntersectionObserver((entries) => {
    entries.forEach((entry) => {
      if (entry.isIntersecting) {
        entry.target.classList.add("is-visible");
        observer.unobserve(entry.target);
      }
    });
  }, { threshold: 0.16 });
  document.querySelectorAll(".reveal").forEach((el) => observer.observe(el));

  const buildLightbox = () => {
    const lightbox = document.createElement("div");
    lightbox.className = "lightbox";
    lightbox.innerHTML = `
      <button type="button" aria-label="Cerrar imagen">&times;</button>
      <figure>
        <img alt="">
        <figcaption></figcaption>
      </figure>`;
    document.body.appendChild(lightbox);
    const image = lightbox.querySelector("img");
    const caption = lightbox.querySelector("figcaption");
    const close = () => lightbox.classList.remove("is-open");

    lightbox.querySelector("button").addEventListener("click", close);
    lightbox.addEventListener("click", (event) => {
      if (event.target === lightbox) close();
    });
    window.addEventListener("keydown", (event) => {
      if (event.key === "Escape") close();
    });

    document.querySelectorAll("[data-lightbox]").forEach((trigger) => {
      trigger.addEventListener("click", (event) => {
        event.preventDefault();
        image.src = trigger.getAttribute("href");
        image.alt = trigger.dataset.caption || "";
        caption.textContent = trigger.dataset.caption || "";
        lightbox.classList.add("is-open");
      });
    });
  };
  buildLightbox();

  const customParticles = (host) => {
    const canvas = document.createElement("canvas");
    host.appendChild(canvas);
    const ctx = canvas.getContext("2d");
    const particles = [];
    let width = 0;
    let height = 0;
    let raf = 0;

    const resize = () => {
      width = canvas.width = host.offsetWidth * devicePixelRatio;
      height = canvas.height = host.offsetHeight * devicePixelRatio;
      canvas.style.width = `${host.offsetWidth}px`;
      canvas.style.height = `${host.offsetHeight}px`;
      const count = Math.max(38, Math.floor(host.offsetWidth / 20));
      particles.length = 0;
      for (let i = 0; i < count; i += 1) {
        particles.push({
          x: Math.random() * width,
          y: Math.random() * height,
          vx: (Math.random() - 0.5) * 0.55 * devicePixelRatio,
          vy: (Math.random() - 0.5) * 0.55 * devicePixelRatio,
          r: (Math.random() * 1.8 + 0.8) * devicePixelRatio,
        });
      }
    };

    const draw = () => {
      ctx.clearRect(0, 0, width, height);
      ctx.fillStyle = "rgba(0, 245, 255, 0.78)";
      ctx.strokeStyle = "rgba(168, 85, 247, 0.18)";
      particles.forEach((p, index) => {
        p.x += p.vx;
        p.y += p.vy;
        if (p.x < 0 || p.x > width) p.vx *= -1;
        if (p.y < 0 || p.y > height) p.vy *= -1;
        ctx.beginPath();
        ctx.arc(p.x, p.y, p.r, 0, Math.PI * 2);
        ctx.fill();
        for (let j = index + 1; j < particles.length; j += 1) {
          const q = particles[j];
          const dist = Math.hypot(p.x - q.x, p.y - q.y);
          if (dist < 145 * devicePixelRatio) {
            ctx.globalAlpha = 1 - dist / (145 * devicePixelRatio);
            ctx.beginPath();
            ctx.moveTo(p.x, p.y);
            ctx.lineTo(q.x, q.y);
            ctx.stroke();
            ctx.globalAlpha = 1;
          }
        }
      });
      raf = requestAnimationFrame(draw);
    };

    resize();
    draw();
    window.addEventListener("resize", () => {
      cancelAnimationFrame(raf);
      resize();
      draw();
    }, { passive: true });
  };

  const initParticles = async () => {
    const hosts = document.querySelectorAll("[data-particles]");
    for (const host of hosts) {
      if (window.tsParticles) {
        const id = `particles-${Math.random().toString(36).slice(2)}`;
        host.id = id;
        try {
          await window.tsParticles.load({
            id,
            options: {
              fullScreen: false,
              background: { color: "transparent" },
              fpsLimit: 60,
              particles: {
                number: { value: 70, density: { enable: true, area: 900 } },
                color: { value: ["#00f5ff", "#a855f7", "#7cff6b"] },
                links: { enable: true, color: "#00f5ff", opacity: 0.18, distance: 135 },
                move: { enable: true, speed: 0.65 },
                opacity: { value: 0.62 },
                size: { value: { min: 1, max: 3 } },
              },
              interactivity: {
                events: { onHover: { enable: true, mode: "repulse" } },
                modes: { repulse: { distance: 120, duration: 0.4 } },
              },
              detectRetina: true,
            },
          });
        } catch {
          customParticles(host);
        }
      } else {
        customParticles(host);
      }
    }
  };

  initParticles();
})();
"""


def write_file(path: Path, content: str) -> None:
    path.write_text(content, encoding="utf-8", newline="\n")


def main() -> None:
    ensure_dirs()
    copy_assets()
    pages = read_pdf_pages()
    write_file(CSS_DIR / "styles.css", CSS)
    write_file(JS_DIR / "main.js", JS)
    write_file(OUT / "index.html", build_index())
    for number, data in PHASES.items():
        if number == 9:
            continue
        write_file(OUT / f"fase{number}.html", build_phase_page(number, data, phase_pdf_text(pages, number)))
    write_file(OUT / "fase9.html", build_phase9())
    write_file(OUT / "presentacion.html", build_presentation())
    print(f"Memoria generada en: {OUT}")


if __name__ == "__main__":
    main()
