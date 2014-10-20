/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_ESCALA")
@NamedQueries({
    @NamedQuery(name = "SipreEscala.findAll", query = "SELECT s FROM SipreEscala s")})
public class SipreEscala implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SipreEscalaPK sipreEscalaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NESCALA_MONTO")
    private BigDecimal nescalaMonto;
    @ManyToMany(mappedBy = "sipreEscalaList")
    private List<SiprePersona> siprePersonaList;

    public SipreEscala() {
    }

    public SipreEscala(SipreEscalaPK sipreEscalaPK) {
        this.sipreEscalaPK = sipreEscalaPK;
    }

    public SipreEscala(String cescalaCodigo, String cescalaHora) {
        this.sipreEscalaPK = new SipreEscalaPK(cescalaCodigo, cescalaHora);
    }

    public SipreEscalaPK getSipreEscalaPK() {
        return sipreEscalaPK;
    }

    public void setSipreEscalaPK(SipreEscalaPK sipreEscalaPK) {
        this.sipreEscalaPK = sipreEscalaPK;
    }

    public BigDecimal getNescalaMonto() {
        return nescalaMonto;
    }

    public void setNescalaMonto(BigDecimal nescalaMonto) {
        this.nescalaMonto = nescalaMonto;
    }

    public List<SiprePersona> getSiprePersonaList() {
        return siprePersonaList;
    }

    public void setSiprePersonaList(List<SiprePersona> siprePersonaList) {
        this.siprePersonaList = siprePersonaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sipreEscalaPK != null ? sipreEscalaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SipreEscala)) {
            return false;
        }
        SipreEscala other = (SipreEscala) object;
        if ((this.sipreEscalaPK == null && other.sipreEscalaPK != null) || (this.sipreEscalaPK != null && !this.sipreEscalaPK.equals(other.sipreEscalaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreEscala[ sipreEscalaPK=" + sipreEscalaPK + " ]";
    }
    
}
