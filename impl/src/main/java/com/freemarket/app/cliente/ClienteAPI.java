package com.freemarket.app.cliente;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cliente")
public class ClienteAPI {

    @Autowired
    private ClienteService clienteService;

    @PostMapping(path = "/new")
    public CadastroDTO cadastrar(@RequestBody CadastroDTO dto) {
        return clienteService.cadastraCliente(dto);
    }

    @GetMapping(path = "/get/{id}")
    public ClienteDTO obter(@PathVariable("id") String id) {
        return clienteService.getById(UUID.fromString(id));
    }

    @PostMapping(path = "/update/{id}")
    public ClienteDTO alterar(@PathVariable("id") String id, @RequestBody ClienteDTO dto) {
        return clienteService.atualizaCliente(id, dto);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void excluir(@PathVariable("id") String id) {
        clienteService.excluirCliente(id);
    }

}
