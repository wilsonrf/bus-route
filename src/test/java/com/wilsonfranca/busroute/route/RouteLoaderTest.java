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

    @Test
    public void testLoadFileWithTopLimitLinesAndStations() {
        routeLoader.readFile("src/test/resources/routes.txt");
        assertThat(routeLoader.getRoutes().hasDirectConnection(287934, 869258)).isTrue();
    }

    @Test
    public void testLoadFileWith10Lines() {
        routeLoader.readFile("src/test/resources/10routesfile");
        assertThat(routeLoader.getRoutes().hasDirectConnection(5, 11)).isTrue();
        assertThat(routeLoader.getRoutes().hasDirectConnection(148, 19)).isTrue();
        assertThat(routeLoader.getRoutes().hasDirectConnection(140, 24)).isTrue();
        assertThat(routeLoader.getRoutes().hasDirectConnection(140, 19)).isFalse();
    }

}
