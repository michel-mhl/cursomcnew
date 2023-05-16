package com.desenvolver.cursomc.services;

import com.desenvolver.cursomc.domain.Cliente;
import com.desenvolver.cursomc.repositories.ClienteRepository;
import com.desenvolver.cursomc.services.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente findById(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

    }
    @Transactional
    public List<Cliente>findAll(){
        List<Cliente> list = clienteRepository.findAll();
        return list;
    }
}
