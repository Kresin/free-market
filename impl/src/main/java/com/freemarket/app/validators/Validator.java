package com.freemarket.app.validators;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.freemarket.app.cliente.CadastroDTO;
import com.freemarket.app.cliente.ClienteDTO;
import com.freemarket.app.exceptions.CadastroException;
import com.freemarket.app.produto.ProdutoDTO;
import com.freemarket.app.usuario.UsuarioDTO;

@Service
public class Validator {

    public void validateCliente(CadastroDTO dto, boolean isUpdate) {
        if (isUpdate) {
            if (dto.id == null) {
                throw new CadastroException("ID inválido");
            }
        }

        if (dto.email == null || dto.email.isEmpty() || isValidEmail(dto.email)) {
            throw new CadastroException("email inválido");
        }

        if (dto.nome == null || dto.nome.isEmpty()) {
            throw new CadastroException("nome inválido");
        }

        if (dto.sobrenome == null || dto.sobrenome.isEmpty()) {
            throw new CadastroException("sobrenome inválido");
        }

        if (dto.sexo == null || dto.sexo.isEmpty() || (!dto.sexo.equalsIgnoreCase("F") && !dto.sexo.equalsIgnoreCase("M"))) {
            throw new CadastroException("sexo inválido");
        }

        if (dto.dataAniversario == null) {
            throw new CadastroException("data de nascimento inválida");
        }
    }

    public void validateCliente(ClienteDTO dto, boolean isUpdate) {
        if (isUpdate) {
            if (dto.id == null) {
                throw new CadastroException("ID inválido");
            }
        }

        if (dto.email == null || dto.email.isEmpty() || isValidEmail(dto.email)) {
            throw new CadastroException("email inválido");
        }

        if (dto.nome == null || dto.nome.isEmpty()) {
            throw new CadastroException("nome inválido");
        }

        if (dto.sobrenome == null || dto.sobrenome.isEmpty()) {
            throw new CadastroException("sobrenome inválido");
        }

        if (dto.sexo == null || dto.sexo.isEmpty() || (!dto.sexo.equalsIgnoreCase("F") && !dto.sexo.equalsIgnoreCase("M"))) {
            throw new CadastroException("sexo inválido");
        }

        if (dto.dataAniversario == null) {
            throw new CadastroException("data de nascimento inválida");
        }
    }

    public void validateUsuario(CadastroDTO dto) {
        if (dto.login == null || dto.login.isEmpty()) {
            throw new CadastroException("login inválido");
        }

        if (dto.senha == null || dto.senha.isEmpty()) {
            throw new CadastroException("senha inválida");
        }
    }

    public void validateUsuario(String id, UsuarioDTO dto) {
        if (id == null || id.isEmpty()) {
            throw new CadastroException("id inválido");
        }

        if (dto.login == null || dto.login.isEmpty()) {
            throw new CadastroException("login inválido");
        }

        if (dto.senha == null || dto.senha.isEmpty()) {
            throw new CadastroException("senha inválida");
        }
    }

    public void validateProduto(ProdutoDTO dto, boolean isUpdate) {
        if (isUpdate) {
            if (dto.id == null) {
                throw new CadastroException("ID inválido");
            }
        }

        if (dto.nome == null || dto.nome.isEmpty()) {
            throw new CadastroException("nome inválido");
        }

        if (dto.descricao == null || dto.descricao.isEmpty()) {
            throw new CadastroException("descrição inválida");
        }

        if (dto.valor <= 0) {
            throw new CadastroException("valor inválido");
        }

        if (dto.estado == null || dto.estado.isEmpty() || (!dto.estado.equalsIgnoreCase("N") && !dto.estado.equalsIgnoreCase("S") && !dto.estado.equalsIgnoreCase("U"))) {
            throw new CadastroException("estado inválido");
        }

        if (dto.clienteId == null) {
            throw new CadastroException("cliente inválido");
        }
    }

    private boolean isValidEmail(String email) {
        return Pattern.compile("^(.+)@(\\\\S+)$").matcher(email).matches();
    }

}
