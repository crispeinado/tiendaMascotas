package com.cristina.tiendaMascotas.serviciosJPAImpl;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cristina.tiendaMascotas.constantesSQL.ConstantesSQL;
import com.cristina.tiendaMascotas.model.Categoria;
import com.cristina.tiendaMascotas.model.Pedido;
import com.cristina.tiendaMascotas.model.Producto;
import com.cristina.tiendaMascotas.servicios.ServicioProductos;


@Service
@Transactional
public class ServicioProductosJPAimpl implements ServicioProductos{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void registrarProducto(Producto p) {
		//obtenemos la categoria del id de categoria del producto para asociarlo directamente antes de guardarlo
		Categoria c = entityManager.find(Categoria.class, p.getIdCategoria());
		p.setCategoria(c);
		entityManager.persist(p);
	}

	@Override
	public List<Producto> obtenerProductos() {
		//ahora en jpa las consultas se hacen indicando el nombre de la clase, no la tabla
		return entityManager.createQuery("select p from Producto p").getResultList();
	}

	@Override
	public void borrarProducto(long id) {
		//lo obtienes y luego lo borras
		Producto p = entityManager.find(Producto.class, id);
		entityManager.remove(p);
	}

	@Override
	public Producto obtenerProductoPorId(long id) {
		return entityManager.find(Producto.class, id);
	}

	@Override
	public void actualizarProducto(Producto p) {
		entityManager.merge(p);
	}

	@Override
	public Map<String, Object> obtenerProductoVerDetallePorId(long id) {
		
		//map es lo mismo que list pero indica sobre el primer tipo de dato qué usarás como indice, object el valor
		//guardar elemento de tipo object donde cada uno tendra el nombre indicado por string
		
		//usando jpql:
	//	return (Producto)entityManager.createQuery("select p.nombre, p.mascota, p.marca, p.tamanioMascota, p.descripcion, p.precio from Producto p where p.id =: id").setParameter("id", id).getSingleResult();
	Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_DETALLES_PRODUCTO);
	query.setParameter("id", id);
	
	//una vez preparada la query para obtener el resultaod de tipo map, hay que haacer esto:
	NativeQueryImpl nativeQuery = (NativeQueryImpl)query;
	nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
	
	
	return (Map<String, Object>)nativeQuery.getSingleResult(); 
	}

	@Override
	public List<Map<String, Object>> obtenerProductosParaListado() {
		Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_LISTADO_PRODUCTOS);
		
		NativeQueryImpl nativeQuery = (NativeQueryImpl)query;
		nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
				
		return nativeQuery.getResultList();
	}

	@Override
	public List<Producto> obtenerProductos(String nombreProducto) {
		return entityManager.createQuery("select p from Producto p where p.nombreProducto like :nombreProducto").setParameter("nombreProducto", "%" + nombreProducto + "%").getResultList();
	}

	@Override
	public List<Producto> obtenerProductos(String nombreProducto, int comienzo, int resultadosPorPagina) {
		return entityManager.createQuery("select p from Producto p where p.nombreProducto like :nombreProducto").setParameter("nombreProducto", "%" + nombreProducto + "%")
				.setFirstResult(comienzo).setMaxResults(resultadosPorPagina).getResultList(); //como el limit de sql
	}

	@Override
	public int obtenerTotalProductos() {
		Query q = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_TOTAL_PRODUCTOS);

		return Integer.parseInt(q.getSingleResult().toString());
	}

	@Override
	public int obtenerTotalProductos(String nombreProducto) {
		Query q = entityManager.createNativeQuery(ConstantesSQL.SQL_OBTENER_TOTAL_PRODUCTOS_POR_NOMBRE);
		q.setParameter("nombreProducto", "%" + nombreProducto + "%");
		return Integer.parseInt(q.getSingleResult().toString());
	}
	
}
