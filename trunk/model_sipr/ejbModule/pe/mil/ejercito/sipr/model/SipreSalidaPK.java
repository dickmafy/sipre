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
public class SipreSalidaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "CSALIDA_MES_PROCESO")
    private String csalidaMesProceso;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "CSALIDA_ENTIDAD")
    private String csalidaEntidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "CSALIDA_SEC")
    private String csalidaSec;

    public SipreSalidaPK() {
    }

    public SipreSalidaPK(String csalidaMesProceso, String csalidaEntidad, String csalidaSec) {
        this.csalidaMesProceso = csalidaMesProceso;
        this.csalidaEntidad = csalidaEntidad;
        this.csalidaSec = csalidaSec;
    }

    public String getCsalidaMesProceso() {
        return csalidaMesProceso;
    }

    public void setCsalidaMesProceso(String csalidaMesProceso) {
        this.csalidaMesProceso = csalidaMesProceso;
    }

    public String getCsalidaEntidad() {
        return csalidaEntidad;
    }

    public void setCsalidaEntidad(String csalidaEntidad) {
        this.csalidaEntidad = csalidaEntidad;
    }

    public String getCsalidaSec() {
        return csalidaSec;
    }

    public void setCsalidaSec(String csalidaSec) {
        this.csalidaSec = csalidaSec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (csalidaMesProceso != null ? csalidaMesProceso.hashCode() : 0);
        hash += (csalidaEntidad != null ? csalidaEntidad.hashCode() : 0);
        hash += (csalidaSec != null ? csalidaSec.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SipreSalidaPK)) {
            return false;
        }
        SipreSalidaPK other = (SipreSalidaPK) object;
        if ((this.csalidaMesProceso == null && other.csalidaMesProceso != null) || (this.csalidaMesProceso != null && !this.csalidaMesProceso.equals(other.csalidaMesProceso))) {
            return false;
        }
        if ((this.csalidaEntidad == null && other.csalidaEntidad != null) || (this.csalidaEntidad != null && !this.csalidaEntidad.equals(other.csalidaEntidad))) {
            return false;
        }
        if ((this.csalidaSec == null && other.csalidaSec != null) || (this.csalidaSec != null && !this.csalidaSec.equals(other.csalidaSec))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreSalidaPK[ csalidaMesProceso=" + csalidaMesProceso + ", csalidaEntidad=" + csalidaEntidad + ", csalidaSec=" + csalidaSec + " ]";
    }
    
}
