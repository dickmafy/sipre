package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the SIPRE_PERFIL database table.
 * 
 */
@Entity
@Table(name="SIPRE_PERFIL")
@NamedQuery(name="SiprePerfil.findAll", query="SELECT s FROM SiprePerfil s")
public class SiprePerfil implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CODIGO_PERFIL")
	private Long codigoPerfil;

	@Column(name="DESCRIPCION_PERFIL")
	private String descripcionPerfil;

	@Column(name="NOMBRE_PERFIL")
	private String nombrePerfil;

	public SiprePerfil() {
	}

	public Long getCodigoPerfil() {
		return this.codigoPerfil;
	}

	public void setCodigoPerfil(Long codigoPerfil) {
		this.codigoPerfil = codigoPerfil;
	}

	public String getDescripcionPerfil() {
		return this.descripcionPerfil;
	}

	public void setDescripcionPerfil(String descripcionPerfil) {
		this.descripcionPerfil = descripcionPerfil;
	}

	public String getNombrePerfil() {
		return this.nombrePerfil;
	}

	public void setNombrePerfil(String nombrePerfil) {
		this.nombrePerfil = nombrePerfil;
	}

}