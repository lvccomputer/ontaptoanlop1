package com.dev.lvc.math1.utils;

import com.dev.lvc.math1.R;

import java.util.Random;

public class SoundUtils {

    private static int[] SOUND_TRUE = {R.raw.dung1,R.raw.dung2,R.raw.dung3,R.raw.dung4,R.raw.dung5};

    private static int[] SOUND_FALSE = {R.raw.sai1,R.raw.sai2,R.raw.sai3,R.raw.sai4,R.raw.sai5};


    public static int getRandomSoundTrue(){

        int soundIndex;
        int[] sound = SOUND_TRUE;

        Random random = new Random();
        soundIndex = random.nextInt(sound.length);

        return sound[soundIndex];
    }
    public static int getRandomSoundFalse(){

        int soundIndex;
        int[] sound = SOUND_FALSE;

        Random random = new Random();
        soundIndex = random.nextInt(sound.length);

        return sound[soundIndex];
    }
}
