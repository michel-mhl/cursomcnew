package com.desenvolver.cursomc.resources;

import com.desenvolver.cursomc.domain.Categoria;
import com.desenvolver.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResources {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Integer id) {
        Categoria obj = categoriaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping()
    public ResponseEntity<List<Categoria>> findAll(){
        List<Categoria> list = categoriaService.findAll();
        return ResponseEntity.ok().body(list);
    }
    @PostMapping()
    public ResponseEntity<Void> insert(@RequestBody      Categoria obj){
        obj = categoriaService.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody Categoria obj,@PathVariable Integer id){
        obj.setId(id);
        obj = categoriaService.update(obj);
        return  ResponseEntity.noContent().build();
    }


}
