package com.example.trang.note.note;

/**
 * Created by Trang on 5/4/2017.
 */

public class Note {
    private int id;
    private String content;
    private String color;
    private String date;

    public Note(int id, String content, String color, String date) {
        this.id = id;
        this.content = content;
        this.color = color;
        this.date = date;
    }

    public Note(String content, String color, String date) {
        this.content = content;
        this.color = color;
        this.date = date;
    }

    public Note(String date) {
        this.date = date;
    }

    public Note() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
