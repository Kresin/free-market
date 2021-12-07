package com.freemarket.app.usuario;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

import javax.crypto.SecretKey;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemarket.app.cliente.Cliente;
import com.freemarket.app.cliente.ClienteDTO;
import com.freemarket.app.cliente.ClienteMapper;
import com.freemarket.app.cliente.ClienteRepository;
import com.freemarket.app.exceptions.LoginException;
import com.freemarket.app.token.JwtTokenUtil;
import com.freemarket.app.validators.Validator;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

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

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private Validator validator;

    public ClienteDTO login(UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.getByLoginAndSenha(dto.login, dto.senha);
        if (usuario != null) {
            ClienteDTO clienteDTO = clienteMapper.dtoFromCliente(usuario.getCliente());
            clienteDTO.token = jwtTokenUtil.generateToken(usuario.getCliente());
            return clienteDTO;
        } else {
            throw new LoginException("Usuário e/ou senha inválidos");
        }
    }

    public Response verificaToken(String token) {
        Boolean isExpired = jwtTokenUtil.isTokenExpired(token);
        if (isExpired) {
            throw new LoginException("Token expirado");
        } else {
            return Response.status(Response.Status.OK).entity(true).build();
        }
    }

    public UsuarioDTO atualizaUsuario(String id, UsuarioDTO dto) {
        validator.validateUsuario(id, dto);
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
