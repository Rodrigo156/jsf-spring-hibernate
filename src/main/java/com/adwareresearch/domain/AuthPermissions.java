package com.adwareresearch.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@NamedNativeQueries({
	@NamedNativeQuery(name = "findAvailablePermissions", 
                            query = "select * from auth_permissions p where p.auth_role_permissions_category_id is null",
                            resultClass = AuthPermissions.class)			  
})

@Entity
@Table(name="auth_permissions",catalog="jsf_example",uniqueConstraints = @UniqueConstraint(columnNames="permission_name"))
public class AuthPermissions  implements Serializable {

     private Integer id;
     private String permissionName;
     private String description;
     private AuthRolePermissionCategory permissionCategory;
     private Set<AuthRolePermission> authRolePermissions = new HashSet<>(0);

    public AuthPermissions() {
    }

	
    public AuthPermissions(String permissionName) {
        this.permissionName = permissionName;
    }
    public AuthPermissions(String permissionName, String description, AuthRolePermissionCategory permissionCategory, Set<AuthRolePermission> authRolePermissions) {
       this.permissionName = permissionName;
       this.authRolePermissions = authRolePermissions;
       this.description = description;
       this.permissionCategory = permissionCategory;
    }
   
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="permission_name", unique=true, nullable=false, length=45)
    public String getPermissionName() {
        return this.permissionName;
    }
    
    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="authPermissions", targetEntity = AuthRolePermission.class)
    @Cascade(CascadeType.ALL)
    public Set<AuthRolePermission> getAuthRolePermissions() {
        return this.authRolePermissions;
    }
    
    public void setAuthRolePermissions(Set<AuthRolePermission> authRolePermissions) {
        this.authRolePermissions = authRolePermissions;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="auth_role_permissions_category_id", nullable=false)
    public AuthRolePermissionCategory getPermissionCategory() {
        return permissionCategory;
    }

    public void setPermissionCategory(AuthRolePermissionCategory permissionCategory) {
        this.permissionCategory = permissionCategory;
    }

    @Column(name="description", length=200)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((permissionName == null) ? 0 : permissionName.hashCode());
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
		AuthPermissions other = (AuthPermissions) obj;
		if (permissionName == null) {
			if (other.permissionName != null)
				return false;
		} else if (!permissionName.equals(other.permissionName))
			return false;
		return true;
	}


	@Override
    public String toString() {
        return permissionName;
    }
}


