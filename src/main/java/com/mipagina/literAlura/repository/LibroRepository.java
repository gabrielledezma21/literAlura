package com.mipagina.literAlura.repository;


import com.mipagina.literAlura.model.Idioma;
import com.mipagina.literAlura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    public Optional<Libro> findByTituloContainsIgnoreCase(String tituloLibro);

    public List<Libro> findByIdioma(Idioma idioma);

    public Optional<Libro> findByTitulo(String titulo);

    public List<Libro> findTop10ByOrderByCantidadDeDescargasDesc();
}
