package pe.mil.ejercito.sipr.model;

import java.io.Serializable;

public class JudicialAsignacion implements Serializable {
	private static final long	serialVersionUID	= 1L;
	private String				codAsig;
	private String				nombreApellido;
	private String				banco;
	private String				codLugar;
	private String				lugar;
	private String				dni;
	private String				fecProces;
	public String getCodAsig() {
		return codAsig;
	}
	public void setCodAsig(String codAsig) {
		this.codAsig = codAsig;
	}
	public String getNombreApellido() {
		return nombreApellido;
	}
	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getCodLugar() {
		return codLugar;
	}
	public void setCodLugar(String codLugar) {
		this.codLugar = codLugar;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getFecProces() {
		return fecProces;
	}
	public void setFecProces(String fecProces) {
		this.fecProces = fecProces;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
