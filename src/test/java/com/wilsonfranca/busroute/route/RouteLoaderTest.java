package com.wilsonfranca.busroute.route;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RouteLoaderTest {

    private RouteLoader routeLoader;

    private Routes routes;

    @Before
    public void before() {
        routes = new Routes();
        this.routeLoader = new RouteLoader(routes);
    }

    /* @Test
    public void testLoadFileWithLimitLinesAndStations() {

        // generate lines
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 100000; i ++) {
            stringBuilder.append(i).append(" ");
            new Random().ints(10, 1, 1000001)
                    .forEach(value -> stringBuilder.append(value).append(" "));
            String line = stringBuilder.toString();
            routeLoader.parseLine(line);
            stringBuilder.delete(0, stringBuilder.length());
        }
    } */

    @Test
    public void testLoadFileWith10Lines() {
        routeLoader.loadFile("src/test/resources/10routesfile");
        assertThat(routeLoader.getRoutes().hasDirectConnection(5, 11)).isTrue();
        assertThat(routeLoader.getRoutes().hasDirectConnection(148, 19)).isTrue();
        assertThat(routeLoader.getRoutes().hasDirectConnection(140, 24)).isTrue();
        assertThat(routeLoader.getRoutes().hasDirectConnection(140, 19)).isFalse();
    }

}
