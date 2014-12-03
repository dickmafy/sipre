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
public class SipreSituacionCedulaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "CSA_CODIGO")
    private String csaCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CCEDULA_CODIGO")
    private Character ccedulaCodigo;

    public SipreSituacionCedulaPK() {
    }

    public SipreSituacionCedulaPK(String csaCodigo, Character ccedulaCodigo) {
        this.csaCodigo = csaCodigo;
        this.ccedulaCodigo = ccedulaCodigo;
    }

    public String getCsaCodigo() {
        return csaCodigo;
    }

    public void setCsaCodigo(String csaCodigo) {
        this.csaCodigo = csaCodigo;
    }

    public Character getCcedulaCodigo() {
        return ccedulaCodigo;
    }

    public void setCcedulaCodigo(Character ccedulaCodigo) {
        this.ccedulaCodigo = ccedulaCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (csaCodigo != null ? csaCodigo.hashCode() : 0);
        hash += (ccedulaCodigo != null ? ccedulaCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SipreSituacionCedulaPK)) {
            return false;
        }
        SipreSituacionCedulaPK other = (SipreSituacionCedulaPK) object;
        if ((this.csaCodigo == null && other.csaCodigo != null) || (this.csaCodigo != null && !this.csaCodigo.equals(other.csaCodigo))) {
            return false;
        }
        if ((this.ccedulaCodigo == null && other.ccedulaCodigo != null) || (this.ccedulaCodigo != null && !this.ccedulaCodigo.equals(other.ccedulaCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreSituacionCedulaPK[ csaCodigo=" + csaCodigo + ", ccedulaCodigo=" + ccedulaCodigo + " ]";
    }
    
}
