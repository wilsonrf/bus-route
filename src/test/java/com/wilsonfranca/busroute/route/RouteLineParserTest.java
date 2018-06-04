package com.wilsonfranca.busroute.route;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnit4.class)
public class RouteLineParserTest {

    private RouteLineParser routeLineParser;

    @Before
    public void setup() {
        routeLineParser = new RouteLineParser();
    }

    @Test
    public void testTryToParseA5routesLineSuccessfully() {

        final String line = "1 70 54 16 35 190";

        Map<Integer, int[]> routes = routeLineParser.parse(line);

        assertThat(routes.size()).isEqualTo(5);

        assertThat(routes.get(70)).contains(54, 16, 35, 190);
        assertThat(routes.get(54)).contains(16, 35, 190);
        assertThat(routes.get(16)).contains(35, 190);
        assertThat(routes.get(35)).contains(190);
    }

    @Test(expected = RuntimeException.class)
    public void testTryToParseAEmptyLineAndThrowsException() {

        final String line = "";

        routeLineParser.parse(line);
    }

    @Test(expected = RuntimeException.class)
    public void testTryToParseANullLineAndThrowsException() {

        final String line = null;

        routeLineParser.parse(line);
    }
}
