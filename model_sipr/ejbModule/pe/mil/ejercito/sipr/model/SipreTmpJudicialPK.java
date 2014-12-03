/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author DIEGO
 */
@Embeddable
public class SipreTmpJudicialPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "CTJ_MES_PROCESO")
    private String ctjMesProceso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "CPERSONA_NRO_ADM")
    private String cpersonaNroAdm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "CTP_CODIGO")
    private String ctpCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "CEC_CODIGO")
    private String cecCodigo;

    public SipreTmpJudicialPK() {
    }

    public SipreTmpJudicialPK(String ctjMesProceso, String cpersonaNroAdm, String ctpCodigo, String cecCodigo) {
        this.ctjMesProceso = ctjMesProceso;
        this.cpersonaNroAdm = cpersonaNroAdm;
        this.ctpCodigo = ctpCodigo;
        this.cecCodigo = cecCodigo;
    }

    public String getCtjMesProceso() {
        return ctjMesProceso;
    }

    public void setCtjMesProceso(String ctjMesProceso) {
        this.ctjMesProceso = ctjMesProceso;
    }

    public String getCpersonaNroAdm() {
        return cpersonaNroAdm;
    }

    public void setCpersonaNroAdm(String cpersonaNroAdm) {
        this.cpersonaNroAdm = cpersonaNroAdm;
    }

    public String getCtpCodigo() {
        return ctpCodigo;
    }

    public void setCtpCodigo(String ctpCodigo) {
        this.ctpCodigo = ctpCodigo;
    }

    public String getCecCodigo() {
        return cecCodigo;
    }

    public void setCecCodigo(String cecCodigo) {
        this.cecCodigo = cecCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctjMesProceso != null ? ctjMesProceso.hashCode() : 0);
        hash += (cpersonaNroAdm != null ? cpersonaNroAdm.hashCode() : 0);
        hash += (ctpCodigo != null ? ctpCodigo.hashCode() : 0);
        hash += (cecCodigo != null ? cecCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SipreTmpJudicialPK)) {
            return false;
        }
        SipreTmpJudicialPK other = (SipreTmpJudicialPK) object;
        if ((this.ctjMesProceso == null && other.ctjMesProceso != null) || (this.ctjMesProceso != null && !this.ctjMesProceso.equals(other.ctjMesProceso))) {
            return false;
        }
        if ((this.cpersonaNroAdm == null && other.cpersonaNroAdm != null) || (this.cpersonaNroAdm != null && !this.cpersonaNroAdm.equals(other.cpersonaNroAdm))) {
            return false;
        }
        if ((this.ctpCodigo == null && other.ctpCodigo != null) || (this.ctpCodigo != null && !this.ctpCodigo.equals(other.ctpCodigo))) {
            return false;
        }
        if ((this.cecCodigo == null && other.cecCodigo != null) || (this.cecCodigo != null && !this.cecCodigo.equals(other.cecCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreTmpJudicialPK[ ctjMesProceso=" + ctjMesProceso + ", cpersonaNroAdm=" + cpersonaNroAdm + ", ctpCodigo=" + ctpCodigo + ", cecCodigo=" + cecCodigo + " ]";
    }
    
}
