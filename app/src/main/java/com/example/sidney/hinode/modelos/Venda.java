package com.example.sidney.hinode.modelos;

import java.sql.Date;

/**
 * Created by Sidney on 25/09/2015.
 */
public class Venda {
    private long id;
    private long idCliente;
    private float valor;
    private Date data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
