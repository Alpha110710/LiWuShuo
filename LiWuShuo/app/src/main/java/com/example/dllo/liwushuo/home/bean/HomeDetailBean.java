package com.example.dllo.liwushuo.home.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dllo on 16/6/2.
 */
public class HomeDetailBean {

    /**
     * code : 200
     * message : OK
     */

    private int code;
    /**
     * ad_monitors : null
     * comments_count : 6
     * content_html :

     * content_type : 1
     * content_url : http://www.liwushuo.com/posts/1043751/content
     * cover_image_url : http://img01.liwushuo.com/image/160602/04dxoyiik.jpg-w720
     * cover_webp_url : http://img01.liwushuo.com/image/160602/04dxoyiik.jpg?imageView2/2/w/720/q/85/format/webp
     * created_at : 1464836400
     * editor_id : 1045
     * feature_list : []
     * id : 1043751
     * item_ad_monitors : {"1009393":[],"1033995":[],"1040954":[],"1041391":[],"1053380":[],"1057864":[],"1057865":[],"1057866":[],"1057867":[],"1057868":[]}
     * label_ids : []
     * liked : false
     * likes_count : 3990
     * published_at : 1464836400
     * share_msg : 分享自@礼物说—90 后生活方式指南
     * shares_count : 368
     * short_title :
     * status : 0
     * template :
     * title : 第5期 | 邂逅清新夏季，享受香氛宠爱
     * updated_at : 1464765661
     * url : http://www.liwushuo.com/posts/1043751
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
        private Object ad_monitors;
        private int comments_count;
        private String content_html;
        private int content_type;
        private String content_url;
        private String cover_image_url;
        private String cover_webp_url;
        private int created_at;
        private int editor_id;
        private int id;
        private ItemAdMonitorsBean item_ad_monitors;
        private boolean liked;
        private int likes_count;
        private int published_at;
        private String share_msg;
        private int shares_count;
        private String short_title;
        private int status;
        private String template;
        private String title;
        private int updated_at;
        private String url;
        private List<?> feature_list;
        private List<?> label_ids;

        public Object getAd_monitors() {
            return ad_monitors;
        }

        public void setAd_monitors(Object ad_monitors) {
            this.ad_monitors = ad_monitors;
        }

        public int getComments_count() {
            return comments_count;
        }

        public void setComments_count(int comments_count) {
            this.comments_count = comments_count;
        }

        public String getContent_html() {
            return content_html;
        }

        public void setContent_html(String content_html) {
            this.content_html = content_html;
        }

        public int getContent_type() {
            return content_type;
        }

        public void setContent_type(int content_type) {
            this.content_type = content_type;
        }

        public String getContent_url() {
            return content_url;
        }

        public void setContent_url(String content_url) {
            this.content_url = content_url;
        }

        public String getCover_image_url() {
            return cover_image_url;
        }

        public void setCover_image_url(String cover_image_url) {
            this.cover_image_url = cover_image_url;
        }

        public String getCover_webp_url() {
            return cover_webp_url;
        }

        public void setCover_webp_url(String cover_webp_url) {
            this.cover_webp_url = cover_webp_url;
        }

        public int getCreated_at() {
            return created_at;
        }

        public void setCreated_at(int created_at) {
            this.created_at = created_at;
        }

        public int getEditor_id() {
            return editor_id;
        }

        public void setEditor_id(int editor_id) {
            this.editor_id = editor_id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public ItemAdMonitorsBean getItem_ad_monitors() {
            return item_ad_monitors;
        }

        public void setItem_ad_monitors(ItemAdMonitorsBean item_ad_monitors) {
            this.item_ad_monitors = item_ad_monitors;
        }

        public boolean isLiked() {
            return liked;
        }

        public void setLiked(boolean liked) {
            this.liked = liked;
        }

        public int getLikes_count() {
            return likes_count;
        }

        public void setLikes_count(int likes_count) {
            this.likes_count = likes_count;
        }

        public int getPublished_at() {
            return published_at;
        }

        public void setPublished_at(int published_at) {
            this.published_at = published_at;
        }

        public String getShare_msg() {
            return share_msg;
        }

        public void setShare_msg(String share_msg) {
            this.share_msg = share_msg;
        }

        public int getShares_count() {
            return shares_count;
        }

        public void setShares_count(int shares_count) {
            this.shares_count = shares_count;
        }

        public String getShort_title() {
            return short_title;
        }

        public void setShort_title(String short_title) {
            this.short_title = short_title;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(int updated_at) {
            this.updated_at = updated_at;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public List<?> getFeature_list() {
            return feature_list;
        }

        public void setFeature_list(List<?> feature_list) {
            this.feature_list = feature_list;
        }

        public List<?> getLabel_ids() {
            return label_ids;
        }

        public void setLabel_ids(List<?> label_ids) {
            this.label_ids = label_ids;
        }

        public static class ItemAdMonitorsBean {
            @SerializedName("1009393")
            private List<?> value1009393;
            @SerializedName("1033995")
            private List<?> value1033995;
            @SerializedName("1040954")
            private List<?> value1040954;
            @SerializedName("1041391")
            private List<?> value1041391;
            @SerializedName("1053380")
            private List<?> value1053380;
            @SerializedName("1057864")
            private List<?> value1057864;
            @SerializedName("1057865")
            private List<?> value1057865;
            @SerializedName("1057866")
            private List<?> value1057866;
            @SerializedName("1057867")
            private List<?> value1057867;
            @SerializedName("1057868")
            private List<?> value1057868;

            public List<?> getValue1009393() {
                return value1009393;
            }

            public void setValue1009393(List<?> value1009393) {
                this.value1009393 = value1009393;
            }

            public List<?> getValue1033995() {
                return value1033995;
            }

            public void setValue1033995(List<?> value1033995) {
                this.value1033995 = value1033995;
            }

            public List<?> getValue1040954() {
                return value1040954;
            }

            public void setValue1040954(List<?> value1040954) {
                this.value1040954 = value1040954;
            }

            public List<?> getValue1041391() {
                return value1041391;
            }

            public void setValue1041391(List<?> value1041391) {
                this.value1041391 = value1041391;
            }

            public List<?> getValue1053380() {
                return value1053380;
            }

            public void setValue1053380(List<?> value1053380) {
                this.value1053380 = value1053380;
            }

            public List<?> getValue1057864() {
                return value1057864;
            }

            public void setValue1057864(List<?> value1057864) {
                this.value1057864 = value1057864;
            }

            public List<?> getValue1057865() {
                return value1057865;
            }

            public void setValue1057865(List<?> value1057865) {
                this.value1057865 = value1057865;
            }

            public List<?> getValue1057866() {
                return value1057866;
            }

            public void setValue1057866(List<?> value1057866) {
                this.value1057866 = value1057866;
            }

            public List<?> getValue1057867() {
                return value1057867;
            }

            public void setValue1057867(List<?> value1057867) {
                this.value1057867 = value1057867;
            }

            public List<?> getValue1057868() {
                return value1057868;
            }

            public void setValue1057868(List<?> value1057868) {
                this.value1057868 = value1057868;
            }
        }
    }
}
