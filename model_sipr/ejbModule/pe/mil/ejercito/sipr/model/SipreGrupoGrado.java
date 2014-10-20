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
@Table(name = "SIPRE_GRUPO_GRADO")
@NamedQueries({
    @NamedQuery(name = "SipreGrupoGrado.findAll", query = "SELECT s FROM SipreGrupoGrado s")})
public class SipreGrupoGrado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "CGG_CODIGO")
    private String cggCodigo;
    @Size(max = 60)
    @Column(name = "VGG_DSC_LARGA")
    private String vggDscLarga;
    @Size(max = 20)
    @Column(name = "VGG_DSC_CORTA")
    private String vggDscCorta;
    @Column(name = "CGG_TIPO_PERSONA")
    private String cggTipoPersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sipreGrupoGrado")
    private List<SipreEspealteMonto> sipreEspealteMontoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sipreGrupoGrado")
    private List<SipreGrado> sipreGradoList;

    public SipreGrupoGrado() {
    }

    public SipreGrupoGrado(String cggCodigo) {
        this.cggCodigo = cggCodigo;
    }

    public String getCggCodigo() {
        return cggCodigo;
    }

    public void setCggCodigo(String cggCodigo) {
        this.cggCodigo = cggCodigo;
    }

    public String getVggDscLarga() {
        return vggDscLarga;
    }

    public void setVggDscLarga(String vggDscLarga) {
        this.vggDscLarga = vggDscLarga;
    }

    public String getVggDscCorta() {
        return vggDscCorta;
    }

    public void setVggDscCorta(String vggDscCorta) {
        this.vggDscCorta = vggDscCorta;
    }

    public String getCggTipoPersona() {
        return cggTipoPersona;
    }

    public void setCggTipoPersona(String cggTipoPersona) {
        this.cggTipoPersona = cggTipoPersona;
    }

    public List<SipreEspealteMonto> getSipreEspealteMontoList() {
        return sipreEspealteMontoList;
    }

    public void setSipreEspealteMontoList(List<SipreEspealteMonto> sipreEspealteMontoList) {
        this.sipreEspealteMontoList = sipreEspealteMontoList;
    }

    public List<SipreGrado> getSipreGradoList() {
        return sipreGradoList;
    }

    public void setSipreGradoList(List<SipreGrado> sipreGradoList) {
        this.sipreGradoList = sipreGradoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cggCodigo != null ? cggCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SipreGrupoGrado)) {
            return false;
        }
        SipreGrupoGrado other = (SipreGrupoGrado) object;
        if ((this.cggCodigo == null && other.cggCodigo != null) || (this.cggCodigo != null && !this.cggCodigo.equals(other.cggCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreGrupoGrado[ cggCodigo=" + cggCodigo + " ]";
    }
    
}
