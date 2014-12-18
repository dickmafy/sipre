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
@Table(name = "SIPRE_TIPO_PLANILLA")
@NamedQueries({
    @NamedQuery(name = "SipreTipoPlanilla.findAll", query = "SELECT s FROM SipreTipoPlanilla s")})
public class SipreTipoPlanilla implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "CTP_CODIGO")
    private String ctpCodigo;
    @Size(max = 50)
    @Column(name = "VTP_DSC")
    private String vtpDsc;
    @Column(name = "CTP_IND_AFE_NETO")
    private String ctpIndAfeNeto;
    @Column(name = "CTP_IND_AFE_IRENTA")
    private String ctpIndAfeIrenta;
    @OneToMany(mappedBy = "sipreTipoPlanilla")
    private List<SipreConceptoIngreso> sipreConceptoIngresoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sipreTipoPlanilla")
    private List<SipreDescuentoNoprocesado> sipreDescuentoNoprocesadoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sipreTipoPlanilla")
    private List<SipreCalculoDescuentoLey> sipreCalculoDescuentoLeyList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sipreTipoPlanilla")
    private List<SiprePlanillaDescuento> siprePlanillaDescuentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sipreTipoPlanilla")
    private List<SiprePlanillaOtro> siprePlanillaOtroList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sipreTipoPlanilla")
    private List<SipreTmpJudicial> sipreTmpJudicialList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sipreTipoPlanilla")
    private List<SipreExcepcion> sipreExcepcionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sipreTipoPlanilla")
    private List<SiprePlanillaDetalle> siprePlanillaDetalleList;

    public SipreTipoPlanilla() {
    }

    public SipreTipoPlanilla(String ctpCodigo) {
        this.ctpCodigo = ctpCodigo;
    }

    public String getCtpCodigo() {
        return ctpCodigo;
    }

    public void setCtpCodigo(String ctpCodigo) {
        this.ctpCodigo = ctpCodigo;
    }

    public String getVtpDsc() {
        return vtpDsc;
    }

    public void setVtpDsc(String vtpDsc) {
        this.vtpDsc = vtpDsc;
    }

    public String getCtpIndAfeNeto() {
        return ctpIndAfeNeto;
    }

    public void setCtpIndAfeNeto(String ctpIndAfeNeto) {
        this.ctpIndAfeNeto = ctpIndAfeNeto;
    }

    public String getCtpIndAfeIrenta() {
        return ctpIndAfeIrenta;
    }

    public void setCtpIndAfeIrenta(String ctpIndAfeIrenta) {
        this.ctpIndAfeIrenta = ctpIndAfeIrenta;
    }

    public List<SipreConceptoIngreso> getSipreConceptoIngresoList() {
        return sipreConceptoIngresoList;
    }

    public void setSipreConceptoIngresoList(List<SipreConceptoIngreso> sipreConceptoIngresoList) {
        this.sipreConceptoIngresoList = sipreConceptoIngresoList;
    }

    public List<SipreDescuentoNoprocesado> getSipreDescuentoNoprocesadoList() {
        return sipreDescuentoNoprocesadoList;
    }

    public void setSipreDescuentoNoprocesadoList(List<SipreDescuentoNoprocesado> sipreDescuentoNoprocesadoList) {
        this.sipreDescuentoNoprocesadoList = sipreDescuentoNoprocesadoList;
    }

    public List<SipreCalculoDescuentoLey> getSipreCalculoDescuentoLeyList() {
        return sipreCalculoDescuentoLeyList;
    }

    public void setSipreCalculoDescuentoLeyList(List<SipreCalculoDescuentoLey> sipreCalculoDescuentoLeyList) {
        this.sipreCalculoDescuentoLeyList = sipreCalculoDescuentoLeyList;
    }

    public List<SiprePlanillaDescuento> getSiprePlanillaDescuentoList() {
        return siprePlanillaDescuentoList;
    }

    public void setSiprePlanillaDescuentoList(List<SiprePlanillaDescuento> siprePlanillaDescuentoList) {
        this.siprePlanillaDescuentoList = siprePlanillaDescuentoList;
    }

    public List<SiprePlanillaOtro> getSiprePlanillaOtroList() {
        return siprePlanillaOtroList;
    }

    public void setSiprePlanillaOtroList(List<SiprePlanillaOtro> siprePlanillaOtroList) {
        this.siprePlanillaOtroList = siprePlanillaOtroList;
    }

    public List<SipreTmpJudicial> getSipreTmpJudicialList() {
        return sipreTmpJudicialList;
    }

    public void setSipreTmpJudicialList(List<SipreTmpJudicial> sipreTmpJudicialList) {
        this.sipreTmpJudicialList = sipreTmpJudicialList;
    }

    public List<SipreExcepcion> getSipreExcepcionList() {
        return sipreExcepcionList;
    }

    public void setSipreExcepcionList(List<SipreExcepcion> sipreExcepcionList) {
        this.sipreExcepcionList = sipreExcepcionList;
    }

    public List<SiprePlanillaDetalle> getSiprePlanillaDetalleList() {
        return siprePlanillaDetalleList;
    }

    public void setSiprePlanillaDetalleList(List<SiprePlanillaDetalle> siprePlanillaDetalleList) {
        this.siprePlanillaDetalleList = siprePlanillaDetalleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ctpCodigo != null ? ctpCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof SipreTipoPlanilla)) {
            return false;
        }
        SipreTipoPlanilla other = (SipreTipoPlanilla) object;
        if ((this.ctpCodigo == null && other.ctpCodigo != null) || (this.ctpCodigo != null && !this.ctpCodigo.equals(other.ctpCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pe.mil.ejercito.sipr.model.SipreTipoPlanilla[ ctpCodigo=" + ctpCodigo + " ]";
    }
    
}
