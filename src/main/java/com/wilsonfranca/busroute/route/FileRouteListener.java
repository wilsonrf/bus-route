package com.wilsonfranca.busroute.route;

import com.wilsonfranca.busroute.config.BusRouteConfigProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class FileRouteListener implements ApplicationListener<ContextRefreshedEvent> {

    private RouteLoader routeLoader;

    private BusRouteConfigProperties busRouteConfigProperties;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public FileRouteListener(final RouteLoader routeLoader, final BusRouteConfigProperties busRouteConfigProperties) {
        this.routeLoader = routeLoader;
        this.busRouteConfigProperties = busRouteConfigProperties;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logger.debug("Starting application event to load file...");
        String file = busRouteConfigProperties.getFilePath();
        if (StringUtils.hasLength(file)) {
            logger.info("File path registred on initialization: [{}]", file);
            routeLoader.load(file);
        } else {
            logger.info("No file path registered on initialization");
        }
    }
}
