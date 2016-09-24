/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Hai Nguyen
 */
@Entity
@Table(name = "USERACCOUNT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserAccount.findAll", query = "SELECT u FROM UserAccount u"),
    @NamedQuery(name = "UserAccount.findById", query = "SELECT u FROM UserAccount u WHERE u.id = :id"),
    @NamedQuery(name = "UserAccount.findByUsername", query = "SELECT u FROM UserAccount u WHERE u.username = :username"),
    @NamedQuery(name = "UserAccount.findByNormalizedusername", query = "SELECT u FROM UserAccount u WHERE u.normalizedusername = :normalizedusername"),
    @NamedQuery(name = "UserAccount.findByEmail", query = "SELECT u FROM UserAccount u WHERE u.email = :email"),
    @NamedQuery(name = "UserAccount.findByEmailconfirmed", query = "SELECT u FROM UserAccount u WHERE u.emailconfirmed = :emailconfirmed"),
    @NamedQuery(name = "UserAccount.findByPasswordhash", query = "SELECT u FROM UserAccount u WHERE u.passwordhash = :passwordhash"),
    @NamedQuery(name = "UserAccount.findBySecuritystamp", query = "SELECT u FROM UserAccount u WHERE u.securitystamp = :securitystamp"),
    @NamedQuery(name = "UserAccount.findByPhonenumber", query = "SELECT u FROM UserAccount u WHERE u.phonenumber = :phonenumber"),
    @NamedQuery(name = "UserAccount.findByPhonenumberconfirmed", query = "SELECT u FROM UserAccount u WHERE u.phonenumberconfirmed = :phonenumberconfirmed"),
    @NamedQuery(name = "UserAccount.findByTwofactorenabled", query = "SELECT u FROM UserAccount u WHERE u.twofactorenabled = :twofactorenabled"),
    @NamedQuery(name = "UserAccount.findByLockoutenddateutc", query = "SELECT u FROM UserAccount u WHERE u.lockoutenddateutc = :lockoutenddateutc"),
    @NamedQuery(name = "UserAccount.findByLockoutenabled", query = "SELECT u FROM UserAccount u WHERE u.lockoutenabled = :lockoutenabled"),
    @NamedQuery(name = "UserAccount.findByAccessfailedcount", query = "SELECT u FROM UserAccount u WHERE u.accessfailedcount = :accessfailedcount"),
    @NamedQuery(name = "UserAccount.findByConcurrencystamp", query = "SELECT u FROM UserAccount u WHERE u.concurrencystamp = :concurrencystamp")})
public class UserAccount implements Serializable {

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
    @Column(name = "USERNAME")
    private String username;
    @Size(max = 255)
    @Column(name = "NORMALIZEDUSERNAME")
    private String normalizedusername;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EMAILCONFIRMED")
    private Character emailconfirmed;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "PASSWORDHASH")
    private String passwordhash;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "SECURITYSTAMP")
    private String securitystamp;
    @Size(max = 20)
    @Column(name = "PHONENUMBER")
    private String phonenumber;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PHONENUMBERCONFIRMED")
    private Character phonenumberconfirmed;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TWOFACTORENABLED")
    private Character twofactorenabled;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LOCKOUTENDDATEUTC")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lockoutenddateutc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "LOCKOUTENABLED")
    private Character lockoutenabled;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACCESSFAILEDCOUNT")
    private BigInteger accessfailedcount;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 36)
    @Column(name = "CONCURRENCYSTAMP")
    private String concurrencystamp;
    @JoinTable(name = "USERROLE", joinColumns = {
        @JoinColumn(name = "USERID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ROLEID", referencedColumnName = "ID")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Role> roleList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userAccount", fetch = FetchType.LAZY)
    private UserToken userToken;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userAccount", fetch = FetchType.LAZY)
    private UserLogin userLogin;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userAccount", fetch = FetchType.LAZY)
    private List<UserClaim> userClaimList;

    public UserAccount() {
    }

    public UserAccount(BigDecimal id) {
        this.id = id;
    }

    public UserAccount(BigDecimal id, String username, String email, Character emailconfirmed, String passwordhash, String securitystamp, Character phonenumberconfirmed, Character twofactorenabled, Date lockoutenddateutc, Character lockoutenabled, BigInteger accessfailedcount, String concurrencystamp) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.emailconfirmed = emailconfirmed;
        this.passwordhash = passwordhash;
        this.securitystamp = securitystamp;
        this.phonenumberconfirmed = phonenumberconfirmed;
        this.twofactorenabled = twofactorenabled;
        this.lockoutenddateutc = lockoutenddateutc;
        this.lockoutenabled = lockoutenabled;
        this.accessfailedcount = accessfailedcount;
        this.concurrencystamp = concurrencystamp;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNormalizedusername() {
        return normalizedusername;
    }

    public void setNormalizedusername(String normalizedusername) {
        this.normalizedusername = normalizedusername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Character getEmailconfirmed() {
        return emailconfirmed;
    }

    public void setEmailconfirmed(Character emailconfirmed) {
        this.emailconfirmed = emailconfirmed;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(String passwordhash) {
        this.passwordhash = passwordhash;
    }

    public String getSecuritystamp() {
        return securitystamp;
    }

    public void setSecuritystamp(String securitystamp) {
        this.securitystamp = securitystamp;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Character getPhonenumberconfirmed() {
        return phonenumberconfirmed;
    }

    public void setPhonenumberconfirmed(Character phonenumberconfirmed) {
        this.phonenumberconfirmed = phonenumberconfirmed;
    }

    public Character getTwofactorenabled() {
        return twofactorenabled;
    }

    public void setTwofactorenabled(Character twofactorenabled) {
        this.twofactorenabled = twofactorenabled;
    }

    public Date getLockoutenddateutc() {
        return lockoutenddateutc;
    }

    public void setLockoutenddateutc(Date lockoutenddateutc) {
        this.lockoutenddateutc = lockoutenddateutc;
    }

    public Character getLockoutenabled() {
        return lockoutenabled;
    }

    public void setLockoutenabled(Character lockoutenabled) {
        this.lockoutenabled = lockoutenabled;
    }

    public BigInteger getAccessfailedcount() {
        return accessfailedcount;
    }

    public void setAccessfailedcount(BigInteger accessfailedcount) {
        this.accessfailedcount = accessfailedcount;
    }

    public String getConcurrencystamp() {
        return concurrencystamp;
    }

    public void setConcurrencystamp(String concurrencystamp) {
        this.concurrencystamp = concurrencystamp;
    }

    @XmlTransient
    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public UserToken getUserToken() {
        return userToken;
    }

    public void setUserToken(UserToken userToken) {
        this.userToken = userToken;
    }

    public UserLogin getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(UserLogin userLogin) {
        this.userLogin = userLogin;
    }

    @XmlTransient
    public List<UserClaim> getUserClaimList() {
        return userClaimList;
    }

    public void setUserClaimList(List<UserClaim> userClaimList) {
        this.userClaimList = userClaimList;
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
        if (!(object instanceof UserAccount)) {
            return false;
        }
        UserAccount other = (UserAccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.UserAccount[ id=" + id + " ]";
    }
    
}
