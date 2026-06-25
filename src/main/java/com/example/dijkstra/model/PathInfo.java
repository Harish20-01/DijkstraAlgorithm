package com.example.dijkstra.model;

public class PathInfo {

    private String path;
    private Integer weight;

    public PathInfo() {
    }

    public PathInfo(String path, Integer weight) {
        this.path = path;
        this.weight = weight;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "{path='" + path + "', weight=" + weight + "}";
    }
}