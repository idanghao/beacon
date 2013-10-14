package com.fusionspy.beacon.site.wls.entity;
// Generated 2013-10-14 0:49:30 by One Data Tools 1.0.0


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * WlsServer.
* 
 */
@Entity
@Table(name="ge_monitor_wls_server")
public class WlsServer  implements java.io.Serializable {

    /**
<<<<<<< HEAD
<<<<<<< HEAD
     * weblogic版本
        */
    private String version;
    /**
     * Server名称
        */
    private String serverName;
    /**
     * 记录时间
        */
    private Date recTime;
    /**
     * IP地址
        */
    private String listenAddress;
    /**
     * 端口
        */
    private String listenPort;
    /**
     * 轮询时间
        */
    private Integer interval;
    /**
     * 用户名
        */
    private String userName;
    /**
     * 密码
=======
    * PK，自动增长.
    */
    private Integer id;
    /**
=======
     * weblogic版本
>>>>>>> f9e9463... wls
        */
    private String version;
    /**
     * Server名称
        */
    private String serverName;
    /**
     * 记录时间
        */
    private Date recTime;
    /**
     * IP地址
        */
    private String listenAddress;
    /**
     * 端口
        */
    private String listenPort;
    /**
     * 轮询时间
        */
    private Integer interval;
    /**
     * 用户名
        */
    private String userName;
    /**
<<<<<<< HEAD
>>>>>>> 9906aa6... WlsResource add prop mem_free
=======
     * 密码
>>>>>>> f9e9463... wls
        */
    private String password;
    /**
        */
    private Integer isSsl;

<<<<<<< HEAD
<<<<<<< HEAD
    private Integer status;

    public WlsServer() {
    }

    @Column(name="version", length=20)
=======
    public WlsServer() {
    }

   
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true)
    public Integer getId() {
    return this.id;
    }
=======
    private Integer status;
>>>>>>> f9e9463... wls

    public WlsServer() {
    }

<<<<<<< HEAD
    @Column(name="version", length=10)
>>>>>>> 9906aa6... WlsResource add prop mem_free
=======
    @Column(name="version", length=20)
>>>>>>> f9e9463... wls
    public String getVersion() {
    return this.version;
    }

    public void setVersion(String version) {
    this.version = version;
    }
<<<<<<< HEAD
<<<<<<< HEAD

    @Id
    @Column(name="server_name", unique=true)
=======
    
    @Column(name="server_name")
>>>>>>> 9906aa6... WlsResource add prop mem_free
=======

    @Id
    @Column(name="server_name", unique=true)
>>>>>>> f9e9463... wls
    public String getServerName() {
    return this.serverName;
    }

    public void setServerName(String serverName) {
    this.serverName = serverName;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="rec_time", length=19)
    public Date getRecTime() {
    return this.recTime;
    }

    public void setRecTime(Date recTime) {
    this.recTime = recTime;
    }
    
    @Column(name="listen_address")
    public String getListenAddress() {
    return this.listenAddress;
    }

    public void setListenAddress(String listenAddress) {
    this.listenAddress = listenAddress;
    }
    
    @Column(name="listen_port", length=10)
    public String getListenPort() {
    return this.listenPort;
    }

    public void setListenPort(String listenPort) {
    this.listenPort = listenPort;
    }
    
<<<<<<< HEAD
<<<<<<< HEAD
    @Column(name="interval_")
=======
    @Column(name="interval")
>>>>>>> 9906aa6... WlsResource add prop mem_free
=======
    @Column(name="interval_")
>>>>>>> f9e9463... wls
    public Integer getInterval() {
    return this.interval;
    }

    public void setInterval(Integer interval) {
    this.interval = interval;
    }
    
    @Column(name="user_name")
    public String getUserName() {
    return this.userName;
    }

    public void setUserName(String userName) {
    this.userName = userName;
    }
    
    @Column(name="password")
    public String getPassword() {
    return this.password;
    }

    public void setPassword(String password) {
    this.password = password;
    }
    
    @Column(name="is_SSL")
    public Integer getIsSsl() {
    return this.isSsl;
    }

    public void setIsSsl(Integer isSsl) {
    this.isSsl = isSsl;
    }

<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> f9e9463... wls
    @Column(name="status")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
<<<<<<< HEAD

    @Override
=======

	@Override
>>>>>>> 9906aa6... WlsResource add prop mem_free
=======

    @Override
>>>>>>> f9e9463... wls
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}


