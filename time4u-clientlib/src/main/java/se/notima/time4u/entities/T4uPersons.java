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
import javax.persistence.ManyToMany;
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
@Table(name = "T4U_PERSONS")
@NamedQueries({@NamedQuery(name = "T4uPersons.findAll", query = "SELECT t FROM T4uPersons t"), 
			   @NamedQuery(name = "T4uPersons.findAllActive", query = "SELECT t FROM T4uPersons t WHERE t.active = :active and t.deleted = :deleted and t.email is not null"),
			   @NamedQuery(name = "T4uPersons.findById", query = "SELECT t FROM T4uPersons t WHERE t.id = :id"), 
			   @NamedQuery(name = "T4uPersons.findByRevision", query = "SELECT t FROM T4uPersons t WHERE t.revision = :revision"), 
			   @NamedQuery(name = "T4uPersons.findByDeleted", query = "SELECT t FROM T4uPersons t WHERE t.deleted = :deleted"), 
			   @NamedQuery(name = "T4uPersons.findByGivenName", query = "SELECT t FROM T4uPersons t WHERE t.givenName = :givenName"), 
			   @NamedQuery(name = "T4uPersons.findBySurname", query = "SELECT t FROM T4uPersons t WHERE t.surname = :surname"), 
			   @NamedQuery(name = "T4uPersons.findByEmail", query = "SELECT t FROM T4uPersons t WHERE t.email = :email"), 
			   @NamedQuery(name = "T4uPersons.findByLastModifiedByClient", query = "SELECT t FROM T4uPersons t WHERE t.lastModifiedByClient = :lastModifiedByClient"), 
			   @NamedQuery(name = "T4uPersons.findByLastSynchronize", query = "SELECT t FROM T4uPersons t WHERE t.lastSynchronize = :lastSynchronize")})
public class T4uPersons implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "revision")
    private long revision;
    @Basic(optional = false)
    @Column(name = "deleted")
    private boolean deleted;
    @Column(name = "givenName")
    private String givenName;
    @Basic(optional = false)
    @Column(name = "surname")
    private String surname;
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "lastModifiedByClient")
    private long lastModifiedByClient;
    @Column(name = "lastSynchronize")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSynchronize;
    @Column(name = "active")
    private boolean active;
    @ManyToMany(mappedBy = "t4uPersonsCollection")
    private Collection<T4uTodosbase> t4uTodosbaseCollection;
    @ManyToMany(mappedBy = "t4uPersonsCollection")
    private Collection<T4uTodos> t4uTodosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personId")
    private Collection<T4uDayinfos> t4uDayinfosCollection;
    @OneToMany(mappedBy = "reporterId")
    private Collection<T4uTodos> t4uTodosCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personId")
    private Collection<T4uUseraccounts> t4uUseraccountsCollection;
    @OneToMany(mappedBy = "reporterId")
    private Collection<T4uTodosbase> t4uTodosbaseCollection1;

    public T4uPersons() {
    }

    public T4uPersons(String id) {
        this.id = id;
    }

    public T4uPersons(String id, long revision, boolean deleted, String surname, long lastModifiedByClient) {
        this.id = id;
        this.revision = revision;
        this.deleted = deleted;
        this.surname = surname;
        this.lastModifiedByClient = lastModifiedByClient;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getRevision() {
        return revision;
    }

    public void setRevision(long revision) {
        this.revision = revision;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getLastModifiedByClient() {
        return lastModifiedByClient;
    }

    public void setLastModifiedByClient(long lastModifiedByClient) {
        this.lastModifiedByClient = lastModifiedByClient;
    }

    public Date getLastSynchronize() {
        return lastSynchronize;
    }

    public void setLastSynchronize(Date lastSynchronize) {
        this.lastSynchronize = lastSynchronize;
    }
    
    public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Collection<T4uTodosbase> getT4uTodosbaseCollection() {
        return t4uTodosbaseCollection;
    }

    public void setT4uTodosbaseCollection(Collection<T4uTodosbase> t4uTodosbaseCollection) {
        this.t4uTodosbaseCollection = t4uTodosbaseCollection;
    }

    public Collection<T4uTodos> getT4uTodosCollection() {
        return t4uTodosCollection;
    }

    public void setT4uTodosCollection(Collection<T4uTodos> t4uTodosCollection) {
        this.t4uTodosCollection = t4uTodosCollection;
    }

    public Collection<T4uDayinfos> getT4uDayinfosCollection() {
        return t4uDayinfosCollection;
    }

    public void setT4uDayinfosCollection(Collection<T4uDayinfos> t4uDayinfosCollection) {
        this.t4uDayinfosCollection = t4uDayinfosCollection;
    }

    public Collection<T4uTodos> getT4uTodosCollection1() {
        return t4uTodosCollection1;
    }

    public void setT4uTodosCollection1(Collection<T4uTodos> t4uTodosCollection1) {
        this.t4uTodosCollection1 = t4uTodosCollection1;
    }

    public Collection<T4uUseraccounts> getT4uUseraccountsCollection() {
        return t4uUseraccountsCollection;
    }

    public void setT4uUseraccountsCollection(Collection<T4uUseraccounts> t4uUseraccountsCollection) {
        this.t4uUseraccountsCollection = t4uUseraccountsCollection;
    }

    public Collection<T4uTodosbase> getT4uTodosbaseCollection1() {
        return t4uTodosbaseCollection1;
    }

    public void setT4uTodosbaseCollection1(Collection<T4uTodosbase> t4uTodosbaseCollection1) {
        this.t4uTodosbaseCollection1 = t4uTodosbaseCollection1;
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
        if (!(object instanceof T4uPersons)) {
            return false;
        }
        T4uPersons other = (T4uPersons) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return(getGivenName() + " " + getSurname());
    }

}
