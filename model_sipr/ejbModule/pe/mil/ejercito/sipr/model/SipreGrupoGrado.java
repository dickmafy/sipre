package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the SIPRE_GRUPO_GRADO database table.
 * 
 */
@Entity
@Table(name="SIPRE_GRUPO_GRADO")
@NamedQuery(name="SipreGrupoGrado.findAll", query="SELECT s FROM SipreGrupoGrado s")
public class SipreGrupoGrado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CGG_CODIGO")
	private String cggCodigo;

	@Column(name="VGG_DSC")
	private String vggDsc;

	//bi-directional many-to-one association to SipreGrado
	@OneToMany(mappedBy="sipreGrupoGrado")
	private List<SipreGrado> sipreGrados;

	public SipreGrupoGrado() {
	}

	public String getCggCodigo() {
		return this.cggCodigo;
	}

	public void setCggCodigo(String cggCodigo) {
		this.cggCodigo = cggCodigo;
	}

	public String getVggDsc() {
		return this.vggDsc;
	}

	public void setVggDsc(String vggDsc) {
		this.vggDsc = vggDsc;
	}

	public List<SipreGrado> getSipreGrados() {
		return this.sipreGrados;
	}

	public void setSipreGrados(List<SipreGrado> sipreGrados) {
		this.sipreGrados = sipreGrados;
	}

	public SipreGrado addSipreGrado(SipreGrado sipreGrado) {
		getSipreGrados().add(sipreGrado);
		sipreGrado.setSipreGrupoGrado(this);

		return sipreGrado;
	}

	public SipreGrado removeSipreGrado(SipreGrado sipreGrado) {
		getSipreGrados().remove(sipreGrado);
		sipreGrado.setSipreGrupoGrado(null);

		return sipreGrado;
	}

}