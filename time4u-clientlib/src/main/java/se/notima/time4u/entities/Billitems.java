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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Daniel Tamm
 */
@Entity
@Table(name = "BILLITEMS")
@NamedQueries({@NamedQuery(name = "Billitems.findAll", query = "SELECT b FROM Billitems b"), @NamedQuery(name = "Billitems.findByBillId", query = "SELECT b FROM Billitems b WHERE b.billId = :billId"), @NamedQuery(name = "Billitems.findByWorkitemId", query = "SELECT b FROM Billitems b WHERE b.workitemId = :workitemId"), @NamedQuery(name = "Billitems.findByBillingRunId", query = "SELECT b FROM Billitems b WHERE b.billingRunId = :billingRunId"), @NamedQuery(name = "Billitems.findByMultiplier", query = "SELECT b FROM Billitems b WHERE b.multiplier = :multiplier")})
public class Billitems implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "bill_id", nullable = false)
    private int billId;
    @Id
    @Basic(optional = false)
    @Column(name = "workitem_id", nullable = false, length = 36)
    private String workitemId;
    @Basic(optional = false)
    @Column(name = "billing_run_id", nullable = false)
    private int billingRunId;
    @Column(name = "multiplier", precision = 15, scale = 3)
    private Double multiplier;

    public Billitems() {
    }

    public Billitems(String workitemId) {
        this.workitemId = workitemId;
    }

    public Billitems(String workitemId, int billId, int billingRunId) {
        this.workitemId = workitemId;
        this.billId = billId;
        this.billingRunId = billingRunId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getWorkitemId() {
        return workitemId;
    }

    public void setWorkitemId(String workitemId) {
        this.workitemId = workitemId;
    }

    public int getBillingRunId() {
        return billingRunId;
    }

    public void setBillingRunId(int billingRunId) {
        this.billingRunId = billingRunId;
    }

    public Double getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(Double multiplier) {
        this.multiplier = multiplier;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (workitemId != null ? workitemId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Billitems)) {
            return false;
        }
        Billitems other = (Billitems) object;
        if ((this.workitemId == null && other.workitemId != null) || (this.workitemId != null && !this.workitemId.equals(other.workitemId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.notima.time4u.entities.Billitems[workitemId=" + workitemId + "]";
    }

}
