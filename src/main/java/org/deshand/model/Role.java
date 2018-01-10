package org.deshand.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author Roland Krüger
 */
//@Entity
public class Role implements GrantedAuthority {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
    private String authority;

    public Role() {}

    public Role(String authority) {
        setAuthority(authority);
    }

    @Id
//    @GeneratedValue
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAuthority(String authority) {
        Objects.requireNonNull(authority);
        this.authority = authority;
    }

//    @Override
//    @Column(unique = true)
    public String getAuthority() {
        return authority;
    }

    @Override
    public String toString() {
        return authority;
    }
}
