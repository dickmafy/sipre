/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

/**
 *
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_DIFERENCIA")
@NamedQueries({
    @NamedQuery(name = "SipreDiferencia.findAll", query = "SELECT s FROM SipreDiferencia s")})
public class SipreDiferencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SipreDiferenciaPK sipreDiferenciaPK;
    @Column(name = "NDIFERENCIA_CNT_REG_VERIF")
    private Integer ndiferenciaCntRegVerif;
    @Column(name = "NDIFERENCIA_CNT_REG_OBS")
    private Integer ndiferenciaCntRegObs;
    @Column(name = "NDIFERENCIA_CNT_REG_CORREC")
    private Integer ndiferenciaCntRegCorrec;
    @Size(max = 9)
    @Column(name = "CDIFERENCIA_USU_MOD")
    private String cdiferenciaUsuMod;
    @Column(name = "DDIFERENCIA_FEC_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ddiferenciaFecReg;
    @Column(name = "DDIFERENCIA_FEC_MOD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ddiferenciaFecMod;
    @JoinColumn(name = "CUSUARIO_CODIGO", referencedColumnName = "CUSUARIO_CODIGO")
    @ManyToOne(optional = false)
    private SipreUsuario sipreUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sipreDiferencia")
    private List<SipreDiferenciaDetalle> sipreDiferenciaDetalleList;

    public SipreDiferencia() {
    }

    public SipreDiferencia(SipreDiferenciaPK sipreDiferenciaPK) {
        this.sipreDiferenciaPK = sipreDiferenciaPK;
    }

    public SipreDiferencia(String cdiferenciaMesProceso, short ndiferenciaNumProceso) {
        this.sipreDiferenciaPK = new SipreDiferenciaPK(cdiferenciaMesProceso, ndiferenciaNumProceso);
    }

    public SipreDiferenciaPK getSipreDiferenciaPK() {
        return sipreDiferenciaPK;
    }

    public void setSipreDiferenciaPK(SipreDiferenciaPK sipreDiferenciaPK) {
        this.sipreDiferenciaPK = sipreDiferenciaPK;
    }

    public Integer getNdiferenciaCntRegVerif() {
        return ndiferenciaCntRegVerif;
    }

    public void setNdiferenciaCntRegVerif(Integer ndiferenciaCntRegVerif) {
        this.ndiferenciaCntRegVerif = ndiferenciaCntRegVerif;
    }

    public Integer getNdiferenciaCntRegObs() {
        return ndiferenciaCntRegObs;
    }

    public void setNdiferenciaCntRegObs(Integer ndiferenciaCntRegObs) {
        this.ndiferenciaCntRegObs = ndiferenciaCntRegObs;
    }

    public Integer getNdiferenciaCntRegCorrec() {
        return ndiferenciaCntRegCorrec;
    }

    public void setNdiferenciaCntRegCorrec(Integer ndiferenciaCntRegCorrec) {
        this.ndiferenciaCntRegCorrec = ndiferenciaCntRegCorrec;
    }

    public String getCdiferenciaUsuMod() {
        return cdiferenciaUsuMod;
    }

    public void setCdiferenciaUsuMod(String cdiferenciaUsuMod) {
        this.cdiferenciaUsuMod = cdiferenciaUsuMod;
    }

    public Date getDdiferenciaFecReg() {
        return ddiferenciaFecReg;
    }

    public void setDdiferenciaFecReg(Date ddiferenciaFecReg) {
        this.ddiferenciaFecReg = ddiferenciaFecReg;
    }

    public Date getDdiferenciaFecMod() {
        return ddiferenciaFecMod;
    }

    public void setDdiferenciaFecMod(Date ddiferenciaFecMod) {
        this.ddiferenciaFecMod = ddiferenciaFecMod;
    }

    public SipreUsuario getSipreUsuario() {
        return sipreUsuario;
    }

    public void setSipreUsuario(SipreUsuario sipreUsuario) {
        this.sipreUsuario = sipreUsuario;
    }

    public List<SipreDiferenciaDetalle> getSipreDiferenciaDetalleList() {
        return sipreDiferenciaDetalleList;
    }

    public void setSipreDiferenciaDetalleList(List<SipreDiferenciaDetalle> sipreDiferenciaDetalleList) {
        this.sipreDiferenciaDetalleList = sipreDiferenciaDetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sipreDiferenciaPK != null ? sipreDiferenciaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SipreDiferencia)) {
            return false;
        }
        SipreDiferencia other = (SipreDiferencia) object;
        if ((this.sipreDiferenciaPK == null && other.sipreDiferenciaPK != null) || (this.sipreDiferenciaPK != null && !this.sipreDiferenciaPK.equals(other.sipreDiferenciaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreDiferencia[ sipreDiferenciaPK=" + sipreDiferenciaPK + " ]";
    }
    
}
