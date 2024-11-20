package com.cristina.tiendaMascotas.interceptores;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class InterceptorAdmin implements HandlerInterceptor{

	private static final String PASS_ADMIN = "123";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//se ejecuta antes de cualquier acceso a /admin:
		//se guarda el token en sesion para identificar al usuario como admin
		System.out.println("se ejecuta el interceptor");
		if(request.getParameter("pass-login-admin") != null) {
			if(request.getParameter("pass-login-admin").equals(PASS_ADMIN)) {
				request.getSession().setAttribute("token-admin", "ok");
			}
		}
		//compruebo el token para permitir el acceso a /admin o redirigir a formulario de identificacion
		if(request.getRequestURI().contains("/admin/")) {
			if(!(request.getSession().getAttribute("token-admin")!=null && request.getSession().getAttribute("token-admin").equals("ok"))) {
				//si no se cumple, el usuario está accediendo como admin sin ser admin
				response.sendRedirect("../loginAdmin");
				
				return false;
			}
		}
		
		return true;
	}

	
	
}
