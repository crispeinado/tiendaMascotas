let regexp_nombre = /^[a-z áéíóúñ]{2,10}$/i
let regexp_email = /^([a-z0-9_\.-]+)@([0-9a-z\.-]+)\.([a-z\.]+)$/i
let regexp_pass = /^[a-z0-9áéíóúñ]{3,10}$/i

let regexp_nombre_completo= /^[a-záéíóúñA-ZÁÉÍÓÚÑ ]{3,25}$/
let regexp_direccion = /^[a-záéíóúñA-ZÁÉÍÓÚÑ ]{3,25}$/
let regexp_provincia = /^[a-záéíóúñA-ZÁÉÍÓÚÑ ]{3,25}$/
let regexp_numero_calle = /^[0-9]{1,3}$/
let regexp_cp = /^[0-9]{5}$/

let regexp_numero_tarjeta = /^[0-9]{12}$/
let regexp_titular_tarjeta = /^[a-záéíóúñA-ZÁÉÍÓÚÑ ]{3,25}$/
let regexp_cvv_tarjeta = /^[0-9]{3}$/
let regexp_fecha_caducidad = /^[0-9]{2}\/[0-9]{2}$/

let regexp_nombre_mascota = /^[a-zA-ZÁÉÍÓÚÑáéíóúñ ]{1,15}$/;



function validarNombre(nombre){
	if(regexp_nombre.test(nombre)){
		return true;
	} else{
		alert("nombre incorrecto")
	}
}

function validarEmail(email){
	if(regexp_email.test(email)){
		return true;
	} else{
		alert("email incorrecto")
	}
}

function validarPass(pass){
	if(regexp_pass.test(pass)){
		return true;
	} else{
		alert("contraseña incorrecta")
	}
}


function validarNombreCompleto(nombreCompleto) {
    if (regexp_nombre_completo.test(nombreCompleto)) {
        return true;
    } else {
        alert("Nombre incorrecto");
    }
}

function validarDireccion(direccion) {
    if (regexp_direccion.test(direccion)) {
        return true;
    } else {
        alert("Dirección incorrecta");
    }
}

function validarProvincia(provincia) {
    if (regexp_provincia.test(provincia)) {
        return true;
    } else {
        alert("Provincia incorrecta");
    }
}

function validarNumeroCalle(numeroCalle) {
    if (regexp_numero_calle.test(numeroCalle)) {
        return true;
    } else {
        alert("Número de calle incorrecto");
    }
}

function validarCP(cp) {
    if (regexp_cp.test(cp)) {
        return true;
    } else {
        alert("Código postal incorrecto");
    }
}


function validarNumeroTarjeta(numeroTarjeta) {
    if (regexp_numero_tarjeta.test(numeroTarjeta)) {
        return true;
    } else {
        alert("Número de tarjeta incorrecto");
    }
}

function validarTitularTarjeta(titularTarjeta) {
    if (regexp_titular_tarjeta.test(titularTarjeta)) {
        return true;
    } else {
        alert("Titular de tarjeta incorrecto");
    }
}

function validarCVVTarjeta(cvvTarjeta) {
    if (regexp_cvv_tarjeta.test(cvvTarjeta)) {
        return true;
    } else {
        alert("CVV incorrecto");
    }
}

function validarFechaCaducidad(fechaCaducidad) {
    if (regexp_fecha_caducidad.test(fechaCaducidad)) {
        return true;
    } else {
        alert("Fecha de caducidad incorrecta");
    }
}

function validarNombreMascota(nombreMascota) {
    if (regexp_nombre_mascota.test(nombreMascota)) {
        return true;
    } else {
        alert("Nombre de mascota incorrecto");
    }
}