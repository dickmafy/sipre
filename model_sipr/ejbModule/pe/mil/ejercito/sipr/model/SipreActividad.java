/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_ACTIVIDAD")
@NamedQueries({
    @NamedQuery(name = "SipreActividad.findAll", query = "SELECT s FROM SipreActividad s")})
public class SipreActividad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "CACTIVIDAD_CODIGO")
    private String cactividadCodigo;
    @Size(max = 60)
    @Column(name = "VACTIVIDAD_DSC_LARGA")
    private String vactividadDscLarga;
    @Size(max = 20)
    @Column(name = "VACTIVIDAD_DSC_CORTA")
    private String vactividadDscCorta;
    @Column(name = "CACTIVIDAD_ESTADO")
    private Character cactividadEstado;
    @Column(name = "CACTIVIDAD_IND_LIM_PROV")
    private Character cactividadIndLimProv;
    @JoinColumn(name = "CNUCLEO_CODIGO", referencedColumnName = "CNUCLEO_CODIGO")
    @ManyToOne(optional = false)
    private SipreNucleo sipreNucleo;

    public SipreActividad() {
    }

    public SipreActividad(String cactividadCodigo) {
        this.cactividadCodigo = cactividadCodigo;
    }

    public String getCactividadCodigo() {
        return cactividadCodigo;
    }

    public void setCactividadCodigo(String cactividadCodigo) {
        this.cactividadCodigo = cactividadCodigo;
    }

    public String getVactividadDscLarga() {
        return vactividadDscLarga;
    }

    public void setVactividadDscLarga(String vactividadDscLarga) {
        this.vactividadDscLarga = vactividadDscLarga;
    }

    public String getVactividadDscCorta() {
        return vactividadDscCorta;
    }

    public void setVactividadDscCorta(String vactividadDscCorta) {
        this.vactividadDscCorta = vactividadDscCorta;
    }

    public Character getCactividadEstado() {
        return cactividadEstado;
    }

    public void setCactividadEstado(Character cactividadEstado) {
        this.cactividadEstado = cactividadEstado;
    }

    public Character getCactividadIndLimProv() {
        return cactividadIndLimProv;
    }

    public void setCactividadIndLimProv(Character cactividadIndLimProv) {
        this.cactividadIndLimProv = cactividadIndLimProv;
    }

    public SipreNucleo getSipreNucleo() {
        return sipreNucleo;
    }

    public void setSipreNucleo(SipreNucleo sipreNucleo) {
        this.sipreNucleo = sipreNucleo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cactividadCodigo != null ? cactividadCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SipreActividad)) {
            return false;
        }
        SipreActividad other = (SipreActividad) object;
        if ((this.cactividadCodigo == null && other.cactividadCodigo != null) || (this.cactividadCodigo != null && !this.cactividadCodigo.equals(other.cactividadCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreActividad[ cactividadCodigo=" + cactividadCodigo + " ]";
    }
    
}
