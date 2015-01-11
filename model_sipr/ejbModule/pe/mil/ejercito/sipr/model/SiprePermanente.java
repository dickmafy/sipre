package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_PERMANENTE")
@NamedQueries({ @NamedQuery(name = "SiprePermanente.findAll", query = "SELECT s FROM SiprePermanente s") })
public class SiprePermanente implements Serializable {
	private static final long	serialVersionUID	= 1L;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Id
	@Basic(optional = false)
	@NotNull
	@Column(name = "CODIGO_PERMANENTE")
	private BigDecimal			codigoPermanente;
	@Size(max = 20)
	private String				cip;

	private Long				monto;

	@Size(max = 20)
	private String				concepto;

	public SiprePermanente() {
	}

	public SiprePermanente(BigDecimal codigoPermanente) {
		this.codigoPermanente = codigoPermanente;
	}

	public BigDecimal getcodigoPermanente() {
		return codigoPermanente;
	}

	public void setcodigoPermanente(BigDecimal codigoPermanente) {
		this.codigoPermanente = codigoPermanente;
	}

	public String getCip() {
		return cip;
	}

	public void setCip(String cip) {
		this.cip = cip;
	}

	public Long getMonto() {
		return monto;
	}

	public void setMonto(Long monto) {
		this.monto = monto;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (codigoPermanente != null ? codigoPermanente.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof SiprePermanente)) {
			return false;
		}
		SiprePermanente other = (SiprePermanente) object;
		if ((this.codigoPermanente == null && other.codigoPermanente != null)
				|| (this.codigoPermanente != null && !this.codigoPermanente.equals(other.codigoPermanente))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.mil.ejercito.sipr.model.SiprePerma[ codigoPermanente=" + codigoPermanente + " ]";
	}

}
