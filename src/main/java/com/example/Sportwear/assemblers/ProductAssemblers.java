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

        int placeholderAmount = 0;
        String placeholderTalla = "{talla}";

        return EntityModel.of(product,
                linkTo(methodOn(ProductController.class).findById(product.getId())).withSelfRel(),
                linkTo(methodOn(ProductController.class).findAll()).withRel("productos"),
                linkTo(methodOn(ProductController.class)
                        .decreaseStock(product.getId(), placeholderTalla, placeholderAmount))
                        .withRel("disminuir-stock-por-talla")
                        .withTitle("Añadir /disminuir-stock-por-talla?talla={talla}&amount={cantidad}"),
                linkTo(methodOn(ProductController.class)
                        .increaseStock(product.getId(), placeholderTalla, placeholderAmount))
                        .withRel("aumentar-stock-por-talla")
                        .withTitle("Añadir /aumentar-stock-por-talla?talla={talla}&amount={cantidad}")
        );
    }
}
