package com.unilex.app.Unilex.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "procesos_judiciales")
public class ProcesoJudicial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El número de radicado no puede estar vacío")
    @Size(min = 23, max = 23, message = "El radicado debe contener exactamente 23 dígitos")
    @Pattern(regexp = "[0-9]+", message = "El radicado solo debe contener números")
    private String numeroRadicado;

    @NotBlank(message = "Debe seleccionar un despacho judicial")
    private String despacho;

    @NotBlank(message = "Debe seleccionar el tipo de proceso")
    private String tipoProceso;

    // Relación ManyToOne con Abogado
    @NotNull(message = "Debe asignar un abogado responsable")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "abogado_id")
    private Abogado abogado;

    public ProcesoJudicial() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroRadicado() { return numeroRadicado; }
    public void setNumeroRadicado(String numeroRadicado) { this.numeroRadicado = numeroRadicado; }

    public String getDespacho() { return despacho; }
    public void setDespacho(String despacho) { this.despacho = despacho; }

    public String getTipoProceso() { return tipoProceso; }
    public void setTipoProceso(String tipoProceso) { this.tipoProceso = tipoProceso; }

    public Abogado getAbogado() { return abogado; }
    public void setAbogado(Abogado abogado) { this.abogado = abogado; }
}