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

/**
 *
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_TMP_JUDICIAL")
@NamedQueries({
    @NamedQuery(name = "SipreTmpJudicial.findAll", query = "SELECT s FROM SipreTmpJudicial s")})
public class SipreTmpJudicial implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SipreTmpJudicialPK sipreTmpJudicialPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NTJ_MONTO")
    private BigDecimal ntjMonto;
    @Column(name = "NTJ_PORCENJAJE")
    private BigDecimal ntjPorcenjaje;
    @JoinColumn(name = "CTP_CODIGO", referencedColumnName = "CTP_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SipreTipoPlanilla sipreTipoPlanilla;
    @JoinColumn(name = "CPERSONA_NRO_ADM", referencedColumnName = "CPERSONA_NRO_ADM", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SiprePersona siprePersona;
    @JoinColumn(name = "CEC_CODIGO", referencedColumnName = "CEC_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SipreEntidadCrediticia sipreEntidadCrediticia;

    public SipreTmpJudicial() {
    }

    public SipreTmpJudicial(SipreTmpJudicialPK sipreTmpJudicialPK) {
        this.sipreTmpJudicialPK = sipreTmpJudicialPK;
    }

    public SipreTmpJudicial(String ctjMesProceso, String cpersonaNroAdm, String ctpCodigo, String cecCodigo) {
        this.sipreTmpJudicialPK = new SipreTmpJudicialPK(ctjMesProceso, cpersonaNroAdm, ctpCodigo, cecCodigo);
    }

    public SipreTmpJudicialPK getSipreTmpJudicialPK() {
        return sipreTmpJudicialPK;
    }

    public void setSipreTmpJudicialPK(SipreTmpJudicialPK sipreTmpJudicialPK) {
        this.sipreTmpJudicialPK = sipreTmpJudicialPK;
    }

    public BigDecimal getNtjMonto() {
        return ntjMonto;
    }

    public void setNtjMonto(BigDecimal ntjMonto) {
        this.ntjMonto = ntjMonto;
    }

    public BigDecimal getNtjPorcenjaje() {
        return ntjPorcenjaje;
    }

    public void setNtjPorcenjaje(BigDecimal ntjPorcenjaje) {
        this.ntjPorcenjaje = ntjPorcenjaje;
    }

    public SipreTipoPlanilla getSipreTipoPlanilla() {
        return sipreTipoPlanilla;
    }

    public void setSipreTipoPlanilla(SipreTipoPlanilla sipreTipoPlanilla) {
        this.sipreTipoPlanilla = sipreTipoPlanilla;
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
        hash += (sipreTmpJudicialPK != null ? sipreTmpJudicialPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SipreTmpJudicial)) {
            return false;
        }
        SipreTmpJudicial other = (SipreTmpJudicial) object;
        if ((this.sipreTmpJudicialPK == null && other.sipreTmpJudicialPK != null) || (this.sipreTmpJudicialPK != null && !this.sipreTmpJudicialPK.equals(other.sipreTmpJudicialPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreTmpJudicial[ sipreTmpJudicialPK=" + sipreTmpJudicialPK + " ]";
    }
    
}
