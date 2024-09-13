package mjr.ch4.recipes;

public class Golfer {
    private String first;
    private String last;
    private int score;

    public Golfer(String first, String last, int score) {
        this.first = first;
        this.last = last;
        this.score = score;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Golfer{" +
                "first='" + first + '\'' +
                ", last='" + last + '\'' +
                ", score=" + score +
                '}';
    }
}
