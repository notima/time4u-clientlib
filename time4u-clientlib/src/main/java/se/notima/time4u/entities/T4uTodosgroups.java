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
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "T4U_TODOSGROUPS")
@NamedQueries({@NamedQuery(name = "T4uTodosgroups.findAll", query = "SELECT t FROM T4uTodosgroups t"), @NamedQuery(name = "T4uTodosgroups.findById", query = "SELECT t FROM T4uTodosgroups t WHERE t.id = :id")})
public class T4uTodosgroups implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private T4uTodosbase t4uTodosbase;
    @OneToMany(mappedBy = "groupId")
    private Collection<T4uTodosbase> t4uTodosbaseCollection;

    public T4uTodosgroups() {
    }

    public T4uTodosgroups(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T4uTodosbase getT4uTodosbase() {
        return t4uTodosbase;
    }

    public void setT4uTodosbase(T4uTodosbase t4uTodosbase) {
        this.t4uTodosbase = t4uTodosbase;
    }

    public Collection<T4uTodosbase> getT4uTodosbaseCollection() {
        return t4uTodosbaseCollection;
    }

    public void setT4uTodosbaseCollection(Collection<T4uTodosbase> t4uTodosbaseCollection) {
        this.t4uTodosbaseCollection = t4uTodosbaseCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof T4uTodosgroups)) {
            return false;
        }
        T4uTodosgroups other = (T4uTodosgroups) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.notima.time4u.entities.T4uTodosgroups[id=" + id + "]";
    }

}
