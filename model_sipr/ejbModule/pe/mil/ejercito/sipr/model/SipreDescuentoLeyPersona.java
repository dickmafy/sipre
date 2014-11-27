/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_DESCUENTO_LEY_PERSONA")
@NamedQueries({
    @NamedQuery(name = "SipreDescuentoLeyPersona.findAll", query = "SELECT s FROM SipreDescuentoLeyPersona s")})
public class SipreDescuentoLeyPersona implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SipreDescuentoLeyPersonaPK sipreDescuentoLeyPersonaPK;
    @JoinColumns({
        @JoinColumn(name = "CDL_CODIGO", referencedColumnName = "CDL_CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "CDLD_CODIGO", referencedColumnName = "CDLD_CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private SipreDescuentoLeyDet sipreDescuentoLeyDet;

    public SipreDescuentoLeyPersona() {
    }

    public SipreDescuentoLeyPersona(SipreDescuentoLeyPersonaPK sipreDescuentoLeyPersonaPK) {
        this.sipreDescuentoLeyPersonaPK = sipreDescuentoLeyPersonaPK;
    }

    public SipreDescuentoLeyPersona(String cpersonaNroAdm, String cdlCodigo, String cdldCodigo) {
        this.sipreDescuentoLeyPersonaPK = new SipreDescuentoLeyPersonaPK(cpersonaNroAdm, cdlCodigo, cdldCodigo);
    }

    public SipreDescuentoLeyPersonaPK getSipreDescuentoLeyPersonaPK() {
        return sipreDescuentoLeyPersonaPK;
    }

    public void setSipreDescuentoLeyPersonaPK(SipreDescuentoLeyPersonaPK sipreDescuentoLeyPersonaPK) {
        this.sipreDescuentoLeyPersonaPK = sipreDescuentoLeyPersonaPK;
    }

    public SipreDescuentoLeyDet getSipreDescuentoLeyDet() {
        return sipreDescuentoLeyDet;
    }

    public void setSipreDescuentoLeyDet(SipreDescuentoLeyDet sipreDescuentoLeyDet) {
        this.sipreDescuentoLeyDet = sipreDescuentoLeyDet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sipreDescuentoLeyPersonaPK != null ? sipreDescuentoLeyPersonaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SipreDescuentoLeyPersona)) {
            return false;
        }
        SipreDescuentoLeyPersona other = (SipreDescuentoLeyPersona) object;
        if ((this.sipreDescuentoLeyPersonaPK == null && other.sipreDescuentoLeyPersonaPK != null) || (this.sipreDescuentoLeyPersonaPK != null && !this.sipreDescuentoLeyPersonaPK.equals(other.sipreDescuentoLeyPersonaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreDescuentoLeyPersona[ sipreDescuentoLeyPersonaPK=" + sipreDescuentoLeyPersonaPK + " ]";
    }
    
}
