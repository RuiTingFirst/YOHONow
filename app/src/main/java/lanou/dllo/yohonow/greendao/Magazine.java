package lanou.dllo.yohonow.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by dllo on 16/12/8.
 */
@Entity
public class Magazine {
    @Id
    private Long id;
    private String journal, imageUrl;
    boolean isChecked = false;
    @Generated(hash = 1012383445)
    public Magazine(Long id, String journal, String imageUrl, boolean isChecked) {
        this.id = id;
        this.journal = journal;
        this.imageUrl = imageUrl;
        this.isChecked = isChecked;
    }
    @Generated(hash = 203756486)
    public Magazine() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getJournal() {
        return this.journal;
    }
    public void setJournal(String journal) {
        this.journal = journal;
    }
    public String getImageUrl() {
        return this.imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public boolean getIsChecked() {
        return this.isChecked;
    }
    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
