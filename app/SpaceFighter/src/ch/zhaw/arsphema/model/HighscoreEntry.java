package ch.zhaw.arsphema.model;

/**
 * eintrag in der highscore liste
 */
public class HighscoreEntry implements Comparable<HighscoreEntry> {

    private String name;
    private Integer score;

    /**
     * empty constructor necessary for JSON
     */
    public HighscoreEntry() {
    }
    /**
	 * konstruktor
	 * @param name der name des spielers
	 * @param score die erreichten punkte
	 */
    public HighscoreEntry(String name, int score) {
        HighscoreEntry.this.name = name;
        HighscoreEntry.this.score = score;
    }
    /**
	 * gibt punkte zurueck
	 * @return score die punkteanzahl
	 */
    public Integer getScore() {
        return score;
    }
    /**
	 * gibt den namen zurueck
	 * @return name den name des spielers
	 */
    public String getName() {
        return name;
    }

    /**
	 * vergleicht mit einem anderem eintrag
	 * @param entry der zu vergleichende eintrag
	 */
    @Override
    public int compareTo(HighscoreEntry entry) {
        //compare by the score
        return entry.getScore().compareTo(score);
    }
}
