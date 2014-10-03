package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the SIPRE_TMP_BONIFICACION database table.
 * 
 */
@Entity
@Table(name = "SIPRE_TMP_BONIFICACION")
@NamedQuery(name = "SipreTmpBonificacion.findAll", query = "SELECT s FROM SipreTmpBonificacion s")
public class SipreTmpBonificacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SipreTmpBonificacionPK id;

	@Column(name = "CTB_CON_REAL")
	private String ctbConReal;

	@Column(name = "CTB_IND_SITUACION")
	private String ctbIndSituacion;

	@Column(name = "NTB_MONTO")
	private BigDecimal ntbMonto;

	@Column(name = "NTB_MTO_REAL")
	private BigDecimal ntbMtoReal;

	@Column(name = "VTB_APE_NOM")
	private String vtbApeNom;

	// bi-directional many-to-one association to SipreConceptoIngreso
	@ManyToOne
	@JoinColumn(name = "CCI_CODIGO", insertable = false, updatable = false)
	private SipreConceptoIngreso sipreConceptoIngreso;

	// bi-directional many-to-one association to SiprePersona
	@ManyToOne
	@JoinColumn(name = "CPERSONA_NRO_ADM", insertable = false, updatable = false)
	private SiprePersona siprePersona;

	public SipreTmpBonificacion() {
	}

	public SipreTmpBonificacionPK getId() {
		return this.id;
	}

	public void setId(SipreTmpBonificacionPK id) {
		this.id = id;
	}

	public String getCtbConReal() {
		return this.ctbConReal;
	}

	public void setCtbConReal(String ctbConReal) {
		this.ctbConReal = ctbConReal;
	}

	public String getCtbIndSituacion() {
		return this.ctbIndSituacion;
	}

	public void setCtbIndSituacion(String ctbIndSituacion) {
		this.ctbIndSituacion = ctbIndSituacion;
	}

	public BigDecimal getNtbMonto() {
		return this.ntbMonto;
	}

	public void setNtbMonto(BigDecimal ntbMonto) {
		this.ntbMonto = ntbMonto;
	}

	public BigDecimal getNtbMtoReal() {
		return this.ntbMtoReal;
	}

	public void setNtbMtoReal(BigDecimal ntbMtoReal) {
		this.ntbMtoReal = ntbMtoReal;
	}

	public String getVtbApeNom() {
		return this.vtbApeNom;
	}

	public void setVtbApeNom(String vtbApeNom) {
		this.vtbApeNom = vtbApeNom;
	}

	public SipreConceptoIngreso getSipreConceptoIngreso() {
		return this.sipreConceptoIngreso;
	}

	public void setSipreConceptoIngreso(
			SipreConceptoIngreso sipreConceptoIngreso) {
		this.sipreConceptoIngreso = sipreConceptoIngreso;
	}

	public SiprePersona getSiprePersona() {
		return this.siprePersona;
	}

	public void setSiprePersona(SiprePersona siprePersona) {
		this.siprePersona = siprePersona;
	}

}