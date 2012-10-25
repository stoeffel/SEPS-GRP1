package ch.zhaw.arsphema.model;

/**
 * Entry in the Highscore compared by the score
 */
public class HighscoreEntry implements Comparable<HighscoreEntry> {

    private String name;
    private Integer score;

    //empty constructor necessary for JSON
    public HighscoreEntry() {
    }

    public HighscoreEntry(String name, int score) {
        HighscoreEntry.this.name = name;
        HighscoreEntry.this.score = score;
    }

    public Integer getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(HighscoreEntry entry) {
        //compare by the score
        return entry.getScore().compareTo(score);
    }
}
