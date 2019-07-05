package com.maqueezu.el.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/5/8 0008.
 *
 * 演示加载集合数据模型
 */
public class LoadModel extends DefApiModel<List<LoadModel.Data>> {


    public static class Data implements Serializable{
        private String str;

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }
    }
}
