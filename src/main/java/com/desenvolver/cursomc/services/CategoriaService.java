package com.desenvolver.cursomc.services;

import com.desenvolver.cursomc.domain.Categoria;
import com.desenvolver.cursomc.domain.Cliente;
import com.desenvolver.cursomc.dto.CategoriaDTO;
import com.desenvolver.cursomc.repositories.CategoriaRepository;
import com.desenvolver.cursomc.services.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repo;

    @Transactional
    public Categoria findById(Integer id) {
        Optional<Categoria> categoria = repo.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));

    }

    @Transactional
    public List<Categoria> findAll() {
        return repo.findAll();
    }

    @Transactional
    public Categoria insert(Categoria obj) {
        obj.setId(null);
        return repo.save(obj);
    }

    public Categoria update(Categoria obj) {
        Categoria newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    @Transactional
    public void delete(Integer id) {
        findById(id);
        repo.deleteById(id);
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, Sort.Direction direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.by(direction, orderBy));
        return repo.findAll(pageRequest);
    }
    public Categoria fromDTO(CategoriaDTO objDTO){
        return  new Categoria(objDTO.getId(),objDTO.getNome());
    }

    private void updateData(Categoria newObj, Categoria obj) {
        newObj.setNome(obj.getNome());
    }
}
