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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "USERCLAIM")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserClaim.findAll", query = "SELECT u FROM UserClaim u"),
    @NamedQuery(name = "UserClaim.findById", query = "SELECT u FROM UserClaim u WHERE u.userClaimPK.id = :id"),
    @NamedQuery(name = "UserClaim.findByType", query = "SELECT u FROM UserClaim u WHERE u.type = :type"),
    @NamedQuery(name = "UserClaim.findByValue", query = "SELECT u FROM UserClaim u WHERE u.value = :value"),
    @NamedQuery(name = "UserClaim.findByUserid", query = "SELECT u FROM UserClaim u WHERE u.userClaimPK.userid = :userid")})
public class UserClaim implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected UserClaimPK userClaimPK;
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
    @JoinColumn(name = "USERID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UserAccount userAccount;

    public UserClaim() {
    }

    public UserClaim(UserClaimPK userClaimPK) {
        this.userClaimPK = userClaimPK;
    }

    public UserClaim(UserClaimPK userClaimPK, String type, String value) {
        this.userClaimPK = userClaimPK;
        this.type = type;
        this.value = value;
    }

    public UserClaim(BigInteger id, BigInteger userid) {
        this.userClaimPK = new UserClaimPK(id, userid);
    }

    public UserClaimPK getUserClaimPK() {
        return userClaimPK;
    }

    public void setUserClaimPK(UserClaimPK userClaimPK) {
        this.userClaimPK = userClaimPK;
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

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userClaimPK != null ? userClaimPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserClaim)) {
            return false;
        }
        UserClaim other = (UserClaim) object;
        if ((this.userClaimPK == null && other.userClaimPK != null) || (this.userClaimPK != null && !this.userClaimPK.equals(other.userClaimPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UserClaim[ userClaimPK=" + userClaimPK + " ]";
    }
    
}
