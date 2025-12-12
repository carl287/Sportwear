package com.example.Sportwear.assemblers;

import com.example.Sportwear.Controller.GestionEnvioController;
import com.example.Sportwear.Model.GestionEnvio;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class GestionEnvioAssembler
        implements RepresentationModelAssembler<GestionEnvio, EntityModel<GestionEnvio>> {

    @Override
    public EntityModel<GestionEnvio> toModel(GestionEnvio gestionEnvio) {

        return EntityModel.of(
                gestionEnvio,
                linkTo(methodOn(GestionEnvioController.class)
                        .getById(gestionEnvio.getId())).withSelfRel(),
                linkTo(methodOn(GestionEnvioController.class)
                        .getAll()).withRel("gestiones-envio")
        );
    }
}
