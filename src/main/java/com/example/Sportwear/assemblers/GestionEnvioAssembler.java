package com.example.Sportwear.assemblers;


import com.example.Sportwear.Controller.GestionEnvioController;
import com.example.Sportwear.Model.GestionEnvio;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GestionEnvioAssembler implements RepresentationModelAssembler<GestionEnvio, EntityModel<GestionEnvio>> {

    @Override
    public EntityModel<GestionEnvio> toModel(GestionEnvio gestionEnvio) {
        int id = gestionEnvio.getId();

        return EntityModel.of(gestionEnvio,
                linkTo(methodOn(GestionEnvioController.class).getById(id)).withSelfRel(),
                linkTo(methodOn(GestionEnvioController.class).getAll()).withRel("Gestiones envio")
        );
    }
}