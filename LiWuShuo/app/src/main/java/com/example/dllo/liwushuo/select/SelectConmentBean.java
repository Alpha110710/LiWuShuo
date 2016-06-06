package com.example.dllo.liwushuo.select;

import java.util.List;

/**
 * Created by dllo on 16/6/6.
 */
public class SelectConmentBean {

    /**
     * code : 200
     * data : {"comments":[{"content":"我，哈哈","created_at":1458295829,"id":444947,"item_id":1001302,"replied_comment":{"content":"有人和我一样就想买那个大雄么","created_at":1433683590,"id":44213,"item_id":1001302,"reply_to_id":null,"show":true,"status":1,"user_id":2386954},"replied_user":{"avatar_url":"http://img03.liwushuo.com/avatar/20150604/f4ypuufta_i.png-w180","can_mobile_login":false,"guest_uuid":null,"id":2386954,"nickname":"小赵","role":0},"reply_to_id":44213,"show":true,"status":1,"user":{"avatar_url":"http://img02.liwushuo.com/avatar/20150304/xde415n8i_i.png-w180","can_mobile_login":false,"guest_uuid":null,"id":1477571,"nickname":"Assassen","role":0}},{"content":"我想买，哈哈","created_at":1435461275,"id":57241,"item_id":1001302,"replied_comment":{"content":"有人和我一样就想买那个大雄么","created_at":1433683590,"id":44213,"item_id":1001302,"reply_to_id":null,"show":true,"status":1,"user_id":2386954},"replied_user":{"avatar_url":"http://img01.liwushuo.com/avatar/20150604/f4ypuufta_i.png-w180","can_mobile_login":false,"guest_uuid":null,"id":2386954,"nickname":"小赵","role":0},"reply_to_id":44213,"show":true,"status":1,"user":{"avatar_url":"http://img02.liwushuo.com/avatar/150622/5373f4d57_a.png-w180","can_mobile_login":false,"guest_uuid":null,"id":2522804,"nickname":"小米","role":0}},{"content":"哈哈哈必须有！","created_at":1433731049,"id":44470,"item_id":1001302,"replied_comment":{"content":"有人和我一样就想买那个大雄么","created_at":1433683590,"id":44213,"item_id":1001302,"reply_to_id":null,"show":true,"status":1,"user_id":2386954},"replied_user":{"avatar_url":"http://img01.liwushuo.com/avatar/20150604/f4ypuufta_i.png-w180","can_mobile_login":false,"guest_uuid":null,"id":2386954,"nickname":"小赵","role":0},"reply_to_id":44213,"show":true,"status":1,"user":{"avatar_url":"http://img01.liwushuo.com/avatar/20150513/2zj9ommre_i.png-w180","can_mobile_login":false,"guest_uuid":null,"id":2249501,"nickname":"到最后我还是想做一棵会开花的树","role":0}},{"content":"有人和我一样就想买那个大雄么","created_at":1433683590,"id":44213,"item_id":1001302,"reply_to_id":null,"show":true,"status":1,"user":{"avatar_url":"http://img02.liwushuo.com/avatar/20150604/f4ypuufta_i.png-w180","can_mobile_login":false,"guest_uuid":null,"id":2386954,"nickname":"小赵","role":0}}],"paging":{"next_url":"http://api.liwushuo.com/v2/items/1001302/comments?limit=20&offset=20"}}
     * message : OK
     */

