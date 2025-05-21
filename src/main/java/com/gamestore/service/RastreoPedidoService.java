package com.gamestore.service;

import com.gamestore.entity.Rastreo_Pedidos;
import com.gamestore.repository.RestreoPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RastreoPedidoService {
    @Autowired
    private RestreoPedidoRepository rastreoPedidoRepository;

    public List<Rastreo_Pedidos> getAllRastreos() {
        return rastreoPedidoRepository.findAll();
    }

    public Optional<Rastreo_Pedidos> getRastreoById(Integer id) {
        return rastreoPedidoRepository.findById(id);
    }

    public Rastreo_Pedidos saveRastreo(Rastreo_Pedidos rastreo) {
        return rastreoPedidoRepository.save(rastreo);
    }

    public void deleteRastreo(Integer id) {
        rastreoPedidoRepository.deleteById(id);
    }
}
