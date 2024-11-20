package com.cristina.tiendaMascotas.serviciosJPAImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cristina.tiendaMascotas.constantesSQL.ConstantesSQL;
import com.cristina.tiendaMascotas.model.Carrito;
import com.cristina.tiendaMascotas.model.Producto;
import com.cristina.tiendaMascotas.model.ProductoCarrito;
import com.cristina.tiendaMascotas.model.Usuario;
import com.cristina.tiendaMascotas.servicios.ServicioCarrito;
import com.cristina.tiendaMascotas.utilidades.Utilidades;

@Service
@Transactional
public class ServicioCarritoJPAImpl implements ServicioCarrito{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void agregarProducto(long idProducto, long idUsuario, int cantidad) {
		Usuario usuario = entityManager.find(Usuario.class, idUsuario);
		Carrito carrito = usuario.getCarrito();
		if (carrito == null) {
			//si el carrito del usuario no existe se crea:
			carrito = new Carrito();
			carrito.setUsuario(usuario);
			usuario.setCarrito(carrito);
			entityManager.persist(carrito);
		}
		//si el carrito no tiene un productoCarrito del idProducto que se quiera agregar, lo creamos:
		boolean productoEnCarrito = false;
		for(ProductoCarrito pc : carrito.getProductosCarrito()) {
			if(pc.getProducto().getId() == idProducto) {
				productoEnCarrito = true;
				//incrementamos la cantidad
				pc.setCantidad(pc.getCantidad() + cantidad);
				entityManager.merge(pc);
			}
		}
		
		if(!productoEnCarrito) {
			//lo creamos:
			ProductoCarrito pc = new ProductoCarrito();
			pc.setCarrito(carrito);
			pc.setProducto(entityManager.find(Producto.class, idProducto));
			pc.setCantidad(cantidad);
			entityManager.persist(pc);
		}
	}

	@Override
	public List<Map<String, Object>> obtenerProductosDelCarritoUsuario(long idUsuario) {
		Usuario usuario = entityManager.find(Usuario.class, idUsuario);
		Carrito carrito = usuario.getCarrito();
		List<Map<String, Object>> res = new ArrayList<Map<String,Object>>();
		if(carrito != null) {
			Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_PRODUCTOS_CARRITO);
			query.setParameter("carrito_id", carrito.getId());
			res = Utilidades.procesaNativeQuery(query);
		}
		
		return res;
	}
	
}
