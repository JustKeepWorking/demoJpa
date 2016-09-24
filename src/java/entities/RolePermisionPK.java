/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Hai Nguyen
 */
@Embeddable
public class RolePermisionPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "ROLEID")
    private BigInteger roleid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MENUID")
    private BigInteger menuid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "METHODID")
    private BigInteger methodid;

    public RolePermisionPK() {
    }

    public RolePermisionPK(BigInteger roleid, BigInteger menuid, BigInteger methodid) {
        this.roleid = roleid;
        this.menuid = menuid;
        this.methodid = methodid;
    }

    public BigInteger getRoleid() {
        return roleid;
    }

    public void setRoleid(BigInteger roleid) {
        this.roleid = roleid;
    }

    public BigInteger getMenuid() {
        return menuid;
    }

    public void setMenuid(BigInteger menuid) {
        this.menuid = menuid;
    }

    public BigInteger getMethodid() {
        return methodid;
    }

    public void setMethodid(BigInteger methodid) {
        this.methodid = methodid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleid != null ? roleid.hashCode() : 0);
        hash += (menuid != null ? menuid.hashCode() : 0);
        hash += (methodid != null ? methodid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolePermisionPK)) {
            return false;
        }
        RolePermisionPK other = (RolePermisionPK) object;
        if ((this.roleid == null && other.roleid != null) || (this.roleid != null && !this.roleid.equals(other.roleid))) {
            return false;
        }
        if ((this.menuid == null && other.menuid != null) || (this.menuid != null && !this.menuid.equals(other.menuid))) {
            return false;
        }
        if ((this.methodid == null && other.methodid != null) || (this.methodid != null && !this.methodid.equals(other.methodid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RolePermisionPK[ roleid=" + roleid + ", menuid=" + menuid + ", methodid=" + methodid + " ]";
    }
    
}
