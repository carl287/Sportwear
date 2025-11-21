package com.example.Sportwear.assemblers;


import com.example.Sportwear.Controller.ShippingController;
import com.example.Sportwear.Model.Shipping;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ShippingAssemblers implements RepresentationModelAssembler<Shipping, EntityModel<Shipping>> {

    @Override
    public EntityModel<Shipping> toModel(Shipping shipping) {

        return EntityModel.of(shipping,
                linkTo(methodOn(ShippingController.class).getShippingById(shipping.getId())).withSelfRel(),
                linkTo(methodOn(ShippingController.class).getAllShippings()).withRel("envios"),
                linkTo(methodOn(ShippingController.class).createShipping(null)).withRel("crear-envio"),
                linkTo(methodOn(ShippingController.class).deleteShipping(shipping.getId())).withRel("eliminar-envio")
        );
    }
}
