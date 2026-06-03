package org.example;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class MyPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getTasks().register("commentStats", CommentStatsTask.class, task -> {
            task.setGroup("statistics");
            task.setDescription("Displays the number of comments in each Java file and the total count.");
        });

        project.getTasks().register("lineStats", LineStatsTask.class, task -> {
            task.setGroup("statistics");
            task.setDescription("Displays the number of lines in each Java file and the total count.");
        });
    }
}