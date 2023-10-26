package com.example.componentstest.entity;

import com.example.componentstest.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Music {

    private int musicId;
    private String musicName;

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }

    public Music(int musicId, String musicName) {
        this.musicId = musicId;
        this.musicName = musicName;
    }

    public static List<Music> getDefaultMusic(){
        List<Music> defaultMusic = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Music music = getRandomMusic();
            defaultMusic.add(music);
        }
        return defaultMusic;
    }

    public static Music getRandomMusic(){
        Random random = new Random();
        int musicIdIndex = random.nextInt(24) + 1;
        int musicNameIndex = random.nextInt(24) + 1;
        return new Music(imageArray[musicIdIndex],nameArray[musicNameIndex]);
    }

    @Override
    public String toString() {
        return "Music{" +
                "musicId=" + musicId +
                ", musicName='" + musicName + '\'' +
                '}';
    }

    private static final int[] imageArray = {R.drawable.thumb01,R.drawable.thumb02,R.drawable.thumb03,R.drawable.thumb04,R.drawable.thumb05,
            R.drawable.thumb06,R.drawable.thumb07,R.drawable.thumb08,R.drawable.thumb09,R.drawable.thumb10,R.drawable.thumb11,
            R.drawable.thumb12,R.drawable.thumb13,R.drawable.thumb14,R.drawable.thumb15,R.drawable.thumb16,R.drawable.thumb17,
            R.drawable.thumb18,R.drawable.thumb19,R.drawable.thumb20,R.drawable.thumb21,R.drawable.thumb22,R.drawable.thumb23,
            R.drawable.thumb24,R.drawable.thumb25};

    private static final String[] nameArray = {"Agent 51 - Bad Times (Album Version)",
            "Agent 51 - Been So Long (Album Version)",
            "Ariana Grande、MAC MILLER - Into You (Alex Ghenea Remix)",
            "Avril Lavigne - 17",
            "BEYOND - Amani (粤语)",
            "Bloc Party - Banquet",
            "Bon Jovi - Because We Can",
            "Coldplay - A Message (2005)",
            "Dream Theater - Another Day",
            "Ester drang - All The Feeling",
            "FLOW (フロウ) - 7-seven- (TVアニメ 「七つの大罪」 エンディングテーマ)",
            "Glen Check - 60's Cardin",
            "Green Day - 21 Guns (《变形金刚2》电影插曲)",
            "Groove Coverage - She.flac",
            "Halestorm - Bad Girl's World",
            "James Blunt - You're Beautiful - 2006重制版",
            "Jeff Buckley - Back In N.Y.C.",
            "Joshua Radin - Beautiful Day",
            "Lady Gaga - Poker Face",
            "Matt Nathanson - All We Are",
            "Miracle Mile - Beginagain",
            "Nickelback - Animals",
            "Oh Sunshine - beautiful (japanese ver.)",
            "OneRepublic - All The Right Moves",
            "OneRepublic - Apologize",
            "Paramore - Ain't It Fun",
            "Rise Against - Behind Closed Doors",
            "Scorpions - Always Somewhere",
            "Skid Row - 18 And Life",
            "Suede - Beautiful Ones",
            "TFBOYS - 开学第一课 (《开学第一课》节目主题曲)",
            "TFBOYS - 剩下的盛夏",
            "The Cranberries - Animal instinct",
            "The Dunwells - Animal",
            "The Fray - Be Still",
            "The Pretty Reckless - Just Tonight",
            "The Rolling Stones - (I Can't Get No) Satisfaction",
            "This Is Love-周兴哲"};
}
