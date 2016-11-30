package lanou.dllo.yohonow.community;

import java.util.List;

/**
 * Created by dllo on 16/11/30.
 */

public class CommunityTrunBean {

    /**
     * alg : SALT_MD5
     * code : 200
     * data : {"authorInfo":{"bgPic":"","headIcon":"http://head.static.yhbimg.com/yhb-head/2016/8/26/12/012c2770697a5fbb123c229dad8238f48c.975577.jpg?imageView/{mode}/w/{width}/h/{height}","nickName":"Yo君","signature":"YOHO!BUY饮水机管理员","uid":7950645},"blocks":[{"contentData":"之前玩ins的时候发现朋友圈之中只有我一个人在玩，便迎来了走在冷风中的冻感。。。。。。一个人的社交网络实在是太无聊了好嘛！！！！ ！ \r\n\r\n想成为网红？\r\n想结交潮人朋友？ \r\n想走进潮流圈？ \r\n觉得自己平时的穿搭审美很孤独？\r\n根本没有人懂？ \r\n想获取最新潮流资讯？ \r\n想分享时下最热潮流单品？ \r\n想买潮品又不知道什么地方可以买？ \r\n想参加大型潮流活动？ \r\n想fo紧余文乐？ \r\n想要潮到炸？？？ \r\n. \r\n. \r\n. \r\nmóu   měn   teí \r\n  没      问     题 \r\n\r\n只要一个YOHO!潮流社区，就可以满足你以上全部要求！ \r\n\r\n我们的社区验证码↓↓↓ \r\n社长：玩YOHO!潮流社区！ \r\n社员：我就是最潮的那个人！ \r\n\r\n（Tips: 社 区 二 十 四 小 时 不 插 电 营 业 中，社 长 在 此 恭 候 大 驾）","order":1,"size":"","templateKey":"text"},{"contentData":"http://img10.static.yhbimg.com/yhb-img01/2016/08/26/13/01bdf4920f5dec13c129a1ddaa6aaeef20.jpg?imageView/{mode}/w/{width}/h/{height}","order":2,"size":"750x1067","templateKey":"image"}],"browse":1693429,"comment":0,"createTime":1472188463478,"forumCode":10001,"forumName":"潮流风向","hasPraise":"N","id":27553,"indexTopTime":0,"isForumTop":0,"isHot":0,"isIndexTop":0,"postsTitle":"Welcome：一起玩YOHO!潮流社区，你就是最潮的那个人！","praise":262,"praiseUsers":[{"bgPic":"","headIcon":"http://img11.static.yhbimg.com/yhb-img01/2016/07/05/13/017ec560b82c132ab2fdb22f7cf6f42b83.png?imageView/{mode}/w/{width}/h/{height}","nickName":"182****1413","uid":16355263},{"bgPic":"","headIcon":"http://head.static.yhbimg.com/yhb-head/2016/11/29/13/01dc995dc4e2c5c11eff9fa1bef55796b4.407302.jpg?imageView/{mode}/w/{width}/h/{height}","nickName":"阿部_417","uid":24190278},{"bgPic":"","headIcon":"http://img11.static.yhbimg.com/yhb-img01/2016/07/05/13/017ec560b82c132ab2fdb22f7cf6f42b83.png?imageView/{mode}/w/{width}/h/{height}","nickName":"131****2589","uid":22000964}],"revieweState":1,"revieweTime":0,"status":0,"updateTime":0}
     * md5 : f93f5a42fd0f808c4057a4e48eb092e1
     * message : 获取帖子详情成功.
     */

    private String alg;
    private int code;
    private DataBean data;
    private String md5;
    private String message;

    public String getAlg() {
        return alg;
    }

