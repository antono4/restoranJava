/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "foodcategory", catalog = "restoranjava", schema = "")
@NamedQueries({
    @NamedQuery(name = "Foodcategory.findAll", query = "SELECT f FROM Foodcategory f"),
    @NamedQuery(name = "Foodcategory.findByCategoryID", query = "SELECT f FROM Foodcategory f WHERE f.categoryID = :categoryID"),
    @NamedQuery(name = "Foodcategory.findByCategoryName", query = "SELECT f FROM Foodcategory f WHERE f.categoryName = :categoryName")})
public class Foodcategory implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "categoryID")
    private Integer categoryID;
    @Basic(optional = false)
    @Column(name = "categoryName")
    private String categoryName;

    public Foodcategory() {
    }

    public Foodcategory(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Foodcategory(Integer categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        Integer oldCategoryID = this.categoryID;
        this.categoryID = categoryID;
        changeSupport.firePropertyChange("categoryID", oldCategoryID, categoryID);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        String oldCategoryName = this.categoryName;
        this.categoryName = categoryName;
        changeSupport.firePropertyChange("categoryName", oldCategoryName, categoryName);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (categoryID != null ? categoryID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Foodcategory)) {
            return false;
        }
        Foodcategory other = (Foodcategory) object;
        if ((this.categoryID == null && other.categoryID != null) || (this.categoryID != null && !this.categoryID.equals(other.categoryID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "admin.Foodcategory[ categoryID=" + categoryID + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
