/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.notima.time4u.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "BILLINGRUN")
@NamedQueries({
    @NamedQuery(name = "BillingRun.findAll", query = "SELECT b FROM BillingRun b"),
    @NamedQuery(name = "BillingRun.findByBillingRunId", query = "SELECT b FROM BillingRun b WHERE b.billingRunId = :billingRunId"),
    @NamedQuery(name = "BillingRun.findByBillDate", query = "SELECT b FROM BillingRun b WHERE b.billDate = :billDate")})
public class BillingRun implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "billing_run_id", nullable = false)
    private Integer billingRunId;
    @Column(name = "bill_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date billDate;

    public BillingRun() {
    }

    public BillingRun(Integer billingRunId) {
        this.billingRunId = billingRunId;
    }

    public Integer getBillingRunId() {
        return billingRunId;
    }

    public void setBillingRunId(Integer billingRunId) {
        this.billingRunId = billingRunId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (billingRunId != null ? billingRunId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BillingRun)) {
            return false;
        }
        BillingRun other = (BillingRun) object;
        if ((this.billingRunId == null && other.billingRunId != null) || (this.billingRunId != null && !this.billingRunId.equals(other.billingRunId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.notima.time4u.entities.BillingRun[billingRunId=" + billingRunId + "]";
    }

}
