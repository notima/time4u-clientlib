/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package se.notima.time4u.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "T4U_DAYINFOS")
@NamedQueries({@NamedQuery(name = "T4uDayinfos.findAll", query = "SELECT t FROM T4uDayinfos t"),
               @NamedQuery(name = "T4uDayinfos.findById", query = "SELECT t FROM T4uDayinfos t WHERE t.id = :id"),
               @NamedQuery(name = "T4uDayinfos.findByDaydate", query = "SELECT t FROM T4uDayinfos t WHERE t.daydate = :daydate"),
               @NamedQuery(name = "T4uDayinfos.findInPeriod", query = "SELECT distinct t from T4uDayinfos t WHERE t.daydate >= :from and t.daydate <= :to order by t.daydate"),
               @NamedQuery(name = "T4uDayinfos.findByRevision", query = "SELECT t FROM T4uDayinfos t WHERE t.revision = :revision"),
               @NamedQuery(name = "T4uDayinfos.findByLastModifiedByClient", query = "SELECT t FROM T4uDayinfos t WHERE t.lastModifiedByClient = :lastModifiedByClient"),
               @NamedQuery(name = "T4uDayinfos.findByRegularTime", query = "SELECT t FROM T4uDayinfos t WHERE t.regularTime = :regularTime"),
               @NamedQuery(name = "T4uDayinfos.findByHasWorkItems", query = "SELECT t FROM T4uDayinfos t WHERE t.hasWorkItems = :hasWorkItems"),
               @NamedQuery(name = "T4uDayinfos.findByHasInvalidWorkItems", query = "SELECT t FROM T4uDayinfos t WHERE t.hasInvalidWorkItems = :hasInvalidWorkItems"),
               @NamedQuery(name = "T4uDayinfos.findByHasTags", query = "SELECT t FROM T4uDayinfos t WHERE t.hasTags = :hasTags"),
               @NamedQuery(name = "T4uDayinfos.findBySumDurations", query = "SELECT t FROM T4uDayinfos t WHERE t.sumDurations = :sumDurations")})
public class T4uDayinfos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "daydate")
    @Temporal(TemporalType.DATE)
    private Date daydate;
    @Basic(optional = false)
    @Column(name = "revision")
    private long revision;
    @Basic(optional = false)
    @Column(name = "lastModifiedByClient")
    private long lastModifiedByClient;
    @Basic(optional = false)
    @Column(name = "regularTime")
    private int regularTime;
    @Basic(optional = false)
    @Column(name = "hasWorkItems")
    private boolean hasWorkItems;
    @Basic(optional = false)
    @Column(name = "hasInvalidWorkItems")
    private boolean hasInvalidWorkItems;
    @Column(name = "hasTags")
    private Boolean hasTags;
    @Basic(optional = false)
    @Column(name = "sumDurations")
    private int sumDurations;
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private T4uPersons personId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dayinfoId")
    private Collection<T4uWorkitems> t4uWorkitemsCollection;

    public T4uDayinfos() {
    }

    public T4uDayinfos(String id) {
        this.id = id;
    }

    public T4uDayinfos(String id, Date daydate, long revision, long lastModifiedByClient, int regularTime, boolean hasWorkItems, boolean hasInvalidWorkItems, int sumDurations) {
        this.id = id;
        this.daydate = daydate;
        this.revision = revision;
        this.lastModifiedByClient = lastModifiedByClient;
        this.regularTime = regularTime;
        this.hasWorkItems = hasWorkItems;
        this.hasInvalidWorkItems = hasInvalidWorkItems;
        this.sumDurations = sumDurations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDaydate() {
        return daydate;
    }

    public void setDaydate(Date daydate) {
        this.daydate = daydate;
    }

    public long getRevision() {
        return revision;
    }

    public void setRevision(long revision) {
        this.revision = revision;
    }

    public long getLastModifiedByClient() {
        return lastModifiedByClient;
    }

    public void setLastModifiedByClient(long lastModifiedByClient) {
        this.lastModifiedByClient = lastModifiedByClient;
    }

    public int getRegularTime() {
        return regularTime;
    }

    public void setRegularTime(int regularTime) {
        this.regularTime = regularTime;
    }

    public boolean getHasWorkItems() {
        return hasWorkItems;
    }

    public void setHasWorkItems(boolean hasWorkItems) {
        this.hasWorkItems = hasWorkItems;
    }

    public boolean getHasInvalidWorkItems() {
        return hasInvalidWorkItems;
    }

    public void setHasInvalidWorkItems(boolean hasInvalidWorkItems) {
        this.hasInvalidWorkItems = hasInvalidWorkItems;
    }

    public Boolean getHasTags() {
        return hasTags;
    }

    public void setHasTags(Boolean hasTags) {
        this.hasTags = hasTags;
    }

    public int getSumDurations() {
        return sumDurations;
    }

    public void setSumDurations(int sumDurations) {
        this.sumDurations = sumDurations;
    }

    public T4uPersons getPersonId() {
        return personId;
    }

    public void setPersonId(T4uPersons personId) {
        this.personId = personId;
    }

    public Collection<T4uWorkitems> getT4uWorkitemsCollection() {
        return t4uWorkitemsCollection;
    }

    public void setT4uWorkitemsCollection(Collection<T4uWorkitems> t4uWorkitemsCollection) {
        this.t4uWorkitemsCollection = t4uWorkitemsCollection;
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
        if (!(object instanceof T4uDayinfos)) {
            return false;
        }
        T4uDayinfos other = (T4uDayinfos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.notima.time4u.entities.T4uDayinfos[id=" + id + "]";
    }

}
