package com.androidtask.repository.local.persistence;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.androidtask.domain.models.UserDetails;
import com.androidtask.domain.models.User;
import com.androidtask.domain.models.FavoritePlace;

import com.androidtask.repository.local.persistence.UserDetailsDao;
import com.androidtask.repository.local.persistence.UserDao;
import com.androidtask.repository.local.persistence.FavoritePlaceDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDetailsDaoConfig;
    private final DaoConfig userDaoConfig;
    private final DaoConfig favoritePlaceDaoConfig;

    private final UserDetailsDao userDetailsDao;
    private final UserDao userDao;
    private final FavoritePlaceDao favoritePlaceDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDetailsDaoConfig = daoConfigMap.get(UserDetailsDao.class).clone();
        userDetailsDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        favoritePlaceDaoConfig = daoConfigMap.get(FavoritePlaceDao.class).clone();
        favoritePlaceDaoConfig.initIdentityScope(type);

        userDetailsDao = new UserDetailsDao(userDetailsDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);
        favoritePlaceDao = new FavoritePlaceDao(favoritePlaceDaoConfig, this);

        registerDao(UserDetails.class, userDetailsDao);
        registerDao(User.class, userDao);
        registerDao(FavoritePlace.class, favoritePlaceDao);
    }
    
    public void clear() {
        userDetailsDaoConfig.clearIdentityScope();
        userDaoConfig.clearIdentityScope();
        favoritePlaceDaoConfig.clearIdentityScope();
    }

    public UserDetailsDao getUserDetailsDao() {
        return userDetailsDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public FavoritePlaceDao getFavoritePlaceDao() {
        return favoritePlaceDao;
    }

}
