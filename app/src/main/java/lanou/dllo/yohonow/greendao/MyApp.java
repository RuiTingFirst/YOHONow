package lanou.dllo.yohonow.greendao;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/12/9.
 */

public class MyApp extends Application{
    private static Context mContext;

    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    // 对外提供获取DaoMaster对象
    public static DaoMaster getDaoMaster() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(getmContext(),"Magazine.db",null);
        /**
         * 初始化DaoMaster对象
         */
        daoMaster = new DaoMaster(helper.getWritableDb());
        return daoMaster;
    }
    //对外提供获取DaoSession对象
    public static DaoSession getDaoSession() {
        if (daoSession == null){
            if (daoMaster == null){
                daoMaster = getDaoMaster();

            }
            /**
             * 初始化daoSession对象
             */
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getmContext() {
        return mContext;
    }
}
