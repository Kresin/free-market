package com.freemarket.app.produto;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freemarket.app.cliente.Cliente;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    List<Produto> getByCliente(Cliente cliente);

    void removeByCliente(Cliente cliente);

}
