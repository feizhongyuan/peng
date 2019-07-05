package com.maqueezu.el.pojo;

/**
 * Created by fei .
 * Created by Date 2019/6/4 14:15
 */

public class UserBean {

    /**
     * result : 1
     * message : null
     * data : {"face":"https://www.maqueezu.com/images/face.png","username":"15002274757","level":"注册用户"}
     */

    private int result;
    private Object message;
    private DataBean data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * face : https://www.maqueezu.com/images/face.png
         * username : 15002274757
         * level : 注册用户
         */

        private String face;
        private String username;
        private String level;

        public String getFace() {
            return face;
        }

        public void setFace(String face) {
            this.face = face;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }
}
