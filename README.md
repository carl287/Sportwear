Este proyecto fue desarrollado para la asignatura Aplicaciones Móviles, correspondiente al cuarto semestre de la carrera Ingeniería en Informática.
El trabajo fue realizado por Paula Leiva y Carlos Calvio.

SportWear: Tienda Deportiva -- ¿Cómo logramos su desarrollo?

- Backend con **Spring Boot** + Oracle Autonomous Database  
- App móvil en **Android Studio (Kotlin + Jetpack Compose)**  
- Consumo de **microservicios** vía Retrofit  
- API externa adicional de frases
- Pruebas unitarias en backend y móvil  
- Documentación mediante Swagger




## Funcionalidades principales en el:

### 🖥 Backend – Spring Boot
El backend del sistema provee varios microservicios completamente funcionales:

#### **1. Gestión de Productos**
- Crear, actualizar, eliminar y listar productos.
- Filtrar por talla.
- Aumentar y disminuir stock por ID.

#### **2. Gestión de Envíos**
- Registrar envíos.
- Buscar y eliminar envíos por ID.
- Listar todos los envíos.

#### **3. Proveedores (Suppliers)**
- CRUD completo de proveedores.

#### **4. Usuarios**
- Registro y login.
- Listado de usuarios.
- Búsqueda por ID.

#### **5. Boletas**
- Crear boleta asociada a un usuario.
- Listar y eliminar boletas.
- Filtrar boletas por usuario.






## Pasos para ejecutar el Backend (Spring Boot + Oracle Autonomous Database)

#### **1. Clonar el repositorio**
git clone https://github.com/carl287/Sportwear

#### **2.Abrir el proyecto en IntelliJ IDEA**
-Selecciona "Open" y carga el proyecto.

-Instala y actualiza todas las sugerencias.

-Cambia las versiones si es necesario en pom.xml.


#### **3. Ejecutar el backend)**
-Desde IntelliJ (botón -> Run).

-Esto significa que levantaste el servicio correctamente, en la terminal hay una linea 
que dice "Using generated security password: ".


-Guarda la password.

#### **4. Acceder a Swagger**
link: http://localhost:8080/swagger-ui/index.html

-si te pide iniciar sesion el usuario es user y la  contraseña es la contarseña temporal que aparece en la terminal en la linea 
que dice "Using generated security password: ".

¿Por qué?
Debido a que Spring Boot te está mostrando esa contraseña porque Spring Security está activado por defecto.



