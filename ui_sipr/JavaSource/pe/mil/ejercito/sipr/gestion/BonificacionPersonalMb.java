package pe.mil.ejercito.sipr.gestion;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.GenericResponseBean;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejb.TmpBonificacionEjbBean;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreTmpBonificacion;


@ManagedBean(name = "bonificacionPersonal")
@ViewScoped
public class BonificacionPersonalMb extends MainContext implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private UsuarioEjbRemote ejbUsuario;
	private TmpBonificacionEjbBean ejbBonificacion;
	private SipreTmpBonificacion bean;
	private GenericResponseBean<SipreTmpBonificacion> sessionBean ; 
	
	public BonificacionPersonalMb(){
		super();
		try{
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			ejbBonificacion = (TmpBonificacionEjbBean) findServiceRemote(TmpBonificacionEjbBean.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	public String goBonificacionPersonalMb(){
		sessionBean = new  GenericResponseBean<>();
		sessionBean.setObjeto(bean);
		registrarVariable("vbonificacion", sessionBean);
		return redirecciona("/modules/parametro/parametroDetalle");
	}
	
	public void newBean(ActionEvent event) {
		bean = new SipreTmpBonificacion();
		//bean.set
	}
	
	public void uploadFile(FileUploadEvent event){
		UploadedFile file= event.getFile();
		
	}

	public void saveBean(ActionEvent event) {
		try {
			bean = ejbBonificacion.persist(bean);
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA,
					SEVERITY_INFO);
		} catch (Exception e) {
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_ERROR_VERIFICAR_BANCO,
					SEVERITY_ERROR);
		}
		
	}

	public void updateBean(ActionEvent event) {
		try {
			bean = ejbBonificacion.merge(bean);
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA,
					SEVERITY_INFO);

		} catch (Exception e) {
			showMessage(ConstantesUtil.MENSAJE_RESPUESTA_ERROR_VERIFICAR_BANCO,
					SEVERITY_ERROR);
		}
		
	}
	

	public SipreTmpBonificacion getBean() {
		return bean;
	}

	public void setBean(SipreTmpBonificacion bean) {
		this.bean = bean;
	}
	
	
	

}
