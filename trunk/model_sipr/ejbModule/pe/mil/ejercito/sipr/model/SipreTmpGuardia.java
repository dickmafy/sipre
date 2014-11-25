package pe.mil.ejercito.sipr.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Copere01
 */
@Entity
@Table(name = "SIPRE_TMP_GUARDIA")
@NamedQueries({
    @NamedQuery(name = "SipreTmpGuardia.findAll", query = "SELECT s FROM SipreTmpGuardia s")})
public class SipreTmpGuardia implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SipreTmpGuardiaPK sipreTmpGuardiaPK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "NTG_MTO_GESTION")
    private BigDecimal ntgMtoGestion;
    @Column(name = "NTG_MTO_PAGADO")
    private BigDecimal ntgMtoPagado;
    @Column(name = "VTG_APE_NOM")
    private String vtgApeNom;
    @Column(name = "CTG_IND_SITUACION")
    private String ctgIndSituacion;
    @Column(name = "NTG_MTO_REINTEGRO")
    private BigDecimal ntgMtoReintegro;
    @Basic(optional = false)
    @Column(name = "MES_PROCESO")
    private String mesProceso;

    public SipreTmpGuardia() {
    }

    public SipreTmpGuardia(SipreTmpGuardiaPK sipreTmpGuardiaPK) {
        this.sipreTmpGuardiaPK = sipreTmpGuardiaPK;
    }

    public SipreTmpGuardia(SipreTmpGuardiaPK sipreTmpGuardiaPK, String mesProceso) {
        this.sipreTmpGuardiaPK = sipreTmpGuardiaPK;
        this.mesProceso = mesProceso;
    }

    public SipreTmpGuardia(String cpersonaNroAdm, String cciCodigo, String ctgMesGuardia) {
        this.sipreTmpGuardiaPK = new SipreTmpGuardiaPK(cpersonaNroAdm, cciCodigo, ctgMesGuardia);
    }

    public SipreTmpGuardiaPK getSipreTmpGuardiaPK() {
        return sipreTmpGuardiaPK;
    }

    public void setSipreTmpGuardiaPK(SipreTmpGuardiaPK sipreTmpGuardiaPK) {
        this.sipreTmpGuardiaPK = sipreTmpGuardiaPK;
    }

    public BigDecimal getNtgMtoGestion() {
        return ntgMtoGestion;
    }

    public void setNtgMtoGestion(BigDecimal ntgMtoGestion) {
        this.ntgMtoGestion = ntgMtoGestion;
    }

    public BigDecimal getNtgMtoPagado() {
        return ntgMtoPagado;
    }

    public void setNtgMtoPagado(BigDecimal ntgMtoPagado) {
        this.ntgMtoPagado = ntgMtoPagado;
    }

    public String getVtgApeNom() {
        return vtgApeNom;
    }

    public void setVtgApeNom(String vtgApeNom) {
        this.vtgApeNom = vtgApeNom;
    }

    public String getCtgIndSituacion() {
        return ctgIndSituacion;
    }

    public void setCtgIndSituacion(String ctgIndSituacion) {
        this.ctgIndSituacion = ctgIndSituacion;
    }

    public BigDecimal getNtgMtoReintegro() {
        return ntgMtoReintegro;
    }

    public void setNtgMtoReintegro(BigDecimal ntgMtoReintegro) {
        this.ntgMtoReintegro = ntgMtoReintegro;
    }

    public String getMesProceso() {
        return mesProceso;
    }

    public void setMesProceso(String mesProceso) {
        this.mesProceso = mesProceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sipreTmpGuardiaPK != null ? sipreTmpGuardiaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SipreTmpGuardia)) {
            return false;
        }
        SipreTmpGuardia other = (SipreTmpGuardia) object;
        if ((this.sipreTmpGuardiaPK == null && other.sipreTmpGuardiaPK != null) || (this.sipreTmpGuardiaPK != null && !this.sipreTmpGuardiaPK.equals(other.sipreTmpGuardiaPK))) {
            return false;
        }
        return true;
    }

    @Override
	public String toString() {
		return "SipreTmpGuardia [sipreTmpGuardiaPK=" + sipreTmpGuardiaPK + ", ntgMtoGestion=" + ntgMtoGestion + ", ntgMtoPagado="
				+ ntgMtoPagado + ", vtgApeNom=" + vtgApeNom + ", ctgIndSituacion=" + ctgIndSituacion + ", ntgMtoReintegro="
				+ ntgMtoReintegro + ", mesProceso=" + mesProceso + "]";
	}
    
}
