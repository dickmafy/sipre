package pe.mil.ejercito.sipr.model;

import java.io.Serializable;

public class CrediticiaConsulta implements Serializable {
    private static final long serialVersionUID = 1L;
	private String				nroAutorizacion;
	private String				codentidad;
	private String				razonSocial;
	private String				fechaAlta;
	private String				fechaBaja;
	private String				situacionAuto;
	private String				codCajaMilitar;
	private String				codDscCaja;

	public String getNroAutorizacion() {
		return nroAutorizacion;
	}

	public void setNroAutorizacion(String nroAutorizacion) {
		this.nroAutorizacion = nroAutorizacion;
	}

	public String getCodentidad() {
		return codentidad;
	}

	public void setCodentidad(String codentidad) {
		this.codentidad = codentidad;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getSituacionAuto() {
		return situacionAuto;
	}

	public void setSituacionAuto(String situacionAuto) {
		this.situacionAuto = situacionAuto;
	}

	public String getCodCajaMilitar() {
		return codCajaMilitar;
	}

	public void setCodCajaMilitar(String codCajaMilitar) {
		this.codCajaMilitar = codCajaMilitar;
	}

	public String getCodDscCaja() {
		return codDscCaja;
	}

	public void setCodDscCaja(String codDscCaja) {
		this.codDscCaja = codDscCaja;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
