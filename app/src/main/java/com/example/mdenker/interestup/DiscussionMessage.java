package com.example.mdenker.interestup;

/**
 * Created by tsengjonathan on 3/29/18.
 */
class DiscussionMessage {
    String message;
    String sender;
    String createdAt;

    public DiscussionMessage(String message, String sender, String createdAt) {
        this.message = message;
        this.sender = sender;
        this.createdAt = createdAt;
    }
}
