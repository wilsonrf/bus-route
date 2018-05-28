package com.wilsonfranca.busroute.route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class Routes {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Map<Integer, int[]> routes;

    public Routes() {
        routes = new ConcurrentHashMap<>();
    }

    public void addRoute(final int from, final int to) {

        logger.debug("Adding route from [{}] to [{}]", from, to);
        int[] toList = routes.get(from);

        if (toList == null) {
            logger.debug("Not found route from [{}] to [{}], creating route from [{}]", from, to, from);
            ArrayList<Integer> temporaryList = new ArrayList<>();
            temporaryList.add(to);
            toList = temporaryList.stream().mapToInt(i -> i).toArray();
            routes.put(from, toList);
        } else {
            logger.debug("Founded route from [{}] to [{}] adding to [{}]", from, to, to);
            add(toList, from, to);
        }

    }

    public Map<Integer, int[]> getRoutes() {
        return routes;
    }

    public int[] stations(int from) {return routes.get(from);}

    public boolean hasDirectConnection(int from, int to) {
        int[] stations = stations(from);
        return Arrays.stream(stations).filter(value -> to == value).findFirst().isPresent();
    }

    protected void add(int[] toList, int from, int to) {
        List<Integer> temporary = Arrays.stream(toList).boxed().collect(Collectors.toList());
        temporary.add(to);
        toList = temporary.stream().mapToInt(i -> i).toArray();
        routes.put(from, toList);
    }

}
