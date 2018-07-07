package pay4free.in.quickbook.model;

/**
 * Created by AAKASH on 07-03-2018.
 */

public class Token {
    private String token;
    private boolean ServerToken;

    public Token() {
    }

    public Token(String token, boolean isServerToken) {
        this.token = token;
        this.ServerToken = isServerToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean ServerToken() {
        return ServerToken;
    }

    public void setServerToken(boolean serverToken) {
        ServerToken = serverToken;
    }
}
