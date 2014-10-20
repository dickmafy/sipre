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
@Table(name = "SIPRE_TMP_BONIFICACION")
@NamedQueries({
    @NamedQuery(name = "SipreTmpBonificacion.findAll", query = "SELECT s FROM SipreTmpBonificacion s")})
public class SipreTmpBonificacion implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SipreTmpBonificacionPK sipreTmpBonificacionPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NTB_MONTO")
    private BigDecimal ntbMonto;
    @Size(max = 80)
    @Column(name = "VTB_APE_NOM")
    private String vtbApeNom;
    @Column(name = "CTB_IND_SITUACION")
    private Character ctbIndSituacion;
    @JoinColumn(name = "CPERSONA_NRO_ADM", referencedColumnName = "CPERSONA_NRO_ADM", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SiprePersona siprePersona;
    @JoinColumn(name = "CCI_CODIGO", referencedColumnName = "CCI_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SipreConceptoIngreso sipreConceptoIngreso;

    public SipreTmpBonificacion() {
    }

    public SipreTmpBonificacion(SipreTmpBonificacionPK sipreTmpBonificacionPK) {
        this.sipreTmpBonificacionPK = sipreTmpBonificacionPK;
    }

    public SipreTmpBonificacion(String cpersonaNroAdm, String cciCodigo, String ctbMesBonificacion) {
        this.sipreTmpBonificacionPK = new SipreTmpBonificacionPK(cpersonaNroAdm, cciCodigo, ctbMesBonificacion);
    }

    public SipreTmpBonificacionPK getSipreTmpBonificacionPK() {
        return sipreTmpBonificacionPK;
    }

    public void setSipreTmpBonificacionPK(SipreTmpBonificacionPK sipreTmpBonificacionPK) {
        this.sipreTmpBonificacionPK = sipreTmpBonificacionPK;
    }

    public BigDecimal getNtbMonto() {
        return ntbMonto;
    }

    public void setNtbMonto(BigDecimal ntbMonto) {
        this.ntbMonto = ntbMonto;
    }

    public String getVtbApeNom() {
        return vtbApeNom;
    }

    public void setVtbApeNom(String vtbApeNom) {
        this.vtbApeNom = vtbApeNom;
    }

    public Character getCtbIndSituacion() {
        return ctbIndSituacion;
    }

    public void setCtbIndSituacion(Character ctbIndSituacion) {
        this.ctbIndSituacion = ctbIndSituacion;
    }

    public SiprePersona getSiprePersona() {
        return siprePersona;
    }

    public void setSiprePersona(SiprePersona siprePersona) {
        this.siprePersona = siprePersona;
    }

    public SipreConceptoIngreso getSipreConceptoIngreso() {
        return sipreConceptoIngreso;
    }

    public void setSipreConceptoIngreso(SipreConceptoIngreso sipreConceptoIngreso) {
        this.sipreConceptoIngreso = sipreConceptoIngreso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sipreTmpBonificacionPK != null ? sipreTmpBonificacionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SipreTmpBonificacion)) {
            return false;
        }
        SipreTmpBonificacion other = (SipreTmpBonificacion) object;
        if ((this.sipreTmpBonificacionPK == null && other.sipreTmpBonificacionPK != null) || (this.sipreTmpBonificacionPK != null && !this.sipreTmpBonificacionPK.equals(other.sipreTmpBonificacionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreTmpBonificacion[ sipreTmpBonificacionPK=" + sipreTmpBonificacionPK + " ]";
    }
    
}
