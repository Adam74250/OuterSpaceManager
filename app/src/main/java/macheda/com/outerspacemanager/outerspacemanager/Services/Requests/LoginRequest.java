package macheda.com.outerspacemanager.outerspacemanager.Services.Requests;

/**
 * Created by adammacheda on 13/03/2018.
 */

public class LoginRequest {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String username;

    private String password;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
