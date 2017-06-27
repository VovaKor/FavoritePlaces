package com.androidtask.domain.models;

import com.androidtask.repository.local.persistence.DaoSession;
import com.androidtask.repository.local.persistence.UserDetailsDao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.DaoException;

/**
 * Entity mapped to table "USER_DETAILS".
 */
@Entity (active = true)
public class UserDetails {

    @Id
    private String id;
    private String first_name;
    private String patronymic;
    private String last_name;
    private String phone;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1181202576)
    private transient UserDetailsDao myDao;
    @Generated(hash = 1914687025)
    public UserDetails(String id, String first_name, String patronymic, String last_name,
            String phone) {
        this.id = id;
        this.first_name = first_name;
        this.patronymic = patronymic;
        this.last_name = last_name;
        this.phone = phone;
    }
    @Generated(hash = 64089743)
    public UserDetails() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getFirst_name() {
        return this.first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
    public String getPatronymic() {
        return this.patronymic;
    }
    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }
    public String getLast_name() {
        return this.last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1485137714)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUserDetailsDao() : null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDetails that = (UserDetails) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (first_name != null ? !first_name.equals(that.first_name) : that.first_name != null)
            return false;
        if (patronymic != null ? !patronymic.equals(that.patronymic) : that.patronymic != null)
            return false;
        if (last_name != null ? !last_name.equals(that.last_name) : that.last_name != null)
            return false;
        return phone != null ? phone.equals(that.phone) : that.phone == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (first_name != null ? first_name.hashCode() : 0);
        result = 31 * result + (patronymic != null ? patronymic.hashCode() : 0);
        result = 31 * result + (last_name != null ? last_name.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}