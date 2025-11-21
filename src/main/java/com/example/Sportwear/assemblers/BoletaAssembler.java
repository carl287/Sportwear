package com.example.Sportwear.assemblers;


import com.example.Sportwear.Controller.BoletaController;
import com.example.Sportwear.Model.Boleta;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class BoletaAssembler implements RepresentationModelAssembler<Boleta, EntityModel<Boleta>> {

    @Override
    public EntityModel<Boleta> toModel(Boleta boleta) {
        return EntityModel.of(boleta,
                linkTo(methodOn(BoletaController.class).getBoletaById(boleta.getId())).withSelfRel(),
                linkTo(methodOn(BoletaController.class).getAllBoletas()).withRel("all-boletas"),
                linkTo(methodOn(BoletaController.class).getBoletasByUser(boleta.getIdUser())).withRel("boletas-by-user"),
                linkTo(methodOn(BoletaController.class).deleteBoleta(boleta.getId())).withRel("eliminar boleta")
        );
    }
}
