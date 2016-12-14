package lanou.dllo.yohonow.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 16/12/10.
 */
@Entity
public class DetailBean {
    @Id
    private Long id;
    private String publishURL, title, createTime, tagName, Image, videoURL;
    private int type;
    @Generated(hash = 1064546875)
    public DetailBean(Long id, String publishURL, String title, String createTime,
            String tagName, String Image, String videoURL, int type) {
        this.id = id;
        this.publishURL = publishURL;
        this.title = title;
        this.createTime = createTime;
        this.tagName = tagName;
        this.Image = Image;
        this.videoURL = videoURL;
        this.type = type;
    }
    @Generated(hash = 610650804)
    public DetailBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPublishURL() {
        return this.publishURL;
    }
    public void setPublishURL(String publishURL) {
        this.publishURL = publishURL;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public String getTagName() {
        return this.tagName;
    }
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
    public String getImage() {
        return this.Image;
    }
    public void setImage(String Image) {
        this.Image = Image;
    }
    public String getVideoURL() {
        return this.videoURL;
    }
    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    
}
