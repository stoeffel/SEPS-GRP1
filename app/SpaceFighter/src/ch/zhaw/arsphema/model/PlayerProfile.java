package ch.zhaw.arsphema.model;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.OrderedMap;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Klasse welche das gespeicherte Spielerprofil repräsentiert.
 *
 * @author spoerriweb
 */
public class PlayerProfile implements Serializable {

    private ArrayList<HighscoreEntry> highscore;
    private String playerName;
    private float soundVolume;
    private float musicVolume;

    /**
     * Konstruktor
     * Setzt die Initialwerte eines neuen Profils
     */
    public PlayerProfile() {
        highscore = new ArrayList<HighscoreEntry>();
        playerName = "";
        musicVolume = 1f;
        soundVolume = 1f;
    }

    /**
     * @return Liste mit den im Profil vorhandenen Highscores
     */
    public ArrayList<HighscoreEntry> getHighscore() {
        return highscore;
    }

    /**
     * Erstellt einen neuen Eintag in der Highscore, sofern dieser eine genügen hohe Punktzahl enthält.
     *
     * @param newEntry Der neue Eintrag
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
     * Gibt die minimal notwendige Punktzahl, welche für einen neuen Highscore Eintrag benötigt wird zurück.
     */
    public int getMinimalScore() {
        return highscore.size() < 10 ? 0 : highscore.get(highscore.size() - 1).getScore() + 1;
    }

    /**
     * Gibt den Defaultnamen des Spielers zurück.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Setzt einen neuen Spielernamen
     *
     * @param playerName Der neue Defaultspielername
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Gibt die gesetzte SFX Lautstaerke zurueck
     *
     * @return soundVolume Die Lautstaerke
     */
    public float getSoundVolume() {
        return soundVolume;
    }

    /**
     * Setzt die SFX Lautstaerke
     */
    public void setSoundVolume(float soundVolume) {
        this.soundVolume = soundVolume;
    }

    /**
     * Gibt die Lautstaerke der Musik zurueck
     *
     * @return musicVolume Die Lautstaerke
     */
    public float getMusicVolume() {
        return musicVolume;
    }

    /**
     * Setzt die Lautstaerke der Musik
     *
     * @param musicVolume Die neue Lautstaerke
     */
    public void setMusicVolume(float musicVolume) {
        this.musicVolume = musicVolume;
    }


    @Override
    /**
     * Implementation der JSON write Methode, welche die Art wie ein Objekt der Klasse gespeichert wird beschreibt.
     * @param json JSON Verarbeitungsklasse
     */
    public void write(Json json) {
        json.writeValue("highscore", highscore);
        json.writeValue("playerName", playerName);
        json.writeValue("musicVolume", musicVolume);
        json.writeValue("soundVolume", soundVolume);
    }

    @Override
    @SuppressWarnings("unchecked")
    /**
     * Implementation der JSON read Methode, welche die Art wie ein Objekt der Klasse gelesen wird beschreibt.
     * @param json JSON Verarbeitungsklasse
     * @param jsonData Daten aus dem JSON File
     */
    public void read(Json json, OrderedMap<String, Object> jsonData) {
        highscore = json.readValue("highscore", ArrayList.class, jsonData);
        playerName = json.readValue("playerName", String.class, jsonData);
        musicVolume = json.readValue("musicVolume", Float.class, jsonData);
        soundVolume = json.readValue("soundVolume", Float.class, jsonData);
    }
}
