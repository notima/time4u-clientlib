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
 * @author Daniel Tamm
 */
@Entity
@Table(name = "BILLS")
@NamedQueries({
    @NamedQuery(name = "Bills.findAll", query = "SELECT b FROM Bills b"),
    @NamedQuery(name = "Bills.findByBillId", query = "SELECT b FROM Bills b WHERE b.billId = :billId"),
    @NamedQuery(name = "Bills.findByInvoiceNo", query = "SELECT b FROM Bills b WHERE b.invoiceNo = :invoiceNo"),
    @NamedQuery(name = "Bills.findByBillFromDate", query = "SELECT b FROM Bills b WHERE b.billFromDate = :billFromDate"),
    @NamedQuery(name = "Bills.findByBillUntilDate", query = "SELECT b FROM Bills b WHERE b.billUntilDate = :billUntilDate"),
    @NamedQuery(name = "Bills.findByBPartnerId", query = "SELECT b FROM Bills b WHERE b.bPartnerId = :bPartnerId")
})
public class Bills implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "bill_id", nullable = false)
    private Integer billId;
    @Column(name = "invoiceNo", length = 100)
    private String invoiceNo;
    @Column(name = "billFromDate")
    @Temporal(TemporalType.DATE)
    private Date billFromDate;
    @Basic(optional = false)
    @Column(name = "billUntilDate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date billUntilDate;
    @Column(name = "b_partner_id")
    private Integer bPartnerId;

    public Bills() {
    }

    public Bills(Integer billId) {
        this.billId = billId;
    }

    public Bills(Integer billId, Date billUntilDate) {
        this.billId = billId;
        this.billUntilDate = billUntilDate;
    }

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Date getBillFromDate() {
        return billFromDate;
    }

    public void setBillFromDate(Date billFromDate) {
        this.billFromDate = billFromDate;
    }

    public Date getBillUntilDate() {
        return billUntilDate;
    }

    public void setBillUntilDate(Date billUntilDate) {
        this.billUntilDate = billUntilDate;
    }

    public Integer getBPartnerId() {
        return bPartnerId;
    }

    public void setBPartnerId(Integer bPartnerId) {
        this.bPartnerId = bPartnerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (billId != null ? billId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bills)) {
            return false;
        }
        Bills other = (Bills) object;
        if ((this.billId == null && other.billId != null) || (this.billId != null && !this.billId.equals(other.billId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.notima.time4u.entities.Bills[billId=" + billId + "]";
    }

}
