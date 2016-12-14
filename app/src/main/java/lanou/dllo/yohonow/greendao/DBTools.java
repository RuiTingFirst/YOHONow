package lanou.dllo.yohonow.greendao;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;


/**
 * Created by dllo on 16/12/9.
 */

public class DBTools {
    private static DBTools ourInstance = new DBTools();
    private static MagazineDao magazineDao;

    /**
     * 对外提供获取本类单例对象的方法
     * @return
     */
    public static DBTools getInstance() {
        if (ourInstance == null){
            synchronized (DBTools.class){
                if (ourInstance == null) {
                    ourInstance = new DBTools();
                }

            }
        }
        magazineDao = MyApp.getDaoSession().getMagazineDao();
        return ourInstance;
    }

    private DBTools() {
    /**
     * 增的方法形参是Person
     */
    }

    /**
     * 增加集合的方法
     * @param list
     */
    public void insertList(List<Magazine> list){
        magazineDao.insertInTx(list);
    }

    /**
     * 删除方法
     */
    public void deleteMagazine(Magazine magazine){
        magazineDao.delete(magazine);
    }
    /**
     * 删除所有内容
     */
    public void deleteAll(){
        magazineDao.deleteAll();
    }
    /**
     * 根据Id删
     */
    public void deleteById(Long id){
        magazineDao.deleteByKey(id);
    }

    /**
     * 查询所有的方法
     */
    public List<Magazine> queryAll(){
        /**
         * 下面两种方法都可以
         */
        List<Magazine> lists = magazineDao.loadAll();
        return lists;
    }
    /**
     * 查重的方法
     */
    //根据journal来查询
    public boolean isHaveTheJournal(String journal){
        /**
         * 属性:Properties
         * MagazineDao.Properties.Journal.eq(journal)判断是否含有该属性
         */
        QueryBuilder<Magazine> queryBuilder = magazineDao.queryBuilder().where(MagazineDao.Properties.Journal.eq(journal));
        /**
         * 获取到我们要查询的内容的size
         */
        Long size = queryBuilder.buildCount().count();
        return size > 0 ? true: false;
    }

    public boolean isSaveMagazine(Magazine magazine){
        QueryBuilder<Magazine> queryBuilder = magazineDao.queryBuilder().where(MagazineDao.Properties.Journal.eq(magazine.getJournal()),MagazineDao.Properties.ImageUrl.eq(magazine.getImageUrl()));
        Long size = queryBuilder.buildCount().count();
        return size > 0 ? true : false;
    }
}
