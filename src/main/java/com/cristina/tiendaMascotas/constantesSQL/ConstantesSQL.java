package com.cristina.tiendaMascotas.constantesSQL;

//cuando jpa se quede corto y queramos ser mas especificos con las consultas sobre una bd concreta, podemos usar jpa son sql
public class ConstantesSQL {

	//NOMBRES DE TABLAS Y COLUMNAS DE LA BD
	public final static String SQL_OBTENER_DETALLES_PRODUCTO = 
			"select p.nombre_producto, p.mascota, p.marca, p.tamanio_mascota, p.descripcion, p.precio, p.id from productos_mascotas as p where p.id = :id";
	
	public static final String SQL_OBTENER_LISTADO_PRODUCTOS = 
			"select p.nombre_producto, p.precio, p.id from productos_mascotas as p";
	
	public static final String SQL_OBTENER_LISTADO_PRODUCTOS_INCLUYENDO_NOMBRE_COMIENZO = 
			"select p.nombre_producto, p.mascota, p.marca, p.tamanio_mascota, p.descripcion, p.precio, p.id from productos_mascotas as p where p.nombre_producto like :nombreProducto order by p.id asc limit :comienzo, 10";
	
	public static final String SQL_OBTENER_PRODUCTOS_CARRITO = 
			"select pm.id, pm.nombre_producto, pm.mascota, pm.marca, pm.precio, pc.cantidad from productos_mascotas as pm,"
			+ " producto_carrito as pc where pc.producto_id = pm.id and pc.carrito_id = :carrito_id";
	
	public static final String SQL_BORRAR_PRODUCTOS_CARRITO = 
			"delete from producto_carrito where carrito_id = :carrito_id";

	public static final String SQL_OBTENER_ID_USUARIO_POR_EMAIL = 
			"select id from usuario where email = :email";
	
	public static final String SQL_OBTENER_TOTAL_PRODUCTOS =
			"select count(id) from productos_mascotas";
	
	public static final String SQL_OBTENER_TOTAL_PRODUCTOS_POR_NOMBRE =
			"select count(id) from productos_mascotas where nombre_producto like :nombreProducto";
	
}
