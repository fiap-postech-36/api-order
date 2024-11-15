package br.com.order.application.controllers;

import br.com.order.application.facade.ProductFacade;
import br.com.order.application.inout.input.FilterInput;
import br.com.order.application.inout.input.ProductInput;
import br.com.order.application.inout.output.ProductOutput;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/product")
@AllArgsConstructor
public class ProductController {

    private final ProductFacade productFacade;

    @PostMapping
    public ResponseEntity<ProductOutput> saveProduct(@RequestBody @Valid final ProductInput productInput){
        return ResponseEntity.ok(productFacade.create(productInput));
    }

    @DeleteMapping("/{idProduct}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long idProduct) {
        productFacade.delete(idProduct);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProductOutput> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductInput productInput){
        return ResponseEntity.ok(productFacade.update(
            new ProductInput(
            id,
            productInput.name(),
            productInput.description(),
            productInput.urlImage(),
            productInput.price(),
            productInput.category())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOutput> findByID(@PathVariable(name = "id",required = false) Long id) throws URISyntaxException {
        return ResponseEntity.ok(productFacade.get(id));
    }

    @GetMapping
    public Page<ProductOutput> findAll(@RequestParam(required = false) final Map<String, String> filter){
        return productFacade.filter(new FilterInput(filter));
    }

}