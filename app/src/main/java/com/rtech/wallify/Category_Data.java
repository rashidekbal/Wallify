package com.rtech.wallify;

public class Category_Data {
    int id;
    String api;
    String image_link;
    String desc;
    public Category_Data(
            int id,String api,String image_link,String desc
    ){
        this.image_link=image_link;
        this.id=id;
        this.api=api;
        this.desc=desc;

    }
}