    public void setAlg(String alg) {
        this.alg = alg;
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

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * authorInfo : {"bgPic":"","headIcon":"http://head.static.yhbimg.com/yhb-head/2016/8/26/12/012c2770697a5fbb123c229dad8238f48c.975577.jpg?imageView/{mode}/w/{width}/h/{height}","nickName":"Yo君","signature":"YOHO!BUY饮水机管理员","uid":7950645}
         * blocks : [{"contentData":"之前玩ins的时候发现朋友圈之中只有我一个人在玩，便迎来了走在冷风中的冻感。。。。。。一个人的社交网络实在是太无聊了好嘛！！！！ ！ \r\n\r\n想成为网红？\r\n想结交潮人朋友？ \r\n想走进潮流圈？ \r\n觉得自己平时的穿搭审美很孤独？\r\n根本没有人懂？ \r\n想获取最新潮流资讯？ \r\n想分享时下最热潮流单品？ \r\n想买潮品又不知道什么地方可以买？ \r\n想参加大型潮流活动？ \r\n想fo紧余文乐？ \r\n想要潮到炸？？？ \r\n. \r\n. \r\n. \r\nmóu   měn   teí \r\n  没      问     题 \r\n\r\n只要一个YOHO!潮流社区，就可以满足你以上全部要求！ \r\n\r\n我们的社区验证码↓↓↓ \r\n社长：玩YOHO!潮流社区！ \r\n社员：我就是最潮的那个人！ \r\n\r\n（Tips: 社 区 二 十 四 小 时 不 插 电 营 业 中，社 长 在 此 恭 候 大 驾）","order":1,"size":"","templateKey":"text"},{"contentData":"http://img10.static.yhbimg.com/yhb-img01/2016/08/26/13/01bdf4920f5dec13c129a1ddaa6aaeef20.jpg?imageView/{mode}/w/{width}/h/{height}","order":2,"size":"750x1067","templateKey":"image"}]
         * browse : 1693429
         * comment : 0
         * createTime : 1472188463478
         * forumCode : 10001
         * forumName : 潮流风向
         * hasPraise : N
         * id : 27553
         * indexTopTime : 0
         * isForumTop : 0
         * isHot : 0
         * isIndexTop : 0
         * postsTitle : Welcome：一起玩YOHO!潮流社区，你就是最潮的那个人！
         * praise : 262
         * praiseUsers : [{"bgPic":"","headIcon":"http://img11.static.yhbimg.com/yhb-img01/2016/07/05/13/017ec560b82c132ab2fdb22f7cf6f42b83.png?imageView/{mode}/w/{width}/h/{height}","nickName":"182****1413","uid":16355263},{"bgPic":"","headIcon":"http://head.static.yhbimg.com/yhb-head/2016/11/29/13/01dc995dc4e2c5c11eff9fa1bef55796b4.407302.jpg?imageView/{mode}/w/{width}/h/{height}","nickName":"阿部_417","uid":24190278},{"bgPic":"","headIcon":"http://img11.static.yhbimg.com/yhb-img01/2016/07/05/13/017ec560b82c132ab2fdb22f7cf6f42b83.png?imageView/{mode}/w/{width}/h/{height}","nickName":"131****2589","uid":22000964}]
         * revieweState : 1
         * revieweTime : 0
         * status : 0
         * updateTime : 0
         */

        private AuthorInfoBean authorInfo;
        private int browse;
        private int comment;
        private long createTime;
        private int forumCode;
        private String forumName;
        private String hasPraise;
        private int id;
        private int indexTopTime;
        private int isForumTop;
        private int isHot;
        private int isIndexTop;
        private String postsTitle;
        private int praise;
        private int revieweState;
        private int revieweTime;
        private int status;
        private int updateTime;
        private List<BlocksBean> blocks;
        private List<PraiseUsersBean> praiseUsers;

        public AuthorInfoBean getAuthorInfo() {
            return authorInfo;
        }

        public void setAuthorInfo(AuthorInfoBean authorInfo) {
            this.authorInfo = authorInfo;
        }

        public int getBrowse() {
            return browse;
        }

        public void setBrowse(int browse) {
            this.browse = browse;
        }

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getForumCode() {
            return forumCode;
        }

        public void setForumCode(int forumCode) {
            this.forumCode = forumCode;
        }

        public String getForumName() {
            return forumName;
        }

        public void setForumName(String forumName) {
            this.forumName = forumName;
        }

        public String getHasPraise() {
            return hasPraise;
        }

        public void setHasPraise(String hasPraise) {
            this.hasPraise = hasPraise;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIndexTopTime() {
            return indexTopTime;
        }

        public void setIndexTopTime(int indexTopTime) {
            this.indexTopTime = indexTopTime;
        }

        public int getIsForumTop() {
            return isForumTop;
        }

        public void setIsForumTop(int isForumTop) {
            this.isForumTop = isForumTop;
        }

        public int getIsHot() {
            return isHot;
        }

        public void setIsHot(int isHot) {
            this.isHot = isHot;
        }

        public int getIsIndexTop() {
            return isIndexTop;
        }

        public void setIsIndexTop(int isIndexTop) {
            this.isIndexTop = isIndexTop;
        }

        public String getPostsTitle() {
            return postsTitle;
        }

        public void setPostsTitle(String postsTitle) {
            this.postsTitle = postsTitle;
        }

        public int getPraise() {
            return praise;
        }

        public void setPraise(int praise) {
            this.praise = praise;
        }

        public int getRevieweState() {
            return revieweState;
        }

        public void setRevieweState(int revieweState) {
            this.revieweState = revieweState;
        }

        public int getRevieweTime() {
            return revieweTime;
        }

        public void setRevieweTime(int revieweTime) {
            this.revieweTime = revieweTime;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(int updateTime) {
            this.updateTime = updateTime;
        }

        public List<BlocksBean> getBlocks() {
            return blocks;
        }

        public void setBlocks(List<BlocksBean> blocks) {
            this.blocks = blocks;
        }

        public List<PraiseUsersBean> getPraiseUsers() {
            return praiseUsers;
        }

        public void setPraiseUsers(List<PraiseUsersBean> praiseUsers) {
            this.praiseUsers = praiseUsers;
        }

        public static class AuthorInfoBean {
            /**
             * bgPic :
             * headIcon : http://head.static.yhbimg.com/yhb-head/2016/8/26/12/012c2770697a5fbb123c229dad8238f48c.975577.jpg?imageView/{mode}/w/{width}/h/{height}
             * nickName : Yo君
             * signature : YOHO!BUY饮水机管理员
             * uid : 7950645
             */

            private String bgPic;
            private String headIcon;
            private String nickName;
            private String signature;
            private int uid;

            public String getBgPic() {
                return bgPic;
            }

            public void setBgPic(String bgPic) {
                this.bgPic = bgPic;
            }

            public String getHeadIcon() {
                return headIcon;
            }

            public void setHeadIcon(String headIcon) {
                this.headIcon = headIcon;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }
        }

        public static class BlocksBean {
            /**
             * contentData : 之前玩ins的时候发现朋友圈之中只有我一个人在玩，便迎来了走在冷风中的冻感。。。。。。一个人的社交网络实在是太无聊了好嘛！！！！ ！

             想成为网红？
             想结交潮人朋友？
             想走进潮流圈？
             觉得自己平时的穿搭审美很孤独？
             根本没有人懂？
             想获取最新潮流资讯？
             想分享时下最热潮流单品？
             想买潮品又不知道什么地方可以买？
             想参加大型潮流活动？
             想fo紧余文乐？
             想要潮到炸？？？
             .
             .
             .
             móu   měn   teí
             没      问     题

             只要一个YOHO!潮流社区，就可以满足你以上全部要求！

             我们的社区验证码↓↓↓
             社长：玩YOHO!潮流社区！
             社员：我就是最潮的那个人！

             （Tips: 社 区 二 十 四 小 时 不 插 电 营 业 中，社 长 在 此 恭 候 大 驾）
             * order : 1
             * size :
             * templateKey : text
             */

            private String contentData;
            private int order;
            private String size;
            private String templateKey;

            public String getContentData() {
                return contentData;
            }

            public void setContentData(String contentData) {
                this.contentData = contentData;
            }

            public int getOrder() {
                return order;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public String getSize() {
                return size;
            }

            public void setSize(String size) {
                this.size = size;
            }

            public String getTemplateKey() {
                return templateKey;
            }

            public void setTemplateKey(String templateKey) {
                this.templateKey = templateKey;
            }
        }

        public static class PraiseUsersBean {
            /**
             * bgPic :
             * headIcon : http://img11.static.yhbimg.com/yhb-img01/2016/07/05/13/017ec560b82c132ab2fdb22f7cf6f42b83.png?imageView/{mode}/w/{width}/h/{height}
             * nickName : 182****1413
             * uid : 16355263
             */

            private String bgPic;
            private String headIcon;
            private String nickName;
            private int uid;

            public String getBgPic() {
                return bgPic;
            }

            public void setBgPic(String bgPic) {
                this.bgPic = bgPic;
            }

            public String getHeadIcon() {
                return headIcon;
            }

            public void setHeadIcon(String headIcon) {
                this.headIcon = headIcon;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public int getUid() {
                return uid;
            }

            public void setUid(int uid) {
                this.uid = uid;
            }
        }
    }
}
