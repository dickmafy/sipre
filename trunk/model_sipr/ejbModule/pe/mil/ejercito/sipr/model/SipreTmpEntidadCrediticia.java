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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_TMP_ENTIDAD_CREDITICIA")
@NamedQueries({
    @NamedQuery(name = "SipreTmpEntidadCrediticia.findAll", query = "SELECT s FROM SipreTmpEntidadCrediticia s")})
public class SipreTmpEntidadCrediticia implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SipreTmpEntidadCrediticiaPK sipreTmpEntidadCrediticiaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NTEC_MONTO")
    private BigDecimal ntecMonto;
    @Column(name = "NTEC_NRO_CUOTA")
    private Integer ntecNroCuota;
    @Size(max = 10)
    @Column(name = "CTEC_NRO_CHEQUE")
    private String ctecNroCheque;
    @Column(name = "NTEC_MTO_ANTERIOR")
    private BigDecimal ntecMtoAnterior;
    @JoinColumn(name = "CPERSONA_NRO_ADM", referencedColumnName = "CPERSONA_NRO_ADM", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SiprePersona siprePersona;
    @JoinColumn(name = "CEC_CODIGO", referencedColumnName = "CEC_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SipreEntidadCrediticia sipreEntidadCrediticia;

    public SipreTmpEntidadCrediticia() {
    }

    public SipreTmpEntidadCrediticia(SipreTmpEntidadCrediticiaPK sipreTmpEntidadCrediticiaPK) {
        this.sipreTmpEntidadCrediticiaPK = sipreTmpEntidadCrediticiaPK;
    }

    public SipreTmpEntidadCrediticia(String cpersonaNroAdm, String cecCodigo, String ctecMesProceso, String ctecTipoMovim) {
        this.sipreTmpEntidadCrediticiaPK = new SipreTmpEntidadCrediticiaPK(cpersonaNroAdm, cecCodigo, ctecMesProceso, ctecTipoMovim);
    }

    public SipreTmpEntidadCrediticiaPK getSipreTmpEntidadCrediticiaPK() {
        return sipreTmpEntidadCrediticiaPK;
    }

    public void setSipreTmpEntidadCrediticiaPK(SipreTmpEntidadCrediticiaPK sipreTmpEntidadCrediticiaPK) {
        this.sipreTmpEntidadCrediticiaPK = sipreTmpEntidadCrediticiaPK;
    }

    public BigDecimal getNtecMonto() {
        return ntecMonto;
    }

    public void setNtecMonto(BigDecimal ntecMonto) {
        this.ntecMonto = ntecMonto;
    }

    public Integer getNtecNroCuota() {
        return ntecNroCuota;
    }

    public void setNtecNroCuota(Integer ntecNroCuota) {
        this.ntecNroCuota = ntecNroCuota;
    }

    public String getCtecNroCheque() {
        return ctecNroCheque;
    }

    public void setCtecNroCheque(String ctecNroCheque) {
        this.ctecNroCheque = ctecNroCheque;
    }

    public BigDecimal getNtecMtoAnterior() {
        return ntecMtoAnterior;
    }

    public void setNtecMtoAnterior(BigDecimal ntecMtoAnterior) {
        this.ntecMtoAnterior = ntecMtoAnterior;
    }

    public SiprePersona getSiprePersona() {
        return siprePersona;
    }

    public void setSiprePersona(SiprePersona siprePersona) {
        this.siprePersona = siprePersona;
    }

    public SipreEntidadCrediticia getSipreEntidadCrediticia() {
        return sipreEntidadCrediticia;
    }

    public void setSipreEntidadCrediticia(SipreEntidadCrediticia sipreEntidadCrediticia) {
        this.sipreEntidadCrediticia = sipreEntidadCrediticia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sipreTmpEntidadCrediticiaPK != null ? sipreTmpEntidadCrediticiaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SipreTmpEntidadCrediticia)) {
            return false;
        }
        SipreTmpEntidadCrediticia other = (SipreTmpEntidadCrediticia) object;
        if ((this.sipreTmpEntidadCrediticiaPK == null && other.sipreTmpEntidadCrediticiaPK != null) || (this.sipreTmpEntidadCrediticiaPK != null && !this.sipreTmpEntidadCrediticiaPK.equals(other.sipreTmpEntidadCrediticiaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreTmpEntidadCrediticia[ sipreTmpEntidadCrediticiaPK=" + sipreTmpEntidadCrediticiaPK + " ]";
    }
    
}
