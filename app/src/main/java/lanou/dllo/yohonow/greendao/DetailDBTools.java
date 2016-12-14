package lanou.dllo.yohonow.greendao;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by dllo on 16/12/10.
 */

public class DetailDBTools {
    private static DetailDBTools ourInstance = new DetailDBTools();
    private static DetailBeanDao detailBeanDao;

    /**
     * 对外提供获取本类单例对象的方法
     *
     * @return
     */
    public static DetailDBTools getInstance() {
        if (ourInstance == null) {
            synchronized (DetailDBTools.class) {
                if (ourInstance == null) {
                    ourInstance = new DetailDBTools();
                }

            }
        }
        detailBeanDao = MyApp.getDaoSession().getDetailBeanDao();
        return ourInstance;
    }

    private DetailDBTools() {
        /**
         * 增的方法形参是Person
         */
    }

    /**
     * 增加集合的方法
     *
     * @param list
     */
    public void insertList(List<DetailBean> list) {
        detailBeanDao.insertInTx(list);
    }

    /**
     * 删除方法
     */
    public void deleteDetailBean(DetailBean detailBean) {
        detailBeanDao.delete(detailBean);
    }

    /**
     * 删除所有内容
     */
    public void deleteAll() {
        detailBeanDao.deleteAll();
    }

    /**
     * 根据Id删
     */
    public void deleteById(Long id) {
        detailBeanDao.deleteByKey(id);
    }

    /**
     * 根据某一个字段进行删除操作
     */
    public void deleteByTitle(String title){
        DeleteQuery<DetailBean> deleteQuery = detailBeanDao.queryBuilder().where(DetailBeanDao.Properties.Title.eq(title)).buildDelete();
        deleteQuery.executeDeleteWithoutDetachingEntities();
    }
    /**
     * 查询所有的方法
     */
    public List<DetailBean> queryAll() {
        /**
         * 下面两种方法都可以
         */
        List<DetailBean> lists = detailBeanDao.loadAll();
        return lists;
    }

    /**
     * 查重的方法
     */
    //根据姓名来查询
    public boolean isHaveTheTitle(String title) {
        /**
         * 属性:Properties
         * DetailBeanDao.Properties.PublishURL.eq(publishURL)判断是否含有该属性
         */
        QueryBuilder<DetailBean> queryBuilder = detailBeanDao.queryBuilder().where(DetailBeanDao.Properties.Title.eq(title));
        /**
         * 获取到我们要查询的内容的size
         */
        Long size = queryBuilder.buildCount().count();
        return size > 0 ? true : false;
    }

    public boolean isSaveDetailBean(DetailBean detailBean) {
        QueryBuilder<DetailBean> queryBuilder = detailBeanDao.queryBuilder().
                where(DetailBeanDao.Properties.PublishURL.eq(detailBean.getPublishURL()),
                        DetailBeanDao.Properties.Image.eq(detailBean.getImage()),
                        DetailBeanDao.Properties.CreateTime.eq(detailBean.getCreateTime()),
                        DetailBeanDao.Properties.TagName.eq(detailBean.getTagName()),
                        DetailBeanDao.Properties.Title.eq(detailBean.getTitle()),
                        DetailBeanDao.Properties.VideoURL.eq(detailBean.getVideoURL()));
        Long size = queryBuilder.buildCount().count();
        return size > 0 ? true : false;
    }
}
