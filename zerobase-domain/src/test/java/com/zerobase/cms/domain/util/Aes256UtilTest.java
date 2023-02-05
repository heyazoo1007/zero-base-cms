package com.zerobase.cms.domain.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class Aes256UtilTest {

    @Test
    void encrypt() {
        String encrypt = Aes256Util.encrypt("hello world");
        assertEquals(Aes256Util.decrypt(encrypt), "hello world");
    }
}