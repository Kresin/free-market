package com.freemarket.app.produto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemarket.app.categoria.Categoria;
import com.freemarket.app.categoria.CategoriaRepository;
import com.freemarket.app.cliente.Cliente;
import com.freemarket.app.cliente.ClienteRepository;
import com.freemarket.app.exceptions.CadastroException;
import com.freemarket.app.validators.Validator;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoMapper produtoMapper;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private Validator validator;

    public ProdutoDTO anunciaProduto(ProdutoDTO dto) {
        validate(dto, false);
        Produto produto = produtoMapper.produtoFromDTO(dto);
        Produto produtoSalvo = produtoRepository.save(produto);

        return produtoMapper.dtoFromProduto(produtoSalvo);
    }

    private void validate(ProdutoDTO dto, boolean isUpdate) {
        validator.validateProduto(dto, isUpdate);

        Optional<Cliente> cliente = clienteRepository.findById(dto.clienteId);
        if (!cliente.isPresent()) {
            throw new CadastroException("id de cliente não encontrado");
        }

        Optional<Categoria> categoria = categoriaRepository.findById(dto.categoriaId);
        if (!categoria.isPresent()) {
            throw new CadastroException("id da categoria não encontrado");
        }
    }

    public ProdutoDTO obterProdutoPorId(UUID id) {
        ProdutoDTO produtoDTO = produtoMapper.dtoFromProduto(produtoRepository.getById(id));
        produtoDTO.nomeCategoria = categoriaRepository.getById(produtoDTO.categoriaId).getNome();
        return produtoDTO;
    }

    public List<ProdutoDTO> obterTodosOsProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoDTO> dtos = produtos.stream().map(produto -> produtoMapper.dtoFromProduto(produto)).collect(Collectors.toList());
        dtos.forEach(this::setNomeCategoria);
        return dtos;
    }

    private void setNomeCategoria(ProdutoDTO dto) {
        dto.nomeCategoria = categoriaRepository.getById(dto.categoriaId).getNome();
    }

    public List<ProdutoDTO> obterMeusProdutos(String id) {
        Cliente cliente = clienteRepository.getById(UUID.fromString(id));
        List<Produto> produtos = produtoRepository.getByCliente(cliente);
        List<ProdutoDTO> dtos = produtos.stream().map(produto -> produtoMapper.dtoFromProduto(produto)).collect(Collectors.toList());
        dtos.forEach(this::setNomeCategoria);
        return dtos;
    }

    public ProdutoDTO atualizaProduto(String id, ProdutoDTO dto) {
        validate(dto, true);
        Produto produto = produtoRepository.getById(UUID.fromString(id));
        produtoMapper.mergeProdutoFromDTO(produto, dto);
        produto = produtoRepository.save(produto);

        return produtoMapper.dtoFromProduto(produto);
    }

    @Transactional
    public void excluirProduto(String id) {
        Produto produto = produtoRepository.getById(UUID.fromString(id));
        produtoRepository.deleteById(produto.getId());
    }

}
