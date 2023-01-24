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


    public Message(String sender, String content) {

        this.sender = sender;
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
        return  this.getSender() + this.getContent() ;
    }
}
