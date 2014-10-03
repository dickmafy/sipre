package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SIPRE_CONCEPTO_DESCUENTO database table.
 * 
 */
@Entity
@Table(name="SIPRE_CONCEPTO_DESCUENTO")
@NamedQuery(name="SipreConceptoDescuento.findAll", query="SELECT s FROM SipreConceptoDescuento s")
public class SipreConceptoDescuento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CCD_CODIGO")
	private String ccdCodigo;

	@Column(name="VCD_DSC")
	private String vcdDsc;

	//bi-directional many-to-one association to SipreConceptoDescuentoLey
	@OneToMany(mappedBy="sipreConceptoDescuento")
	private List<SipreConceptoDescuentoLey> sipreConceptoDescuentoLeys;

	//bi-directional many-to-many association to SipreConceptoIngreso
	@ManyToMany(mappedBy="sipreConceptoDescuentos")
	private List<SipreConceptoIngreso> sipreConceptoIngresos;

	public SipreConceptoDescuento() {
	}

	public String getCcdCodigo() {
		return this.ccdCodigo;
	}

	public void setCcdCodigo(String ccdCodigo) {
		this.ccdCodigo = ccdCodigo;
	}

	public String getVcdDsc() {
		return this.vcdDsc;
	}

	public void setVcdDsc(String vcdDsc) {
		this.vcdDsc = vcdDsc;
	}

	public List<SipreConceptoDescuentoLey> getSipreConceptoDescuentoLeys() {
		return this.sipreConceptoDescuentoLeys;
	}

	public void setSipreConceptoDescuentoLeys(List<SipreConceptoDescuentoLey> sipreConceptoDescuentoLeys) {
		this.sipreConceptoDescuentoLeys = sipreConceptoDescuentoLeys;
	}

	public SipreConceptoDescuentoLey addSipreConceptoDescuentoLey(SipreConceptoDescuentoLey sipreConceptoDescuentoLey) {
		getSipreConceptoDescuentoLeys().add(sipreConceptoDescuentoLey);
		sipreConceptoDescuentoLey.setSipreConceptoDescuento(this);

		return sipreConceptoDescuentoLey;
	}

	public SipreConceptoDescuentoLey removeSipreConceptoDescuentoLey(SipreConceptoDescuentoLey sipreConceptoDescuentoLey) {
		getSipreConceptoDescuentoLeys().remove(sipreConceptoDescuentoLey);
		sipreConceptoDescuentoLey.setSipreConceptoDescuento(null);

		return sipreConceptoDescuentoLey;
	}

	public List<SipreConceptoIngreso> getSipreConceptoIngresos() {
		return this.sipreConceptoIngresos;
	}

	public void setSipreConceptoIngresos(List<SipreConceptoIngreso> sipreConceptoIngresos) {
		this.sipreConceptoIngresos = sipreConceptoIngresos;
	}

}