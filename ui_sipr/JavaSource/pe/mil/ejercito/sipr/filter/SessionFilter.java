package pe.mil.ejercito.sipr.filter;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pe.mil.ejercito.sipr.commons.UParametro;
import pe.mil.ejercito.sipr.commons.UProperties;

public class SessionFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession(false);
		String loginMain = request.getContextPath()
				+ UProperties.getMessage(UParametro.PROP_CONFIGURACIONES,
						UParametro.SSION_FTER_RDRCNA);

		boolean loggedIn = (session != null)
				&& (session.getAttribute(UParametro.SSION_VRBLE_USRIO) != null);
		boolean resourceRequest = request.getRequestURI().startsWith(
				request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER
						+ "/");
		if (loggedIn || resourceRequest) {
			response.sendRedirect(loginMain);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}

}
