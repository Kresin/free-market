package com.freemarket.app.usuario;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemarket.app.cliente.Cliente;
import com.freemarket.app.cliente.ClienteDTO;
import com.freemarket.app.cliente.ClienteMapper;
import com.freemarket.app.cliente.ClienteRepository;
import com.freemarket.app.exceptions.LoginException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private ClienteMapper clienteMapper;

    public ClienteDTO login(UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.getByLoginAndSenha(dto.login, dto.senha);
        if (usuario != null) {
            return clienteMapper.dtoFromCliente(usuario.getCliente());
        } else {
            throw new LoginException("Login ou senha inválidos.");
        }
    }

    public UsuarioDTO atualizaUsuario(String id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.getById(UUID.fromString(id));
        usuarioMapper.mergeUsuarioFromDTO(usuario, dto);
        usuario = usuarioRepository.save(usuario);

        return usuarioMapper.dtoFromUsuario(usuario);
    }

    @Transactional
    public void excluirUsuario(String id) {
        Usuario usuario = usuarioRepository.getById(UUID.fromString(id));
        Cliente cliente = usuario.getCliente();
        usuarioRepository.deleteById(usuario.getId());
        clienteRepository.deleteById(cliente.getId());
    }

}
