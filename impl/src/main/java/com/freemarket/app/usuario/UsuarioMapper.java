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

    public UsuarioDTO dtoFromUsuario(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioDTO dto = new UsuarioDTO();
        mergeDTOFromUsuario(dto, usuario);
        return dto;
    }

    private void mergeDTOFromUsuario(UsuarioDTO dto, Usuario usuario) {
        if (dto == null || usuario == null) {
            return;
        }

        dto.login = usuario.getLogin();
        dto.senha = usuario.getSenha();
    }

    public void mergeUsuarioFromDTO(Usuario usuario, UsuarioDTO dto) {
        if (usuario == null || dto == null) {
            return;
        }

        usuario.setLogin(dto.login);
        usuario.setSenha(dto.senha);
    }

}
