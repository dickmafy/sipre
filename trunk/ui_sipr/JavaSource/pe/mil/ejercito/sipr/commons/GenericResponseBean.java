package pe.mil.ejercito.sipr.commons;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class GenericResponseBean<T> {

	private T objeto;
	private List<T> lista;

	private String codigoRespuesta;
	private String mensaje;

	public GenericResponseBean() {
		super();
	}

	public GenericResponseBean(T objeto, List<T> lista, String codigoRespuesta, String mensaje) {
		super();
		this.objeto = objeto;
		this.lista = lista;
		this.codigoRespuesta = codigoRespuesta;
		this.mensaje = mensaje;
	}

	public T getObjeto() {
		return objeto;
	}

	public void setObjeto(T objeto) {
		this.objeto = objeto;
	}

	public List<T> getLista() {
		return lista;
	}

	public void setLista(List<T> lista) {
		this.lista = lista;
	}

	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
