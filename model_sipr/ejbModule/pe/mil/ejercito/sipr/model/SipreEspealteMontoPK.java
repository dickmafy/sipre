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
public class SipreEspealteMontoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "CGG_CODIGO")
    private String cggCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "CEA_CODIGO")
    private String ceaCodigo;

    public SipreEspealteMontoPK() {
    }

    public SipreEspealteMontoPK(String cggCodigo, String ceaCodigo) {
        this.cggCodigo = cggCodigo;
        this.ceaCodigo = ceaCodigo;
    }

    public String getCggCodigo() {
        return cggCodigo;
    }

    public void setCggCodigo(String cggCodigo) {
        this.cggCodigo = cggCodigo;
    }

    public String getCeaCodigo() {
        return ceaCodigo;
    }

    public void setCeaCodigo(String ceaCodigo) {
        this.ceaCodigo = ceaCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cggCodigo != null ? cggCodigo.hashCode() : 0);
        hash += (ceaCodigo != null ? ceaCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SipreEspealteMontoPK)) {
            return false;
        }
        SipreEspealteMontoPK other = (SipreEspealteMontoPK) object;
        if ((this.cggCodigo == null && other.cggCodigo != null) || (this.cggCodigo != null && !this.cggCodigo.equals(other.cggCodigo))) {
            return false;
        }
        if ((this.ceaCodigo == null && other.ceaCodigo != null) || (this.ceaCodigo != null && !this.ceaCodigo.equals(other.ceaCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreEspealteMontoPK[ cggCodigo=" + cggCodigo + ", ceaCodigo=" + ceaCodigo + " ]";
    }
    
}
