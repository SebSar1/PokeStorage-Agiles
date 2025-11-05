
# Manual Técnico — PokeStorage

> Versión: 1.0

## Índice

1. [Introduccion](#introduccion)
2. [Alcance y objetivos](#alcance-y-objetivos)
3. [Resumen del proyecto](#resumen-del-proyecto)
4. [Estructura del repositorio y ficheros clave](#estructura-del-repositorio-y-ficheros-clave)
5. [Requisitos y dependencias](#requisitos-y-dependencias)
6. [Arquitectura del sistema](#arquitectura-del-sistema)
    - [Capas y responsabilidades](#capas-y-responsabilidades)
    - [Diagramas (componentes y flujo)](#diagramas-componentes-y-flujo)
7. [Base de datos y scripts SQL](#base-de-datos-y-scripts-sql)
8. [Detalles de implementacion por modulo](#detalles-de-implementacion-por-modulo)
    - [DTOs](#dtos)
    - [DAO DataAccess](#dao-dataaccess)
    - [Business Logic (BL)](#business-logic-bl)
    - [User Interface (UI)](#user-interface-ui)
9. [Instalacion, compilacion y despliegue](#instalacion-compilacion-y-despliegue)
10. [Ejecucion y uso](#ejecucion-y-uso)
11. [Pruebas y verificacion](#pruebas-y-verificacion)
12. [Mantenimiento y respaldo](#mantenimiento-y-respaldo)

## Introduccion

Este documento describe de forma técnica la aplicación PokeStorage: su arquitectura, ficheros, instalación, despliegue, pruebas, mantenimiento y recomendaciones. Está pensado para desarrolladores, administradores y personal técnico que vaya a mantener o desplegar la aplicación.

El contenido está redactado para ser auto-contenido y accionable. Incluye ejemplos de ejecución y referencias a los ficheros existentes en el repositorio.

## Alcance y objetivos

- Documentar la arquitectura del sistema.
- Proveer instrucciones reproducibles para compilar, ejecutar y desplegar.
- Explicar la estructura de datos y scripts SQL.
- Dar pautas de pruebas, mantenimiento y seguridad.

Audiencia: desarrolladores Java (nivel intermedio-avanzado), administradores de BD y QA.

## Resumen del proyecto

PokeStorage es una aplicación Java (aplicación de escritorio / Swing) organizada en capas: interfaz de usuario, lógica de negocio, acceso a datos y scripts SQL para la base de datos. La estructura principal del proyecto está dentro de la carpeta `src/`.

Breve funcionalidad: gestión de usuarios y almacenamiento relacionado con una colección de Pokémon (según nombres de paquetes y formularios: `MochilaPokemon`, `ListaPokemon`, etc.).

## Estructura del repositorio y ficheros clave

Estructura principal (resumen):

```text
src/
    App.java
    BusinessLogic/UsuarioBL.java
    DataAccess/
        IDAO.java
        SQLiteDataHelper.java
        DAO/UsuarioDao.java
        DTO/UsuarioDTO.java
    UserInterface/
        CustomerControl/
            SebButton.java
            SebLabel.java
        Form/
            HistorialPanel.java
            IniciarSesionPanel.java
            ListaPokemon.java
            LoginAdminPanel.java
            MainForm.java
            MochilaPokemon.java
            PokeStoragePanel.java
            SplashScreenForm.java

```
- `DataAccess/SQLiteDataHelper.java`: helper para conexión con SQLite.
- `DataAccess/DAO/UsuarioDao.java`: DAO para operaciones CRUD sobre usuarios.
- `DataAccess/DTO/UsuarioDTO.java`: DTO que modela la entidad Usuario.
- `Script/DDL_Usuario.sql`, `Script/DML.sql`: scripts para crear y poblar la base de datos.

## Requisitos y dependencias

Hardware mínimo recomendado:
- CPU moderna (2+ cores), 4 GB RAM.

Software / dependencias:
- JDK 8 o superior (se recomienda JDK 11+).
- SQLite (archivo de base de datos local). No se requiere servidor separado.
- Entorno de desarrollo: cualquier IDE Java (IntelliJ, Eclipse, NetBeans) o compilación por línea de comandos con `javac`.

Librerías externas: el proyecto parece usar solo la librería estándar de Java (Swing para UI) y JDBC (driver de SQLite). Si falta el driver SQLite JDBC, descargar `sqlite-jdbc` y añadirlo al classpath.

Verificar versión de Java:

```
java -version
javac -version
```

## Arquitectura del sistema

El sistema sigue una arquitectura clásica en capas:

- Capa de presentación (UI): `src/UserInterface/Form/*` y `src/UserInterface/CustomerControl/*`.
- Capa de lógica de negocio (BL): `src/BusinessLogic/*`.
- Capa de acceso a datos (DAO): `src/DataAccess/DAO/*`, con `SQLiteDataHelper` como auxiliar de conexión.
- Capa de modelos/datos (DTO): `src/DataAccess/DTO/*`.

### Capas y responsabilidades

- UI: renderiza formularios, recoge entradas del usuario y muestra resultados. No debe contener lógica de acceso a datos.
- BL: valida reglas, orquesta operaciones entre UI y DAOs.
- DAO: encapsula consultas SQL, mapeo entre `ResultSet` y `DTO`.
- DTO: objetos simples con getters/setters que transportan datos entre capas.

### Diagramas (componentes y flujo)

Nota: aquí se proporciona una descripción y marcadores para insertar diagramas. Si desea imágenes, puede colocarlas en `docs/img/` y referenciarlas.

- Diagrama de componentes: UI ↔ BL ↔ DAO ↔ SQLite
- Diagrama ER (resumen): tabla `Usuario` con campos típicos (id, nombre, email, password, fechaRegistro). Consulte `Script/DDL_Usuario.sql`.

Ejemplo ASCII simple (flujo):

```
Usuario (UI) -> IniciarSesionPanel -> UsuarioBL -> UsuarioDao -> SQLite (Script/DDL_Usuario.sql)
```

## Base de datos y scripts SQL

Los scripts se encuentran en `Script/DDL_Usuario.sql` y `Script/DML.sql`.

- `DDL_Usuario.sql`: contiene las sentencias CREATE TABLE y constraints.
- `DML.sql`: inserciones iniciales para pruebas.

Recomendaciones:

1. Revisar `DDL_Usuario.sql` antes de ejecutar en producción.
2. Realizar un respaldo del fichero `.db` (si existe) antes de aplicar cambios.

Ejecutar los scripts con sqlite3 (ejemplo):

```
sqlite3 pokestorage.db < Script/DDL_Usuario.sql
sqlite3 pokestorage.db < Script/DML.sql
```

Si se usa el driver JDBC, la aplicación crea/usa automáticamente el archivo de BD según la configuración en `SQLiteDataHelper.java`.

## Detalles de implementacion por modulo

En las siguientes subsecciones se describen responsabilidades y fragmentos de ejemplo. Adaptar según el código real en `src/`.

### DTOs

`UsuarioDTO.java` representa la entidad Usuario.

Contrato:

- Campos típicos: id (int), nombre (String), email (String), password (String), fechaRegistro (Date).
- Métodos: getters/setters, constructor vacío y constructor con campos.

Ejemplo de uso (pseudocódigo):

```
UsuarioDTO u = new UsuarioDTO();
u.setNombre("Ash");
u.setEmail("ash@example.com");

// Pasar DTO a DAO/BL
```

Edge cases: campos nulos, validaciones en BL, tamaños máximos.

### DAO DataAccess

`UsuarioDao.java` implementa `IDAO` para persistencia.

Responsabilidades:

- Abrir/cerrar conexiones (usar `SQLiteDataHelper`).
- Ejecutar consultas parametrizadas (PreparedStatement) para evitar inyección SQL.
- Mapear `ResultSet` a `UsuarioDTO`.

Ejemplo ilustrativo de método (pseudocódigo):

```
public UsuarioDTO findById(int id) {
	Connection c = helper.getConnection();
	PreparedStatement ps = c.prepareStatement("SELECT * FROM Usuario WHERE id = ?");
	ps.setInt(1, id);
	ResultSet rs = ps.executeQuery();
	if (rs.next()) {
		return mapResultSetToDTO(rs);
	}
	return null;
}
```

Buenas prácticas:

- Cerrar `ResultSet`, `PreparedStatement` y `Connection` en finally o usar try-with-resources (Java 7+).
- Manejar y registrar excepciones SQLException.

### Business Logic (BL)

`UsuarioBL.java` contiene validaciones y reglas de negocio. Ejemplos:

- Verificar unicidad de email antes de registrar usuario.
- Validar formato del email y complejidad de contraseña.
- Orquestar llamadas a DAO y devolver resultados adecuados a UI.

Contrato (inputs/outputs):

- Input: DTOs o parámetros primitivos desde la UI.
- Output: DTOs, booleanos o excepciones controladas.

### User Interface (UI)

Formulario principal: `MainForm.java`.

Paneles importantes:
- `IniciarSesionPanel.java`: formulario de login.
- `ListaPokemon.java`, `MochilaPokemon.java`: vistas relacionadas con la colección.

Consideraciones:

- Separar lógica de presentación de lógica de validación (mantener UI delgada).
- En eventos de UI, llamar a `UsuarioBL` y gestionar resultados (mostrar mensajes). 

Ejemplo de evento (pseudocódigo):

```
loginButton.addActionListener(e -> {
	boolean ok = usuarioBL.login(emailField.getText(), passwordField.getText());
	if (ok) {
		// abrir MainForm
	} else {
		// mostrar error
	}
});
```

## Instalacion, compilacion y despliegue

Preparar el entorno:

1. Instalar JDK 11+.
2. (Opcional) Añadir driver `sqlite-jdbc` al classpath o al proyecto.

Compilar desde la raíz del proyecto (bash en Windows e.g., Git Bash):

```
# Compilar todo
javac -d bin -sourcepath src $(find src -name "*.java")

# Empaquetar en JAR (opcional)
jar cfe PokeStorage.jar App -C bin .
```

Nota: en Windows, `find` puede ser reemplazado por un script o compilación desde IDE. Alternativamente usar ant/maven/gradle si se desea.

Despliegue:

- Entregar `PokeStorage.jar` y el driver sqlite en el mismo directorio, junto con el archivo de base de datos `pokestorage.db` si se desea pre-poblar.
- Configurar atajo o script de inicio que ejecute:

```
java -cp ".;sqlite-jdbc.jar;PokeStorage.jar" App
```

## Ejecucion y uso

- Ejecutar el JAR o desde el IDE.
- Primer arranque: si la BD no existe, la aplicación puede crear las tablas mediante `SQLiteDataHelper` o se debe ejecutar `Script/DDL_Usuario.sql`.

Recomendación para pruebas locales:

1. Crear una BD de desarrollo: `pokestorage_dev.db`.
2. Aplicar `DDL_Usuario.sql` y `DML.sql`.
3. Ejecutar la aplicación apuntando a esta BD.

## Pruebas y verificacion

Estrategia de pruebas mínima:

- Pruebas unitarias (recomendadas): crear tests automatizados para la capa BL y DAO. Framework sugerido: JUnit 5.
- Pruebas de integración: verificar que DAO escribe/lee correctamente con una BD SQLite en memoria (`jdbc:sqlite::memory:`) o con una BD de prueba.
- Pruebas manuales: pasos de verificación para login, registro de usuario y operaciones CRUD.

Casos de prueba básicos (ejemplos):

1. Registro de usuario válido — esperar éxito.
2. Registro con email duplicado — esperar error controlado.
3. Login con credenciales válidas/ inválidas.
4. Lectura/actualización de información de usuario.

Comandos útiles para ejecutar pruebas (si se añaden tests con Maven/Gradle):

```
mvn test
gradle test
```

## Mantenimiento y respaldo

Backups:

- Hacer copias periódicas del archivo SQLite (.db). Por ejemplo: `pokestorage.db.bak`.
- Mantener scripts DDL y DML versionados en `Script/`.

Procedimiento de restauración rápida:

1. Detener la aplicación.
2. Reemplazar `pokestorage.db` por el backup.
3. Reiniciar.


