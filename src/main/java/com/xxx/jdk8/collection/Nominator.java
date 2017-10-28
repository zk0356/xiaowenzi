package com.xxx.jdk8.collection;

/**
 * Created by xiaowenzi on 2017/9/19.
 */
public class Nominator implements Comparable<Nominator> {
    private String name;
    private int vote;

    public Nominator(String name, int vote) {
        this.name = name;
        this.vote = vote;
    }

    public String getName() {
        return name;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        return "Nominator{" +
                "name='" + name + '\'' +
                ", vote=" + vote +
                '}';
    }

    @Override
    public int compareTo(Nominator o) {
        return this.getVote() - o.getVote();
    }
}
