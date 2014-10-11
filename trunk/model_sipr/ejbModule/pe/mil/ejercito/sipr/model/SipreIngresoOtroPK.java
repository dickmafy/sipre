package pe.mil.ejercito.sipr.model;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author DIEGO
 */
@Embeddable
public class SipreIngresoOtroPK implements Serializable {
   
	private static final long serialVersionUID = 1L;
    
	@Size(min = 1, max = 4)
    @Column(name = "CCI_CODIGO")
    private String cciCodigo;
    
    @Size(min = 1, max = 9)
    @Column(name = "CPERSONA_NRO_ADM")
    private String cpersonaNroAdm;

    public SipreIngresoOtroPK() {
    }

    public SipreIngresoOtroPK(String cciCodigo, String cpersonaNroAdm) {
        this.cciCodigo = cciCodigo;
        this.cpersonaNroAdm = cpersonaNroAdm;
    }

    public String getCciCodigo() {
        return cciCodigo;
    }

    public void setCciCodigo(String cciCodigo) {
        this.cciCodigo = cciCodigo;
    }

    public String getCpersonaNroAdm() {
        return cpersonaNroAdm;
    }

    public void setCpersonaNroAdm(String cpersonaNroAdm) {
        this.cpersonaNroAdm = cpersonaNroAdm;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cciCodigo != null ? cciCodigo.hashCode() : 0);
        hash += (cpersonaNroAdm != null ? cpersonaNroAdm.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SipreIngresoOtroPK)) {
            return false;
        }
        SipreIngresoOtroPK other = (SipreIngresoOtroPK) object;
        if ((this.cciCodigo == null && other.cciCodigo != null) || (this.cciCodigo != null && !this.cciCodigo.equals(other.cciCodigo))) {
            return false;
        }
        if ((this.cpersonaNroAdm == null && other.cpersonaNroAdm != null) || (this.cpersonaNroAdm != null && !this.cpersonaNroAdm.equals(other.cpersonaNroAdm))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SIPRE.SipreIngresoOtroPK[ cciCodigo=" + cciCodigo + ", cpersonaNroAdm=" + cpersonaNroAdm + " ]";
    }
    
}
