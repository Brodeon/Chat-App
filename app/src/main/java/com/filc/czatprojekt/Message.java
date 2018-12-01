package com.filc.czatprojekt;

public class Message {

    private String id;
    private String messageText;
    private String messageUser;
    private long timestamp;

    public Message() {}

    public Message(String id, String messageText, String messageUser, long timestamp) {
        this.id = id;
        this.messageText = messageText;
        this.messageUser = messageUser;
        this.timestamp = timestamp;
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
