package pe.mil.ejercito.sipr.descuento;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.ConceptoDescuentoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.ConceptoDescuentoLeyEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.DescuentoLeyDetalleEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.DescuentoLeyEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.EntidadCrediticiaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreConceptoDescuento;
import pe.mil.ejercito.sipr.model.SipreConceptoDescuentoLey;
import pe.mil.ejercito.sipr.model.SipreConceptoDescuentoLeyPK;
import pe.mil.ejercito.sipr.model.SipreDescuentoLey;
import pe.mil.ejercito.sipr.model.SipreDescuentoLeyDet;
import pe.mil.ejercito.sipr.model.SipreEntidadCrediticia;

@ManagedBean(name = "conceptoDescuentoLeyMb")
@ViewScoped
public class ConceptoDescuentoLeyMb extends MainContext implements Serializable {

	private static final long serialVersionUID = 1L;
	private UsuarioEjbRemote ejbUsuario;
	private ConceptoDescuentoLeyEjbRemote ejb;
	private EntidadCrediticiaEjbRemote ejbEntidadCrediticia;
	private DescuentoLeyEjbRemote ejbDescuentoLey;
	private DescuentoLeyDetalleEjbRemote ejbDescuentoLeyDetalle;
	private ConceptoDescuentoEjbRemote ejbConceptoDescuento;
	
	
	private SipreConceptoDescuentoLey bean;
	
	private List<SipreDescuentoLey> beanDescuentoLeyList;
	private List<SipreDescuentoLeyDet> beanDescuentoLeyDetList;
	private List<SipreConceptoDescuentoLey> beanList;
	private List<SipreEntidadCrediticia> beanEntidadesCrediticiasList;
	private List<SipreConceptoDescuento> beanConceptoDescuentoList;
	

	public ConceptoDescuentoLeyMb() {
		super();
		try {
			ejbUsuario 				= (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			ejb 					= (ConceptoDescuentoLeyEjbRemote) findServiceRemote(ConceptoDescuentoLeyEjbRemote.class);
			ejbEntidadCrediticia 	= (EntidadCrediticiaEjbRemote) findServiceRemote(EntidadCrediticiaEjbRemote.class);
			ejbDescuentoLey	= (DescuentoLeyEjbRemote) findServiceRemote(DescuentoLeyEjbRemote.class);
			ejbDescuentoLeyDetalle= (DescuentoLeyDetalleEjbRemote) findServiceRemote(DescuentoLeyDetalleEjbRemote.class);
			ejbConceptoDescuento= (ConceptoDescuentoEjbRemote) findServiceRemote(ConceptoDescuentoEjbRemote.class);
			
			beanList = ejb.findAll(ConstantesUtil.LISTAR_EJB_REMOTE);
			beanEntidadesCrediticiasList =  ejbEntidadCrediticia.findAll();
			beanDescuentoLeyDetList=  ejbDescuentoLeyDetalle.findAll();
			beanDescuentoLeyList =  ejbDescuentoLey.findAll();
			beanConceptoDescuentoList =  ejbConceptoDescuento.findAll();
			
			
			cleanBean();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateDescuentoLeyDetalle(){
		beanDescuentoLeyDetList = ejbDescuentoLeyDetalle.findAllByIdDescuentoLey(bean.getSipreConceptoDescuentoLeyPK().getCdlCodigo());
		System.out.println(beanDescuentoLeyList.size());
	}
	
	
	public void newBean(ActionEvent event) {
		cleanBean();
	}

	public void cleanBean() {
		bean = new SipreConceptoDescuentoLey();
		SipreConceptoDescuentoLeyPK pk = new SipreConceptoDescuentoLeyPK();
		SipreEntidadCrediticia sipreEntidadCrediticia = new SipreEntidadCrediticia();
		bean.setSipreConceptoDescuentoLeyPK(pk);
		bean.setSipreEntidadCrediticia(sipreEntidadCrediticia);
		SipreConceptoDescuento sipreConceptoDescuento = new SipreConceptoDescuento();
		bean.setSipreConceptoDescuento(sipreConceptoDescuento);
		
	}

	public void saveBean(ActionEvent event) {
		try {
				bean = ejb.persist(bean);
				showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA,
						SEVERITY_INFO);
		} catch (Exception e) {
			showMessage(
					ConstantesUtil.MENSAJE_RESPUESTA_ERROR_GENERAL,
					SEVERITY_ERROR);
		}
		beanList = ejb.findAll(100);
		cleanBean();
	}

	public void updateBean(ActionEvent event) {
		try {
				bean = ejb.merge(bean);
				showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA,
						SEVERITY_INFO);
		} catch (Exception e) {
			showMessage(
					ConstantesUtil.MENSAJE_RESPUESTA_ERROR_GENERAL,
					SEVERITY_ERROR);
		}
		beanList = ejb.findAll(100);
		cleanBean();
	}

	public SipreConceptoDescuentoLey getBean() {
		return bean;
	}

	public void setBean(SipreConceptoDescuentoLey bean) {
		this.bean = bean;
	}

	public List<SipreDescuentoLey> getBeanDescuentoLeyList() {
		return beanDescuentoLeyList;
	}

	public void setBeanDescuentoLeyList(List<SipreDescuentoLey> beanDescuentoLeyList) {
		this.beanDescuentoLeyList = beanDescuentoLeyList;
	}

	public List<SipreDescuentoLeyDet> getBeanDescuentoLeyDetList() {
		return beanDescuentoLeyDetList;
	}

	public void setBeanDescuentoLeyDetList(
			List<SipreDescuentoLeyDet> beanDescuentoLeyDetList) {
		this.beanDescuentoLeyDetList = beanDescuentoLeyDetList;
	}

	public List<SipreConceptoDescuentoLey> getBeanList() {
		return beanList;
	}

	public void setBeanList(List<SipreConceptoDescuentoLey> beanList) {
		this.beanList = beanList;
	}

	public List<SipreEntidadCrediticia> getBeanEntidadesCrediticiasList() {
		return beanEntidadesCrediticiasList;
	}

	public void setBeanEntidadesCrediticiasList(
			List<SipreEntidadCrediticia> beanEntidadesCrediticiasList) {
		this.beanEntidadesCrediticiasList = beanEntidadesCrediticiasList;
	}

	public List<SipreConceptoDescuento> getBeanConceptoDescuentoList() {
		return beanConceptoDescuentoList;
	}

	public void setBeanConceptoDescuentoList(
			List<SipreConceptoDescuento> beanConceptoDescuentoList) {
		this.beanConceptoDescuentoList = beanConceptoDescuentoList;
	}

	
		
}