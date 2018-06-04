package com.wilsonfranca.busroute.direct;

import com.wilsonfranca.busroute.route.Route;
import com.wilsonfranca.busroute.route.RouteNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/direct")
public class DirectController {

    private DirectService directService;

    private DirectResourceAssembler directResourceAssembler;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public DirectController(DirectService directService, DirectResourceAssembler directResourceAssembler) {
        this.directService = directService;
        this.directResourceAssembler = directResourceAssembler;
    }

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<?> direct(@RequestParam(name = "dep_sid", required = true) final Integer departure,
                              @RequestParam(name = "arr_sid", required = true) final Integer arrival) {

        logger.info("Start direct controller: dep_sid=[{}] arr_sid=[{}]", departure, arrival);

        Optional<Route> optional = directService.getRoute(departure, arrival);

        Route route = optional.orElseThrow(RouteNotFoundException::new);

        DirectResource directResource = directResourceAssembler.toResource(route);

        return ResponseEntity.ok(directResource);
    }

}
