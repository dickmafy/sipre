/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_ESPEALTE_MONTO")
@NamedQueries({
    @NamedQuery(name = "SipreEspealteMonto.findAll", query = "SELECT s FROM SipreEspealteMonto s")})
public class SipreEspealteMonto implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SipreEspealteMontoPK sipreEspealteMontoPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NEM_MONTO")
    private BigDecimal nemMonto;
    @JoinColumn(name = "CGG_CODIGO", referencedColumnName = "CGG_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SipreGrupoGrado sipreGrupoGrado;
    @JoinColumn(name = "CEA_CODIGO", referencedColumnName = "CEA_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SipreEspecialidadAlterna sipreEspecialidadAlterna;

    public SipreEspealteMonto() {
    }

    public SipreEspealteMonto(SipreEspealteMontoPK sipreEspealteMontoPK) {
        this.sipreEspealteMontoPK = sipreEspealteMontoPK;
    }

    public SipreEspealteMonto(String cggCodigo, String ceaCodigo) {
        this.sipreEspealteMontoPK = new SipreEspealteMontoPK(cggCodigo, ceaCodigo);
    }

    public SipreEspealteMontoPK getSipreEspealteMontoPK() {
        return sipreEspealteMontoPK;
    }

    public void setSipreEspealteMontoPK(SipreEspealteMontoPK sipreEspealteMontoPK) {
        this.sipreEspealteMontoPK = sipreEspealteMontoPK;
    }

    public BigDecimal getNemMonto() {
        return nemMonto;
    }

    public void setNemMonto(BigDecimal nemMonto) {
        this.nemMonto = nemMonto;
    }

    public SipreGrupoGrado getSipreGrupoGrado() {
        return sipreGrupoGrado;
    }

    public void setSipreGrupoGrado(SipreGrupoGrado sipreGrupoGrado) {
        this.sipreGrupoGrado = sipreGrupoGrado;
    }

    public SipreEspecialidadAlterna getSipreEspecialidadAlterna() {
        return sipreEspecialidadAlterna;
    }

    public void setSipreEspecialidadAlterna(SipreEspecialidadAlterna sipreEspecialidadAlterna) {
        this.sipreEspecialidadAlterna = sipreEspecialidadAlterna;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sipreEspealteMontoPK != null ? sipreEspealteMontoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SipreEspealteMonto)) {
            return false;
        }
        SipreEspealteMonto other = (SipreEspealteMonto) object;
        if ((this.sipreEspealteMontoPK == null && other.sipreEspealteMontoPK != null) || (this.sipreEspealteMontoPK != null && !this.sipreEspealteMontoPK.equals(other.sipreEspealteMontoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreEspealteMonto[ sipreEspealteMontoPK=" + sipreEspealteMontoPK + " ]";
    }
    
}
