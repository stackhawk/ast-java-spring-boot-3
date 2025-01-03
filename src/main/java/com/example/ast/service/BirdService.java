package com.example.ast.service;

import com.example.ast.model.Bird;
import com.example.ast.model.Eagle;
import com.example.ast.model.Key;
import com.example.ast.model.Penguin;
import com.example.ast.model.Song;
import com.example.ast.model.Sparrow;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class BirdService {

    public static final List<Bird> birds = new ArrayList<>(
            Arrays.asList(
                    new Sparrow("Sparrow", 0.25, 0.02),
                    new Eagle("Eagle", 2.0, 6.0),
                    new Penguin("Penguin", 0.75, 15.0)
            )
    );

    public static final List<Song> songs = new ArrayList<>(
            Arrays.asList(
                    new Song(Key.C_MAJOR, "Song 1", birds.get(0), 2021),
                    new Song(Key.D_SHARP_MAJOR, "Song 2", birds.get(1), 2020),
                    new Song(Key.A_MINOR, "Song 3", birds.get(2), 2019),
                    new Song(Key.G_MAJOR, "Song 4", birds.get(0), 2018),
                    new Song(Key.F_SHARP_MINOR, "Song 5", birds.get(1), 2017),
                    new Song(Key.B_MAJOR, "Song 6", birds.get(2), 2016),
                    new Song(Key.E_MINOR, "Song 7", birds.get(0), 2015),
                    new Song(Key.C_SHARP_MINOR, "Song 8", birds.get(1), 2014),
                    new Song(Key.D_MAJOR, "Song 9", birds.get(2), 2013),
                    new Song(Key.F_MAJOR, "Song 10", birds.get(0), 2012),
                    new Song(Key.A_SHARP_MINOR, "Song 11", birds.get(1), 2011),
                    new Song(Key.G_SHARP_MAJOR, "Song 12", birds.get(2), 2010)
            )
    );

    public List<Bird> getBirds() {
        return birds;
    }

    public List<Song> getSongs() {
        return songs;
    }
}
