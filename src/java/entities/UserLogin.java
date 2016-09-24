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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Hai Nguyen
 */
@Entity
@Table(name = "USERLOGIN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserLogin.findAll", query = "SELECT u FROM UserLogin u"),
    @NamedQuery(name = "UserLogin.findByUserid", query = "SELECT u FROM UserLogin u WHERE u.userid = :userid"),
    @NamedQuery(name = "UserLogin.findByLoginprovider", query = "SELECT u FROM UserLogin u WHERE u.loginprovider = :loginprovider"),
    @NamedQuery(name = "UserLogin.findByProviderkey", query = "SELECT u FROM UserLogin u WHERE u.providerkey = :providerkey"),
    @NamedQuery(name = "UserLogin.findByProviderdisplayname", query = "SELECT u FROM UserLogin u WHERE u.providerdisplayname = :providerdisplayname")})
public class UserLogin implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "USERID")
    private BigDecimal userid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "LOGINPROVIDER")
    private String loginprovider;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PROVIDERKEY")
    private String providerkey;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PROVIDERDISPLAYNAME")
    private String providerdisplayname;
    @JoinColumn(name = "USERID", referencedColumnName = "ID", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private UserAccount userAccount;

    public UserLogin() {
    }

    public UserLogin(BigDecimal userid) {
        this.userid = userid;
    }

    public UserLogin(BigDecimal userid, String loginprovider, String providerkey, String providerdisplayname) {
        this.userid = userid;
        this.loginprovider = loginprovider;
        this.providerkey = providerkey;
        this.providerdisplayname = providerdisplayname;
    }

    public BigDecimal getUserid() {
        return userid;
    }

    public void setUserid(BigDecimal userid) {
        this.userid = userid;
    }

    public String getLoginprovider() {
        return loginprovider;
    }

    public void setLoginprovider(String loginprovider) {
        this.loginprovider = loginprovider;
    }

    public String getProviderkey() {
        return providerkey;
    }

    public void setProviderkey(String providerkey) {
        this.providerkey = providerkey;
    }

    public String getProviderdisplayname() {
        return providerdisplayname;
    }

    public void setProviderdisplayname(String providerdisplayname) {
        this.providerdisplayname = providerdisplayname;
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
        hash += (userid != null ? userid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserLogin)) {
            return false;
        }
        UserLogin other = (UserLogin) object;
        if ((this.userid == null && other.userid != null) || (this.userid != null && !this.userid.equals(other.userid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UserLogin[ userid=" + userid + " ]";
    }
    
}
