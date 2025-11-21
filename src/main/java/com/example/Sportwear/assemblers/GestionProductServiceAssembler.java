package com.example.Sportwear.assemblers;

import com.example.Sportwear.Controller.GestionProductController;
import com.example.Sportwear.Model.GestionProduct;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GestionProductServiceAssembler implements RepresentationModelAssembler<GestionProduct, EntityModel<GestionProduct>> {

    @Override
    public EntityModel<GestionProduct> toModel(GestionProduct gestionProduct) {
        return EntityModel.of(gestionProduct,
                linkTo(methodOn(GestionProductController.class).increaseStock(gestionProduct.getId(), 0)).withRel("aumentar-stock"),
                linkTo(methodOn(GestionProductController.class).decreaseStock(gestionProduct.getId(), 0)).withRel("disminuir-stock")
        );
    }
}
