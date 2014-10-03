package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SIPRE_DESCUENTO_LEY database table.
 * 
 */
@Entity
@Table(name="SIPRE_DESCUENTO_LEY")
@NamedQuery(name="SipreDescuentoLey.findAll", query="SELECT s FROM SipreDescuentoLey s")
public class SipreDescuentoLey implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CDL_CODIGO")
	private String cdlCodigo;

	@Column(name="VDL_DSC")
	private String vdlDsc;

	//bi-directional many-to-one association to SipreDescuentoLeyDet
	@OneToMany(mappedBy="sipreDescuentoLey")
	private List<SipreDescuentoLeyDet> sipreDescuentoLeyDets;

	public SipreDescuentoLey() {
	}

	public String getCdlCodigo() {
		return this.cdlCodigo;
	}

	public void setCdlCodigo(String cdlCodigo) {
		this.cdlCodigo = cdlCodigo;
	}

	public String getVdlDsc() {
		return this.vdlDsc;
	}

	public void setVdlDsc(String vdlDsc) {
		this.vdlDsc = vdlDsc;
	}

	public List<SipreDescuentoLeyDet> getSipreDescuentoLeyDets() {
		return this.sipreDescuentoLeyDets;
	}

	public void setSipreDescuentoLeyDets(List<SipreDescuentoLeyDet> sipreDescuentoLeyDets) {
		this.sipreDescuentoLeyDets = sipreDescuentoLeyDets;
	}

	public SipreDescuentoLeyDet addSipreDescuentoLeyDet(SipreDescuentoLeyDet sipreDescuentoLeyDet) {
		getSipreDescuentoLeyDets().add(sipreDescuentoLeyDet);
		sipreDescuentoLeyDet.setSipreDescuentoLey(this);

		return sipreDescuentoLeyDet;
	}

	public SipreDescuentoLeyDet removeSipreDescuentoLeyDet(SipreDescuentoLeyDet sipreDescuentoLeyDet) {
		getSipreDescuentoLeyDets().remove(sipreDescuentoLeyDet);
		sipreDescuentoLeyDet.setSipreDescuentoLey(null);

		return sipreDescuentoLeyDet;
	}

}