/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_TMP_PERSONA")
@NamedQueries({
    @NamedQuery(name = "SipreTmpPersona.findAll", query = "SELECT s FROM SipreTmpPersona s")})
public class SipreTmpPersona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "CPERSONA_NRO_ADM")
    private String cpersonaNroAdm;
    @Size(max = 8)
    @Column(name = "CTP_DNI")
    private String ctpDni;
    @Column(name = "CTP_IND_QUI")
    private Character ctpIndQui;
    @Column(name = "DTP_FEC_FAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtpFecFal;
    @Column(name = "DTP_FEC_ING")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtpFecIng;
    @Size(max = 30)
    @Column(name = "VTP_DOC_ALTA")
    private String vtpDocAlta;
    @Size(max = 80)
    @Column(name = "VTP_APE_NOM")
    private String vtpApeNom;
    @Column(name = "CTP_SEXO")
    private Character ctpSexo;
    @Column(name = "DTP_FEC_NAC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtpFecNac;
    @Column(name = "NTP_NRO_HIJO")
    private Short ntpNroHijo;
    @Size(max = 3)
    @Column(name = "CTP_COD_GRA_PEN")
    private String ctpCodGraPen;
    @Column(name = "CTP_IND_ONP")
    private Character ctpIndOnp;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NTP_POR_UNIF")
    private BigDecimal ntpPorUnif;
    @Size(max = 20)
    @Column(name = "VTP_CAD_FUNC")
    private String vtpCadFunc;
    @Size(max = 18)
    @Column(name = "VTP_COD_ESSALUD")
    private String vtpCodEssalud;
    @Size(max = 16)
    @Column(name = "VTP_CUSPP")
    private String vtpCuspp;
    @Column(name = "CTP_IND_AGUIN")
    private Character ctpIndAguin;
    @Column(name = "DTP_FEC_AFI_AFP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtpFecAfiAfp;
    @Column(name = "DTP_FEC_FIN_CONTR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtpFecFinContr;
    @Column(name = "DTP_FEC_PROMO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtpFecPromo;
    @Column(name = "NTP_RET_ASCENSO")
    private Short ntpRetAscenso;
    @Size(max = 2)
    @Column(name = "CTP_IND_LICENCIA")
    private String ctpIndLicencia;
    @Column(name = "DTP_FEC_RETIRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtpFecRetiro;
    @Size(max = 30)
    @Column(name = "VTP_DOC_RETIRO")
    private String vtpDocRetiro;
    @Size(max = 6)
    @Column(name = "CTP_SER_RECON")
    private String ctpSerRecon;
    @Size(max = 30)
    @Column(name = "VTP_DOC_RECON")
    private String vtpDocRecon;
    @Column(name = "NTP_POR_PENSION")
    private BigDecimal ntpPorPension;
    @Column(name = "CTP_SEX_PENSION")
    private Character ctpSexPension;
    @Size(max = 80)
    @Column(name = "VTP_NOM_CAUSANTE")
    private String vtpNomCausante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CTP_COD_GRADO")
    private String ctpCodGrado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "CTP_COD_UNIDAD")
    private String ctpCodUnidad;
    @Size(max = 2)
    @Column(name = "CTP_COD_SIT_CAUSAL")
    private String ctpCodSitCausal;
    @Size(max = 3)
    @Column(name = "CTP_COD_ARMA")
    private String ctpCodArma;
    @Size(max = 2)
    @Column(name = "CTP_COD_AGRUPADOR")
    private String ctpCodAgrupador;
    @Size(max = 8)
    @Column(name = "CTP_COD_CARGO")
    private String ctpCodCargo;
    @Size(max = 2)
    @Column(name = "CTP_COD_ESP_ALT")
    private String ctpCodEspAlt;
    @Column(name = "CTP_COD_EST_CIVIL")
    private Character ctpCodEstCivil;
    @Size(max = 2)
    @Column(name = "CTP_COD_BANCO")
    private String ctpCodBanco;
    @Column(name = "CTP_COD_CEDULA")
    private Character ctpCodCedula;
    @Size(max = 2)
    @Column(name = "CTP_COD_SIT_ADM")
    private String ctpCodSitAdm;

    public SipreTmpPersona() {
    }

    public SipreTmpPersona(String cpersonaNroAdm) {
        this.cpersonaNroAdm = cpersonaNroAdm;
    }

    public SipreTmpPersona(String cpersonaNroAdm, String ctpCodGrado, String ctpCodUnidad) {
        this.cpersonaNroAdm = cpersonaNroAdm;
        this.ctpCodGrado = ctpCodGrado;
        this.ctpCodUnidad = ctpCodUnidad;
    }

    public String getCpersonaNroAdm() {
        return cpersonaNroAdm;
    }

    public void setCpersonaNroAdm(String cpersonaNroAdm) {
        this.cpersonaNroAdm = cpersonaNroAdm;
    }

    public String getCtpDni() {
        return ctpDni;
    }

    public void setCtpDni(String ctpDni) {
        this.ctpDni = ctpDni;
    }

    public Character getCtpIndQui() {
        return ctpIndQui;
    }

    public void setCtpIndQui(Character ctpIndQui) {
        this.ctpIndQui = ctpIndQui;
    }

    public Date getDtpFecFal() {
        return dtpFecFal;
    }

    public void setDtpFecFal(Date dtpFecFal) {
        this.dtpFecFal = dtpFecFal;
    }

    public Date getDtpFecIng() {
        return dtpFecIng;
    }

    public void setDtpFecIng(Date dtpFecIng) {
        this.dtpFecIng = dtpFecIng;
    }

    public String getVtpDocAlta() {
        return vtpDocAlta;
    }

    public void setVtpDocAlta(String vtpDocAlta) {
        this.vtpDocAlta = vtpDocAlta;
    }

    public String getVtpApeNom() {
        return vtpApeNom;
    }

    public void setVtpApeNom(String vtpApeNom) {
        this.vtpApeNom = vtpApeNom;
    }

    public Character getCtpSexo() {
        return ctpSexo;
    }

    public void setCtpSexo(Character ctpSexo) {
        this.ctpSexo = ctpSexo;
    }

    public Date getDtpFecNac() {
        return dtpFecNac;
    }

    public void setDtpFecNac(Date dtpFecNac) {
        this.dtpFecNac = dtpFecNac;
    }

    public Short getNtpNroHijo() {
        return ntpNroHijo;
    }

    public void setNtpNroHijo(Short ntpNroHijo) {
        this.ntpNroHijo = ntpNroHijo;
    }

    public String getCtpCodGraPen() {
        return ctpCodGraPen;
    }

    public void setCtpCodGraPen(String ctpCodGraPen) {
        this.ctpCodGraPen = ctpCodGraPen;
    }

    public Character getCtpIndOnp() {
        return ctpIndOnp;
    }

    public void setCtpIndOnp(Character ctpIndOnp) {
        this.ctpIndOnp = ctpIndOnp;
    }

    public BigDecimal getNtpPorUnif() {
        return ntpPorUnif;
    }

    public void setNtpPorUnif(BigDecimal ntpPorUnif) {
        this.ntpPorUnif = ntpPorUnif;
    }

    public String getVtpCadFunc() {
        return vtpCadFunc;
    }

    public void setVtpCadFunc(String vtpCadFunc) {
        this.vtpCadFunc = vtpCadFunc;
    }

    public String getVtpCodEssalud() {
        return vtpCodEssalud;
    }

    public void setVtpCodEssalud(String vtpCodEssalud) {
        this.vtpCodEssalud = vtpCodEssalud;
    }

    public String getVtpCuspp() {
        return vtpCuspp;
    }

    public void setVtpCuspp(String vtpCuspp) {
        this.vtpCuspp = vtpCuspp;
    }

    public Character getCtpIndAguin() {
        return ctpIndAguin;
    }

    public void setCtpIndAguin(Character ctpIndAguin) {
        this.ctpIndAguin = ctpIndAguin;
    }

    public Date getDtpFecAfiAfp() {
        return dtpFecAfiAfp;
    }

    public void setDtpFecAfiAfp(Date dtpFecAfiAfp) {
        this.dtpFecAfiAfp = dtpFecAfiAfp;
    }

    public Date getDtpFecFinContr() {
        return dtpFecFinContr;
    }

    public void setDtpFecFinContr(Date dtpFecFinContr) {
        this.dtpFecFinContr = dtpFecFinContr;
    }

    public Date getDtpFecPromo() {
        return dtpFecPromo;
    }

    public void setDtpFecPromo(Date dtpFecPromo) {
        this.dtpFecPromo = dtpFecPromo;
    }

    public Short getNtpRetAscenso() {
        return ntpRetAscenso;
    }

    public void setNtpRetAscenso(Short ntpRetAscenso) {
        this.ntpRetAscenso = ntpRetAscenso;
    }

    public String getCtpIndLicencia() {
        return ctpIndLicencia;
    }

    public void setCtpIndLicencia(String ctpIndLicencia) {
        this.ctpIndLicencia = ctpIndLicencia;
    }

    public Date getDtpFecRetiro() {
        return dtpFecRetiro;
    }

    public void setDtpFecRetiro(Date dtpFecRetiro) {
        this.dtpFecRetiro = dtpFecRetiro;
    }

    public String getVtpDocRetiro() {
        return vtpDocRetiro;
    }

    public void setVtpDocRetiro(String vtpDocRetiro) {
        this.vtpDocRetiro = vtpDocRetiro;
    }

    public String getCtpSerRecon() {
        return ctpSerRecon;
    }

    public void setCtpSerRecon(String ctpSerRecon) {
        this.ctpSerRecon = ctpSerRecon;
    }

    public String getVtpDocRecon() {
        return vtpDocRecon;
    }

    public void setVtpDocRecon(String vtpDocRecon) {
        this.vtpDocRecon = vtpDocRecon;
    }

    public BigDecimal getNtpPorPension() {
        return ntpPorPension;
    }

    public void setNtpPorPension(BigDecimal ntpPorPension) {
        this.ntpPorPension = ntpPorPension;
    }

    public Character getCtpSexPension() {
        return ctpSexPension;
    }

    public void setCtpSexPension(Character ctpSexPension) {
        this.ctpSexPension = ctpSexPension;
    }

    public String getVtpNomCausante() {
        return vtpNomCausante;
    }

    public void setVtpNomCausante(String vtpNomCausante) {
        this.vtpNomCausante = vtpNomCausante;
    }

    public String getCtpCodGrado() {
        return ctpCodGrado;
    }

    public void setCtpCodGrado(String ctpCodGrado) {
        this.ctpCodGrado = ctpCodGrado;
    }

    public String getCtpCodUnidad() {
        return ctpCodUnidad;
    }

    public void setCtpCodUnidad(String ctpCodUnidad) {
        this.ctpCodUnidad = ctpCodUnidad;
    }

    public String getCtpCodSitCausal() {
        return ctpCodSitCausal;
    }

    public void setCtpCodSitCausal(String ctpCodSitCausal) {
        this.ctpCodSitCausal = ctpCodSitCausal;
    }

    public String getCtpCodArma() {
        return ctpCodArma;
    }

    public void setCtpCodArma(String ctpCodArma) {
        this.ctpCodArma = ctpCodArma;
    }

    public String getCtpCodAgrupador() {
        return ctpCodAgrupador;
    }

    public void setCtpCodAgrupador(String ctpCodAgrupador) {
        this.ctpCodAgrupador = ctpCodAgrupador;
    }

    public String getCtpCodCargo() {
        return ctpCodCargo;
    }

    public void setCtpCodCargo(String ctpCodCargo) {
        this.ctpCodCargo = ctpCodCargo;
    }

    public String getCtpCodEspAlt() {
        return ctpCodEspAlt;
    }

    public void setCtpCodEspAlt(String ctpCodEspAlt) {
        this.ctpCodEspAlt = ctpCodEspAlt;
    }

    public Character getCtpCodEstCivil() {
        return ctpCodEstCivil;
    }

    public void setCtpCodEstCivil(Character ctpCodEstCivil) {
        this.ctpCodEstCivil = ctpCodEstCivil;
    }

    public String getCtpCodBanco() {
        return ctpCodBanco;
    }

    public void setCtpCodBanco(String ctpCodBanco) {
        this.ctpCodBanco = ctpCodBanco;
    }

    public Character getCtpCodCedula() {
        return ctpCodCedula;
    }

    public void setCtpCodCedula(Character ctpCodCedula) {
        this.ctpCodCedula = ctpCodCedula;
    }

    public String getCtpCodSitAdm() {
        return ctpCodSitAdm;
    }

    public void setCtpCodSitAdm(String ctpCodSitAdm) {
        this.ctpCodSitAdm = ctpCodSitAdm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpersonaNroAdm != null ? cpersonaNroAdm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SipreTmpPersona)) {
            return false;
        }
        SipreTmpPersona other = (SipreTmpPersona) object;
        if ((this.cpersonaNroAdm == null && other.cpersonaNroAdm != null) || (this.cpersonaNroAdm != null && !this.cpersonaNroAdm.equals(other.cpersonaNroAdm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreTmpPersona[ cpersonaNroAdm=" + cpersonaNroAdm + " ]";
    }
    
}
