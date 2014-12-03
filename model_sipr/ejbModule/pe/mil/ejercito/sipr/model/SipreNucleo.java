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
@Table(name = "SIPRE_NUCLEO")
@NamedQueries({
    @NamedQuery(name = "SipreNucleo.findAll", query = "SELECT s FROM SipreNucleo s")})
public class SipreNucleo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "CNUCLEO_CODIGO")
    private String cnucleoCodigo;
    @Size(max = 60)
    @Column(name = "VNUCLEO_DSC_LARGA")
    private String vnucleoDscLarga;
    @Size(max = 20)
    @Column(name = "VNUCLEO_DSC_CORTA")
    private String vnucleoDscCorta;
    @Column(name = "CNUCLEO_ESTADO")
    private Character cnucleoEstado;
    @Size(max = 2)
    @Column(name = "CNUCLEO_NRO_SEC")
    private String cnucleoNroSec;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sipreNucleo")
    private List<SipreUnidad> sipreUnidadList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sipreNucleo")
    private List<SipreActividad> sipreActividadList;

    public SipreNucleo() {
    }

    public SipreNucleo(String cnucleoCodigo) {
        this.cnucleoCodigo = cnucleoCodigo;
    }

    public String getCnucleoCodigo() {
        return cnucleoCodigo;
    }

    public void setCnucleoCodigo(String cnucleoCodigo) {
        this.cnucleoCodigo = cnucleoCodigo;
    }

    public String getVnucleoDscLarga() {
        return vnucleoDscLarga;
    }

    public void setVnucleoDscLarga(String vnucleoDscLarga) {
        this.vnucleoDscLarga = vnucleoDscLarga;
    }

    public String getVnucleoDscCorta() {
        return vnucleoDscCorta;
    }

    public void setVnucleoDscCorta(String vnucleoDscCorta) {
        this.vnucleoDscCorta = vnucleoDscCorta;
    }

    public Character getCnucleoEstado() {
        return cnucleoEstado;
    }

    public void setCnucleoEstado(Character cnucleoEstado) {
        this.cnucleoEstado = cnucleoEstado;
    }

    public String getCnucleoNroSec() {
        return cnucleoNroSec;
    }

    public void setCnucleoNroSec(String cnucleoNroSec) {
        this.cnucleoNroSec = cnucleoNroSec;
    }

    public List<SipreUnidad> getSipreUnidadList() {
        return sipreUnidadList;
    }

    public void setSipreUnidadList(List<SipreUnidad> sipreUnidadList) {
        this.sipreUnidadList = sipreUnidadList;
    }

    public List<SipreActividad> getSipreActividadList() {
        return sipreActividadList;
    }

    public void setSipreActividadList(List<SipreActividad> sipreActividadList) {
        this.sipreActividadList = sipreActividadList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cnucleoCodigo != null ? cnucleoCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SipreNucleo)) {
            return false;
        }
        SipreNucleo other = (SipreNucleo) object;
        if ((this.cnucleoCodigo == null && other.cnucleoCodigo != null) || (this.cnucleoCodigo != null && !this.cnucleoCodigo.equals(other.cnucleoCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreNucleo[ cnucleoCodigo=" + cnucleoCodigo + " ]";
    }
    
}
