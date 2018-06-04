package com.wilsonfranca.busroute;

import com.wilsonfranca.busroute.direct.DirectResource;
import com.wilsonfranca.busroute.direct.DirectResourceAssembler;
import com.wilsonfranca.busroute.direct.DirectService;
import com.wilsonfranca.busroute.route.Route;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class DirectControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DirectService directService;

    @MockBean
    DirectResourceAssembler directResourceAssembler;

    @Test
    public void testTryGettingAnExistingDirectRouteAndGet200Ok() throws Exception {
        Route route = new Route(3, 4, true);
        when(directService.getRoute(3, 4)).thenReturn(Optional.of(route));
        when(directResourceAssembler.toResource(route)).thenReturn(new DirectResource(3, 4, true));
        mockMvc.perform(get("/api/direct")
                .param("dep_sid", "3")
                .param("arr_sid", "4"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'dep_sid': 3, 'arr_sid': 4, 'direct_bus_route': true}"));
    }


    @Test
    public void testTryGettingAnExistingNotDirectRouteAndGet200Ok() throws Exception {
        Route route = new Route(5, 6, false);
        when(directService.getRoute(5, 6)).thenReturn(Optional.of(route));
        when(directResourceAssembler.toResource(route)).thenReturn(new DirectResource(5, 6, false));
        mockMvc.perform(get("/api/direct")
                .param("dep_sid", "5")
                .param("arr_sid", "6"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'dep_sid': 5, 'arr_sid': 6, 'direct_bus_route': false}"));
    }

    @Test
    public void testTryGettingAdirectRouteWithoutDepSidParameterAndGet400BadRequest() throws Exception {
        mockMvc.perform(get("/api/direct")
                .param("arr_sid", "4"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testTryGettingAdirectRouteWithoutArrSidParameterAndGet400BadRequest() throws Exception {

        mockMvc.perform(get("/api/direct")
                .param("dep_sid", "3"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testTryGettingAdirectRouteWithoutBothParametersAndGet400BadRequest() throws Exception {

        mockMvc.perform(get("/api/direct"))
                .andExpect(status().isBadRequest());
    }


}
