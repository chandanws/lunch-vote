package io.philipg.lunchvote.web.dish;

import io.philipg.lunchvote.TestUtil;
import io.philipg.lunchvote.service.DishService;
import io.philipg.lunchvote.web.AbstractControllerTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static io.philipg.lunchvote.DishTestData.*;
import static io.philipg.lunchvote.RestaurantTestData.RESTAURANT1_ID;
import static io.philipg.lunchvote.TestUtil.userHttpBasic;
import static io.philipg.lunchvote.UserTestData.USER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DishRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = DishRestController.REST_URL + '/';

    @Autowired
    protected DishService service;

    @Test
    public void testGetAllByState() throws Exception {
        TestUtil.print(
                mockMvc.perform(get(REST_URL + "?restaurantId=" + RESTAURANT1_ID)
                        .with(userHttpBasic(USER)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andExpect(contentJson(DISH1, DISH2)));
    }
}
