package com.mipagina.literAlura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    private Integer fechaNacimiento;
    private Integer fechaMuerte;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaMuerte() {
        return fechaMuerte;
    }

    public void setFechaMuerte(Integer fechaMuerte) {
        this.fechaMuerte = fechaMuerte;
    }

    @Override
    public String toString() {
        return """
            
            Nombre: %s
            Fecha de Nacimiento: %d
            Fecha de Muerte: %d
            
           
            """.formatted(
                nombre,
                fechaNacimiento,
                fechaMuerte);
    }

    public Autor(){

    }

    public Autor(DatosAutor d){
        this.nombre = d.nombre();
        this.fechaNacimiento = d.fechaNacimiento();
        this.fechaMuerte = d.fechaMuerte();
    }
}
