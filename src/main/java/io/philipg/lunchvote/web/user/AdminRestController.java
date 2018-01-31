package io.philipg.lunchvote.web.user;

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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static io.philipg.lunchvote.util.ValidationUtil.assureIdConsistent;
import static io.philipg.lunchvote.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = AdminRestController.REST_URL, description = "Operations about user management")
public class AdminRestController {
    static final String REST_URL = "/api/admin/users";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View users", notes = "Get all users", response = User.class, responseContainer = "List")
    public List<User> getAll(){
        log.info("getAll");
        return service.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get an user")
    public User get(@PathVariable int id){
        log.info("get {}", id);
        return service.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create an user")
    public ResponseEntity<User> createWithLocation(@Valid @RequestBody User user){
        log.info("createWithLocation {}", user);
        checkNew(user);
        User created = service.create(user);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Update an user")
    public void update(@Valid @RequestBody User user, @PathVariable("id") int id){
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        service.update(user);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete an user")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id){
        log.info("delete {}", id);
        service.delete(id);
    }

}
