package com.cristina.tiendaMascotas.servicioSetUp;

import java.io.IOException;
//import java.net.MalformedURLException;
import java.net.URL;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cristina.tiendaMascotas.model.Categoria;
import com.cristina.tiendaMascotas.model.Pedido;
import com.cristina.tiendaMascotas.model.Producto;
import com.cristina.tiendaMascotas.model.ProductoPedido;
import com.cristina.tiendaMascotas.model.Usuario;
import com.cristina.tiendaMascotas.model.estadosPedido.EstadosPedido;
import com.cristina.tiendaMascotas.servicios.ServicioCarrito;


@Service
@Transactional
public class SetUpJPAimpl implements SetUp{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ServicioCarrito servicioCarrito;
	
	@Override
	public void setUp() {
		//vamos a usar una entidad para ver si ya hemos inicializado la base de datos
		//dicha entidad la llamamos SetUp
		com.cristina.tiendaMascotas.model.SetUp registroSetUp = null;
		
		try {
			//es casi idéntico a select s.* from tabla_setup as s
			registroSetUp = (com.cristina.tiendaMascotas.model.SetUp)entityManager.createQuery("select s from SetUp as s").getSingleResult();
		} catch (Exception e) {
			System.out.println("No se encontro ningun registro de setup, comenzamos setup...");
		}

		if ( registroSetUp == null || ! registroSetUp.isCompleto()) {
			//creamos categorías:
			Categoria alimentacion = new Categoria("alimentacion", "categoria alimentos para mascotas");
			Categoria higiene = new Categoria("higiene", "categoria higiene de mascotas");
			Categoria cuidados = new Categoria("cuidados", "categoria cuidados de mascotas");
			Categoria juguetes = new Categoria("juguetes", "categoria juguetes para mascotas");
			Categoria accesorios = new Categoria("accesorios", "categoria accesorios para mascotas");
			Categoria ropa = new Categoria("ropa", "categoria ropa para mascotas");
			Categoria entrenamiento = new Categoria("entrenamiento", "categoria productos de entrenamiento para mascotas");
			Categoria salud = new Categoria("salud", "categoria productos de salud y bienestar para mascotas");
			Categoria transporte = new Categoria("transporte", "categoria productos para transporte de mascotas");
			Categoria habitat = new Categoria("habitat", "categoria productos para hábitats y jaulas para mascotas");

			entityManager.persist(alimentacion);
			entityManager.persist(higiene);
			entityManager.persist(cuidados);
			entityManager.persist(juguetes);
			entityManager.persist(accesorios);
			entityManager.persist(ropa);
			entityManager.persist(entrenamiento);
			entityManager.persist(salud);
			entityManager.persist(transporte);
			entityManager.persist(habitat);
			
			
			//creamos productos:
			Producto p1 = new Producto("Pienso", "Perro", "Criadores", "Grande", "Hipoalergénico", 12.5);
			p1.setCategoria(alimentacion);
			p1.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/1.png"));
			entityManager.persist(p1);

			Producto p2 = new Producto("Arena", "Gato","Nova Clean","Mediano","Perfumado", 4.5);
			p2.setCategoria(higiene);
			p2.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/2.png"));
			entityManager.persist(p2);

			Producto p3 = new Producto("Acuario", "Pez", "Aquatlantis", "Pequeño", "100L", 104.5);
			p3.setCategoria(cuidados);
			p3.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/3.png"));
			entityManager.persist(p3);
			
			Producto p4 = new Producto("Cama", "Perro", "Comfy Pet", "Grande", "Orthopedic", 55.0);
			p4.setCategoria(accesorios);
			p4.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/4.png"));
			entityManager.persist(p4);

			Producto p5 = new Producto("Rascador", "Gato", "Feline Fun", "Mediano", "Con juguetes", 35.0);
			p5.setCategoria(accesorios);
			p5.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/5.png"));
			entityManager.persist(p5);

			Producto p6 = new Producto("Comida", "Pez", "Fish Feast", "Pequeño", "Variado", 8.5);
			p6.setCategoria(alimentacion);
			p6.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/6.png"));
			entityManager.persist(p6);

			Producto p7 = new Producto("Jaula", "Pájaro", "Birdie Home", "Grande", "Con perchas", 75.0);
			p7.setCategoria(cuidados);
			p7.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/7.png"));
			entityManager.persist(p7);

			Producto p8 = new Producto("Sustrato", "Reptil", "Reptile Ground", "Mediano", "Natural", 15.0);
			p8.setCategoria(higiene);
			p8.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/8.png"));
			entityManager.persist(p8);

			Producto p9 = new Producto("Correa", "Perro", "Walky Leash", "Grande", "Reflectante", 10.0);
			p9.setCategoria(accesorios);
			p9.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/9.png"));
			entityManager.persist(p9);

			Producto p10 = new Producto("Comedero", "Gato", "Cat Dish", "Mediano", "Antideslizante", 12.0);
			p10.setCategoria(accesorios);
			p10.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/10.png"));
			entityManager.persist(p10);

			Producto p11 = new Producto("Filtro", "Pez", "Clean Water", "Pequeño", "Biológico", 20.0);
			p11.setCategoria(cuidados);
			p11.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/11.png"));
			entityManager.persist(p11);

			Producto p12 = new Producto("Jaula", "Pájaro", "Tweet Home", "Mediano", "Con juguetes", 60.0);
			p12.setCategoria(accesorios);
			p12.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/12.png"));
			entityManager.persist(p12);

			Producto p13 = new Producto("Lámpara", "Reptil", "Heat Light", "Pequeño", "Infrarrojo", 22.0);
			p13.setCategoria(accesorios);
			p13.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/13.png"));
			entityManager.persist(p13);

			Producto p14 = new Producto("Collar", "Perro", "Stylish Pup", "Mediano", "De cuero", 18.0);
			p14.setCategoria(accesorios);
			p14.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/14.png"));
			entityManager.persist(p14);

			Producto p15 = new Producto("Rascador", "Gato", "Claw Tower", "Grande", "Múltiples niveles", 45.0);
			p15.setCategoria(accesorios);
			p15.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/15.png"));
			entityManager.persist(p15);

			Producto p16 = new Producto("Comida", "Pez", "Goldfish Treats", "Mediano", "Escamas", 9.0);
			p16.setCategoria(alimentacion);
			p16.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/16.png"));
			entityManager.persist(p16);

			Producto p17 = new Producto("Nido", "Pájaro", "Cozy Nest", "Pequeño", "De madera", 12.5);
			p17.setCategoria(accesorios);
			p17.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/17.png"));
			entityManager.persist(p17);

			Producto p18 = new Producto("Sustrato", "Reptil", "Desert Sand", "Grande", "Esterilizado", 17.0);
			p18.setCategoria(higiene);
			p18.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/18.png"));
			entityManager.persist(p18);

			Producto p19 = new Producto("Cepillo", "Perro", "Fur Detangler", "Pequeño", "De cerdas suaves", 8.0);
			p19.setCategoria(cuidados);
			p19.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/19.png"));
			entityManager.persist(p19);

			Producto p20 = new Producto("Comedero", "Gato", "Feeding Frenzy", "Grande", "Automático", 40.0);
			p20.setCategoria(cuidados);
			p20.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/20.png"));
			entityManager.persist(p20);

			Producto p21 = new Producto("Calentador", "Pez", "Aqua Warm", "Mediano", "Sumergible", 25.0);
			p21.setCategoria(cuidados);
			p21.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/21.png"));
			entityManager.persist(p21);

			Producto p22 = new Producto("Bañera", "Pájaro", "Splash Fun", "Pequeño", "Plástico", 13.5);
			p22.setCategoria(accesorios);
			p22.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/22.png"));
			entityManager.persist(p22);

			Producto p23 = new Producto("Luz UVB", "Reptil", "Bright Beam", "Grande", "Para terrarios", 30.0);
			p23.setCategoria(accesorios);
			p23.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/23.png"));
			entityManager.persist(p23);

			Producto p24 = new Producto("Arnés", "Perro", "Secure Fit", "Mediano", "Acolchado", 20.0);
			p24.setCategoria(accesorios);
			p24.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/24.png"));
			entityManager.persist(p24);

			Producto p25 = new Producto("Juguete", "Gato", "Play Tunnel", "Grande", "Con plumas", 25.0);
			p25.setCategoria(accesorios);
			p25.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/25.png"));
			entityManager.persist(p25);

			Producto p26 = new Producto("Comida", "Pez", "Tropical Mix", "Pequeño", "En pellets", 11.0);
			p26.setCategoria(alimentacion);
			p26.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/26.png"));
			entityManager.persist(p26);

			Producto p27 = new Producto("Percha", "Pájaro", "Rest Perch", "Mediano", "De madera natural", 7.0);
			p27.setCategoria(accesorios);
			p27.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/27.png"));
			entityManager.persist(p27);

			Producto p28 = new Producto("Decoración", "Reptil", "Rept", "Pequeño", "Rama de madera", 5.25);
			p28.setCategoria(accesorios);
			p28.setImagenPortada(leerBytesDeRutaOrigen("http://localhost:8080/imagenes_base/28.png"));
			entityManager.persist(p28);
			
			//creamos usuarios
			Usuario u1 = new Usuario("cristina", "cris@gmail.com", "123");
			Usuario u2 = new Usuario("maria", "maria@gmail.com", "456");
			Usuario u3 = new Usuario("peinado", "pei@gmail.com", "789");
			Usuario u4 = new Usuario("gines", "gin@gmail.com", "abc");

			entityManager.persist(u1);
			entityManager.persist(u2);
			entityManager.persist(u3);

			//metemos productos en el carrito de u1 y u2 usuario
			servicioCarrito.agregarProducto(p1.getId(), u1.getId(), 3);
			servicioCarrito.agregarProducto(p2.getId(), u1.getId(), 1);
			servicioCarrito.agregarProducto(p3.getId(), u2.getId(), 2);
			servicioCarrito.agregarProducto(p2.getId(), u2.getId(), 5);

			//metemos pedidos
			Pedido pedido1 = new Pedido();
			pedido1.setNombreCompleto("Cristina Peinado");
			pedido1.setTipoCalle("CALLE");
			pedido1.setDireccion("Hilados");
			pedido1.setNumero("12");
			pedido1.setProvincia("Madrid");
			pedido1.setCp("28850");
			pedido1.setTipoTarjeta("VISA");
			pedido1.setNumTarjeta("123 456 789");
			pedido1.setTitularTarjeta("Cristina Peinado");
			pedido1.setCvvTarjeta("111");
			pedido1.setFechaCaducidadTarjeta("09/27");
			pedido1.setUsuario(u1);
			pedido1.setEstado(EstadosPedido.COMPLETO.name());
			entityManager.persist(pedido1);
			ProductoPedido pp1 = new ProductoPedido();
			pp1.setPedido(pedido1);
			pp1.setProducto(p1);
			pp1.setCantidad(3);
			entityManager.persist(pp1);
			
			Pedido pedido2 = new Pedido();
			pedido2.setNombreCompleto("Teresa Ginés");
			pedido2.setTipoCalle("AVENIDA");
			pedido2.setDireccion("Cervantes");
			pedido2.setNumero("5");
			pedido2.setProvincia("Madrid");
			pedido2.setCp("08850");
			pedido2.setTipoTarjeta("VISA");
			pedido2.setNumTarjeta("789 456 123");
			pedido2.setTitularTarjeta("Teresa Gines");
			pedido2.setCvvTarjeta("222");
			pedido2.setFechaCaducidadTarjeta("10/26");
			pedido2.setUsuario(u2);
			pedido2.setEstado(EstadosPedido.COMPLETO.name());
			entityManager.persist(pedido2);
			ProductoPedido pp2 = new ProductoPedido();
			pp2.setPedido(pedido2);
			pp2.setProducto(p2);
			pp2.setCantidad(2);
			entityManager.persist(pp2);

			Pedido pedido3 = new Pedido();
			pedido3.setNombreCompleto("Laura Martínez");
			pedido3.setTipoCalle("PLAZA");
			pedido3.setDireccion("Mayor");
			pedido3.setNumero("7");
			pedido3.setProvincia("Sevilla");
			pedido3.setCp("41003");
			pedido3.setTipoTarjeta("VISA");
			pedido3.setNumTarjeta("9999 0000 1111 2222");
			pedido3.setTitularTarjeta("Laura Martínez");
			pedido3.setCvvTarjeta("345");
			pedido3.setFechaCaducidadTarjeta("11/26");
			pedido3.setUsuario(u1);
			pedido3.setEstado(EstadosPedido.COMPLETO.name());
			entityManager.persist(pedido3);
			ProductoPedido pp3 = new ProductoPedido();
			pp3.setPedido(pedido3);
			pp3.setProducto(p3);
			pp3.setCantidad(3);
			entityManager.persist(pp3);

			Pedido pedido4 = new Pedido();
			pedido4.setNombreCompleto("Pablo Hernández");
			pedido4.setTipoCalle("RONDA");
			pedido4.setDireccion("de la Estrella");
			pedido4.setNumero("23");
			pedido4.setProvincia("Málaga");
			pedido4.setCp("29004");
			pedido4.setTipoTarjeta("Mastercard");
			pedido4.setNumTarjeta("3333 4444 5555 6666");
			pedido4.setTitularTarjeta("Pablo Hernández");
			pedido4.setCvvTarjeta("456");
			pedido4.setFechaCaducidadTarjeta("02/23");
			pedido4.setUsuario(u2);
			pedido4.setEstado(EstadosPedido.INCOMPLETO.name());
			entityManager.persist(pedido4);
			ProductoPedido pp4 = new ProductoPedido();
			pp4.setPedido(pedido4);
			pp4.setProducto(p4);
			pp4.setCantidad(1);
			entityManager.persist(pp4);

			Pedido pedido5 = new Pedido();
			pedido5.setNombreCompleto("Carmen Rodríguez");
			pedido5.setTipoCalle("TRAVESÍA");
			pedido5.setDireccion("del Molino");
			pedido5.setNumero("34");
			pedido5.setProvincia("Alicante");
			pedido5.setCp("03005");
			pedido5.setTipoTarjeta("VISA");
			pedido5.setNumTarjeta("7777 8888 9999 0000");
			pedido5.setTitularTarjeta("Carmen Rodríguez");
			pedido5.setCvvTarjeta("567");
			pedido5.setFechaCaducidadTarjeta("07/27");
			pedido5.setUsuario(u3);
			pedido5.setEstado(EstadosPedido.FINALIZADO.name());
			entityManager.persist(pedido5);
			ProductoPedido pp5 = new ProductoPedido();
			pp5.setPedido(pedido5);
			pp5.setProducto(p5);
			pp5.setCantidad(2);
			entityManager.persist(pp5);

			Pedido pedido6 = new Pedido();
			pedido6.setNombreCompleto("José García");
			pedido6.setTipoCalle("AVENIDA");
			pedido6.setDireccion("de la Paz");
			pedido6.setNumero("19");
			pedido6.setProvincia("Murcia");
			pedido6.setCp("30006");
			pedido6.setTipoTarjeta("Mastercard");
			pedido6.setNumTarjeta("1111 2222 3333 4444");
			pedido6.setTitularTarjeta("José García");
			pedido6.setCvvTarjeta("678");
			pedido6.setFechaCaducidadTarjeta("04/26");
			pedido6.setUsuario(u1);
			pedido6.setEstado(EstadosPedido.COMPLETO.name());
			entityManager.persist(pedido6);
			ProductoPedido pp6 = new ProductoPedido();
			pp6.setPedido(pedido6);
			pp6.setProducto(p6);
			pp6.setCantidad(1);
			entityManager.persist(pp6);

			Pedido pedido7 = new Pedido();
			pedido7.setNombreCompleto("María Fernández");
			pedido7.setTipoCalle("CALLE");
			pedido7.setDireccion("de las Flores");
			pedido7.setNumero("28");
			pedido7.setProvincia("Zaragoza");
			pedido7.setCp("50007");
			pedido7.setTipoTarjeta("VISA");
			pedido7.setNumTarjeta("5555 6666 7777 8888");
			pedido7.setTitularTarjeta("María Fernández");
			pedido7.setCvvTarjeta("789");
			pedido7.setFechaCaducidadTarjeta("01/25");
			pedido7.setUsuario(u2);
			pedido7.setEstado(EstadosPedido.FINALIZADO.name());
			entityManager.persist(pedido7);
			ProductoPedido pp7 = new ProductoPedido();
			pp7.setPedido(pedido7);
			pp7.setProducto(p7);
			pp7.setCantidad(3);
			entityManager.persist(pp7);

			Pedido pedido8 = new Pedido();
			pedido8.setNombreCompleto("Antonio Pérez");
			pedido8.setTipoCalle("PLAZA");
			pedido8.setDireccion("Nueva");
			pedido8.setNumero("1");
			pedido8.setProvincia("Bilbao");
			pedido8.setCp("48008");
			pedido8.setTipoTarjeta("Mastercard");
			pedido8.setNumTarjeta("9999 0000 1111 2222");
			pedido8.setTitularTarjeta("Antonio Pérez");
			pedido8.setCvvTarjeta("890");
			pedido8.setFechaCaducidadTarjeta("09/24");
			pedido8.setUsuario(u3);
			pedido8.setEstado(EstadosPedido.INCOMPLETO.name());
			entityManager.persist(pedido8);
			ProductoPedido pp8 = new ProductoPedido();
			pp8.setPedido(pedido8);
			pp8.setProducto(p8);
			pp8.setCantidad(2);
			entityManager.persist(pp8);

			Pedido pedido9 = new Pedido();
			pedido9.setNombreCompleto("Isabel Ruiz");
			pedido9.setTipoCalle("RONDA");
			pedido9.setDireccion("de la Luna");
			pedido9.setNumero("10");
			pedido9.setProvincia("Granada");
			pedido9.setCp("18009");
			pedido9.setTipoTarjeta("VISA");
			pedido9.setNumTarjeta("3333 4444 5555 6666");
			pedido9.setTitularTarjeta("Isabel Ruiz");
			pedido9.setCvvTarjeta("901");
			pedido9.setFechaCaducidadTarjeta("06/26");
			pedido9.setUsuario(u1);
			pedido9.setEstado(EstadosPedido.COMPLETO.name());
			entityManager.persist(pedido9);
			ProductoPedido pp9 = new ProductoPedido();
			pp9.setPedido(pedido9);
			pp9.setProducto(p9);
			pp9.setCantidad(1);
			entityManager.persist(pp9);

			Pedido pedido10 = new Pedido();
			pedido10.setNombreCompleto("Francisco Gómez");
			pedido10.setTipoCalle("TRAVESÍA");
			pedido10.setDireccion("del Río");
			pedido10.setNumero("22");
			pedido10.setProvincia("Vigo");
			pedido10.setCp("36210");
			pedido10.setTipoTarjeta("Mastercard");
			pedido10.setNumTarjeta("7777 8888 9999 0000");
			pedido10.setTitularTarjeta("Francisco Gómez");
			pedido10.setCvvTarjeta("012");
			pedido10.setFechaCaducidadTarjeta("03/23");
			pedido10.setUsuario(u2);
			pedido10.setEstado(EstadosPedido.FINALIZADO.name());
			entityManager.persist(pedido10);
			ProductoPedido pp10 = new ProductoPedido();
			pp10.setPedido(pedido10);
			pp10.setProducto(p10);
			pp10.setCantidad(3);
			entityManager.persist(pp10);

			Pedido pedido11 = new Pedido();
			pedido11.setNombreCompleto("Marta Díaz");
			pedido11.setTipoCalle("AVENIDA");
			pedido11.setDireccion("de la Iglesia");
			pedido11.setNumero("17");
			pedido11.setProvincia("Santander");
			pedido11.setCp("39011");
			pedido11.setTipoTarjeta("VISA");
			pedido11.setNumTarjeta("1111 2222 3333 4444");
			pedido11.setTitularTarjeta("Marta Díaz");
			pedido11.setCvvTarjeta("123");
			pedido11.setFechaCaducidadTarjeta("05/25");
			pedido11.setUsuario(u3);
			pedido11.setEstado(EstadosPedido.INCOMPLETO.name());
			entityManager.persist(pedido11);
			ProductoPedido pp11 = new ProductoPedido();
			pp11.setPedido(pedido11);
			pp11.setProducto(p11);
			pp11.setCantidad(2);
			entityManager.persist(pp11);

			Pedido pedido12 = new Pedido();
			pedido12.setNombreCompleto("Juan Ortega");
			pedido12.setTipoCalle("CALLE");
			pedido12.setDireccion("del Mar");
			pedido12.setNumero("33");
			pedido12.setProvincia("Cádiz");
			pedido12.setCp("11012");
			pedido12.setTipoTarjeta("Mastercard");
			pedido12.setNumTarjeta("5555 6666 7777 8888");
			pedido12.setTitularTarjeta("Juan Ortega");
			pedido12.setCvvTarjeta("234");
			pedido12.setFechaCaducidadTarjeta("08/24");
			pedido12.setUsuario(u1);
			pedido12.setEstado(EstadosPedido.FINALIZADO.name());
			entityManager.persist(pedido12);
			ProductoPedido pp12 = new ProductoPedido();
			pp12.setPedido(pedido12);
			pp12.setProducto(p12);
			pp12.setCantidad(1);
			entityManager.persist(pp12);

			Pedido pedido13 = new Pedido();
			pedido13.setNombreCompleto("Elena Navarro");
			pedido13.setTipoCalle("PLAZA");
			pedido13.setDireccion("de la República");
			pedido13.setNumero("21");
			pedido13.setProvincia("Palma");
			pedido13.setCp("07013");
			pedido13.setTipoTarjeta("VISA");
			pedido13.setNumTarjeta("9999 0000 1111 2222");
			pedido13.setTitularTarjeta("Elena Navarro");
			pedido13.setCvvTarjeta("345");
			pedido13.setFechaCaducidadTarjeta("11/26");

			Pedido pedido14 = new Pedido();
			pedido14.setNombreCompleto("Luis Castro");
			pedido14.setTipoCalle("RONDA");
			pedido14.setDireccion("del Carmen");
			pedido14.setNumero("9");
			pedido14.setProvincia("Oviedo");
			pedido14.setCp("33014");
			pedido14.setTipoTarjeta("Mastercard");
			pedido14.setNumTarjeta("3333 4444 5555 6666");
			pedido14.setTitularTarjeta("Luis Castro");
			pedido14.setCvvTarjeta("456");
			pedido14.setFechaCaducidadTarjeta("02/23");

			Pedido pedido15 = new Pedido();
			pedido15.setNombreCompleto("Clara Morales");
			pedido15.setTipoCalle("TRAVESÍA");
			pedido15.setDireccion("de la Sierra");
			pedido15.setNumero("14");
			pedido15.setProvincia("Salamanca");
			pedido15.setCp("37015");
			pedido15.setTipoTarjeta("VISA");
			pedido15.setNumTarjeta("7777 8888 9999 0000");
			pedido15.setTitularTarjeta("Clara Morales");
			pedido15.setCvvTarjeta("567");
			pedido15.setFechaCaducidadTarjeta("07/27");

			
			//setup
			com.cristina.tiendaMascotas.model.SetUp setup = new com.cristina.tiendaMascotas.model.SetUp();
			setup.setCompleto(true);
			entityManager.persist(setup);
		}
	
	}

	private byte[] leerBytesDeRutaOrigen(String rutaOrigen) {
		byte[] info = null;
		try {
			URL url = new URL(rutaOrigen);
			info = IOUtils.toByteArray(url);
			
		} catch (IOException e) { //cambio de malformedURL a IOException
			System.out.println("No se ha podido leer de la ruta indicada.");
			e.printStackTrace();
		}
		return info;
	}
	
}
