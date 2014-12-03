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
public class SipreEscalaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "CESCALA_CODIGO")
    private String cescalaCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "CESCALA_HORA")
    private String cescalaHora;

    public SipreEscalaPK() {
    }

    public SipreEscalaPK(String cescalaCodigo, String cescalaHora) {
        this.cescalaCodigo = cescalaCodigo;
        this.cescalaHora = cescalaHora;
    }

    public String getCescalaCodigo() {
        return cescalaCodigo;
    }

    public void setCescalaCodigo(String cescalaCodigo) {
        this.cescalaCodigo = cescalaCodigo;
    }

    public String getCescalaHora() {
        return cescalaHora;
    }

    public void setCescalaHora(String cescalaHora) {
        this.cescalaHora = cescalaHora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cescalaCodigo != null ? cescalaCodigo.hashCode() : 0);
        hash += (cescalaHora != null ? cescalaHora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SipreEscalaPK)) {
            return false;
        }
        SipreEscalaPK other = (SipreEscalaPK) object;
        if ((this.cescalaCodigo == null && other.cescalaCodigo != null) || (this.cescalaCodigo != null && !this.cescalaCodigo.equals(other.cescalaCodigo))) {
            return false;
        }
        if ((this.cescalaHora == null && other.cescalaHora != null) || (this.cescalaHora != null && !this.cescalaHora.equals(other.cescalaHora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreEscalaPK[ cescalaCodigo=" + cescalaCodigo + ", cescalaHora=" + cescalaHora + " ]";
    }
    
}
