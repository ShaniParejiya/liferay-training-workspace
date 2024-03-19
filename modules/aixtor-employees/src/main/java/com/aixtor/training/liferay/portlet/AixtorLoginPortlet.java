package com.aixtor.training.liferay.portlet;

import com.aixtor.training.liferay.constants.AixtorLoginConstant;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.auth.session.AuthenticatedSessionManagerUtil;

import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.ProcessAction;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(immediate = true, property = { "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css", "com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=LoginAuthentication", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/html/login/login_form.jsp",
		"javax.portlet.name=" + AixtorLoginConstant.AIXTOR_LOGIN_PORTLET,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user" }, service = Portlet.class)
public class AixtorLoginPortlet extends MVCPortlet {

	private static final Log log=LogFactoryUtil.getLog(AixtorLoginPortlet.class); 
	
	@Reference
	private UserLocalService userLocalService;


	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		// TODO Auto-generated method stub
		System.out.println("render called");
		super.render(renderRequest, renderResponse);
	}

	@ProcessAction(name = "LoginForm")
	@Override
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse)
			throws IOException, PortletException {

		log.info("Custom action callled with log");
		String email = ParamUtil.getString(actionRequest, "email");

		String password = ParamUtil.getString(actionRequest, "password");

//		boolean rememberMe = ParamUtil.getBoolean(actionRequest, "rememberMe");

		boolean rememberMe = true;

		String authType = CompanyConstants.AUTH_TYPE_EA;

		HttpServletResponse httpResponse = PortalUtil.getHttpServletResponse(actionResponse);
		HttpServletRequest httpRequest = PortalUtil.getHttpServletRequest(actionRequest);
		boolean loginSuccessful = false;

		try {
			User user = userLocalService.getUserByEmailAddress(PortalUtil.getDefaultCompanyId(), email);
			if(user != null) {
			AuthenticatedSessionManagerUtil.login(httpRequest, httpResponse, email, password, rememberMe, authType);
			loginSuccessful = true;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			if (loginSuccessful == true) {
				actionResponse.sendRedirect("http://localhost:8080/web/finance_site/home");
			} else {
//				System.out.println("else called");
				actionRequest.setAttribute("email",email);
				actionRequest.setAttribute("password", password);
				actionRequest.setAttribute("LoginAuthentication", "Email And Password Does not matched");
			}
		}
	}
}
