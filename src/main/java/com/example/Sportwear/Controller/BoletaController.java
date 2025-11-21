package com.example.Sportwear.Controller;

import com.example.Sportwear.Model.Boleta;
import com.example.Sportwear.Service.BoletaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boletas")
@Tag(name = "Vista de Boletas")
public class BoletaController {
    private final BoletaService boletaService;
    public BoletaController(BoletaService boletaService) {
        this.boletaService = boletaService;
    }
    //GET
    @Operation(summary = "Lista todas las boletas", description = "Devuelve un listado de todas las boletas registradas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boletas listadas correctamente"),
            @ApiResponse(responseCode = "204", description = "No hay contenido (lista vacía)"),
            @ApiResponse(responseCode = "500", description = "Error interno al listar boletas")
    })
    @GetMapping
    public ResponseEntity<List<Boleta>> getAllBoletas() {
        List<Boleta> lista = boletaService.getAll();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } else {
            return new ResponseEntity<>(lista, HttpStatus.OK); // 200 OK
        }
    }

    //GET (Buscar por ID)
    @Operation(summary = "Busca una boleta por ID", description = "Obtiene los datos de una boleta específica según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boleta encontrada correctamente"),
            @ApiResponse(responseCode = "404", description = "Boleta no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno al buscar boleta")
    })
    @GetMapping("/{id}")
    @Parameter(description = "ID de la boleta a buscar", example = "101")
    public ResponseEntity<Boleta> getBoletaById(int id) {
        try {
            Boleta boleta = boletaService.getById(id);
            return new ResponseEntity<>(boleta, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //GET (Buscar por ID de Usuario)
    @Operation(summary = "Busca boletas por ID de usuario", description = "Obtiene todas las boletas asociadas a un ID de usuario específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Boletas listadas correctamente"),
            @ApiResponse(responseCode = "204", description = "No hay boletas para ese usuario"),
            @ApiResponse(responseCode = "500", description = "Error interno al buscar boletas")
    })
    @GetMapping("/user/{idUser}")
    @Parameter(description = "ID del usuario para buscar sus boletas", example = "2")
    public ResponseEntity<List<Boleta>> getBoletasByUser(@PathVariable int idUser) {
        List<Boleta> lista = boletaService.getByUser(idUser);
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } else {
            return new ResponseEntity<>(lista, HttpStatus.OK); // 200 OK
        }
    }

    //POST
    @Operation(summary = "Crear nueva boleta", description = "Registra una nueva boleta en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Boleta creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno al crear boleta")
    })
    @PostMapping
    public ResponseEntity<Boleta> createBoleta(@RequestBody Boleta boleta) {
        try {
            Boleta newBoleta = boletaService.create(boleta);
            return new ResponseEntity<>(newBoleta, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DELETE
    @Operation(summary = "Eliminar boleta por ID", description = "Elimina una boleta específica según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Boleta eliminada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Boleta no encontrada"),
            @ApiResponse(responseCode = "500", description = "Error interno al eliminar boleta")
    })
    @DeleteMapping("/{id}")
    @Parameter(description = "ID de la boleta a eliminar", example = "101")
    public ResponseEntity<HttpStatus> deleteBoleta(@PathVariable int id) {
        try {
            boletaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
