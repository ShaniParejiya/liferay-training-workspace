package com.aixtor.training.liferay.portlet;

import com.aixtor.training.liferay.constants.AixtorDesignationConstant;
import com.aixtor.training.liferay.employee.service.builder.model.Designation;
import com.aixtor.training.liferay.employee.service.builder.service.DesignationLocalService;
import com.liferay.counter.kernel.service.CounterLocalService;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.Date;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=AixtorDesignation",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/html/designation/designation.jsp",
		"javax.portlet.name=" + AixtorDesignationConstant.AIXTOR_DESIGNATION,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
		},

service = Portlet.class)
public class AixtorDesignationPortlet extends MVCPortlet {

	@Reference
	DesignationLocalService designationLocalService;

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		super.render(renderRequest, renderResponse);
	}

	@ProcessAction(name = "designationForm")
	@Override
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
			
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String designationName = ParamUtil.getString(actionRequest, AixtorDesignationConstant.DESIGNATION_NAME);
		Designation designation = designationLocalService.createDesignation(CounterLocalServiceUtil.increment(Designation.class.getName()));
		
		designation.setCompanyId(themeDisplay.getCompanyId());
		designation.setUserName(themeDisplay.getUser().getFullName());
		designation.setCreateDate(new Date());
		designation.setModifiedDate(new Date());
		designation.setUserId(themeDisplay.getUserId());
		designation.setDesignationName(designationName);
		designation.setDesignationId(CounterLocalServiceUtil.increment(Designation.class.getName()));
		
		designationLocalService.addDesignation(designation);
	}
}
