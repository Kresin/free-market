package com.freemarket.app.produto;

import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public Produto produtoFromDTO(ProdutoDTO dto) {
        if (dto == null) {
            return null;
        }

        Produto produto = new Produto();
        mergeProdutoFromDTO(produto, dto);
        return produto;
    }

    public void mergeProdutoFromDTO(Produto produto, ProdutoDTO dto) {
        if (produto == null || dto == null) {
            return;
        }

        produto.setNome(dto.nome);
        produto.setDescricao(dto.descricao);
        produto.setEstado(dto.estado);
        produto.setValor(dto.valor);
        produto.setCategoriaId(dto.categoriaId);
        produto.setClienteId(dto.clienteId);
    }

    public ProdutoDTO dtoFromProduto(Produto produto) {
        if (produto == null) {
            return null;
        }

        ProdutoDTO dto = new ProdutoDTO();
        mergeDTOFromProduto(dto, produto);
        return dto;
    }

    private void mergeDTOFromProduto(ProdutoDTO dto, Produto produto) {
        if (dto == null || produto == null) {
            return;
        }

        dto.id = produto.getId();
        dto.nome = produto.getNome();
        dto.categoriaId = produto.getCategoriaId();
        dto.clienteId = produto.getClienteId();
        dto.descricao = produto.getDescricao();
        dto.estado = produto.getEstado();
        dto.valor = produto.getValor();
    }

}
