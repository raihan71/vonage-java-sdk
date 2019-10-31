package com.nexmo.client.conversation;

import com.nexmo.client.AbstractMethod;
import com.nexmo.client.HttpWrapper;
import com.nexmo.client.auth.JWTAuthMethod;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.RequestBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class DeleteMemberMethod extends AbstractMethod<SpecificMemberRequest,Integer> {
    private static final String PATH = "/conversations/";
    private static final Class[] ALLOWED_AUTH_METHODS = new Class[]{JWTAuthMethod.class};

    public DeleteMemberMethod(HttpWrapper wrapper){super(wrapper);}
    @Override
    protected Class[] getAcceptableAuthMethods() {
        return ALLOWED_AUTH_METHODS;
    }

    @Override
    public RequestBuilder makeRequest(SpecificMemberRequest request) throws UnsupportedEncodingException {
        String url = httpWrapper.getHttpConfig().getVersionedApiBaseUri("v0.1") + PATH + request.getConversation_id() + "/members/" + request.getMember_id();
        return RequestBuilder.delete(url);
    }

    @Override
    public Integer parseResponse(HttpResponse response) throws IOException {
        return response.getStatusLine().getStatusCode();
    }
}