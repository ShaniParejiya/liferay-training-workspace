package com.aixtor.training.liferay.dynamicinclude.portletfilter;

import com.liferay.journal.constants.JournalContentPortletKeys;

import java.io.IOException;
import java.io.PrintWriter;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.PortletFilter;
import javax.portlet.filter.RenderFilter;

import org.osgi.service.component.annotations.Component;




@Component(
	    immediate = true,
	    property = {
	            "javax.portlet.name=" + JournalContentPortletKeys.JOURNAL_CONTENT
	    },
	    service = PortletFilter.class
	)
public class PortletFilterOverrideWebPortlet implements RenderFilter{

	@Override
	public void init(FilterConfig filterConfig) throws PortletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(RenderRequest request, RenderResponse response, FilterChain chain)
			throws IOException, PortletException {
		
		System.out.println("PortletFilter called :::: ");

		PrintWriter printWriter = response.getWriter();
		printWriter.println("<h2>this is Blog Which is Override using portlet Filter</h2>");
        chain.doFilter(request, response);
	}

}
