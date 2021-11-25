package com.freemarket.app.produto;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freemarket.app.cliente.Cliente;
import com.freemarket.app.cliente.ClienteRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoMapper produtoMapper;

    @Autowired
    private ClienteRepository clienteRepository;

    public ProdutoDTO anunciaProduto(ProdutoDTO dto) {
        Produto produto = produtoMapper.produtoFromDTO(dto);
        Produto produtoSalvo = produtoRepository.save(produto);

        return produtoMapper.dtoFromProduto(produtoSalvo);
    }

    public ProdutoDTO obterProdutoPorId(UUID id) {
        return produtoMapper.dtoFromProduto(produtoRepository.getById(id));
    }

    public List<ProdutoDTO> obterTodosOsProdutos() {
        List<Produto> produtos = produtoRepository.getAll();
        return produtos.stream().map(produto -> produtoMapper.dtoFromProduto(produto)).collect(Collectors.toList());
    }

    public List<ProdutoDTO> obterMeusProdutos(String id) {
        Cliente cliente = clienteRepository.getById(UUID.fromString(id));
        List<Produto> produtos = produtoRepository.getByCliente(cliente);
        return produtos.stream().map(produto -> produtoMapper.dtoFromProduto(produto)).collect(Collectors.toList());
    }

    public ProdutoDTO atualizaProduto(String id, ProdutoDTO dto) {
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
