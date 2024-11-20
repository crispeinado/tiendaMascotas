package com.cristina.tiendaMascotas.serviciosREST;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cristina.tiendaMascotas.model.Usuario;
import com.cristina.tiendaMascotas.servicios.ServicioCarrito;

@RestController
public class ServicioRESTCarrito {
	
	@Autowired
	private ServicioCarrito servicioCarrito;

	@RequestMapping("agregar-producto-carrito")
	public String agregarProductoAlCarrito(@RequestParam("id") Integer id, @RequestParam("cantidad") Integer cantidad, HttpServletRequest request) {
		
		Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
		
		servicioCarrito.agregarProducto(id, usuario.getId(), cantidad);
		
		return "ok";
	}
	
	@RequestMapping("obtener-productos-carrito")
	public List<Map<String, Object>> obtenerProductosDelCarrito(HttpServletRequest request) {
		return servicioCarrito.obtenerProductosDelCarritoUsuario(((Usuario) request.getSession().getAttribute("usuario")).getId());
	}
	
}
