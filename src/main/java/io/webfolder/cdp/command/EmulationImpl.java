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

import io.webfolder.cdp.session.SessionInvocationHandler;
import io.webfolder.cdp.type.constant.EmulatedVisionDeficiency;
import io.webfolder.cdp.type.constant.Platform;
import io.webfolder.cdp.type.dom.RGBA;
import io.webfolder.cdp.type.emulation.MediaFeature;
import io.webfolder.cdp.type.emulation.ScreenOrientation;
import io.webfolder.cdp.type.emulation.UserAgentMetadata;
import io.webfolder.cdp.type.emulation.VirtualTimePolicy;
import io.webfolder.cdp.type.page.Viewport;

public class EmulationImpl implements Emulation {

	private static final Object[] EMPTY_VALUES = new Object[]{};
	private static final String[] EMPTY_ARGS = new String[]{};
	private final SessionInvocationHandler handler;

	public EmulationImpl(SessionInvocationHandler handler) {
		this.handler = handler;
	}

	@Override
	public Boolean canEmulate() {
		return (Boolean) handler.invoke("Emulation", "canEmulate", "Emulation.canEmulate", "result", Boolean.class,
				null, false, false, false, EMPTY_ARGS, EMPTY_VALUES);
	}

	@Override
	public void clearDeviceMetricsOverride() {
		handler.invoke("Emulation", "clearDeviceMetricsOverride", "Emulation.clearDeviceMetricsOverride", null,
				void.class, null, true, false, false, EMPTY_ARGS, EMPTY_VALUES);
	}

	@Override
	public void clearGeolocationOverride() {
		handler.invoke("Emulation", "clearGeolocationOverride", "Emulation.clearGeolocationOverride", null, void.class,
				null, true, false, false, EMPTY_ARGS, EMPTY_VALUES);
	}

	@Override
	public void resetPageScaleFactor() {
		handler.invoke("Emulation", "resetPageScaleFactor", "Emulation.resetPageScaleFactor", null, void.class, null,
				true, false, false, EMPTY_ARGS, EMPTY_VALUES);
	}

	@Override
	public void setFocusEmulationEnabled(Boolean enabled) {
		handler.invoke("Emulation", "setFocusEmulationEnabled", "Emulation.setFocusEmulationEnabled", null, void.class,
				null, true, false, false, new String[]{"enabled"}, new Object[]{enabled});
	}

	@Override
	public void setCPUThrottlingRate(Double rate) {
		handler.invoke("Emulation", "setCPUThrottlingRate", "Emulation.setCPUThrottlingRate", null, void.class, null,
				true, false, false, new String[]{"rate"}, new Object[]{rate});
	}

	@Override
	public void setDefaultBackgroundColorOverride(RGBA color) {
		handler.invoke("Emulation", "setDefaultBackgroundColorOverride", "Emulation.setDefaultBackgroundColorOverride",
				null, void.class, null, true, false, false, new String[]{"color"}, new Object[]{color});
	}

	@Override
	public void setDeviceMetricsOverride(Integer width, Integer height, Double deviceScaleFactor, Boolean mobile,
			Double scale, Integer screenWidth, Integer screenHeight, Integer positionX, Integer positionY,
			Boolean dontSetVisibleSize, ScreenOrientation screenOrientation, Viewport viewport) {
		handler.invoke("Emulation", "setDeviceMetricsOverride", "Emulation.setDeviceMetricsOverride", null, void.class,
				null, true, false, false,
				new String[]{"width", "height", "deviceScaleFactor", "mobile", "scale", "screenWidth", "screenHeight",
						"positionX", "positionY", "dontSetVisibleSize", "screenOrientation", "viewport"},
				new Object[]{width, height, deviceScaleFactor, mobile, scale, screenWidth, screenHeight, positionX,
						positionY, dontSetVisibleSize, screenOrientation, viewport});
	}

