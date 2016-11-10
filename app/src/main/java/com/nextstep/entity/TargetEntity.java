package com.nextstep.entity;

import java.util.HashSet;
import java.util.Set;


public class TargetEntity {
    private int id;
    private String title;
    private PersonEntity person;
    private Set<TaskEntity> tasksById = new HashSet<>();
    private boolean finishTarget;

    public TargetEntity() {
    }

    public TargetEntity(String title, PersonEntity personByPerson) {
        this.title = title;
        this.person = personByPerson;
        this.finishTarget = false;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFinishTarget() {
        return finishTarget;
    }
    public void setFinishTarget(boolean finishTarget) {
        this.finishTarget = finishTarget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TargetEntity that = (TargetEntity) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    public PersonEntity getPerson() {
        return person;
    }
    public void setPerson(PersonEntity person) {
        this.person = person;
    }

    public Set<TaskEntity> getTasksById() {
        return tasksById;
    }
    public void setTasksById(Set<TaskEntity> tasksById) {
        this.tasksById = tasksById;
    }

    @Override
    public String toString() {
        return "TargetEntity{" +
                "title='" + title + '\'' +
                ", person=" + person +
                '}';
    }
}
