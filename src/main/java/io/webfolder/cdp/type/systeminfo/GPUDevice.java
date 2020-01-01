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
package io.webfolder.cdp.type.systeminfo;

import com.vimeo.stag.UseStag;

/**
 * Describes a single graphics processor (GPU)
 */
@UseStag
public class GPUDevice {
    private Double vendorId;

    private Double deviceId;

    private String vendorString;

    private String deviceString;

    private String driverVendor;

    private String driverVersion;

    /**
     * PCI ID of the GPU vendor, if available; 0 otherwise.
     */
    public Double getVendorId() {
        return vendorId;
    }

    /**
     * PCI ID of the GPU vendor, if available; 0 otherwise.
     */
    public void setVendorId(Double vendorId) {
        this.vendorId = vendorId;
    }

    /**
     * PCI ID of the GPU device, if available; 0 otherwise.
     */
    public Double getDeviceId() {
        return deviceId;
    }

    /**
     * PCI ID of the GPU device, if available; 0 otherwise.
     */
    public void setDeviceId(Double deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * String description of the GPU vendor, if the PCI ID is not available.
     */
    public String getVendorString() {
        return vendorString;
    }

    /**
     * String description of the GPU vendor, if the PCI ID is not available.
     */
    public void setVendorString(String vendorString) {
        this.vendorString = vendorString;
    }

    /**
     * String description of the GPU device, if the PCI ID is not available.
     */
    public String getDeviceString() {
        return deviceString;
    }

    /**
     * String description of the GPU device, if the PCI ID is not available.
     */
    public void setDeviceString(String deviceString) {
        this.deviceString = deviceString;
    }

    /**
     * String description of the GPU driver vendor.
     */
    public String getDriverVendor() {
        return driverVendor;
    }

    /**
     * String description of the GPU driver vendor.
     */
    public void setDriverVendor(String driverVendor) {
        this.driverVendor = driverVendor;
    }

    /**
     * String description of the GPU driver version.
     */
    public String getDriverVersion() {
        return driverVersion;
    }

    /**
     * String description of the GPU driver version.
     */
    public void setDriverVersion(String driverVersion) {
        this.driverVersion = driverVersion;
    }
}
