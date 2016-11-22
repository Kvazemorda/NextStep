package com.nextstep.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.HashSet;
import java.util.Set;
@DatabaseTable(tableName = "family")
public class FamilyEntity {
    @DatabaseField(generatedId = true) private long id;
    @DatabaseField private String nameFamily;
    @ForeignCollectionField(eager = true) private Set<PersonEntity> personEntitySet = new HashSet<>();

    public FamilyEntity() {
    }

    public FamilyEntity(String nameFamily) {
        this.nameFamily = nameFamily;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getNameFamily() {
        return nameFamily;
    }
    public void setNameFamily(String nameFamily) {
        this.nameFamily = nameFamily;
    }

    public Set<PersonEntity> getPersonEntitySet() {
        return personEntitySet;
    }
    public void setPersonEntitySet(Set<PersonEntity> personEntitySet) {
        this.personEntitySet = personEntitySet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FamilyEntity)) return false;

        FamilyEntity that = (FamilyEntity) o;

        if (id != that.id) return false;
        if (nameFamily != null ? !nameFamily.equals(that.nameFamily) : that.nameFamily != null) return false;
        if (personEntitySet != null ? !personEntitySet.equals(that.personEntitySet) : that.personEntitySet != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) id;
        result = 31 * result + (nameFamily != null ? nameFamily.hashCode() : 0);
        result = 31 * result + (personEntitySet != null ? personEntitySet.hashCode() : 0);
        return result;
    }
}
