package com.wilsonfranca.busroute.route;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedList;

@Component
public class Routes {

    private HashMap<Integer, LinkedList<Integer>> routes = new HashMap<>();

    public void addRoute(final int from, final int to) {

        LinkedList<Integer> toList = routes.get(from);

        if (toList == null) {
            toList = new LinkedList<>();
            toList.add(to);
            routes.put(from, toList);
        } else {
            if (!toList.contains(to)) {
                toList.add(to);
            }
        }

    }

    public HashMap<Integer, LinkedList<Integer>> getRoutes() {
        return routes;
    }
}
