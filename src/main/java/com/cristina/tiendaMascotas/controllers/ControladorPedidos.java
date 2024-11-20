package com.cristina.tiendaMascotas.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cristina.tiendaMascotas.model.Pedido;
import com.cristina.tiendaMascotas.model.estadosPedido.EstadosPedido;
import com.cristina.tiendaMascotas.servicios.ServicioPedidos;

@Controller
@RequestMapping("admin/")
public class ControladorPedidos {

	@Autowired
	private ServicioPedidos servicioPedidos;
	
	@RequestMapping("pedidos")
	private String obtenerPedidos(Model model) {
		model.addAttribute("pedidos", servicioPedidos.obtenerPedidos());
		return "admin/pedidos";
	}
	
	@RequestMapping("ver-detalles-pedido")
	private String verDetallesPedido(String id, Model model) {
		Pedido p = servicioPedidos.obtenerPedidoPorId(Integer.parseInt(id));
		model.addAttribute("pedido", p);
		
		//le damos a la vista los valores del desplegable de estados de pedido
		Map<String, String> estados = new HashMap<String, String>();
		estados.put(EstadosPedido.INCOMPLETO.name(), "iniciado por el usuario");
		estados.put(EstadosPedido.COMPLETO.name(), "completado por el usuario");
		estados.put(EstadosPedido.FINALIZADO.name(), "pedido completado y enviado");
		model.addAttribute("estados", estados);
		
		return "admin/pedido-detalles";
	}
	
}
