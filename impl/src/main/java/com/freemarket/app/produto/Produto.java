package com.freemarket.app.produto;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "ds_produto")
    private String descricao;

    @OneToOne
    @JoinColumn(name = "categoria_produto_id", referencedColumnName = "id")
    private Categoria categoria;

    @Column(name = "categoria_produto_id")
    private UUID categoriaId;

    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    @Column(name = "cliente_id")
    private UUID clienteId;

    @Column(name = "valor")
    private float valor;

    @Column(name = "fl_estado")
    private String estado;

}
