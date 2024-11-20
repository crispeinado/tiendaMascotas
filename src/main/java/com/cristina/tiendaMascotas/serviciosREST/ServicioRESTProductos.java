package com.cristina.tiendaMascotas.serviciosREST;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.cristina.tiendaMascotas.model.Producto;
import com.cristina.tiendaMascotas.servicios.ServicioProductos;
import com.google.gson.Gson;

//los servicios ahora en spring mvc se pueden hacer usando controladores, que no devuelven una vista, sino json 

@RestController
public class ServicioRESTProductos {

	@Autowired
	private ServicioProductos servicioProductos;
	
	@RequestMapping("obtener-productos-json")
	public String obtenerProductosEnJSON(){
		List<Map<String, Object>> productos = servicioProductos.obtenerProductosParaListado();
		return new Gson().toJson(productos);
	} //este listado no muestra toda la info. para obtener toda la info tenemos que hacer lo siguiente:
	
	@RequestMapping("obtener-detalles-producto")
	//el requestParam.. es para recibir directamente como entero el id
	//por que integer en vez de int: lo especifica por clase en vz de tipo basico.
	//en algunas versiones de java no hay diferencia. Se puede hacer integer parse int
	public String obtenerDetallesProducto(@RequestParam("id") Integer id) {
		return new Gson().toJson(servicioProductos.obtenerProductoVerDetallePorId(id));
	}
	
}
