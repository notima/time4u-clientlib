/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.notima.time4u.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "T4U_PROJECTS_PROPERTIES")
@NamedQueries({@NamedQuery(name = "T4uProjectsProperties.findAll", query = "SELECT t FROM T4uProjectsProperties t"), @NamedQuery(name = "T4uProjectsProperties.findByName", query = "SELECT t FROM T4uProjectsProperties t WHERE t.t4uProjectsPropertiesPK.name = :name"), @NamedQuery(name = "T4uProjectsProperties.findByEntityId", query = "SELECT t FROM T4uProjectsProperties t WHERE t.t4uProjectsPropertiesPK.entityId = :entityId"), @NamedQuery(name = "T4uProjectsProperties.findByMetaType", query = "SELECT t FROM T4uProjectsProperties t WHERE t.metaType = :metaType"), @NamedQuery(name = "T4uProjectsProperties.findByBoolValue", query = "SELECT t FROM T4uProjectsProperties t WHERE t.boolValue = :boolValue"), @NamedQuery(name = "T4uProjectsProperties.findByDateValue", query = "SELECT t FROM T4uProjectsProperties t WHERE t.dateValue = :dateValue"), @NamedQuery(name = "T4uProjectsProperties.findByIntValue", query = "SELECT t FROM T4uProjectsProperties t WHERE t.intValue = :intValue")})
public class T4uProjectsProperties implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected T4uProjectsPropertiesPK t4uProjectsPropertiesPK;
    @Basic(optional = false)
    @Column(name = "metaType")
    private int metaType;
    @Lob
    @Column(name = "strValue")
    private String strValue;
    @Column(name = "boolValue")
    private Boolean boolValue;
    @Column(name = "dateValue")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateValue;
    @Column(name = "intValue")
    private Integer intValue;
    @JoinColumn(name = "entityId", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private T4uProjects t4uProjects;

    public T4uProjectsProperties() {
    }

    public T4uProjectsProperties(T4uProjectsPropertiesPK t4uProjectsPropertiesPK) {
        this.t4uProjectsPropertiesPK = t4uProjectsPropertiesPK;
    }

    public T4uProjectsProperties(T4uProjectsPropertiesPK t4uProjectsPropertiesPK, int metaType) {
        this.t4uProjectsPropertiesPK = t4uProjectsPropertiesPK;
        this.metaType = metaType;
    }

    public T4uProjectsProperties(String name, String entityId) {
        this.t4uProjectsPropertiesPK = new T4uProjectsPropertiesPK(name, entityId);
    }

    public T4uProjectsPropertiesPK getT4uProjectsPropertiesPK() {
        return t4uProjectsPropertiesPK;
    }

    public void setT4uProjectsPropertiesPK(T4uProjectsPropertiesPK t4uProjectsPropertiesPK) {
        this.t4uProjectsPropertiesPK = t4uProjectsPropertiesPK;
    }

    public int getMetaType() {
        return metaType;
    }

    public void setMetaType(int metaType) {
        this.metaType = metaType;
    }

    public String getStrValue() {
        return strValue;
    }

    public void setStrValue(String strValue) {
        this.strValue = strValue;
    }

    public Boolean getBoolValue() {
        return boolValue;
    }

    public void setBoolValue(Boolean boolValue) {
        this.boolValue = boolValue;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public T4uProjects getT4uProjects() {
        return t4uProjects;
    }

    public void setT4uProjects(T4uProjects t4uProjects) {
        this.t4uProjects = t4uProjects;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (t4uProjectsPropertiesPK != null ? t4uProjectsPropertiesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof T4uProjectsProperties)) {
            return false;
        }
        T4uProjectsProperties other = (T4uProjectsProperties) object;
        if ((this.t4uProjectsPropertiesPK == null && other.t4uProjectsPropertiesPK != null) || (this.t4uProjectsPropertiesPK != null && !this.t4uProjectsPropertiesPK.equals(other.t4uProjectsPropertiesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.notima.time4u.entities.T4uProjectsProperties[t4uProjectsPropertiesPK=" + t4uProjectsPropertiesPK + "]";
    }

}
