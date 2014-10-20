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
public class SiprePlanillaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "CPERSONA_NRO_ADM")
    private String cpersonaNroAdm;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "CPLANILLA_MES_PROCESO")
    private String cplanillaMesProceso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NPLANILLA_NUM_PROCESO")
    private Integer nplanillaNumProceso;

    public SiprePlanillaPK() {
    }

    public SiprePlanillaPK(String cpersonaNroAdm, String cplanillaMesProceso, Integer nplanillaNumProceso) {
        this.cpersonaNroAdm = cpersonaNroAdm;
        this.cplanillaMesProceso = cplanillaMesProceso;
        this.nplanillaNumProceso = nplanillaNumProceso;
    }

    public String getCpersonaNroAdm() {
        return cpersonaNroAdm;
    }

    public void setCpersonaNroAdm(String cpersonaNroAdm) {
        this.cpersonaNroAdm = cpersonaNroAdm;
    }

    public String getCplanillaMesProceso() {
        return cplanillaMesProceso;
    }

    public void setCplanillaMesProceso(String cplanillaMesProceso) {
        this.cplanillaMesProceso = cplanillaMesProceso;
    }

    public Integer getNplanillaNumProceso() {
        return nplanillaNumProceso;
    }

    public void setNplanillaNumProceso(Integer nplanillaNumProceso) {
        this.nplanillaNumProceso = nplanillaNumProceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpersonaNroAdm != null ? cpersonaNroAdm.hashCode() : 0);
        hash += (cplanillaMesProceso != null ? cplanillaMesProceso.hashCode() : 0);
        hash += (int) nplanillaNumProceso;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SiprePlanillaPK)) {
            return false;
        }
        SiprePlanillaPK other = (SiprePlanillaPK) object;
        if ((this.cpersonaNroAdm == null && other.cpersonaNroAdm != null) || (this.cpersonaNroAdm != null && !this.cpersonaNroAdm.equals(other.cpersonaNroAdm))) {
            return false;
        }
        if ((this.cplanillaMesProceso == null && other.cplanillaMesProceso != null) || (this.cplanillaMesProceso != null && !this.cplanillaMesProceso.equals(other.cplanillaMesProceso))) {
            return false;
        }
        if (this.nplanillaNumProceso != other.nplanillaNumProceso) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SiprePlanillaPK[ cpersonaNroAdm=" + cpersonaNroAdm + ", cplanillaMesProceso=" + cplanillaMesProceso + ", nplanillaNumProceso=" + nplanillaNumProceso + " ]";
    }
    
}
