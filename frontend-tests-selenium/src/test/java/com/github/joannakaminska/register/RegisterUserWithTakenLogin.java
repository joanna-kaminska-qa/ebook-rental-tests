package com.github.joannakaminska.register;

import com.github.joannakaminska.BaseTest;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class RegisterUserWithTakenLogin extends BaseTest {

    @Test
    void shouldShowError() {

        open("https://ta-bookrental-fe.onrender.com/register");

        $("#login").setValue("TestUser12345");
        $("#password").setValue("TestPassword12345");
        $("#password-repeat").setValue("TestPassword12345");

        $("#register-btn").click();

        $(".alert.alert--error p").should(appear);

        $(".alert.alert--error p").shouldHave(text("This user already exist!"));

    }
}
