package com.desenvolver.cursomc.resources;

import com.desenvolver.cursomc.domain.Categoria;
import com.desenvolver.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResources {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Categoria obj = categoriaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping()
    public ResponseEntity<List<Categoria>> findAll(){
        List<Categoria> list = categoriaService.findAll();
        return ResponseEntity.ok().body(list);
    }


}
