package com.freemarket.app.usuario;

import java.nio.charset.StandardCharsets;
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
import com.freemarket.app.cliente.ClienteMapper;
import com.freemarket.app.cliente.ClienteRepository;
import com.freemarket.app.validators.Validator;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class UsuarioService {

    private final SecretKey CHAVE = Keys.hmacShaKeyFor("7f-j&CKk=coNzZc0y7_4obMP?#TfcYq%fcD0mDpenW2nc!lfGoZ|d?f&RNbDHUX6".getBytes(StandardCharsets.UTF_8));

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private Validator validator;

    public Response login(UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.getByLoginAndSenha(dto.login, dto.senha);
        if (usuario != null) {
            String jwtToken = Jwts.builder()
                    .setSubject(usuario.getLogin())
                    .setIssuer("localhost:8080")
                    .setIssuedAt(new Date())
                    .setExpiration(Date.from(
                            LocalDateTime.now()
                                    .plusMinutes(15L)
                                    .atZone(ZoneId.systemDefault())
                            .toInstant()))
                    .signWith(CHAVE, SignatureAlgorithm.ES512)
                    .compact();

            return Response.status(Response.Status.OK).entity(jwtToken).build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuário e/ou senha inválidos.").build();
        }
    }

    public Response verificaToken(String token) {
        try {
            Jwts.parser().setSigningKey(CHAVE).parseClaimsJws(token);
            return Response.status(Response.Status.OK).entity(true).build();
        } catch (ExpiredJwtException ex) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Token expirado.").build();
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
