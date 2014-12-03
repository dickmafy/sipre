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

/**
 *
 * @author DIEGO
 */
@Embeddable
public class SipreDescuentoLeyPersonaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "CPERSONA_NRO_ADM")
    private String cpersonaNroAdm;
    @Basic(optional = false)
    @Column(name = "CDL_CODIGO")
    private String cdlCodigo;
    @Basic(optional = false)
    @Column(name = "CDLD_CODIGO")
    private String cdldCodigo;

    public SipreDescuentoLeyPersonaPK() {
    }

    public SipreDescuentoLeyPersonaPK(String cpersonaNroAdm, String cdlCodigo, String cdldCodigo) {
        this.cpersonaNroAdm = cpersonaNroAdm;
        this.cdlCodigo = cdlCodigo;
        this.cdldCodigo = cdldCodigo;
    }

    public String getCpersonaNroAdm() {
        return cpersonaNroAdm;
    }

    public void setCpersonaNroAdm(String cpersonaNroAdm) {
        this.cpersonaNroAdm = cpersonaNroAdm;
    }

    public String getCdlCodigo() {
        return cdlCodigo;
    }

    public void setCdlCodigo(String cdlCodigo) {
        this.cdlCodigo = cdlCodigo;
    }

    public String getCdldCodigo() {
        return cdldCodigo;
    }

    public void setCdldCodigo(String cdldCodigo) {
        this.cdldCodigo = cdldCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpersonaNroAdm != null ? cpersonaNroAdm.hashCode() : 0);
        hash += (cdlCodigo != null ? cdlCodigo.hashCode() : 0);
        hash += (cdldCodigo != null ? cdldCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SipreDescuentoLeyPersonaPK)) {
            return false;
        }
        SipreDescuentoLeyPersonaPK other = (SipreDescuentoLeyPersonaPK) object;
        if ((this.cpersonaNroAdm == null && other.cpersonaNroAdm != null) || (this.cpersonaNroAdm != null && !this.cpersonaNroAdm.equals(other.cpersonaNroAdm))) {
            return false;
        }
        if ((this.cdlCodigo == null && other.cdlCodigo != null) || (this.cdlCodigo != null && !this.cdlCodigo.equals(other.cdlCodigo))) {
            return false;
        }
        if ((this.cdldCodigo == null && other.cdldCodigo != null) || (this.cdldCodigo != null && !this.cdldCodigo.equals(other.cdldCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreDescuentoLeyPersonaPK[ cpersonaNroAdm=" + cpersonaNroAdm + ", cdlCodigo=" + cdlCodigo + ", cdldCodigo=" + cdldCodigo + " ]";
    }
    
}
