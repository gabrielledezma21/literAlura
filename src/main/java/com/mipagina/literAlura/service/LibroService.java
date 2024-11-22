package com.mipagina.literAlura.service;

import com.mipagina.literAlura.model.*;
import com.mipagina.literAlura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
public class LibroService {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();

    @Autowired
    private LibroRepository libRepo;

    @Autowired
    private AutorService auServ;

    @Transactional
    public void agregarLibro(){
        //carga el libro en la base de datos luego de buscarlo en la API
        System.out.println("Ingrese el nombre del libro deseado: ");
        String tituloLibro = teclado.next();
        teclado.nextLine();
        String libro = buscarLibroEnAPI(tituloLibro);
        System.out.println(libro);
    }

    //busca el libro en la api y si está lo guarda en la base de datos
    private String buscarLibroEnAPI(String tituloLibro){
        var json = consumoApi.obtenerDatos(URL_BASE+"?search="+tituloLibro.replace(" ","+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibro> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if(libroBuscado.isPresent()){
            // Verifica si el autor ya existe en la base de datos
            Autor autorExistente = auServ.buscarAutorPorNombre(libroBuscado.get().autores().getFirst());

            // Si el autor no existe, se crea uno nuevo
            if (autorExistente == null) {
                autorExistente = new Autor(libroBuscado.get().autores().getFirst());
                auServ.guardarEnBaseDeDatos(autorExistente);
            }

            // Crea el libro y asigna el autor
            Libro l = new Libro(libroBuscado.get(), autorExistente);
            guardarEnBaseDeDatos(l);
            return l.toString();
        } else {
            return "Libro no encontrado";
        }
    }

    private void guardarEnBaseDeDatos(Libro l){
        // Verifica si el libro ya existe en la base de datos
        Optional<Libro> libroExistente = libRepo.findByTitulo(l.getTitulo());
        if (libroExistente.isEmpty()) {
            libRepo.save(l);
        }
    }

    public void buscarLibroPorTitulo(){
        System.out.println("Ingrese el título del libro deseado: ");
        var tituloLibro = teclado.next();
        teclado.nextLine();
        Optional<Libro> libro = libRepo.findByTituloContainsIgnoreCase(tituloLibro);
        if(libro.isPresent()){
            Libro l = libro.get();
            System.out.println(l.toString());
        } else {
            System.out.println("Libro no encontrado");
        }
    }

    public void listarLibros(){
        List<Libro> libros = libRepo.findAll();
        if(!libros.isEmpty()){
            libros.stream()
                    .forEach(libro -> System.out.println(libro.toString()));
        } else {
            System.out.println("No hay libros registrados.");
        }

    }

    public void listarLibrosEnIdioma(){
        Idioma idiomaLibro = null;
        do {
            System.out.println("Idiomas disponibles: ");
            for (Idioma i : Idioma.values()) {
                System.out.println(" - " + i.toString() + " - ");
            }
            System.out.println("Ingrese el idioma del libro deseado: ");
            var idioma = teclado.next();
            teclado.nextLine();
            idiomaLibro = Idioma.desdeConsola(idioma);
        } while(idiomaLibro == null);
        List<Libro> libros = libRepo.findByIdioma(idiomaLibro);
        if(!libros.isEmpty()){
            libros.stream()
                    .forEach(libro -> System.out.println(libro.toString()));
        } else {
            System.out.println("No hay libros en ese idioma registrados.");
        }
    }

}
