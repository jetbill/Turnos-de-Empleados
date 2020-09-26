package com.jetbill.app.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "turnos")
public class Turno  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La hora de entrada no puede estar vacia")
    @Column(name = "hentrada",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date hentrada;

    @NotNull(message = "La hora de salida no puede estar vacia")
    @Column(name = "hsalida",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date hsalida;

    @NotEmpty(message = "no puede estar vacio")
    @Column(name = "dia", nullable = false)
    private String dia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    //@JsonBackReference
    //@JsonIgnore
    private Empleado empleado;

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Turno() {
    }

    public Turno(Date hentrada, Date hsalida, String dia, Empleado empleado) {
        this.hentrada = hentrada;
        this.hsalida = hsalida;
        this.dia = dia;
        this.empleado = empleado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getHentrada() {
        return hentrada;
    }

    public void setHentrada(Date hentrada) {
        this.hentrada = hentrada;
    }

    public Date getHsalida() {
        return hsalida;
    }

    public void setHsalida(Date hsalida) {
        this.hsalida = hsalida;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }
}