	@Override
	public void setScrollbarsHidden(Boolean hidden) {
		handler.invoke("Emulation", "setScrollbarsHidden", "Emulation.setScrollbarsHidden", null, void.class, null,
				true, false, false, new String[]{"hidden"}, new Object[]{hidden});
	}

	@Override
	public void setDocumentCookieDisabled(Boolean disabled) {
		handler.invoke("Emulation", "setDocumentCookieDisabled", "Emulation.setDocumentCookieDisabled", null,
				void.class, null, true, false, false, new String[]{"disabled"}, new Object[]{disabled});
	}

	@Override
	public void setEmitTouchEventsForMouse(Boolean enabled, Platform configuration) {
		handler.invoke("Emulation", "setEmitTouchEventsForMouse", "Emulation.setEmitTouchEventsForMouse", null,
				void.class, null, true, false, false, new String[]{"enabled", "configuration"},
				new Object[]{enabled, configuration});
	}

	@Override
	public void setEmulatedMedia(String media, List<MediaFeature> features) {
		handler.invoke("Emulation", "setEmulatedMedia", "Emulation.setEmulatedMedia", null, void.class, null, true,
				false, false, new String[]{"media", "features"}, new Object[]{media, features});
	}

	@Override
	public void setEmulatedVisionDeficiency(EmulatedVisionDeficiency type) {
		handler.invoke("Emulation", "setEmulatedVisionDeficiency", "Emulation.setEmulatedVisionDeficiency", null,
				void.class, null, true, false, false, new String[]{"type"}, new Object[]{type});
	}

	@Override
	public void setGeolocationOverride(Double latitude, Double longitude, Double accuracy) {
		handler.invoke("Emulation", "setGeolocationOverride", "Emulation.setGeolocationOverride", null, void.class,
				null, true, false, false, new String[]{"latitude", "longitude", "accuracy"},
				new Object[]{latitude, longitude, accuracy});
	}

	@Override
	public void setNavigatorOverrides(String platform) {
		handler.invoke("Emulation", "setNavigatorOverrides", "Emulation.setNavigatorOverrides", null, void.class, null,
				true, false, false, new String[]{"platform"}, new Object[]{platform});
	}

	@Override
	public void setPageScaleFactor(Double pageScaleFactor) {
		handler.invoke("Emulation", "setPageScaleFactor", "Emulation.setPageScaleFactor", null, void.class, null, true,
				false, false, new String[]{"pageScaleFactor"}, new Object[]{pageScaleFactor});
	}

	@Override
	public void setScriptExecutionDisabled(Boolean value) {
		handler.invoke("Emulation", "setScriptExecutionDisabled", "Emulation.setScriptExecutionDisabled", null,
				void.class, null, true, false, false, new String[]{"value"}, new Object[]{value});
	}

	@Override
	public void setTouchEmulationEnabled(Boolean enabled, Integer maxTouchPoints) {
		handler.invoke("Emulation", "setTouchEmulationEnabled", "Emulation.setTouchEmulationEnabled", null, void.class,
				null, true, false, false, new String[]{"enabled", "maxTouchPoints"},
				new Object[]{enabled, maxTouchPoints});
	}

	@Override
	public Double setVirtualTimePolicy(VirtualTimePolicy policy, Double budget,
			Integer maxVirtualTimeTaskStarvationCount, Boolean waitForNavigation, Double initialVirtualTime) {
		return (Double) handler.invoke("Emulation", "setVirtualTimePolicy", "Emulation.setVirtualTimePolicy",
				"virtualTimeTicksBase", Double.class, null, false, false, false,
				new String[]{"policy", "budget", "maxVirtualTimeTaskStarvationCount", "waitForNavigation",
						"initialVirtualTime"},
				new Object[]{policy, budget, maxVirtualTimeTaskStarvationCount, waitForNavigation, initialVirtualTime});
	}

