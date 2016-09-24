/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "ROLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r"),
    @NamedQuery(name = "Role.findById", query = "SELECT r FROM Role r WHERE r.id = :id"),
    @NamedQuery(name = "Role.findByName", query = "SELECT r FROM Role r WHERE r.name = :name"),
    @NamedQuery(name = "Role.findByNormalizedname", query = "SELECT r FROM Role r WHERE r.normalizedname = :normalizedname"),
    @NamedQuery(name = "Role.findByConcurencystamp", query = "SELECT r FROM Role r WHERE r.concurencystamp = :concurencystamp")})
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NAME")
    private String name;
    @Size(max = 255)
    @Column(name = "NORMALIZEDNAME")
    private String normalizedname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "CONCURENCYSTAMP")
    private String concurencystamp;
    @ManyToMany(mappedBy = "roleList", fetch = FetchType.LAZY)
    private List<UserAccount> userAccountList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleid", fetch = FetchType.LAZY)
    private List<RoleClaim> roleClaimList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role", fetch = FetchType.LAZY)
    private List<RolePermision> rolePermisionList;

    public Role() {
    }

    public Role(BigDecimal id) {
        this.id = id;
    }

    public Role(BigDecimal id, String name, String concurencystamp) {
        this.id = id;
        this.name = name;
        this.concurencystamp = concurencystamp;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNormalizedname() {
        return normalizedname;
    }

    public void setNormalizedname(String normalizedname) {
        this.normalizedname = normalizedname;
    }

    public String getConcurencystamp() {
        return concurencystamp;
    }

    public void setConcurencystamp(String concurencystamp) {
        this.concurencystamp = concurencystamp;
    }

    @XmlTransient
    public List<UserAccount> getUserAccountList() {
        return userAccountList;
    }

    public void setUserAccountList(List<UserAccount> userAccountList) {
        this.userAccountList = userAccountList;
    }

    @XmlTransient
    public List<RoleClaim> getRoleClaimList() {
        return roleClaimList;
    }

    public void setRoleClaimList(List<RoleClaim> roleClaimList) {
        this.roleClaimList = roleClaimList;
    }

    @XmlTransient
    public List<RolePermision> getRolePermisionList() {
        return rolePermisionList;
    }

    public void setRolePermisionList(List<RolePermision> rolePermisionList) {
        this.rolePermisionList = rolePermisionList;
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
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", name=" + name + ", normalizedname=" + normalizedname + ", concurencystamp=" + concurencystamp + '}';
    }

    
    
}
