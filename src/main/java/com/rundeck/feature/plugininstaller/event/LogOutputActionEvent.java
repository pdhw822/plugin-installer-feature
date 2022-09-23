package com.rundeck.feature.plugininstaller.event;

import com.rundeck.feature.api.output.ActionOutputEvent;
import com.rundeck.feature.api.output.OutputLevel;

public class LogOutputActionEvent implements ActionOutputEvent {
    String actionId;
    String message;
    OutputLevel level = OutputLevel.NORMAL;
    Long timestamp = System.nanoTime();

    public LogOutputActionEvent() {
    }

    public LogOutputActionEvent(String actionId, String message) {
        this.actionId = actionId;
        this.message = message;
    }

    public LogOutputActionEvent(String actionId, String message, OutputLevel level) {
        this.actionId = actionId;
        this.message = message;
        this.level = level;
    }

    public LogOutputActionEvent(String actionId, String message, OutputLevel level, Long timestamp) {
        this.actionId = actionId;
        this.message = message;
        this.level = level;
        this.timestamp = timestamp;
    }

    @Override
    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public OutputLevel getLevel() {
        return level;
    }

    public void setLevel(OutputLevel level) {
        this.level = level;
    }

    @Override
    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
