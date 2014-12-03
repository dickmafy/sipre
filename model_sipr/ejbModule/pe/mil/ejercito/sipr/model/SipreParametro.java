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
@Table(name = "SIPRE_PARAMETRO")
@NamedQueries({
    @NamedQuery(name = "SipreParametro_1.findAll", query = "SELECT s FROM SipreParametro s")})
public class SipreParametro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CPD_CODIGO")
    private String cpdCodigo;
    @Size(max = 20)
    @Column(name = "VPD_DSC")
    private String vpdDsc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cpdCodigo")
    private List<SipreParametroDetalle> sipreParametroDetalleList;

    public SipreParametro() {
    }

    public SipreParametro(String cpdCodigo) {
        this.cpdCodigo = cpdCodigo;
    }

    public String getCpdCodigo() {
        return cpdCodigo;
    }

    public void setCpdCodigo(String cpdCodigo) {
        this.cpdCodigo = cpdCodigo;
    }

    public String getVpdDsc() {
        return vpdDsc;
    }

    public void setVpdDsc(String vpdDsc) {
        this.vpdDsc = vpdDsc;
    }

    public List<SipreParametroDetalle> getSipreParametroDetalleList() {
        return sipreParametroDetalleList;
    }

    public void setSipreParametroDetalleList(List<SipreParametroDetalle> sipreParametroDetalleList) {
        this.sipreParametroDetalleList = sipreParametroDetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpdCodigo != null ? cpdCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SipreParametro)) {
            return false;
        }
        SipreParametro other = (SipreParametro) object;
        if ((this.cpdCodigo == null && other.cpdCodigo != null) || (this.cpdCodigo != null && !this.cpdCodigo.equals(other.cpdCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreParametro_1[ cpdCodigo=" + cpdCodigo + " ]";
    }
    
}
