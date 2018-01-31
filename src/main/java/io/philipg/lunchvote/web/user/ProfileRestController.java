package io.philipg.lunchvote.web.user;

import io.philipg.lunchvote.AuthorizedUser;
import io.philipg.lunchvote.model.User;
import io.philipg.lunchvote.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

import java.net.URI;

import static io.philipg.lunchvote.util.ValidationUtil.assureIdConsistent;
import static io.philipg.lunchvote.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = ProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Api( value = ProfileRestController.REST_URL, description = "Operations about user's profile")
public class ProfileRestController {
    static final String REST_URL = "/api/profile";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@AuthenticationPrincipal AuthorizedUser authorizedUser) {
        log.info("get {}", authorizedUser.getId());
        return service.get(authorizedUser.getId());
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@AuthenticationPrincipal AuthorizedUser authorizedUser) {
        log.info("delete {}", authorizedUser.getId());
        service.delete(authorizedUser.getId());
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody User user, @AuthenticationPrincipal AuthorizedUser authorizedUser) {
        log.info("update {} with id={}", user, authorizedUser.getId());
        assureIdConsistent(user, authorizedUser.getId());
        service.update(user);
    }

    @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Register an user")
    public ResponseEntity<User> registerWithLocation(@Valid @RequestBody User user){
        log.info("registerWithLocation {}", user);
        checkNew(user);
        User created = service.create(user);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
