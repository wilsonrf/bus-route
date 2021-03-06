package com.wilsonfranca.busroute.direct;

import com.wilsonfranca.busroute.route.Route;
import com.wilsonfranca.busroute.route.Routes;
import org.springframework.stereotype.Service;

@Service
public class DirectService {

    private Routes routes;

    public DirectService(Routes routes) {
        this.routes = routes;
    }

    public Route getRoute(final int from, final int to) {
        return routes.getRoute(from, to);
    }
}
