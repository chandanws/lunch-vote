package io.philipg.lunchvote.web.restaurant;

import io.philipg.lunchvote.TestUtil;
import io.philipg.lunchvote.service.RestaurantService;
import io.philipg.lunchvote.web.AbstractControllerTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static io.philipg.lunchvote.RestaurantTestData.RESTAURANT1;
import static io.philipg.lunchvote.RestaurantTestData.RESTAURANT1_ID;
import static io.philipg.lunchvote.TestUtil.userHttpBasic;
import static io.philipg.lunchvote.RestaurantTestData.*;
import static io.philipg.lunchvote.UserTestData.USER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantRestController.REST_URL + '/';

    @Autowired
    protected RestaurantService service;

    @Test
    public void testGetAllByState() throws Exception{
        TestUtil.print(
                mockMvc.perform(get(REST_URL)
                        .with(userHttpBasic(USER)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andExpect(contentJson(RESTAURANT1, RESTAURANT3)));
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT1));
    }
}