    private int code;
    /**
     * comments : [{"content":"我，哈哈","created_at":1458295829,"id":444947,"item_id":1001302,"replied_comment":{"content":"有人和我一样就想买那个大雄么","created_at":1433683590,"id":44213,"item_id":1001302,"reply_to_id":null,"show":true,"status":1,"user_id":2386954},"replied_user":{"avatar_url":"http://img03.liwushuo.com/avatar/20150604/f4ypuufta_i.png-w180","can_mobile_login":false,"guest_uuid":null,"id":2386954,"nickname":"小赵","role":0},"reply_to_id":44213,"show":true,"status":1,"user":{"avatar_url":"http://img02.liwushuo.com/avatar/20150304/xde415n8i_i.png-w180","can_mobile_login":false,"guest_uuid":null,"id":1477571,"nickname":"Assassen","role":0}},{"content":"我想买，哈哈","created_at":1435461275,"id":57241,"item_id":1001302,"replied_comment":{"content":"有人和我一样就想买那个大雄么","created_at":1433683590,"id":44213,"item_id":1001302,"reply_to_id":null,"show":true,"status":1,"user_id":2386954},"replied_user":{"avatar_url":"http://img01.liwushuo.com/avatar/20150604/f4ypuufta_i.png-w180","can_mobile_login":false,"guest_uuid":null,"id":2386954,"nickname":"小赵","role":0},"reply_to_id":44213,"show":true,"status":1,"user":{"avatar_url":"http://img02.liwushuo.com/avatar/150622/5373f4d57_a.png-w180","can_mobile_login":false,"guest_uuid":null,"id":2522804,"nickname":"小米","role":0}},{"content":"哈哈哈必须有！","created_at":1433731049,"id":44470,"item_id":1001302,"replied_comment":{"content":"有人和我一样就想买那个大雄么","created_at":1433683590,"id":44213,"item_id":1001302,"reply_to_id":null,"show":true,"status":1,"user_id":2386954},"replied_user":{"avatar_url":"http://img01.liwushuo.com/avatar/20150604/f4ypuufta_i.png-w180","can_mobile_login":false,"guest_uuid":null,"id":2386954,"nickname":"小赵","role":0},"reply_to_id":44213,"show":true,"status":1,"user":{"avatar_url":"http://img01.liwushuo.com/avatar/20150513/2zj9ommre_i.png-w180","can_mobile_login":false,"guest_uuid":null,"id":2249501,"nickname":"到最后我还是想做一棵会开花的树","role":0}},{"content":"有人和我一样就想买那个大雄么","created_at":1433683590,"id":44213,"item_id":1001302,"reply_to_id":null,"show":true,"status":1,"user":{"avatar_url":"http://img02.liwushuo.com/avatar/20150604/f4ypuufta_i.png-w180","can_mobile_login":false,"guest_uuid":null,"id":2386954,"nickname":"小赵","role":0}}]
     * paging : {"next_url":"http://api.liwushuo.com/v2/items/1001302/comments?limit=20&offset=20"}
     */

    private DataBean data;
    private String message;

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
         * next_url : http://api.liwushuo.com/v2/items/1001302/comments?limit=20&offset=20
         */

        private PagingBean paging;
        /**
         * content : 我，哈哈
         * created_at : 1458295829
         * id : 444947
         * item_id : 1001302
         * replied_comment : {"content":"有人和我一样就想买那个大雄么","created_at":1433683590,"id":44213,"item_id":1001302,"reply_to_id":null,"show":true,"status":1,"user_id":2386954}
         * replied_user : {"avatar_url":"http://img03.liwushuo.com/avatar/20150604/f4ypuufta_i.png-w180","can_mobile_login":false,"guest_uuid":null,"id":2386954,"nickname":"小赵","role":0}
         * reply_to_id : 44213
         * show : true
         * status : 1
         * user : {"avatar_url":"http://img02.liwushuo.com/avatar/20150304/xde415n8i_i.png-w180","can_mobile_login":false,"guest_uuid":null,"id":1477571,"nickname":"Assassen","role":0}
         */

        private List<CommentsBean> comments;

        public PagingBean getPaging() {
            return paging;
        }

        public void setPaging(PagingBean paging) {
            this.paging = paging;
        }

        public List<CommentsBean> getComments() {
            return comments;
        }

        public void setComments(List<CommentsBean> comments) {
            this.comments = comments;
        }

        public static class PagingBean {
            private String next_url;

            public String getNext_url() {
                return next_url;
            }

            public void setNext_url(String next_url) {
                this.next_url = next_url;
            }
        }

        public static class CommentsBean {
            private String content;
            private int created_at;
            private int id;
            private int item_id;
            /**
             * content : 有人和我一样就想买那个大雄么
             * created_at : 1433683590
             * id : 44213
             * item_id : 1001302
             * reply_to_id : null
             * show : true
             * status : 1
             * user_id : 2386954
             */

