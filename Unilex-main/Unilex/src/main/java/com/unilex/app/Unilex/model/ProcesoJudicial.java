package com.unilex.app.Unilex.model;

import jakarta.persistence.*;

@Entity
@Table(name = "proceso_judicial")
public class ProcesoJudicial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroRadicado;
    private String despacho;
    private String tipoProceso;

    private boolean activo = true; // Nuevo campo para filtros reactivos

    // Constructor vacío requerido por JPA
    public ProcesoJudicial() {}

    // Constructor con parámetros para las pruebas reactivas
    public ProcesoJudicial(Long id, String numeroRadicado, String despacho, String tipoProceso, boolean activo) {
        this.id = id;
        this.numeroRadicado = numeroRadicado;
        this.despacho = despacho;
        this.tipoProceso = tipoProceso;
        this.activo = activo;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroRadicado() { return numeroRadicado; }
    public void setNumeroRadicado(String numeroRadicado) { this.numeroRadicado = numeroRadicado; }

    public String getDespacho() { return despacho; }
    public void setDespacho(String despacho) { this.despacho = despacho; }

    public String getTipoProceso() { return tipoProceso; }
    public void setTipoProceso(String tipoProceso) { this.tipoProceso = tipoProceso; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}