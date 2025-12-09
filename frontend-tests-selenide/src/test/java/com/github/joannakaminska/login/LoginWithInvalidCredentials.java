package com.github.joannakaminska.login;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class LoginWithInvalidCredentials {

    @Test
    void shouldNotLoginAndShowErrorTest() {

        open("https://ta-bookrental-fe.onrender.com/login");

        $("#login").setValue("TestUser123456");
        $("#password").setValue("TestPassword123456666666");

        $("#login-btn").click();

        $(".alert.alert--error p").shouldHave(Condition.text("Login failed"));

    }
}
