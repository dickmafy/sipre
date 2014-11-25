package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_INGRESO_GRADO")
@NamedQueries({
		@NamedQuery(name = "SipreIngresoGrado.findAll", query = "SELECT s FROM SipreIngresoGrado s") })
public class SipreIngresoGrado implements Serializable {
	private static final long		serialVersionUID	= 1L;
	@EmbeddedId
	protected SipreIngresoGradoPK	sipreIngresoGradoPK;
	// @Max(value=?) @Min(value=?)//if you know range of your decimal fields
	// consider using these annotations to enforce field validation
	@Column(name = "NIG_MONTO")
	private BigDecimal				nigMonto;
	@Column(name = "CIG_IND_INGRESO")
	private String					cigIndIngreso;
	@Column(name = "CIG_IND_CALCULO")
	private String					cigIndCalculo;
	@Basic(optional = false)
	@Column(name = "CIG_IND_SITUACION")
	private String					cigIndSituacion;
	@JoinColumn(name = "CGRADO_CODIGO", referencedColumnName = "CGRADO_CODIGO", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private SipreGrado				sipreGrado;
	@JoinColumn(name = "CCI_CODIGO", referencedColumnName = "CCI_CODIGO", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private SipreConceptoIngreso	sipreConceptoIngreso;

	public SipreIngresoGrado() {
	}

	public SipreIngresoGrado(SipreIngresoGradoPK sipreIngresoGradoPK) {
		this.sipreIngresoGradoPK = sipreIngresoGradoPK;
	}

	public SipreIngresoGrado(SipreIngresoGradoPK sipreIngresoGradoPK, String cigIndSituacion) {
		this.sipreIngresoGradoPK = sipreIngresoGradoPK;
		this.cigIndSituacion = cigIndSituacion;
	}

	public SipreIngresoGrado(String cgradoCodigo, String cciCodigo) {
		this.sipreIngresoGradoPK = new SipreIngresoGradoPK(cgradoCodigo, cciCodigo);
	}

	public SipreIngresoGradoPK getSipreIngresoGradoPK() {
		return sipreIngresoGradoPK;
	}

	public void setSipreIngresoGradoPK(SipreIngresoGradoPK sipreIngresoGradoPK) {
		this.sipreIngresoGradoPK = sipreIngresoGradoPK;
	}

	public BigDecimal getNigMonto() {
		return nigMonto;
	}

	public void setNigMonto(BigDecimal nigMonto) {
		this.nigMonto = nigMonto;
	}

	public String getCigIndIngreso() {
		return cigIndIngreso;
	}

	public void setCigIndIngreso(String cigIndIngreso) {
		this.cigIndIngreso = cigIndIngreso;
	}

	public String getCigIndCalculo() {
		return cigIndCalculo;
	}

	public void setCigIndCalculo(String cigIndCalculo) {
		this.cigIndCalculo = cigIndCalculo;
	}

	public String getCigIndSituacion() {
		return cigIndSituacion;
	}

	public void setCigIndSituacion(String cigIndSituacion) {
		this.cigIndSituacion = cigIndSituacion;
	}

	public SipreGrado getSipreGrado() {
		return sipreGrado;
	}

	public void setSipreGrado(SipreGrado sipreGrado) {
		this.sipreGrado = sipreGrado;
	}

	public SipreConceptoIngreso getSipreConceptoIngreso() {
		return sipreConceptoIngreso;
	}

	public void setSipreConceptoIngreso(SipreConceptoIngreso sipreConceptoIngreso) {
		this.sipreConceptoIngreso = sipreConceptoIngreso;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (sipreIngresoGradoPK != null ? sipreIngresoGradoPK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof SipreIngresoGrado)) {
			return false;
		}
		SipreIngresoGrado other = (SipreIngresoGrado) object;
		if ((this.sipreIngresoGradoPK == null && other.sipreIngresoGradoPK != null)
				|| (this.sipreIngresoGradoPK != null && !this.sipreIngresoGradoPK.equals(other.sipreIngresoGradoPK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.mil.ejercito.sipr.model.SipreIngresoGrado[ sipreIngresoGradoPK=" + sipreIngresoGradoPK + " ]";
	}

}
