Este proyecto fue desarrollado para la asignatura Aplicaciones Móviles, correspondiente al cuarto semestre de la carrera de Ingeniería en Informática.
El trabajo fue realizado por Paula Leiva y Carlos Calvio.

SportWear: Tienda Deportiva -- ¿Cómo logramos su desarrollo?

- Backend con **Spring Boot** + Oracle Autonomous Database  
- App móvil en **Android Studio (Kotlin + Jetpack Compose)**  
- Consumo de **microservicios** vía Retrofit  
- API externa adicional de frases
- Pruebas unitarias en backend y móvil  
- Documentación mediante Swagger




## Funcionalidades principales en el:

### Backend – Spring Boot
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

-Sincronizar con gradle :
✔settings.gradle
✔ build.gradle (Proyecto)
✔ build.gradle (App / módulo)
✔ libs.versions.toml


#### **3. Ejecutar el backend**

-Iniciar el proyecto desde IntelliJ IDEA utilizando Run en la clase principal SportwearApplication.

Si el servicio se inicia correctamente, la consola mostrará un mensaje similar a:

Using generated security password: <valor_generado>

Esta contraseña corresponde al usuario predeterminado user creado por Spring Security para entornos de desarrollo.
Es importante copiar y guardar este valor, ya que será necesario para acceder a la interfaz de Swagger UI o realizar solicitudes protegidas mientras no se configure un mecanismo de autenticación propio.

#### **4. Acceder a Swagger ya subido a una instancia AWS**
link: http://44.222.120.69:8080/swagger-ui/index.html#/

-si te pide iniciar sesion el usuario es user y la  contraseña es la contraseña temporal que aparece en la terminal en la linea 
que dice "Using generated security password: <valor_generado>".

¿Por qué?
Debido a que Spring Boot te está mostrando esa contraseña porque Spring Security está activado por defecto.


#### **5. Pruebas básicas del backend (API REST)**

Una vez que el backend esté ejecutándose correctamente, es importante verificar que los endpoints funcionen antes de conectar la app móvil.
Para esto puedes utilizar Swagger, que permite probar cada operación HTTP de manera visual.

A continuación, se describen pruebas esenciales:

#### A) Crear un producto (POST)

En Swagger, abre el endpoint:
POST /api/products

Haz clic en Try it out.

Ingresa un JSON válido con los atributos del producto, por ejemplo:
{
  "name": "Zapatillas Adizero Boston 13 Mujer",
  "description": "Zapatillas de alto rendimiento para running",
  "price": 139990,
  "category": "Zapatillas",
  "size": "M",
  "color": "Rojo",
  "imageUrl": "https://www.adidas.cl/camiseta-local-seleccion-chilena-26/KG8542.html",
  "stock": 20
}
Ejecuta la petición y verifica que el servidor responde con 201 (Created)

#### B) Obtener productos (GET)

-Abre el endpoint:
GET /api/products

-En id pon el id que se creó automaticamente en POST, este id se meustra abajo de 201 .

-Verifica que aparezca el producto creado en el paso anterior.

#### C) Actualizar un producto (PUT)

Abre:
PUT /api/products/{id}

Envía un JSON con la información actualizada (por ejemplo, cambiar la imageUrl).

Ejecuta y revisa nuevamente el GET para confirmar que los cambios se aplicaron.


 #### **6. Confirmar el despliegue en AWS**

Instancia 1 – Link:
http://44.221.81.240:8080/api/products
¿Qué función cumple esta instancia?
Esta instancia corresponde al servidor principal utilizado para ejecutar y probar la API en producción.
Permite realizar pruebas completas de los endpoints (GET, POST, PUT, DELETE) desde Swagger, Postman u otra herramienta, verificando que el backend Spring Boot esté funcionando correctamente.

Instancia 2 – Link:
http://44.222.120.69:8080/api/products
¿Qué función cumple esta instancia?
Esta instancia se utiliza como servidor espejo para visualizar el catálogo desde la aplicación móvil.
La app consume este endpoint para mostrar los productos en tiempo real. También permite comprobar que la API está operativa, accesible públicamente y retornando los datos necesarios para el frontend.

 **¿Qué diferencia hay entre ambas?**

Instancia 1: ambiente de pruebas directas de API (testing de endpoints).

Instancia 2: ambiente consumido por la app móvil para visualizar el catálogo (producción ligera).








agregando los archivos previamente ya agregados : RetrofitClient.kt, ProductApi.kt, archivos Local y Remote con los links correspondientes dentro de los codigos.

