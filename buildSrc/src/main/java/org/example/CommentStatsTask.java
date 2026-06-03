package org.example;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CommentStatsTask extends DefaultTask {

    @TaskAction
    public void execute() {
        File sourceDir = getProject().file("src/main/java");
        if (!sourceDir.exists()) {
            getLogger().warn("Java files are not found");
            return;
        }

        List<File> javaFiles = new ArrayList<>();
        findJavaFiles(sourceDir, javaFiles);

        System.out.println("----------------------------------------");
        System.out.println("COMMENT STATISTICS");
        System.out.println("----------------------------------------");

        int totalComments = 0;

        for (File file : javaFiles) {
            try {
                List<String> lines = Files.readAllLines(file.toPath());
                int commentsCount = countComments(lines);
                totalComments += commentsCount;

                System.out.println("File: " + file.getName() + " -> Comments: " + commentsCount);
            } catch (IOException e) {
                getLogger().error("Could not read file: " + file.getName(), e);
            }
        }

        System.out.println("----------------------------------------");
        System.out.println("TOTAL COMMENTS: " + totalComments);
        System.out.println("----------------------------------------");
    }

    private void findJavaFiles(File dir, List<File> fileList) {
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File file : files) {
            if (file.isDirectory()) {
                findJavaFiles(file, fileList);
            } else if (file.getName().endsWith(".java")) {
                fileList.add(file);
            }
        }
    }

    private int countComments(List<String> lines) {
        int n = 0;
        for (String line : lines) {
            if (line.trim().startsWith("//")) {
                n++;
            }
        }
        return n;
    }
}