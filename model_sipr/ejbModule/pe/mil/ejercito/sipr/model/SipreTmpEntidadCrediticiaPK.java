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
public class SipreTmpEntidadCrediticiaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "CPERSONA_NRO_ADM")
    private String cpersonaNroAdm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "CEC_CODIGO")
    private String cecCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "CTEC_MES_PROCESO")
    private String ctecMesProceso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CTEC_TIPO_MOVIM")
    private Character ctecTipoMovim;

    public SipreTmpEntidadCrediticiaPK() {
    }

    public SipreTmpEntidadCrediticiaPK(String cpersonaNroAdm, String cecCodigo, String ctecMesProceso, Character ctecTipoMovim) {
        this.cpersonaNroAdm = cpersonaNroAdm;
        this.cecCodigo = cecCodigo;
        this.ctecMesProceso = ctecMesProceso;
        this.ctecTipoMovim = ctecTipoMovim;
    }

    public String getCpersonaNroAdm() {
        return cpersonaNroAdm;
    }

    public void setCpersonaNroAdm(String cpersonaNroAdm) {
        this.cpersonaNroAdm = cpersonaNroAdm;
    }

    public String getCecCodigo() {
        return cecCodigo;
    }

    public void setCecCodigo(String cecCodigo) {
        this.cecCodigo = cecCodigo;
    }

    public String getCtecMesProceso() {
        return ctecMesProceso;
    }

    public void setCtecMesProceso(String ctecMesProceso) {
        this.ctecMesProceso = ctecMesProceso;
    }

    public Character getCtecTipoMovim() {
        return ctecTipoMovim;
    }

    public void setCtecTipoMovim(Character ctecTipoMovim) {
        this.ctecTipoMovim = ctecTipoMovim;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpersonaNroAdm != null ? cpersonaNroAdm.hashCode() : 0);
        hash += (cecCodigo != null ? cecCodigo.hashCode() : 0);
        hash += (ctecMesProceso != null ? ctecMesProceso.hashCode() : 0);
        hash += (ctecTipoMovim != null ? ctecTipoMovim.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SipreTmpEntidadCrediticiaPK)) {
            return false;
        }
        SipreTmpEntidadCrediticiaPK other = (SipreTmpEntidadCrediticiaPK) object;
        if ((this.cpersonaNroAdm == null && other.cpersonaNroAdm != null) || (this.cpersonaNroAdm != null && !this.cpersonaNroAdm.equals(other.cpersonaNroAdm))) {
            return false;
        }
        if ((this.cecCodigo == null && other.cecCodigo != null) || (this.cecCodigo != null && !this.cecCodigo.equals(other.cecCodigo))) {
            return false;
        }
        if ((this.ctecMesProceso == null && other.ctecMesProceso != null) || (this.ctecMesProceso != null && !this.ctecMesProceso.equals(other.ctecMesProceso))) {
            return false;
        }
        if ((this.ctecTipoMovim == null && other.ctecTipoMovim != null) || (this.ctecTipoMovim != null && !this.ctecTipoMovim.equals(other.ctecTipoMovim))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreTmpEntidadCrediticiaPK[ cpersonaNroAdm=" + cpersonaNroAdm + ", cecCodigo=" + cecCodigo + ", ctecMesProceso=" + ctecMesProceso + ", ctecTipoMovim=" + ctecTipoMovim + " ]";
    }
    
}
