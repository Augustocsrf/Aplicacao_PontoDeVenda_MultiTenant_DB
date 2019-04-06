package com.app.main.Aplicacao2.multitenancy;

public interface TenantSupport {
  String getTenantId();

  void setTenantId(String tenantId);
}
