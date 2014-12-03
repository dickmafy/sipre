/*
 * To change this license header, choose License Headers in Project Properties. To change this template file, choose Tools | Templates and
 * open the template in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author DIEGO
 */
@Entity
@Table(name = "PERSONAL_PLANILLAS")
@NamedQueries({
		@NamedQuery(name = "PersonalPlanillas.findAll", query = "SELECT p FROM PersonalPlanillas p") })
public class PersonalPlanillas implements Serializable {
	private static final long	serialVersionUID	= 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "CPLANILLAS_NROADMIN")
	private String				cplanillasNroadmin;
	@Column(name = "CPLANILLAS_DNI")
	private String				cplanillasDni;
	@Column(name = "VPLANILLAS_QUINQUENIO")
	private String				vplanillasQuinquenio;
	@Column(name = "DPLANILLAS_FECHFALL")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dplanillasFechfall;
	@Column(name = "DPLANILLAS_FECHING")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dplanillasFeching;
	@Column(name = "VPLANILLAS_DOCALTA")
	private String				vplanillasDocalta;
	@Column(name = "VPLANILLAS_APENOM")
	private String				vplanillasApenom;
	@Column(name = "VSEXO_CODIGO")
	private String				vsexoCodigo;
	@Column(name = "DPLANILLAS_FECHNACE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dplanillasFechnace;
	@Column(name = "VGRADO_PENSION")
	private String				vgradoPension;
	@Column(name = "VONP_CODIGO")
	private String				vonpCodigo;
	@Column(name = "VPLANILLAS_CADENAF")
	private String				vplanillasCadenaf;
	@Column(name = "VESSALUD_CODIGO")
	private String				vessaludCodigo;
	@Column(name = "VPLANILLAS_CUSSP")
	private String				vplanillasCussp;
	// @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
	@Column(name = "NPLANILLAS_AGUINALDO")
	private BigDecimal			nplanillasAguinaldo;
	@Column(name = "DPLANILLAS_AFILAFP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dplanillasAfilafp;
	@Column(name = "DPLANILLAS_FCONTRATO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dplanillasFcontrato;
	@Column(name = "DPLANILLAS_FECHPROMO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dplanillasFechpromo;
	@Column(name = "DPLANILLAS_RETASCENSO")
	private Short				dplanillasRetascenso;
	@Column(name = "NPLANILLAS_INDICA_LIC")
	private String				nplanillasIndicaLic;
	@Column(name = "DPLANILLAS_FECHRETI")
	@Temporal(TemporalType.TIMESTAMP)
	private Date				dplanillasFechreti;
	@Column(name = "DPLANILLAS_DOCRETI")
	private String				dplanillasDocreti;
	@Column(name = "NPLANILLAS_SERVICIOS")
	private String				nplanillasServicios;
	@Column(name = "VPLANILLAS_DOCRECO")
	private String				vplanillasDocreco;
	@Column(name = "NPLANILLAS_PORCPEN")
	private BigDecimal			nplanillasPorcpen;
	@Column(name = "VSEXO_PENSION")
	private String				vsexoPension;
	@Column(name = "VPLANILLAS_NOMCAUSANTE")
	private String				vplanillasNomcausante;
	@Column(name = "VGRADOS_CODIGO")
	private String				vgradosCodigo;
	@Column(name = "VUNIDADES_CODIGO")
	private String				vunidadesCodigo;
	@Column(name = "VSITUCAUSAL_CODIGO")
	private String				vsitucausalCodigo;
	@Column(name = "VARMAS_CODIGO")
	private String				varmasCodigo;
	@Column(name = "CUSUARIO_AGRUPA")
	private String				cusuarioAgrupa;
	@Column(name = "VCARGO_CODIGO")
	private String				vcargoCodigo;
	@Column(name = "VPLANILLAS_ESPALT")
	private String				vplanillasEspalt;
	@Column(name = "VESTCIV_CODIGO")
	private String				vestcivCodigo;
	@Column(name = "VBANCO_CODIGO")
	private String				vbancoCodigo;
	@Column(name = "VSITUADM_CODIGO")
	private String				vsituadmCodigo;
	@Column(name = "VCEDULA_CODIGO")
	private String				vcedulaCodigo;
	@Column(name = "VCANT_HIJOS")
	private Short				vcantHijos;
	@Column(name = "VPORC_UNIF")
	private BigDecimal			vporcUnif;

	public PersonalPlanillas() {
	}

	public PersonalPlanillas(String cplanillasNroadmin) {
		this.cplanillasNroadmin = cplanillasNroadmin;
	}

	public String getCplanillasNroadmin() {
		return cplanillasNroadmin;
	}

	public void setCplanillasNroadmin(String cplanillasNroadmin) {
		this.cplanillasNroadmin = cplanillasNroadmin;
	}

	public String getCplanillasDni() {
		return cplanillasDni;
	}

	public void setCplanillasDni(String cplanillasDni) {
		this.cplanillasDni = cplanillasDni;
	}

	public String getVplanillasQuinquenio() {
		return vplanillasQuinquenio;
	}

	public void setVplanillasQuinquenio(String vplanillasQuinquenio) {
		this.vplanillasQuinquenio = vplanillasQuinquenio;
	}

	public Date getDplanillasFechfall() {
		return dplanillasFechfall;
	}

	public void setDplanillasFechfall(Date dplanillasFechfall) {
		this.dplanillasFechfall = dplanillasFechfall;
	}

	public Date getDplanillasFeching() {
		return dplanillasFeching;
	}

	public void setDplanillasFeching(Date dplanillasFeching) {
		this.dplanillasFeching = dplanillasFeching;
	}

	public String getVplanillasDocalta() {
		return vplanillasDocalta;
	}

	public void setVplanillasDocalta(String vplanillasDocalta) {
		this.vplanillasDocalta = vplanillasDocalta;
	}

	public String getVplanillasApenom() {
		return vplanillasApenom;
	}

	public void setVplanillasApenom(String vplanillasApenom) {
		this.vplanillasApenom = vplanillasApenom;
	}

	public String getVsexoCodigo() {
		return vsexoCodigo;
	}

	public void setVsexoCodigo(String vsexoCodigo) {
		this.vsexoCodigo = vsexoCodigo;
	}

	public Date getDplanillasFechnace() {
		return dplanillasFechnace;
	}

	public void setDplanillasFechnace(Date dplanillasFechnace) {
		this.dplanillasFechnace = dplanillasFechnace;
	}

	public String getVgradoPension() {
		return vgradoPension;
	}

	public void setVgradoPension(String vgradoPension) {
		this.vgradoPension = vgradoPension;
	}

	public String getVonpCodigo() {
		return vonpCodigo;
	}

	public void setVonpCodigo(String vonpCodigo) {
		this.vonpCodigo = vonpCodigo;
	}

	public String getVplanillasCadenaf() {
		return vplanillasCadenaf;
	}

	public void setVplanillasCadenaf(String vplanillasCadenaf) {
		this.vplanillasCadenaf = vplanillasCadenaf;
	}

	public String getVessaludCodigo() {
		return vessaludCodigo;
	}

	public void setVessaludCodigo(String vessaludCodigo) {
		this.vessaludCodigo = vessaludCodigo;
	}

	public String getVplanillasCussp() {
		return vplanillasCussp;
	}

	public void setVplanillasCussp(String vplanillasCussp) {
		this.vplanillasCussp = vplanillasCussp;
	}

	public BigDecimal getNplanillasAguinaldo() {
		return nplanillasAguinaldo;
	}

	public void setNplanillasAguinaldo(BigDecimal nplanillasAguinaldo) {
		this.nplanillasAguinaldo = nplanillasAguinaldo;
	}

	public Date getDplanillasAfilafp() {
		return dplanillasAfilafp;
	}

	public void setDplanillasAfilafp(Date dplanillasAfilafp) {
		this.dplanillasAfilafp = dplanillasAfilafp;
	}

	public Date getDplanillasFcontrato() {
		return dplanillasFcontrato;
	}

	public void setDplanillasFcontrato(Date dplanillasFcontrato) {
		this.dplanillasFcontrato = dplanillasFcontrato;
	}

	public Date getDplanillasFechpromo() {
		return dplanillasFechpromo;
	}

	public void setDplanillasFechpromo(Date dplanillasFechpromo) {
		this.dplanillasFechpromo = dplanillasFechpromo;
	}

	public Short getDplanillasRetascenso() {
		return dplanillasRetascenso;
	}

	public void setDplanillasRetascenso(Short dplanillasRetascenso) {
		this.dplanillasRetascenso = dplanillasRetascenso;
	}

	public String getNplanillasIndicaLic() {
		return nplanillasIndicaLic;
	}

	public void setNplanillasIndicaLic(String nplanillasIndicaLic) {
		this.nplanillasIndicaLic = nplanillasIndicaLic;
	}

	public Date getDplanillasFechreti() {
		return dplanillasFechreti;
	}

	public void setDplanillasFechreti(Date dplanillasFechreti) {
		this.dplanillasFechreti = dplanillasFechreti;
	}

	public String getDplanillasDocreti() {
		return dplanillasDocreti;
	}

	public void setDplanillasDocreti(String dplanillasDocreti) {
		this.dplanillasDocreti = dplanillasDocreti;
	}

	public String getNplanillasServicios() {
		return nplanillasServicios;
	}

	public void setNplanillasServicios(String nplanillasServicios) {
		this.nplanillasServicios = nplanillasServicios;
	}

	public String getVplanillasDocreco() {
		return vplanillasDocreco;
	}

	public void setVplanillasDocreco(String vplanillasDocreco) {
		this.vplanillasDocreco = vplanillasDocreco;
	}

	public BigDecimal getNplanillasPorcpen() {
		return nplanillasPorcpen;
	}

	public void setNplanillasPorcpen(BigDecimal nplanillasPorcpen) {
		this.nplanillasPorcpen = nplanillasPorcpen;
	}

	public String getVsexoPension() {
		return vsexoPension;
	}

	public void setVsexoPension(String vsexoPension) {
		this.vsexoPension = vsexoPension;
	}

	public String getVplanillasNomcausante() {
		return vplanillasNomcausante;
	}

	public void setVplanillasNomcausante(String vplanillasNomcausante) {
		this.vplanillasNomcausante = vplanillasNomcausante;
	}

	public String getVgradosCodigo() {
		return vgradosCodigo;
	}

	public void setVgradosCodigo(String vgradosCodigo) {
		this.vgradosCodigo = vgradosCodigo;
	}

	public String getVunidadesCodigo() {
		return vunidadesCodigo;
	}

	public void setVunidadesCodigo(String vunidadesCodigo) {
		this.vunidadesCodigo = vunidadesCodigo;
	}

	public String getVsitucausalCodigo() {
		return vsitucausalCodigo;
	}

	public void setVsitucausalCodigo(String vsitucausalCodigo) {
		this.vsitucausalCodigo = vsitucausalCodigo;
	}

	public String getVarmasCodigo() {
		return varmasCodigo;
	}

	public void setVarmasCodigo(String varmasCodigo) {
		this.varmasCodigo = varmasCodigo;
	}

	public String getCusuarioAgrupa() {
		return cusuarioAgrupa;
	}

	public void setCusuarioAgrupa(String cusuarioAgrupa) {
		this.cusuarioAgrupa = cusuarioAgrupa;
	}

	public String getVcargoCodigo() {
		return vcargoCodigo;
	}

	public void setVcargoCodigo(String vcargoCodigo) {
		this.vcargoCodigo = vcargoCodigo;
	}

	public String getVplanillasEspalt() {
		return vplanillasEspalt;
	}

	public void setVplanillasEspalt(String vplanillasEspalt) {
		this.vplanillasEspalt = vplanillasEspalt;
	}

	public String getVestcivCodigo() {
		return vestcivCodigo;
	}

	public void setVestcivCodigo(String vestcivCodigo) {
		this.vestcivCodigo = vestcivCodigo;
	}

	public String getVbancoCodigo() {
		return vbancoCodigo;
	}

	public void setVbancoCodigo(String vbancoCodigo) {
		this.vbancoCodigo = vbancoCodigo;
	}

	public String getVsituadmCodigo() {
		return vsituadmCodigo;
	}

	public void setVsituadmCodigo(String vsituadmCodigo) {
		this.vsituadmCodigo = vsituadmCodigo;
	}

	public String getVcedulaCodigo() {
		return vcedulaCodigo;
	}

	public void setVcedulaCodigo(String vcedulaCodigo) {
		this.vcedulaCodigo = vcedulaCodigo;
	}

	public Short getVcantHijos() {
		return vcantHijos;
	}

	public void setVcantHijos(Short vcantHijos) {
		this.vcantHijos = vcantHijos;
	}

	public BigDecimal getVporcUnif() {
		return vporcUnif;
	}

	public void setVporcUnif(BigDecimal vporcUnif) {
		this.vporcUnif = vporcUnif;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (cplanillasNroadmin != null ? cplanillasNroadmin.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		
		if (!(object instanceof PersonalPlanillas)) {
			return false;
		}
		PersonalPlanillas other = (PersonalPlanillas) object;
		if ((this.cplanillasNroadmin == null && other.cplanillasNroadmin != null)
				|| (this.cplanillasNroadmin != null && !this.cplanillasNroadmin.equals(other.cplanillasNroadmin))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "pe.mil.ejercito.sipr.model.PersonalPlanillas[ cplanillasNroadmin=" + cplanillasNroadmin + " ]";
	}

}
