package lanou.dllo.yohonow.home;

import java.util.List;

/**
 * Created by dllo on 16/11/24.
 */
public class HomeBean {

    private String status;
    private int code;
    private String message;
    private List<DataBean> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private int type;
        private List<ParamsBean> params;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public List<ParamsBean> getParams() {
            return params;
        }

        public void setParams(List<ParamsBean> params) {
            this.params = params;
        }

        public static class ParamsBean {
            private String id;
            private String cid;
            private String create_time;
            private String publishURL;
            private String image;
            private String cover;
            private int width;
            private int height;
            private int contentType;
            private String title;
            private String titleFont;
            private String subTitleFont;
            private String subtitle;
            private String summary;
            private String app;
            private String updateMd5;
            private int imgNum;
            private int type;
            private List<TagBean> tag;
            private String journal;

            public String getJournal() {
                return journal;
            }

            public void setJournal(String journal) {
                this.journal = journal;
            }

            public String getCover() {
                return cover;
            }

            public void setCover(String cover) {
                this.cover = cover;
            }
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getPublishURL() {
                return publishURL;
            }

            public void setPublishURL(String publishURL) {
                this.publishURL = publishURL;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getContentType() {
                return contentType;
            }

            public void setContentType(int contentType) {
                this.contentType = contentType;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getTitleFont() {
                return titleFont;
            }

            public void setTitleFont(String titleFont) {
                this.titleFont = titleFont;
            }

            public String getSubTitleFont() {
                return subTitleFont;
            }

            public void setSubTitleFont(String subTitleFont) {
                this.subTitleFont = subTitleFont;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public String getApp() {
                return app;
            }

            public void setApp(String app) {
                this.app = app;
            }

            public String getUpdateMd5() {
                return updateMd5;
            }

            public void setUpdateMd5(String updateMd5) {
                this.updateMd5 = updateMd5;
            }

            public int getImgNum() {
                return imgNum;
            }

            public void setImgNum(int imgNum) {
                this.imgNum = imgNum;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public List<TagBean> getTag() {
                return tag;
            }

            public void setTag(List<TagBean> tag) {
                this.tag = tag;
            }

            public static class TagBean {
                /**
                 * tag_name : 球鞋
                 * type : 1
                 * tag_id : 3
                 */

                private String tag_name;
                private int type;
                private String tag_id;

                public String getTag_name() {
                    return tag_name;
                }

                public void setTag_name(String tag_name) {
                    this.tag_name = tag_name;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public String getTag_id() {
                    return tag_id;
                }

                public void setTag_id(String tag_id) {
                    this.tag_id = tag_id;
                }
            }
        }
    }
}
