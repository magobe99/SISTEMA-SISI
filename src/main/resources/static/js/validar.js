function validarInicio(){
    var email = document.getElementById("txtemail").value;
    var clave = document.getElementById("txtPassword").value;
	
    expresion = /\w+@\w+\.+[a-z]/;

    if (email.length > 20) {

        Swal.fire({
          icon: 'error',
          title: 'Datos Incompletos',
          text: 'El correo es muy extenso!',
          showConfirmButton: true,
          timer: 8000

        });
        return false;

      } else if (!email || email == "") {


        Swal.fire({
          icon: 'error',
          title: 'Datos Incompletos',
          text: 'El correo es requerido',
          showConfirmButton: true,
          timer: 8000

        });
        return false;

      }

      else if (!expresion.test(email)) {
        Swal.fire({
          icon: 'error',
          title: 'Datos Incompletos',
          text: 'El correo no es valido',
          showConfirmButton: true,
          timer: 8000

        });
        return false;
      }
      var linke = /*[[@{/}]]*/'';
    if(email === "admin20@gmail.com" && clave === "12345"){
        window.location.href = linke;
        return false; 
    }
    if(email === "Coordinador30@gmail.com" && clave === "12345"){
        window.location.href = linke; 
        return false; 
    }

    if(email === "Vendedor40@gmail.com" && clave === "12345"){
        window.location.href = linke; 
        return false; 

    } else{
        Swal.fire({
            icon: 'error',
            title: 'Credenciales Invalidas',
            text: 'Acceso no permitido, usuario y/o clave invalidos ',
            showConfirmButton:true,
            timer:4500
        });

        return false; 

        
    }

}

function validarContra() {
    var core = document.getElementById("core").value;


    expresion = /\w+@\w+\.+[a-z]/;

    if (core.length > 20) {

        Swal.fire({
            icon: 'error',
            title: 'Datos Incompletos',
            text: 'El correo es muy extenso!',
            showConfirmButton: true,
            timer: 8000

        });
        return false;

    }
    if (!core || core == "") {


        Swal.fire({
            icon: 'error',
            title: 'Datos Incompletos',
            text: 'El correo es requerido',
            showConfirmButton: true,
            timer: 8000

        });
        return false;

    }

    if (!expresion.test(core)) {
        Swal.fire({
            icon: 'error',
            title: 'Datos Incompletos',
            text: 'El correo no es valido',
            showConfirmButton: true,
            timer: 8000

        });
        return false;
    }

    else {
        Swal.fire({
            icon: "success",
            title: "Exito",
            text: "Su nueva contraseña fue enviada a su correo",
            showConfirmButton: true,
            showConfirmButton: true,
            timer: 4500
        });

        return false;


    }

}