package com.adwareresearch.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.springframework.security.core.GrantedAuthority;

@NamedNativeQueries({
	@NamedNativeQuery(name = "findByRoleName", 
					  query = "select * from auth_roles r where r.role_name = :roleName",
					  resultClass = AuthRoles.class)			  
})

@Entity
@Table(name="auth_roles",catalog="jsf_example", uniqueConstraints = @UniqueConstraint(columnNames="role_name"))
public class AuthRoles  implements Serializable, GrantedAuthority {

	private static final long serialVersionUID = 4765403305475261730L;
	
	private Integer id;
    private String roleName;
    private Set<AuthUserRoles> authUserRoleses = new HashSet<>();
    private Set<AuthRolePermission> authRolePermissions = new HashSet<>();

    public AuthRoles() {}

    public AuthRoles(String roleName) {
        this.roleName = roleName;
    }
    
    public AuthRoles(String roleName, Set<AuthUserRoles> authUserRoleses, Set<AuthRolePermission> authRolePermissions) {
       this.roleName = roleName;
       this.authUserRoleses = authUserRoleses;
       this.authRolePermissions = authRolePermissions;
    }
   
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name="role_name", unique=true, nullable=false, length=45)
    public String getRoleName() {
        return this.roleName;
    }
        
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="authRoles", targetEntity = AuthUserRoles.class)
    @Cascade(CascadeType.ALL)
    public Set<AuthUserRoles> getAuthUserRoleses() {
        return this.authUserRoleses;
    }
    
    public void setAuthUserRoleses(Set<AuthUserRoles> authUserRoleses) {
        this.authUserRoleses = authUserRoleses;
    }

    @OneToMany(fetch=FetchType.EAGER, mappedBy="authRoles", targetEntity = AuthRolePermission.class)
    @Cascade(CascadeType.ALL)
    public Set<AuthRolePermission> getAuthRolePermissions() {
        return this.authRolePermissions;
    }
    
    public void setAuthRolePermissions(Set<AuthRolePermission> authRolePermissions) {
        this.authRolePermissions = authRolePermissions;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((roleName == null) ? 0 : roleName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AuthRoles other = (AuthRoles) obj;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		return true;
	}

	@Override
	@Transient
	public String getAuthority() {
		return getRoleName();
	}

	@Override
	public String toString() {
		return roleName;
	}
}
