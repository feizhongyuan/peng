package com.maqueezu.el.ui.view.doublelistview;

import java.util.List;

/**
 * Created by fei .
 * Created by Date 2019/7/9 17:20
 */

public class Bean {

        private String title;
        private List<DataBean> dataList;

    public Bean(String title, List<DataBean> dataList) {
        this.title = title;
        this.dataList = dataList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDataList(List<DataBean> dataList) {
        this.dataList = dataList;
    }

    public String getTitle() {

        return title;
    }

    public List<DataBean> getDataList() {
        return dataList;
    }

    public static class DataBean{
            private String name;
            private String price;
            private String url;

            public DataBean(String name, String price, String url) {
                this.name = name;
                this.price = price;
                this.url = url;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getName() {

                return name;
            }

            public String getPrice() {
                return price;
            }

            public String getUrl() {
                return url;
            }
        }

}
