package com.freemarket.app.cliente;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/new")
    public CadastroDTO cadastrar(@RequestBody CadastroDTO dto) {
        return clienteService.cadastraCliente(dto);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/get/{id}")
    public ClienteDTO obter(@PathVariable("id") String id) {
        return clienteService.obterClientePorId(UUID.fromString(id));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/update/{id}")
    public ClienteDTO alterar(@PathVariable("id") String id, @RequestBody ClienteDTO dto) {
        return clienteService.atualizaCliente(id, dto);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping(path = "/delete/{id}")
    public void excluir(@PathVariable("id") String id) {
        clienteService.excluirCliente(id);
    }

}
