package pe.mil.ejercito.sipr.registro;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.ejbremote.GrupoGradoEjbRemote;
import pe.mil.ejercito.sipr.model.SipreGrupoGrado;

@ManagedBean(name="grupoGrado")
@ViewScoped
public class GrupoGrado extends MainContext implements Serializable{


}
