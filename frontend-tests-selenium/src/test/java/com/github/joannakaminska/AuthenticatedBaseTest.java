package com.github.joannakaminska;

import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.*;

public abstract class AuthenticatedBaseTest extends BaseTest {

    @BeforeAll
    static void login() {
        open("https://ta-bookrental-fe.onrender.com/login");

        $("#login").setValue("TestUser12345678");
        $("#password").setValue("MojeNoweSuperBezpieczneHaslo!@#$%^");
        $("#login-btn").click();
    }
}
