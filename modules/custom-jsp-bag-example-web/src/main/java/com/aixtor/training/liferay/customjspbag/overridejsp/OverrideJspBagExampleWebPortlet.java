package com.aixtor.training.liferay.customjspbag.overridejsp;

import com.liferay.portal.deploy.hot.CustomJspBag;
import com.liferay.portal.kernel.servlet.taglib.DynamicInclude;
import com.liferay.portal.kernel.url.URLContainer;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

@Component(
	    immediate = true,
	    		 property = {
	    			    	"context.id=OverrideJspBagExampleWebPortlet",
	    			        "context.name=update password using customjsp bag",
	    			    	"service.ranking:Integer=100"
	    			    }
	)
public class OverrideJspBagExampleWebPortlet implements CustomJspBag {

	private Bundle _bundle;
	private List<String> _customJsps;
	
	
	@Override
	public String getCustomJspDir() {
		return "META-INF/resources/jsps/";
	}
	
	@Activate
	protected void activate(BundleContext bundleContext) {
		_bundle = bundleContext.getBundle();

		_customJsps = new ArrayList<>();

		Enumeration<URL> entries = _bundle.findEntries(
			getCustomJspDir(), "*.jsp", true);

		while (entries.hasMoreElements()) {
			URL url = entries.nextElement();

			_customJsps.add(url.getPath());
		}
	}

	@Override
	public List<String> getCustomJsps() {
		 return _customJsps;
	}
	
	
	private final URLContainer _urlContainer = new URLContainer() {

	    @Override
	    public URL getResource(String name) {
	        return _bundle.getEntry(name);
	    }

	    @Override
	    public Set<String> getResources(String path) {
	        Set<String> paths = new HashSet<>();

	        for (String entry : _customJsps) {
	            if (entry.startsWith(path)) {
	               paths.add(entry);
	            }
	        }

	        return paths;
	    }

	};
	
	@Override
	public URLContainer getURLContainer() {
		return _urlContainer;
	}

	@Override
	public boolean isCustomJspGlobal() {
		return true;
	}
}
