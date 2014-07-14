package com.adwareresearch.domain;

import java.io.Serializable;
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
@Table(name="auth_role_permission",catalog="jsf_example")
public class AuthRolePermission  implements Serializable {
	
	private static final long serialVersionUID = 4242387867736697321L;
	
	private Integer id;
    private AuthRoles authRoles;
    private AuthPermissions authPermissions;

    public AuthRolePermission() {
    }

    public AuthRolePermission(AuthRoles authRoles, AuthPermissions authPermissions) {
       this.authRoles = authRoles;
       this.authPermissions = authPermissions;
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

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="auth_permissions_id", nullable=false)
    public AuthPermissions getAuthPermissions() {
        return this.authPermissions;
    }
    
    public void setAuthPermissions(AuthPermissions authPermissions) {
        this.authPermissions = authPermissions;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
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
        final AuthRolePermission other = (AuthRolePermission) obj;
        return Objects.equals(this.id, other.id);
    }
}


