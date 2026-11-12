package com.unilex.app.Unilex.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    // Relación con Abogado que faltaba para solucionar el error de Hibernate
    @ManyToOne
    @JoinColumn(name = "abogado_id")
    private Abogado abogado;

    // Constructor vacío exigido por JPA
    public ProcesoJudicial() {
    }

    // Constructor con parámetros
    public ProcesoJudicial(Long id, String numeroRadicado, String despacho, String tipoProceso, Abogado abogado) {
        this.id = id;
        this.numeroRadicado = numeroRadicado;
        this.despacho = despacho;
        this.tipoProceso = tipoProceso;
        this.abogado = abogado;
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

    public Abogado getAbogado() {
        return abogado;
    }
    public void setAbogado(Abogado abogado) {
        this.abogado = abogado;
    }
}