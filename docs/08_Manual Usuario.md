# Manual de Usuario — PokeStorage

> Versión: 1.0
>
> Fecha: 2025-11-04
>
## Índice

1. [Introducción](#introducción)
2. [Convenciones y símbolos](#convenciones-y-símbolos)
3. [Requisitos previos](#requisitos-previos)
4. [Instalación y ejecución rápida](#instalación-y-ejecución-rápida)
5. [Primer inicio — Configuración inicial](#primer-inicio-configuracion-inicial)
6. [Pantallas y flujo de la aplicación (UI)](#pantallas-y-flujo-de-la-aplicación-ui)
   - [Iniciar sesión / Registro](#iniciar-sesión--registro)
   - [MainForm — Panel principal](#mainform--panel-principal)
   - [ListaPokemon](#listapokemon)
   - [MochilaPokemon](#mochilapokemon)
   - [PokeStoragePanel y otras vistas](#pokestoragepanel-y-otras-vistas)
7. [Operaciones comunes (guías paso a paso)](#operaciones-comunes-guías-paso-a-paso)
   - [Registro de usuario](#registro-de-usuario)
   - [Inicio de sesión](#inicio-de-sesión)
   - [Añadir/Eliminar Pokémon de la mochila](#añadireliminar-pokémon-de-la-mochila)
   - [Buscar y filtrar Pokémon](#buscar-y-filtrar-pokémon)
   - [Exportar/Importar datos (backup)](#exportarimportar-datos-backup)

---

## Introducción

Este Manual de Usuario está dirigido a usuarios finales de la aplicación PokeStorage. Su objetivo es explicar, con un enfoque práctico y paso a paso, cómo instalar, configurar y utilizar las funciones principales del sistema: gestión de usuarios, manejo de la colección (Lista/Mochila), búsqueda, respaldos y opciones de exportación.

Nota importante sobre la entrega

La aplicación se distribuye como un único archivo ejecutable Java (`PokeStorage.jar`). A continuación se indican las formas recomendadas de ejecutar el JAR en Windows.

El manual asume conocimientos básicos de entornos Windows y uso de aplicaciones Java (ejecución de JAR). Para tareas de instalación avanzada o mantenimiento del servidor/BD consulte el Manual Técnico en `docs/05_ManualTecnico.md`.

---

## Convenciones y símbolos

- Nota: Información complementaria o recomendaciones.
- Precaución: Indicaciones que pueden afectar datos.
- Tip: Atajos o sugerencias para mejorar la experiencia.

Elementos de la UI que se mencionan: botones, campos, menús y paneles se escribirán en monoespacio como `IniciarSesionPanel`, `MainForm`, `MochilaPokemon`.

---

## Requisitos previos

Antes de usar PokeStorage asegúrate de tener:

Antes de usar PokeStorage asegúrate de tener:

- Java Runtime (JRE) 11 o superior instalado.
- Espacio suficiente en disco para tus datos (dependiendo del uso, normalmente 200 MB es suficiente para la app y datos básicos).

Recomendaciones de hardware básicas:

- CPU 2+ cores, 4 GB RAM (mínimo)
- 200 MB de espacio libre para la aplicación y datos (más para backups si los necesitas)

---

## Instalación y ejecución rápida


Sigue estos pasos para una instalación mínima en Windows usando Git Bash, PowerShell o haciendo doble clic sobre el JAR:

1. Coloca `PokeStorage.jar` en una carpeta, por ejemplo `C:\PokeStorage`.


Formas sencillas de ejecutar la aplicación:

- Ejecutar por doble clic (Windows): si los JAR están correctamente asociados a Java, basta con hacer doble clic en `PokeStorage.jar`.

- Ejecutar desde línea de comandos (opción simple):

```bash
# Abre una terminal en la carpeta donde está el JAR y ejecuta:
java -jar PokeStorage.jar
```

- Crear un lanzador (.bat) en Windows (ejemplo): crea `run_pokestorage.bat` con este contenido:

```bat
@echo off
cd /d "%~dp0"
java -jar PokeStorage.jar
pause
```

Si recibes un error al iniciar, consulta al equipo de soporte o revisa el `README.md` del proyecto para instrucciones específicas de instalación.

---


## Primer inicio — Configuracion inicial

Al primer inicio la aplicación puede ofrecer crear los datos necesarios automáticamente. Si la aplicación no inicializa datos por sí sola, sigue estos pasos sencillos:

1. Inicia `PokeStorage.jar` con doble clic o mediante el lanzador.
2. Si la aplicación solicita crear una cuenta administrativa, crea una usando un email y contraseña válidos.
3. Comprueba que puedes iniciar sesión y acceder a la pantalla principal (`MainForm`).

Consejo: guarda una copia de tus datos regularmente utilizando la opción de exportar o guardando el archivo de datos desde la aplicación (si está disponible).

---

## Pantallas y flujo de la aplicación (UI)

A continuación se describen las pantallas principales y la finalidad de cada una. Todos los nombres de pantallas entre comillas corresponden a clases en el proyecto.

### Iniciar sesión / Registro

- Panel: `IniciarSesionPanel`
- Campos: Email, Contraseña
- Botones: `Iniciar Sesión`, `Registrar` (si existe)

Flujo:

1. El usuario introduce su email y contraseña.
2. Al pulsar `Iniciar Sesión`, la UI invoca `UsuarioBL` para validar credenciales.
3. Si las credenciales son correctas, se abre `MainForm`.
4. Si hay error, se muestra un mensaje con la causa (usuario no existe, contraseña incorrecta, error técnico).

Tip: Si olvidaste la contraseña revisa si el sistema tiene flujo de recuperación; si no, contacta al administrador.

### MainForm — Panel principal

`MainForm` actúa como contenedor de las secciones principales:

- Barra lateral o menú de navegación (Lista, Mochila, Historial, Ajustes).
- Área de contenido que carga los paneles: `ListaPokemon`, `MochilaPokemon`, `HistorialPanel`, etc.

Desde `MainForm` puedes:

- Navegar entre paneles.
- Ver estado del usuario actual (nombre/email).
- Cerrar sesión.

### ListaPokemon

`ListaPokemon` muestra la colección pública/disponible o la colección del usuario según diseño.

Funciones:

- Listado paginado (si aplica).
- Búsqueda por nombre y filtros por tipo/raridad.
- Botones para ver detalles o agregar a la mochila.

Ejemplo de uso:

1. En el campo de búsqueda escribe `Pikachu` y pulsa Enter.
2. En los resultados pulsa `Agregar a Mochila` para mover la entidad a `MochilaPokemon`.

### MochilaPokemon

`MochilaPokemon` es donde el usuario administra su colección privada.

Funciones:

- Ver lista de Pokémon guardados.
- Eliminar elementos.
- Ordenar por fecha, nombre, tipo.
- Exportar lista (si la aplicación soporta exportación).

### PokeStoragePanel y otras vistas

- `PokeStoragePanel`: vista principal de administración de almacenamiento y acciones globales.
- `HistorialPanel`: muestra obra de auditoría o actividad del usuario.

---

## Operaciones comunes (guías paso a paso)

### Registro de usuario

1. Abre la aplicación y haz clic en `Registrar` (si hay botón) o en la opción de registro del `IniciarSesionPanel`.
2. Completa el formulario: Nombre, Email, Contraseña, Confirmar Contraseña.
3. Pulsa `Registrar`.
4. Si el registro es válido, recibirás confirmación y podrás iniciar sesión.

Precauciones:

- Utiliza una contraseña segura (mínimo 8 caracteres, mezcla de letras y números).
- Si el email ya existe, el sistema mostrará un error.

### Inicio de sesión

1. Introduce tu email y contraseña en `IniciarSesionPanel`.
2. Pulsa `Iniciar Sesión`.
3. Si las credenciales son válidas, accederás a `MainForm`.

Solución de problemas:

- Error: `Usuario no encontrado` — revisa que el email esté correctamente escrito.
- Error: `Contraseña incorrecta` — intenta recuperar la contraseña o contactar soporte.

### Añadir/Eliminar Pokémon de la mochila

Añadir desde `ListaPokemon`:

1. Busca el Pokémon deseado.
2. Pulsa `Agregar a Mochila` junto al registro.
3. Abre `MochilaPokemon` para confirmar que aparece en la lista.

Eliminar desde `MochilaPokemon`:

1. Selecciona el Pokémon en `MochilaPokemon`.
2. Pulsa `Eliminar` o el icono de papelera.
3. Confirma la eliminación.

Precaución: la eliminación puede ser irreversible si no hay backups.

### Buscar y filtrar Pokémon

La mayoría de listados incluyen un cuadro de búsqueda y filtros.

- Búsqueda: introduce texto y pulsa Enter.
- Filtros: tipo, nivel, rareza — aplica y observa el listado actualizado.

Tip: combina búsqueda y filtros para resultados precisos.

### Exportar/Importar datos (backup)

Si la aplicación ofrece exportar la mochila o la lista de usuarios, sigue estos pasos:

1. Navega a `PokeStoragePanel` o a la sección `Ajustes`.
2. Busca `Exportar datos` o `Backup`.
3. Selecciona formato (CSV, JSON) si está disponible.
4. Elige ubicación y confirma.

Para importar, selecciona `Importar` y carga el fichero con el formato esperado.

---

Fin del Manual de Usuario — PokeStorage

