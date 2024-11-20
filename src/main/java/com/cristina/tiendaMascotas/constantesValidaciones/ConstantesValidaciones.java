package com.cristina.tiendaMascotas.constantesValidaciones;

public class ConstantesValidaciones {

	//registro
	public final static String REGEXP_NOMBRE_REGISTRO_USUARIO = "^[a-zA-Z áéíóúñÁÉÍÓÚÑ]{2,25}$";
	public final static String REGEXP_EMAIL_REGISTRO_USUARIO = "^([a-z0-9_\\.-]+)@([0-9a-z\\.-]+)\\.([a-z\\.]+)$";
	public final static String REGEXP_PASS_REGISTRO_USUARIO = "^[a-z0-9áéíóúñ]{3,10}$";
	
	//paso1
	public final static String REGEXP_NOMBRE_COMPLETO = "^[a-zA-ZÁÉÍÓÚÑáéíóúñ ]{3,25}$";
	public final static String REGEXP_DIRECCION = "^[a-zA-ZÁÉÍÓÚÑáéíóúñ ]{3,25}$";
	public final static String REGEXP_PROVINCIA = "^[a-zA-ZÁÉÍÓÚÑáéíóúñ ]{3,25}$";
	public final static String REGEXP_NUMERO_CALLE = "^[0-9]{1,3}$";
	public final static String REGEXP_CP = "^[0-9]{5}$";
	//paso2
	public final static String REGEXP_NUMERO_TARJETA = "^[0-9]{12}$";
	public final static String REGEXP_TITULAR_TARJETA = "^[a-zA-ZÁÉÍÓÚÑáéíóúñ ]{3,25}$";
	public final static String REGEXP_CVV_TARJETA = "^[0-9]{3}$";
	public final static String REGEXP_FECHA_CADUCIDAD = "^[0-9]{2}/[0-9]{2}$";
	//paso3
	public final static String REGEXP_NOMBRE_MASCOTA = "^[a-zA-ZÁÉÍÓÚÑáéíóúñ ]{1,15}$";

}
