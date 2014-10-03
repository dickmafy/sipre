package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SIPRE_UNIDAD database table.
 * 
 */
@Entity
@Table(name="SIPRE_UNIDAD")
@NamedQuery(name="SipreUnidad.findAll", query="SELECT s FROM SipreUnidad s")
public class SipreUnidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CUNIDAD_CODIGO")
	private String cunidadCodigo;

	@Column(name="CUNIDAD_ESTADO")
	private String cunidadEstado;

	@Column(name="VUNIDAD_DSC_CORTA")
	private String vunidadDscCorta;

	@Column(name="VUNIDAD_DSC_LARGA")
	private String vunidadDscLarga;

	//bi-directional many-to-one association to SiprePersona
	@OneToMany(mappedBy="sipreUnidad")
	private List<SiprePersona> siprePersonas;

	//bi-directional many-to-one association to SipreActividad
	@ManyToOne
	@JoinColumn(name="CACTIVIDAD_CODIGO")
	private SipreActividad sipreActividad;

	//bi-directional many-to-one association to SipreNucleo
	@ManyToOne
	@JoinColumn(name="CNUCLEO_CODIGO")
	private SipreNucleo sipreNucleo;

	public SipreUnidad() {
	}

	public String getCunidadCodigo() {
		return this.cunidadCodigo;
	}

	public void setCunidadCodigo(String cunidadCodigo) {
		this.cunidadCodigo = cunidadCodigo;
	}

	public String getCunidadEstado() {
		return this.cunidadEstado;
	}

	public void setCunidadEstado(String cunidadEstado) {
		this.cunidadEstado = cunidadEstado;
	}

	public String getVunidadDscCorta() {
		return this.vunidadDscCorta;
	}

	public void setVunidadDscCorta(String vunidadDscCorta) {
		this.vunidadDscCorta = vunidadDscCorta;
	}

	public String getVunidadDscLarga() {
		return this.vunidadDscLarga;
	}

	public void setVunidadDscLarga(String vunidadDscLarga) {
		this.vunidadDscLarga = vunidadDscLarga;
	}

	public List<SiprePersona> getSiprePersonas() {
		return this.siprePersonas;
	}

	public void setSiprePersonas(List<SiprePersona> siprePersonas) {
		this.siprePersonas = siprePersonas;
	}

	public SiprePersona addSiprePersona(SiprePersona siprePersona) {
		getSiprePersonas().add(siprePersona);
		siprePersona.setSipreUnidad(this);

		return siprePersona;
	}

	public SiprePersona removeSiprePersona(SiprePersona siprePersona) {
		getSiprePersonas().remove(siprePersona);
		siprePersona.setSipreUnidad(null);

		return siprePersona;
	}

	public SipreActividad getSipreActividad() {
		return this.sipreActividad;
	}

	public void setSipreActividad(SipreActividad sipreActividad) {
		this.sipreActividad = sipreActividad;
	}

	public SipreNucleo getSipreNucleo() {
		return this.sipreNucleo;
	}

	public void setSipreNucleo(SipreNucleo sipreNucleo) {
		this.sipreNucleo = sipreNucleo;
	}

}