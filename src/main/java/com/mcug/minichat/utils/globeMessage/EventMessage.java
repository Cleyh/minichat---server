package com.mcug.minichat.utils.globeMessage;

import lombok.Data;

@Data
public class EventMessage {
    String queue;
    String message;
    Object data;

    public EventMessage() {
        this.queue = "";
        this.message = "";
        this.data = null;
    }

    public EventMessage setQueue(String queue) {
        this.queue = queue;
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
