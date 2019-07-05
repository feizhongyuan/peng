package com.maqueezu.el.pojo;

/**
 * 这是模拟登录时 返回用户对象的模型
 *
 * UserModel 最终的样子就是  {ResultCode:"1",Message:"请求成功",ResultData:{id:"123",name:"张三",age:"18"}}
 *
 */
public class UserModel extends DefApiModel<UserModel.User> {


    public static class User{

        private String id;
        private String name;
        private String age;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }
    }
}
