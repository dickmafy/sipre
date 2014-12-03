/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "SIPRE_ROL")
@NamedQueries({
    @NamedQuery(name = "SipreRol.findAll", query = "SELECT s FROM SipreRol s")})
public class SipreRol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CROL_CODIGO")
    private String crolCodigo;
    @Size(max = 30)
    @Column(name = "VROL_DSC")
    private String vrolDsc;
    @Column(name = "CROL_EST")
    private Character crolEst;
    @ManyToMany(mappedBy = "sipreRolList")
    private List<SipreUsuario> sipreUsuarioList;

    public SipreRol() {
    }

    public SipreRol(String crolCodigo) {
        this.crolCodigo = crolCodigo;
    }

    public String getCrolCodigo() {
        return crolCodigo;
    }

    public void setCrolCodigo(String crolCodigo) {
        this.crolCodigo = crolCodigo;
    }

    public String getVrolDsc() {
        return vrolDsc;
    }

    public void setVrolDsc(String vrolDsc) {
        this.vrolDsc = vrolDsc;
    }

    public Character getCrolEst() {
        return crolEst;
    }

    public void setCrolEst(Character crolEst) {
        this.crolEst = crolEst;
    }

    public List<SipreUsuario> getSipreUsuarioList() {
        return sipreUsuarioList;
    }

    public void setSipreUsuarioList(List<SipreUsuario> sipreUsuarioList) {
        this.sipreUsuarioList = sipreUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (crolCodigo != null ? crolCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SipreRol)) {
            return false;
        }
        SipreRol other = (SipreRol) object;
        if ((this.crolCodigo == null && other.crolCodigo != null) || (this.crolCodigo != null && !this.crolCodigo.equals(other.crolCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreRol[ crolCodigo=" + crolCodigo + " ]";
    }
    
}
