package com.adwareresearch.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="auth_user_roles",catalog="jsf_example")
public class AuthUserRoles  implements java.io.Serializable {

    private Integer id;
    private AuthRoles authRoles;
    private AuthUser authUser;

    public AuthUserRoles() {
    }

    public AuthUserRoles(AuthRoles authRoles, AuthUser authUser) {
       this.authRoles = authRoles;
       this.authUser = authUser;
    }
   
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="auth_roles_id", nullable=false)
    public AuthRoles getAuthRoles() {
        return this.authRoles;
    }
    
    public void setAuthRoles(AuthRoles authRoles) {
        this.authRoles = authRoles;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="auth_user_id", nullable=false)
    public AuthUser getAuthUser() {
        return this.authUser;
    }
    
    public void setAuthUser(AuthUser authUser) {
        this.authUser = authUser;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AuthUserRoles other = (AuthUserRoles) obj;
        return Objects.equals(this.id, other.id);
    }
}


