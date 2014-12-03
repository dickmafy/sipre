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
public class SipreConceptoDescuentoLeyPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "CDLD_CODIGO")
    private String cdldCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CDL_CODIGO")
    private String cdlCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "CCD_CODIGO")
    private String ccdCodigo;

    public SipreConceptoDescuentoLeyPK() {
    }

    public SipreConceptoDescuentoLeyPK(String cdldCodigo, String cdlCodigo, String ccdCodigo) {
        this.cdldCodigo = cdldCodigo;
        this.cdlCodigo = cdlCodigo;
        this.ccdCodigo = ccdCodigo;
    }

    public String getCdldCodigo() {
        return cdldCodigo;
    }

    public void setCdldCodigo(String cdldCodigo) {
        this.cdldCodigo = cdldCodigo;
    }

    public String getCdlCodigo() {
        return cdlCodigo;
    }

    public void setCdlCodigo(String cdlCodigo) {
        this.cdlCodigo = cdlCodigo;
    }

    public String getCcdCodigo() {
        return ccdCodigo;
    }

    public void setCcdCodigo(String ccdCodigo) {
        this.ccdCodigo = ccdCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdldCodigo != null ? cdldCodigo.hashCode() : 0);
        hash += (cdlCodigo != null ? cdlCodigo.hashCode() : 0);
        hash += (ccdCodigo != null ? ccdCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SipreConceptoDescuentoLeyPK)) {
            return false;
        }
        SipreConceptoDescuentoLeyPK other = (SipreConceptoDescuentoLeyPK) object;
        if ((this.cdldCodigo == null && other.cdldCodigo != null) || (this.cdldCodigo != null && !this.cdldCodigo.equals(other.cdldCodigo))) {
            return false;
        }
        if ((this.cdlCodigo == null && other.cdlCodigo != null) || (this.cdlCodigo != null && !this.cdlCodigo.equals(other.cdlCodigo))) {
            return false;
        }
        if ((this.ccdCodigo == null && other.ccdCodigo != null) || (this.ccdCodigo != null && !this.ccdCodigo.equals(other.ccdCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreConceptoDescuentoLeyPK[ cdldCodigo=" + cdldCodigo + ", cdlCodigo=" + cdlCodigo + ", ccdCodigo=" + ccdCodigo + " ]";
    }
    
}
