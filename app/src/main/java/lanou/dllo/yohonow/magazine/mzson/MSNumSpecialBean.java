package lanou.dllo.yohonow.magazine.mzson;

import java.util.List;

/**
 * Created by dllo on 16/11/26.
 */
public class MSNumSpecialBean {

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
        /**
         * magId : 10138
         * magChapter : [{"deviceType":0,"sectionId":"1","sectionTitle":"Cover","sectionThumb":"http://static0.myoho.net/2016/08/18/13/ezineimg/569201eaad72ffcc16e1797b09163e92.jpg?v=1471500118","sectionUrl":"http://h5api.myoho.net/index.php?r=share/share&magId=10138&secId=1&updateTime=1471500118","sectionAddress":"http://staticzine.myoho.net/10138/1/1.zip?v=1471500118","sectionBytes":"1344512","sectionSize":1.28,"sectionRealBytes":"1641924","sharePic":"http://static0.myoho.net/2016/08/18/13/ezineimg/06f45be928a432bee789e86f6c8158ea.jpg?v=1471500118"},{"deviceType":0,"sectionId":"7","sectionTitle":"操作指南","sectionThumb":"http://static0.myoho.net/2016/08/18/13/ezineimg/2fcf958d28fdb46635c0197acf02ec08.jpg?v=1471499645","sectionUrl":"http://h5api.myoho.net/index.php?r=share/share&magId=10138&secId=7&updateTime=1471499645","sectionAddress":"http://staticzine.myoho.net/10138/1/7.zip?v=1471499645","sectionBytes":"578361","sectionSize":0.55,"sectionRealBytes":"857567","sharePic":"http://static0.myoho.net/2016/08/18/13/ezineimg/eeff350a4e7990f39bd82cd30b15d271.jpg?v=1471499645"},{"deviceType":0,"sectionId":"5","sectionTitle":"Music News","sectionThumb":"http://static0.myoho.net/2016/08/18/13/ezineimg/5328f50ee9da091c3a539f8ff1aab630.jpg?v=1471500067","sectionUrl":"http://h5api.myoho.net/index.php?r=share/share&magId=10138&secId=5&updateTime=1471500067","sectionAddress":"http://staticzine.myoho.net/10138/1/5.zip?v=1471500067","sectionBytes":"2889818","sectionSize":2.76,"sectionRealBytes":"3082646","sharePic":"http://static0.myoho.net/2016/08/18/13/ezineimg/d8a7ff777552c9735dd77ed0f0800b21.jpg?v=1471500067"},{"deviceType":0,"sectionId":"6","sectionTitle":"Fashion","sectionThumb":"http://static0.myoho.net/2016/08/18/13/ezineimg/ad01b519a1ba3f069825b1f5f819a1bd.jpg?v=1471500082","sectionUrl":"http://h5api.myoho.net/index.php?r=share/share&magId=10138&secId=6&updateTime=1471500082","sectionAddress":"http://staticzine.myoho.net/10138/1/6.zip?v=1471500082","sectionBytes":"884416","sectionSize":0.84,"sectionRealBytes":"1038412","sharePic":"http://static0.myoho.net/2016/08/18/13/ezineimg/05751951db967eef40496c2df5539479.jpg?v=1471500082"},{"deviceType":0,"sectionId":"8","sectionTitle":"Interview","sectionThumb":"http://static0.myoho.net/2016/08/18/13/ezineimg/69d090993aeeeae4993457d80f4c0b61.jpg?v=0","sectionUrl":"http://h5api.myoho.net/index.php?r=share/share&magId=10138&secId=8&updateTime=0","sectionAddress":"http://staticzine.myoho.net/10138/1/8.zip?v=0","sectionBytes":"2199568","sectionSize":2.1,"sectionRealBytes":"2406128","sharePic":"http://static0.myoho.net/2016/08/18/13/ezineimg/4ebb22b860045e221c78ef4c97d06d4b.jpg?v=0"},{"deviceType":0,"sectionId":"3","sectionTitle":"Voice","sectionThumb":"http://static0.myoho.net/2016/08/18/13/ezineimg/ae4fc40895f3bf9cc32df8477427104e.jpg?v=1471504651","sectionUrl":"http://h5api.myoho.net/index.php?r=share/share&magId=10138&secId=3&updateTime=1471504651","sectionAddress":"http://staticzine.myoho.net/10138/1/3.zip?v=1471504651","sectionBytes":"584263","sectionSize":0.56,"sectionRealBytes":"719068","sharePic":"http://static0.myoho.net/2016/08/18/13/ezineimg/d1b28cffdb78140dd6380019248e3f64.jpg?v=1471504651"}]
         * shareTitle : YOHO!BOY 潮流生活百科 HMV X YOHO! 别册
         * app : 1
         * title :
         * cover : http://static0.myoho.net/2016/08/18/13/ezineimg/ff63ce078b42b75c74c9fad8187c8a08.jpg?v=1471504683
         * magType : 2
         * deviceType : 0
         * journal : HMV X YOHO! 别册
         * summary : 今刊hmv x YOHO! 带大家走进香港电影的国度，透过访问人气电影《导火新闻线》的一众演员，了解现时的本土电影文化。香港电影，香港缩影，本土电影的珍贵，未必只在于它的成本制作和票房收入，更多是它如何见证了城市的一段时间、一件事或一个人等等，是纪录也是艺术。
         * summarys : 今刊hmv x YOHO! 帶大家走進香港電影的國度，透過訪問人氣電影《導火新聞線》的一眾演員，了解現時的本土電影文化。香港電影，香港縮影，本土電影的珍貴，未必只在於它的成本製作和票房收入，更多是它如何見證了城市的一段時間、一件事或一個人等等，是紀錄也是藝術。
         * releaseDate : 1471504683
         * bytes : 8482204
         * size : 8.09
         * idfbytes : 0
         * idfsize : 0
         * address : http://staticzine.myoho.net/10138/1/ezine.zip?v=1471504683
         * idfAddress :
         * description :
         * isH5 : 1
         */

