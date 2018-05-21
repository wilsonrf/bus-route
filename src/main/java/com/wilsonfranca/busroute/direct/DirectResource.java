package com.wilsonfranca.busroute.direct;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DirectResource {

    @JsonProperty("dep_sid")
    private Integer departure;

    @JsonProperty("arr_sid")
    private Integer arrival;

    @JsonProperty("direct_bus_route")
    private Boolean directBusRoute;

    public Integer getDeparture() {
        return departure;
    }

    public void setDeparture(Integer departure) {
        this.departure = departure;
    }

    public Integer getArrival() {
        return arrival;
    }

    public void setArrival(Integer arrival) {
        this.arrival = arrival;
    }

    public Boolean getDirectBusRoute() {
        return directBusRoute;
    }

    public void setDirectBusRoute(Boolean directBusRoute) {
        this.directBusRoute = directBusRoute;
    }
}
