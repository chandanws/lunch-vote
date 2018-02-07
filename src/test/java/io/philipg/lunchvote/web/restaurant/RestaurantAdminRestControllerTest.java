package io.philipg.lunchvote.web.restaurant;

import io.philipg.lunchvote.RestaurantTestData;
import io.philipg.lunchvote.TestUtil;
import io.philipg.lunchvote.model.Restaurant;
import io.philipg.lunchvote.model.State;
import io.philipg.lunchvote.service.RestaurantService;
import io.philipg.lunchvote.util.exception.ErrorType;
import io.philipg.lunchvote.web.AbstractControllerTest;
import io.philipg.lunchvote.web.json.JsonUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static io.philipg.lunchvote.RestaurantTestData.*;
import static io.philipg.lunchvote.TestUtil.userHttpBasic;
import static io.philipg.lunchvote.UserTestData.ADMIN;
import static io.philipg.lunchvote.UserTestData.USER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RestaurantAdminRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = RestaurantAdminRestController.REST_URL + '/';

    @Autowired
    private RestaurantService service;

    @Test
    public void testGet() throws Exception {
        TestUtil.print(
                mockMvc.perform(get(REST_URL + RESTAURANT1_ID)
                        .with(userHttpBasic(ADMIN)))
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andExpect(contentJson(RESTAURANT1)));
    }

    @Test
    public void testGetNotFound() throws Exception {
        TestUtil.print(
                mockMvc.perform(get(REST_URL + 1)
                        .with(userHttpBasic(ADMIN)))
                        .andExpect(status().isUnprocessableEntity())
                        .andDo(print()));
    }

    @Test
    public void testGetUnauth() throws Exception {
        TestUtil.print(
                mockMvc.perform(get(REST_URL))
                        .andExpect(status().isUnauthorized()));
    }

    @Test
    public void testGetForbidden() throws Exception {
        TestUtil.print(
                mockMvc.perform(get(REST_URL)
                        .with(userHttpBasic(USER)))
                        .andExpect(status().isForbidden()));
    }


    @Test
    public void testDelete() throws Exception {
        Restaurant expected = RestaurantTestData.getRemoved();
        expected.setState(State.STATE_REMOVED);
        TestUtil.print(
                mockMvc.perform(delete(REST_URL + RESTAURANT1_ID)
                        .with(userHttpBasic(ADMIN)))
                        .andDo(print())
                        .andExpect(status().isNoContent()));
        assertMatch(service.get(RESTAURANT1_ID),expected);
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        TestUtil.print(
                mockMvc.perform(delete(REST_URL + 1)
                        .with(userHttpBasic(ADMIN)))
                        .andExpect(status().isUnprocessableEntity())
                        .andDo(print()));
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant updated =  RestaurantTestData.getUpdated();
        TestUtil.print(
                mockMvc.perform(put(REST_URL + RESTAURANT1_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.writeValue(updated))
                        .with(userHttpBasic(ADMIN)))
                        .andExpect(status().isOk()));

        assertMatch(service.get(RESTAURANT1_ID), updated);
    }

    @Test
    public void testCreate() throws Exception {
        Restaurant expected = RestaurantTestData.getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());
        Restaurant returned = TestUtil.readFromJson(action, Restaurant.class);
        expected.setId(returned.getId());

        assertMatch(returned, expected);
        assertMatch(service.getAll(), RESTAURANT1, RESTAURANT2, RESTAURANT3 ,expected);
    }

    @Test
    public void testCreateInvalid() throws Exception {
        Restaurant invalid = RestaurantTestData.getCreated();
        invalid.setName("");
        TestUtil.print(
                mockMvc.perform(post(REST_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonUtil.writeValue(invalid))
                        .with(userHttpBasic(ADMIN)))
                        .andDo(print())
                        .andExpect(status().isUnprocessableEntity())
                        .andExpect(errorType(ErrorType.VALIDATION_ERROR))
                        .andDo(print()));
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(
                mockMvc.perform(get(REST_URL)
                        .with(userHttpBasic(ADMIN)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andExpect(contentJson(RESTAURANT1, RESTAURANT2, RESTAURANT3))
                        .andDo(print()));
    }
}
