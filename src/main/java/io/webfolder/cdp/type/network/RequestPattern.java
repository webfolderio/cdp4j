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
package io.webfolder.cdp.type.network;

import io.webfolder.cdp.annotation.Experimental;

/**
 * Request pattern for interception
 */
@Experimental
public class RequestPattern {
    private String urlPattern;

    private ResourceType resourceType;

    private InterceptionStage interceptionStage;

    /**
     * Wildcards ('*' -> zero or more, '?' -> exactly one) are allowed. Escape character is
     * backslash. Omitting is equivalent to "*".
     */
    public String getUrlPattern() {
        return urlPattern;
    }

    /**
     * Wildcards ('*' -> zero or more, '?' -> exactly one) are allowed. Escape character is
     * backslash. Omitting is equivalent to "*".
     */
    public void setUrlPattern(String urlPattern) {
        this.urlPattern = urlPattern;
    }

    /**
     * If set, only requests for matching resource types will be intercepted.
     */
    public ResourceType getResourceType() {
        return resourceType;
    }

    /**
     * If set, only requests for matching resource types will be intercepted.
     */
    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * Stage at wich to begin intercepting requests. Default is Request.
     */
    public InterceptionStage getInterceptionStage() {
        return interceptionStage;
    }

    /**
     * Stage at wich to begin intercepting requests. Default is Request.
     */
    public void setInterceptionStage(InterceptionStage interceptionStage) {
        this.interceptionStage = interceptionStage;
    }
}
