package com.cristina.tiendaMascotas.model.tiposExtra;

import java.util.List;
import java.util.Map;

public class ResumenPedido {

	//productos del carrito
	private List<Map<String, Object>> productos;
	
	//datos de envio
	private String nombreCompleto;
	private String tipoCalle;
	private String direccion;
	private String numero;
	private String provincia;
	private String cp;
	
	//datos de pago
	private String tipoTarjeta;
	private String numTarjeta;
	private String titularTarjeta;
	private String cvvTarjeta;
	private String fechaCaducidadTarjeta;
	
	//datos de la mascota
	private String nombreMascota;
	private String especie;
	private int edad;
	private String tamanio;
	
	//si no hay constructor, java incorpora uno vacio por defecto
	
	public List<Map<String, Object>> getProductos() {
		return productos;
	}
	public void setProductos(List<Map<String, Object>> productos) {
		this.productos = productos;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getTipoCalle() {
		return tipoCalle;
	}
	public void setTipoCalle(String tipoCalle) {
		this.tipoCalle = tipoCalle;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCp() {
		return cp;
	}
	public void setCp(String cp) {
		this.cp = cp;
	}
	public String getTipoTarjeta() {
		return tipoTarjeta;
	}
	public void setTipoTarjeta(String tipoTarjeta) {
		this.tipoTarjeta = tipoTarjeta;
	}
	public String getNumTarjeta() {
		return numTarjeta;
	}
	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}
	public String getTitularTarjeta() {
		return titularTarjeta;
	}
	public void setTitularTarjeta(String titularTarjeta) {
		this.titularTarjeta = titularTarjeta;
	}
	public String getCvvTarjeta() {
		return cvvTarjeta;
	}
	public void setCvvTarjeta(String cvvTarjeta) {
		this.cvvTarjeta = cvvTarjeta;
	}
	public String getFechaCaducidadTarjeta() {
		return fechaCaducidadTarjeta;
	}
	public void setFechaCaducidadTarjeta(String fechaCaducidadTarjeta) {
		this.fechaCaducidadTarjeta = fechaCaducidadTarjeta;
	}
	public String getNombreMascota() {
		return nombreMascota;
	}
	public void setNombreMascota(String nombreMascota) {
		this.nombreMascota = nombreMascota;
	}
	public String getEspecie() {
		return especie;
	}
	public void setEspecie(String especie) {
		this.especie = especie;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getTamanio() {
		return tamanio;
	}
	public void setTamanio(String tamanio) {
		this.tamanio = tamanio;
	}
	
}
