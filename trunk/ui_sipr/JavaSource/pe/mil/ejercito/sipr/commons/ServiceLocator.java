package pe.mil.ejercito.sipr.commons;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServiceLocator extends Faces{
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("rawtypes")
	final Hashtable jndiProperties = new Hashtable();

	@SuppressWarnings("unchecked")
	public ServiceLocator() {
		jndiProperties.put(Context.URL_PKG_PREFIXES,"org.jboss.ejb.client.naming");
	}

	/**
	 * Permite ubicar e instanciar un EJB ubicado en el árbol JNDI
	 * 
	 * @param nombreBean
	 * @param nombreBeanRemoto
	 * @return Objeto EjbRemoto
	 */
	public Object findServiceRemote(@SuppressWarnings("rawtypes") Class c) {
		Object obj = null;
		try {
			String nbreCse = c.getSimpleName();
			int pos = nbreCse.lastIndexOf(UProperties.getMessage(UParametro.PROP_CONFIGURACIONES,UParametro.SFJO_EJB_REMOTO));
			String nbreCseBean = nbreCse.substring(0, pos).concat(UProperties.getMessage(UParametro.PROP_CONFIGURACIONES,UParametro.SFJO_EJB_BEAN));
			Context context = new InitialContext(jndiProperties);
			obj = context.lookup("ejb:ear_sipr/model_sipr//" + nbreCseBean+ "!" + c.getName());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return obj;
	}
}