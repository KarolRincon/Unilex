package com.unilex.app.Unilex.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProcesoJudicialDTO {

    @NotBlank(message = "El número de radicado es obligatorio.")
    @Size(min = 23, max = 23, message = "El radicado debe contener exactamente 23 dígitos numéricos.")
    @Pattern(regexp = "^[0-9]+$", message = "El radicado solo debe contener números.")
    private String radicado;

    @NotBlank(message = "El nombre del demandante es obligatorio.")
    @Size(min = 3, max = 100, message = "El nombre del demandante debe tener entre 3 y 100 caracteres.")
    private String demandante;

    @NotBlank(message = "El nombre del demandado es obligatorio.")
    @Size(min = 3, max = 100, message = "El nombre del demandado debe tener entre 3 y 100 caracteres.")
    private String demandado;

    @NotBlank(message = "Debe especificar el despacho judicial.")
    private String despachoJudicial;

    @NotBlank(message = "Debe seleccionar el estado inicial del proceso.")
    private String estadoActual;

    // Constructores
    public ProcesoJudicialDTO() {}

    // Getters y Setters
    public String getRadicado() { return radicado; }
    public void setRadicado(String radicado) { this.radicado = radicado; }

    public String getDemandante() { return demandante; }
    public void setDemandante(String demandante) { this.demandante = demandante; }

    public String getDemandado() { return demandado; }
    public void setDemandado(String demandado) { this.demandado = demandado; }

    public String getDespachoJudicial() { return despachoJudicial; }
    public void setDespachoJudicial(String despachoJudicial) { this.despachoJudicial = despachoJudicial; }

    public String getEstadoActual() { return estadoActual; }
    public void setEstadoActual(String estadoActual) { this.estadoActual = estadoActual; }
}