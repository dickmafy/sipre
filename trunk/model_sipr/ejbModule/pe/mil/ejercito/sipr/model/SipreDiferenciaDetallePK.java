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
public class SipreDiferenciaDetallePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "CDIFERENCIA_MES_PROCESO")
    private String cdiferenciaMesProceso;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NDIFERENCIA_NUM_PROCESO")
    private short ndiferenciaNumProceso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "CPERSONA_NRO_ADM")
    private String cpersonaNroAdm;

    public SipreDiferenciaDetallePK() {
    }

    public SipreDiferenciaDetallePK(String cdiferenciaMesProceso, short ndiferenciaNumProceso, String cpersonaNroAdm) {
        this.cdiferenciaMesProceso = cdiferenciaMesProceso;
        this.ndiferenciaNumProceso = ndiferenciaNumProceso;
        this.cpersonaNroAdm = cpersonaNroAdm;
    }

    public String getCdiferenciaMesProceso() {
        return cdiferenciaMesProceso;
    }

    public void setCdiferenciaMesProceso(String cdiferenciaMesProceso) {
        this.cdiferenciaMesProceso = cdiferenciaMesProceso;
    }

    public short getNdiferenciaNumProceso() {
        return ndiferenciaNumProceso;
    }

    public void setNdiferenciaNumProceso(short ndiferenciaNumProceso) {
        this.ndiferenciaNumProceso = ndiferenciaNumProceso;
    }

    public String getCpersonaNroAdm() {
        return cpersonaNroAdm;
    }

    public void setCpersonaNroAdm(String cpersonaNroAdm) {
        this.cpersonaNroAdm = cpersonaNroAdm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdiferenciaMesProceso != null ? cdiferenciaMesProceso.hashCode() : 0);
        hash += (int) ndiferenciaNumProceso;
        hash += (cpersonaNroAdm != null ? cpersonaNroAdm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SipreDiferenciaDetallePK)) {
            return false;
        }
        SipreDiferenciaDetallePK other = (SipreDiferenciaDetallePK) object;
        if ((this.cdiferenciaMesProceso == null && other.cdiferenciaMesProceso != null) || (this.cdiferenciaMesProceso != null && !this.cdiferenciaMesProceso.equals(other.cdiferenciaMesProceso))) {
            return false;
        }
        if (this.ndiferenciaNumProceso != other.ndiferenciaNumProceso) {
            return false;
        }
        if ((this.cpersonaNroAdm == null && other.cpersonaNroAdm != null) || (this.cpersonaNroAdm != null && !this.cpersonaNroAdm.equals(other.cpersonaNroAdm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreDiferenciaDetallePK[ cdiferenciaMesProceso=" + cdiferenciaMesProceso + ", ndiferenciaNumProceso=" + ndiferenciaNumProceso + ", cpersonaNroAdm=" + cpersonaNroAdm + " ]";
    }
    
}
