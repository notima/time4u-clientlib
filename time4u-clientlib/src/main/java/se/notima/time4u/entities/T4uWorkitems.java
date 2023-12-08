/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.notima.time4u.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "T4U_WORKITEMS")
@NamedQueries({@NamedQuery(name = "T4uWorkitems.findAll", query = "SELECT t FROM T4uWorkitems t"), 
               @NamedQuery(name = "T4uWorkitems.findById", query = "SELECT t FROM T4uWorkitems t WHERE t.id = :id"),
               @NamedQuery(name = "T4uWorkitems.findByValid", query = "SELECT t FROM T4uWorkitems t WHERE t.valid = :valid"),
               @NamedQuery(name = "T4uWorkitems.findByWEnd", query = "SELECT t FROM T4uWorkitems t WHERE t.wEnd = :wEnd"),
               @NamedQuery(name = "T4uWorkitems.findByWBegin", query = "SELECT t FROM T4uWorkitems t WHERE t.wBegin = :wBegin")})
public class T4uWorkitems implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Lob
    @Column(name = "wComment")
    private String wComment;
    @Basic(optional = false)
    @Column(name = "valid")
    private boolean valid;
    @Basic(optional = false)
    @Column(name = "wEnd")
    private int wEnd;
    @Basic(optional = false)
    @Column(name = "wBegin")
    private int wBegin;
    @JoinColumn(name = "tododata_id", referencedColumnName = "id")
    @ManyToOne
    private T4uTodosdata tododataId;
    @JoinColumn(name = "dayinfo_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private T4uDayinfos dayinfoId;
    @JoinColumn(name = "todo_id", referencedColumnName = "id")
    @ManyToOne
    private T4uTodos todoId;
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private T4uTasks taskId;
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private T4uProjects projectId;

    public T4uWorkitems() {
    }

    public T4uWorkitems(String id) {
        this.id = id;
    }

    public T4uWorkitems(String id, String wComment, boolean valid, int wEnd, int wBegin) {
        this.id = id;
        this.wComment = wComment;
        this.valid = valid;
        this.wEnd = wEnd;
        this.wBegin = wBegin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWComment() {
        return wComment;
    }

    public void setWComment(String wComment) {
        this.wComment = wComment;
    }

    public boolean getValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public int getWEnd() {
        return wEnd;
    }

    public void setWEnd(int wEnd) {
        this.wEnd = wEnd;
    }

    public int getWBegin() {
        return wBegin;
    }

    public void setWBegin(int wBegin) {
        this.wBegin = wBegin;
    }

    public T4uTodosdata getTododataId() {
        return tododataId;
    }

    public void setTododataId(T4uTodosdata tododataId) {
        this.tododataId = tododataId;
    }

    public T4uDayinfos getDayinfoId() {
        return dayinfoId;
    }

    public void setDayinfoId(T4uDayinfos dayinfoId) {
        this.dayinfoId = dayinfoId;
    }

    public T4uTodos getTodoId() {
        return todoId;
    }

    public void setTodoId(T4uTodos todoId) {
        this.todoId = todoId;
    }

    public T4uTasks getTaskId() {
        return taskId;
    }

    public void setTaskId(T4uTasks taskId) {
        this.taskId = taskId;
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
        if (!(object instanceof T4uWorkitems)) {
            return false;
        }
        T4uWorkitems other = (T4uWorkitems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.notima.time4u.entities.T4uWorkitems[id=" + id + "]";
    }

}
