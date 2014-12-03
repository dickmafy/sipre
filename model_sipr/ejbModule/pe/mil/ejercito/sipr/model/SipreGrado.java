/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author DIEGO
 */
@Entity
@Table(name = "SIPRE_GRADO")
@NamedQueries({
    @NamedQuery(name = "SipreGrado.findAll", query = "SELECT s FROM SipreGrado s")})
public class SipreGrado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CGRADO_CODIGO")
    private String cgradoCodigo;
    @Size(max = 60)
    @Column(name = "VGRADO_DSC_LARGA")
    private String vgradoDscLarga;
    @Size(max = 20)
    @Column(name = "VGRADO_DSC_CORTA")
    private String vgradoDscCorta;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sipreGrado")
    private List<SiprePersona> siprePersonaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sipreGrado")
    private List<SipreIngresoGrado> sipreIngresoGradoList;
    @JoinColumn(name = "CGG_CODIGO", referencedColumnName = "CGG_CODIGO")
    @ManyToOne(optional = false)
    private SipreGrupoGrado sipreGrupoGrado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sipreGrado")
    private List<SiprePlanilla> siprePlanillaList;

    public SipreGrado() {
    }

    public SipreGrado(String cgradoCodigo) {
        this.cgradoCodigo = cgradoCodigo;
    }

    public String getCgradoCodigo() {
        return cgradoCodigo;
    }

    public void setCgradoCodigo(String cgradoCodigo) {
        this.cgradoCodigo = cgradoCodigo;
    }

    public String getVgradoDscLarga() {
        return vgradoDscLarga;
    }

    public void setVgradoDscLarga(String vgradoDscLarga) {
        this.vgradoDscLarga = vgradoDscLarga;
    }

    public String getVgradoDscCorta() {
        return vgradoDscCorta;
    }

    public void setVgradoDscCorta(String vgradoDscCorta) {
        this.vgradoDscCorta = vgradoDscCorta;
    }

    public List<SiprePersona> getSiprePersonaList() {
        return siprePersonaList;
    }

    public void setSiprePersonaList(List<SiprePersona> siprePersonaList) {
        this.siprePersonaList = siprePersonaList;
    }

    public List<SipreIngresoGrado> getSipreIngresoGradoList() {
        return sipreIngresoGradoList;
    }

    public void setSipreIngresoGradoList(List<SipreIngresoGrado> sipreIngresoGradoList) {
        this.sipreIngresoGradoList = sipreIngresoGradoList;
    }

    public SipreGrupoGrado getSipreGrupoGrado() {
        return sipreGrupoGrado;
    }

    public void setSipreGrupoGrado(SipreGrupoGrado sipreGrupoGrado) {
        this.sipreGrupoGrado = sipreGrupoGrado;
    }

    public List<SiprePlanilla> getSiprePlanillaList() {
        return siprePlanillaList;
    }

    public void setSiprePlanillaList(List<SiprePlanilla> siprePlanillaList) {
        this.siprePlanillaList = siprePlanillaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cgradoCodigo != null ? cgradoCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SipreGrado)) {
            return false;
        }
        SipreGrado other = (SipreGrado) object;
        if ((this.cgradoCodigo == null && other.cgradoCodigo != null) || (this.cgradoCodigo != null && !this.cgradoCodigo.equals(other.cgradoCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreGrado[ cgradoCodigo=" + cgradoCodigo + " ]";
    }
    
}
