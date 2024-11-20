function checkout_paso_0(){
	$("#contenedor").html(html_checkout_1)
	$("#aceptar_paso_1").click(checkout_paso_1_aceptar)
}

function checkout_paso_1_aceptar(){
	$("form").on("submit", function(event){
		event.preventDefault();
		let nombreCompleto = $("#campo_nombre").val()
		let tipoCalle = $("#tipoCalle").find(":selected").val()
		let direccion = $("#campo_direccion").val()
		let numero = $("#campo_numero").val()
		let provincia = $("#campo_provincia").val()
		let cp = $("#campo_codigo_postal").val()
			
		if( !validarNombreCompleto(nombreCompleto) ||
			!validarDireccion(direccion) ||
			!validarProvincia(provincia) ||
			!validarNumeroCalle(numero) ||
			!validarCP(cp) ){
				alert("hay datos incorrectos")
				return
		}
				
			
		$.post("realizar-pedido-paso1",{
			nombreCompleto: nombreCompleto,
			tipoCalle: tipoCalle,
			direccion: direccion,
			numero: numero,
			provincia: provincia,
			cp: cp
		}).done(function(res){
			if(res=="ok"){
				$("#contenedor").html(html_checkout_2)
				$("#aceptar_paso_2").click(checkout_paso_2_aceptar)
			}else{
				alert(res)
			}
		})
	})
	
}

function checkout_paso_2_aceptar(){
	$("form").on("submit", function(event){
		event.preventDefault();
		
		let tipo = $("#tipo_tarjeta").find(":selected").val()
		let num = $("#numero_tarjeta").val()
		let titular = $("#titular_tarjeta").val()
		let cvv = $("#cvv_tarjeta").val()
		let fecha_caducidad = $("#fecha_caducidad_tarjeta").val()

		if( !validarNumeroTarjeta(num) ||
			!validarTitularTarjeta(titular) ||
			!validarCVVTarjeta(cvv) ||
			!validarFechaCaducidad(fecha_caducidad)){		
			
				alert("hay datos incorrectos")
				return
						}
			
		$.post("realizar-pedido-paso2",{
			tipoTarjeta: tipo,
			numTarjeta: num,
			titularTarjeta: titular,
			cvvTarjeta: cvv,
			fechaCaducidadTarjeta: fecha_caducidad
		}).done(function(res){
				if(res=="ok"){
					$("#contenedor").html(html_checkout_3)
					$("#aceptar_paso_3").click(checkout_paso_3_aceptar)
				}else{
					alert(res)
				}
			})
	})
}

function checkout_paso_3_aceptar(){
	$("form").on("submit", function(event){
		event.preventDefault();
		
		let nombre = $("#nombre_mascota").val()
		let especie = $("#especie_mascota").find(":selected").val()
		let edad = $("#edad_mascota").val()
		let tamanio = $("#tamanio_mascota").find(":selected").val()

		if( !validarNombreMascota(nombre) ){
			alert("hay datos incorrectos")
			return
		}
			
		$.post("realizar-pedido-paso3",{
			nombreMascota: nombre,
			especie: especie,
			edad: edad,
			tamanio: tamanio,
		}).done(function(res){
			console.log("resumen del pedido: ")
			console.log(res)
			let html = Mustache.render(html_resumen_pedido,res)
			$("#contenedor").html(html)
			$("#boton_confirmar_pedido").click(confirmar_pedido)
		})
	})

}

function confirmar_pedido(){
	$.post("confirmar-pedido").done(function(res){
		if(res == "pedido completado"){
			alert("gracias por realizar tu pedido")
			obtenerProductos()
		}
	})
}