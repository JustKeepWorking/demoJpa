/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hai Nguyen
 */
@Entity
@Table(name = "ROLECLAIM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoleClaim.findAll", query = "SELECT r FROM RoleClaim r"),
    @NamedQuery(name = "RoleClaim.findById", query = "SELECT r FROM RoleClaim r WHERE r.id = :id"),
    @NamedQuery(name = "RoleClaim.findByType", query = "SELECT r FROM RoleClaim r WHERE r.type = :type"),
    @NamedQuery(name = "RoleClaim.findByValue", query = "SELECT r FROM RoleClaim r WHERE r.value = :value")})
public class RoleClaim implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private BigDecimal id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "TYPE")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "VALUE")
    private String value;
    @JoinColumn(name = "ROLEID", referencedColumnName = "ID")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Role roleid;

    public RoleClaim() {
    }

    public RoleClaim(BigDecimal id) {
        this.id = id;
    }

    public RoleClaim(BigDecimal id, String type, String value) {
        this.id = id;
        this.type = type;
        this.value = value;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Role getRoleid() {
        return roleid;
    }

    public void setRoleid(Role roleid) {
        this.roleid = roleid;
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
        if (!(object instanceof RoleClaim)) {
            return false;
        }
        RoleClaim other = (RoleClaim) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RoleClaim[ id=" + id + " ]";
    }
    
}
