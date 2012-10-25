package ch.zhaw.arsphema.services;


import ch.zhaw.arsphema.model.HighscoreEntry;
import ch.zhaw.arsphema.model.PlayerProfile;
import ch.zhaw.arsphema.util.Paths;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;


/**
 * JSON FileHandling for the PlayerProfile
 */
public class ProfileManager {

    private PlayerProfile profile;

    public ProfileManager() {
    }

    public PlayerProfile loadPlayerProfile() {

        FileHandle profileFile = Gdx.files.local(Paths.PLAYER_PROFILE);

        // return the profile if loaded
        if (profile != null) {
            return profile;
        }

        //JSON Utility
        Json json = new Json();

        if (profileFile.exists()) {

            try {
                //read the JSON String
                String profileString = profileFile.readString().trim();

                // restore player profile
                profile = json.fromJson(PlayerProfile.class, profileString);

            } catch (Exception e) {
                System.out.println("Error loading Profile -> create new Profile");
                //Create a new Profile if loading error occours
                profile = new PlayerProfile();
                savePlayerProfile(profile);
            }

        } else {
            // create a new player profile
            profile = new PlayerProfile();
            savePlayerProfile(profile);
        }

        return profile;
    }


    /**
     * Saves the player profile to the JSON file
     *
     * @param profile the player profile to save
     */
    protected void savePlayerProfile(PlayerProfile profile) {

        FileHandle profileDataFile = Gdx.files.local(Paths.PLAYER_PROFILE);

        //JSON Utility
        Json json = new Json();

        // convert to json
        String profileAsText = json.toJson(profile);

        // write the profile to the JSON file
        profileDataFile.writeString(profileAsText, false);
    }

    /**
     * Quick profile save
     */
    public void savePlayerProfile() {
        if (profile != null) {
            savePlayerProfile(profile);
        }
    }

    //just for testing - will be removed later.
    public void createTestProfile() {
        PlayerProfile testProfile = new PlayerProfile();
        testProfile.addHighscoreEntry(new HighscoreEntry("Chuck Norris", 666666));
        testProfile.addHighscoreEntry(new HighscoreEntry("Chuck Norris", 222222));
        testProfile.addHighscoreEntry(new HighscoreEntry("Chuck Norris", 555555));
        testProfile.addHighscoreEntry(new HighscoreEntry("Chuck Norris", 777777));
        testProfile.addHighscoreEntry(new HighscoreEntry("Chuck Norris", 333333));
        testProfile.addHighscoreEntry(new HighscoreEntry("Chuck Norris", 999999));
        savePlayerProfile(testProfile);
    }

}

