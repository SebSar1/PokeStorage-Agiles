# **PokeStorage – Documento de Diseño Técnico (SDD)**
**Versión:** 1.1  
**Fecha:** 2025-11-04  
**Autores:** Equipo PokeStorage  
**Metodología:** Modelo Tradicional Basado en Prototipos  
**Repositorio:** [PokeStorage-Agiles](https://github.com/SebSar1/PokeStorage-Agiles/tree/main)

---

## **1. Introducción**

### **1.1 Propósito**
Este documento describe el diseño técnico del proyecto PokeStorage, implementado con una arquitectura en capas funcionales, organizada en BusinessLogic, DataAccess, Resource y UserInterface.  
El propósito es detallar cómo están estructurados los módulos, sus relaciones, responsabilidades, y cómo cada uno satisface los requerimientos definidos en el SRD.

### **1.2 Alcance**
El diseño técnico define:
- La organización del código fuente por capas.  
- La estructura de clases y sus interacciones.  
- Los componentes del sistema (interfaz, lógica y acceso a datos).  
- La relación de cada componente con los requisitos funcionales (RF) y las issues de Jira.

---

## **2. Arquitectura del Sistema**

El sistema adopta una arquitectura modular en capas, que organiza el código en función de responsabilidades específicas.

### **2.1 Capas Principales**

| Capa | Descripción | Ejemplos de clases / funciones | RF asociados |
|------|--------------|--------------------------------|---------------|
| **BusinessLogic/** | Contiene la lógica de negocio y las estructuras de datos (listas, pilas, árboles). | `ListaCircular.java`, `Pila.java`, `ArbolAVL.java`, `Pokemon.java` | RF03–RF07 |
| **DataAccess/** | Maneja la lectura y escritura en archivos o base de datos local (persistencia). | `ArchivoManager.java`, `ConexionDB.java` | RF01, RF03 |
| **Resource/Img/** | Almacena imágenes, íconos y otros recursos visuales. | `Img/` (sprites, fondos, íconos) | RF08 |
| **UserInterface/** | Define la interfaz gráfica con la que interactúa el usuario. | `LoginWindow.java`, `MainMenu.java`, `RegisterPokemon.java`, `HistorialView.java` | RF01–RF08 |

---

## **3. Diseño de la Interfaz (Prototipo Visual)**

### **3.1 Descripción General del Prototipo**

El prototipo de baja fidelidad fue creado en Figma para definir la estructura visual del sistema, probar el flujo de navegación y validar la experiencia del usuario antes del desarrollo técnico.

📎 **Enlace al prototipo:**  
[🎨 Ver en Figma](https://www.figma.com/proto/NC9hzU04BwlzPl3x1dlIuO/POKESTORAGE?node-id=4-49&p=f&t=eNDcSpQYYDKjEFM9-1&scaling=min-zoom&content-scaling=fixed&page-id=0%3A1&starting-point-node-id=23%3A75)

### **3.2 Flujo General de Navegación**

- **Pantalla Splash** (Invitación Grupo de WhatsApp)  
  ↓  
- **Pantalla Principal**  
  ├── **Iniciar Sesión** → *Pantalla Inicio de Sesión* → *Pantalla Usuario*  
  └── **Crear Cuenta** → *Pantalla Crear Cuenta* → *Pantalla Usuario*

- **Pantalla Usuario**  
  ├── **Registrar Pokémon** → *Pantalla Registrar*  
  ├── **Ver Árbol** → *Pantalla Ver Árbol*  
  ├── **Historial** → *Pantalla Historial*  
  └── **Lista Pokémon** → *Lista Pokémon (Anterior / Siguiente)*

### **3.3 Descripción de Pantallas**

| Pantalla | Descripción | Elementos principales | RF Asociados |
|-----------|--------------|------------------------|---------------|
| **Splash** | Pantalla inicial de bienvenida con logo y acceso. | Imagen principal, QR, botón de inicio. | RF08 |
| **Principal** | Permite iniciar sesión o crear una cuenta. | Botones “Iniciar sesión” y “Crear cuenta”. | RF01, RF02 |
| **Crear Cuenta** | Registra nuevos usuarios con nombre, edad y contraseña. | Campos de texto, botón “Guardar”. | RF01 |
| **Inicio de Sesión** | Valida credenciales existentes. | Inputs de usuario y contraseña. | RF02 |
| **Menú Usuario** | Panel principal con accesos a las funciones del sistema. | Botones “Registrar”, “Historial”, “Ver Árbol”, “Lista Pokémon”. | RF03–RF07 |
| **Registrar Pokémon** | Permite agregar nuevos Pokémon con sus datos. | Campos: nombre, tipo, poder, imagen, botón guardar. | RF03 |
| **Lista Pokémon** | Muestra Pokémon en recorrido circular (anterior / siguiente). | Imágenes y botones de navegación. | RF04, RF06 |
| **Ver Árbol** | Visualiza los Pokémon ordenados por poder (AVL). | Panel de visualización. | RF07 |
| **Historial** | Registra y muestra los Pokémon capturados o eliminados. | Tabla de registros y botón regresar. | RF05 |
