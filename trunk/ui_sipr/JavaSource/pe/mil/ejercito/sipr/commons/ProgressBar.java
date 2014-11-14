package pe.mil.ejercito.sipr.commons;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "progressBar")
@ApplicationScoped
public class ProgressBar {

	public int	contador;

	@SuppressWarnings("unused")
	public Integer barraProgreso(int contadorActual, int contadorTotal) {

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

	public int getContador() {
		return contador;
	}

	public void setContador(int contador) {
		this.contador = contador;
	}

}
