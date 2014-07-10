package com.adwareresearch.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@NamedNativeQueries({
	@NamedNativeQuery(name = "findByUsernameAndPassword", 
					  query = "select * from auth_user u where u.user_name = :username and u.password = :password",
					  resultClass = AuthUser.class),
    @NamedNativeQuery(name = "findByUsername", 
					  query = "select * from auth_user u where u.user_name = :username",
					  resultClass = AuthUser.class),
	@NamedNativeQuery(name = "list",
					  query = "select * from auth_user",
					  resultClass = AuthUser.class)				  
})

@Entity
@Table(name="auth_user",catalog="jsf_example", uniqueConstraints = @UniqueConstraint(columnNames="user_name"))
public class AuthUser  implements Serializable {

     private Integer id;
     private String userName;
     private String password;
     private String firstName;
     private String lastName;
     private String email;
     private Date passwordExpiry;
     private Byte userLocked;
     private Byte userActive;
     private Set<AuthUserRoles> authUserRoleses = new HashSet<>(0);

    public AuthUser() {}

    public AuthUser(String userName, String password, Date passwordExpiry) {
        this.userName = userName;
        this.password = password;
        this.passwordExpiry = passwordExpiry;
    }
    
    public AuthUser(String userName, String password, String firstName, String lastName, String email, Date passwordExpiry, Byte userLocked, Byte userActive, Set<AuthUserRoles> authUserRoleses) {
       this.userName = userName;
       this.password = password;
       this.firstName = firstName;
       this.lastName = lastName;
       this.email = email;
       this.passwordExpiry = passwordExpiry;
       this.userLocked = userLocked;
       this.userActive = userActive;
       this.authUserRoleses = authUserRoleses;
    }
   
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="user_name", unique=true, nullable=false, length=45)
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
  
    @Column(name="password", nullable=false, length=200)
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="first_name", length=45)
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name="last_name", length=45)
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name="email", length=200)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="password_expiry", nullable=false, length=19)
    public Date getPasswordExpiry() {
        return this.passwordExpiry;
    }
    
    public void setPasswordExpiry(Date passwordExpiry) {
        this.passwordExpiry = passwordExpiry;
    }

    @Column(name="user_locked")
    public Byte getUserLocked() {
        return this.userLocked;
    }
    
    public void setUserLocked(Byte userLocked) {
        this.userLocked = userLocked;
    }

    @Column(name="user_active")
    public Byte getUserActive() {
        return this.userActive;
    }
    
    public void setUserActive(Byte userActive) {
        this.userActive = userActive;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="authUser", targetEntity = AuthUserRoles.class)
    public Set<AuthUserRoles> getAuthUserRoleses() {
        return this.authUserRoleses;
    }
    
    public void setAuthUserRoleses(Set<AuthUserRoles> authUserRoleses) {
        this.authUserRoleses = authUserRoleses;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
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
		AuthUser other = (AuthUser) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
}