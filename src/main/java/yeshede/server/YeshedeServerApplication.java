package yeshede.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

import yeshede.server.resources.BookResource;
import yeshede.server.resources.OriginalSourceResource;

public class YeshedeServerApplication extends Application {

    @Override
    public Restlet createInboundRoot() {
        Router router = new Router(getContext());
        router.attach("/originalSources", OriginalSourceResource.class);
        router.attach("/books", BookResource.class);
        return router;
    }

}