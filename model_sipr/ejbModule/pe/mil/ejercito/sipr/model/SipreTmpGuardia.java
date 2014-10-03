package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;

/**
 * The persistent class for the SIPRE_TMP_GUARDIA database table.
 * 
 */
@Entity
@Table(name = "SIPRE_TMP_GUARDIA")
@NamedQuery(name = "SipreTmpGuardia.findAll", query = "SELECT s FROM SipreTmpGuardia s")
public class SipreTmpGuardia implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SipreTmpGuardiaPK id;

	@Column(name = "CTG_TIPO")
	private String ctgTipo;

	@Column(name = "NTG_MONTO")
	private BigDecimal ntgMonto;

	// bi-directional many-to-one association to SipreConceptoIngreso
	@ManyToOne
	@JoinColumn(name = "CCI_CODIGO", insertable = false, updatable = false)
	private SipreConceptoIngreso sipreConceptoIngreso;

	// bi-directional many-to-one association to SiprePersona
	@ManyToOne
	@JoinColumn(name = "CPERSONA_NRO_ADM", insertable = false, updatable = false)
	private SiprePersona siprePersona;

	public SipreTmpGuardia() {
	}

	public SipreTmpGuardiaPK getId() {
		return this.id;
	}

	public void setId(SipreTmpGuardiaPK id) {
		this.id = id;
	}

	public String getCtgTipo() {
		return this.ctgTipo;
	}

	public void setCtgTipo(String ctgTipo) {
		this.ctgTipo = ctgTipo;
	}

	public BigDecimal getNtgMonto() {
		return this.ntgMonto;
	}

	public void setNtgMonto(BigDecimal ntgMonto) {
		this.ntgMonto = ntgMonto;
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