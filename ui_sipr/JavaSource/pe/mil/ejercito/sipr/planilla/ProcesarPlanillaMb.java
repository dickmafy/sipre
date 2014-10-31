package pe.mil.ejercito.sipr.planilla;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.jboss.security.plugins.TmpFilePassword;
import org.primefaces.component.log.Log;

import pe.mil.ejercito.sipr.commons.ConstantesUtil;
import pe.mil.ejercito.sipr.commons.Faces;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.commons.UValidacion;
import pe.mil.ejercito.sipr.dto.UsuarioDto;
import pe.mil.ejercito.sipr.ejb.FamiliaEjbBean;
import pe.mil.ejercito.sipr.ejb.GenericDAO;
import pe.mil.ejercito.sipr.ejbremote.FamiliaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.GrupoGradoEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.IngresoConceptoPersonalEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.PersonaEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.ReintegroPersonalEjbRemote;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreIngresoOtro;
import pe.mil.ejercito.sipr.model.SipreIngresoOtroPK;
import pe.mil.ejercito.sipr.model.SiprePerfil;
import pe.mil.ejercito.sipr.model.SiprePersona;
import pe.mil.ejercito.sipr.model.SipreTmpFamilia;
import pe.mil.ejercito.sipr.model.SipreUsuario;

@ManagedBean(name = "procesarPlanillaMb")
@ViewScoped
public class ProcesarPlanillaMb extends MainContext implements Serializable {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private UsuarioEjbRemote ejbUsuario;
	private FamiliaEjbRemote ejbTmpFamilia;
	private PersonaEjbRemote ejbPersona;

	private List<SipreTmpFamilia> beanTmpFamiliaList;
	private List<SiprePersona> beanPersonaList;

	private SipreTmpFamilia beanTmpFamilia;
	private SiprePersona beanPersona;

	public ProcesarPlanillaMb() {
		super();
		try {
			ejbUsuario = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
			ejbTmpFamilia = (FamiliaEjbRemote) findServiceRemote(FamiliaEjbRemote.class);
			ejbPersona = (PersonaEjbRemote) findServiceRemote(PersonaEjbRemote.class);

			beanTmpFamiliaList = ejbTmpFamilia.findAll(100);
			beanPersonaList = ejbPersona.findAll(100);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void procesarNumeroHijos() {
		beanPersona = new SiprePersona();
		String numeroHijosPersona = null;
		String cipPersona = null;
		Integer obtenerEdad;
		String fechaNacimiento;
		SipreTmpFamilia sipreTmpFamilia;
		Integer procesoContadorHijos = null;

		for (SiprePersona itemPersona : beanPersonaList) {

			procesoContadorHijos = 0;
			beanTmpFamiliaList = null;

			try {
				beanTmpFamiliaList = ejbTmpFamilia
						.findAllByIdPersona(itemPersona.getCpersonaNroAdm());
			} catch (Exception e) {
				showMessage(
						ConstantesUtil.MENSAJE_RESPUESTA_ERROR_GENERAL
								+ e.getMessage(), SEVERITY_ERROR);

			}

			for (SipreTmpFamilia itemTmpFamilia : beanTmpFamiliaList) {

				// si tiene datos
				if (!UValidacion.esNuloOVacio(itemTmpFamilia.getCtfCif())) {
					String codigoTmpFamilia = itemTmpFamilia.getCtfCif();
					
					// codigo 50,60
					codigoTmpFamilia = codigoTmpFamilia.substring(
							codigoTmpFamilia.length() - 2,
							codigoTmpFamilia.length());

					// 1. que tengan esos codigo son familia
					if ("59".equals(codigoTmpFamilia)
							|| "60".equals(codigoTmpFamilia)) {

						// 2 fecha nacimiento menor a 18
						fechaNacimiento = UValidacion
								.ConvertDateToString(itemTmpFamilia
										.getDtfFecNac());
						obtenerEdad = UValidacion.getEdad(fechaNacimiento);

						if (obtenerEdad > 18) {

						} else if (obtenerEdad <= 18) {

							procesoContadorHijos++;
							updateProcesoNumeroHijo(cipPersona,
									procesoContadorHijos);
						}

					} else {
						showMessage(
								"No tiene los codigo 56 y 60 ,no tiene familiares.",
								SEVERITY_ERROR);
					}

				}

			}
		}

	}

	private void updateProcesoNumeroHijo(String cipPersona,
			Integer procesoContadorHijos) {
		// Actualizar Numero hijo de persona
		beanPersona.setNpersonaNroHijo(procesoContadorHijos.toString());
		beanPersona.setCpersonaNroAdm(cipPersona);
		// ejbPersona.merge(beanPersona);

		showMessage(
				ConstantesUtil.MENSAJE_RESPUESTA_CORRECTA + " Numero hijos : "
						+ procesoContadorHijos + "(" + beanPersona.toString()
						+ ")", SEVERITY_INFO);
	}

	public List<SipreTmpFamilia> getBeanTmpFamiliaList() {
		return beanTmpFamiliaList;
	}

	public void setBeanTmpFamiliaList(List<SipreTmpFamilia> beanTmpFamiliaList) {
		this.beanTmpFamiliaList = beanTmpFamiliaList;
	}

	public List<SiprePersona> getBeanPersonaList() {
		return beanPersonaList;
	}

	public void setBeanPersonaList(List<SiprePersona> beanPersonaList) {
		this.beanPersonaList = beanPersonaList;
	}

	public SipreTmpFamilia getBeanTmpFamilia() {
		return beanTmpFamilia;
	}

	public void setBeanTmpFamilia(SipreTmpFamilia beanTmpFamilia) {
		this.beanTmpFamilia = beanTmpFamilia;
	}

	public SiprePersona getBeanPersona() {
		return beanPersona;
	}

	public void setBeanPersona(SiprePersona beanPersona) {
		this.beanPersona = beanPersona;
	}
}