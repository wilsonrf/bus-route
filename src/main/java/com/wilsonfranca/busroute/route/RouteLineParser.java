package com.wilsonfranca.busroute.route;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RouteLineParser {

    private Logger logger = LoggerFactory.getLogger(RouteLineParser.class);

    /**
     * Parse a line of a route
     * The first element on a line is the route unique identifier
     * A station id can never occur twice within the same bus route.
     * @param line
     * @return
     */
    public Map<Integer, int[]> parse(final String line) {

        Assert.hasLength(line, "Line cannot be null or empty");

        Map<Integer, int[]> routes = new HashMap<>();

        logger.debug("Line to be parsed: [{}]", line);

        List<Map<Integer, int[]>> result = new ArrayList<>();

        String[] strings = line.split(" ");

        List<String> stringList = IntStream.range(0, strings.length)
                .filter(i -> i > 0)
                .mapToObj(i -> strings[i])
                .filter(StringUtils::hasLength)
                .collect(Collectors.toList());

        for (int i = 0; i < stringList.size(); i++) {
            int from = Integer.valueOf(stringList.get(i));
            int[] to = stringList.stream()
                    .skip(i + 1)
                    .mapToInt(Integer::valueOf).toArray();
            routes.put(from, to);
        }

        return routes;
    }
}
