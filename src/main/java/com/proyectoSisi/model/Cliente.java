package com.proyectoSisi.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity

@Table(name="clientes")
public class Cliente implements Serializable{
	

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "DocCliente", nullable = false, length = 30, unique = true)
	private String DocCliente;
	@Column(name = "TipoDoc", length = 50)
	private String TipoDoc;
	@Column(name = "nombres", length = 50)
	private String nombres;
	@Column(name = "apellidos", length = 50)
	private String apellidos;
	@Column(name = "telefono", length = 20)
	private String telefono;
	@Column(name = "correo", length = 50)
	private String correo;


	
	@OneToMany(mappedBy="cliente")
	private List<Venta> idClient;
	

	
	public Cliente() {
		super();
	}



	public Cliente(Integer id, String docCliente, String tipoDoc, String nombres, String apellidos, String telefono,
			String correo) {
		super();
		this.id = id;
		DocCliente = docCliente;
		TipoDoc = tipoDoc;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.correo = correo;

	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getDocCliente() {
		return DocCliente;
	}



	public void setDocCliente(String docCliente) {
		DocCliente = docCliente;
	}



	public String getTipoDoc() {
		return TipoDoc;
	}



	public void setTipoDoc(String tipoDoc) {
		TipoDoc = tipoDoc;
	}



	public String getNombres() {
		return nombres;
	}



	public void setNombres(String nombres) {
		this.nombres = nombres;
	}



	public String getApellidos() {
		return apellidos;
	}



	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}



	public String getTelefono() {
		return telefono;
	}



	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	public String getCorreo() {
		return correo;
	}



	public void setCorreo(String correo) {
		this.correo = correo;
	}



	@Override
	public String toString() {
		return DocCliente;
	}

	public String getNombreCompletoCliente() {
		return this.nombres != null && this.apellidos !=null ?
				this.nombres + " " + this.apellidos: "-----";
	}

	
	



}