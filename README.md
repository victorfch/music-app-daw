# App de musica


## Descripcion del proyecto.
En esta aplicación el cliente podrá guardar información sobre sus artistas como sus grupos de música favoritos. La clase que tiene el main de la versión gráfica se llama MusicaCLI y la del javaFX se llama MainApp

## Requisitos pedidos.
### Conexión con base de datos.
El nombre de la base de datos es music_project, el usuario es root y sin contraseña.
En este proyecto para la base de datos tenemos 3 tablas:
- Artista: Puede ser el nombre de un artista como David Bisbal o un grupo como Linkin Park
- Album: Relacionado con un determinado artista
- Cancion: Relacionado con un determinado album

Disponemos de dos interfaces funcionando como servicios ArtistaServicio y AlbumServicio. Cada una con su respectiva implementación y una clase llamada CancionServicioMySQL que es parecida a las demás pero ya tenía muchas clases hechas y tampoco quería añadir su intefaz.

### Code smell
En la carpeta docs dispones de un pdf donde se puede ver un ejemplo de code smell

### XML.
Se pueden importar artistas del fichero artistas.xml situado en la raiz del proyecto de NetBeans.
También se pueden exportar todos los albumes

### Expresiones regulares.
Disponemos de dos expresiones regulares.
La primera está en la clase ArtistaEditController, en la línea 52, donde se controla que la nacionalidad tenga solo 3 letras.
La segunda está en la clase Constantes, en la línea 32, utilizada para controlar las fechas introducidas en la versión CLI.

## Funcionamiento del programa.
### Artista
Para añadir un artista o grupo nuevo haga click en el botón "Añadir" de la ventana de Artistas.
En esta ventana tendrá un listado de los artistas guardados, la posibilidad de importarlos de un xml y un filtro donde escribiendo su nombre o la nacionalidad y haciendo click en filtrar, se mostrarán los artistas que cumplan dichos criterios
Las acciones permitidas son editarlo, verlo mas detalladamente y eliminarlo.
Al elegir la opción "Ver" saldrá una ventana donde a la izquierda tenemos los campos, no son editables. Y a la derecha los albumes de ese artista.

### Album
Para añadir un album es obligatorio tener un artista guardado. Para guardar un album haga click en el botón "Añadir" en la ventada de Albumes, en el formulario tienes un desplegable para elegir el artista al que pertenece.
En el panel principal dispone de una lista de albumes, la opción de exportar a xml y también tiene la opción de filtrar por nombre o género, pop, rock, house, etc.
Las acciones permitidas son editarlo y eliminarlo.

### Canción
Para añadir una canción es necesario tener un artista y un album asociado. Al elegir Canciones en el menu se mostrará una ventana con dos ComboBox, al elegir el artista se refresca automáticamente los álbumes del artista elegido. A continuación tienes un campo para escribir el título de dicha canción.
Al darle a buscar saldrá una alerta donde al hacer click en leer más podrás encontrar la letra de dicha canción. Da igual que que el nombre del artista/grupo tenga espacios, ya todo está debidamente saneado para no tener problemas. Un ejemplo sería que al cantante Ed Sheeran, el album al que pertenece(a la hora de buscar la canción este campo no afecta pero necesitaba algo intermedio para tener 3 tablas en este proyecto, por eso la creación de la tabla album) y el título de la canción happier. Y te debería salir dicha canción.
Las acciones disponibles solo son eliminar y ver(se verá en una alerta).
Puedes buscar cualquier letra de cualquier grupo que en caso de no tener la letra se te avisará.


## Capturas de pantalla.
Resumen del resultado de sonar

<img src="https://github.com/victorfch/music-app-daw/blob/master/BibiotecaMusicaFX/docs/img/sonar.png">



Si miramos fijamente nos encontramos con este resultado

<img src="https://github.com/victorfch/music-app-daw/blob/master/BibiotecaMusicaFX/docs/img/sonar-detallado.png">


A continuación, tenemos un par de capturas del JavaDoc que está situado en la carpeta docs

<img src="https://github.com/victorfch/music-app-daw/blob/master/BibiotecaMusicaFX/docs/img/javadoc.png">

<img src="https://github.com/victorfch/music-app-daw/blob/master/BibiotecaMusicaFX/docs/img/comentario-javadoc.png">
