package com.mario219.grability.viewFactory;

/**
 * Created by mario219 on 25/01/17.
 */

public class SingleFeedModel {

    private int id;
    private String title;
    private String displayName;
    private String description;

    public SingleFeedModel(){

    }

    public SingleFeedModel(String title, String displayName, String description){
        this.title = title;
        this.displayName = displayName;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

