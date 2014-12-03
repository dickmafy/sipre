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
public class SipreDescuentoLeyDetPK implements Serializable {
   
	private static final long serialVersionUID = 1L;
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

    public SipreDescuentoLeyDetPK() {
    }

    public SipreDescuentoLeyDetPK(String cdldCodigo, String cdlCodigo) {
        this.cdldCodigo = cdldCodigo;
        this.cdlCodigo = cdlCodigo;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdldCodigo != null ? cdldCodigo.hashCode() : 0);
        hash += (cdlCodigo != null ? cdlCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SipreDescuentoLeyDetPK)) {
            return false;
        }
        SipreDescuentoLeyDetPK other = (SipreDescuentoLeyDetPK) object;
        if ((this.cdldCodigo == null && other.cdldCodigo != null) || (this.cdldCodigo != null && !this.cdldCodigo.equals(other.cdldCodigo))) {
            return false;
        }
        if ((this.cdlCodigo == null && other.cdlCodigo != null) || (this.cdlCodigo != null && !this.cdlCodigo.equals(other.cdlCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreDescuentoLeyDetPK[ cdldCodigo=" + cdldCodigo + ", cdlCodigo=" + cdlCodigo + " ]";
    }
    
}
