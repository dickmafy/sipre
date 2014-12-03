/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_ESTADO_CIVIL")
@NamedQueries({
    @NamedQuery(name = "SipreEstadoCivil.findAll", query = "SELECT s FROM SipreEstadoCivil s")})
public class SipreEstadoCivil implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CEC_CODIGO")
    private String cecCodigo;
    @Size(max = 20)
    @Column(name = "VEC_DSC")
    private String vecDsc;
    @Column(name = "CEC_IND_COB")
    private Character cecIndCob;
    @OneToMany(mappedBy = "sipreEstadoCivil")
    private List<SiprePersona> siprePersonaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sipreEstadoCivil")
    private List<SiprePlanilla> siprePlanillaList;

    public SipreEstadoCivil() {
    }

    public SipreEstadoCivil(String cecCodigo) {
        this.cecCodigo = cecCodigo;
    }

    public String getCecCodigo() {
        return cecCodigo;
    }

    public void setCecCodigo(String cecCodigo) {
        this.cecCodigo = cecCodigo;
    }

    public String getVecDsc() {
        return vecDsc;
    }

    public void setVecDsc(String vecDsc) {
        this.vecDsc = vecDsc;
    }

    public Character getCecIndCob() {
        return cecIndCob;
    }

    public void setCecIndCob(Character cecIndCob) {
        this.cecIndCob = cecIndCob;
    }

    public List<SiprePersona> getSiprePersonaList() {
        return siprePersonaList;
    }

    public void setSiprePersonaList(List<SiprePersona> siprePersonaList) {
        this.siprePersonaList = siprePersonaList;
    }

    public List<SiprePlanilla> getSiprePlanillaList() {
        return siprePlanillaList;
    }

    public void setSiprePlanillaList(List<SiprePlanilla> siprePlanillaList) {
        this.siprePlanillaList = siprePlanillaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cecCodigo != null ? cecCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SipreEstadoCivil)) {
            return false;
        }
        SipreEstadoCivil other = (SipreEstadoCivil) object;
        if ((this.cecCodigo == null && other.cecCodigo != null) || (this.cecCodigo != null && !this.cecCodigo.equals(other.cecCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreEstadoCivil[ cecCodigo=" + cecCodigo + " ]";
    }
    
}
