package com.github.joannakaminska.login;

import com.github.joannakaminska.BaseTest;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

class LoginWIthValidCredentials extends BaseTest {

    @Test
    void shouldLoginUserTest() {

        open("https://ta-bookrental-fe.onrender.com/login");

        $("#login").setValue("TestUser12345678");
        $("#password").setValue("MojeNoweSuperBezpieczneHaslo!@#$%^");

        $("#login-btn").click();

        $("#titles").shouldBe(visible);

    }
}
