/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "SIPRE_AUDITORIA")
@NamedQueries({
    @NamedQuery(name = "SipreAuditoria.findAll", query = "SELECT s FROM SipreAuditoria s")})
public class SipreAuditoria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "AUD_CODIGO")
    private Long audCodigo;
    @Size(max = 40)
    @Column(name = "AUD_ACCION")
    private String audAccion;
    @Column(name = "AUD_FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date audFecha;
    @Size(max = 50)
    @Column(name = "AUD_TABLA")
    private String audTabla;
    @Size(max = 600)
    @Column(name = "AUD_VALOR_NUEVO")
    private String audValorNuevo;
    @Size(max = 600)
    @Column(name = "AUD_VALOR_ANTIGUO")
    private String audValorAntiguo;
    @Size(max = 30)
    @Column(name = "AUD_RESULTADO_ACCION")
    private String audResultadoAccion;
    @Size(max = 9)
    @Column(name = "CUSUARIO_CODIGO")
    private String cusuarioCodigo;

    public SipreAuditoria() {
    }

    public SipreAuditoria(Long audCodigo) {
        this.audCodigo = audCodigo;
    }

    public Long getAudCodigo() {
        return audCodigo;
    }

    public void setAudCodigo(Long audCodigo) {
        this.audCodigo = audCodigo;
    }

    public String getAudAccion() {
        return audAccion;
    }

    public void setAudAccion(String audAccion) {
        this.audAccion = audAccion;
    }

    public Date getAudFecha() {
        return audFecha;
    }

    public void setAudFecha(Date audFecha) {
        this.audFecha = audFecha;
    }

    public String getAudTabla() {
        return audTabla;
    }

    public void setAudTabla(String audTabla) {
        this.audTabla = audTabla;
    }

    public String getAudValorNuevo() {
        return audValorNuevo;
    }

    public void setAudValorNuevo(String audValorNuevo) {
        this.audValorNuevo = audValorNuevo;
    }

    public String getAudValorAntiguo() {
        return audValorAntiguo;
    }

    public void setAudValorAntiguo(String audValorAntiguo) {
        this.audValorAntiguo = audValorAntiguo;
    }

    public String getAudResultadoAccion() {
        return audResultadoAccion;
    }

    public void setAudResultadoAccion(String audResultadoAccion) {
        this.audResultadoAccion = audResultadoAccion;
    }

    public String getCusuarioCodigo() {
        return cusuarioCodigo;
    }

    public void setCusuarioCodigo(String cusuarioCodigo) {
        this.cusuarioCodigo = cusuarioCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (audCodigo != null ? audCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SipreAuditoria)) {
            return false;
        }
        SipreAuditoria other = (SipreAuditoria) object;
        if ((this.audCodigo == null && other.audCodigo != null) || (this.audCodigo != null && !this.audCodigo.equals(other.audCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreAuditoria[ audCodigo=" + audCodigo + " ]";
    }
    
}
