package pe.mil.ejercito.sipr.seguridad;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import pe.mil.ejercito.sipr.commons.Encripta;
import pe.mil.ejercito.sipr.commons.Faces;
import pe.mil.ejercito.sipr.commons.MainContext;
import pe.mil.ejercito.sipr.commons.UParametro;
import pe.mil.ejercito.sipr.commons.UProperties;
import pe.mil.ejercito.sipr.dto.UsuarioDto;
import pe.mil.ejercito.sipr.ejbremote.UsuarioEjbRemote;
import pe.mil.ejercito.sipr.model.SipreAuditoria;
import pe.mil.ejercito.sipr.model.SipreUsuario;

@ManagedBean(name = "indexBean")
@RequestScoped
public class Index extends MainContext {
	private static final long serialVersionUID = 1L;
	private UsuarioDto usuarioDto;
	private UsuarioEjbRemote usrioEjb;

	public Index() {
		super();
		setUsuarioDto(new UsuarioDto());
		usrioEjb = (UsuarioEjbRemote) findServiceRemote(UsuarioEjbRemote.class);
	}

	public String validarIngreso() {
		String rtnoPgna = "";
		usuarioDto.setClave(Encripta.encripta(usuarioDto.getClave(),
				Encripta.HASH_SHA1));

		SipreUsuario usuario = usrioEjb.getUsuario(usuarioDto);

		if (usuario != null) {
			registrarVariable(UParametro.SSION_VRBLE_USRIO, usuario);
			ejbAuditoria.persist(getAuditoria("INGRESO AL SISTEMA", new Date(),
					"USUARIO", usuarioDto.getNickname(), null,
					"ACCESO AL SISTEMA", usuario.getCusuarioCodigo()));

			rtnoPgna = redirecciona(UProperties.getMessage(
					UParametro.PROP_CONFIGURACIONES, UParametro.PGNA_PCPAL));
		} else {
			ejbAuditoria.persist(getAuditoria("INGRESO AL SISTEMA", new Date(), "USUARIO", usuarioDto.getNickname(), null, "CREDENCIALES INCORRECTAS", null));
			showMessage(UProperties.getMessage(UParametro.PROP_MENSAJES,
					UParametro.MSJE_ERROR_USRIO_CVE_ICRTO),
					Faces.SEVERITY_ERROR);
		}

		return rtnoPgna;
	}

	public UsuarioDto getUsuarioDto() {
		return usuarioDto;
	}

	public void setUsuarioDto(UsuarioDto usuarioDto) {
		this.usuarioDto = usuarioDto;
	}
}
