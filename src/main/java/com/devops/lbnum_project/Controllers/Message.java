package com.devops.lbnum_project.Controllers;

import java.io.Serial;
import java.io.Serializable;

public class Message implements Serializable {
    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 4632638763453902255L;
    private String sender, content;
    private int userId;



    public Message(String content) {
        this.content = content;
    }

    public Message( String content,String sender) {

        this.sender = sender;
        this.content = content;
    }
    public Message( String content,int userId) {
        this.userId = userId;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String toString() {
        if (getContent() != null && getSender() !=null){
            return String.format("%s : %s", this.getSender(), this.getContent());
        }else {
            return getContent();
        }
    }
}
