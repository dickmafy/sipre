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
@Table(name = "SIPRE_CEDULA")
@NamedQueries({
    @NamedQuery(name = "SipreCedula.findAll", query = "SELECT s FROM SipreCedula s")})
public class SipreCedula implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CCEDULA_CODIGO")
    private String ccedulaCodigo;
    @Size(max = 40)
    @Column(name = "VCEDULA_DSC")
    private String vcedulaDsc;
    @Column(name = "CCEDULA_IND_RENOV")
    private Character ccedulaIndRenov;
    @OneToMany(mappedBy = "sipreCedula")
    private List<SiprePersona> siprePersonaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sipreCedula")
    private List<SipreSituacionCedula> sipreSituacionCedulaList;
    @OneToMany(mappedBy = "sipreCedula")
    private List<SiprePlanilla> siprePlanillaList;

    public SipreCedula() {
    }

    public SipreCedula(String ccedulaCodigo) {
        this.ccedulaCodigo = ccedulaCodigo;
    }

    public String getCcedulaCodigo() {
        return ccedulaCodigo;
    }

    public void setCcedulaCodigo(String ccedulaCodigo) {
        this.ccedulaCodigo = ccedulaCodigo;
    }

    public String getVcedulaDsc() {
        return vcedulaDsc;
    }

    public void setVcedulaDsc(String vcedulaDsc) {
        this.vcedulaDsc = vcedulaDsc;
    }

    public Character getCcedulaIndRenov() {
        return ccedulaIndRenov;
    }

    public void setCcedulaIndRenov(Character ccedulaIndRenov) {
        this.ccedulaIndRenov = ccedulaIndRenov;
    }

    public List<SiprePersona> getSiprePersonaList() {
        return siprePersonaList;
    }

    public void setSiprePersonaList(List<SiprePersona> siprePersonaList) {
        this.siprePersonaList = siprePersonaList;
    }

    public List<SipreSituacionCedula> getSipreSituacionCedulaList() {
        return sipreSituacionCedulaList;
    }

    public void setSipreSituacionCedulaList(List<SipreSituacionCedula> sipreSituacionCedulaList) {
        this.sipreSituacionCedulaList = sipreSituacionCedulaList;
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
        hash += (ccedulaCodigo != null ? ccedulaCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SipreCedula)) {
            return false;
        }
        SipreCedula other = (SipreCedula) object;
        if ((this.ccedulaCodigo == null && other.ccedulaCodigo != null) || (this.ccedulaCodigo != null && !this.ccedulaCodigo.equals(other.ccedulaCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreCedula[ ccedulaCodigo=" + ccedulaCodigo + " ]";
    }
    
}
