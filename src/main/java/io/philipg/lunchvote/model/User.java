package io.philipg.lunchvote.model;

import java.util.Set;

public class User extends AbstractNamedEntity {
    
    private String email;
    
    private String password;

    private Set<Role> roles;
}
