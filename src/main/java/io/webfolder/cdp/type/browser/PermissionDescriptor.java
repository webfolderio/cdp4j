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
package io.webfolder.cdp.type.browser;

import io.webfolder.cdp.annotation.Experimental;

/**
 * Definition of PermissionDescriptor defined in the Permissions API:
 * https://w3c
 * github
 * io/permissions/#dictdef-permissiondescriptor
 */
@Experimental
public class PermissionDescriptor {
    private String name;

    private Boolean sysex;

    private Boolean userVisibleOnly;

    private String type;

    private Boolean allowWithoutSanitization;

    /**
     * Name of permission.
     * See https://cs.chromium.org/chromium/src/third_party/blink/renderer/modules/permissions/permission_descriptor.idl for valid permission names.
     */
    public String getName() {
        return name;
    }

    /**
     * Name of permission.
     * See https://cs.chromium.org/chromium/src/third_party/blink/renderer/modules/permissions/permission_descriptor.idl for valid permission names.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * For "midi" permission, may also specify sysex control.
     */
    public Boolean isSysex() {
        return sysex;
    }

    /**
     * For "midi" permission, may also specify sysex control.
     */
    public void setSysex(Boolean sysex) {
        this.sysex = sysex;
    }

    /**
     * For "push" permission, may specify userVisibleOnly.
     * Note that userVisibleOnly = true is the only currently supported type.
     */
    public Boolean isUserVisibleOnly() {
        return userVisibleOnly;
    }

    /**
     * For "push" permission, may specify userVisibleOnly.
     * Note that userVisibleOnly = true is the only currently supported type.
     */
    public void setUserVisibleOnly(Boolean userVisibleOnly) {
        this.userVisibleOnly = userVisibleOnly;
    }

    /**
     * For "wake-lock" permission, must specify type as either "screen" or "system".
     */
    public String getType() {
        return type;
    }

    /**
     * For "wake-lock" permission, must specify type as either "screen" or "system".
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * For "clipboard" permission, may specify allowWithoutSanitization.
     */
    public Boolean isAllowWithoutSanitization() {
        return allowWithoutSanitization;
    }

    /**
     * For "clipboard" permission, may specify allowWithoutSanitization.
     */
    public void setAllowWithoutSanitization(Boolean allowWithoutSanitization) {
        this.allowWithoutSanitization = allowWithoutSanitization;
    }
}
