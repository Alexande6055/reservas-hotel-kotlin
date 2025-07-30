# ✅ Enunciado #1: Sistema de Reservas para Hotel por Consola

## Descripción general

Desarrolla una aplicación de consola en **Kotlin** que permita gestionar un sistema básico de **reservas de habitaciones en un hotel**.  
La aplicación debe ser capaz de registrar usuarios y asignarlos a habitaciones disponibles (privadas o compartidas).  
Además, debe permitir al administrador agregar nuevas habitaciones al sistema.

---

## 🧾 Requisitos funcionales

### Registro de usuarios

- El sistema debe solicitar el **nombre completo** y el **número de identificación** del huésped.
- El usuario deberá indicar cuántas personas desean hospedarse con él (**mínimo 1**).
- El sistema intentará asignar una habitación disponible:
    - ✅ Si hay una **habitación privada** disponible para la cantidad de personas, se asigna.
    - ✅ Si no, busca **habitaciones compartidas** con camas suficientes.
    - ❌ Si no hay espacio suficiente, se notifica que **no hay disponibilidad**.

### Gestión de habitaciones (solo por administrador)

- El sistema debe permitir ingresar **nuevas habitaciones**.
- Cada habitación debe tener:
    - Un **identificador único (ID)**
    - **Tipo**: Privada o Compartida
    - **Número total de camas**
    - **Número de camas disponibles** (se actualiza automáticamente)

### Reservas

- Cada vez que un huésped se registre, debe asignarse a una habitación que cumpla los requisitos.
- En habitaciones compartidas, deben poder convivir huéspedes distintos mientras haya camas disponibles.

### Visualización del estado actual

- El sistema debe permitir mostrar:
    - ✅ Habitaciones disponibles y su estado.
    - ✅ Historial de huéspedes registrados y su habitación asignada.

---

## 🛠️ Pistas técnicas

- Usa **clases** para representar: `Habitación`, `Huésped`, `Reserva`, y `Hotel`.
- Usa **colecciones** como `List`, `MutableList`, o incluso `Map`.
- Usa **funciones** para mantener el código modular.
- El ciclo principal del programa debe funcionar con un `while (true)` hasta que se decida salir.
- Usa entrada/salida por consola (`readLine()`, `println()`).
- **Opcional**: puedes manejar usuarios con roles (`Admin`, `Cliente`).

---

## 📈 Objetivo didáctico

Este ejercicio te permitirá practicar:

- ✅ Programación orientada a objetos en Kotlin
- ✅ Colecciones mutables
- ✅ Lógica condicional y repetitiva
- ✅ Diseño de estructuras de datos personalizadas
- ✅ Validación de datos
- ✅ Simulación de un sistema en consola


# reservas_hotel

This project uses [Gradle](https://gradle.org/).
To build and run the application, use the *Gradle* tool window by clicking the Gradle icon in the right-hand toolbar,
or run it directly from the terminal:

* Run `./gradlew run` to build and run the application.
* Run `./gradlew build` to only build the application.
* Run `./gradlew check` to run all checks, including tests.
* Run `./gradlew clean` to clean all build outputs.

Note the usage of the Gradle Wrapper (`./gradlew`).
This is the suggested way to use Gradle in production projects.

[Learn more about the Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html).

[Learn more about Gradle tasks](https://docs.gradle.org/current/userguide/command_line_interface.html#common_tasks).

This project follows the suggested multi-module setup and consists of the `app` and `utils` subprojects.
The shared build logic was extracted to a convention plugin located in `buildSrc`.

This project uses a version catalog (see `gradle/libs.versions.toml`) to declare and version dependencies
and both a build cache and a configuration cache (see `gradle.properties`).