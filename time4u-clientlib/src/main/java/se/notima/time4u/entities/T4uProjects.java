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
 * @author Daniel Tamm
 */
@Entity
@Table(name = "T4U_PROJECTS")
@NamedQueries({@NamedQuery(name = "T4uProjects.findAll", query = "SELECT t FROM T4uProjects t"),
			   @NamedQuery(name = "T4uProjects.findAllActive", query = "SELECT t FROM T4uProjects t WHERE t.active = :active and t.deleted = :deleted"),
               @NamedQuery(name = "T4uProjects.findById", query = "SELECT t FROM T4uProjects t WHERE t.id = :id"),
               @NamedQuery(name = "T4uProjects.findByName", query = "SELECT t FROM T4uProjects t WHERE t.name = :name"),
               @NamedQuery(name = "T4uProjects.findByRevision", query = "SELECT t FROM T4uProjects t WHERE t.revision = :revision"),
               @NamedQuery(name = "T4uProjects.findByActive", query = "SELECT t FROM T4uProjects t WHERE t.active = :active"),
               @NamedQuery(name = "T4uProjects.findByDeleted", query = "SELECT t FROM T4uProjects t WHERE t.deleted = :deleted"),
               @NamedQuery(name = "T4uProjects.findByParentId", query = "SELECT t FROM T4uProjects t WHERE t.parentId = :parentId"),
               @NamedQuery(name = "T4uProjects.findRootProjects", query = "SELECT t FROM T4uProjects t WHERE t.parentId is null and t.active = :active and t.deleted = :deleted ORDER BY t.name"),
               @NamedQuery(name = "T4uProjects.findByLastModifiedByClient", query = "SELECT t FROM T4uProjects t WHERE t.lastModifiedByClient = :lastModifiedByClient")})
public class T4uProjects implements Serializable {
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
    //@Lob
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
    //@Lob
    @Column(name = "parentKey")
    private String parentKey;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectId")
    private Collection<T4uWorkitems> t4uWorkitemsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "t4uProjects")
    private Collection<T4uProjectsProperties> t4uProjectsPropertiesCollection;
    @OneToMany(mappedBy = "parentId")
    private Collection<T4uProjects> t4uProjectsCollection;
    @JoinColumn(name = "parent_id", referencedColumnName = "id")
    @ManyToOne
    private T4uProjects parentId;
    @OneToMany(mappedBy = "projectId")
    private Collection<T4uTasks> t4uTasksCollection;

    public T4uProjects() {
    }

    public T4uProjects(String id) {
        this.id = id;
    }

    public T4uProjects(String id, String name, long revision, boolean active, boolean deleted, long lastModifiedByClient) {
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

    public String getParentKey() {
        return parentKey;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }

    public Collection<T4uWorkitems> getT4uWorkitemsCollection() {
        return t4uWorkitemsCollection;
    }

    public void setT4uWorkitemsCollection(Collection<T4uWorkitems> t4uWorkitemsCollection) {
        this.t4uWorkitemsCollection = t4uWorkitemsCollection;
    }

    public Collection<T4uProjectsProperties> getT4uProjectsPropertiesCollection() {
        return t4uProjectsPropertiesCollection;
    }

    public void setT4uProjectsPropertiesCollection(Collection<T4uProjectsProperties> t4uProjectsPropertiesCollection) {
        this.t4uProjectsPropertiesCollection = t4uProjectsPropertiesCollection;
    }

    public Collection<T4uProjects> getT4uProjectsCollection() {
        return t4uProjectsCollection;
    }

    public void setT4uProjectsCollection(Collection<T4uProjects> t4uProjectsCollection) {
        this.t4uProjectsCollection = t4uProjectsCollection;
    }

    public T4uProjects getParentId() {
        return parentId;
    }

    public void setParentId(T4uProjects parentId) {
        this.parentId = parentId;
    }

    public Collection<T4uTasks> getT4uTasksCollection() {
        return t4uTasksCollection;
    }

    public void setT4uTasksCollection(Collection<T4uTasks> t4uTasksCollection) {
        this.t4uTasksCollection = t4uTasksCollection;
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
        if (!(object instanceof T4uProjects)) {
            return false;
        }
        T4uProjects other = (T4uProjects) object;
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
