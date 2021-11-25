package com.freemarket.app.categoria;

import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    public CategoriaDTO dtoFromCategoria(Categoria categoria) {
        if (categoria == null) {
            return null;
        }

        CategoriaDTO dto = new CategoriaDTO();
        mergeDTOFromCategoria(dto, categoria);
        return dto;
    }

    private void mergeDTOFromCategoria(CategoriaDTO dto, Categoria categoria) {
        if (dto == null || categoria == null) {
            return;
        }

        dto.id = categoria.getId();
        dto.descricao = categoria.getDescricao();
        dto.nome = categoria.getNome();
    }

}
