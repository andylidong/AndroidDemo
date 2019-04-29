package com.andy.module.login.data.login;

/**
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2019-04-28$ 15:12$
 * @UpdateUser: lidong
 * @UpdateDate: 2019-04-28$ 15:12$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class Login {

    private String email;

    private String password;

    public Login() {
    }

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
