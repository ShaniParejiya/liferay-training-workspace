package com.aixtor.training.liferay.auditlog;

import com.aixtor.training.liferay.audit.api.AixtorAuditApi;
import com.aixtor.training.liferay.audit.model.AuditLog;
import com.aixtor.training.liferay.audit.service.AuditLogLocalService;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author shani.patel
 */
@Component(
	immediate = true,
	property = {
		// TODO enter required service properties
	},
	service = AixtorAuditApi.class
)
public class AixtorAuditService implements AixtorAuditApi {

	@Reference
	AuditLogLocalService auditLogLocalService;
	
	
	@Override
	public void addAuditLog(String action, ThemeDisplay themeDisplay,String portletName) {
		
		AuditLog auditLog = auditLogLocalService
				.createAuditLog(CounterLocalServiceUtil.increment(AuditLog.class.getName()));
		auditLog.setAction(action);
		auditLog.setModuleName(portletName);
		auditLog.setCreatedBy(themeDisplay.getUser().getUserId());
		auditLog.setModifiedBy(themeDisplay.getUser().getUserId());
		auditLog.setGroupId(themeDisplay.getScopeGroupId());
		auditLog.setCompanyId(themeDisplay.getCompanyId());
		auditLog.setUserId(themeDisplay.getUserId());
		auditLog.setUserName(themeDisplay.getUser().getFullName());
	    auditLogLocalService.addAuditLog(auditLog);   
	    System.out.println("Audit log Entry Called...:::\n\n\n" + auditLog.getAction());
	}
}