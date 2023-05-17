package com.desenvolver.cursomc.resources;

import com.desenvolver.cursomc.domain.Pedido;
import com.desenvolver.cursomc.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResources {
    @Autowired
    private PedidoService pedidoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Pedido obj = pedidoService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping()
    public ResponseEntity<List<Pedido>> findAll(){
        List<Pedido> list = pedidoService.findAll();
        return ResponseEntity.ok().body(list);
    }


}
