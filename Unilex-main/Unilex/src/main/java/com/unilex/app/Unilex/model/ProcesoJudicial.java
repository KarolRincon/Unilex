package com.unilex.app.Unilex.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "procesos_judiciales")
public class ProcesoJudicial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroRadicado;
    private String despacho;
    private String tipoProceso;

    // Constructor vacío exigido por JPA
    public ProcesoJudicial() {
    }

    // Constructor con parámetros
    public ProcesoJudicial(Long id, String numeroRadicado, String despacho, String tipoProceso) {
        this.id = id;
        this.numeroRadicado = numeroRadicado;
        this.despacho = despacho;
        this.tipoProceso = tipoProceso;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroRadicado() {
        return numeroRadicado;
    }
    public void setNumeroRadicado(String numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    public String getDespacho() {
        return despacho;
    }
    public void setDespacho(String despacho) {
        this.despacho = despacho;
    }

    public String getTipoProceso() {
        return tipoProceso;
    }
    public void setTipoProceso(String tipoProceso) {
        this.tipoProceso = tipoProceso;
    }
}