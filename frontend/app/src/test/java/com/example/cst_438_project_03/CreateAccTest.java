package com.example.cst_438_project_03;

import org.junit.Test;
import static org.junit.Assert.*;

import android.content.Context;

public class CreateAccTest {
    @Test
    public void emptyUserField() {
        String username = "";
        assertEquals(true, CreateAcc.isUsernameEmpty(username));
    }

    @Test
    public void emptyPasswordField() {
        String password = "";
        assertEquals(true, CreateAcc.isPasswordEmpty(password));
    }

    @Test
    public void filledUserField() {
        String username = "user";
        assertEquals(false, CreateAcc.isUsernameEmpty(username));
    }

    @Test
    public void filledPasswordField() {
        String password = "password";
        assertEquals(false, CreateAcc.isPasswordEmpty(password));
    }

    @Test
    public void hasInput() {
        String username = "user";
        String password = "password";
        assertEquals(true, CreateAcc.hasInput(username, password));
    }

}