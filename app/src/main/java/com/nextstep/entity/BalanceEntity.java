package com.nextstep.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigDecimal;
import java.util.Date;

@DatabaseTable(tableName = "balance")
public class BalanceEntity {

    @DatabaseField(generatedId = true) long id;
    @DatabaseField BigDecimal balance;
    @DatabaseField Date dateBalance;
    @DatabaseField(foreign = true, foreignAutoRefresh = true) PersonEntity personEntity;

    public BalanceEntity() {
    }

    public BalanceEntity(BigDecimal balance, Date dateBalance, PersonEntity personEntity) {
        this.balance = balance;
        this.dateBalance = dateBalance;
        this.personEntity = personEntity;
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

    public Date getDateBalance() {
        return dateBalance;
    }

    public void setDateBalance(Date dateBalance) {
        this.dateBalance = dateBalance;
    }

    public PersonEntity getPersonEntity() {
        return personEntity;
    }

    public void setPersonEntity(PersonEntity personEntity) {
        this.personEntity = personEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BalanceEntity that = (BalanceEntity) o;

        if (dateBalance != null ? !dateBalance.equals(that.dateBalance) : that.dateBalance != null) return false;
        return personEntity != null ? personEntity.equals(that.personEntity) : that.personEntity == null;

    }

    @Override
    public int hashCode() {
        int result = dateBalance != null ? dateBalance.hashCode() : 0;
        result = 31 * result + (personEntity != null ? personEntity.hashCode() : 0);
        return result;
    }
}
