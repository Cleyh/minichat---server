package com.mcug.minichat.utils.entity;

import lombok.Data;

@Data
public class EventMessage {
    String eventName;
    String message;
    Object data;

    public EventMessage() {
        this.eventName = "";
        this.message = "";
        this.data = null;
    }

    public EventMessage setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public EventMessage setMessage(String message) {
        this.message = message;
        return this;
    }

    public EventMessage setData(Object data) {
        this.data = data;
        return this;
    }
}
