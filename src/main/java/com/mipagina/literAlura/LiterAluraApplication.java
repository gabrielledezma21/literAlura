package com.mipagina.literAlura;

import com.mipagina.literAlura.main.Main;
import com.mipagina.literAlura.service.AutorService;
import com.mipagina.literAlura.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private LibroService libServ;

	@Autowired
	private AutorService auServ;

	@Autowired
	private ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main principal = new Main(libServ,auServ, context);
		principal.mostrarMenu();
	}

}
