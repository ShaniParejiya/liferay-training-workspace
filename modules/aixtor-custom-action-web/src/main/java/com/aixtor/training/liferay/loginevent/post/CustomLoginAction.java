package com.aixtor.training.liferay.loginevent.post;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"key=login.events.post"
		},
		service = LifecycleAction.class
	)
public class CustomLoginAction implements LifecycleAction {

	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent) throws ActionException {
		// TODO Auto-generated method stub
		System.out.println("login event post =" + lifecycleEvent);
	}

}
