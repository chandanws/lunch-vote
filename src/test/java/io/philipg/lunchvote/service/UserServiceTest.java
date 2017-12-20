package io.philipg.lunchvote.service;

import io.philipg.lunchvote.UserTestData;
import io.philipg.lunchvote.model.Role;
import io.philipg.lunchvote.model.User;
import io.philipg.lunchvote.repository.JpaUtil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import java.util.Collections;
import java.util.Date;

public class UserServiceTest extends AbstractServiceTest {
    @Autowired
    protected UserService service;

    @Autowired
    private CacheManager cacheManager;

    @Autowired (required = false)
    protected JpaUtil jpaUtil;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("users").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    public void create() throws Exception {
        User newUser = new User(null, "New", "new@gmail.com", "newPass", 1555, false, new Date(), Collections.singleton(Role.ROLE_USER));
        User created = service.create(newUser);
        newUser.setId(created.getId());
        UserTestData.assertMatch(service.getAll(), UserTestData.ADMIN, newUser, UserTestData.USER);
    }
}
