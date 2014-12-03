/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_USUARIO")
@NamedQueries({
	@NamedQuery(name="SipreUsuario.validarUsuario", query="SELECT s FROM SipreUsuario s where s.vusuarioNom=:nickname and s.vusuarioPass=:clave"),
    @NamedQuery(name = "SipreUsuario.findAll", query = "SELECT s FROM SipreUsuario s")})
public class SipreUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "CUSUARIO_CODIGO")
    private String cusuarioCodigo;
    @Size(max = 30)
    @Column(name = "VUSUARIO_NOM")
    private String vusuarioNom;
    @Size(max = 100)
    @Column(name = "VUSUARIO_PASS")
    private String vusuarioPass;
    @Column(name = "DUSUARIO_FEC_REG")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dusuarioFecReg;
    @Column(name = "CUSUARIO_EST")
    private String cusuarioEst;
    @Column(name = "DUSUARIO_FEC_MOD")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dusuarioFecMod;
    @JoinTable(name = "SIPRE_USUARIO_ROL", joinColumns = {
        @JoinColumn(name = "CUSUARIO_CODIGO", referencedColumnName = "CUSUARIO_CODIGO")}, inverseJoinColumns = {
        @JoinColumn(name = "CROL_CODIGO", referencedColumnName = "CROL_CODIGO")})
    @ManyToMany
    private List<SipreRol> sipreRolList;
   

    public SipreUsuario() {
    }

    public SipreUsuario(String cusuarioCodigo) {
        this.cusuarioCodigo = cusuarioCodigo;
    }

    public String getCusuarioCodigo() {
        return cusuarioCodigo;
    }

    public void setCusuarioCodigo(String cusuarioCodigo) {
        this.cusuarioCodigo = cusuarioCodigo;
    }

    public String getVusuarioNom() {
        return vusuarioNom;
    }

    public void setVusuarioNom(String vusuarioNom) {
        this.vusuarioNom = vusuarioNom;
    }

    public String getVusuarioPass() {
        return vusuarioPass;
    }

    public void setVusuarioPass(String vusuarioPass) {
        this.vusuarioPass = vusuarioPass;
    }

    public Date getDusuarioFecReg() {
        return dusuarioFecReg;
    }

    public void setDusuarioFecReg(Date dusuarioFecReg) {
        this.dusuarioFecReg = dusuarioFecReg;
    }

    public String getCusuarioEst() {
        return cusuarioEst;
    }

    public void setCusuarioEst(String cusuarioEst) {
        this.cusuarioEst = cusuarioEst;
    }

    public Date getDusuarioFecMod() {
        return dusuarioFecMod;
    }

    public void setDusuarioFecMod(Date dusuarioFecMod) {
        this.dusuarioFecMod = dusuarioFecMod;
    }

    public List<SipreRol> getSipreRolList() {
        return sipreRolList;
    }

    public void setSipreRolList(List<SipreRol> sipreRolList) {
        this.sipreRolList = sipreRolList;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cusuarioCodigo != null ? cusuarioCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SipreUsuario)) {
            return false;
        }
        SipreUsuario other = (SipreUsuario) object;
        if ((this.cusuarioCodigo == null && other.cusuarioCodigo != null) || (this.cusuarioCodigo != null && !this.cusuarioCodigo.equals(other.cusuarioCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreUsuario[ cusuarioCodigo=" + cusuarioCodigo + " ]";
    }
    
}
