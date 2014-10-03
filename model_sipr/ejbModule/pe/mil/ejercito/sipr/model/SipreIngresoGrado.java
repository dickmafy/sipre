package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the SIPRE_INGRESO_GRADO database table.
 * 
 */
@Entity
@Table(name="SIPRE_INGRESO_GRADO")
@NamedQuery(name="SipreIngresoGrado.findAll", query="SELECT s FROM SipreIngresoGrado s")
public class SipreIngresoGrado implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SipreIngresoGradoPK id;

	@Column(name="CIG_IND_CALCULO")
	private String cigIndCalculo;

	@Column(name="CIG_IND_INGRESO")
	private String cigIndIngreso;

	@Column(name="CIG_IND_SITUACION")
	private String cigIndSituacion;

	@Column(name="NIG_MONTO")
	private BigDecimal nigMonto;

	//bi-directional many-to-one association to SipreConceptoIngreso
	@ManyToOne
	@JoinColumn(name="CCI_CODIGO")
	private SipreConceptoIngreso sipreConceptoIngreso;

	//bi-directional many-to-one association to SipreGrado
	@ManyToOne
	@JoinColumn(name="CGRADO_CODIGO")
	private SipreGrado sipreGrado;

	public SipreIngresoGrado() {
	}

	public SipreIngresoGradoPK getId() {
		return this.id;
	}

	public void setId(SipreIngresoGradoPK id) {
		this.id = id;
	}

	public String getCigIndCalculo() {
		return this.cigIndCalculo;
	}

	public void setCigIndCalculo(String cigIndCalculo) {
		this.cigIndCalculo = cigIndCalculo;
	}

	public String getCigIndIngreso() {
		return this.cigIndIngreso;
	}

	public void setCigIndIngreso(String cigIndIngreso) {
		this.cigIndIngreso = cigIndIngreso;
	}

	public String getCigIndSituacion() {
		return this.cigIndSituacion;
	}

	public void setCigIndSituacion(String cigIndSituacion) {
		this.cigIndSituacion = cigIndSituacion;
	}

	public BigDecimal getNigMonto() {
		return this.nigMonto;
	}

	public void setNigMonto(BigDecimal nigMonto) {
		this.nigMonto = nigMonto;
	}

	public SipreConceptoIngreso getSipreConceptoIngreso() {
		return this.sipreConceptoIngreso;
	}

	public void setSipreConceptoIngreso(SipreConceptoIngreso sipreConceptoIngreso) {
		this.sipreConceptoIngreso = sipreConceptoIngreso;
	}

	public SipreGrado getSipreGrado() {
		return this.sipreGrado;
	}

	public void setSipreGrado(SipreGrado sipreGrado) {
		this.sipreGrado = sipreGrado;
	}

}