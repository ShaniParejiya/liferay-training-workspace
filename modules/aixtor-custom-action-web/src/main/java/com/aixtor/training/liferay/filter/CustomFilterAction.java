package com.aixtor.training.liferay.filter;

import org.osgi.service.component.annotations.Component;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

@Component(
		immediate = true,
		property = {
				
			"servlet-context-name=",
			"servlet-filter-name=CustomFilter",
			"url-pattern=/*"
		},
		service = Filter.class
	)
public class CustomFilterAction implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("filter init called :::");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("filter do filter called ::::");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		System.out.println("filter destroyed successfully ::::");
	}

}
