package pe.mil.ejercito.sipr.dto;

import java.io.Serializable;
import java.math.BigDecimal;

public class BoletaDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private BigDecimal totalIngresos;
	private BigDecimal totalDescuentos;
	
	private BigDecimal totalNeto;
	private BigDecimal LiquidezDescuento;
	private BigDecimal sueldoMinimo;
	
	public BigDecimal getTotalIngresos() {
		return totalIngresos;
	}
	public void setTotalIngresos(BigDecimal totalIngresos) {
		this.totalIngresos = totalIngresos;
	}
	public BigDecimal getTotalDescuentos() {
		return totalDescuentos;
	}
	public void setTotalDescuentos(BigDecimal totalDescuentos) {
		this.totalDescuentos = totalDescuentos;
	}
	public BigDecimal getTotalNeto() {
		return totalNeto;
	}
	public void setTotalNeto(BigDecimal totalNeto) {
		this.totalNeto = totalNeto;
	}
	public BigDecimal getLiquidezDescuento() {
		return LiquidezDescuento;
	}
	public void setLiquidezDescuento(BigDecimal liquidezDescuento) {
		LiquidezDescuento = liquidezDescuento;
	}
	public BigDecimal getSueldoMinimo() {
		return sueldoMinimo;
	}
	public void setSueldoMinimo(BigDecimal sueldoMinimo) {
		this.sueldoMinimo = sueldoMinimo;
	}
	

	
	
}
