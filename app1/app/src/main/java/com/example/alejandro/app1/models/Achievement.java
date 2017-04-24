package com.example.alejandro.app1.models;

import static android.R.attr.description;
import static android.R.attr.id;
import static com.example.alejandro.app1.R.id.email;
import static com.example.alejandro.app1.R.id.username;

/**
 * Created by Kartik on 4/23/2017.
 */

public class Achievement {

    String name;
    String progress;

    public Achievement(String name, String progress) {
        this.name = name;
        this.progress = progress;
    }

    public String getName() {
        return name;
    }

    public String getProgress() {
        return progress;
    }

    public String toString() {
        return name + " // " + description + " // " + progress;
    }
}
