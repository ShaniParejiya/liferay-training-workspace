<%@ include file="/html/login/init.jsp"%>

<portlet:actionURL var="getLoginDetails" name="LoginForm" />
<p>
	<form action="${getLoginDetails}" method="post"> 
  <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Email address</label>
    <input type="email" name="<portlet:namespace />email"  class="form-control" value="${email}" id="emailId" aria-describedby="emailHelp" required="required">
  </div>
  <div class="mb-3">
    <label for="exampleInputPassword1" class="form-label">Password</label>
    <input type="password" name="<portlet:namespace />password" class="form-control" value="${password}" id="exampleInputPassword1" required="required">
  </div>
  <button type="submit" class="btn btn-primary">Sign In</button>
  
  <div style="color: red;"><b>${LoginAuthentication}</b></div>
</form>
</p>