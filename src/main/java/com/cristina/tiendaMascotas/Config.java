package com.cristina.tiendaMascotas;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.cristina.tiendaMascotas.interceptores.InterceptorAdmin;

//si o si se tiene que llamar Config
@Component
public class Config implements WebMvcConfigurer{
	
	@Autowired
	private InterceptorAdmin interceptorAdmin;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptorAdmin);
		registry.addInterceptor(localeChangeInterceptor());
	}
	
	@Bean
	public SessionLocaleResolver localeResolver() {
		//en spring, cuando ponemos @bean tanto sobre una clase como sobre un método, estamos diciendo:
		//- si es una clase: que un objeto de la misma pase a formar parte del contenedor de spring
		//- si es un método: el objeto que de en el return el metodo es el que pasa a formar parte del contenedor de spring
		//y ambos se pueden pedir por @Autowired
		
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		//idioma del usuario se mantiene en sesión y se marca por defecto el que tenga el usuario:
		localeResolver.setDefaultLocale(Locale.getDefault());
		return localeResolver;
		
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		//realizar cambio de idioma
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setIgnoreInvalidLocale(true); //si está en otro idioma lo ignora
		localeChangeInterceptor.setParamName("idioma");
		return localeChangeInterceptor;
	}
	
}
