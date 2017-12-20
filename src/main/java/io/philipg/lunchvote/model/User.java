package io.philipg.lunchvote.model;

import java.util.Date;
import java.util.Set;

public class User extends AbstractNamedEntity {
    
    private String email;
    
    private String password;

    private Set<Role> roles;

    public User(int userId, String user, String s, String password, Role roleUser) {
        
    }

    public User(int adminId, String admin, String s, String admin1, Role roleAdmin, Role roleUser) {
    }

    public <T> User(Object o, String aNew, String s, String newPass, int i, boolean b, Date date, Set<T> singleton) {
    }
}
