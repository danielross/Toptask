package server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class RestletDispatch extends Application
{
	@Override
	public synchronized Restlet createInboundRoot()
	{

		final Router router = new Router(getContext());

		router.attach("/user", UserController.class);
		
		return router;
	}

}
