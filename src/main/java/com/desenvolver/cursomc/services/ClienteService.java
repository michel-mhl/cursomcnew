package com.desenvolver.cursomc.services;

import com.desenvolver.cursomc.domain.Categoria;
import com.desenvolver.cursomc.domain.Cliente;
import com.desenvolver.cursomc.dto.CategoriaDTO;
import com.desenvolver.cursomc.dto.ClienteDTO;
import com.desenvolver.cursomc.repositories.ClienteRepository;
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
public class ClienteService {
    @Autowired
    private ClienteRepository repo;

    @Transactional
    public Cliente findById(Integer id) {
        Optional<Cliente> cliente = repo.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

    }

    @Transactional
    public List<Cliente> findAll() {
        List<Cliente> list = repo.findAll();
        return list;
    }


    public Cliente update(Cliente obj) {
        Cliente newObj = findById(obj.getId());
        updateData(newObj, obj);
        return repo.save(newObj);
    }


    @Transactional
    public void delete(Integer id) {
        findById(id);
        repo.deleteById(id);
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, Sort.Direction direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.by(direction, orderBy));
        return repo.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO objDTO) {
        return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);

    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
    }


}
