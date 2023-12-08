/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.notima.time4u.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author daniel
 */
@Embeddable
public class T4uProjectsPropertiesPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "entityId")
    private String entityId;

    public T4uProjectsPropertiesPK() {}

    public T4uProjectsPropertiesPK(String name, String entityId) {
        this.name = name;
        this.entityId = entityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        hash += (entityId != null ? entityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof T4uProjectsPropertiesPK)) {
            return false;
        }
        T4uProjectsPropertiesPK other = (T4uProjectsPropertiesPK) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        if ((this.entityId == null && other.entityId != null) || (this.entityId != null && !this.entityId.equals(other.entityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.notima.time4u.entities.T4uProjectsPropertiesPK[name=" + name + ", entityId=" + entityId + "]";
    }

}
