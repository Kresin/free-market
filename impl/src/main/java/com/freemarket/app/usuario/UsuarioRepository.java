package com.freemarket.app.usuario;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.freemarket.app.cliente.Cliente;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    void removeByCliente(Cliente cliente);

    Usuario getByLoginAndSenha(String login, String senha);

    long countByLogin(String login);

}
