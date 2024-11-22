package com.mipagina.literAlura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Integer cantidadDeDescargas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Integer getCantidadDeDescargas() {
        return cantidadDeDescargas;
    }

    public void setCantidadDeDescargas(Integer cantidadDeDescargas) {
        this.cantidadDeDescargas = cantidadDeDescargas;
    }

    @Override
    public String toString() {
        return """
                
                Titulo: %s
                Autor: %s
                Idioma: %s
                Cantidad de Descargas: %d
                
                """.formatted(titulo, autor.getNombre(), Idioma.toString(idioma), cantidadDeDescargas);
    }

    public Libro(){}

    public Libro(DatosLibro d, Autor autorExistente){
        this.titulo = d.titulo();
        this.autor = autorExistente;
        this.idioma = Idioma.desdeAPI(d.idiomas().getFirst().trim());
        this.cantidadDeDescargas = d.numeroDescargas();
    }
}
