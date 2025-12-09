package com.github.joannakaminska.register;

import com.github.joannakaminska.BaseTest;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


class RegisterNewUser extends BaseTest {

    @Test
    void ShouldRegisterNewUserTest() {

        open("https://ta-bookrental-fe.onrender.com/register");

        $("#login").setValue("TestUser1234567888");
        $("#password").setValue("TestPassword12345");
        $("#password-repeat").setValue("TestPassword12345");

        $("#register-btn").click();

        $(".alert.alert--success p").should(appear);

        $(".alert.alert--success p").shouldHave(text("You have been successfully registered!"));

    }
}