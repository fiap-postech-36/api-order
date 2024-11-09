package br.com.nomeempresa.restaurante.application.controllers;

import br.com.nomeempresa.restaurante.application.facade.OrderFacade;
import br.com.nomeempresa.restaurante.application.inout.input.FilterInput;
import br.com.nomeempresa.restaurante.application.inout.input.OrderInput;
import br.com.nomeempresa.restaurante.application.inout.output.OrderOutput;
import br.com.nomeempresa.restaurante.domain.core.domain.entities.Order;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderFacade orderFacade;

    @GetMapping
    public ResponseEntity<Page<OrderOutput>> findAll(@RequestParam(required = false) final Map<String, String> filterParams) {
        return ResponseEntity.ok().body(orderFacade.filter(new FilterInput(filterParams)));
    }

    @PostMapping
    public ResponseEntity<OrderOutput> create(@RequestBody @Valid final OrderInput orderInput) {
        return ResponseEntity.ok().body(orderFacade.create(orderInput));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Order> delete(@PathVariable final Long id) {
        orderFacade.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<OrderOutput> getById(@PathVariable final Long id) {
        return ResponseEntity.ok().body(orderFacade.get(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderOutput> edit(@PathVariable final Long id, @RequestBody OrderInput orderInput) {
        return ResponseEntity.ok().body(orderFacade.update(new OrderInput(id, orderInput.products())));
    }

    @PutMapping("/update-status/{id}")
    public ResponseEntity<OrderOutput> updateStatus(@PathVariable final Long id) {
        return ResponseEntity.ok().body(orderFacade.updateStatusOrder(id));
    }

}

