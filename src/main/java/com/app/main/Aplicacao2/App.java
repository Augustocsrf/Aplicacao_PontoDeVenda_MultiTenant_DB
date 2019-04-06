package com.app.main.Aplicacao2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.app.main.Aplicacao2.recursos.*;
import com.app.main.Aplicacao2.multitenancy.*;

import java.io.File;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;

@EnableJpaRepositories(basePackages = "com.app.main.Aplicacao2.recursos")
@SpringBootApplication
@ComponentScan({"com.app.main.Aplicacao2", "com.app.main.Aplicacao2.recursos"})
public class App {
	
	
	public static void main(String[] args) {
		new File(PDVResource.dataDirectory).mkdir();
		
		SpringApplication.run(App.class, args);
	}
	

	@Bean
	public EmptyInterceptor hibernateInterceptor() {
		return new EmptyInterceptor() {
			@Override
		    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
				if (entity instanceof TenantSupport) {
					((TenantSupport) entity).setTenantId(TenantContext.getCurrentTenant());
		        }
		        return false;
		    }
			
		    @Override
		    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		    	if (entity instanceof TenantSupport) {
		    		((TenantSupport) entity).setTenantId(TenantContext.getCurrentTenant());
		        }
		    }
		    
		    @Override
		    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
		    	if (entity instanceof TenantSupport) {
		    		((TenantSupport) entity).setTenantId(TenantContext.getCurrentTenant());
		        }
		        return false;
		    }
		};
	}
}