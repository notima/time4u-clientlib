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
public class ProjectCustomerMapPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "project_id")
    private String projectId;
    @Basic(optional = false)
    @Column(name = "b_partner_id")
    private int bPartnerId;

    public ProjectCustomerMapPK() {
    }

    public ProjectCustomerMapPK(String projectId, int bPartnerId) {
        this.projectId = projectId;
        this.bPartnerId = bPartnerId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getBPartnerId() {
        return bPartnerId;
    }

    public void setBPartnerId(int bPartnerId) {
        this.bPartnerId = bPartnerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectId != null ? projectId.hashCode() : 0);
        hash += (int) bPartnerId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectCustomerMapPK)) {
            return false;
        }
        ProjectCustomerMapPK other = (ProjectCustomerMapPK) object;
        if ((this.projectId == null && other.projectId != null) || (this.projectId != null && !this.projectId.equals(other.projectId))) {
            return false;
        }
        if (this.bPartnerId != other.bPartnerId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.notima.time4u.entities.ProjectCustomerMapPK[projectId=" + projectId + ", bPartnerId=" + bPartnerId + "]";
    }

}
