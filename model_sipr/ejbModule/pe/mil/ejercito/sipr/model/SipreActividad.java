package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SIPRE_ACTIVIDAD database table.
 * 
 */
@Entity
@Table(name="SIPRE_ACTIVIDAD")
@NamedQuery(name="SipreActividad.findAll", query="SELECT s FROM SipreActividad s")
public class SipreActividad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CACTIVIDAD_CODIGO")
	private String cactividadCodigo;

	@Column(name="CACTIVIDAD_ESTADO")
	private String cactividadEstado;

	@Column(name="VACTIVIDAD_DSC_CORTA")
	private String vactividadDscCorta;

	@Column(name="VACTIVIDAD_DSC_LARGA")
	private String vactividadDscLarga;

	//bi-directional many-to-one association to SipreNucleo
	@ManyToOne
	@JoinColumn(name="CNUCLEO_CODIGO",insertable = false, updatable = false)
	private SipreNucleo sipreNucleo;

	//bi-directional many-to-one association to SipreUnidad
	@OneToMany(mappedBy="sipreActividad")
	private List<SipreUnidad> sipreUnidads;

	public SipreActividad() {
	}

	public String getCactividadCodigo() {
		return this.cactividadCodigo;
	}

	public void setCactividadCodigo(String cactividadCodigo) {
		this.cactividadCodigo = cactividadCodigo;
	}

	public String getCactividadEstado() {
		return this.cactividadEstado;
	}

	public void setCactividadEstado(String cactividadEstado) {
		this.cactividadEstado = cactividadEstado;
	}

	public String getVactividadDscCorta() {
		return this.vactividadDscCorta;
	}

	public void setVactividadDscCorta(String vactividadDscCorta) {
		this.vactividadDscCorta = vactividadDscCorta;
	}

	public String getVactividadDscLarga() {
		return this.vactividadDscLarga;
	}

	public void setVactividadDscLarga(String vactividadDscLarga) {
		this.vactividadDscLarga = vactividadDscLarga;
	}

	public SipreNucleo getSipreNucleo() {
		return this.sipreNucleo;
	}

	public void setSipreNucleo(SipreNucleo sipreNucleo) {
		this.sipreNucleo = sipreNucleo;
	}

	public List<SipreUnidad> getSipreUnidads() {
		return this.sipreUnidads;
	}

	public void setSipreUnidads(List<SipreUnidad> sipreUnidads) {
		this.sipreUnidads = sipreUnidads;
	}

	public SipreUnidad addSipreUnidad(SipreUnidad sipreUnidad) {
		getSipreUnidads().add(sipreUnidad);
		sipreUnidad.setSipreActividad(this);

		return sipreUnidad;
	}

	public SipreUnidad removeSipreUnidad(SipreUnidad sipreUnidad) {
		getSipreUnidads().remove(sipreUnidad);
		sipreUnidad.setSipreActividad(null);

		return sipreUnidad;
	}

}