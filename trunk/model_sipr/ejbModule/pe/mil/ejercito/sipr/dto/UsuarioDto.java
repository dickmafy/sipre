package pe.mil.ejercito.sipr.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import pe.mil.ejercito.sipr.model.SipreUsuario;

public class UsuarioDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nickname;
	private String clave;
	private String cusuarioCodigo;

	private String cusuarioEst;

	private Date dusuarioFecReg;

	private String vusuarioNom;

	private String vusuarioPass;
	private SipreUsuario sipreUsuario;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCusuarioCodigo() {
		return cusuarioCodigo;
	}

	public void setCusuarioCodigo(String cusuarioCodigo) {
		this.cusuarioCodigo = cusuarioCodigo;
	}

	public String getCusuarioEst() {
		return cusuarioEst;
	}

	public void setCusuarioEst(String cusuarioEst) {
		this.cusuarioEst = cusuarioEst;
	}

	public Date getDusuarioFecReg() {
		return dusuarioFecReg;
	}

	public void setDusuarioFecReg(Date dusuarioFecReg) {
		this.dusuarioFecReg = dusuarioFecReg;
	}

	public String getVusuarioNom() {
		return vusuarioNom;
	}

	public void setVusuarioNom(String vusuarioNom) {
		this.vusuarioNom = vusuarioNom;
	}

	public String getVusuarioPass() {
		return vusuarioPass;
	}

	public void setVusuarioPass(String vusuarioPass) {
		this.vusuarioPass = vusuarioPass;
	}

	public SipreUsuario getSipreUsuario() {
		return sipreUsuario;
	}

	public void setSipreUsuario(SipreUsuario sipreUsuario) {
		this.sipreUsuario = sipreUsuario;
	}
}
