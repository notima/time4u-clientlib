/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.notima.time4u.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "PROJECT_CUSTOMER_MAP")
@NamedQueries({@NamedQuery(name = "ProjectCustomerMap.findAll", query = "SELECT p FROM ProjectCustomerMap p"), 
               @NamedQuery(name = "ProjectCustomerMap.findByProjectId", query = "SELECT p FROM ProjectCustomerMap p WHERE p.projectCustomerMapPK.projectId = :projectId"),
               @NamedQuery(name = "ProjectCustomerMap.findByBPartnerId", query = "SELECT p FROM ProjectCustomerMap p WHERE p.projectCustomerMapPK.bPartnerId = :bPartnerId"),
               @NamedQuery(name = "ProjectCustomerMap.findByBpartnerName", query = "SELECT p FROM ProjectCustomerMap p WHERE p.bpartnerName = :bpartnerName"),
               @NamedQuery(name = "ProjectCustomerMap.findByCProductId", query = "SELECT p FROM ProjectCustomerMap p WHERE p.cProductId = :cProductId"),
               @NamedQuery(name = "ProjectCustomerMap.findByProductName", query = "SELECT p FROM ProjectCustomerMap p WHERE p.productName = :productName")})
public class ProjectCustomerMap implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ProjectCustomerMapPK projectCustomerMapPK;
    @Column(name = "bpartner_name")
    private String bpartnerName;
    @Column(name = "c_product_id")
    private Integer cProductId;
    @Column(name = "product_name")
    private String productName;
    @JoinColumn(name = "project_id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private T4uProjects t4uProjects;

    public ProjectCustomerMap() {
    }

    public ProjectCustomerMap(ProjectCustomerMapPK projectCustomerMapPK) {
        this.projectCustomerMapPK = projectCustomerMapPK;
    }

    public ProjectCustomerMap(String projectId, int bPartnerId) {
        this.projectCustomerMapPK = new ProjectCustomerMapPK(projectId, bPartnerId);
    }

    public ProjectCustomerMapPK getProjectCustomerMapPK() {
        return projectCustomerMapPK;
    }

    public void setProjectCustomerMapPK(ProjectCustomerMapPK projectCustomerMapPK) {
        this.projectCustomerMapPK = projectCustomerMapPK;
    }

    public String getBpartnerName() {
        return bpartnerName;
    }

    public void setBpartnerName(String bpartnerName) {
        this.bpartnerName = bpartnerName;
    }

    public Integer getCProductId() {
        return cProductId;
    }

    public void setCProductId(Integer cProductId) {
        this.cProductId = cProductId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
        hash += (projectCustomerMapPK != null ? projectCustomerMapPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectCustomerMap)) {
            return false;
        }
        ProjectCustomerMap other = (ProjectCustomerMap) object;
        if ((this.projectCustomerMapPK == null && other.projectCustomerMapPK != null) || (this.projectCustomerMapPK != null && !this.projectCustomerMapPK.equals(other.projectCustomerMapPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.notima.time4u.entities.ProjectCustomerMap[projectCustomerMapPK=" + projectCustomerMapPK + "]";
    }

}
