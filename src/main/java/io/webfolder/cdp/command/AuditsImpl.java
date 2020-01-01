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

import io.webfolder.cdp.session.SessionInvocationHandler;
import io.webfolder.cdp.type.audits.GetEncodedResponseResult;
import io.webfolder.cdp.type.constant.Encoding;

public class AuditsImpl implements Audits {

	private final SessionInvocationHandler handler;

	public AuditsImpl(SessionInvocationHandler handler) {
		this.handler = handler;
	}

	@Override
	public GetEncodedResponseResult getEncodedResponse(String requestId, Encoding encoding, Double quality,
			Boolean sizeOnly) {
		return (GetEncodedResponseResult) handler.invoke("Audits", "getEncodedResponse", "Audits.getEncodedResponse",
				null, GetEncodedResponseResult.class, null, false, false, false,
				new String[]{"requestId", "encoding", "quality", "sizeOnly"},
				new Object[]{requestId, encoding, quality, sizeOnly});
	}

	@Override
	public GetEncodedResponseResult getEncodedResponse(String requestId, Encoding encoding) {
		return (GetEncodedResponseResult) handler.invoke("Audits", "getEncodedResponse", "Audits.getEncodedResponse",
				null, GetEncodedResponseResult.class, null, false, false, false, new String[]{"requestId", "encoding"},
				new Object[]{requestId, encoding});
	}
}
