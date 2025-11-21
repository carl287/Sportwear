package com.example.Sportwear.assemblers;


import com.example.Sportwear.Controller.SupplierController;
import com.example.Sportwear.Model.Suppliers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

// Se elimina la importación incorrecta: import java.util.function.Supplier;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SuppliersModelAssembler implements RepresentationModelAssembler<Suppliers, EntityModel<Suppliers>> {

    @Override
    public EntityModel<Suppliers> toModel(Suppliers supplier) {

        return EntityModel.of(supplier,
                linkTo(methodOn(SupplierController.class).getById(supplier.getId())).withSelfRel(),
                linkTo(methodOn(SupplierController.class).getAll()).withRel("suppliers"),
                linkTo(methodOn(SupplierController.class).delete(supplier.getId()))).withRel("delete-supplier")
        );
    }
}
