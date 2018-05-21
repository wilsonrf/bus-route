package com.wilsonfranca.busroute.direct;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/direct")
public class DirectController {

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<?> list(@RequestParam(name = "dep_sid", required = true) final String departure,
                              @RequestParam(name = "arr_sid", required = true) final String arrival) {
        return ResponseEntity.notFound().build();
    }

}
