package com.rtech.wallify;

public class ImageData {
    int id;
    String link;
    String PreviewLink;
    int height;
    int width;
    public ImageData(int id,String previewLink,String link,int height,int width)
    {    this.id=id;
        this.PreviewLink=previewLink;
        this.link=link;
        this.height=height;
        this.width=width;
    }
}
