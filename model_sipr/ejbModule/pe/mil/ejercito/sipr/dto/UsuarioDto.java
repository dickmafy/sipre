package pe.mil.ejercito.sipr.dto;

import java.io.Serializable;

public class UsuarioDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nickname;
	private String clave;

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
}
