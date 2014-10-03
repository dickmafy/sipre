package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SIPRE_ENTIDAD_CREDITICIA database table.
 * 
 */
@Entity
@Table(name="SIPRE_ENTIDAD_CREDITICIA")
@NamedQuery(name="SipreEntidadCrediticia.findAll", query="SELECT s FROM SipreEntidadCrediticia s")
public class SipreEntidadCrediticia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CEC_CODIGO")
	private String cecCodigo;

	@Column(name="CEC_IND_CLAS")
	private String cecIndClas;

	@Column(name="CEC_IND_PRIO")
	private String cecIndPrio;

	@Column(name="CEC_TIPO_DCTO")
	private String cecTipoDcto;

	@Column(name="VEC_DESC")
	private String vecDesc;

	//bi-directional many-to-one association to SipreConceptoDescuentoLey
	@OneToMany(mappedBy="sipreEntidadCrediticia")
	private List<SipreConceptoDescuentoLey> sipreConceptoDescuentoLeys;

	//bi-directional many-to-one association to SipreTmpEntidadCrediticia
	@OneToMany(mappedBy="sipreEntidadCrediticia")
	private List<SipreTmpEntidadCrediticia> sipreTmpEntidadCrediticias;

	public SipreEntidadCrediticia() {
	}

	public String getCecCodigo() {
		return this.cecCodigo;
	}

	public void setCecCodigo(String cecCodigo) {
		this.cecCodigo = cecCodigo;
	}

	public String getCecIndClas() {
		return this.cecIndClas;
	}

	public void setCecIndClas(String cecIndClas) {
		this.cecIndClas = cecIndClas;
	}

	public String getCecIndPrio() {
		return this.cecIndPrio;
	}

	public void setCecIndPrio(String cecIndPrio) {
		this.cecIndPrio = cecIndPrio;
	}

	public String getCecTipoDcto() {
		return this.cecTipoDcto;
	}

	public void setCecTipoDcto(String cecTipoDcto) {
		this.cecTipoDcto = cecTipoDcto;
	}

	public String getVecDesc() {
		return this.vecDesc;
	}

	public void setVecDesc(String vecDesc) {
		this.vecDesc = vecDesc;
	}

	public List<SipreConceptoDescuentoLey> getSipreConceptoDescuentoLeys() {
		return this.sipreConceptoDescuentoLeys;
	}

	public void setSipreConceptoDescuentoLeys(List<SipreConceptoDescuentoLey> sipreConceptoDescuentoLeys) {
		this.sipreConceptoDescuentoLeys = sipreConceptoDescuentoLeys;
	}

	public SipreConceptoDescuentoLey addSipreConceptoDescuentoLey(SipreConceptoDescuentoLey sipreConceptoDescuentoLey) {
		getSipreConceptoDescuentoLeys().add(sipreConceptoDescuentoLey);
		sipreConceptoDescuentoLey.setSipreEntidadCrediticia(this);

		return sipreConceptoDescuentoLey;
	}

	public SipreConceptoDescuentoLey removeSipreConceptoDescuentoLey(SipreConceptoDescuentoLey sipreConceptoDescuentoLey) {
		getSipreConceptoDescuentoLeys().remove(sipreConceptoDescuentoLey);
		sipreConceptoDescuentoLey.setSipreEntidadCrediticia(null);

		return sipreConceptoDescuentoLey;
	}

	public List<SipreTmpEntidadCrediticia> getSipreTmpEntidadCrediticias() {
		return this.sipreTmpEntidadCrediticias;
	}

	public void setSipreTmpEntidadCrediticias(List<SipreTmpEntidadCrediticia> sipreTmpEntidadCrediticias) {
		this.sipreTmpEntidadCrediticias = sipreTmpEntidadCrediticias;
	}

	public SipreTmpEntidadCrediticia addSipreTmpEntidadCrediticia(SipreTmpEntidadCrediticia sipreTmpEntidadCrediticia) {
		getSipreTmpEntidadCrediticias().add(sipreTmpEntidadCrediticia);
		sipreTmpEntidadCrediticia.setSipreEntidadCrediticia(this);

		return sipreTmpEntidadCrediticia;
	}

	public SipreTmpEntidadCrediticia removeSipreTmpEntidadCrediticia(SipreTmpEntidadCrediticia sipreTmpEntidadCrediticia) {
		getSipreTmpEntidadCrediticias().remove(sipreTmpEntidadCrediticia);
		sipreTmpEntidadCrediticia.setSipreEntidadCrediticia(null);

		return sipreTmpEntidadCrediticia;
	}

}