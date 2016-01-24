package com.codizer.pojo;

import java.sql.Timestamp;

/**
 * 
 * @author Adrian
 *
 */
public class Contacto {

	private int id;
	private String nombre;
	private String apellidos;
	private String empresa;

	private String telefono;
	private String celular;
	private String correo;
	private String url;

	private Timestamp fNacimiento;
	private String redSocial;
	
	// Dirección
	private String calle;
	private int num;
	private int cp;
	private String ciudad;
	private String estado;

	private String nota;

	/**
	 * Obtener el id de un Contacto
	 * @return id de tipo Int
	 */
	public int getId() {
		return id;
	}

	/**
	 * Asignar el id de un Contacto
	 * @param id de tipo Int
	 */
	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getfNacimiento() {
		return fNacimiento;
	}

	public void setfNacimiento(Timestamp fNacimiento) {
		this.fNacimiento = fNacimiento;
	}

	public String getRedSocial() {
		return redSocial;
	}

	public void setRedSocial(String redSocial) {
		this.redSocial = redSocial;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota; 
	}

	/**
	 * Retorna la información de un objeto Contacto
	 * 
	 * @return Información Contacto de tipo String
	 */
	@Override
	public String toString() {
		return "Contacto [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", empresa=" + empresa
				+ ", telefono=" + telefono + ", celular=" + celular + ", correo=" + correo + ", url=" + url + ", calle="
				+ calle + ", num=" + num + ", cp=" + cp + ", ciudad=" + ciudad + ", estado=" + estado + ", fNacimiento="
				+ fNacimiento + ", redSocial=" + redSocial + ", nota=" + nota + "]";
	}
	
	

	
}
