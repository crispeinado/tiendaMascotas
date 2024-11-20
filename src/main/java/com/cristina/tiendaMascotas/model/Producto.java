package com.cristina.tiendaMascotas.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

@Entity //creará en la base de datos una tabla llamada producto con todos los campos. Como no queremos el campo portada*
@Table(name = "productos_mascotas") //se asocia la clase con la tabla, por tanto "productos_mascotas" sera el nombre de la tabla
public class Producto {

	@Size(min=3, max=40, message="el nombre del producto debe tener entre 3 y 40 caracteres")
	@NotEmpty(message = "el nombre del producto no debe estar vacio")
	@Pattern(regexp = "^[A-Za-z0-9 áéíóúñÁÉÍÓÚÑ]{3,40}$", message= "el nombre del producto debe contener letras y numeros y entre 3 a 40 caracteres")
	@Column(name="nombre_producto", length = 120) //para la bd. Se pueden poner muchas propiedades (unique, nullable...)
	private String nombreProducto;
	
	@Column(length = 120)
	private String mascota;
	
	private String marca;
	
	@Column(name="tamanio_mascota")
	private String tamanioMascota;
	
	private String descripcion;
	
	@NotNull(message="debes insertar un precio")
	@Min(value=1, message="el precio minimo es 1 euro")
	@Max(value = 999, message="el precio maximo es de 999 euros")
	private double precio;
	
	//asociaciones:
	@ManyToOne(optional = false)
	private Categoria categoria;
	
	@Transient
	//asi gestiono la id de categoria del desplegable
	private int idCategoria;
	
	//guardamos la imagen de la portada en bd
	@Lob
	@Column(name="imagen_portada")
	private byte[] imagenPortada;
	
	//*ponemos esto:
	@Transient //se pone encima de cada campo que no quieres
	private MultipartFile archivoSubido;
	
	private Date fechaUltimaModificacion;
	
	//para indicar que este campo es la pk:
	@Id
	@GeneratedValue //auto increment
	private long id;
	
	public Producto() {

	}

	public Producto(String nombreProducto, String mascota, String marca, String tamanioMascota, String descripcion, double precio) {
		super();
		this.nombreProducto = nombreProducto;
		this.mascota = mascota;
		this.marca = marca;
		this.tamanioMascota = tamanioMascota;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getMascota() {
		return mascota;
	}

	public void setMascota(String mascota) {
		this.mascota = mascota;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getTamanioMascota() {
		return tamanioMascota;
	}

	public void setPesoMascota(String tamanioMascota) {
		this.tamanioMascota = tamanioMascota;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public byte[] getImagenPortada() {
		return imagenPortada;
	}

	public void setImagenPortada(byte[] imagenPortada) {
		this.imagenPortada = imagenPortada;
	}

	public MultipartFile getArchivoSubido() {
		return archivoSubido;
	}

	public void setArchivoSubido(MultipartFile archivoSubido) {
		this.archivoSubido = archivoSubido;
	}

	public Date getFechaUltimaModificacion() {
		return fechaUltimaModificacion;
	}

	public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
		this.fechaUltimaModificacion = fechaUltimaModificacion;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Producto [nombreProducto=" + nombreProducto + ", mascota=" + mascota + ", tamanioMascota=" + tamanioMascota + ", descripcion="
				+ descripcion + ", precio=" + precio + "]";
	}


}
