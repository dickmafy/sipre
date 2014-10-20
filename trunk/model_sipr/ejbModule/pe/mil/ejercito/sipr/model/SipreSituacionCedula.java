/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_SITUACION_CEDULA")
@NamedQueries({
    @NamedQuery(name = "SipreSituacionCedula.findAll", query = "SELECT s FROM SipreSituacionCedula s")})
public class SipreSituacionCedula implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SipreSituacionCedulaPK sipreSituacionCedulaPK;
    @Size(max = 6)
    @Column(name = "CSCE_IND_PORCENTAJE")
    private String csceIndPorcentaje;
    @JoinColumn(name = "CSA_CODIGO", referencedColumnName = "CSA_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SipreSituacionAdm sipreSituacionAdm;
    @JoinColumn(name = "CCEDULA_CODIGO", referencedColumnName = "CCEDULA_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SipreCedula sipreCedula;

    public SipreSituacionCedula() {
    }

    public SipreSituacionCedula(SipreSituacionCedulaPK sipreSituacionCedulaPK) {
        this.sipreSituacionCedulaPK = sipreSituacionCedulaPK;
    }

    public SipreSituacionCedula(String csaCodigo, Character ccedulaCodigo) {
        this.sipreSituacionCedulaPK = new SipreSituacionCedulaPK(csaCodigo, ccedulaCodigo);
    }

    public SipreSituacionCedulaPK getSipreSituacionCedulaPK() {
        return sipreSituacionCedulaPK;
    }

    public void setSipreSituacionCedulaPK(SipreSituacionCedulaPK sipreSituacionCedulaPK) {
        this.sipreSituacionCedulaPK = sipreSituacionCedulaPK;
    }

    public String getCsceIndPorcentaje() {
        return csceIndPorcentaje;
    }

    public void setCsceIndPorcentaje(String csceIndPorcentaje) {
        this.csceIndPorcentaje = csceIndPorcentaje;
    }

    public SipreSituacionAdm getSipreSituacionAdm() {
        return sipreSituacionAdm;
    }

    public void setSipreSituacionAdm(SipreSituacionAdm sipreSituacionAdm) {
        this.sipreSituacionAdm = sipreSituacionAdm;
    }

    public SipreCedula getSipreCedula() {
        return sipreCedula;
    }

    public void setSipreCedula(SipreCedula sipreCedula) {
        this.sipreCedula = sipreCedula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sipreSituacionCedulaPK != null ? sipreSituacionCedulaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SipreSituacionCedula)) {
            return false;
        }
        SipreSituacionCedula other = (SipreSituacionCedula) object;
        if ((this.sipreSituacionCedulaPK == null && other.sipreSituacionCedulaPK != null) || (this.sipreSituacionCedulaPK != null && !this.sipreSituacionCedulaPK.equals(other.sipreSituacionCedulaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreSituacionCedula[ sipreSituacionCedulaPK=" + sipreSituacionCedulaPK + " ]";
    }
    
}
