package io.philipg.lunchvote.web.user;

import io.philipg.lunchvote.model.Role;
import io.philipg.lunchvote.model.User;
import io.philipg.lunchvote.service.UserService;
import io.philipg.lunchvote.web.AbstractControllerTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Collections;

import static io.philipg.lunchvote.TestUtil.userHttpBasic;
import static io.philipg.lunchvote.UserTestData.*;
import static io.philipg.lunchvote.UserTestData.assertMatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProfileRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = ProfileRestController.REST_URL + '/';

    @Autowired
    protected UserService userService;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + USER_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(USER));
    }

    @Test
    public void testDelete() throws Exception{
        mockMvc.perform(delete(REST_URL + USER_ID)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(userService.getAll(), USER);
    }

    @Test
    public void testUpdate() throws Exception{
        User updated = new User(USER);
        updated.setName("UpdatedProfileName");
        mockMvc.perform(put(REST_URL + USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER))
                .content(jsonWithPassword(updated, USER.getPassword())))
                .andExpect(status().isOk());

        assertMatch(userService.get(USER_ID), updated);
    }

}
