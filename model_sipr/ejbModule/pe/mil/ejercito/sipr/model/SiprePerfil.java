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
@Table(name = "SIPRE_PERFIL")
@NamedQueries({
    @NamedQuery(name = "SiprePerfil.findAll", query = "SELECT s FROM SiprePerfil s")})
public class SiprePerfil implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PERFIL")
    private Long codigoPerfil;
    @Size(max = 30)
    @Column(name = "NOMBRE_PERFIL")
    private String nombrePerfil;
    @Size(max = 160)
    @Column(name = "DESCRIPCION_PERFIL")
    private String descripcionPerfil;

    public SiprePerfil() {
    }

    public SiprePerfil(Long codigoPerfil) {
        this.codigoPerfil = codigoPerfil;
    }

    public Long getCodigoPerfil() {
        return codigoPerfil;
    }

    public void setCodigoPerfil(Long codigoPerfil) {
        this.codigoPerfil = codigoPerfil;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    public String getDescripcionPerfil() {
        return descripcionPerfil;
    }

    public void setDescripcionPerfil(String descripcionPerfil) {
        this.descripcionPerfil = descripcionPerfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPerfil != null ? codigoPerfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SiprePerfil)) {
            return false;
        }
        SiprePerfil other = (SiprePerfil) object;
        if ((this.codigoPerfil == null && other.codigoPerfil != null) || (this.codigoPerfil != null && !this.codigoPerfil.equals(other.codigoPerfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SiprePerfil[ codigoPerfil=" + codigoPerfil + " ]";
    }
    
}