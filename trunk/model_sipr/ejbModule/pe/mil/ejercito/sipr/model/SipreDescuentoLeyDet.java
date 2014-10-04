package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SIPRE_DESCUENTO_LEY_DET database table.
 * 
 */
@Entity
@Table(name="SIPRE_DESCUENTO_LEY_DET")
@NamedQuery(name="SipreDescuentoLeyDet.findAll", query="SELECT s FROM SipreDescuentoLeyDet s")
public class SipreDescuentoLeyDet implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SipreDescuentoLeyDetPK id;

	@Column(name="VDLD_DSC")
	private String vdldDsc;

	//bi-directional many-to-one association to SipreConceptoDescuentoLey
	@OneToMany(mappedBy="sipreDescuentoLeyDet")
	private List<SipreConceptoDescuentoLey> sipreConceptoDescuentoLeys;

	//bi-directional many-to-one association to SipreDescuentoLey
	@ManyToOne
	@JoinColumn(name="CDL_CODIGO",insertable = false, updatable = false)
	private SipreDescuentoLey sipreDescuentoLey;

	public SipreDescuentoLeyDet() {
	}

	public SipreDescuentoLeyDetPK getId() {
		return this.id;
	}

	public void setId(SipreDescuentoLeyDetPK id) {
		this.id = id;
	}

	public String getVdldDsc() {
		return this.vdldDsc;
	}

	public void setVdldDsc(String vdldDsc) {
		this.vdldDsc = vdldDsc;
	}

	public List<SipreConceptoDescuentoLey> getSipreConceptoDescuentoLeys() {
		return this.sipreConceptoDescuentoLeys;
	}

	public void setSipreConceptoDescuentoLeys(List<SipreConceptoDescuentoLey> sipreConceptoDescuentoLeys) {
		this.sipreConceptoDescuentoLeys = sipreConceptoDescuentoLeys;
	}

	public SipreConceptoDescuentoLey addSipreConceptoDescuentoLey(SipreConceptoDescuentoLey sipreConceptoDescuentoLey) {
		getSipreConceptoDescuentoLeys().add(sipreConceptoDescuentoLey);
		sipreConceptoDescuentoLey.setSipreDescuentoLeyDet(this);

		return sipreConceptoDescuentoLey;
	}

	public SipreConceptoDescuentoLey removeSipreConceptoDescuentoLey(SipreConceptoDescuentoLey sipreConceptoDescuentoLey) {
		getSipreConceptoDescuentoLeys().remove(sipreConceptoDescuentoLey);
		sipreConceptoDescuentoLey.setSipreDescuentoLeyDet(null);

		return sipreConceptoDescuentoLey;
	}

	public SipreDescuentoLey getSipreDescuentoLey() {
		return this.sipreDescuentoLey;
	}

	public void setSipreDescuentoLey(SipreDescuentoLey sipreDescuentoLey) {
		this.sipreDescuentoLey = sipreDescuentoLey;
	}

}