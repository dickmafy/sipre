package pe.mil.ejercito.sipr.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Copere01
 */
@Embeddable
public class SipreTmpGuardiaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "CPERSONA_NRO_ADM")
    private String cpersonaNroAdm;
    @Basic(optional = false)
    @Column(name = "CCI_CODIGO")
    private String cciCodigo;
    @Basic(optional = false)
    @Column(name = "CTG_MES_GUARDIA")
    private String ctgMesGuardia;

    public SipreTmpGuardiaPK() {
    }

    public SipreTmpGuardiaPK(String cpersonaNroAdm, String cciCodigo, String ctgMesGuardia) {
        this.cpersonaNroAdm = cpersonaNroAdm;
        this.cciCodigo = cciCodigo;
        this.ctgMesGuardia = ctgMesGuardia;
    }

    public String getCpersonaNroAdm() {
        return cpersonaNroAdm;
    }

    public void setCpersonaNroAdm(String cpersonaNroAdm) {
        this.cpersonaNroAdm = cpersonaNroAdm;
    }

    public String getCciCodigo() {
        return cciCodigo;
    }

    public void setCciCodigo(String cciCodigo) {
        this.cciCodigo = cciCodigo;
    }

    public String getCtgMesGuardia() {
        return ctgMesGuardia;
    }

    public void setCtgMesGuardia(String ctgMesGuardia) {
        this.ctgMesGuardia = ctgMesGuardia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpersonaNroAdm != null ? cpersonaNroAdm.hashCode() : 0);
        hash += (cciCodigo != null ? cciCodigo.hashCode() : 0);
        hash += (ctgMesGuardia != null ? ctgMesGuardia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SipreTmpGuardiaPK)) {
            return false;
        }
        SipreTmpGuardiaPK other = (SipreTmpGuardiaPK) object;
        if ((this.cpersonaNroAdm == null && other.cpersonaNroAdm != null) || (this.cpersonaNroAdm != null && !this.cpersonaNroAdm.equals(other.cpersonaNroAdm))) {
            return false;
        }
        if ((this.cciCodigo == null && other.cciCodigo != null) || (this.cciCodigo != null && !this.cciCodigo.equals(other.cciCodigo))) {
            return false;
        }
        if ((this.ctgMesGuardia == null && other.ctgMesGuardia != null) || (this.ctgMesGuardia != null && !this.ctgMesGuardia.equals(other.ctgMesGuardia))) {
            return false;
        }
        return true;
    }

    @Override
	public String toString() {
		return String.format("SipreTmpGuardiaPK [cpersonaNroAdm=%s, cciCodigo=%s, ctgMesGuardia=%s]", cpersonaNroAdm, cciCodigo,
				ctgMesGuardia);
	}
    
}
