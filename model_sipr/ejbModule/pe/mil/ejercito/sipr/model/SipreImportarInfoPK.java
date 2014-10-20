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
public class SipreImportarInfoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "CII_MES_PROCESO")
    private String ciiMesProceso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NII_NUM_PROCESO")
    private short niiNumProceso;

    public SipreImportarInfoPK() {
    }

    public SipreImportarInfoPK(String ciiMesProceso, short niiNumProceso) {
        this.ciiMesProceso = ciiMesProceso;
        this.niiNumProceso = niiNumProceso;
    }

    public String getCiiMesProceso() {
        return ciiMesProceso;
    }

    public void setCiiMesProceso(String ciiMesProceso) {
        this.ciiMesProceso = ciiMesProceso;
    }

    public short getNiiNumProceso() {
        return niiNumProceso;
    }

    public void setNiiNumProceso(short niiNumProceso) {
        this.niiNumProceso = niiNumProceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ciiMesProceso != null ? ciiMesProceso.hashCode() : 0);
        hash += (int) niiNumProceso;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SipreImportarInfoPK)) {
            return false;
        }
        SipreImportarInfoPK other = (SipreImportarInfoPK) object;
        if ((this.ciiMesProceso == null && other.ciiMesProceso != null) || (this.ciiMesProceso != null && !this.ciiMesProceso.equals(other.ciiMesProceso))) {
            return false;
        }
        if (this.niiNumProceso != other.niiNumProceso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreImportarInfoPK[ ciiMesProceso=" + ciiMesProceso + ", niiNumProceso=" + niiNumProceso + " ]";
    }
    
}
