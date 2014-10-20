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
public class SipreIngresoGradoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CGRADO_CODIGO")
    private String cgradoCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "CCI_CODIGO")
    private String cciCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "CIG_SITUACION")
    private String cigSituacion;

    public SipreIngresoGradoPK() {
    }

    public SipreIngresoGradoPK(String cgradoCodigo, String cciCodigo, String cigSituacion) {
        this.cgradoCodigo = cgradoCodigo;
        this.cciCodigo = cciCodigo;
        this.cigSituacion = cigSituacion;
    }

    public String getCgradoCodigo() {
        return cgradoCodigo;
    }

    public void setCgradoCodigo(String cgradoCodigo) {
        this.cgradoCodigo = cgradoCodigo;
    }

    public String getCciCodigo() {
        return cciCodigo;
    }

    public void setCciCodigo(String cciCodigo) {
        this.cciCodigo = cciCodigo;
    }

    public String getCigSituacion() {
        return cigSituacion;
    }

    public void setCigSituacion(String cigSituacion) {
        this.cigSituacion = cigSituacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cgradoCodigo != null ? cgradoCodigo.hashCode() : 0);
        hash += (cciCodigo != null ? cciCodigo.hashCode() : 0);
        hash += (cigSituacion != null ? cigSituacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SipreIngresoGradoPK)) {
            return false;
        }
        SipreIngresoGradoPK other = (SipreIngresoGradoPK) object;
        if ((this.cgradoCodigo == null && other.cgradoCodigo != null) || (this.cgradoCodigo != null && !this.cgradoCodigo.equals(other.cgradoCodigo))) {
            return false;
        }
        if ((this.cciCodigo == null && other.cciCodigo != null) || (this.cciCodigo != null && !this.cciCodigo.equals(other.cciCodigo))) {
            return false;
        }
        if ((this.cigSituacion == null && other.cigSituacion != null) || (this.cigSituacion != null && !this.cigSituacion.equals(other.cigSituacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreIngresoGradoPK[ cgradoCodigo=" + cgradoCodigo + ", cciCodigo=" + cciCodigo + ", cigSituacion=" + cigSituacion + " ]";
    }
    
}
