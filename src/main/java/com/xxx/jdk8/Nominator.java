package com.xxx.jdk8;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Nominator nominator = (Nominator) o;

        return name.equals(nominator.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int compareTo(Nominator o) {
        return this.vote - o.getVote();
    }
}
