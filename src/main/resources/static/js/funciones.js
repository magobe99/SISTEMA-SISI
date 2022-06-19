function reporte(){
	
	var fecha1 = document.getElementById("fecha1").value;
	var fecha2 = document.getElementById("fecha2").value;
	var tip0 = document.getElementById("tip0").value;
	
	window.open("/report/ventas/ReporteVentas?fechaInicio="+fecha1+"&fechaFin="+fecha2+"&tipo="+tip0);

	
}

function reporteCompras(){
	
	var fecha1 = document.getElementById("fecha1").value;
	var fecha2 = document.getElementById("fecha2").value;
	var tip0 = document.getElementById("tip0").value;
	
	window.open("/report/Compras?fechaInicio="+fecha1+"&fechaFin="+fecha2+"&tipo="+tip0);

	
}


function reporte2(){
	
	var categ = document.getElementById("categ").value;
	var tip0 = document.getElementById("tip0").value;
	
	window.open("/report/ventas/ProductoCategoria?categoria="+categ+"&tipo="+tip0);
	

	
}

function reporteLotes(){
	
	var fecha1 = document.getElementById("fecha1").value;
	var fecha2 = document.getElementById("fecha2").value;
	var tip0 = document.getElementById("tip0").value;
	
		window.open("/report/lotes?fechaInicio="+fecha1+"&fechaFin="+fecha2+"&tipo="+tip0);
	
	
}


function reporteProducciones(){
	
	var fecha1 = document.getElementById("fecha1").value;
	var fecha2 = document.getElementById("fecha2").value;
	var tip0 = document.getElementById("tip0").value;
	
		window.open("/report/produccionesF?fechaInicio="+fecha1+"&fechaFin="+fecha2+"&tipo="+tip0);
	
	
}


function reporteUsere(){
	
	var fecha1 = document.getElementById("fecha10").value;
	var fecha2 = document.getElementById("fecha20").value;
	var tip0 = document.getElementById("tipe").value;
	
	
	window.open("/report/ventas/ventasUsuarios?fechaInicio="+fecha1+"&fechaFin="+fecha2+"&tipo="+tip0);
	
}

function Devoluciones(){
	//BUSCAR CODIGO DE COMPRA PARA DEVOLUCIÓN
	var idVenta = document.getElementById("idVenta").value;
	
	location.href="/Devoluciones/"+idVenta;
	
	
}

btn.onclick = function(){
		 			var buscar = document.getElementById("buscar").value;
		 			if (buscar == 'proveedores'){
		 				location.href="/proveedores";
		 			}
		 			else if (buscar == 'nuevo proveedor'){
		 				location.href="/proveedores/nuevo";
		 			}
		 			else if (buscar == 'usuarios'){
		 				location.href="/usuarios";
		 			}
		 			else if (buscar == 'nuevo usuario'){
		 				location.href="/registro";
		 			}
		 			else if (buscar == 'roles'){
		 				location.href="/roles";
		 			}
		 			else if (buscar == 'nuevo rol'){
		 				location.href="/roles/nuevo";
		 			}
		 			else if (buscar == 'clientes'){
		 				location.href="/clientes";
		 			}
		 			else if (buscar == 'nuevo cliente'){
		 				location.href="/clientes/nuevo";
		 			}
		 			else if (buscar == 'inventario'){
		 				location.href="/inventarios";
		 			}
		 			else if (buscar == 'categorías'){
		 				location.href="/categorias";
		 			}
		 			else if (buscar == 'nueva categoría'){
		 				wlocation.href="/categorias/nuevo";
		 			}
		 			else if (buscar == 'materias primas'){
		 				location.href="/productos";
		 			}
		 			else if (buscar == 'nueva materia prima'){
		 				location.href="/productos/nuevo";
		 			}
		 			else if (buscar == 'producciones'){
		 				location.href="/producciones";
		 			}
		 			else if (buscar == 'movimientos materia prima'){
		 				location.href="/movimientosMP";
		 			}
		 			else if (buscar == 'productos procesados'){
		 				location.href="/productosPRO";
		 			}
		 			else if (buscar == 'nuevo producto procesado'){
		 				location.href="/productosPRO/nuevo";
		 			}
		 			else if (buscar == 'lotes'){
		 				location.href="/lotes";
		 			}
		 			else if (buscar == 'movimientos productos procesados'){
		 				location.href="/movimientosPP";
		 			}
		 			else if (buscar == 'compras'){
		 				wlocation.href="/compras/";
		 			}
		 			else if (buscar == 'nueva compra'){
		 				location.href="/comprar/";
		 			}
		 			else if (buscar == 'ventas'){
		 				location.href="/ventas/";
		 			}
		 			else if (buscar == 'nueva venta'){
		 				location.href="/vender/";
		 			}
		 			else if (buscar == 'contacto clientes'){
		 				location.href="/contact";
		 			}
		 			else if (buscar == 'contacto usuarios'){
		 				location.href="/contact2";
		 			}
		 			else {
		 				location.href="/noEncontrado";
		 			}
		 		}




