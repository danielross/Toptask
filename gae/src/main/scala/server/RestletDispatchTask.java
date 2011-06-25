package server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class RestletDispatchTask extends Application
{
	@Override
	public synchronized Restlet createInboundRoot()
	{

		final Router router = new Router (getContext ());

		router.attach("/task", TaskController.class);
		
		return router;
	}

}
