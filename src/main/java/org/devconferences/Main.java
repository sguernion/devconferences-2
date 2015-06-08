package org.devconferences;

import net.codestory.http.WebServer;
import net.codestory.http.injection.GuiceAdapter;
import net.codestory.http.templating.ModelAndView;
import org.devconferences.elastic.DeveloppementESNode;
import org.devconferences.events.EventsEndPoint;
import org.devconferences.meetup.MeetupEndPoint;
import org.devconferences.security.Authentication;
import org.devconferences.security.SecurityFilter;

import java.util.Collections;
import java.util.Properties;
import java.util.stream.StreamSupport;

public class Main {

    public static final int PORT = 8080;

    public static void main(String[] args) {
        WebServer webServer = new WebServer();

        webServer.configure(routes -> {
                    routes.setIocAdapter(new GuiceAdapter());
                    routes.filter(SecurityFilter.class);
                    routes.add(Authentication.class);
                    routes.add(EventsEndPoint.class);
                    routes.add(MeetupEndPoint.class);
                    routes.get("/city/:id", (context, id) -> ModelAndView.of("index"));
                }
        );
        webServer.start(PORT);

        boolean prodMode = Boolean.parseBoolean(System.getProperty("PROD_MODE", "false"));
        boolean skipDevNode= Boolean.parseBoolean(System.getProperty("SKIP_DEV_NODE", "false"));
        if(!prodMode && !skipDevNode){
            System.out.println("-DSKIP_DEV_NODE=true To skip ES dev node creation");
            DeveloppementESNode.createDevNode();
        }
    }

}
