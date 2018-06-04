package com.wilsonfranca.busroute.route;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;

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
    public void testTryReadFileFromTestResourcesSuccessfully() {

        Path path = routeLoader.readFile("src/test/resources/10routesfile");
        assertThat(path).isNotNull();
        assertThat(path).isRegularFile();

    }

    @Test(expected = RuntimeException.class)
    public void testTryReadNullFilePathAndThrowException() {
        Path path = routeLoader.readFile(null);
    }

    @Test(expected = RuntimeException.class)
    public void testTryReadEmptyFilePathAndThrowException() {
        Path path = routeLoader.readFile("");
    }

    @Test(expected = RuntimeException.class)
    public void testTryLoadNullFilePathAndThrowException() {
        routeLoader.load(null);
    }

    @Test(expected = RuntimeException.class)
    public void testTryLoadEmptyFilePathAndThrowException() {
        routeLoader.load("");
    }

    @Test
    public void testTryLoadFileWith10Lines() {
        routeLoader.load("src/test/resources/10routesfile");
        assertThat(routeLoader.getRoutes().hasDirectConnection(5, 11)).isTrue();
        assertThat(routeLoader.getRoutes().hasDirectConnection(148, 19)).isTrue();
        assertThat(routeLoader.getRoutes().hasDirectConnection(140, 24)).isTrue();
        assertThat(routeLoader.getRoutes().hasDirectConnection(140, 19)).isFalse();
    }
}
