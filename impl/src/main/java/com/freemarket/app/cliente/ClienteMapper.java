package com.freemarket.app.cliente;

import org.springframework.stereotype.Component;

import com.freemarket.app.usuario.Usuario;

@Component
public class ClienteMapper {

    public Cliente clienteFromCadastroDTO(CadastroDTO dto) {
        if (dto == null) {
            return null;
        }

        Cliente cliente = new Cliente();
        mergeClienteFromDTO(cliente, dto);
        return cliente;
    }

    private void mergeClienteFromDTO(Cliente entity, CadastroDTO dto) {
        if (entity == null || dto == null) {
            return;
        }

        entity.setNome(dto.nome);
        entity.setSobrenome(dto.sobrenome);
        entity.setEmail(dto.email);
        entity.setDataAniversario(dto.dataAniversario);
        entity.setSexo(dto.sexo);
    }

    public CadastroDTO dtoFromClienteAndUsuario(Cliente cliente, Usuario usuario) {
        if (cliente == null || usuario == null) {
            return null;
        }

        CadastroDTO dto = new CadastroDTO();
        mergeDtoFromClienteAndUsuario(dto, cliente, usuario);
        return dto;
    }

    private void mergeDtoFromClienteAndUsuario(CadastroDTO dto, Cliente cliente, Usuario usuario) {
        if (dto == null || cliente == null || usuario == null) {
            return;
        }

        dto.id = cliente.getId();
        dto.nome = cliente.getNome();
        dto.sobrenome = cliente.getSobrenome();
        dto.dataAniversario = cliente.getDataAniversario();
        dto.sexo = cliente.getSexo();
        dto.email = cliente.getEmail();
        dto.login = usuario.getLogin();
        dto.senha = usuario.getSenha();
    }

    public Cliente clienteFromDTO(ClienteDTO dto) {
        if (dto == null) {
            return null;
        }

        Cliente cliente = new Cliente();
        mergeClienteFromDTO(cliente, dto);
        return cliente;
    }

    public void mergeClienteFromDTO(Cliente cliente, ClienteDTO dto) {
        if (cliente == null || dto == null) {
            return;
        }

        cliente.setEmail(dto.email);
        cliente.setSexo(dto.sexo);
        cliente.setNome(dto.nome);
        cliente.setSobrenome(dto.sobrenome);
        cliente.setDataAniversario(dto.dataAniversario);
    }

    public ClienteDTO dtoFromCliente(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        ClienteDTO dto = new ClienteDTO();
        mergeDTOFromCliente(dto, cliente);
        return dto;
    }

    private void mergeDTOFromCliente(ClienteDTO dto, Cliente cliente) {
        if (dto == null || cliente == null) {
            return;
        }

        dto.id = cliente.getId();
        dto.nome = cliente.getNome();
        dto.sobrenome = cliente.getSobrenome();
        dto.email = cliente.getEmail();
        dto.sexo = cliente.getSexo();
        dto.dataAniversario = cliente.getDataAniversario();
    }

}
