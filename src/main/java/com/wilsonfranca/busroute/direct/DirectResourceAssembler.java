package com.wilsonfranca.busroute.direct;

import com.wilsonfranca.busroute.route.Route;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class DirectResourceAssembler extends ResourceAssemblerSupport<Route, DirectResource> {

    /**
     * Creates a new {@link ResourceAssemblerSupport} using the given controller class and resource type.
     *
     * @param controllerClass must not be {@literal null}.
     * @param resourceType    must not be {@literal null}.
     */
    public DirectResourceAssembler(Class<?> controllerClass, Class<DirectResource> resourceType) {
        super(controllerClass, resourceType);
    }

    public DirectResourceAssembler() {
        super(DirectController.class, DirectResource.class);
    }

    @Override
    public DirectResource toResource(Route entity) {

        DirectResource directResource = new DirectResource(entity.getFrom(), entity.getTo(), entity.isDirect());

        return directResource;
    }
}
