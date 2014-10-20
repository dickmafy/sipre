/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_PLANILLA_ADICIONAL")
@NamedQueries({
    @NamedQuery(name = "SiprePlanillaAdicional.findAll", query = "SELECT s FROM SiprePlanillaAdicional s")})
public class SiprePlanillaAdicional implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SiprePlanillaAdicionalPK siprePlanillaAdicionalPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NPD_MTO_CONCEPTO")
    private BigDecimal npdMtoConcepto;
    @JoinColumns({
        @JoinColumn(name = "CPLANILLA_MES_PROCESO", referencedColumnName = "CPLANILLA_MES_PROCESO", insertable = false, updatable = false),
        @JoinColumn(name = "CPERSONA_NRO_ADM", referencedColumnName = "CPERSONA_NRO_ADM", insertable = false, updatable = false),
        @JoinColumn(name = "NPLANILLA_NUM_PROCESO", referencedColumnName = "NPLANILLA_NUM_PROCESO", insertable = false, updatable = false),
        @JoinColumn(name = "CTP_CODIGO", referencedColumnName = "CTP_CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "CCI_CODIGO", referencedColumnName = "CCI_CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private SiprePlanillaDetalle siprePlanillaDetalle;

    public SiprePlanillaAdicional() {
    }

    public SiprePlanillaAdicional(SiprePlanillaAdicionalPK siprePlanillaAdicionalPK) {
        this.siprePlanillaAdicionalPK = siprePlanillaAdicionalPK;
    }

    public SiprePlanillaAdicional(String cpaMesAdicional, String cciCodigo, String ctpCodigo, String cpersonaNroAdm, String cplanillaMesProceso, short nplanillaNumProceso) {
        this.siprePlanillaAdicionalPK = new SiprePlanillaAdicionalPK(cpaMesAdicional, cciCodigo, ctpCodigo, cpersonaNroAdm, cplanillaMesProceso, nplanillaNumProceso);
    }

    public SiprePlanillaAdicionalPK getSiprePlanillaAdicionalPK() {
        return siprePlanillaAdicionalPK;
    }

    public void setSiprePlanillaAdicionalPK(SiprePlanillaAdicionalPK siprePlanillaAdicionalPK) {
        this.siprePlanillaAdicionalPK = siprePlanillaAdicionalPK;
    }

    public BigDecimal getNpdMtoConcepto() {
        return npdMtoConcepto;
    }

    public void setNpdMtoConcepto(BigDecimal npdMtoConcepto) {
        this.npdMtoConcepto = npdMtoConcepto;
    }

    public SiprePlanillaDetalle getSiprePlanillaDetalle() {
        return siprePlanillaDetalle;
    }

    public void setSiprePlanillaDetalle(SiprePlanillaDetalle siprePlanillaDetalle) {
        this.siprePlanillaDetalle = siprePlanillaDetalle;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (siprePlanillaAdicionalPK != null ? siprePlanillaAdicionalPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SiprePlanillaAdicional)) {
            return false;
        }
        SiprePlanillaAdicional other = (SiprePlanillaAdicional) object;
        if ((this.siprePlanillaAdicionalPK == null && other.siprePlanillaAdicionalPK != null) || (this.siprePlanillaAdicionalPK != null && !this.siprePlanillaAdicionalPK.equals(other.siprePlanillaAdicionalPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SiprePlanillaAdicional[ siprePlanillaAdicionalPK=" + siprePlanillaAdicionalPK + " ]";
    }
    
}
