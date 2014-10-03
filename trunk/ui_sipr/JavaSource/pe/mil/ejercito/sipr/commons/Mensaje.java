package pe.mil.ejercito.sipr.commons;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import pe.mil.ejercito.sipr.commons.UParametro;
import pe.mil.ejercito.sipr.commons.UProperties;

@ApplicationScoped
@ManagedBean(name = "mensajeMb")
public class Mensaje {
	private String msjeInciarSsion;
	private String msjeErrorUsrioCveIcrto;
	private String wterMkerUsrio;
	private String wterMkerClve;
	private String rqredMsjeCtsna;
	private String rqredMsjeUsrio;
	private String tttUsrio;
	private String tttCtsna;
	private String btnIgsar;
	private String linkNacdeCtea;
	private String ttloStma;

	private String msjeFter;
	private String msjeAjaxCdcles;

	public Mensaje() {
		setMsjeInciarSsion(UProperties.getMessage(UParametro.PROP_MENSAJES,
				UParametro.MSJE_INCIAR_SSION));
		setMsjeErrorUsrioCveIcrto(UProperties
				.getMessage(UParametro.PROP_MENSAJES,
						UParametro.MSJE_ERROR_USRIO_CVE_ICRTO));
		setWterMkerUsrio(UProperties.getMessage(UParametro.PROP_MENSAJES,
				UParametro.WTER_MKER_USRIO));
		setWterMkerClve(UProperties.getMessage(UParametro.PROP_MENSAJES,
				UParametro.WTER_MKER_CLVE));
		setBtnIgsar(UProperties.getMessage(UParametro.PROP_MENSAJES,
				UParametro.BTN_IGSAR));
		setLinkNacdeCtea(UProperties.getMessage(UParametro.PROP_MENSAJES,
				UParametro.LINK_NACDE_CTA));
		setTttUsrio(UProperties.getMessage(UParametro.PROP_MENSAJES,
				UParametro.TTT_USRIO));
		setTttCtsna(UProperties.getMessage(UParametro.PROP_MENSAJES,
				UParametro.TTT_CTSNA));
		setMsjeFter(UProperties.getMessage(UParametro.PROP_MENSAJES,
				UParametro.MSJE_FTER));
		setMsjeAjaxCdcles(UProperties.getMessage(UParametro.PROP_MENSAJES,
				UParametro.MSJE_AJAX_CDCLES));
		setRqredMsjeUsrio(UProperties.getMessage(UParametro.PROP_MENSAJES,
				UParametro.RQRED_MSJE_USRIO));
		setRqredMsjeCtsna(UProperties.getMessage(UParametro.PROP_MENSAJES,
				UParametro.RQRED_MSJE_CTSNA));
		setTtloStma(UProperties.getMessage(UParametro.PROP_MENSAJES,
				UParametro.TTLO_STMA));
	}

	public String getMsjeInciarSsion() {
		return msjeInciarSsion;
	}

	public void setMsjeInciarSsion(String msjeInciarSsion) {
		this.msjeInciarSsion = msjeInciarSsion;
	}

	public String getMsjeErrorUsrioCveIcrto() {
		return msjeErrorUsrioCveIcrto;
	}

	public void setMsjeErrorUsrioCveIcrto(String msjeErrorUsrioCveIcrto) {
		this.msjeErrorUsrioCveIcrto = msjeErrorUsrioCveIcrto;
	}

	public String getWterMkerUsrio() {
		return wterMkerUsrio;
	}

	public void setWterMkerUsrio(String wterMkerUsrio) {
		this.wterMkerUsrio = wterMkerUsrio;
	}

	public String getWterMkerClve() {
		return wterMkerClve;
	}

	public void setWterMkerClve(String wterMkerClve) {
		this.wterMkerClve = wterMkerClve;
	}

	public String getBtnIgsar() {
		return btnIgsar;
	}

	public void setBtnIgsar(String btnIgsar) {
		this.btnIgsar = btnIgsar;
	}

	public String getLinkNacdeCtea() {
		return linkNacdeCtea;
	}

	public void setLinkNacdeCtea(String linkNacdeCtea) {
		this.linkNacdeCtea = linkNacdeCtea;
	}

	public String getTttUsrio() {
		return tttUsrio;
	}

	public void setTttUsrio(String tttUsrio) {
		this.tttUsrio = tttUsrio;
	}

	public String getTttCtsna() {
		return tttCtsna;
	}

	public void setTttCtsna(String tttCtsna) {
		this.tttCtsna = tttCtsna;
	}

	public String getMsjeFter() {
		return msjeFter;
	}

	public void setMsjeFter(String msjeFter) {
		this.msjeFter = msjeFter;
	}

	public String getMsjeAjaxCdcles() {
		return msjeAjaxCdcles;
	}

	public void setMsjeAjaxCdcles(String msjeAjaxCdcles) {
		this.msjeAjaxCdcles = msjeAjaxCdcles;
	}

	public String getRqredMsjeCtsna() {
		return rqredMsjeCtsna;
	}

	public void setRqredMsjeCtsna(String rqredMsjeCtsna) {
		this.rqredMsjeCtsna = rqredMsjeCtsna;
	}

	public String getRqredMsjeUsrio() {
		return rqredMsjeUsrio;
	}

	public void setRqredMsjeUsrio(String rqredMsjeUsrio) {
		this.rqredMsjeUsrio = rqredMsjeUsrio;
	}

	public String getTtloStma() {
		return ttloStma;
	}

	public void setTtloStma(String ttloStma) {
		this.ttloStma = ttloStma;
	}
}
