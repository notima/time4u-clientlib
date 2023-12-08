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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "T4U_TODOS")
@NamedQueries({@NamedQuery(name = "T4uTodos.findAll", query = "SELECT t FROM T4uTodos t"), @NamedQuery(name = "T4uTodos.findByKind", query = "SELECT t FROM T4uTodos t WHERE t.kind = :kind"), @NamedQuery(name = "T4uTodos.findById", query = "SELECT t FROM T4uTodos t WHERE t.id = :id"), @NamedQuery(name = "T4uTodos.findByState", query = "SELECT t FROM T4uTodos t WHERE t.state = :state"), @NamedQuery(name = "T4uTodos.findByRevision", query = "SELECT t FROM T4uTodos t WHERE t.revision = :revision"), @NamedQuery(name = "T4uTodos.findByCompleted", query = "SELECT t FROM T4uTodos t WHERE t.completed = :completed"), @NamedQuery(name = "T4uTodos.findByDeleted", query = "SELECT t FROM T4uTodos t WHERE t.deleted = :deleted"), @NamedQuery(name = "T4uTodos.findByLastModifiedByClient", query = "SELECT t FROM T4uTodos t WHERE t.lastModifiedByClient = :lastModifiedByClient"), @NamedQuery(name = "T4uTodos.findByCompletedat", query = "SELECT t FROM T4uTodos t WHERE t.completedat = :completedat"), @NamedQuery(name = "T4uTodos.findByCreatedat", query = "SELECT t FROM T4uTodos t WHERE t.createdat = :createdat"), @NamedQuery(name = "T4uTodos.findByDeadline", query = "SELECT t FROM T4uTodos t WHERE t.deadline = :deadline"), @NamedQuery(name = "T4uTodos.findByPriority", query = "SELECT t FROM T4uTodos t WHERE t.priority = :priority"), @NamedQuery(name = "T4uTodos.findByEstimatedTime", query = "SELECT t FROM T4uTodos t WHERE t.estimatedTime = :estimatedTime")})
public class T4uTodos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "kind")
    private String kind;
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
    @Basic(optional = false)
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
    @Column(name = "completedat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date completedat;
    @Basic(optional = false)
    @Column(name = "createdat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdat;
    @Column(name = "deadline")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;
    @Basic(optional = false)
    @Column(name = "priority")
    private int priority;
    @Column(name = "estimatedTime")
    private Integer estimatedTime;
    @JoinTable(name = "T4U_TODOS_TO_PERSONS", joinColumns = {@JoinColumn(name = "todo_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "person_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<T4uPersons> t4uPersonsCollection;
    @JoinTable(name = "T4U_TODOS_DEPENDS", joinColumns = {@JoinColumn(name = "dependent_id", referencedColumnName = "id")}, inverseJoinColumns = {@JoinColumn(name = "dependsOn_id", referencedColumnName = "id")})
    @ManyToMany
    private Collection<T4uTodos> t4uTodosCollection;
    @ManyToMany(mappedBy = "t4uTodosCollection")
    private Collection<T4uTodos> t4uTodosCollection1;
    @OneToMany(mappedBy = "todoId")
    private Collection<T4uWorkitems> t4uWorkitemsCollection;
    @OneToMany(mappedBy = "groupId")
    private Collection<T4uTodos> t4uTodosCollection2;
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    @ManyToOne
    private T4uTodos groupId;
    @JoinColumn(name = "reporter_id", referencedColumnName = "id")
    @ManyToOne
    private T4uPersons reporterId;
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private T4uTasks taskId;

    public T4uTodos() {
    }

    public T4uTodos(String id) {
        this.id = id;
    }

    public T4uTodos(String id, String kind, int state, long revision, String header, String description, boolean completed, boolean deleted, long lastModifiedByClient, Date createdat, int priority) {
        this.id = id;
        this.kind = kind;
        this.state = state;
        this.revision = revision;
        this.header = header;
        this.description = description;
        this.completed = completed;
        this.deleted = deleted;
        this.lastModifiedByClient = lastModifiedByClient;
        this.createdat = createdat;
        this.priority = priority;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
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

    public Date getCompletedat() {
        return completedat;
    }

    public void setCompletedat(Date completedat) {
        this.completedat = completedat;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Integer getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Integer estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public Collection<T4uPersons> getT4uPersonsCollection() {
        return t4uPersonsCollection;
    }

    public void setT4uPersonsCollection(Collection<T4uPersons> t4uPersonsCollection) {
        this.t4uPersonsCollection = t4uPersonsCollection;
    }

    public Collection<T4uTodos> getT4uTodosCollection() {
        return t4uTodosCollection;
    }

    public void setT4uTodosCollection(Collection<T4uTodos> t4uTodosCollection) {
        this.t4uTodosCollection = t4uTodosCollection;
    }

    public Collection<T4uTodos> getT4uTodosCollection1() {
        return t4uTodosCollection1;
    }

    public void setT4uTodosCollection1(Collection<T4uTodos> t4uTodosCollection1) {
        this.t4uTodosCollection1 = t4uTodosCollection1;
    }

    public Collection<T4uWorkitems> getT4uWorkitemsCollection() {
        return t4uWorkitemsCollection;
    }

    public void setT4uWorkitemsCollection(Collection<T4uWorkitems> t4uWorkitemsCollection) {
        this.t4uWorkitemsCollection = t4uWorkitemsCollection;
    }

    public Collection<T4uTodos> getT4uTodosCollection2() {
        return t4uTodosCollection2;
    }

    public void setT4uTodosCollection2(Collection<T4uTodos> t4uTodosCollection2) {
        this.t4uTodosCollection2 = t4uTodosCollection2;
    }

    public T4uTodos getGroupId() {
        return groupId;
    }

    public void setGroupId(T4uTodos groupId) {
        this.groupId = groupId;
    }

    public T4uPersons getReporterId() {
        return reporterId;
    }

    public void setReporterId(T4uPersons reporterId) {
        this.reporterId = reporterId;
    }

    public T4uTasks getTaskId() {
        return taskId;
    }

    public void setTaskId(T4uTasks taskId) {
        this.taskId = taskId;
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
        if (!(object instanceof T4uTodos)) {
            return false;
        }
        T4uTodos other = (T4uTodos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.notima.time4u.entities.T4uTodos[id=" + id + "]";
    }

}
