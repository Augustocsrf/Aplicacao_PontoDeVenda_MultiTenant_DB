package com.app.main.Aplicacao2.multitenancy;

import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class TenantFilter implements Filter {

  private static final String TENANT_HEADER = "tenant";
  private static String currentTenant;  
  
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  		@Override
  		public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    	HttpServletRequest request = (HttpServletRequest) servletRequest;
    	String tenantHeader = request.getHeader(TENANT_HEADER);
    	if (tenantHeader != null && !tenantHeader.isEmpty()) {
    		TenantContext.setCurrentTenant(tenantHeader);
    	}
    	filterChain.doFilter(servletRequest, servletResponse);
  	}

	@Override
	public void destroy() {
	}

	public static String getCurrentTenant() {
		return currentTenant;
	}
	
	public static void setCurrentTenant(String currentTenant) {
		TenantFilter.currentTenant = currentTenant;
	}
}
