package macheda.com.outerspacemanager.outerspacemanager.services.responses;

/**
 * Created by adammacheda on 13/03/2018.
 */

public class LoginResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    private String expires;

    public LoginResponse(String token, String expires) {
        this.token = token;
        this.expires = expires;
    }
}
