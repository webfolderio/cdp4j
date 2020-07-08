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
package io.webfolder.cdp.type.page;

import java.util.List;

import io.webfolder.cdp.annotation.Experimental;

/**
 * The installability error
 */
@Experimental
public class InstallabilityError {
    private String errorId;

    private List<InstallabilityErrorArgument> errorArguments;

    /**
     * The error id (e.g. 'manifest-missing-suitable-icon').
     */
    public String getErrorId() {
        return errorId;
    }

    /**
     * The error id (e.g. 'manifest-missing-suitable-icon').
     */
    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }

    /**
     * The list of error arguments (e.g. {name:'minimum-icon-size-in-pixels', value:'64'}).
     */
    public List<InstallabilityErrorArgument> getErrorArguments() {
        return errorArguments;
    }

    /**
     * The list of error arguments (e.g. {name:'minimum-icon-size-in-pixels', value:'64'}).
     */
    public void setErrorArguments(List<InstallabilityErrorArgument> errorArguments) {
        this.errorArguments = errorArguments;
    }
}
