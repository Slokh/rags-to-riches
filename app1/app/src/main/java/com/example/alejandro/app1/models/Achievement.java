package com.example.alejandro.app1.models;

/**
 * Written by: Kartik Patel
 *
 * Achievement class to store a particular achievement in the pair of
 * Achievement name and the user's progress on that achievement
 */

public class Achievement {

    String name;
    String progress;

    /**
     *
     * @param name      achievement name
     * @param progress  achievement progress
     */
    public Achievement(String name, String progress) {
        this.name = name;
        this.progress = progress;
    }

    /**
     *
     * @return  the achievement name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return  the progress on the achievement
     */
    public String getProgress() {
        return progress;
    }

}
