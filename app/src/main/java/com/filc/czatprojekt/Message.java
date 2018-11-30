package com.filc.czatprojekt;

public class Message {

    private String messageText;
    private String messageUser;

    public Message() {}

    public Message(String messageText, String messageUser) {
        this.messageText = messageText;
        this.messageUser = messageUser;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

    public void setMessageUser(String messageUser) {
        this.messageUser = messageUser;
    }
}
