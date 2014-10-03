package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the SIPRE_BOLETA_CABECERA database table.
 * 
 */
@Entity
@Table(name="SIPRE_BOLETA_CABECERA")
@NamedQuery(name="SipreBoletaCabecera.findAll", query="SELECT s FROM SipreBoletaCabecera s")
public class SipreBoletaCabecera implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SipreBoletaCabeceraPK id;

	@Column(name="CBC_COD_GRA_EFEC")
	private String cbcCodGraEfec;

	@Column(name="CBC_COD_GRA_PENS")
	private String cbcCodGraPens;

	@Column(name="CBC_COD_UNIDAD")
	private String cbcCodUnidad;

	@Column(name="CBC_DNI")
	private String cbcDni;

	@Column(name="CBC_IND_ACT_PENS")
	private String cbcIndActPens;

	@Column(name="NBC_MTO_EGRESO")
	private BigDecimal nbcMtoEgreso;

	@Column(name="NBC_MTO_INGRESO")
	private BigDecimal nbcMtoIngreso;

	@Column(name="NBC_NUM_BOLETA")
	private BigDecimal nbcNumBoleta;

	@Column(name="TCB_TIP_PERSONA")
	private String tcbTipPersona;

	@Column(name="VBC_DES_BANCO")
	private String vbcDesBanco;

	@Column(name="VBC_DES_GRA_EFEC")
	private String vbcDesGraEfec;

	@Column(name="VBC_DES_GRA_PENS")
	private String vbcDesGraPens;

	@Column(name="VBC_DES_UNIDAD")
	private String vbcDesUnidad;

	@Column(name="VBC_LUGAR")
	private String vbcLugar;

	@Column(name="VBC_REG_PENS")
	private String vbcRegPens;

	@Column(name="VBC_REG_REMUN")
	private String vbcRegRemun;

	//bi-directional many-to-one association to SipreBoletaDetalle
	@OneToMany(mappedBy="sipreBoletaCabecera")
	private List<SipreBoletaDetalle> sipreBoletaDetalles;

	public SipreBoletaCabecera() {
	}

	public SipreBoletaCabeceraPK getId() {
		return this.id;
	}

	public void setId(SipreBoletaCabeceraPK id) {
		this.id = id;
	}

	public String getCbcCodGraEfec() {
		return this.cbcCodGraEfec;
	}

	public void setCbcCodGraEfec(String cbcCodGraEfec) {
		this.cbcCodGraEfec = cbcCodGraEfec;
	}

	public String getCbcCodGraPens() {
		return this.cbcCodGraPens;
	}

	public void setCbcCodGraPens(String cbcCodGraPens) {
		this.cbcCodGraPens = cbcCodGraPens;
	}

	public String getCbcCodUnidad() {
		return this.cbcCodUnidad;
	}

	public void setCbcCodUnidad(String cbcCodUnidad) {
		this.cbcCodUnidad = cbcCodUnidad;
	}

	public String getCbcDni() {
		return this.cbcDni;
	}

	public void setCbcDni(String cbcDni) {
		this.cbcDni = cbcDni;
	}

	public String getCbcIndActPens() {
		return this.cbcIndActPens;
	}

	public void setCbcIndActPens(String cbcIndActPens) {
		this.cbcIndActPens = cbcIndActPens;
	}

	public BigDecimal getNbcMtoEgreso() {
		return this.nbcMtoEgreso;
	}

	public void setNbcMtoEgreso(BigDecimal nbcMtoEgreso) {
		this.nbcMtoEgreso = nbcMtoEgreso;
	}

	public BigDecimal getNbcMtoIngreso() {
		return this.nbcMtoIngreso;
	}

	public void setNbcMtoIngreso(BigDecimal nbcMtoIngreso) {
		this.nbcMtoIngreso = nbcMtoIngreso;
	}

	public BigDecimal getNbcNumBoleta() {
		return this.nbcNumBoleta;
	}

	public void setNbcNumBoleta(BigDecimal nbcNumBoleta) {
		this.nbcNumBoleta = nbcNumBoleta;
	}

	public String getTcbTipPersona() {
		return this.tcbTipPersona;
	}

	public void setTcbTipPersona(String tcbTipPersona) {
		this.tcbTipPersona = tcbTipPersona;
	}

	public String getVbcDesBanco() {
		return this.vbcDesBanco;
	}

	public void setVbcDesBanco(String vbcDesBanco) {
		this.vbcDesBanco = vbcDesBanco;
	}

	public String getVbcDesGraEfec() {
		return this.vbcDesGraEfec;
	}

	public void setVbcDesGraEfec(String vbcDesGraEfec) {
		this.vbcDesGraEfec = vbcDesGraEfec;
	}

	public String getVbcDesGraPens() {
		return this.vbcDesGraPens;
	}

	public void setVbcDesGraPens(String vbcDesGraPens) {
		this.vbcDesGraPens = vbcDesGraPens;
	}

	public String getVbcDesUnidad() {
		return this.vbcDesUnidad;
	}

	public void setVbcDesUnidad(String vbcDesUnidad) {
		this.vbcDesUnidad = vbcDesUnidad;
	}

	public String getVbcLugar() {
		return this.vbcLugar;
	}

	public void setVbcLugar(String vbcLugar) {
		this.vbcLugar = vbcLugar;
	}

	public String getVbcRegPens() {
		return this.vbcRegPens;
	}

	public void setVbcRegPens(String vbcRegPens) {
		this.vbcRegPens = vbcRegPens;
	}

	public String getVbcRegRemun() {
		return this.vbcRegRemun;
	}

	public void setVbcRegRemun(String vbcRegRemun) {
		this.vbcRegRemun = vbcRegRemun;
	}

	public List<SipreBoletaDetalle> getSipreBoletaDetalles() {
		return this.sipreBoletaDetalles;
	}

	public void setSipreBoletaDetalles(List<SipreBoletaDetalle> sipreBoletaDetalles) {
		this.sipreBoletaDetalles = sipreBoletaDetalles;
	}

	public SipreBoletaDetalle addSipreBoletaDetalle(SipreBoletaDetalle sipreBoletaDetalle) {
		getSipreBoletaDetalles().add(sipreBoletaDetalle);
		sipreBoletaDetalle.setSipreBoletaCabecera(this);

		return sipreBoletaDetalle;
	}

	public SipreBoletaDetalle removeSipreBoletaDetalle(SipreBoletaDetalle sipreBoletaDetalle) {
		getSipreBoletaDetalles().remove(sipreBoletaDetalle);
		sipreBoletaDetalle.setSipreBoletaCabecera(null);

		return sipreBoletaDetalle;
	}

}