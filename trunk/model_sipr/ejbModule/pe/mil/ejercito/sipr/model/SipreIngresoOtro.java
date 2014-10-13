package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_INGRESO_OTRO")
@XmlRootElement
@NamedQueries({
		@NamedQuery(name = "SipreIngresoOtro.findAll", query = "SELECT s FROM SipreIngresoOtro s"),
		@NamedQuery(name = "SipreIngresoOtro.findByNioMto", query = "SELECT s FROM SipreIngresoOtro s WHERE s.nioMto = :nioMto"),
		@NamedQuery(name = "SipreIngresoOtro.findByCioUsuMod", query = "SELECT s FROM SipreIngresoOtro s WHERE s.cioUsuMod = :cioUsuMod"),
		@NamedQuery(name = "SipreIngresoOtro.findByDioFecReg", query = "SELECT s FROM SipreIngresoOtro s WHERE s.dioFecReg = :dioFecReg"),
		@NamedQuery(name = "SipreIngresoOtro.findByDioFecMod", query = "SELECT s FROM SipreIngresoOtro s WHERE s.dioFecMod = :dioFecMod"),
		@NamedQuery(name = "SipreIngresoOtro.findByCciCodigo", query = "SELECT s FROM SipreIngresoOtro s WHERE s.id.cciCodigo = :cciCodigo"),
		@NamedQuery(name = "SipreIngresoOtro.findByCpersonaNroAdm", query = "SELECT s FROM SipreIngresoOtro s WHERE s.id.cpersonaNroAdm = :cpersonaNroAdm") })
public class SipreIngresoOtro implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	protected SipreIngresoOtroPK id;

	@Column(name = "NIO_MTO")
	private BigDecimal nioMto;

	@Size(max = 9)
	@Column(name = "CIO_USU_MOD")
	private String cioUsuMod;

	@Column(name = "DIO_FEC_REG")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dioFecReg;

	@Column(name = "DIO_FEC_MOD")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dioFecMod;

	@JoinColumn(name = "CPERSONA_NRO_ADM", referencedColumnName = "CPERSONA_NRO_ADM", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private SiprePersona siprePersona;

	@JoinColumn(name = "CCI_CODIGO", referencedColumnName = "CCI_CODIGO", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private SipreConceptoIngreso sipreConceptoIngreso;

		
	public SipreIngresoOtro() {
	}

	public SipreIngresoOtro(SipreIngresoOtroPK sipreIngresoOtroPK) {
		this.id = sipreIngresoOtroPK;
	}

	public SipreIngresoOtro(String cciCodigo, String cpersonaNroAdm) {
		this.id = new SipreIngresoOtroPK(cciCodigo, cpersonaNroAdm);
	}

	public SipreIngresoOtroPK getId() {
		return id;
	}

	public void setId(SipreIngresoOtroPK id) {
		this.id = id;
	}

	public BigDecimal getNioMto() {
		return nioMto;
	}

	public void setNioMto(BigDecimal nioMto) {
		this.nioMto = nioMto;
	}

	public String getCioUsuMod() {
		return cioUsuMod;
	}

	public void setCioUsuMod(String cioUsuMod) {
		this.cioUsuMod = cioUsuMod;
	}

	public Date getDioFecReg() {
		return dioFecReg;
	}

	public void setDioFecReg(Date dioFecReg) {
		this.dioFecReg = dioFecReg;
	}

	public Date getDioFecMod() {
		return dioFecMod;
	}

	public void setDioFecMod(Date dioFecMod) {
		this.dioFecMod = dioFecMod;
	}

	public SiprePersona getSiprePersona() {
		return siprePersona;
	}

	public void setSiprePersona(SiprePersona siprePersona) {
		this.siprePersona = siprePersona;
	}

	public SipreConceptoIngreso getSipreConceptoIngreso() {
		return sipreConceptoIngreso;
	}

	public void setSipreConceptoIngreso(
			SipreConceptoIngreso sipreConceptoIngreso) {
		this.sipreConceptoIngreso = sipreConceptoIngreso;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof SipreIngresoOtro)) {
			return false;
		}
		SipreIngresoOtro other = (SipreIngresoOtro) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "SIPRE.SipreIngresoOtro[ sipreIngresoOtroPK=" + id + " ]";
	}

}
