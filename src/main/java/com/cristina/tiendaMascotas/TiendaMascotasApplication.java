package com.cristina.tiendaMascotas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TiendaMascotasApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaMascotasApplication.class, args);
	}

}

/*
 * para que funcione hacer lo siguiente: 
 * en el xampp parar los servicios, config, my.ini (txt) y ahi cambiar max_allowed_packet=1M y ponerle más (10)
 */