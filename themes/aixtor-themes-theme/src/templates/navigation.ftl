<!-- <nav aria-label="<@liferay.language key="site-pages" />" class="${nav_css_class}" id="navigation" role="navigation">
	<ul role="menubar">
		<#list nav_items as nav_item>
			<#assign
				nav_item_attr_has_popup = ""
				nav_item_css_class = ""
				nav_item_layout = nav_item.getLayout()
			/>

			<#if nav_item.isSelected()>
				<#assign
					nav_item_attr_has_popup = "aria-haspopup='true'"
					nav_item_css_class = "selected"
				/>
			</#if>

			<li class="${nav_item_css_class}" id="layout_${nav_item.getLayoutId()}" role="presentation">
				<a ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem"><span><@liferay_theme["layout-icon"] layout=nav_item_layout /> ${nav_item.getName()}</span></a>

				<#if nav_item.hasChildren()>
					<ul class="child-menu" role="menu">
						<#list nav_item.getChildren() as nav_child>
							<#assign
								nav_child_css_class = ""
							/>
							<#if nav_item.isSelected()>
								<#assign
									nav_child_css_class = "selected"
								/>
							</#if>

							<li class="${nav_child_css_class}" id="layout_${nav_child.getLayoutId()}" role="presentation">
								<a href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem">${nav_child.getName()}</a>
							</li>
						</#list>
					</ul>
				</#if>
			</li>
		</#list>
	</ul>
</nav>  --> 

<!-- <nav aria-label="<@liferay.language key="site-pages" />" class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0" role="menubar">
                <#list nav_items as nav_item>
                    <#assign
                        nav_item_attr_has_popup = ""
                        nav_item_css_class = ""
                        nav_item_layout = nav_item.getLayout()
                    />
                    <#if nav_item.isSelected()>
                        <#assign
                            nav_item_attr_has_popup = "aria-haspopup='true'"
                            nav_item_css_class = "selected"
                        />
                    </#if>
                    <li class="${nav_item_css_class}" id="layout_${nav_item.getLayoutId()}" role="presentation">
                        <a ${nav_item_attr_has_popup} href="${nav_item.getURL()}" ${nav_item.getTarget()} role="menuitem" class="nav-link active" aria-current="page">
                            <span><@liferay_theme["layout-icon"] layout=nav_item_layout /> ${nav_item.getName()}</span>
                        </a>
                        <#if nav_item.hasChildren()>
                            <ul class="child-menu" role="menu">
                                <#list nav_item.getChildren() as nav_child>
                                    <#assign nav_child_css_class = "" />
                                    <#if nav_item.isSelected()>
                                        <#assign nav_child_css_class = "selected" />
                                    </#if>
                                    <li class="${nav_child_css_class}" id="layout_${nav_child.getLayoutId()}" role="presentation">
                                        <a href="${nav_child.getURL()}" ${nav_child.getTarget()} role="menuitem">${nav_child.getName()}</a>
                                    </li>
                                </#list>
                            </ul>
                        </#if>
                    </li>
                </#list>
            </ul>
        </div>
    </div>
</nav> -->

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">Aixtor Technologies </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
    	<#list nav_items as nav_item>
    		<#if nav_item.hasChildren()>
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          Pages
		        </a>
		        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
		        	<a class="nav-link" href="${nav_item.getURL()}" ${nav_item.getTarget()}>${nav_item.getName()}</a>
		        	<#list nav_item.getChildren() as nav_child>
		          		<a class="dropdown-item" href="${nav_child.getURL()}" ${nav_child.getTarget()}>${nav_child.getName()}</a>
		          	</#list>
		        </div>
		      </li>
		    <#else>
		      <li class="nav-item active">
		        <a class="nav-link" href="${nav_item.getURL()}" ${nav_item.getTarget()}>${nav_item.getName()}</a>
		      </li>
    		</#if>
	      
	    </#list>
    </ul>
  </div>
</nav>