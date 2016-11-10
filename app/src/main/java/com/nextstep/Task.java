package com.nextstep;

public class Task {
    public String task;
    public String description;
    public Double cost;

    public Task(String task, String description, Double cost) {
        this.task = task;
        this.description = description;
        this.cost = cost;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Task{" +
                "task='" + task + '\'' +
                ", description='" + description + '\'' +
                ", cost='" + cost + '\'' +
                '}';
    }
}
