/**
 * cdp4j Commercial License
 *
 * Copyright 2017, 2020 WebFolder OÜ
 *
 * Permission  is hereby  granted,  to "____" obtaining  a  copy of  this software  and
 * associated  documentation files  (the "Software"), to deal in  the Software  without
 * restriction, including without limitation  the rights  to use, copy, modify,  merge,
 * publish, distribute  and sublicense  of the Software,  and to permit persons to whom
 * the Software is furnished to do so, subject to the following conditions:
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  IMPLIED,
 * INCLUDING  BUT NOT  LIMITED  TO THE  WARRANTIES  OF  MERCHANTABILITY, FITNESS  FOR A
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL  THE AUTHORS  OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package io.webfolder.cdp.command;

import java.util.List;

import com.google.gson.reflect.TypeToken;

import io.webfolder.cdp.session.SessionInvocationHandler;
import io.webfolder.cdp.type.webauthn.Credential;
import io.webfolder.cdp.type.webauthn.VirtualAuthenticatorOptions;

public class WebAuthnImpl implements WebAuthn {

    private static final Object[] EMPTY_VALUES = new Object[]{};
    private static final String[] EMPTY_ARGS = new String[]{};
    private final SessionInvocationHandler handler;
    private static final TypeToken<List<Credential>> TT_LIST_CREDENTIAL = new TypeToken<List<Credential>>() { };

    public WebAuthnImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public void enable() {
        handler.invoke("WebAuthn", "enable", "WebAuthn.enable", null, void.class, null, true, true, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public void disable() {
        handler.invoke("WebAuthn", "disable", "WebAuthn.disable", null, void.class, null, true, false, true, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public String addVirtualAuthenticator(VirtualAuthenticatorOptions options) {
        return (String) handler.invoke("WebAuthn", "addVirtualAuthenticator", "WebAuthn.addVirtualAuthenticator",
                "authenticatorId", String.class, null, false, false, false, new String[]{"options"},
                new Object[]{options});
    }

    @Override
    public void removeVirtualAuthenticator(String authenticatorId) {
        handler.invoke("WebAuthn", "removeVirtualAuthenticator", "WebAuthn.removeVirtualAuthenticator", null,
                void.class, null, true, false, false, new String[]{"authenticatorId"}, new Object[]{authenticatorId});
    }

    @Override
    public void addCredential(String authenticatorId, Credential credential) {
        handler.invoke("WebAuthn", "addCredential", "WebAuthn.addCredential", null, void.class, null, true, false,
                false, new String[]{"authenticatorId", "credential"}, new Object[]{authenticatorId, credential});
    }

    @Override
    public Credential getCredential(String authenticatorId, String credentialId) {
        return (Credential) handler.invoke("WebAuthn", "getCredential", "WebAuthn.getCredential", "credential",
                Credential.class, null, false, false, false, new String[]{"authenticatorId", "credentialId"},
                new Object[]{authenticatorId, credentialId});
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<Credential> getCredentials(String authenticatorId) {
        return (List<Credential>) handler.invoke("WebAuthn", "getCredentials", "WebAuthn.getCredentials", "credentials",
                List.class, TT_LIST_CREDENTIAL.getType(), false, false, false, new String[]{"authenticatorId"},
                new Object[]{authenticatorId});
    }

    @Override
    public void removeCredential(String authenticatorId, String credentialId) {
        handler.invoke("WebAuthn", "removeCredential", "WebAuthn.removeCredential", null, void.class, null, true, false,
                false, new String[]{"authenticatorId", "credentialId"}, new Object[]{authenticatorId, credentialId});
    }

    @Override
    public void clearCredentials(String authenticatorId) {
        handler.invoke("WebAuthn", "clearCredentials", "WebAuthn.clearCredentials", null, void.class, null, true, false,
                false, new String[]{"authenticatorId"}, new Object[]{authenticatorId});
    }

    @Override
    public void setUserVerified(String authenticatorId, Boolean isUserVerified) {
        handler.invoke("WebAuthn", "setUserVerified", "WebAuthn.setUserVerified", null, void.class, null, true, false,
                false, new String[]{"authenticatorId", "isUserVerified"},
                new Object[]{authenticatorId, isUserVerified});
    }
}
