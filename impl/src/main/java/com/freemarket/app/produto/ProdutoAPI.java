package com.freemarket.app.produto;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produto")
public class ProdutoAPI {

    @Autowired
    private ProdutoService produtoService;

    @CrossOrigin
    @PostMapping(path = "/new")
    public ProdutoDTO anunciaProduto(@RequestBody ProdutoDTO dto) {
        return produtoService.anunciaProduto(dto);
    }

    @CrossOrigin
    @GetMapping(path = "/getById/{id}")
    public ProdutoDTO obter(@PathVariable("id") String id) {
        return produtoService.obterProdutoPorId(UUID.fromString(id));
    }

    @CrossOrigin
    @GetMapping(path = "/getAll")
    public List<ProdutoDTO> obterProdutos() {
        return produtoService.obterTodosOsProdutos();
    }

    @CrossOrigin
    @GetMapping(path = "/getMyProducts/{id}")
    public List<ProdutoDTO> obterMeusProdutos(@PathVariable String id) {
        return produtoService.obterMeusProdutos(id);
    }

    @CrossOrigin
    @PostMapping(path = "/update/{id}")
    public ProdutoDTO alterar(@PathVariable String id, @RequestBody ProdutoDTO dto) {
        return produtoService.atualizaProduto(id, dto);
    }

    @CrossOrigin
    @DeleteMapping(path = "/delete/{id}")
    public void excluir(@PathVariable String id) {
        produtoService.excluirProduto(id);
    }

}
