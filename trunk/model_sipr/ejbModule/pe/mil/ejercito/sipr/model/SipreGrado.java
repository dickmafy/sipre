package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SIPRE_GRADO database table.
 * 
 */
@Entity
@Table(name="SIPRE_GRADO")
@NamedQuery(name="SipreGrado.findAll", query="SELECT s FROM SipreGrado s")
public class SipreGrado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CGRADO_CODIGO")
	private String cgradoCodigo;

	@Column(name="VGRADO_DSC_CORTA")
	private String vgradoDscCorta;

	@Column(name="VGRADO_DSC_LARGA")
	private String vgradoDscLarga;

	//bi-directional many-to-one association to SipreGrupoGrado
	@ManyToOne
	@JoinColumn(name="CGG_CODIGO",insertable = false, updatable = false)
	private SipreGrupoGrado sipreGrupoGrado;

	//bi-directional many-to-one association to SipreIngresoGrado
	@OneToMany(mappedBy="sipreGrado")
	private List<SipreIngresoGrado> sipreIngresoGrados;

	//bi-directional many-to-one association to SiprePersona
	@OneToMany(mappedBy="sipreGrado")
	private List<SiprePersona> siprePersonas;

	public SipreGrado() {
	}

	public String getCgradoCodigo() {
		return this.cgradoCodigo;
	}

	public void setCgradoCodigo(String cgradoCodigo) {
		this.cgradoCodigo = cgradoCodigo;
	}

	public String getVgradoDscCorta() {
		return this.vgradoDscCorta;
	}

	public void setVgradoDscCorta(String vgradoDscCorta) {
		this.vgradoDscCorta = vgradoDscCorta;
	}

	public String getVgradoDscLarga() {
		return this.vgradoDscLarga;
	}

	public void setVgradoDscLarga(String vgradoDscLarga) {
		this.vgradoDscLarga = vgradoDscLarga;
	}

	public SipreGrupoGrado getSipreGrupoGrado() {
		return this.sipreGrupoGrado;
	}

	public void setSipreGrupoGrado(SipreGrupoGrado sipreGrupoGrado) {
		this.sipreGrupoGrado = sipreGrupoGrado;
	}

	public List<SipreIngresoGrado> getSipreIngresoGrados() {
		return this.sipreIngresoGrados;
	}

	public void setSipreIngresoGrados(List<SipreIngresoGrado> sipreIngresoGrados) {
		this.sipreIngresoGrados = sipreIngresoGrados;
	}

	public SipreIngresoGrado addSipreIngresoGrado(SipreIngresoGrado sipreIngresoGrado) {
		getSipreIngresoGrados().add(sipreIngresoGrado);
		sipreIngresoGrado.setSipreGrado(this);

		return sipreIngresoGrado;
	}

	public SipreIngresoGrado removeSipreIngresoGrado(SipreIngresoGrado sipreIngresoGrado) {
		getSipreIngresoGrados().remove(sipreIngresoGrado);
		sipreIngresoGrado.setSipreGrado(null);

		return sipreIngresoGrado;
	}

	public List<SiprePersona> getSiprePersonas() {
		return this.siprePersonas;
	}

	public void setSiprePersonas(List<SiprePersona> siprePersonas) {
		this.siprePersonas = siprePersonas;
	}

	public SiprePersona addSiprePersona(SiprePersona siprePersona) {
		getSiprePersonas().add(siprePersona);
		siprePersona.setSipreGrado(this);

		return siprePersona;
	}

	public SiprePersona removeSiprePersona(SiprePersona siprePersona) {
		getSiprePersonas().remove(siprePersona);
		siprePersona.setSipreGrado(null);

		return siprePersona;
	}

}