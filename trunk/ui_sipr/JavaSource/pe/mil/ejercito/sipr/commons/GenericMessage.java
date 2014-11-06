package pe.mil.ejercito.sipr.commons;

import java.util.List;

public class GenericMessage<T> {
	private String	mensaje;
	private String	campo;
	private String	codigoRespuesta;
	private String	tipoMensaje;
	private List<T>	listaObjeto;
	private T		objeto;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getCampo() {
		return campo;
	}

	public void setCampo(String campo) {
		this.campo = campo;
	}

	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public String getTipoMensaje() {
		return tipoMensaje;
	}

	public void setTipoMensaje(String tipoMensaje) {
		this.tipoMensaje = tipoMensaje;
	}

	public List<T> getListaObjeto() {
		return listaObjeto;
	}

	public void setListaObjeto(List<T> listaObjeto) {
		this.listaObjeto = listaObjeto;
	}

	public T getObjeto() {
		return objeto;
	}

	public void setObjeto(T objeto) {
		this.objeto = objeto;
	}
}
