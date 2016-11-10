package com.nextstep.entity;

import java.math.BigDecimal;

public class CashFlowEntity {
    private long id;
    private BigDecimal balance;
    private String title;
    private int task;
    private TaskEntity taskByTask;

    public CashFlowEntity() {
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getTask() {
        return task;
    }
    public void setTask(int task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CashFlowEntity that = (CashFlowEntity) o;

        if (balance != that.balance) return false;
        if (id != that.id) return false;
        if (task != that.task) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) id;
        temp = Long.parseLong(String.valueOf(balance));
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + task;
        return result;
    }

    public TaskEntity getTaskByTask() {
        return taskByTask;
    }
    public void setTaskByTask(TaskEntity taskByTask) {
        this.taskByTask = taskByTask;
    }
}
