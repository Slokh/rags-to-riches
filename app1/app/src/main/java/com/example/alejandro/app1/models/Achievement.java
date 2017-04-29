package com.example.alejandro.app1.models;

import static android.R.attr.description;
import static android.R.attr.id;
import static com.example.alejandro.app1.R.id.email;
import static com.example.alejandro.app1.R.id.username;

/**
 * Achievement class to store a particular achievement in the pair of
 * Achievement name and the user's progress on that achievement
 * Created by Kartik on 4/23/2017.
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
