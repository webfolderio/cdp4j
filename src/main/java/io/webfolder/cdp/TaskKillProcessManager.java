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
package io.webfolder.cdp;

import static java.lang.String.valueOf;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.io.IOException;
import java.lang.reflect.Field;

import io.webfolder.cdp.exception.CdpException;

public class TaskKillProcessManager extends ProcessManager {

    private CdpProcess process;

    @Override
    void setProcess(CdpProcess process) {
        this.process = process;
    }

    @Override
    public boolean kill() {
        Field handleField;
        try {
            handleField = process.getProcess().getClass().getDeclaredField("handle");
        } catch (NoSuchFieldException | SecurityException e) {
            throw new CdpException(e);
        }
        handleField.setAccessible(true);
        Object pid;
        try {
            pid = handleField.get(process.getProcess());
        } catch (IllegalArgumentException | IllegalAccessException e) {
            throw new CdpException(e);
        }
        try {
            Process process = Runtime
                                .getRuntime()
                                .exec(new String[] {
                                        "cmd", "/c",
                                        "taskkill",
                                        "/pid",
                                        valueOf(pid), "/T", "/F"
                                    });
            return process.waitFor(10, SECONDS) && process.exitValue() == 0;
        } catch (IOException | InterruptedException e) {
            throw new CdpException(e);
        }
    }
}
