package com.cristina.tiendaMascotas.controllers.imagen;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cristina.tiendaMascotas.servicios.ServicioProductos;

@Controller
public class MostrarImagenProducto {

	@Autowired
	private ServicioProductos servicioProductos;
	
	@RequestMapping("mostrar_imagen")
	public void mostrarImagen(String id, HttpServletResponse response) throws IOException {
		byte[] info = servicioProductos.obtenerProductoPorId(Integer.parseInt(id)).getImagenPortada();
		if(info==null) {
			return;
		}
		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(info);
		response.getOutputStream().close();
	}
	
}
