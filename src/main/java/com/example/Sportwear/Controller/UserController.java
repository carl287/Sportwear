package com.example.Sportwear.Controller;



import com.example.Sportwear.Model.User;
import com.example.Sportwear.Repository.DTO.LoginRequest;
import com.example.Sportwear.Service.UserService;
import com.example.Sportwear.assemblers.UserModelAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@Tag(name = "Vista de usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    UserModelAssembler assembler;

    @Operation(summary = "Lista todos los usuarios", description = "Devuelve un listado de todos los usuarios registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuarios listados correctamente"),
            @ApiResponse(responseCode = "500", description = "Error interno al listar usuarios")
    })

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userService.findByUsername(username);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<User>>> getUsers() {
        List<User> lista = userService.listarUsuarios();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(assembler.toCollectionModel(lista), HttpStatus.OK);
        }
    }

    @Operation(summary = "Busca un usuario por ID", description = "Obtiene los datos de un usuario específico según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuario encontrado correctamente"),
            @ApiResponse(responseCode = "404", description = "Usuario no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno al buscar usuario")
    })
    @GetMapping("/id/{id}")
    @Parameter(description = "ID del usuario a buscar", example = "1")
    public ResponseEntity<EntityModel<User>> getUserById(@PathVariable int id) {
        User user = userService.obtenerUsuarioPorId(id);
        if (user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(assembler.toModel(user), HttpStatus.OK);
        }
    }

    @Operation(summary = "Inicio de sesión de usuario", description = "Permite que un cliente inicie sesión con su nombre de usuario y contraseña.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Inicio de sesión exitoso"),
            @ApiResponse(responseCode = "401", description = "Credenciales inválidas"),
            @ApiResponse(responseCode = "500", description = "Error interno en el inicio de sesión")
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            Optional<User> user = userService.login(loginRequest.getUsername(), loginRequest.getPassword());

            if (user.isPresent()) {
                return ResponseEntity.ok("¡Has iniciado sesión exitosamente!: " + user.get().getUsername());
            } else {
                return ResponseEntity.status(401).body("Credenciales inválidas");
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno en el inicio de sesión.");
        }
    }

    @Operation(summary = "Registrar usuario", description = "Crea un nuevo usuario en la base de datos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario registrado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos o usuario duplicado"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            User nuevo = userService.registrar(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);

        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al registrar usuario");
        }
    }




}
