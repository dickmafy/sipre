package pe.mil.ejercito.sipr.commons;

public class UParametro {
	public UParametro() {
		super();
	}

	private static UParametro instancia;

	/** Ruta donde se encuentra el archivo de mensajes. */
	public static final String PROP_CONFIGURACIONES;
	public static final String PROP_MENSAJES;

	public static final String SFJO_EJB_REMOTO;
	public static final String SFJO_EJB_BEAN;
	
	public static final String MSJE_INCIAR_SSION;
	public static final String MSJE_ERROR_USRIO_CVE_ICRTO;
	public static final String WTER_MKER_USRIO;
	public static final String WTER_MKER_CLVE;
	public static final String RQRED_MSJE_USRIO;
	public static final String RQRED_MSJE_CTSNA;
	public static final String TTT_USRIO;
	public static final String TTT_CTSNA;
	public static final String BTN_IGSAR;
	public static final String LINK_NACDE_CTA;
	public static final String MSJE_FTER;
	public static final String MSJE_AJAX_CDCLES;
	
	public static final String PGNA_PCPAL;
	
	public static final String SSION_VRBLE_USRIO;
	
	public static final String SSION_FTER_RDRCNA;
	public static final String TTLO_STMA;
	
	/*PARA EFECTO DE PRUEBAS, NO DEFINIR VARIABLES ABAJO DE LAS DEFINICIONES REALIZADAS AQUI*/
	public static final String MNU_LTRAL_DFULT;
	public static final String CTNDO_DFULT;

	static {
		PROP_CONFIGURACIONES = "pe.mil.ejercito.sipr.properties.configuraciones";
		PROP_MENSAJES="pe.mil.ejercito.sipr.properties.messages";
		
		SFJO_EJB_REMOTO = "SFJO_EJB_REMOTO";
		SFJO_EJB_BEAN = "SFJO_EJB_BEAN";
		MSJE_ERROR_USRIO_CVE_ICRTO = "MSJE_ERROR_USRIO_CVE_ICRTO";
		MSJE_INCIAR_SSION="MSJE_INCIAR_SSION";
		WTER_MKER_USRIO="WTER_MKER_USRIO";
		WTER_MKER_CLVE="WTER_MKER_CLVE";
		TTT_USRIO="TTT_USRIO";
		TTT_CTSNA="TTT_CTSNA";
		BTN_IGSAR="BTN_IGSAR";
		LINK_NACDE_CTA="LINK_NACDE_CTA";
		MSJE_AJAX_CDCLES="MSJE_AJAX_CDCLES";
		MSJE_FTER="MSJE_FTER";
		RQRED_MSJE_USRIO="RQRED_MSJE_USRIO";
		RQRED_MSJE_CTSNA="RQRED_MSJE_CTSNA";
		TTLO_STMA="TTLO_STMA";
		
		PGNA_PCPAL="PGNA_PCPAL";
		
		SSION_VRBLE_USRIO="usuario";
		
		SSION_FTER_RDRCNA="SSION_FTER_RDRCNA";
		
		CTNDO_DFULT="CTNDO_DFULT";
		MNU_LTRAL_DFULT="MNU_LTRAL_DFULT";
	}

	/**
	 * Resumen. Método que se encarga de sincronizar las llamadas a la instancia
	 * de la clase.
	 * 
	 * @return instancia : Devuelve la instancia de la clase UParametros, tipo
	 *         UParametro
	 * @throws java.lang.Exception
	 *             Objeto que captura excepciones que ocurren.
	 */
	public static final synchronized UParametro getInstance() throws Exception {
		if (instancia == null) {
			instancia = new UParametro();
		}
		return instancia;
	}
}
