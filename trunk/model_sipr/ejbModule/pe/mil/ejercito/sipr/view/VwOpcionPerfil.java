package pe.mil.ejercito.sipr.view;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the VW_OPCION_PERFIL database table.
 * 
 */
@Entity
@Table(name="VW_OPCION_PERFIL")
@NamedQueries({
	@NamedQuery(name="VwOpcionPerfil.findAll", query="SELECT v FROM VwOpcionPerfil v"),
	@NamedQuery(name="VwOpcionPerfil.findById", query="SELECT v FROM VwOpcionPerfil v where v.idPerfil=:idPerfil order by v.nivel, v.orden")
})

public class VwOpcionPerfil implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long estado;

	@Column(name="ESTADO_OS")
	private Long estadoOs;

	private String icon;

	@Id
	private Long id;

	@Column(name="ID_OPCION_SISTEMA")
	private Long idOpcionSistema;

	@Column(name="ID_PADRE")
	private Long idPadre;

	@Column(name = "CODIGO_PERFIL")
	private Long idPerfil;

	private Long nivel;

	private String nombre;

	@Column(name="NOMBRE_PERFIL")
	private String nombrePerfil;

	private String orden;

	private String pagina;

	@Column(name="TIPO_MENU")
	private Long tipoMenu;

	public VwOpcionPerfil() {
	}

	public Long getEstado() {
		return this.estado;
	}

	public void setEstado(Long estado) {
		this.estado = estado;
	}

	public Long getEstadoOs() {
		return this.estadoOs;
	}

	public void setEstadoOs(Long estadoOs) {
		this.estadoOs = estadoOs;
	}

	public String getIcon() {
		return this.icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdOpcionSistema() {
		return this.idOpcionSistema;
	}

	public void setIdOpcionSistema(Long idOpcionSistema) {
		this.idOpcionSistema = idOpcionSistema;
	}

	public Long getIdPadre() {
		return this.idPadre;
	}

	public void setIdPadre(Long idPadre) {
		this.idPadre = idPadre;
	}

	public Long getIdPerfil() {
		return this.idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public Long getNivel() {
		return this.nivel;
	}

	public void setNivel(Long nivel) {
		this.nivel = nivel;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombrePerfil() {
		return this.nombrePerfil;
	}

	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}

	public String getOrden() {
		return this.orden;
	}

	public void setOrden(String orden) {
		this.orden = orden;
	}

	public String getPagina() {
		return this.pagina;
	}

	public void setPagina(String pagina) {
		this.pagina = pagina;
	}

	public Long getTipoMenu() {
		return this.tipoMenu;
	}

	public void setTipoMenu(Long tipoMenu) {
		this.tipoMenu = tipoMenu;
	}

}