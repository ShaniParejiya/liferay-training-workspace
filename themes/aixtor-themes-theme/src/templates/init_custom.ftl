<#assign
	login_in_page="http://localhost:8080/web/finance_site/theme-generator"
	
	employeeLocalService = serviceLocator.findService("com.aixtor.training.liferay.employee.service.builder.service.EmployeeLocalService")
	userLocalService=serviceLocator.findService("com.liferay.portal.kernel.service.UserLocalService")
	employeecount =employeeLocalService.getEmployeesCount()
/>

<#if is_signed_in>
	<#assign	
     userName = userLocalService.getUser(themeDisplay.getUserId()).getFullName()   
   />
<#else>
    <#assign userName = "Guest" />
</#if>