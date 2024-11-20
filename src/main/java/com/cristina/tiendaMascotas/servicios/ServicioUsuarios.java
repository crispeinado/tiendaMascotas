package com.cristina.tiendaMascotas.servicios;

import com.cristina.tiendaMascotas.model.Usuario;

public interface ServicioUsuarios {

	void registrarUsuario(Usuario u);

	Usuario obtenerUsuarioPorEmailyPass(String email, String pass);

	boolean comprobarEmailExiste(String email);
	
}
