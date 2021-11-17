package com.freemarket.app.produto;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.freemarket.app.categoria.Categoria;
import com.freemarket.app.cliente.Cliente;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "id")
    private String nome;

    @Column(name = "id")
    private String descricao;

    @OneToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;

    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @Column(name = "id")
    private float valor;

    @Column(name = "id")
    private String estado;

}