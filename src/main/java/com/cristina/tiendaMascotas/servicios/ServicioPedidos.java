package com.cristina.tiendaMascotas.servicios;

import java.util.List;


import com.cristina.tiendaMascotas.model.Pedido;
import com.cristina.tiendaMascotas.model.tiposExtra.ResumenPedido;

public interface ServicioPedidos {

	//parte de cliente
	//paso1
	void procesarPaso1(String nombreCompleto, String tipoCalle, String direccion, String numero, String provincia, String cp, long idUsuario);
	//paso2
	void procesarPaso2(String tipoTarjeta, String numTarjeta, String titularTarjeta, String cvvTarjeta, String fechaCaducidadTarjeta, long idUsuario);
	//paso3
	void procesarPaso3(String nombreMascota, String especie, int edad, String tamanio, long idUsuario);
	//final
	ResumenPedido obtenerResumenPedido(long idUsuario);
	void confirmarPedido(long idUsuario);
	
	//administracion
	List<Pedido> obtenerPedidos();
	Pedido obtenerPedidoPorId(int idPedido);
	
	void actualizarEstadoPedido(int id, String estado);

	
}
