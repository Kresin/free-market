package com.freemarket.app.cliente;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemarket.app.exceptions.CadastroException;
import com.freemarket.app.produto.ProdutoRepository;
import com.freemarket.app.token.JwtTokenUtil;
import com.freemarket.app.usuario.Usuario;
import com.freemarket.app.usuario.UsuarioMapper;
import com.freemarket.app.usuario.UsuarioRepository;
import com.freemarket.app.validators.Validator;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private Validator validator;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public CadastroDTO cadastraCliente(CadastroDTO cadastroDTO) {
        validate(cadastroDTO);
        Cliente cliente = clienteMapper.clienteFromCadastroDTO(cadastroDTO);
        Cliente clienteSalvo = clienteRepository.save(cliente);

        Usuario usuario = usuarioMapper.usuarioFromClienteAndCadastroDTO(cadastroDTO, clienteSalvo);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        CadastroDTO dto = clienteMapper.dtoFromClienteAndUsuario(clienteSalvo, usuarioSalvo);
        dto.token = jwtTokenUtil.generateToken(clienteSalvo);

        return dto;
    }

    private void validate(CadastroDTO cadastroDTO) {
        validator.validateCliente(cadastroDTO, false);
        validator.validateUsuario(cadastroDTO);

        long clientes = clienteRepository.countByEmail(cadastroDTO.email);
        if (clientes > 0) {
            throw new CadastroException("email já registrado");
        }

        long usuarios = usuarioRepository.countByLogin(cadastroDTO.login);
        if (usuarios > 0) {
            throw new CadastroException("login já registrado");
        }
    }

    public ClienteDTO obterClientePorId(UUID uuid) {
        return clienteMapper.dtoFromCliente(clienteRepository.getById(uuid));
    }

    public ClienteDTO atualizaCliente(String id, ClienteDTO dto) {
        validator.validateCliente(dto, true);
        Cliente cliente = clienteRepository.getById(UUID.fromString(id));
        clienteMapper.mergeClienteFromDTO(cliente, dto);
        cliente = clienteRepository.save(cliente);

        return clienteMapper.dtoFromCliente(clienteRepository.save(cliente));
    }

    @Transactional
    public void excluirCliente(String id) {
        Cliente cliente = clienteRepository.getById(UUID.fromString(id));
        usuarioRepository.removeByCliente(cliente);
        produtoRepository.removeByCliente(cliente);
        clienteRepository.deleteById(cliente.getId());
    }

}
