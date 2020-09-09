/*
 * Copyright (c) 2020 Vonage
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
package com.vonage.client.application;

import com.vonage.client.HttpConfig;
import com.vonage.client.HttpWrapper;
import org.apache.http.client.methods.RequestBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ListApplicationsMethodTest {
    private ListApplicationsMethod method;

    @Before
    public void setUp() {
        method = new ListApplicationsMethod(new HttpWrapper());
    }

    @Test
    public void testMakeRequestWithNoParameters() throws Exception {
        RequestBuilder builder = method.makeRequest(ListApplicationRequest.builder().build());

        assertEquals("GET", builder.getMethod());
        assertEquals("application/json", builder.getFirstHeader("Content-Type").getValue());
    }

    @Test
    public void testMakeRequestWithParameters() throws Exception {
        RequestBuilder builder = method.makeRequest(ListApplicationRequest.builder().page(1).pageSize(10).build());

        assertEquals("GET", builder.getMethod());
        assertEquals("application/json", builder.getFirstHeader("Content-Type").getValue());
    }

    @Test
    public void testDefaultUriWithNoParameters() throws Exception {
        RequestBuilder builder = method.makeRequest(ListApplicationRequest.builder().build());

        assertEquals("GET", builder.getMethod());
        assertEquals("https://api.nexmo.com/v2/applications", builder.build().getURI().toString());
    }

    @Test
    public void testCustomUriWithNoParameters() throws Exception {
        HttpWrapper wrapper = new HttpWrapper(HttpConfig.builder().baseUri("https://example.com").build());
        ListApplicationsMethod method = new ListApplicationsMethod(wrapper);

        RequestBuilder builder = method.makeRequest(ListApplicationRequest.builder().build());

        assertEquals("GET", builder.getMethod());
        assertEquals("https://example.com/v2/applications", builder.build().getURI().toString());
    }

    @Test
    public void testDefaultUriWithParameters() throws Exception {
        RequestBuilder builder = method.makeRequest(ListApplicationRequest.builder().page(1).pageSize(10).build());

        assertEquals("GET", builder.getMethod());
        assertEquals("https://api.nexmo.com/v2/applications?page_size=10&page=1", builder.build().getURI().toString());
    }

    @Test
    public void testCustomUriWithParameters() throws Exception {
        HttpWrapper wrapper = new HttpWrapper(HttpConfig.builder().baseUri("https://example.com").build());
        ListApplicationsMethod method = new ListApplicationsMethod(wrapper);

        RequestBuilder builder = method.makeRequest(ListApplicationRequest.builder().page(1).pageSize(10).build());

        assertEquals("GET", builder.getMethod());
        assertEquals("https://example.com/v2/applications?page_size=10&page=1", builder.build().getURI().toString());
    }
}