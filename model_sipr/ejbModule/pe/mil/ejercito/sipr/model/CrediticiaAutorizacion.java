package pe.mil.ejercito.sipr.model;

import java.io.Serializable;

public class CrediticiaAutorizacion implements Serializable {
	private static final long	serialVersionUID	= 1L;
	private String				codAdmin;
	private String				codCPMP;
	private String				nombre;
	private String				estado;
	private String				fechaRegistro;
	private String				personalCajaEjercito;
	private String				codRegimen;
	private String				codregimenDescripcion;
	private String				nroAutorizacion;

	public String getNroAutorizacion() {
		return nroAutorizacion;
	}

	public void setNroAutorizacion(String nroAutorizacion) {
		this.nroAutorizacion = nroAutorizacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCodAdmin() {
		return codAdmin;
	}

	public void setCodAdmin(String codAdmin) {
		this.codAdmin = codAdmin;
	}

	public String getCodCPMP() {
		return codCPMP;
	}

	public void setCodCPMP(String codCPMP) {
		this.codCPMP = codCPMP;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getPersonalCajaEjercito() {
		return personalCajaEjercito;
	}

	public void setPersonalCajaEjercito(String personalCajaEjercito) {
		this.personalCajaEjercito = personalCajaEjercito;
	}

	public String getCodRegimen() {
		return codRegimen;
	}

	public void setCodRegimen(String codRegimen) {
		this.codRegimen = codRegimen;
	}

	public String getCodregimenDescripcion() {
		return codregimenDescripcion;
	}

	public void setCodregimenDescripcion(String codregimenDescripcion) {
		this.codregimenDescripcion = codregimenDescripcion;
	}
}
