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
@Table(name = "SIPRE_AGRUPADOR")
@NamedQueries({
    @NamedQuery(name = "SipreAgrupador.findAll", query = "SELECT s FROM SipreAgrupador s")})
public class SipreAgrupador implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "CAGRUPADOR_CODIGO")
    private String cagrupadorCodigo;
    @Size(max = 30)
    @Column(name = "VAGRUPADOR_DSC")
    private String vagrupadorDsc;
    @OneToMany(mappedBy = "sipreAgrupador")
    private List<SiprePersona> siprePersonaList;
    @OneToMany(mappedBy = "sipreAgrupador")
    private List<SiprePlanilla> siprePlanillaList;

    public SipreAgrupador() {
    }

    public SipreAgrupador(String cagrupadorCodigo) {
        this.cagrupadorCodigo = cagrupadorCodigo;
    }

    public String getCagrupadorCodigo() {
        return cagrupadorCodigo;
    }

    public void setCagrupadorCodigo(String cagrupadorCodigo) {
        this.cagrupadorCodigo = cagrupadorCodigo;
    }

    public String getVagrupadorDsc() {
        return vagrupadorDsc;
    }

    public void setVagrupadorDsc(String vagrupadorDsc) {
        this.vagrupadorDsc = vagrupadorDsc;
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
        hash += (cagrupadorCodigo != null ? cagrupadorCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SipreAgrupador)) {
            return false;
        }
        SipreAgrupador other = (SipreAgrupador) object;
        if ((this.cagrupadorCodigo == null && other.cagrupadorCodigo != null) || (this.cagrupadorCodigo != null && !this.cagrupadorCodigo.equals(other.cagrupadorCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreAgrupador[ cagrupadorCodigo=" + cagrupadorCodigo + " ]";
    }
    
}
