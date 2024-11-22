package com.mipagina.literAlura.service;

import com.mipagina.literAlura.model.Autor;
import com.mipagina.literAlura.model.DatosAutor;
import com.mipagina.literAlura.model.Libro;
import com.mipagina.literAlura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Scanner;

@Service
@Transactional
public class AutorService {

    private Scanner teclado = new Scanner(System.in);

    @Autowired
    private AutorRepository auRepo;

    public void guardarEnBaseDeDatos(Autor a) {
        // Verifica si el autor ya existe en la base de datos
        Autor autorExistente = auRepo.findByNombre(a.getNombre());
        if (autorExistente == null) {
            auRepo.save(a);
        }
    }

    public boolean autorEnBaseDeDatos(Autor a){
        List<Autor> autores = auRepo.findAll();
        return (autores.contains(a));
    }

    public void listarAutores() {
        List<Autor> autores = auRepo.findAll();
        if(!autores.isEmpty()){
            autores.stream()
                    .forEach(autor -> System.out.println(autor.toString()));
        } else {
            System.out.println("No hay autores registrados.");
        }
    }

    public void listarAutoresVivosEnAnio() {
        System.out.println("Ingrese el año del cual desea saber los autores vivos: ");
        Integer anio = teclado.nextInt();
        teclado.nextLine();
        List<Autor> autores = auRepo.listarAutoresVivosEnAnio(anio);
        if(!autores.isEmpty()){
            autores.stream()
                    .forEach(autor -> System.out.println(autor.toString()));
        } else {
            System.out.println("No hay autores registrados que hayan estado vivos en ese año.");
        }
    }

    public Autor buscarAutorPorNombre(DatosAutor d) {
        return auRepo.findByNombre(d.nombre());
    }

    public void listarAutorPorNombre() {
        System.out.println("Ingrese el nombre del autor: ");
        String nombre = teclado.nextLine();
        var autor = auRepo.findByNombreContainsIgnoreCase(nombre);
        if(autor.isPresent()){
            Autor a = autor.get();
            System.out.println(a.toString());
        } else {
            System.out.println("No hay autores registrados con ese nombre.");
        }
    }

    public void buscarAutorEnEpoca() {
        System.out.println("Ingrese el primer año de la época de la cual desea saber los autores vivos: ");
        Integer num1 = teclado.nextInt();
        teclado.nextLine();
        System.out.println("Ingrese el último año de la época de la cual desea saber los autores vivos: ");
        Integer num2 = teclado.nextInt();
        teclado.nextLine();
        List<Autor> autores = auRepo.buscarAutorEnEpoca(num1,num2);
        if(!autores.isEmpty()){
            autores.stream()
                    .forEach(autor -> System.out.println(autor.toString()));
        } else {
            System.out.println("No hay autores registrados que hayan estado vivos en esa época.");
        }
    }
}
