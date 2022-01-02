package com.example.mynewsapp.topheadlines;

public class TopHeadlinesModel{
    String title,desp,source,Link;

    public TopHeadlinesModel(String title, String desp, String source,String Link) {
        this.title = title;
        this.desp = desp;
        this.source = source;
        this.Link=Link;
    }

    public String getTitle() {
        return title;
    }

    public String getDesp() {
        return desp;
    }

    public String getSource() {
        return source;
    }
    public String getLink(){
        return Link;
    }
}
