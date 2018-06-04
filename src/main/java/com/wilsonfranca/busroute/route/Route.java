package com.wilsonfranca.busroute.route;

public class Route {

    private int from;

    private int to;

    private boolean direct;

    public Route(int from, int to, boolean direct) {
        this.from = from;
        this.to = to;
        this.direct = direct;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public boolean isDirect() {
        return direct;
    }

    public void setDirect(boolean direct) {
        this.direct = direct;
    }
}