            private RepliedCommentBean replied_comment;
            /**
             * avatar_url : http://img03.liwushuo.com/avatar/20150604/f4ypuufta_i.png-w180
             * can_mobile_login : false
             * guest_uuid : null
             * id : 2386954
             * nickname : 小赵
             * role : 0
             */

            private RepliedUserBean replied_user;
            private int reply_to_id;
            private boolean show;
            private int status;
            /**
             * avatar_url : http://img02.liwushuo.com/avatar/20150304/xde415n8i_i.png-w180
             * can_mobile_login : false
             * guest_uuid : null
             * id : 1477571
             * nickname : Assassen
             * role : 0
             */

            private UserBean user;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getCreated_at() {
                return created_at;
            }

            public void setCreated_at(int created_at) {
                this.created_at = created_at;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getItem_id() {
                return item_id;
            }

            public void setItem_id(int item_id) {
                this.item_id = item_id;
            }

            public RepliedCommentBean getReplied_comment() {
                return replied_comment;
            }

            public void setReplied_comment(RepliedCommentBean replied_comment) {
                this.replied_comment = replied_comment;
            }

            public RepliedUserBean getReplied_user() {
                return replied_user;
            }

            public void setReplied_user(RepliedUserBean replied_user) {
                this.replied_user = replied_user;
            }

            public int getReply_to_id() {
                return reply_to_id;
            }

            public void setReply_to_id(int reply_to_id) {
                this.reply_to_id = reply_to_id;
            }

            public boolean isShow() {
                return show;
            }

            public void setShow(boolean show) {
                this.show = show;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public static class RepliedCommentBean {
                private String content;
                private int created_at;
                private int id;
                private int item_id;
                private Object reply_to_id;
                private boolean show;
                private int status;
                private int user_id;

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public int getCreated_at() {
                    return created_at;
                }

                public void setCreated_at(int created_at) {
                    this.created_at = created_at;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getItem_id() {
                    return item_id;
                }

                public void setItem_id(int item_id) {
                    this.item_id = item_id;
                }

                public Object getReply_to_id() {
                    return reply_to_id;
                }

                public void setReply_to_id(Object reply_to_id) {
                    this.reply_to_id = reply_to_id;
                }

                public boolean isShow() {
                    return show;
                }

                public void setShow(boolean show) {
                    this.show = show;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }
            }

            public static class RepliedUserBean {
                private String avatar_url;
                private boolean can_mobile_login;
                private Object guest_uuid;
                private int id;
                private String nickname;
                private int role;

                public String getAvatar_url() {
                    return avatar_url;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }

                public boolean isCan_mobile_login() {
                    return can_mobile_login;
                }

                public void setCan_mobile_login(boolean can_mobile_login) {
                    this.can_mobile_login = can_mobile_login;
                }

                public Object getGuest_uuid() {
                    return guest_uuid;
                }

                public void setGuest_uuid(Object guest_uuid) {
                    this.guest_uuid = guest_uuid;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public int getRole() {
                    return role;
                }

                public void setRole(int role) {
                    this.role = role;
                }
            }

            public static class UserBean {
                private String avatar_url;
                private boolean can_mobile_login;
                private Object guest_uuid;
                private int id;
                private String nickname;
                private int role;

                public String getAvatar_url() {
                    return avatar_url;
                }

                public void setAvatar_url(String avatar_url) {
                    this.avatar_url = avatar_url;
                }

                public boolean isCan_mobile_login() {
                    return can_mobile_login;
                }

                public void setCan_mobile_login(boolean can_mobile_login) {
                    this.can_mobile_login = can_mobile_login;
                }

                public Object getGuest_uuid() {
                    return guest_uuid;
                }

                public void setGuest_uuid(Object guest_uuid) {
                    this.guest_uuid = guest_uuid;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getNickname() {
                    return nickname;
                }

                public void setNickname(String nickname) {
                    this.nickname = nickname;
                }

                public int getRole() {
                    return role;
                }

                public void setRole(int role) {
                    this.role = role;
                }
            }
        }
    }
}
