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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_TMP_BANCO")
@NamedQueries({
    @NamedQuery(name = "SipreTmpBanco.findAll", query = "SELECT s FROM SipreTmpBanco s")})
public class SipreTmpBanco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "CPERSONA_NRO_ADM")
    private String cpersonaNroAdm;
    @JoinColumn(name = "CPERSONA_NRO_ADM", referencedColumnName = "CPERSONA_NRO_ADM", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private SiprePersona siprePersona;
    @JoinColumn(name = "CBANCO_CODIGO", referencedColumnName = "CBANCO_CODIGO")
    @ManyToOne
    private SipreBanco sipreBanco;

    public SipreTmpBanco() {
    }

    public SipreTmpBanco(String cpersonaNroAdm) {
        this.cpersonaNroAdm = cpersonaNroAdm;
    }

    public String getCpersonaNroAdm() {
        return cpersonaNroAdm;
    }

    public void setCpersonaNroAdm(String cpersonaNroAdm) {
        this.cpersonaNroAdm = cpersonaNroAdm;
    }

    public SiprePersona getSiprePersona() {
        return siprePersona;
    }

    public void setSiprePersona(SiprePersona siprePersona) {
        this.siprePersona = siprePersona;
    }

    public SipreBanco getSipreBanco() {
        return sipreBanco;
    }

    public void setSipreBanco(SipreBanco sipreBanco) {
        this.sipreBanco = sipreBanco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpersonaNroAdm != null ? cpersonaNroAdm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SipreTmpBanco)) {
            return false;
        }
        SipreTmpBanco other = (SipreTmpBanco) object;
        if ((this.cpersonaNroAdm == null && other.cpersonaNroAdm != null) || (this.cpersonaNroAdm != null && !this.cpersonaNroAdm.equals(other.cpersonaNroAdm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreTmpBanco[ cpersonaNroAdm=" + cpersonaNroAdm + " ]";
    }
    
}
