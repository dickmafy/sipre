package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the SIPRE_BOLETA_DETALLE database table.
 * 
 */
@Embeddable
public class SipreBoletaDetallePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="CBC_MES_PROCESO", insertable=false, updatable=false)
	private String cbcMesProceso;

	@Column(name="CBC_NRO_ADM", insertable=false, updatable=false)
	private String cbcNroAdm;

	@Column(name="NBC_NUM_PROCESO", insertable=false, updatable=false)
	private long nbcNumProceso;

	@Column(name="CBD_TIP_PLANILLA")
	private String cbdTipPlanilla;

	@Column(name="NBD_SEC")
	private long nbdSec;

	public SipreBoletaDetallePK() {
	}
	public String getCbcMesProceso() {
		return this.cbcMesProceso;
	}
	public void setCbcMesProceso(String cbcMesProceso) {
		this.cbcMesProceso = cbcMesProceso;
	}
	public String getCbcNroAdm() {
		return this.cbcNroAdm;
	}
	public void setCbcNroAdm(String cbcNroAdm) {
		this.cbcNroAdm = cbcNroAdm;
	}
	public long getNbcNumProceso() {
		return this.nbcNumProceso;
	}
	public void setNbcNumProceso(long nbcNumProceso) {
		this.nbcNumProceso = nbcNumProceso;
	}
	public String getCbdTipPlanilla() {
		return this.cbdTipPlanilla;
	}
	public void setCbdTipPlanilla(String cbdTipPlanilla) {
		this.cbdTipPlanilla = cbdTipPlanilla;
	}
	public long getNbdSec() {
		return this.nbdSec;
	}
	public void setNbdSec(long nbdSec) {
		this.nbdSec = nbdSec;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof SipreBoletaDetallePK)) {
			return false;
		}
		SipreBoletaDetallePK castOther = (SipreBoletaDetallePK)other;
		return 
			this.cbcMesProceso.equals(castOther.cbcMesProceso)
			&& this.cbcNroAdm.equals(castOther.cbcNroAdm)
			&& (this.nbcNumProceso == castOther.nbcNumProceso)
			&& this.cbdTipPlanilla.equals(castOther.cbdTipPlanilla)
			&& (this.nbdSec == castOther.nbdSec);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cbcMesProceso.hashCode();
		hash = hash * prime + this.cbcNroAdm.hashCode();
		hash = hash * prime + ((int) (this.nbcNumProceso ^ (this.nbcNumProceso >>> 32)));
		hash = hash * prime + this.cbdTipPlanilla.hashCode();
		hash = hash * prime + ((int) (this.nbdSec ^ (this.nbdSec >>> 32)));
		
		return hash;
	}
}