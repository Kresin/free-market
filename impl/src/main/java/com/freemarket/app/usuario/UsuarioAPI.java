package com.freemarket.app.usuario;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioAPI {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(path = "/login")
    public Response login(@RequestBody UsuarioDTO dto) {
        return usuarioService.login(dto);
    }

    @PostMapping(path = "/verifyToken")
    public Response verifyToken(@QueryParam("token") String token) {
        return usuarioService.verificaToken(token);
    }

    @PostMapping(path = "/update/{id}")
    public UsuarioDTO alterar(@PathVariable("id") String id, @RequestBody UsuarioDTO dto) {
        return usuarioService.atualizaUsuario(id, dto);
    }

    @DeleteMapping(path = "/delete/{id}")
    public void excluir(@PathVariable("id") String id) {
        usuarioService.excluirUsuario(id);
    }

}
