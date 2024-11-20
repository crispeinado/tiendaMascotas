function obtenerProductos(){
	$.ajax("obtener-productos-json").done( function( respuesta ) {
		//vamos a procesar la respuesta con el json de los productos:
		//transformamos el json recibido a array de javascript:
		let productos = JSON.parse(respuesta)
		console.log(productos)
		let texto_html = ""
		
		texto_html = Mustache.render( html_listado_productos, productos )
		
		$("#contenedor").html(texto_html)
		//una vez volcado el listado, decimos qué debe hacer el enlace 'ver detalles':
		$(".enlace_ver_detalles_producto").click(mostrarDetallesProducto)		
	} )
}

function mostrarDetallesProducto(){
	//this en js para eventos: en este caso, click de raton, indica el elememnto sobre el qeu se ha hecho click
	//si le pones $ puedes usas jquery sobre ese elemento
	//attr para obtener atributo de un elemento
	let idProducto = $(this).attr("id-producto")
	//para pedir un json a una ruta (endpoint)
	$.getJSON("obtener-detalles-producto",{
		id: idProducto
	}).done(function(res){
		let html = Mustache.render(html_detalles_producto, res)
		$("#contenedor").html(html)
		$("#enlace_agregar_al_carrito").click(agregarProductoAlCarrito)
		
		//alert("recibido del servicio rest: " + res) 
		//no da una respuesta ok. mañana cuando el servicio nos devuelva la info del producto no queremos hacerlo con el array de bytes, solo ciertos campos
		
		//usar una plantilla y mustache para mostrar dicha info al usuario
	})
}

function agregarProductoAlCarrito(){
	if(nombre_login==""){
		alert("tienes que identificarte para poder comprar productos")
		mostrarFormularioLogin()
		return;
	}
	
	let idProducto = $(this).attr("id-producto")
	$.post("agregar-producto-carrito",{
		id: idProducto,
		cantidad: 1
	}).done(function(res){
		if(res == "ok"){
			alert("producto agregado al carrito correctamente.")
		}
	})
}

function enviarInfoUsuarioAlServidor(){
	let nombre = $("#nombre").val()
	let email = $("#email").val()
	let pass = $("#pass").val()
	
	if( ! validarNombre(nombre) ||
		! validarEmail(email) ||
		! validarPass(pass) ){
			alert("hay datos incorrectos")
			return
	}
	
	$.post("registrar-usuario-cliente",
		{
			nombre: nombre,
			email: email,
			pass: pass
		}		
	).done(function(res){
		alert(res)
		mostrarFormularioLogin()
	})
}

function mostrarFormularioRegistroUsuario(){
	$("#contenedor").html(html_formulario_registro_usuario)
	$("#boton_registro_usuario").click(enviarInfoUsuarioAlServidor)
}

function mostrarFormularioLogin(){
	$("#contenedor").html(html_formulario_identificacion_usuario)
	
	if(typeof(Cookies.get("email"))!="undefined"){
			$("#email").val(Cookies.get("email"))
		}
	if(typeof(Cookies.get("pass"))!="undefined"){
			$("#pass").val(Cookies.get("pass"))
	}
	
	$("#form_login").submit(
		function(e){
			e.preventDefault()
			$.post("identificar-usuario",{
				email: $("#email").val(),
				pass: $("#pass").val()
			}).done(function(res){
				console.log("respuesta recibida")
				console.log(res)
				if(res.operacion == "ok"){
					//si el login es ok y ademas se activa el checkbox, guardo el email y pass del usuario en una cookie
					if($("#recordar_datos").prop("checked")){
						Cookies.set("email",$("#email").val(),{expires:100})
						Cookies.set("pass",$("#pass").val(),{expires:100})
					}else{ //no es necesario el else
						//si el usuario se identifica sin activar el checkbox, se borran las cookies previas
						if(typeof(Cookies.get("email"))!="undefined"){
							Cookies.remove("email")
						}
						if(typeof(Cookies.get("pass"))!="undefined"){
							Cookies.remove("pass")
							//tambien puedes meter los dos ifs en uno
						}
					}
					nombre_login = res.usuario
					alert("bienvenido " + nombre_login + " ya puedes comprar en la tienda para mascotas")
					obtenerProductos()
					$("#menu-cerrar-sesion").css("visibility","visible")
					$("#menu-identificarme").hide()
					$("#menu-registrarme").hide()
				}else{
					alert("email o pass incorrectos")
				}
			})
		}
	)
}

function cerrarSesionUsuario(){
	$.get("cerrar-sesion-usuario").done(function(res){
		if(res == "ok"){
			alert("hasta pronto, " + nombre_login)
			nombre_login = ""
			$("#menu-cerrar-sesion").css("visibility", "none")
			$("#menu-identificarme").show()
			$("#menu-registrarme").show()
			obtenerProductos()
		}
	})
}

function obtenerProductosCarrito(){
	if(nombre_login == ""){
		alert("tienes que identificarte para acceder a tu carrito")
		mostrarFormularioLogin()
		return //asi no continua. se hace en vez de poner else para no encadenar
	}
	$.getJSON("obtener-productos-carrito").done(
		function(res){
			console.log("productos del carrito:")
			console.log(res)
			let res_html = Mustache.render(html_carrito,res)
			$("#contenedor").html(res_html)
			$(".enlace_ver_detalles_producto").click(mostrarDetallesProducto)
			let total_productos = 0
			for(indice in res){
				total_productos += res[indice].cantidad
			}
			$("#total_productos").html(total_productos)
			$("#realizar-pedido").click(checkout_paso_0)
		}
	)
}
