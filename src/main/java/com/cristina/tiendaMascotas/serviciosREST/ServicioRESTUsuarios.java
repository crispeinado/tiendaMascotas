package com.cristina.tiendaMascotas.serviciosREST;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cristina.tiendaMascotas.constantesValidaciones.ConstantesValidaciones;
import com.cristina.tiendaMascotas.model.Usuario;
import com.cristina.tiendaMascotas.servicios.ServicioUsuarios;
import com.cristina.tiendaMascotas.serviciosREST.respuestas.RespuestaLogin;

@RestController
public class ServicioRESTUsuarios {

	@Autowired
	private ServicioUsuarios servicioUsuarios;
	
	@RequestMapping("registrar-usuario-cliente")
	public String registrarUsuario(
			String nombre, String pass, String email){
		
		
		if( servicioUsuarios.comprobarEmailExiste(email) ) {
			return "email ya registrado";
		}
		
		//validar datos:
		Pattern patternNombre = Pattern.compile(ConstantesValidaciones.REGEXP_NOMBRE_REGISTRO_USUARIO);
		Pattern patternEmail = Pattern.compile(ConstantesValidaciones.REGEXP_EMAIL_REGISTRO_USUARIO);
		Pattern patternPass = Pattern.compile(ConstantesValidaciones.REGEXP_PASS_REGISTRO_USUARIO);


		
		Matcher matcherNombre = patternNombre.matcher(nombre);
		Matcher matcherEmail = patternEmail.matcher(email);
		Matcher matcherPass = patternPass.matcher(pass);

		
		if ( ! matcherNombre.matches() ) {
			return "nombre incorrecto desde el lado del servidor";
		}
		if( ! matcherEmail.matches() ) {
			return "email incorrecto desde el lado del servidor";
		}
		if( ! matcherPass.matches()) {
			return "pass incorrecto desde el lado del server";
		}
		
		
		Usuario u = new Usuario(nombre, email, pass);
		servicioUsuarios.registrarUsuario(u);
		return "usuario registrado correctamente";
	}
	
	@RequestMapping("identificar-usuario")
	public RespuestaLogin identificarUsuario(String email, String pass, HttpServletRequest request){
		Usuario u = servicioUsuarios.obtenerUsuarioPorEmailyPass(email, pass);
		RespuestaLogin rl = null;
		if( u != null) {
			rl = new RespuestaLogin("ok", u.getNombre());
			//vamos a meter en sesion, la informacion del usuario que se ha identificado
			request.getSession().setAttribute("usuario", u);
		}else {
			rl = new RespuestaLogin("no-ok", "");
		}
		return rl;
	}
	
	@RequestMapping("cerrar-sesion-usuario")
	public String cerrarSesionUsuario(HttpServletRequest request) {
		request.getSession().removeAttribute("usuario");
		return "ok";
	}
	
}
