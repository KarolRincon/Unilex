package com.unilex.app.Unilex.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "abogados")
public class Abogado {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@NotBlank(message = "El nombre del abogado es obligatorio")
private String nombre;

@NotBlank(message = "El correo electrónico es obligatorio")
@Email(message = "Debe ingresar un correo electrónico válido")
private String email;

@NotBlank(message = "La especialidad es obligatoria")
private String especialidad;

@OneToMany(mappedBy = "abogado", cascade = CascadeType.ALL)
private List<ProcesoJudicial> procesos;

    public Abogado() {}

    public Abogado(Long id, String nombre, String email, String especialidad) {
    this.id = id;
    this.nombre = nombre;
    this.email = email;
    this.especialidad = especialidad;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public List<ProcesoJudicial> getProcesos() { return procesos; }
        public void setProcesos(List<ProcesoJudicial> procesos) { this.procesos = procesos; }
            }
