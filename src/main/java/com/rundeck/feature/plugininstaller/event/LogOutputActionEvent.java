package com.rundeck.feature.plugininstaller.event;

import com.rundeck.feature.api.output.ActionOutputEvent;
import com.rundeck.feature.api.output.OutputLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogOutputActionEvent implements ActionOutputEvent {
    String actionId;
    String message;
    OutputLevel level = OutputLevel.NORMAL;
    Long timestamp = System.nanoTime();
    String user;


    public LogOutputActionEvent(String actionId, String user, String message) {
        this.actionId = actionId;
        this.message = message;
        this.user = user;
    }

    public LogOutputActionEvent(String actionId, String user, String message, OutputLevel level) {
        this.actionId = actionId;
        this.message = message;
        this.user = user;
        this.level = level;
    }

}
