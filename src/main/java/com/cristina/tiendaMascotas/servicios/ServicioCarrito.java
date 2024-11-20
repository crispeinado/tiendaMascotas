package com.cristina.tiendaMascotas.servicios;

import java.util.List;
import java.util.Map;

public interface ServicioCarrito {

	//no hace falta poner public en interfaces
	void agregarProducto(long idProducto, long idUsuario, int cantidad);
	
	List<Map<String, Object>> obtenerProductosDelCarritoUsuario(long idUsuario);
}
