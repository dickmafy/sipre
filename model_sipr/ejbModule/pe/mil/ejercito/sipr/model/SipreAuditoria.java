package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the SIPRE_AUDITORIA database table.
 * 
 */
@Entity
@Table(name="SIPRE_AUDITORIA")
@NamedQuery(name="SipreAuditoria.findAll", query="SELECT s FROM SipreAuditoria s")
public class SipreAuditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="\"aud_codigo\"")
	private Long audCodigo;

	@Column(name="\"aud_accion\"")
	private String audAccion;

	@Temporal(TemporalType.DATE)
	@Column(name="\"aud_fecha\"")
	private Date audFecha;

	@Column(name="\"aud_resultado_accion\"")
	private String audResultadoAccion;

	@Column(name="\"aud_tabla\"")
	private String audTabla;

	@Column(name="\"aud_valor_antiguo\"")
	private String audValorAntiguo;

	@Column(name="\"aud_valor_nuevo\"")
	private String audValorNuevo;

	@Column(name="\"cusuario_codigo\"")
	private String cusuarioCodigo;

	public SipreAuditoria() {
	}

	public Long getAudCodigo() {
		return this.audCodigo;
	}

	public void setAudCodigo(Long audCodigo) {
		this.audCodigo = audCodigo;
	}

	public String getAudAccion() {
		return this.audAccion;
	}

	public void setAudAccion(String audAccion) {
		this.audAccion = audAccion;
	}

	public Date getAudFecha() {
		return this.audFecha;
	}

	public void setAudFecha(Date audFecha) {
		this.audFecha = audFecha;
	}

	public String getAudResultadoAccion() {
		return this.audResultadoAccion;
	}

	public void setAudResultadoAccion(String audResultadoAccion) {
		this.audResultadoAccion = audResultadoAccion;
	}

	public String getAudTabla() {
		return this.audTabla;
	}

	public void setAudTabla(String audTabla) {
		this.audTabla = audTabla;
	}

	public String getAudValorAntiguo() {
		return this.audValorAntiguo;
	}

	public void setAudValorAntiguo(String audValorAntiguo) {
		this.audValorAntiguo = audValorAntiguo;
	}

	public String getAudValorNuevo() {
		return this.audValorNuevo;
	}

	public void setAudValorNuevo(String audValorNuevo) {
		this.audValorNuevo = audValorNuevo;
	}

	public String getCusuarioCodigo() {
		return this.cusuarioCodigo;
	}

	public void setCusuarioCodigo(String cusuarioCodigo) {
		this.cusuarioCodigo = cusuarioCodigo;
	}

}