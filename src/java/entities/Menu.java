/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hai Nguyen
 */
@Entity
@Table(name = "MENU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m"),
    @NamedQuery(name = "Menu.findById", query = "SELECT m FROM Menu m WHERE m.id = :id"),
    @NamedQuery(name = "Menu.findByPosition", query = "SELECT m FROM Menu m WHERE m.position = :position"),
    @NamedQuery(name = "Menu.findByName", query = "SELECT m FROM Menu m WHERE m.name = :name"),
    @NamedQuery(name = "Menu.findByFormname", query = "SELECT m FROM Menu m WHERE m.formname = :formname")})
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POSITION")
    private BigInteger position;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "FORMNAME")
    private String formname;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menu", fetch = FetchType.LAZY)
    private List<RolePermision> rolePermisionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menuid", fetch = FetchType.LAZY)
    private List<Method> methodList;
    @OneToMany(mappedBy = "filiationid", fetch = FetchType.LAZY)
    private List<Menu> menuList;
    @JoinColumn(name = "FILIATIONID", referencedColumnName = "ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Menu filiationid;

    public Menu() {
    }

    public Menu(BigDecimal id) {
        this.id = id;
    }

    public Menu(BigDecimal id, BigInteger position, String name, String formname) {
        this.id = id;
        this.position = position;
        this.name = name;
        this.formname = formname;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigInteger getPosition() {
        return position;
    }

    public void setPosition(BigInteger position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormname() {
        return formname;
    }

    public void setFormname(String formname) {
        this.formname = formname;
    }

    @XmlTransient
    public List<RolePermision> getRolePermisionList() {
        return rolePermisionList;
    }

    public void setRolePermisionList(List<RolePermision> rolePermisionList) {
        this.rolePermisionList = rolePermisionList;
    }

    @XmlTransient
    public List<Method> getMethodList() {
        return methodList;
    }

    public void setMethodList(List<Method> methodList) {
        this.methodList = methodList;
    }

    @XmlTransient
    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public Menu getFiliationid() {
        return filiationid;
    }

    public void setFiliationid(Menu filiationid) {
        this.filiationid = filiationid;
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
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Menu[ id=" + id + " ]";
    }
    
}
