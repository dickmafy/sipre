/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
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
@Table(name = "SIPRE_IMPORTAR_INFO")
@NamedQueries({
    @NamedQuery(name = "SipreImportarInfo.findAll", query = "SELECT s FROM SipreImportarInfo s")})
public class SipreImportarInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SipreImportarInfoPK sipreImportarInfoPK;
    @Column(name = "DII_FEC_CORTE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date diiFecCorte;
    @Column(name = "NII_CAN_REG_ORIG")
    private Integer niiCanRegOrig;
    @Column(name = "NII_CAN_REG_IMPO")
    private Integer niiCanRegImpo;
    @Size(max = 100)
    @Column(name = "VII_OBS")
    private String viiObs;
    @Size(max = 9)
    @Column(name = "CII_USU_MOD")
    private String ciiUsuMod;
    @Column(name = "DII_FEC_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date diiFecReg;
    @Column(name = "DII_FEC_MOD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date diiFecMod;
    @JoinColumn(name = "CUSUARIO_CODIGO", referencedColumnName = "CUSUARIO_CODIGO")
    @ManyToOne(optional = false)
    private SipreUsuario sipreUsuario;

    public SipreImportarInfo() {
    }

    public SipreImportarInfo(SipreImportarInfoPK sipreImportarInfoPK) {
        this.sipreImportarInfoPK = sipreImportarInfoPK;
    }

    public SipreImportarInfo(String ciiMesProceso, short niiNumProceso) {
        this.sipreImportarInfoPK = new SipreImportarInfoPK(ciiMesProceso, niiNumProceso);
    }

    public SipreImportarInfoPK getSipreImportarInfoPK() {
        return sipreImportarInfoPK;
    }

    public void setSipreImportarInfoPK(SipreImportarInfoPK sipreImportarInfoPK) {
        this.sipreImportarInfoPK = sipreImportarInfoPK;
    }

    public Date getDiiFecCorte() {
        return diiFecCorte;
    }

    public void setDiiFecCorte(Date diiFecCorte) {
        this.diiFecCorte = diiFecCorte;
    }

    public Integer getNiiCanRegOrig() {
        return niiCanRegOrig;
    }

    public void setNiiCanRegOrig(Integer niiCanRegOrig) {
        this.niiCanRegOrig = niiCanRegOrig;
    }

    public Integer getNiiCanRegImpo() {
        return niiCanRegImpo;
    }

    public void setNiiCanRegImpo(Integer niiCanRegImpo) {
        this.niiCanRegImpo = niiCanRegImpo;
    }

    public String getViiObs() {
        return viiObs;
    }

    public void setViiObs(String viiObs) {
        this.viiObs = viiObs;
    }

    public String getCiiUsuMod() {
        return ciiUsuMod;
    }

    public void setCiiUsuMod(String ciiUsuMod) {
        this.ciiUsuMod = ciiUsuMod;
    }

    public Date getDiiFecReg() {
        return diiFecReg;
    }

    public void setDiiFecReg(Date diiFecReg) {
        this.diiFecReg = diiFecReg;
    }

    public Date getDiiFecMod() {
        return diiFecMod;
    }

    public void setDiiFecMod(Date diiFecMod) {
        this.diiFecMod = diiFecMod;
    }

    public SipreUsuario getSipreUsuario() {
        return sipreUsuario;
    }

    public void setSipreUsuario(SipreUsuario sipreUsuario) {
        this.sipreUsuario = sipreUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sipreImportarInfoPK != null ? sipreImportarInfoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SipreImportarInfo)) {
            return false;
        }
        SipreImportarInfo other = (SipreImportarInfo) object;
        if ((this.sipreImportarInfoPK == null && other.sipreImportarInfoPK != null) || (this.sipreImportarInfoPK != null && !this.sipreImportarInfoPK.equals(other.sipreImportarInfoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreImportarInfo[ sipreImportarInfoPK=" + sipreImportarInfoPK + " ]";
    }
    
}
