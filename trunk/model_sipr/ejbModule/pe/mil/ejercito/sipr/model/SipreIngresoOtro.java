/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_INGRESO_OTRO")
@NamedQueries({
    @NamedQuery(name = "SipreIngresoOtro.findAll", query = "SELECT s FROM SipreIngresoOtro s")})
public class SipreIngresoOtro implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SipreIngresoOtroPK id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NIO_MTO")
    private BigDecimal nioMto;
    @Column(name = "DIO_FEC_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dioFecReg;
    @Column(name = "DIO_FEC_MOD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dioFecMod;
    @Size(max = 9)
    @Column(name = "CIO_USU_MOD")
    private String cioUsuMod;
    @JoinColumn(name = "CUSUARIO_CODIGO", referencedColumnName = "CUSUARIO_CODIGO")
    @ManyToOne(optional = false)
    private SipreUsuario sipreUsuario;
    @JoinColumn(name = "CPERSONA_NRO_ADM", referencedColumnName = "CPERSONA_NRO_ADM",insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SiprePersona siprePersona;
    @JoinColumn(name = "CCI_CODIGO", referencedColumnName = "CCI_CODIGO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SipreConceptoIngreso sipreConceptoIngreso;

    public SipreIngresoOtro() {
    }

    public SipreIngresoOtro(SipreIngresoOtroPK sipreIngresoOtroPK) {
        this.id = sipreIngresoOtroPK;
    }

    public SipreIngresoOtro(String cpersonaNroAdm, String cciCodigo) {
        this.id = new SipreIngresoOtroPK(cpersonaNroAdm, cciCodigo);
    }

    public SipreIngresoOtroPK getId() {
		return id;
	}

	public void setId(SipreIngresoOtroPK id) {
		this.id = id;
	}

    public BigDecimal getNioMto() {
        return nioMto;
    }

    public void setNioMto(BigDecimal nioMto) {
        this.nioMto = nioMto;
    }

    public Date getDioFecReg() {
        return dioFecReg;
    }

    public void setDioFecReg(Date dioFecReg) {
        this.dioFecReg = dioFecReg;
    }

    public Date getDioFecMod() {
        return dioFecMod;
    }

    public void setDioFecMod(Date dioFecMod) {
        this.dioFecMod = dioFecMod;
    }

    public String getCioUsuMod() {
        return cioUsuMod;
    }

    public void setCioUsuMod(String cioUsuMod) {
        this.cioUsuMod = cioUsuMod;
    }

    public SipreUsuario getSipreUsuario() {
        return sipreUsuario;
    }

    public void setSipreUsuario(SipreUsuario sipreUsuario) {
        this.sipreUsuario = sipreUsuario;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SipreIngresoOtro)) {
            return false;
        }
        SipreIngresoOtro other = (SipreIngresoOtro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreIngresoOtro[ sipreIngresoOtroPK=" + id + " ]";
    }
    
}
