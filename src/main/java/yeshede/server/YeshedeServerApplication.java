package yeshede.server;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;

import yeshede.server.resources.BookResource;
import yeshede.server.resources.LocationResource;
import yeshede.server.resources.OriginalSourceResource;
import yeshede.server.resources.TextResource;

public class YeshedeServerApplication extends Application {

    @Override
    public Restlet createInboundRoot() {
    	Directory directory = new Directory(getContext(), "clap://class/static/");
        directory.setDeeplyAccessible(true);
    	
        Router router = new Router(getContext());
        router.attach("/web", directory);
        router.attach("/originalSources", OriginalSourceResource.class);
        router.attach("/books", BookResource.class);
        router.attach("/texts", TextResource.class);
        router.attach("/locations", LocationResource.class);
        return router;
    }

}