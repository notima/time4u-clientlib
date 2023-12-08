/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.notima.time4u.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "T4U_USERROLES")
@NamedQueries({@NamedQuery(name = "T4uUserroles.findAll", query = "SELECT t FROM T4uUserroles t"), @NamedQuery(name = "T4uUserroles.findByRoleId", query = "SELECT t FROM T4uUserroles t WHERE t.roleId = :roleId"), @NamedQuery(name = "T4uUserroles.findByName", query = "SELECT t FROM T4uUserroles t WHERE t.name = :name")})
public class T4uUserroles implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "roleId")
    private String roleId;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "t4uUserrolesCollection")
    private Collection<T4uUseraccounts> t4uUseraccountsCollection;

    public T4uUserroles() {
    }

    public T4uUserroles(String roleId) {
        this.roleId = roleId;
    }

    public T4uUserroles(String roleId, String name) {
        this.roleId = roleId;
        this.name = name;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<T4uUseraccounts> getT4uUseraccountsCollection() {
        return t4uUseraccountsCollection;
    }

    public void setT4uUseraccountsCollection(Collection<T4uUseraccounts> t4uUseraccountsCollection) {
        this.t4uUseraccountsCollection = t4uUseraccountsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof T4uUserroles)) {
            return false;
        }
        T4uUserroles other = (T4uUserroles) object;
        if ((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.notima.time4u.entities.T4uUserroles[roleId=" + roleId + "]";
    }

}
