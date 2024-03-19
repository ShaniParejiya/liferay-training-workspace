package com.aixtor.training.liferay.employee.portlet;

import com.aixtor.training.liferay.employee.constants.AixtorDepartmentWebContstant;
import com.aixtor.training.liferay.employee.service.builder.model.Department;
import com.aixtor.training.liferay.employee.service.builder.service.DepartmentLocalService;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
		immediate = true,
		property = {
			"com.liferay.portlet.display-category=category.sample",
			"com.liferay.portlet.header-portlet-css=/css/main.css",
			"com.liferay.portlet.instanceable=true",
			"javax.portlet.display-name=AixtorDepartmentWeb",
			"javax.portlet.init-param.template-path=/",
			"javax.portlet.init-param.view-template=/html/department/department.jsp",
			"javax.portlet.name=" + AixtorDepartmentWebContstant.AIXTOR_DEPARTMENT,
			"javax.portlet.resource-bundle=content.Language",
			"javax.portlet.security-role-ref=power-user,user"
		},
		service = Portlet.class
	)
public class AixtorDepartmentWebPortlet extends MVCPortlet {
	
	
	@Reference
	DepartmentLocalService departmentLocalService;
	
	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		System.out.println("render department called");
		List<User> users = UserLocalServiceUtil.getUsers(-1, -1);
		renderRequest.setAttribute(AixtorDepartmentWebContstant.USERNAME_LIST, users);
		super.render(renderRequest, renderResponse);
	}
	
	@ProcessAction(name = "departmentDetails")
	@Override
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {
			
		String DepartmentName = ParamUtil.getString(actionRequest, AixtorDepartmentWebContstant.DEPARTMENT_NAME);
		String DepartmentHead= ParamUtil.getString(actionRequest, AixtorDepartmentWebContstant.DEPARTMENT_HEAD);

		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		Department department = departmentLocalService.createDepartment(CounterLocalServiceUtil.increment(Department.class.getName()));
		
		department.setCompanyId(themeDisplay.getCompanyId());
		department.setUserName(themeDisplay.getUser().getFullName());
		department.setCreateDate(new Date());
		department.setModifiedDate(new Date());
		department.setUserId(themeDisplay.getUserId());
		department.setDepartmentName(DepartmentName);
		department.setDepartmentHead(DepartmentHead);
		department.setDepartmentId(CounterLocalServiceUtil.increment(Department.class.getName()));
		departmentLocalService.addDepartment(department);
	}
}
