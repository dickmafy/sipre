/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_CONCEPTO_DESCUENTO_LEY")
@NamedQueries({
    @NamedQuery(name = "SipreConceptoDescuentoLey.findAll", query = "SELECT s FROM SipreConceptoDescuentoLey s")})
public class SipreConceptoDescuentoLey implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SipreConceptoDescuentoLeyPK sipreConceptoDescuentoLeyPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NCDL_POR_EMPLEADOR")
    private BigDecimal ncdlPorEmpleador;
    @Column(name = "NCDL_POR_EMPLEADO")
    private BigDecimal ncdlPorEmpleado;
    @Column(name = "CCDL_TIPO_PERSONA")
    private String ccdlTipoPersona;
    @Column(name = "CCDL_IND_POR_MONTO")
    private String ccdlIndPorMonto;
    @Column(name = "NCDL_MIN_APLICABLE")
    private BigDecimal ncdlMinAplicable;
    @Column(name = "NCDL_MAX_APLICABLE")
    private BigDecimal ncdlMaxAplicable;
    @Column(name = "CCDL_ESTADO")
    private String ccdlEstado;
    @JoinColumn(name = "CEC_CODIGO", referencedColumnName = "CEC_CODIGO")
    @ManyToOne
    private SipreEntidadCrediticia sipreEntidadCrediticia;
    @JoinColumns({
        @JoinColumn(name = "CDL_CODIGO", referencedColumnName = "CDL_CODIGO", insertable = false, updatable = false),
        @JoinColumn(name = "CDLD_CODIGO", referencedColumnName = "CDLD_CODIGO", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private SipreDescuentoLeyDet sipreDescuentoLeyDet;
    
    @Transient
    @JoinColumn(name = "CCD_CODIGO", referencedColumnName = "CCD_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SipreConceptoDescuento sipreConceptoDescuento;
    
    @OneToMany(mappedBy = "sipreConceptoDescuentoLey")
    private List<SipreTmpPlanillaDescuento> sipreTmpPlanillaDescuentoList;
    @OneToMany(mappedBy = "sipreConceptoDescuentoLey")
    private List<SiprePlanillaDescuento> siprePlanillaDescuentoList;

    public SipreConceptoDescuentoLey() {
    }

    public SipreConceptoDescuentoLey(SipreConceptoDescuentoLeyPK sipreConceptoDescuentoLeyPK) {
        this.sipreConceptoDescuentoLeyPK = sipreConceptoDescuentoLeyPK;
    }

    public SipreConceptoDescuentoLey(String cdldCodigo, String cdlCodigo, String ccdCodigo) {
        this.sipreConceptoDescuentoLeyPK = new SipreConceptoDescuentoLeyPK(cdldCodigo, cdlCodigo, ccdCodigo);
    }

    public SipreConceptoDescuentoLeyPK getSipreConceptoDescuentoLeyPK() {
        return sipreConceptoDescuentoLeyPK;
    }

    public void setSipreConceptoDescuentoLeyPK(SipreConceptoDescuentoLeyPK sipreConceptoDescuentoLeyPK) {
        this.sipreConceptoDescuentoLeyPK = sipreConceptoDescuentoLeyPK;
    }

    public BigDecimal getNcdlPorEmpleador() {
        return ncdlPorEmpleador;
    }

    public void setNcdlPorEmpleador(BigDecimal ncdlPorEmpleador) {
        this.ncdlPorEmpleador = ncdlPorEmpleador;
    }

    public BigDecimal getNcdlPorEmpleado() {
        return ncdlPorEmpleado;
    }

    public void setNcdlPorEmpleado(BigDecimal ncdlPorEmpleado) {
        this.ncdlPorEmpleado = ncdlPorEmpleado;
    }

    public String getCcdlTipoPersona() {
        return ccdlTipoPersona;
    }

    public void setCcdlTipoPersona(String ccdlTipoPersona) {
        this.ccdlTipoPersona = ccdlTipoPersona;
    }

    public String getCcdlIndPorMonto() {
        return ccdlIndPorMonto;
    }

    public void setCcdlIndPorMonto(String ccdlIndPorMonto) {
        this.ccdlIndPorMonto = ccdlIndPorMonto;
    }

    public BigDecimal getNcdlMinAplicable() {
        return ncdlMinAplicable;
    }

    public void setNcdlMinAplicable(BigDecimal ncdlMinAplicable) {
        this.ncdlMinAplicable = ncdlMinAplicable;
    }

    public BigDecimal getNcdlMaxAplicable() {
        return ncdlMaxAplicable;
    }

    public void setNcdlMaxAplicable(BigDecimal ncdlMaxAplicable) {
        this.ncdlMaxAplicable = ncdlMaxAplicable;
    }

    public String getCcdlEstado() {
        return ccdlEstado;
    }

    public void setCcdlEstado(String ccdlEstado) {
        this.ccdlEstado = ccdlEstado;
    }

    public SipreEntidadCrediticia getSipreEntidadCrediticia() {
        return sipreEntidadCrediticia;
    }

    public void setSipreEntidadCrediticia(SipreEntidadCrediticia sipreEntidadCrediticia) {
        this.sipreEntidadCrediticia = sipreEntidadCrediticia;
    }

    public SipreDescuentoLeyDet getSipreDescuentoLeyDet() {
        return sipreDescuentoLeyDet;
    }

    public void setSipreDescuentoLeyDet(SipreDescuentoLeyDet sipreDescuentoLeyDet) {
        this.sipreDescuentoLeyDet = sipreDescuentoLeyDet;
    }

    public SipreConceptoDescuento getSipreConceptoDescuento() {
        return sipreConceptoDescuento;
    }

    public void setSipreConceptoDescuento(SipreConceptoDescuento sipreConceptoDescuento) {
        this.sipreConceptoDescuento = sipreConceptoDescuento;
    }

    public List<SipreTmpPlanillaDescuento> getSipreTmpPlanillaDescuentoList() {
        return sipreTmpPlanillaDescuentoList;
    }

    public void setSipreTmpPlanillaDescuentoList(List<SipreTmpPlanillaDescuento> sipreTmpPlanillaDescuentoList) {
        this.sipreTmpPlanillaDescuentoList = sipreTmpPlanillaDescuentoList;
    }

    public List<SiprePlanillaDescuento> getSiprePlanillaDescuentoList() {
        return siprePlanillaDescuentoList;
    }

    public void setSiprePlanillaDescuentoList(List<SiprePlanillaDescuento> siprePlanillaDescuentoList) {
        this.siprePlanillaDescuentoList = siprePlanillaDescuentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sipreConceptoDescuentoLeyPK != null ? sipreConceptoDescuentoLeyPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SipreConceptoDescuentoLey)) {
            return false;
        }
        SipreConceptoDescuentoLey other = (SipreConceptoDescuentoLey) object;
        if ((this.sipreConceptoDescuentoLeyPK == null && other.sipreConceptoDescuentoLeyPK != null) || (this.sipreConceptoDescuentoLeyPK != null && !this.sipreConceptoDescuentoLeyPK.equals(other.sipreConceptoDescuentoLeyPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreConceptoDescuentoLey[ sipreConceptoDescuentoLeyPK=" + sipreConceptoDescuentoLeyPK + " ]";
    }
    
}
