package com.nebnewt.tasklist;

/**
 * Created by Neb on 18.11.2016.
 */

public class Task {
    private String description;
    private String name;
    private int importance;

    public Task(String name, String description, int importance)
    {
        this.name=name;
        this.description=description;
        this.importance=importance;
    }

    public int getImportance() {
        return importance;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

}
