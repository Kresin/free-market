package com.freemarket.app.categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaAPI {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping(path = "/getAll")
    public List<CategoriaDTO> obterCategorias() {
        return categoriaService.obterCategorias();
    }

}
