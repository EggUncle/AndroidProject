package com.rixin.officeauto;

/**
 * Created by egguncle on 16.9.22.
 */

public class AccessToken {
    private String access_token;

    private String token_type;

    private int expires_in;

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAccess_token() {
        return this.access_token;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getToken_type() {
        return this.token_type;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public int getExpires_in() {
        return this.expires_in;
    }
}
