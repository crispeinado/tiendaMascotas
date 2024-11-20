 package com.cristina.tiendaMascotas.serviciosJPAImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cristina.tiendaMascotas.constantesSQL.ConstantesSQL;
import com.cristina.tiendaMascotas.model.Carrito;
import com.cristina.tiendaMascotas.model.Pedido;
import com.cristina.tiendaMascotas.model.ProductoCarrito;
import com.cristina.tiendaMascotas.model.ProductoPedido;
import com.cristina.tiendaMascotas.model.Usuario;
import com.cristina.tiendaMascotas.model.estadosPedido.EstadosPedido;
import com.cristina.tiendaMascotas.model.tiposExtra.ResumenPedido;
import com.cristina.tiendaMascotas.servicios.ServicioCarrito;
import com.cristina.tiendaMascotas.servicios.ServicioPedidos;
import com.cristina.tiendaMascotas.utilidades.Utilidades;

@Service
@Transactional
public class ServicioPedidosJPAImpl implements ServicioPedidos {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ServicioCarrito servicioCarrito;

	//en cuanto el usuario completa el paso 1, se genera, si no existe todavía, un único pedido incompleto para el usuario,
	//sobre el que vamos a preparar los datos de la compra
	private Pedido obtenerPedidoActual(long idUsuario) throws Exception {
		Usuario usuario = entityManager.find(Usuario.class, idUsuario);
		Object pedidoEnProceso = null;
		
		List<Pedido> resultadoConsulta =  entityManager.createQuery("select p from Pedido p where p.estado = :estado and p.usuario.id = :usuario_id")
		.setParameter("estado", EstadosPedido.INCOMPLETO.name()).setParameter("usuario_id", idUsuario).getResultList();
		//.name() para que no te de un objeto
		
		if(resultadoConsulta.size() == 1) {
			pedidoEnProceso = resultadoConsulta.get(0);
		}else if(resultadoConsulta.size() > 1) {
			throw new Exception("hay mas de un pedido incompleto para un mismo usuario (está mal)");
		}
		
		Pedido pedido = null;
		if(pedidoEnProceso != null) {
			pedido = (Pedido) pedidoEnProceso;
		}else {
			pedido = new Pedido();
			pedido.setEstado(EstadosPedido.INCOMPLETO.name());
			pedido.setUsuario(usuario);
		}
		
		return pedido;		
	}
	
	@Override
	public void procesarPaso1(String nombreCompleto, String tipoCalle, String direccion, String numero, String provincia, String cp, long idUsuario) {
		try {
			Pedido p = obtenerPedidoActual(idUsuario);
			p.setNombreCompleto(nombreCompleto);
			p.setTipoCalle(tipoCalle);
			p.setDireccion(direccion);
			p.setNumero(numero);
			p.setProvincia(provincia);
			p.setCp(cp);
			entityManager.merge(p);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error procesando paso 1");
		}
	}

	@Override
	public void procesarPaso2(String tipoTarjeta, String numTarjeta, String titularTarjeta, String cvvTarjeta, String fechaCaducidadTarjeta, long idUsuario) {
		try {
			Pedido p = obtenerPedidoActual(idUsuario);
			p.setTipoTarjeta(tipoTarjeta);
			p.setNumTarjeta(numTarjeta);
			p.setTitularTarjeta(titularTarjeta);
			p.setCvvTarjeta(cvvTarjeta);
			p.setFechaCaducidadTarjeta(fechaCaducidadTarjeta);
			entityManager.merge(p);
		} catch (Exception e) {
			System.out.println("Error procesando paso 2");
			e.printStackTrace();
		}
	}
	
	public void procesarPaso3(String nombreMascota, String especie, int edad, String tamanio, long idUsuario) {
		try {
			Pedido p = obtenerPedidoActual(idUsuario);
			p.setNombreMascota(nombreMascota);
			p.setEspecie(especie);
			p.setEdad(edad);
			p.setTamanio(tamanio);
			entityManager.merge(p);
		} catch (Exception e) {
			System.out.println("Error procesando paso 3");
			e.printStackTrace();
		}
		
	}

	@Override
	public ResumenPedido obtenerResumenPedido(long idUsuario) {
		
		ResumenPedido resumen = new ResumenPedido();
		try {
			Pedido p = obtenerPedidoActual(idUsuario);
			//datos de envio
			resumen.setNombreCompleto(p.getNombreCompleto());
			resumen.setTipoCalle(p.getTipoCalle());
			resumen.setDireccion(p.getDireccion());
			resumen.setNumero(p.getNumero());
			resumen.setProvincia(p.getProvincia());
			resumen.setCp(p.getCp());
			//datos de pago
			resumen.setTipoTarjeta(p.getTipoTarjeta());
			resumen.setNumTarjeta(p.getNumTarjeta());
			resumen.setTitularTarjeta(p.getTitularTarjeta());
			resumen.setCvvTarjeta(p.getCvvTarjeta());
			resumen.setFechaCaducidadTarjeta(p.getFechaCaducidadTarjeta());
			//datos de la mascota
			resumen.setNombreMascota(p.getNombreMascota());
			resumen.setEspecie(p.getEspecie());
			resumen.setEdad(p.getEdad());
			resumen.setTamanio(p.getTamanio());
			//productos del carrito
			resumen.setProductos(servicioCarrito.obtenerProductosDelCarritoUsuario(idUsuario));
		} catch (Exception e) {
			System.out.println("Error obteniendo el resumen del pedido");
			e.printStackTrace();
		}
		
		return resumen;
	}

	@Override
	public void confirmarPedido(long idUsuario) {
		try {
			Pedido pedido = obtenerPedidoActual(idUsuario);
			Usuario usuario = entityManager.find(Usuario.class, idUsuario);
			Carrito carrito = usuario.getCarrito();
			
			//pasar todos los productos del carrito al pedido
			if(carrito != null && carrito.getProductosCarrito().size() > 0) {
				for(ProductoCarrito pc: carrito.getProductosCarrito()) {
					ProductoPedido pp = new ProductoPedido();
					pp.setCantidad(pc.getCantidad());
					pp.setProducto(pc.getProducto());
					pedido.getProductosPedido().add(pp);
					pp.setPedido(pedido);
					entityManager.persist(pp);
					System.out.println("pasar todos los productos del carrito al pedido");
				}
			}
			
			//borrar los productos del carrito del usuario
			Query query = entityManager.createNativeQuery(ConstantesSQL.SQL_BORRAR_PRODUCTOS_CARRITO);
			query.setParameter("carrito_id", carrito.getId());
			//finalizar pedido
			pedido.setEstado(EstadosPedido.COMPLETO.name());
			entityManager.merge(pedido);
			
		} catch (Exception e) {
			System.out.println("Error confirmando pedido.");
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Pedido> obtenerPedidos(){
		return entityManager.createQuery("select p from Pedido p order by p.id desc").getResultList();
	}

	@Override
	public Pedido obtenerPedidoPorId(int idPedido) {
		return entityManager.find(Pedido.class, idPedido);
	}

	@Override
	public void actualizarEstadoPedido(int id, String estado) {
		Pedido p =entityManager.find(Pedido.class, id);
		p.setEstado(estado);
		entityManager.merge(p);
	}

}
