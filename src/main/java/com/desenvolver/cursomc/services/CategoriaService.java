package com.desenvolver.cursomc.services;

import com.desenvolver.cursomc.domain.Categoria;
import com.desenvolver.cursomc.repositories.CategoriaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Transactional
    public Categoria findById(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElse(null);

    }
    @Transactional
    public List<Categoria>findAll(){
        List<Categoria> list = categoriaRepository.findAll();
        return list;
    }
}
