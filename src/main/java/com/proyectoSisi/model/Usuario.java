package com.proyectoSisi.model;

import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "codUsuario", unique = true)
	private String codUsuario;
	
	@Column(name = "tipo_Documento")
	private String tipo_Documento;
	
	@Column(name = "nombre")
	private String nombre;

	@Column(name = "apellido")
	private String apellido;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "genero")
	private String genero;
	
	@Column(name = "telefono")
	private String telefono;

	private String email;
	private String password;
	
	@Column(name = "reset_password_token", length = 30)
    private String resetPasswordToken;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(
			name = "usuarios_roles",
			joinColumns = @JoinColumn(name = "usuario_id",referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "id")
			)
	private Collection<Rol> roles;

	@OneToMany(mappedBy = "usuario")
	private List<Compra> Cod_Usuario;

	@OneToMany(mappedBy = "usuarioVenta")
	private List<Venta> cod_usuario;

	@OneToMany(mappedBy = "usuarioProduccion")
	private List<Produccion> idUsuario;

	@OneToMany(mappedBy = "userLote")
	private List<Lote> Id_usuario;
	
	
public Usuario() {
		
	}

	public Usuario(Long id, String codUsuario, String tipo_Documento, String nombre, String apellido, String direccion,
			String genero, String telefono, String email, String password, Collection<Rol> roles) {
		super();
		this.id = id;
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

	

	public Usuario(String codUsuario, String tipo_Documento, String nombre, String apellido, String direccion,
			String genero, String telefono, String email, String password, Collection<Rol> roles) {
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


	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	@Override
	public String toString() {
		return codUsuario;
	}
	
	public String getNombreCompletos() {
		return this.nombre + this.apellido != null ? this.nombre + " " + this.apellido : "---";
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	


}
