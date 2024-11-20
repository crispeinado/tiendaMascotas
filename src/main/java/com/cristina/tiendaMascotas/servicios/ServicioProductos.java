package com.cristina.tiendaMascotas.servicios;

import java.util.List;
import java.util.Map;

import com.cristina.tiendaMascotas.model.Producto;

public interface ServicioProductos {

	void registrarProducto(Producto p);
	
	List<Producto> obtenerProductos();
	
	List<Producto> obtenerProductos(String nombreProducto);
	
	List<Producto> obtenerProductos (String nombreProducto, int comienzo, int resultadosPorPagina);
	
	void borrarProducto(long id);
	
	Producto obtenerProductoPorId(long id);
	
	void actualizarProducto(Producto p);

	Map<String, Object> obtenerProductoVerDetallePorId(long id);

	List<Map<String, Object>> obtenerProductosParaListado();

	int obtenerTotalProductos();

	int obtenerTotalProductos(String nombreProducto);

	
}
