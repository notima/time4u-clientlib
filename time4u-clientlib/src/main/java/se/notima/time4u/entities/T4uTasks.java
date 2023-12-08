/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.notima.time4u.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "T4U_TASKS")
@NamedQueries({ @NamedQuery(name = "T4uTasks.findAll", query = "SELECT t FROM T4uTasks t"), 
				@NamedQuery(name = "T4uTasks.findById", query = "SELECT t FROM T4uTasks t WHERE t.id = :id"), 
				@NamedQuery(name = "T4uTasks.findByName", query = "SELECT t FROM T4uTasks t WHERE t.name = :name"), 
				@NamedQuery(name = "T4uTasks.findByRevision", query = "SELECT t FROM T4uTasks t WHERE t.revision = :revision"), 
				@NamedQuery(name = "T4uTasks.findByActive", query = "SELECT t FROM T4uTasks t WHERE t.active = :active"), 
				@NamedQuery(name = "T4uTasks.findByDeleted", query = "SELECT t FROM T4uTasks t WHERE t.deleted = :deleted"), 
				@NamedQuery(name = "T4uTasks.findByLastModifiedByClient", query = "SELECT t FROM T4uTasks t WHERE t.lastModifiedByClient = :lastModifiedByClient")})
public class T4uTasks implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "revision")
    private long revision;
    @Lob
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "active")
    private boolean active;
    @Basic(optional = false)
    @Column(name = "deleted")
    private boolean deleted;
    @Basic(optional = false)
    @Column(name = "lastModifiedByClient")
    private long lastModifiedByClient;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskId")
    private Collection<T4uWorkitems> t4uWorkitemsCollection;
    @OneToMany(mappedBy = "taskId")
    private Collection<T4uTodosdata> t4uTodosdataCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "taskId")
    private Collection<T4uTodos> t4uTodosCollection;
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @ManyToOne
    private T4uProjects projectId;

    public T4uTasks() {
    }

    public T4uTasks(String id) {
        this.id = id;
    }

    public T4uTasks(String id, String name, long revision, boolean active, boolean deleted, long lastModifiedByClient) {
        this.id = id;
        this.name = name;
        this.revision = revision;
        this.active = active;
        this.deleted = deleted;
        this.lastModifiedByClient = lastModifiedByClient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getRevision() {
        return revision;
    }

    public void setRevision(long revision) {
        this.revision = revision;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public long getLastModifiedByClient() {
        return lastModifiedByClient;
    }

    public void setLastModifiedByClient(long lastModifiedByClient) {
        this.lastModifiedByClient = lastModifiedByClient;
    }

    public Collection<T4uWorkitems> getT4uWorkitemsCollection() {
        return t4uWorkitemsCollection;
    }

    public void setT4uWorkitemsCollection(Collection<T4uWorkitems> t4uWorkitemsCollection) {
        this.t4uWorkitemsCollection = t4uWorkitemsCollection;
    }

    public Collection<T4uTodosdata> getT4uTodosdataCollection() {
        return t4uTodosdataCollection;
    }

    public void setT4uTodosdataCollection(Collection<T4uTodosdata> t4uTodosdataCollection) {
        this.t4uTodosdataCollection = t4uTodosdataCollection;
    }

    public Collection<T4uTodos> getT4uTodosCollection() {
        return t4uTodosCollection;
    }

    public void setT4uTodosCollection(Collection<T4uTodos> t4uTodosCollection) {
        this.t4uTodosCollection = t4uTodosCollection;
    }

    public T4uProjects getProjectId() {
        return projectId;
    }

    public void setProjectId(T4uProjects projectId) {
        this.projectId = projectId;
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
        if (!(object instanceof T4uTasks)) {
            return false;
        }
        T4uTasks other = (T4uTasks) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getName();
    }

}
