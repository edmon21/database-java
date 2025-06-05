# Práctica 3 - Mini base de datos de videojuegos

Esta práctica consiste en ayudar a Jaume, un gamer profesional, a informatizar su colección de videojuegos. 
Para lograrlo, implementaremos una pequeña base de datos que permita guardar información sobre varios videojuegos. 
El sistema permitirá listar videojuegos, mostrar su información y eliminarlos. La información se obtiene inicialmente 
de archivos de texto, pero se guarda en un archivo binario que se utilizará para todas las operaciones posteriores. 
Esta práctica forma parte de la asignatura **Programación II** del **Grado de Ingeniería Informática (UOC)** durante el **curso 2024-2025**.

## Descripción

El objetivo de esta práctica es implementar una pequeña base de datos de videojuegos para ayudar a un usuario a gestionar su colección.

El sistema permite:
- Cargar videojuegos a partir de archivos de texto.
- Almacenar la información en un archivo binario.
- Listar los videojuegos disponibles.
- Consultar información de un videojuego concreto.
- Eliminar videojuegos.

## Requisitos

- **Java JDK 8 o superior**
- **IntelliJ IDEA** como entorno de desarrollo
- **Librería ACM**: se debe incluir `acm.jar` en el classpath para ejecutar el proyecto

## Archivos necesarios

Para poder utilizar la base de datos, es necesario disponer de los archivos `.txt` con la información de cada videojuego y del archivo `videogames.txt`, que contiene la lista de los archivos a cargar.

Estos archivos han sido incluidos en el repositorio y **son imprescindibles para inicializar correctamente la base de datos**.

### Formato esperado de los archivos `.txt` de videojuegos:
1. Título (obligatorio)
2. Serie (opcional)
3. Editor (si falta, se genera advertencia)
4. Año de publicación (si falta o no es válido, se pone -1)
5. Cantidad de ventas (si falta o no es válida, se pone -1)

### Ejemplo de `videogames.txt`
```
Minecraft.txt
GTA_V.txt
WiiSports.txt
...
```

## Estructura del proyecto

El proyecto se compone de las siguientes clases:

- `Main.java`: Contiene el menú principal y la interacción con el usuario.
- `VideoGameInfo.java`: Representa la información de un videojuego.
- `VideoGameInfoReader.java`: Lee los archivos de texto y retorna objetos `VideoGameInfo`.
- `VideoGamesDB.java`: Gestiona la base de datos binaria e implementa las operaciones CRUD.
- `PackUtils.java`: Clase auxiliar para empaquetar y desempaquetar datos en formato binario.

## Funcionamiento

1. Al ejecutar el programa, se lee `videogames.txt`.
2. Por cada archivo indicado, se lee la información del videojuego.
3. Se guarda la información en el archivo binario `videogamesDB.dat`.
4. Se muestra un menú con las siguientes opciones:
   - **1** – Listar los títulos de los videojuegos
   - **2** – Mostrar información de un videojuego
   - **3** – Eliminar un videojuego
   - **4** – Salir

## Ejecución

Asegúrate de que:
- Tienes `acm.jar` en el directorio padre o en el classpath del proyecto.
- Has compilado todas las clases.
- Estás ejecutando la clase `Main`.

Desde terminal (ejemplo):
```bash
java -cp ../acm.jar:. Main
```

## Notas finales

- El archivo `videogamesDB.dat` se reinicia en cada ejecución.
- Los errores (archivo no encontrado, título vacío...) se muestran por `System.err`.
- Este proyecto es únicamente una práctica académica, no está pensado para entornos de producción.
