package com.aixtor.training.liferay.audit.api;

import com.liferay.portal.kernel.theme.ThemeDisplay;

/**
 * @author shani.patel
 */
public interface AixtorAuditApi {
	
	public void addAuditLog(String action, ThemeDisplay themeDisplay,String ProtletName);
}