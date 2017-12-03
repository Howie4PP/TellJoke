package com.example.shenhaichen.javajoke;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jokes {


    private List<String> jokeList = new ArrayList<String>() {{
        add("This is my first joke");
        add("This is my second joke");
        add("This is my third joke");
        add("All joke is fun? Thank you!");
    }};


    public String getJoke(){
        Random random = new Random();
        int num = random.nextInt(jokeList.size()-1);
        return jokeList.get(num);
    }
}
