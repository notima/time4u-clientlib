/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.notima.time4u.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "T4U_USERACCOUNTS")
@NamedQueries({@NamedQuery(name = "T4uUseraccounts.findAll", query = "SELECT t FROM T4uUseraccounts t"), @NamedQuery(name = "T4uUseraccounts.findByUserId", query = "SELECT t FROM T4uUseraccounts t WHERE t.userId = :userId"), @NamedQuery(name = "T4uUseraccounts.findByHashedPassword", query = "SELECT t FROM T4uUseraccounts t WHERE t.hashedPassword = :hashedPassword"), @NamedQuery(name = "T4uUseraccounts.findByLastLogin", query = "SELECT t FROM T4uUseraccounts t WHERE t.lastLogin = :lastLogin")})
public class T4uUseraccounts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "userId")
    private String userId;
    @Column(name = "hashedPassword")
    private String hashedPassword;
    @Column(name = "lastLogin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    @JoinTable(name = "T4U_USERACCOUNT_USERROLES", joinColumns = {@JoinColumn(name = "userId", referencedColumnName = "userId")}, inverseJoinColumns = {@JoinColumn(name = "roleId", referencedColumnName = "roleId")})
    @ManyToMany
    private Collection<T4uUserroles> t4uUserrolesCollection;
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private T4uPersons personId;

    public T4uUseraccounts() {
    }

    public T4uUseraccounts(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Collection<T4uUserroles> getT4uUserrolesCollection() {
        return t4uUserrolesCollection;
    }

    public void setT4uUserrolesCollection(Collection<T4uUserroles> t4uUserrolesCollection) {
        this.t4uUserrolesCollection = t4uUserrolesCollection;
    }

    public T4uPersons getPersonId() {
        return personId;
    }

    public void setPersonId(T4uPersons personId) {
        this.personId = personId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof T4uUseraccounts)) {
            return false;
        }
        T4uUseraccounts other = (T4uUseraccounts) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.notima.time4u.entities.T4uUseraccounts[userId=" + userId + "]";
    }

}
