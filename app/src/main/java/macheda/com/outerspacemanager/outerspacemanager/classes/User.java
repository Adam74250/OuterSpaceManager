package macheda.com.outerspacemanager.outerspacemanager.classes;

/**
 * Created by adammacheda on 16/04/2018.
 */

public class User {
    private int points;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;

    public User(int points, String username) {
        this.points = points;
        this.username = username;
    }
}
