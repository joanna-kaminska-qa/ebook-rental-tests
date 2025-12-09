package com.github.joannakaminska.register;

import com.github.joannakaminska.BaseTest;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class RegisterUserWithoutFullData extends BaseTest {

    @Test
    void shouldShowError() {

        open("https://ta-bookrental-fe.onrender.com/register");

        $("#login").setValue("TesttingUseer1234");

        $("#register-btn").click();

        $(".alert.alert--error p").should(appear);

        $(".alert.alert--error p").shouldHave(text("You can't leave fields empty"));

    }
}
