package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SIPRE_TMP_ENTIDAD_CREDITICIA database table.
 * 
 */
@Entity
@Table(name="SIPRE_TMP_ENTIDAD_CREDITICIA")
@NamedQuery(name="SipreTmpEntidadCrediticia.findAll", query="SELECT s FROM SipreTmpEntidadCrediticia s")
public class SipreTmpEntidadCrediticia implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SipreTmpEntidadCrediticiaPK id;

	@Column(name="NTEC_MONTO")
	private BigDecimal ntecMonto;

	@Column(name="NTEC_NRO_CUOTA")
	private BigDecimal ntecNroCuota;

	//bi-directional many-to-one association to SipreEntidadCrediticia
	@ManyToOne
	@JoinColumn(name="CEC_CODIGO")
	private SipreEntidadCrediticia sipreEntidadCrediticia;

	//bi-directional many-to-one association to SiprePersona
	@ManyToOne
	@JoinColumn(name="CPERSONA_NRO_ADM")
	private SiprePersona siprePersona;

	public SipreTmpEntidadCrediticia() {
	}

	public SipreTmpEntidadCrediticiaPK getId() {
		return this.id;
	}

	public void setId(SipreTmpEntidadCrediticiaPK id) {
		this.id = id;
	}

	public BigDecimal getNtecMonto() {
		return this.ntecMonto;
	}

	public void setNtecMonto(BigDecimal ntecMonto) {
		this.ntecMonto = ntecMonto;
	}

	public BigDecimal getNtecNroCuota() {
		return this.ntecNroCuota;
	}

	public void setNtecNroCuota(BigDecimal ntecNroCuota) {
		this.ntecNroCuota = ntecNroCuota;
	}

	public SipreEntidadCrediticia getSipreEntidadCrediticia() {
		return this.sipreEntidadCrediticia;
	}

	public void setSipreEntidadCrediticia(SipreEntidadCrediticia sipreEntidadCrediticia) {
		this.sipreEntidadCrediticia = sipreEntidadCrediticia;
	}

	public SiprePersona getSiprePersona() {
		return this.siprePersona;
	}

	public void setSiprePersona(SiprePersona siprePersona) {
		this.siprePersona = siprePersona;
	}

}