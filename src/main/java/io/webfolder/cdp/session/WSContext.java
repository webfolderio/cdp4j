/**
 * cdp4j - Chrome DevTools Protocol for Java
 * Copyright © 2017, 2018 WebFolder OÜ (support@webfolder.io)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.webfolder.cdp.session;

import io.webfolder.cdp.exception.CdpException;
import io.webfolder.cdp.exception.CommandException;
import io.webfolder.cdp.json.JsonResponse;

import java.util.concurrent.CountDownLatch;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

class WSContext {

    private String jsonRequest;

    private CountDownLatch latch = new CountDownLatch(1);

    private JsonResponse data;

    private CommandException error;

    public WSContext(String jsonRequest) {
        this.jsonRequest = jsonRequest;
    }

    void await(final int timeout) {
        try {
            latch.await(timeout, MILLISECONDS);
        } catch (InterruptedException e) {
            throw new CdpException(e);
        }
    }

    void setData(final JsonResponse data) {
        this.data = data;
        latch.countDown();
    }

    public JsonResponse getData() {
        return data;
    }

    public String getJsonRequest() {
        return jsonRequest;
    }

    void setError(CommandException error) {
        this.error = error;
        latch.countDown();
    }

    CommandException getError() {
        return error;
    }
}
