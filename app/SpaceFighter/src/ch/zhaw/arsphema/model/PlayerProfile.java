package ch.zhaw.arsphema.model;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Klasse für profile und einstellungen eines spielers
 */
public class PlayerProfile implements Serializable {

    private ArrayList<HighscoreEntry> highscore;
    private String playerName;
    private float soundVolume;
    private float musicVolume;

    public PlayerProfile() {
        highscore = new ArrayList<HighscoreEntry>();
        playerName = "";
        musicVolume = 1f;
        soundVolume = 1f;
    }

    public ArrayList<HighscoreEntry> getHighscore() {
        return highscore;
    }

    /**
     * macht einen neuen highscore eintrag falls dieser gut genug ist
     * @param newEntry neuer highscore eintrag
     */
    public void addHighscoreEntry(HighscoreEntry newEntry) {
        boolean entryAllowed = highscore.size() < 10;
        for (HighscoreEntry highscoreEntry : highscore) {
            if (entryAllowed || highscoreEntry.getScore() < newEntry.getScore()) {
                entryAllowed = true;
                break;
            }
        }

        if (entryAllowed) {
            highscore.add(newEntry);
            Collections.sort(highscore);
            if (highscore.size() > 10) {
                highscore.remove(10);
            }
        }
    }

    /**
     * gibt minimale punkteanzahl zurueck
     */
    public int getMinimalScore() {
        return highscore.size() < 10 ? 0 : highscore.get(highscore.size() - 1).getScore() + 1;
    }

    /**
     * gibt den spielernamen zurueck
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * setzt den neuen spieler name
     * @param playerName den neuen spielername
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * gibt die sfx lautstaerke zurueck
     * @return soundVolume die lautstaerke
     */
    public float getSoundVolume() {
        return soundVolume;
    }

    /**
     * setzt die sfx lautstaerke
     */
    public void setSoundVolume(float soundVolume) {
        this.soundVolume = soundVolume;
    }

    /**
     * gibt die musik lautstaerke zurueck
     * @return musicVolume die lautstaerke
     */
    public float getMusicVolume() {
        return musicVolume;
    }

    /**
     * setzt die musik lautstaerke
     * @param musicVolume die neue lautstaerke
     */
    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
    }

    /**
     * schreibt ein player profil in das jason file
     * @param jason das jason file
     */
    @Override
    public void write(Json json) {
        json.writeValue("highscore", highscore);
        json.writeValue("playerName", playerName);
        json.writeValue("musicVolume", musicVolume);
        json.writeValue("soundVolume", soundVolume);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void read(Json json, OrderedMap<String, Object> jsonData) {
        highscore = json.readValue("highscore", ArrayList.class, jsonData);
        playerName = json.readValue("playerName", String.class, jsonData);
        musicVolume = json.readValue("musicVolume", Float.class, jsonData);
        soundVolume = json.readValue("soundVolume", Float.class, jsonData);
    }
}
