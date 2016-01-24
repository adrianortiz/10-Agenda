package modelo;

/**
 * 
 * @author Adrian
 *
 */
public class Contacto {
	
	private String nombre;
	private String apellidos;
	private String empresa;
	
	private String telefono;
	private String celular;
	private String correo;
	private String url;
	
	// Dirección
	private String calle;
	private String num;
	private String cp;
	private String ciudad;
	private String estado;
	
	private String fNacimiento;
	private String redSocial;
	
	private String nota;
	
	public Contacto(){}
	
	/**
	 * 
	 * @param nombre
	 * @param apellidos
	 * @param empresa
	 * @param telefono
	 * @param celular
	 * @param correo
	 * @param url
	 * @param calle
	 * @param num
	 * @param cp
	 * @param ciudad
	 * @param estado
	 * @param fNacimiento
	 * @param redSocial
	 * @param nota
	 */
	public Contacto(String nombre, String apellidos, String empresa, String telefono, String celular, String correo,
			String url, String calle, String num, String cp, String ciudad, String estado, String fNacimiento,
			String redSocial, String nota) {
		super();
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.empresa = empresa;
		this.telefono = telefono;
		this.celular = celular;
		this.correo = correo;
		this.url = url;
		this.calle = calle;
		this.num = num;
		this.cp = cp;
		this.ciudad = ciudad;
		this.estado = estado;
		this.fNacimiento = fNacimiento;
		this.redSocial = redSocial;
		this.nota = nota;
	}

	/**
	 * 
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * 
	 * @return
	 */
	public String getApellidos() {
		return apellidos;
	}

	/**
	 * 
	 * @param apellidos
	 */
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

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
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

	public String getfNacimiento() {
		return fNacimiento;
	}

	public void setfNacimiento(String fNacimiento) {
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

	@Override
	public String toString() {
		return "Contacto [nombre=" + nombre + ", apellidos=" + apellidos + ", empresa=" + empresa + ", telefono="
				+ telefono + ", celular=" + celular + ", correo=" + correo + ", url=" + url + ", calle=" + calle
				+ ", num=" + num + ", cp=" + cp + ", ciudad=" + ciudad + ", estado=" + estado + ", fNacimiento="
				+ fNacimiento + ", redSocial=" + redSocial + ", nota=" + nota + "]";
	}
	
	
	
}
