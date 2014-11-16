/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.Serializable;

/**
 *
 * @author naomi
 */
public class Row implements Serializable {

    
    private int idpost;
    private String replaydate="";
    private String sender="";
    private String receiver="";
    private int numpost;
    private String subjectpost ="";
    private String textpost ="";
    private int iduser ;
    // Add/generate constructor(s), getters and setters.

public Row(){
    
}
    public Row(int idpost, String sender, String receiver, String replydate,
               int numpost, String subjectpost, String textpost, int iduser) {

        this.idpost=idpost;
       this.sender=sender;
        this.receiver=receiver;
       this.replaydate=replydate;
       this.numpost=numpost;
       this.subjectpost =subjectpost;
       this.textpost =textpost;
       this.iduser =iduser;
    }

    // Getters ------------------------------------------------------------------------------------

    /**
     * Returns the ID of he message.
     */
    public int getIdPost() {
        return idpost;
    }

    /**
     * Returns the sender

     */
    public String getSender() {
        return sender;
    }

    /**
     * Returns the sender.

     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * Returns the email address of this User.
     * @return The email address of this User.
     */
    public String getReplaydate() {
        return replaydate;
    }

    /**
     * Returns the numpost of this message
     */
    public Integer getNumpost() {
        return numpost;
    }

     public String getSubjectpost() {
        return subjectpost;
    }

      public String getTextPost() {
        return textpost;

    }
 public Integer getIdUser() {
        return iduser;
    }
    // Setters ------------------------------------------------------------------------------------

    /**
     * Sets the ID of this User.
     * @param id The ID of this User.
     */
    /**
     * Returns the ID of he message.
     */
    public void setIdPost(int idpost) {
        this.idpost=idpost;

    }

    /**
     * Returns the sender

     */
    public void setSender( String sender) {
        this.sender=sender;
    }

    /**
     * Returns the sender.

     */
    public void setReceiver(String receiver) {
        this.receiver= receiver;
    }

    /**
     * Returns the email address of this User.
     * @return The email address of this User.
     */
    public void setReplydate( String replaydate) {
       this.replaydate = replaydate;
    }

    /**
     * Returns the numpost of this message
     */
    public void setNumpost(int numpost) {
        this.numpost = numpost;
    }

     public void setSubjectpost(String subjectpost) {
        this.subjectpost= subjectpost;
    }

      public void setTextPost(String textpost) {
        this.textpost = textpost;

    }
 public void setIdUser(int iduser) {
        this.iduser = iduser;
    }




}
