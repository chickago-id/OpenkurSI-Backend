package backend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

/**
 * Author : supi.core@gmail.com | github.com/sup1core
 */

 @Entity
 @Table(name = "mailbox")

 public class Mailbox {
 
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;

     @Column(name = "subject", nullable = false, updatable = false)
     private String subject;

    //  @ManyToOne(fetch = FetchType.LAZY)
    //  @JoinColumns({
    //     @JoinColumn(name = "sender", insertable = false, updatable = false, referencedColumnName="id_user"),
    //     @JoinColumn(name = "receiver", insertable = false, updatable = false, referencedColumnName="id_user")
    //  })
    
     @ManyToOne
     @JoinColumn(name = "sender", insertable = false, updatable = false, referencedColumnName="id_user")
     private UserDetail userDetail;
     private Long sender;

     @ManyToOne
     @JoinColumn(name = "receiver", insertable = false, updatable = false, referencedColumnName="id")
     private User user;
     private Long receiver;

     @ManyToOne
     @JoinColumn(name = "id_notifcategory", insertable = false, updatable = false, nullable = false)
     NotifCategory notifCategory;
     private Integer id_notifcategory;

     @Column(name = "uri", nullable = true)
     private String uri;

     @Column(name = "image", nullable = true)
     private String image;

     @Column(name = "date", nullable = false)
     @CreationTimestamp
     private Date date;

     @Column(name = "bodymessage", nullable = false, columnDefinition = "text")
     private String bodymessage;

     @Column(name = "status_id", nullable = false)
     private Integer status_id;

    //  ================ setter | getter ===============

    /**
     * @param bodymessage the bodymessage to set
     */
    public void setBodymessage(String bodymessage) {
        this.bodymessage = bodymessage;
    }
    /**
     * @return the bodymessage
     */
    public String getBodymessage() {
        return bodymessage;
    }
    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @param id_notifcategory the id_notifcategory to set
     */
    public void setId_notifcategory(Integer id_notifcategory) {
        this.id_notifcategory = id_notifcategory;
    }
    /**
     * @return the id_notifcategory
     */
    public Integer getId_notifcategory() {
        return id_notifcategory;
    }
    /**
     * @param notifCategory the notifCategory to set
     */
    public void setNotifCategory(NotifCategory notifCategory) {
        this.notifCategory = notifCategory;
    }
    /**
     * @return the notifCategory
     */
    public NotifCategory getNotifCategory() {
        return notifCategory;
    }
    /**
     * @param receiver the receiver to set
     */
    public void setReceiver(Long receiver) {
        this.receiver = receiver;
    }
    /**
     * @return the receiver
     */
    public Long getReceiver() {
        return receiver;
    }
    /**
     * @param sender the sender to set
     */
    public void setSender(Long sender) {
        this.sender = sender;
    }
    /**
     * @return the sender
     */
    public Long getSender() {
        return sender;
    }
    /**
     * @param status_id the status_id to set
     */
    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
    }
    /**
     * @return the status_id
     */
    public Integer getStatus_id() {
        return status_id;
    }
    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }
    /**
     * @param userDetail the userDetail to set
     */
    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }
    /**
     * @return the userDetail
     */
    public UserDetail getUserDetail() {
        return userDetail;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }
    /**
     * @return the user
     */
    public User getUser() {
        return user;
    }
 }