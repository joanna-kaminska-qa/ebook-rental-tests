package com.github.joannakaminska;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    @BeforeAll
    static void setUp() {
        Configuration.browser = "chrome";
        Configuration.timeout = 60000;
    }
}
