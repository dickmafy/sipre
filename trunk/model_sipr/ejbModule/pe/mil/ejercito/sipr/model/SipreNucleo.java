package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SIPRE_NUCLEO database table.
 * 
 */
@Entity
@Table(name="SIPRE_NUCLEO")
@NamedQuery(name="SipreNucleo.findAll", query="SELECT s FROM SipreNucleo s")
public class SipreNucleo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CNUCLEO_CODIGO")
	private String cnucleoCodigo;

	@Column(name="CNUCLEO_ESTADO")
	private String cnucleoEstado;

	@Column(name="VNUCLEO_DSC_CORTA")
	private String vnucleoDscCorta;

	@Column(name="VNUCLEO_DSC_LARGA")
	private String vnucleoDscLarga;

	//bi-directional many-to-one association to SipreActividad
	@OneToMany(mappedBy="sipreNucleo")
	private List<SipreActividad> sipreActividads;

	//bi-directional many-to-one association to SipreUnidad
	@OneToMany(mappedBy="sipreNucleo")
	private List<SipreUnidad> sipreUnidads;

	public SipreNucleo() {
	}

	public String getCnucleoCodigo() {
		return this.cnucleoCodigo;
	}

	public void setCnucleoCodigo(String cnucleoCodigo) {
		this.cnucleoCodigo = cnucleoCodigo;
	}

	public String getCnucleoEstado() {
		return this.cnucleoEstado;
	}

	public void setCnucleoEstado(String cnucleoEstado) {
		this.cnucleoEstado = cnucleoEstado;
	}

	public String getVnucleoDscCorta() {
		return this.vnucleoDscCorta;
	}

	public void setVnucleoDscCorta(String vnucleoDscCorta) {
		this.vnucleoDscCorta = vnucleoDscCorta;
	}

	public String getVnucleoDscLarga() {
		return this.vnucleoDscLarga;
	}

	public void setVnucleoDscLarga(String vnucleoDscLarga) {
		this.vnucleoDscLarga = vnucleoDscLarga;
	}

	public List<SipreActividad> getSipreActividads() {
		return this.sipreActividads;
	}

	public void setSipreActividads(List<SipreActividad> sipreActividads) {
		this.sipreActividads = sipreActividads;
	}

	public SipreActividad addSipreActividad(SipreActividad sipreActividad) {
		getSipreActividads().add(sipreActividad);
		sipreActividad.setSipreNucleo(this);

		return sipreActividad;
	}

	public SipreActividad removeSipreActividad(SipreActividad sipreActividad) {
		getSipreActividads().remove(sipreActividad);
		sipreActividad.setSipreNucleo(null);

		return sipreActividad;
	}

	public List<SipreUnidad> getSipreUnidads() {
		return this.sipreUnidads;
	}

	public void setSipreUnidads(List<SipreUnidad> sipreUnidads) {
		this.sipreUnidads = sipreUnidads;
	}

	public SipreUnidad addSipreUnidad(SipreUnidad sipreUnidad) {
		getSipreUnidads().add(sipreUnidad);
		sipreUnidad.setSipreNucleo(this);

		return sipreUnidad;
	}

	public SipreUnidad removeSipreUnidad(SipreUnidad sipreUnidad) {
		getSipreUnidads().remove(sipreUnidad);
		sipreUnidad.setSipreNucleo(null);

		return sipreUnidad;
	}

}