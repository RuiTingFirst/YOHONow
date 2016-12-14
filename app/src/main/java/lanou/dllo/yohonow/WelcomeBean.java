package lanou.dllo.yohonow;

/**
 * Created by dllo on 16/12/9.
 */

public class WelcomeBean {

    /**
     * status : 0
     * code : 200000
     * data : {"id":"13","pic":"http://img02.yohoboys.com/staticimg/2016/12/05/09/02d3ad3d72fedc0c45c8af66c1680610ed.jpg","delay":"6","uptime":"1480728213","endtime":"1607126400","is_del":"0","link":"","platform":"2","isShowTip":true}
     * message : 操作成功
     */

    private String status;
    private int code;
    private DataBean data;
    private String message;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * id : 13
         * pic : http://img02.yohoboys.com/staticimg/2016/12/05/09/02d3ad3d72fedc0c45c8af66c1680610ed.jpg
         * delay : 6
         * uptime : 1480728213
         * endtime : 1607126400
         * is_del : 0
         * link :
         * platform : 2
         * isShowTip : true
         */

        private String id;
        private String pic;
        private String delay;
        private String uptime;
        private String endtime;
        private String is_del;
        private String link;
        private String platform;
        private boolean isShowTip;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getDelay() {
            return delay;
        }

        public void setDelay(String delay) {
            this.delay = delay;
        }

        public String getUptime() {
            return uptime;
        }

        public void setUptime(String uptime) {
            this.uptime = uptime;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getIs_del() {
            return is_del;
        }

        public void setIs_del(String is_del) {
            this.is_del = is_del;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getPlatform() {
            return platform;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }

        public boolean isIsShowTip() {
            return isShowTip;
        }

        public void setIsShowTip(boolean isShowTip) {
            this.isShowTip = isShowTip;
        }
    }
}
