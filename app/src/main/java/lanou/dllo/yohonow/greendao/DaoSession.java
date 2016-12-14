package lanou.dllo.yohonow.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import lanou.dllo.yohonow.greendao.DetailBean;
import lanou.dllo.yohonow.greendao.Magazine;

import lanou.dllo.yohonow.greendao.DetailBeanDao;
import lanou.dllo.yohonow.greendao.MagazineDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig detailBeanDaoConfig;
    private final DaoConfig magazineDaoConfig;

    private final DetailBeanDao detailBeanDao;
    private final MagazineDao magazineDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        detailBeanDaoConfig = daoConfigMap.get(DetailBeanDao.class).clone();
        detailBeanDaoConfig.initIdentityScope(type);

        magazineDaoConfig = daoConfigMap.get(MagazineDao.class).clone();
        magazineDaoConfig.initIdentityScope(type);

        detailBeanDao = new DetailBeanDao(detailBeanDaoConfig, this);
        magazineDao = new MagazineDao(magazineDaoConfig, this);

        registerDao(DetailBean.class, detailBeanDao);
        registerDao(Magazine.class, magazineDao);
    }
    
    public void clear() {
        detailBeanDaoConfig.clearIdentityScope();
        magazineDaoConfig.clearIdentityScope();
    }

    public DetailBeanDao getDetailBeanDao() {
        return detailBeanDao;
    }

    public MagazineDao getMagazineDao() {
        return magazineDao;
    }

}