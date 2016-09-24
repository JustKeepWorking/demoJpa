/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hai Nguyen
 */
@Entity
@Table(name = "ROLEPERMISION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolePermision.findAll", query = "SELECT r FROM RolePermision r"),
    @NamedQuery(name = "RolePermision.findByRoleid", query = "SELECT r FROM RolePermision r WHERE r.rolePermisionPK.roleid = :roleid"),
    @NamedQuery(name = "RolePermision.findByMenuid", query = "SELECT r FROM RolePermision r WHERE r.rolePermisionPK.menuid = :menuid"),
    @NamedQuery(name = "RolePermision.findByMethodid", query = "SELECT r FROM RolePermision r WHERE r.rolePermisionPK.methodid = :methodid")})
public class RolePermision implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RolePermisionPK rolePermisionPK;
    @JoinColumn(name = "MENUID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Menu menu;
    @JoinColumn(name = "METHODID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Method method;
    @JoinColumn(name = "ROLEID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Role role;

    public RolePermision() {
    }

    public RolePermision(RolePermisionPK rolePermisionPK) {
        this.rolePermisionPK = rolePermisionPK;
    }

    public RolePermision(BigInteger roleid, BigInteger menuid, BigInteger methodid) {
        this.rolePermisionPK = new RolePermisionPK(roleid, menuid, methodid);
    }

    public RolePermisionPK getRolePermisionPK() {
        return rolePermisionPK;
    }

    public void setRolePermisionPK(RolePermisionPK rolePermisionPK) {
        this.rolePermisionPK = rolePermisionPK;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rolePermisionPK != null ? rolePermisionPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolePermision)) {
            return false;
        }
        RolePermision other = (RolePermision) object;
        if ((this.rolePermisionPK == null && other.rolePermisionPK != null) || (this.rolePermisionPK != null && !this.rolePermisionPK.equals(other.rolePermisionPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RolePermision[ rolePermisionPK=" + rolePermisionPK + " ]";
    }
    
}
