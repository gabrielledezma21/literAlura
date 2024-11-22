package com.mipagina.literAlura.main;

import com.mipagina.literAlura.service.AutorService;
import com.mipagina.literAlura.service.LibroService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.util.Scanner;

public class Main {

    private Scanner teclado = new Scanner(System.in);
    private LibroService libServ;
    private AutorService auServ;
    private ApplicationContext context;

    public Main(LibroService libServ, AutorService auServ, ApplicationContext context) {
        this.libServ = libServ;
        this.auServ = auServ;
        this.context = context;
    }


    public void mostrarMenu(){
        int opcion = -1;
        do{
            menu();
            opcion = teclado.nextInt();
            teclado.nextLine();
            seleccionarOpciones(opcion);
        } while (opcion != 0);
        SpringApplication.exit(context, () -> 0);
    }

    private void menu(){
        var menu = """
                    
                    Elija una opción:
                    
                    1 - Agregar libro por titulo
                    2 - Buscar libro por titulo
                    3 - Listar libros registrados
                    4 - Listar autores registrados
                    5 - Listar autores vivos en un determinado año
                    6 - Listar libros por idioma
                    7 - Top 10 libros más descargados
                    8 - Buscar autor por nombre
                    9 - Buscar autores que vivieron en una época
                    0 - Salir
                    
                    """;
        System.out.println(menu);
    }

    private void seleccionarOpciones(Integer opcion){
         switch(opcion) {
            case 0 -> System.out.println("Saliendo...");
            case 1 -> libServ.agregarLibro();
            case 2 -> libServ.buscarLibroPorTitulo();
            case 3 -> libServ.listarLibros();
            case 4 -> auServ.listarAutores();
            case 5 -> auServ.listarAutoresVivosEnAnio();
            case 6 -> libServ.listarLibrosEnIdioma();
            case 7 -> libServ.top10LibrosMasDescargados();
            case 8 -> auServ.listarAutorPorNombre();
            case 9 -> auServ.buscarAutorEnEpoca();
            default -> System.out.println("Opcion inválida");
        };
    }


}
