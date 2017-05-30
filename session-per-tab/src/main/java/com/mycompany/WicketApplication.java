package com.mycompany;

import org.apache.wicket.Page;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.cycle.PageRequestHandlerTracker;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 */
public class WicketApplication extends AuthenticatedWebApplication
{
	@Override
	public Class<? extends WebPage> getHomePage()
	{
		return PageA.class;
	}

	@Override
	public void init()
	{
		super.init();

		mountPage("a", PageA.class);
		mountPage("b", PageB.class);

		getRequestCycleListeners().add(new PageRequestHandlerTracker());

		setSessionStoreProvider(SessionPerTabHttpSessionStore::new);

		getComponentInstantiationListeners().add(component -> {
			if (component instanceof Page) {
				component.add(new SessionPerTabBehavior());
			}
		});
	}

	@Override
	protected Class<? extends WebPage> getSignInPageClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
		return MySession.class;
	}


}
