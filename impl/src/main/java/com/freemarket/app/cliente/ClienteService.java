package com.freemarket.app.cliente;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemarket.app.produto.ProdutoRepository;
import com.freemarket.app.usuario.Usuario;
import com.freemarket.app.usuario.UsuarioMapper;
import com.freemarket.app.usuario.UsuarioRepository;

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

    public CadastroDTO cadastraCliente(CadastroDTO cadastroDTO) {
        Cliente cliente = clienteMapper.clienteFromCadastroDTO(cadastroDTO);
        Cliente clienteSalvo = clienteRepository.save(cliente);

        Usuario usuario = usuarioMapper.usuarioFromClienteAndCadastroDTO(cadastroDTO, clienteSalvo);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return clienteMapper.dtoFromClienteAndUsuario(clienteSalvo, usuarioSalvo);
    }

    public ClienteDTO obterClientePorId(UUID uuid) {
        return clienteMapper.dtoFromCliente(clienteRepository.getById(uuid));
    }

    public ClienteDTO atualizaCliente(String id, ClienteDTO dto) {
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
