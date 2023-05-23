package com.desenvolver.cursomc.resources;


import com.desenvolver.cursomc.domain.Categoria;
import com.desenvolver.cursomc.domain.Cliente;


import com.desenvolver.cursomc.dto.CategoriaDTO;
import com.desenvolver.cursomc.dto.ClienteDTO;
import com.desenvolver.cursomc.services.ClienteService;
import com.desenvolver.cursomc.services.exceptions.DataIntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/cliente")
public class ClienteResources {
    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
        Cliente obj = clienteService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
/*    @GetMapping()
    public ResponseEntity<List<Cliente>> findAll(){
        List<Cliente> list = clienteService.findAll();
        return ResponseEntity.ok().body(list);
    }*/

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Validated @RequestBody ClienteDTO objDto, @PathVariable Integer id) {
        Cliente obj = clienteService.fromDTO(objDto);
        obj.setId(id);
        obj = clienteService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            clienteService.delete(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException(" Nao é possivel excluir porque a entidades relacionadas  ");
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> list = clienteService.findAll();
        List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") Sort.Direction direction) {
        Page<Cliente> list = clienteService.findPage(page, linesPerPage, orderBy, direction);
        Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }


}
