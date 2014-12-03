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
@Table(name = "SIPRE_CARGO")
@NamedQueries({
    @NamedQuery(name = "SipreCargo.findAll", query = "SELECT s FROM SipreCargo s")})
public class SipreCargo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "CCARGO_CODIGO")
    private String ccargoCodigo;
    @Size(max = 60)
    @Column(name = "VCARGO_DSC")
    private String vcargoDsc;
    @OneToMany(mappedBy = "sipreCargo")
    private List<SiprePersona> siprePersonaList;
    @OneToMany(mappedBy = "sipreCargo")
    private List<SiprePlanilla> siprePlanillaList;

    public SipreCargo() {
    }

    public SipreCargo(String ccargoCodigo) {
        this.ccargoCodigo = ccargoCodigo;
    }

    public String getCcargoCodigo() {
        return ccargoCodigo;
    }

    public void setCcargoCodigo(String ccargoCodigo) {
        this.ccargoCodigo = ccargoCodigo;
    }

    public String getVcargoDsc() {
        return vcargoDsc;
    }

    public void setVcargoDsc(String vcargoDsc) {
        this.vcargoDsc = vcargoDsc;
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
        hash += (ccargoCodigo != null ? ccargoCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SipreCargo)) {
            return false;
        }
        SipreCargo other = (SipreCargo) object;
        if ((this.ccargoCodigo == null && other.ccargoCodigo != null) || (this.ccargoCodigo != null && !this.ccargoCodigo.equals(other.ccargoCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreCargo[ ccargoCodigo=" + ccargoCodigo + " ]";
    }
    
}
