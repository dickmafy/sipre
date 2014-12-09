package pe.mil.ejercito.sipr.commons;

import java.util.Date;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "progressBar")
@ApplicationScoped
public class ProgressBar {
	private Date	fechaInicio;
	private Date	fechaFin;
	private String	fechaInicioText;
	private String	fechaFinText;

	public int		contador;
	private String	tiempoTotal;

	public ProgressBar() {
		contador = 0;
	}

	public void onComplete() {
		contador = 0;
		tiempoTotal = UValidacion.getSMHDEntreFechasString(fechaInicioText, UValidacion.getFechaYHoraActual());
	}

	public Integer barraProgreso(int contadorActual, int contadorTotal) {
		if (contadorActual == 1) {
			fechaInicioText = UValidacion.getFechaYHoraActual();
		}

		try {
			Double valorActual = (double) contadorActual;
			Double valorTotal = (double) contadorTotal;
			Double porcentaje = new Double(valorActual / valorTotal);
			contador = (int) (porcentaje * 100);
			if (contador > 100) {
				contador = 100;
			}
		} catch (Exception e) {
			contador = 0;
		}

		return contador;

	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public Date getFechaFin() {
		return fechaFin;
	}


	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	public String getTiempoTotal() {
		return tiempoTotal;
	}

	public void setTiempoTotal(String tiempoTotal) {
		this.tiempoTotal = tiempoTotal;
	}


	public String getFechaInicioText() {
		return fechaInicioText;
	}

	public void setFechaInicioText(String fechaInicioText) {
		this.fechaInicioText = fechaInicioText;
	}

	public String getFechaFinText() {
		return fechaFinText;
	}

	public void setFechaFinText(String fechaFinText) {
		this.fechaFinText = fechaFinText;
	}

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

}
