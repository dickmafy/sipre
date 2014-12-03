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
@Table(name = "SIPRE_DESCUENTO_LEY")
@NamedQueries({
    @NamedQuery(name = "SipreDescuentoLey.findAll", query = "SELECT s FROM SipreDescuentoLey s")})
public class SipreDescuentoLey implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CDL_CODIGO")
    private String cdlCodigo;
    @Size(max = 40)
    @Column(name = "VDL_DSC")
    private String vdlDsc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sipreDescuentoLey")
    private List<SipreDescuentoLeyDet> sipreDescuentoLeyDetList;

    public SipreDescuentoLey() {
    }

    public SipreDescuentoLey(String cdlCodigo) {
        this.cdlCodigo = cdlCodigo;
    }

    public String getCdlCodigo() {
        return cdlCodigo;
    }

    public void setCdlCodigo(String cdlCodigo) {
        this.cdlCodigo = cdlCodigo;
    }

    public String getVdlDsc() {
        return vdlDsc;
    }

    public void setVdlDsc(String vdlDsc) {
        this.vdlDsc = vdlDsc;
    }

    public List<SipreDescuentoLeyDet> getSipreDescuentoLeyDetList() {
        return sipreDescuentoLeyDetList;
    }

    public void setSipreDescuentoLeyDetList(List<SipreDescuentoLeyDet> sipreDescuentoLeyDetList) {
        this.sipreDescuentoLeyDetList = sipreDescuentoLeyDetList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdlCodigo != null ? cdlCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SipreDescuentoLey)) {
            return false;
        }
        SipreDescuentoLey other = (SipreDescuentoLey) object;
        if ((this.cdlCodigo == null && other.cdlCodigo != null) || (this.cdlCodigo != null && !this.cdlCodigo.equals(other.cdlCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreDescuentoLey[ cdlCodigo=" + cdlCodigo + " ]";
    }
    
}
