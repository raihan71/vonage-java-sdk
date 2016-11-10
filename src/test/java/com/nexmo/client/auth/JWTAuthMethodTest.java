package com.nexmo.client.auth;
/*
 * Copyright (c) 2011-2016 Nexmo Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

import static org.junit.Assert.*;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.nexmo.client.TestUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

public class JWTAuthMethodTest {
    private TestUtils testUtils;
    private JWTAuthMethod auth;

    @Before
    public void setUp() throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, IOException {
        this.testUtils = new TestUtils();

        byte[] keyBytes = testUtils.loadKey("test/keys/application_key");
        auth = new JWTAuthMethod("application-id", keyBytes);
    }

    @Test
    public void testConstructToken()
            throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, IOException,
            SignatureException, JWTVerifyException {
        String constructedToken = auth.constructToken(1234, "1111111");

        byte[] keyBytes = testUtils.loadKey("test/keys/application_public_key.der");
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PublicKey key = kf.generatePublic(spec);

        final JWTVerifier verifier = new JWTVerifier(key);
        final Map<String, Object> claims = verifier.verify(constructedToken);

        assertEquals(1234, claims.get("iat"));
        assertEquals("1111111", claims.get("jti"));
        assertEquals("application-id", claims.get("application_id"));
    }
}
