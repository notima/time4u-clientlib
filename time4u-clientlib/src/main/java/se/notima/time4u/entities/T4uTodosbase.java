/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.notima.time4u.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "T4U_TODOSBASE")
@NamedQueries({@NamedQuery(name = "T4uTodosbase.findAll", query = "SELECT t FROM T4uTodosbase t"), @NamedQuery(name = "T4uTodosbase.findById", query = "SELECT t FROM T4uTodosbase t WHERE t.id = :id"), @NamedQuery(name = "T4uTodosbase.findByState", query = "SELECT t FROM T4uTodosbase t WHERE t.state = :state"), @NamedQuery(name = "T4uTodosbase.findByRevision", query = "SELECT t FROM T4uTodosbase t WHERE t.revision = :revision"), @NamedQuery(name = "T4uTodosbase.findByCompleted", query = "SELECT t FROM T4uTodosbase t WHERE t.completed = :completed"), @NamedQuery(name = "T4uTodosbase.findByDeleted", query = "SELECT t FROM T4uTodosbase t WHERE t.deleted = :deleted"), @NamedQuery(name = "T4uTodosbase.findByLastModifiedByClient", query = "SELECT t FROM T4uTodosbase t WHERE t.lastModifiedByClient = :lastModifiedByClient"), @NamedQuery(name = "T4uTodosbase.findByCreatedat", query = "SELECT t FROM T4uTodosbase t WHERE t.createdat = :createdat"), @NamedQuery(name = "T4uTodosbase.findByCompletedat", query = "SELECT t FROM T4uTodosbase t WHERE t.completedat = :completedat"), @NamedQuery(name = "T4uTodosbase.findByDeadline", query = "SELECT t FROM T4uTodosbase t WHERE t.deadline = :deadline")})
public class T4uTodosbase implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "state")
    private int state;
    @Basic(optional = false)
    @Column(name = "revision")
    private long revision;
    @Basic(optional = false)
    @Lob
    @Column(name = "header")
    private String header;
    @Lob
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "completed")
    private boolean completed;
    @Basic(optional = false)
    @Column(name = "deleted")
    private boolean deleted;
    @Basic(optional = false)
    @Column(name = "lastModifiedByClient")
    private long lastModifiedByClient;
    @Basic(optional = false)
    @Column(name = "createdat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdat;
    @Column(name = "completedat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date completedat;
    @Column(name = "deadline")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;
    @JoinTable(name = "T4U_TODOSBASE_TO_PERSONS", joinColumns = {@JoinColumn(name = "todo_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "person_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<T4uPersons> t4uPersonsCollection;
    @JoinTable(name = "T4U_TODOSBASE_DEPENDS", joinColumns = {@JoinColumn(name = "dependent_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "dependsOn_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<T4uTodosbase> t4uTodosbaseCollection;
    @ManyToMany(mappedBy = "t4uTodosbaseCollection")
    private Collection<T4uTodosbase> t4uTodosbaseCollection1;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "t4uTodosbase")
    private T4uTodosgroups t4uTodosgroups;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "t4uTodosbase")
    private T4uTodosdata t4uTodosdata;
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    @ManyToOne
    private T4uTodosgroups groupId;
    @JoinColumn(name = "reporter_id", referencedColumnName = "id")
    @ManyToOne
    private T4uPersons reporterId;

    public T4uTodosbase() {
    }

    public T4uTodosbase(String id) {
        this.id = id;
    }

    public T4uTodosbase(String id, int state, long revision, String header, boolean completed, boolean deleted, long lastModifiedByClient, Date createdat) {
        this.id = id;
        this.state = state;
        this.revision = revision;
        this.header = header;
        this.completed = completed;
        this.deleted = deleted;
        this.lastModifiedByClient = lastModifiedByClient;
        this.createdat = createdat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getRevision() {
        return revision;
    }

    public void setRevision(long revision) {
        this.revision = revision;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
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

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public Date getCompletedat() {
        return completedat;
    }

    public void setCompletedat(Date completedat) {
        this.completedat = completedat;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Collection<T4uPersons> getT4uPersonsCollection() {
        return t4uPersonsCollection;
    }

    public void setT4uPersonsCollection(Collection<T4uPersons> t4uPersonsCollection) {
        this.t4uPersonsCollection = t4uPersonsCollection;
    }

    public Collection<T4uTodosbase> getT4uTodosbaseCollection() {
        return t4uTodosbaseCollection;
    }

    public void setT4uTodosbaseCollection(Collection<T4uTodosbase> t4uTodosbaseCollection) {
        this.t4uTodosbaseCollection = t4uTodosbaseCollection;
    }

    public Collection<T4uTodosbase> getT4uTodosbaseCollection1() {
        return t4uTodosbaseCollection1;
    }

    public void setT4uTodosbaseCollection1(Collection<T4uTodosbase> t4uTodosbaseCollection1) {
        this.t4uTodosbaseCollection1 = t4uTodosbaseCollection1;
    }

    public T4uTodosgroups getT4uTodosgroups() {
        return t4uTodosgroups;
    }

    public void setT4uTodosgroups(T4uTodosgroups t4uTodosgroups) {
        this.t4uTodosgroups = t4uTodosgroups;
    }

    public T4uTodosdata getT4uTodosdata() {
        return t4uTodosdata;
    }

    public void setT4uTodosdata(T4uTodosdata t4uTodosdata) {
        this.t4uTodosdata = t4uTodosdata;
    }

    public T4uTodosgroups getGroupId() {
        return groupId;
    }

    public void setGroupId(T4uTodosgroups groupId) {
        this.groupId = groupId;
    }

    public T4uPersons getReporterId() {
        return reporterId;
    }

    public void setReporterId(T4uPersons reporterId) {
        this.reporterId = reporterId;
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
        if (!(object instanceof T4uTodosbase)) {
            return false;
        }
        T4uTodosbase other = (T4uTodosbase) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.notima.time4u.entities.T4uTodosbase[id=" + id + "]";
    }

}
