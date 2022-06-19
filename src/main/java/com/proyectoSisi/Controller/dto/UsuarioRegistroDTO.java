package com.proyectoSisi.Controller.dto;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.proyectoSisi.model.Rol;

public class UsuarioRegistroDTO {

	private Long id;
	private String codUsuario;
	private String tipo_Documento;
	private String nombre;
	private String apellido;
	private String direccion;
	private String genero;
	private String telefono;
	private String email;
	private String password;
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(
			name = "usuarios_roles",
			joinColumns = @JoinColumn(name = "usuario_id",referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "id")
			)
	private Collection<Rol> roles;

	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getTipo_Documento() {
		return tipo_Documento;
	}

	public void setTipo_Documento(String tipo_Documento) {
		this.tipo_Documento = tipo_Documento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Rol> roles) {
		this.roles = roles;
	}

	

	public UsuarioRegistroDTO(String codUsuario, String tipo_Documento, String nombre, String apellido,
			String direccion, String genero, String telefono, String email, String password, Collection<Rol> roles) {
		super();
		this.codUsuario = codUsuario;
		this.tipo_Documento = tipo_Documento;
		this.nombre = nombre;
		this.apellido = apellido;
		this.direccion = direccion;
		this.genero = genero;
		this.telefono = telefono;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public UsuarioRegistroDTO() {

	}

}
