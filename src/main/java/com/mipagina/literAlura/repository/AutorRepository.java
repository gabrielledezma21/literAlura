package com.mipagina.literAlura.repository;

import com.mipagina.literAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= :anio AND a.fechaMuerte >= :anio")
    public List<Autor> listarAutoresVivosEnAnio(Integer anio);

    public Autor findByNombre(String nombre);
}