	@Override
	public void setLocaleOverride(String locale) {
		handler.invoke("Emulation", "setLocaleOverride", "Emulation.setLocaleOverride", null, void.class, null, true,
				false, false, new String[]{"locale"}, new Object[]{locale});
	}

	@Override
	public void setTimezoneOverride(String timezoneId) {
		handler.invoke("Emulation", "setTimezoneOverride", "Emulation.setTimezoneOverride", null, void.class, null,
				true, false, false, new String[]{"timezoneId"}, new Object[]{timezoneId});
	}

	@Override
	public void setVisibleSize(Integer width, Integer height) {
		handler.invoke("Emulation", "setVisibleSize", "Emulation.setVisibleSize", null, void.class, null, true, false,
				false, new String[]{"width", "height"}, new Object[]{width, height});
	}

	@Override
	public void setUserAgentOverride(String userAgent, String acceptLanguage, String platform,
			UserAgentMetadata userAgentMetadata) {
		handler.invoke("Emulation", "setUserAgentOverride", "Emulation.setUserAgentOverride", null, void.class, null,
				true, false, false, new String[]{"userAgent", "acceptLanguage", "platform", "userAgentMetadata"},
				new Object[]{userAgent, acceptLanguage, platform, userAgentMetadata});
	}

	@Override
	public void setDefaultBackgroundColorOverride() {
		handler.invoke("Emulation", "setDefaultBackgroundColorOverride", "Emulation.setDefaultBackgroundColorOverride",
				null, void.class, null, true, false, false, EMPTY_ARGS, EMPTY_VALUES);
	}

	@Override
	public void setDeviceMetricsOverride(Integer width, Integer height, Double deviceScaleFactor, Boolean mobile) {
		handler.invoke("Emulation", "setDeviceMetricsOverride", "Emulation.setDeviceMetricsOverride", null, void.class,
				null, true, false, false, new String[]{"width", "height", "deviceScaleFactor", "mobile"},
				new Object[]{width, height, deviceScaleFactor, mobile});
	}

	@Override
	public void setEmitTouchEventsForMouse(Boolean enabled) {
		handler.invoke("Emulation", "setEmitTouchEventsForMouse", "Emulation.setEmitTouchEventsForMouse", null,
				void.class, null, true, false, false, new String[]{"enabled"}, new Object[]{enabled});
	}

	@Override
	public void setEmulatedMedia() {
		handler.invoke("Emulation", "setEmulatedMedia", "Emulation.setEmulatedMedia", null, void.class, null, true,
				false, false, EMPTY_ARGS, EMPTY_VALUES);
	}

	@Override
	public void setGeolocationOverride() {
		handler.invoke("Emulation", "setGeolocationOverride", "Emulation.setGeolocationOverride", null, void.class,
				null, true, false, false, EMPTY_ARGS, EMPTY_VALUES);
	}

	@Override
	public void setTouchEmulationEnabled(Boolean enabled) {
		handler.invoke("Emulation", "setTouchEmulationEnabled", "Emulation.setTouchEmulationEnabled", null, void.class,
				null, true, false, false, new String[]{"enabled"}, new Object[]{enabled});
	}

	@Override
	public Double setVirtualTimePolicy(VirtualTimePolicy policy) {
		return (Double) handler.invoke("Emulation", "setVirtualTimePolicy", "Emulation.setVirtualTimePolicy",
				"virtualTimeTicksBase", Double.class, null, false, false, false, new String[]{"policy"},
				new Object[]{policy});
	}

	@Override
	public void setLocaleOverride() {
		handler.invoke("Emulation", "setLocaleOverride", "Emulation.setLocaleOverride", null, void.class, null, true,
				false, false, EMPTY_ARGS, EMPTY_VALUES);
	}

	@Override
	public void setUserAgentOverride(String userAgent) {
		handler.invoke("Emulation", "setUserAgentOverride", "Emulation.setUserAgentOverride", null, void.class, null,
				true, false, false, new String[]{"userAgent"}, new Object[]{userAgent});
	}
}