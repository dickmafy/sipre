/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "SIPRE_PARAMETRO_DETALLE")
@NamedQueries({
    @NamedQuery(name = "SipreParametroDetalle_1.findAll", query = "SELECT s FROM SipreParametroDetalle s")})
public class SipreParametroDetalle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "CPARAMETRO_MES_PROCESO")
    private String cparametroMesProceso;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NPARAMETRO_VAL")
    private BigDecimal nparametroVal;
    @JoinColumn(name = "CPD_CODIGO", referencedColumnName = "CPD_CODIGO")
    @ManyToOne(optional = false)
    private SipreParametro cpdCodigo;

    public SipreParametroDetalle() {
    }

    public SipreParametroDetalle(String cparametroMesProceso) {
        this.cparametroMesProceso = cparametroMesProceso;
    }

    public String getCparametroMesProceso() {
        return cparametroMesProceso;
    }

    public void setCparametroMesProceso(String cparametroMesProceso) {
        this.cparametroMesProceso = cparametroMesProceso;
    }

    public BigDecimal getNparametroVal() {
        return nparametroVal;
    }

    public void setNparametroVal(BigDecimal nparametroVal) {
        this.nparametroVal = nparametroVal;
    }

    public SipreParametro getCpdCodigo() {
        return cpdCodigo;
    }

    public void setCpdCodigo(SipreParametro cpdCodigo) {
        this.cpdCodigo = cpdCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cparametroMesProceso != null ? cparametroMesProceso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SipreParametroDetalle)) {
            return false;
        }
        SipreParametroDetalle other = (SipreParametroDetalle) object;
        if ((this.cparametroMesProceso == null && other.cparametroMesProceso != null) || (this.cparametroMesProceso != null && !this.cparametroMesProceso.equals(other.cparametroMesProceso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreParametroDetalle_1[ cparametroMesProceso=" + cparametroMesProceso + " ]";
    }
    
}