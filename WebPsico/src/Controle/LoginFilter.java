package Controle;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manager.LoginMB;
import modelo.Usuario;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns="/pages/*", servletNames="{Faces Servlet}")
public class LoginFilter implements Filter {

	@Inject
	LoginMB loginMB;
	
	Usuario logado;
	
    /**
     * Default constructor. 
     */
    public LoginFilter() {
    }

        
	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("!!!!!!!!!!!");
		logado = loginMB.getLogado();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String url = httpServletRequest.getRequestURI();
		HttpSession session = httpServletRequest.getSession();
		if (logado!=null || url.lastIndexOf("login.jsf")>-1) {
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect("login.jsf");
		}
	}


	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	

}
