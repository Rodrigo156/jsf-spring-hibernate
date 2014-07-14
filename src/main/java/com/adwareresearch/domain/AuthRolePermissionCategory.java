package com.adwareresearch.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="auth_role_permissions_category",catalog="jsf_example")
public class AuthRolePermissionCategory implements Serializable {
	
	private static final long serialVersionUID = -4284320863318261102L;
	
	private int id;
    private String categoryName;
    private Set<AuthPermissions> permissions = new HashSet<>(0);

    public AuthRolePermissionCategory() {}
    
    public AuthRolePermissionCategory(int id, String categoryName, Set<AuthPermissions> permissions) {
        this.id = id;
        this.categoryName = categoryName;
        this.permissions = permissions;
    }

    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name="category_name", unique=true, nullable=false, length=200)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="permissionCategory", targetEntity = AuthPermissions.class)
    @Cascade(CascadeType.ALL)
    public Set<AuthPermissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<AuthPermissions> permissions) {
        this.permissions = permissions;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((categoryName == null) ? 0 : categoryName.hashCode());
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
		AuthRolePermissionCategory other = (AuthRolePermissionCategory) obj;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		return true;
	}
}
