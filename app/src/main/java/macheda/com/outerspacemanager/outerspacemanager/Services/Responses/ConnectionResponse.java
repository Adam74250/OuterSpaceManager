package macheda.com.outerspacemanager.outerspacemanager.Services.Responses;

/**
 * Created by amacheda on 05/03/2018.
 */

public class ConnectionResponse {
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    private String expires;

    public ConnectionResponse(String token, String expires) {
        this.token = token;
        this.expires = expires;
    }
}