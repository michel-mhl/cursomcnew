package com.desenvolver.cursomc.services;

import com.desenvolver.cursomc.domain.Pedido;
import com.desenvolver.cursomc.repositories.PedidoRepository;
import com.desenvolver.cursomc.services.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional
    public Pedido findById(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));

    }
    @Transactional
    public List<Pedido>findAll(){
        List<Pedido> list = pedidoRepository.findAll();
        return list;
    }
}
