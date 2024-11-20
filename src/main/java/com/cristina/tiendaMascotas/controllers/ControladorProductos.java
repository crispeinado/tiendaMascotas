package com.cristina.tiendaMascotas.controllers;


import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cristina.tiendaMascotas.model.Producto;
import com.cristina.tiendaMascotas.servicios.ServicioCategorias;
import com.cristina.tiendaMascotas.servicios.ServicioProductos;

@Controller
@RequestMapping("admin/")
public class ControladorProductos {

    private static final int MAX_PRODUCTOS = 10;
	// Con Spring, se puede pedir una bean de una manera cómoda:
    @Autowired
    private ServicioProductos servicioProductos;
    // Así se le dice a Spring que nos dé la única bean que tenga de la única clase que esté implementando el interfaz ServicioProductos
    
    @Autowired
    private ServicioCategorias servicioCategorias;
    
    // Lo que pongamos en @RequestMapping es la ruta que atiende el siguiente método:
    @RequestMapping("productos")
    public String obtenerProductos(@RequestParam(name="nombreProducto", defaultValue = "") String nombreProducto, @RequestParam(name="comienzo", defaultValue = "0") Integer comienzo, Model model){
    	
        List<Producto> productos = servicioProductos.obtenerProductos(nombreProducto,comienzo,MAX_PRODUCTOS);
        int totalProductos = servicioProductos.obtenerTotalProductos(nombreProducto);
        model.addAttribute("productos", productos); //**el atributo es lo mismo que lo que debes tener en productos 
        model.addAttribute("nombreProducto", nombreProducto);
        model.addAttribute("siguiente", comienzo + MAX_PRODUCTOS);
        model.addAttribute("total", totalProductos);
        model.addAttribute("anterior",comienzo - MAX_PRODUCTOS);
        return "admin/productos"; // Esto es la jsp que se muestra
    }
    
    @RequestMapping("productos-borrar")
    public String borrarProducto(String id, Model model){
    	
        servicioProductos.borrarProducto(Integer.parseInt(id));
        return obtenerProductos("", 0, model);
    }
    
    @RequestMapping("productos-nuevo")
    public String nuevoProducto(Model model) {
        // Se muestra al usuario un formulario para registrar un producto.
        // Spring MVC nos pide que le mandemos al formulario un objeto indicando el valor por defecto de cada campo
        Producto p = new Producto();
        p.setPrecio(1);
        model.addAttribute("nuevoProducto", p);
        //aqui metemos las categorias para que lleguen a la vista
        model.addAttribute("categorias", servicioCategorias.obtenerCategoria());
        return "admin/productos-nuevo";
    }
    
    @RequestMapping("productos-guardar-nuevo")
    public String guardarNuevoProducto(@ModelAttribute("nuevoProducto") @Valid Producto nuevoProducto, BindingResult br, Model model, HttpServletRequest request) {
    	
    	if(br.hasErrors()) {
            model.addAttribute("categorias", servicioCategorias.obtenerCategoria());
            return "admin/productos-nuevo";
    	}
    	
    	//le asignamos el archivo subido:
    	try {
			nuevoProducto.setImagenPortada(nuevoProducto.getArchivoSubido().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
        servicioProductos.registrarProducto(nuevoProducto);
        
        return obtenerProductos("", 0, model);
    }
    
    @RequestMapping("productos-editar")
    public String editarProducto(String id, Model model) {
    	Producto p = servicioProductos.obtenerProductoPorId(Long.parseLong(id));
    	
    	model.addAttribute("productoEditar", p);
    	
    	return "admin/productos-editar";
    }
    
    @RequestMapping("productos-guardar-cambios")
    public String guardarCambiosProducto(Producto productoEditar, Model model, HttpServletRequest request) { 
    	//se tiene que llamar productoEditar
    	
    	servicioProductos.actualizarProducto(productoEditar);

    	return obtenerProductos("", 0, model);
    	
    }
}
