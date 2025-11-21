package com.example.Sportwear.assemblers;


import com.example.Sportwear.Controller.ProductController;
import com.example.Sportwear.Model.Product;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductAssemblers implements RepresentationModelAssembler<Product, EntityModel<Product>> {
    @Override
    public EntityModel<Product> toModel(Product product) {

       return EntityModel.of(product,
               linkTo(methodOn(ProductController.class).findById(product.getId())).withSelfRel(),
               linkTo(methodOn(ProductController.class).findAll()).withRel("productos"));
    }
}