        private String magId;
        private String shareTitle;
        private int app;
        private String title;
        private String cover;
        private String magType;
        private int deviceType;
        private String journal;
        private String summary;
        private String summarys;
        private String releaseDate;
        private int bytes;
        private double size;
        private int idfbytes;
        private int idfsize;
        private String address;
        private String idfAddress;
        private String description;
        private int isH5;
        private List<MagChapterBean> magChapter;

        public String getMagId() {
            return magId;
        }

        public void setMagId(String magId) {
            this.magId = magId;
        }

        public String getShareTitle() {
            return shareTitle;
        }

        public void setShareTitle(String shareTitle) {
            this.shareTitle = shareTitle;
        }

        public int getApp() {
            return app;
        }

        public void setApp(int app) {
            this.app = app;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getMagType() {
            return magType;
        }

        public void setMagType(String magType) {
            this.magType = magType;
        }

        public int getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(int deviceType) {
            this.deviceType = deviceType;
        }

        public String getJournal() {
            return journal;
        }

        public void setJournal(String journal) {
            this.journal = journal;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getSummarys() {
            return summarys;
        }

        public void setSummarys(String summarys) {
            this.summarys = summarys;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public int getBytes() {
            return bytes;
        }

        public void setBytes(int bytes) {
            this.bytes = bytes;
        }

        public double getSize() {
            return size;
        }

        public void setSize(double size) {
            this.size = size;
        }

        public int getIdfbytes() {
            return idfbytes;
        }

        public void setIdfbytes(int idfbytes) {
            this.idfbytes = idfbytes;
        }

        public int getIdfsize() {
            return idfsize;
        }

        public void setIdfsize(int idfsize) {
            this.idfsize = idfsize;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIdfAddress() {
            return idfAddress;
        }

        public void setIdfAddress(String idfAddress) {
            this.idfAddress = idfAddress;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getIsH5() {
            return isH5;
        }

        public void setIsH5(int isH5) {
            this.isH5 = isH5;
        }

        public List<MagChapterBean> getMagChapter() {
            return magChapter;
        }

        public void setMagChapter(List<MagChapterBean> magChapter) {
            this.magChapter = magChapter;
        }

        public static class MagChapterBean {
            /**
             * deviceType : 0
             * sectionId : 1
             * sectionTitle : Cover
             * sectionThumb : http://static0.myoho.net/2016/08/18/13/ezineimg/569201eaad72ffcc16e1797b09163e92.jpg?v=1471500118
             * sectionUrl : http://h5api.myoho.net/index.php?r=share/share&magId=10138&secId=1&updateTime=1471500118
             * sectionAddress : http://staticzine.myoho.net/10138/1/1.zip?v=1471500118
             * sectionBytes : 1344512
             * sectionSize : 1.28
             * sectionRealBytes : 1641924
             * sharePic : http://static0.myoho.net/2016/08/18/13/ezineimg/06f45be928a432bee789e86f6c8158ea.jpg?v=1471500118
             */

            private int deviceType;
            private String sectionId;
            private String sectionTitle;
            private String sectionThumb;
            private String sectionUrl;
            private String sectionAddress;
            private String sectionBytes;
            private double sectionSize;
            private String sectionRealBytes;
            private String sharePic;

            public int getDeviceType() {
                return deviceType;
            }

            public void setDeviceType(int deviceType) {
                this.deviceType = deviceType;
            }

            public String getSectionId() {
                return sectionId;
            }

            public void setSectionId(String sectionId) {
                this.sectionId = sectionId;
            }

            public String getSectionTitle() {
                return sectionTitle;
            }

            public void setSectionTitle(String sectionTitle) {
                this.sectionTitle = sectionTitle;
            }

            public String getSectionThumb() {
                return sectionThumb;
            }

            public void setSectionThumb(String sectionThumb) {
                this.sectionThumb = sectionThumb;
            }

            public String getSectionUrl() {
                return sectionUrl;
            }

            public void setSectionUrl(String sectionUrl) {
                this.sectionUrl = sectionUrl;
            }

            public String getSectionAddress() {
                return sectionAddress;
            }

            public void setSectionAddress(String sectionAddress) {
                this.sectionAddress = sectionAddress;
            }

            public String getSectionBytes() {
                return sectionBytes;
            }

            public void setSectionBytes(String sectionBytes) {
                this.sectionBytes = sectionBytes;
            }

            public double getSectionSize() {
                return sectionSize;
            }

            public void setSectionSize(double sectionSize) {
                this.sectionSize = sectionSize;
            }

            public String getSectionRealBytes() {
                return sectionRealBytes;
            }

            public void setSectionRealBytes(String sectionRealBytes) {
                this.sectionRealBytes = sectionRealBytes;
            }

            public String getSharePic() {
                return sharePic;
            }

            public void setSharePic(String sharePic) {
                this.sharePic = sharePic;
            }
        }
    }
}
