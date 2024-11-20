package com.cristina.tiendaMascotas.serviciosREST;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cristina.tiendaMascotas.constantesValidaciones.ConstantesValidaciones;
import com.cristina.tiendaMascotas.model.Usuario;
import com.cristina.tiendaMascotas.model.tiposExtra.ResumenPedido;
import com.cristina.tiendaMascotas.servicios.ServicioPedidos;

@RestController
public class ServicioRESTPedidos {

	@Autowired
	private ServicioPedidos servicioPedidos;
	
	@RequestMapping("realizar-pedido-paso1")
	public String realizarPedidoPaso1(String nombreCompleto, String tipoCalle, String direccion, String numero, String provincia, String cp, HttpServletRequest request) {
		//al completar el paso 1, generamos una instancia o registro de la entidad Pedido con el campo estado a INCOMPLETO.
		//Cuando el usuario complete todos los pasos, marcaremos el estado del pedido a COMPLETO
		
		//validar datos:
		Pattern patternNombreCompleto = Pattern.compile(ConstantesValidaciones.REGEXP_NOMBRE_COMPLETO);
		Pattern patternDireccion = Pattern.compile(ConstantesValidaciones.REGEXP_DIRECCION);
		Pattern patternProvincia = Pattern.compile(ConstantesValidaciones.REGEXP_PROVINCIA);
		Pattern patternNumero = Pattern.compile(ConstantesValidaciones.REGEXP_NUMERO_CALLE);
		Pattern patternCp = Pattern.compile(ConstantesValidaciones.REGEXP_CP);
		
		Matcher matcherNombreCompleto = patternNombreCompleto.matcher(nombreCompleto);
		Matcher matcherDireccion = patternDireccion.matcher(direccion);
		Matcher matcherProvincia = patternProvincia.matcher(provincia);
		Matcher matcherNumero = patternNumero.matcher(numero);
		Matcher matcherCp = patternCp.matcher(cp);

		
		if ( ! matcherNombreCompleto.matches() ) {
			return "nombre incorrecto desde el lado del servidor";
		}
		if( ! matcherDireccion.matches() ) {
			return "direccion incorrecta desde el lado del servidor";
		}
		if( ! matcherProvincia.matches()) {
			return "provincia incorrecta desde el lado del server";
		}
		if( ! matcherNumero.matches()) {
			return "numero incorrecto desde el lado del server";
		}
		if( ! matcherCp.matches()) {
			return "codigo postal incorrecto desde el lado del server";
		}
		
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		servicioPedidos.procesarPaso1(nombreCompleto, tipoCalle, direccion, numero, provincia, cp, u.getId());		
		return "ok";
		
	}
	
	@RequestMapping("realizar-pedido-paso2")
	public String realizarPedidoPaso2(String tipoTarjeta, String numTarjeta, String titularTarjeta, String cvvTarjeta, String fechaCaducidadTarjeta, HttpServletRequest request) {
		
		//validar datos:
		Pattern patternNumTarjeta = Pattern.compile(ConstantesValidaciones.REGEXP_NUMERO_TARJETA);
		Pattern patternTitularTarjeta = Pattern.compile(ConstantesValidaciones.REGEXP_TITULAR_TARJETA);
		Pattern patternCvvTarjeta = Pattern.compile(ConstantesValidaciones.REGEXP_CVV_TARJETA);
		Pattern patternFechaCaducidadTarjeta = Pattern.compile(ConstantesValidaciones.REGEXP_FECHA_CADUCIDAD);


		
		Matcher matcherNumTarjeta = patternNumTarjeta.matcher(numTarjeta);
		Matcher matcherTitularTarjeta = patternTitularTarjeta.matcher(titularTarjeta);
		Matcher matcherCvvTarjeta = patternCvvTarjeta.matcher(cvvTarjeta);
		Matcher matcherFechaCaducidadTarjeta = patternFechaCaducidadTarjeta.matcher(fechaCaducidadTarjeta);

		
		if ( ! matcherNumTarjeta.matches() ) {
			return "Numero de tarjeta incorrecto desde el lado del server";
		}
		if( ! matcherTitularTarjeta.matches() ) {
			return "Titular de la tarjeta incorrecto desde el lado del server";
		}
		if( ! matcherCvvTarjeta.matches()) {
			return "CVV incorrecto desde el lado del server";
		}
		if( ! matcherFechaCaducidadTarjeta.matches()) {
			return "Caducidad de la tarjeta incorrecto desde el lado del server";
		}
		
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		servicioPedidos.procesarPaso2(tipoTarjeta, numTarjeta, titularTarjeta, cvvTarjeta, fechaCaducidadTarjeta, u.getId());
		return "ok";
		
	}
	
	@RequestMapping("realizar-pedido-paso3")
	public ResumenPedido realizarPedidoPaso3(String nombreMascota, String especie, int edad, String tamanio, HttpServletRequest request) {
		//validacion
		Pattern patternNombreMascota = Pattern.compile(ConstantesValidaciones.REGEXP_NOMBRE_MASCOTA);
		Matcher matcherNombreMascota = patternNombreMascota.matcher(nombreMascota);
		
		if( ! matcherNombreMascota.matches()) {
			System.out.println("Nombre de mascota incorrecto desde el lado del servidor");
		}
		
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		servicioPedidos.procesarPaso3(nombreMascota, especie, edad, tamanio, u.getId());
		ResumenPedido resumen = servicioPedidos.obtenerResumenPedido(u.getId());
		return resumen;
	}
	
	@RequestMapping("confirmar-pedido")
	public String confirmarPedido(HttpServletRequest request) {
		Usuario u = (Usuario)request.getSession().getAttribute("usuario");
		servicioPedidos.confirmarPedido(u.getId());
		
		return "pedido completado";
	}
	
}
