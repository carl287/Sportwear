package com.example.Sportwear.Controller;

import com.example.Sportwear.Model.Shipping;
import com.example.Sportwear.Service.ShippingService;
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
@RequestMapping("/shipping")
@Tag(name = "Vista de Envíos (Shipping)")
public class ShippingController {

    private final ShippingService shippingService;

    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    //POST
    @Operation(summary = "Crear un nuevo registro de envío", description = "Registra los detalles de un nuevo envío en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Envío creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
            @ApiResponse(responseCode = "500", description = "Error interno al crear el envío")
    })
    @PostMapping
    public ResponseEntity<Shipping> createShipping(@RequestBody Shipping shipping) {
        try {
            Shipping newShipping = shippingService.create(shipping);
            return new ResponseEntity<>(newShipping, HttpStatus.CREATED); // 201 Created
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    //GET (Buscar por ID) ---
    @Operation(summary = "Busca un envío por ID", description = "Obtiene los detalles de un envío específico según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envío encontrado correctamente"),
            @ApiResponse(responseCode = "404", description = "Envío no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno al buscar envío")
    })
    @GetMapping("/{id}")
    @Parameter(description = "ID del envío a buscar", example = "5")
    public ResponseEntity<Shipping> getShippingById(@PathVariable int id) {
        try {
            Shipping shipping = shippingService.getById(id);
            return new ResponseEntity<>(shipping, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //GET
    @Operation(summary = "Lista todos los envíos", description = "Devuelve un listado de todos los envíos registrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envíos listados correctamente"),
            @ApiResponse(responseCode = "204", description = "No hay contenido (lista vacía)"),
            @ApiResponse(responseCode = "500", description = "Error interno al listar envíos")
    })
    @GetMapping
    public ResponseEntity<List<Shipping>> getAllShippings() {
        List<Shipping> lista = shippingService.getAll();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }
    }

    // PUT
    @Operation(summary = "Actualizar detalles del envío", description = "Actualiza toda la información de un envío existente por su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Envío actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Envío no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno al actualizar el envío")
    })
    @PutMapping("/{id}")
    @Parameter(description = "ID del envío a actualizar", example = "5")
    public ResponseEntity<Shipping> updateShipping(@PathVariable int id, @RequestBody Shipping shippingDetails) {
        try {
            Shipping updatedShipping = shippingService.update(id, shippingDetails);
            return new ResponseEntity<>(updatedShipping, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // --- DELETE (Eliminar por ID) ---
    @Operation(summary = "Eliminar envío por ID", description = "Elimina un registro de envío específico según su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Envío eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Envío no encontrado"),
            @ApiResponse(responseCode = "500", description = "Error interno al eliminar envío")
    })
    @DeleteMapping("/{id}")
    @Parameter(description = "ID del envío a eliminar", example = "5")
    public ResponseEntity<HttpStatus> deleteShipping(@PathVariable int id) {
        try {
            shippingService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}