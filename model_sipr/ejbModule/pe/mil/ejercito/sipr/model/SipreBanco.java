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
@Table(name = "SIPRE_BANCO")
@NamedQueries({
    @NamedQuery(name = "SipreBanco.findAll", query = "SELECT s FROM SipreBanco s")})
public class SipreBanco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "CBANCO_CODIGO")
    private String cbancoCodigo;
    @Size(max = 40)
    @Column(name = "VBANCO_DSC")
    private String vbancoDsc;
    @Column(name = "CBANCO_ESTADO")
    private Character cbancoEstado;
    @OneToMany(mappedBy = "sipreBanco")
    private List<SiprePersona> siprePersonaList;
    @OneToMany(mappedBy = "sipreBanco")
    private List<SipreTmpBanco> sipreTmpBancoList;
    @OneToMany(mappedBy = "sipreBanco")
    private List<SiprePlanilla> siprePlanillaList;

    public SipreBanco() {
    }

    public SipreBanco(String cbancoCodigo) {
        this.cbancoCodigo = cbancoCodigo;
    }

    public String getCbancoCodigo() {
        return cbancoCodigo;
    }

    public void setCbancoCodigo(String cbancoCodigo) {
        this.cbancoCodigo = cbancoCodigo;
    }

    public String getVbancoDsc() {
        return vbancoDsc;
    }

    public void setVbancoDsc(String vbancoDsc) {
        this.vbancoDsc = vbancoDsc;
    }

    public Character getCbancoEstado() {
        return cbancoEstado;
    }

    public void setCbancoEstado(Character cbancoEstado) {
        this.cbancoEstado = cbancoEstado;
    }

    public List<SiprePersona> getSiprePersonaList() {
        return siprePersonaList;
    }

    public void setSiprePersonaList(List<SiprePersona> siprePersonaList) {
        this.siprePersonaList = siprePersonaList;
    }

    public List<SipreTmpBanco> getSipreTmpBancoList() {
        return sipreTmpBancoList;
    }

    public void setSipreTmpBancoList(List<SipreTmpBanco> sipreTmpBancoList) {
        this.sipreTmpBancoList = sipreTmpBancoList;
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
        hash += (cbancoCodigo != null ? cbancoCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SipreBanco)) {
            return false;
        }
        SipreBanco other = (SipreBanco) object;
        if ((this.cbancoCodigo == null && other.cbancoCodigo != null) || (this.cbancoCodigo != null && !this.cbancoCodigo.equals(other.cbancoCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreBanco[ cbancoCodigo=" + cbancoCodigo + " ]";
    }
    
}
