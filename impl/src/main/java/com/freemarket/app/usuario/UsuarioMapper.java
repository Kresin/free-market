package com.freemarket.app.usuario;

import org.springframework.stereotype.Component;

import com.freemarket.app.cliente.Cliente;
import com.freemarket.app.cliente.CadastroDTO;

@Component
public class UsuarioMapper {

    public Usuario usuarioFromClienteAndCadastroDTO(CadastroDTO dto, Cliente cliente) {
        if (dto == null || cliente == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        mergeUsuarioFromClienteDTOAndCliente(usuario, dto, cliente);
        return usuario;
    }

    void mergeUsuarioFromClienteDTOAndCliente(Usuario usuario, CadastroDTO dto, Cliente cliente) {
        if (usuario == null || dto == null) {
            return;
        }

        usuario.setLogin(dto.login);
        usuario.setSenha(dto.senha);
        usuario.setCliente(cliente);
    }

}
