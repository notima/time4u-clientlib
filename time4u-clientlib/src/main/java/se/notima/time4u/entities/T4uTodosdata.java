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
import javax.persistence.ManyToOne;
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
@Table(name = "T4U_TODOSDATA")
@NamedQueries({@NamedQuery(name = "T4uTodosdata.findAll", query = "SELECT t FROM T4uTodosdata t"), @NamedQuery(name = "T4uTodosdata.findById", query = "SELECT t FROM T4uTodosdata t WHERE t.id = :id"), @NamedQuery(name = "T4uTodosdata.findByPriority", query = "SELECT t FROM T4uTodosdata t WHERE t.priority = :priority"), @NamedQuery(name = "T4uTodosdata.findByEstimatedTime", query = "SELECT t FROM T4uTodosdata t WHERE t.estimatedTime = :estimatedTime")})
public class T4uTodosdata implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "priority")
    private int priority;
    @Column(name = "estimatedTime")
    private Integer estimatedTime;
    @OneToMany(mappedBy = "tododataId")
    private Collection<T4uWorkitems> t4uWorkitemsCollection;
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    @ManyToOne
    private T4uTasks taskId;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private T4uTodosbase t4uTodosbase;

    public T4uTodosdata() {
    }

    public T4uTodosdata(String id) {
        this.id = id;
    }

    public T4uTodosdata(String id, int priority) {
        this.id = id;
        this.priority = priority;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Collection<T4uWorkitems> getT4uWorkitemsCollection() {
        return t4uWorkitemsCollection;
    }

    public void setT4uWorkitemsCollection(Collection<T4uWorkitems> t4uWorkitemsCollection) {
        this.t4uWorkitemsCollection = t4uWorkitemsCollection;
    }

    public T4uTasks getTaskId() {
        return taskId;
    }

    public void setTaskId(T4uTasks taskId) {
        this.taskId = taskId;
    }

    public T4uTodosbase getT4uTodosbase() {
        return t4uTodosbase;
    }

    public void setT4uTodosbase(T4uTodosbase t4uTodosbase) {
        this.t4uTodosbase = t4uTodosbase;
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
        if (!(object instanceof T4uTodosdata)) {
            return false;
        }
        T4uTodosdata other = (T4uTodosdata) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.notima.time4u.entities.T4uTodosdata[id=" + id + "]";
    }

}
