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
import io.webfolder.cdp.type.css.CSSComputedStyleProperty;
import io.webfolder.cdp.type.css.CSSMedia;
import io.webfolder.cdp.type.css.CSSRule;
import io.webfolder.cdp.type.css.CSSStyle;
import io.webfolder.cdp.type.css.GetBackgroundColorsResult;
import io.webfolder.cdp.type.css.GetInlineStylesForNodeResult;
import io.webfolder.cdp.type.css.GetMatchedStylesForNodeResult;
import io.webfolder.cdp.type.css.PlatformFontUsage;
import io.webfolder.cdp.type.css.RuleUsage;
import io.webfolder.cdp.type.css.SelectorList;
import io.webfolder.cdp.type.css.SourceRange;
import io.webfolder.cdp.type.css.StyleDeclarationEdit;
import io.webfolder.cdp.type.css.Value;

public class CSSImpl implements CSS {

    private static final TypeToken<List<String>> COLLECT_CLASS_NAMES = new TypeToken<List<String>>() {
    };
    private static final String[] EMPTY_ARGS = new String[] {};
    private static final Object[] EMPTY_VALUES = new Object[] {};
    private static final TypeToken<List<CSSComputedStyleProperty>> GET_COMPUTED_STYLE_FOR_NODE = new TypeToken<List<CSSComputedStyleProperty>>() {
    };
    private static final TypeToken<List<CSSMedia>> GET_MEDIA_QUERIES = new TypeToken<List<CSSMedia>>() {
    };
    private static final TypeToken<List<PlatformFontUsage>> GET_PLATFORM_FONTS_FOR_NODE = new TypeToken<List<PlatformFontUsage>>() {
    };
    private static final TypeToken<List<CSSStyle>> SET_STYLE_TEXTS = new TypeToken<List<CSSStyle>>() {
    };
    private static final TypeToken<List<RuleUsage>> STOP_RULE_USAGE_TRACKING = new TypeToken<List<RuleUsage>>() {
    };
    private static final TypeToken<List<RuleUsage>> TAKE_COVERAGE_DELTA = new TypeToken<List<RuleUsage>>() {
    };
    private final SessionInvocationHandler handler;

    public CSSImpl(SessionInvocationHandler handler) {
        this.handler = handler;
    }

    @Override
    public CSSRule addRule(String styleSheetId, String ruleText, SourceRange location) {
        return (CSSRule) handler.invoke("CSS", "addRule", "CSS.addRule", "rule", CSSRule.class, null, false, false,
                false, new String[] { "styleSheetId", "ruleText", "location" },
                new Object[] { styleSheetId, ruleText, location });
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<String> collectClassNames(String styleSheetId) {
        return (List<String>) handler.invoke("CSS", "collectClassNames", "CSS.collectClassNames", "classNames",
                List.class, COLLECT_CLASS_NAMES.getType(), false, false, false, new String[] { "styleSheetId" },
                new Object[] { styleSheetId });
    }

    @Override
    public String createStyleSheet(String frameId) {
        return (String) handler.invoke("CSS", "createStyleSheet", "CSS.createStyleSheet", "styleSheetId", String.class,
                null, false, false, false, new String[] { "frameId" }, new Object[] { frameId });
    }

    @Override
    public void disable() {
        handler.invoke("CSS", "disable", "CSS.disable", null, void.class, null, true, false, true, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public void enable() {
        handler.invoke("CSS", "enable", "CSS.enable", null, void.class, null, true, true, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    public void forcePseudoState(Integer nodeId, List<String> forcedPseudoClasses) {
        handler.invoke("CSS", "forcePseudoState", "CSS.forcePseudoState", null, void.class, null, true, false, false,
                new String[] { "nodeId", "forcedPseudoClasses" }, new Object[] { nodeId, forcedPseudoClasses });
    }

    @Override
    public GetBackgroundColorsResult getBackgroundColors(Integer nodeId) {
        return (GetBackgroundColorsResult) handler.invoke("CSS", "getBackgroundColors", "CSS.getBackgroundColors", null,
                GetBackgroundColorsResult.class, null, false, false, false, new String[] { "nodeId" },
                new Object[] { nodeId });
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<CSSComputedStyleProperty> getComputedStyleForNode(Integer nodeId) {
        return (List<CSSComputedStyleProperty>) handler.invoke("CSS", "getComputedStyleForNode",
                "CSS.getComputedStyleForNode", "computedStyle", List.class, GET_COMPUTED_STYLE_FOR_NODE.getType(),
                false, false, false, new String[] { "nodeId" }, new Object[] { nodeId });
    }

    @Override
    public GetInlineStylesForNodeResult getInlineStylesForNode(Integer nodeId) {
        return (GetInlineStylesForNodeResult) handler.invoke("CSS", "getInlineStylesForNode",
                "CSS.getInlineStylesForNode", null, GetInlineStylesForNodeResult.class, null, false, false, false,
                new String[] { "nodeId" }, new Object[] { nodeId });
    }

    @Override
    public GetMatchedStylesForNodeResult getMatchedStylesForNode(Integer nodeId) {
        return (GetMatchedStylesForNodeResult) handler.invoke("CSS", "getMatchedStylesForNode",
                "CSS.getMatchedStylesForNode", null, GetMatchedStylesForNodeResult.class, null, false, false, false,
                new String[] { "nodeId" }, new Object[] { nodeId });
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<CSSMedia> getMediaQueries() {
        return (List<CSSMedia>) handler.invoke("CSS", "getMediaQueries", "CSS.getMediaQueries", "medias", List.class,
                GET_MEDIA_QUERIES.getType(), false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<PlatformFontUsage> getPlatformFontsForNode(Integer nodeId) {
        return (List<PlatformFontUsage>) handler.invoke("CSS", "getPlatformFontsForNode", "CSS.getPlatformFontsForNode",
                "fonts", List.class, GET_PLATFORM_FONTS_FOR_NODE.getType(), false, false, false,
                new String[] { "nodeId" }, new Object[] { nodeId });
    }

    @Override
    public String getStyleSheetText(String styleSheetId) {
        return (String) handler.invoke("CSS", "getStyleSheetText", "CSS.getStyleSheetText", "text", String.class, null,
                false, false, false, new String[] { "styleSheetId" }, new Object[] { styleSheetId });
    }

    @Override
    public void setEffectivePropertyValueForNode(Integer nodeId, String propertyName, String value) {
        handler.invoke("CSS", "setEffectivePropertyValueForNode", "CSS.setEffectivePropertyValueForNode", null,
                void.class, null, true, false, false, new String[] { "nodeId", "propertyName", "value" },
                new Object[] { nodeId, propertyName, value });
    }

    @Override
    public Value setKeyframeKey(String styleSheetId, SourceRange range, String keyText) {
        return (Value) handler.invoke("CSS", "setKeyframeKey", "CSS.setKeyframeKey", "keyText", Value.class, null,
                false, false, false, new String[] { "styleSheetId", "range", "keyText" },
                new Object[] { styleSheetId, range, keyText });
    }

    @Override
    public CSSMedia setMediaText(String styleSheetId, SourceRange range, String text) {
        return (CSSMedia) handler.invoke("CSS", "setMediaText", "CSS.setMediaText", "media", CSSMedia.class, null,
                false, false, false, new String[] { "styleSheetId", "range", "text" },
                new Object[] { styleSheetId, range, text });
    }

    @Override
    public SelectorList setRuleSelector(String styleSheetId, SourceRange range, String selector) {
        return (SelectorList) handler.invoke("CSS", "setRuleSelector", "CSS.setRuleSelector", "selectorList",
                SelectorList.class, null, false, false, false, new String[] { "styleSheetId", "range", "selector" },
                new Object[] { styleSheetId, range, selector });
    }

    @Override
    public String setStyleSheetText(String styleSheetId, String text) {
        return (String) handler.invoke("CSS", "setStyleSheetText", "CSS.setStyleSheetText", "sourceMapURL",
                String.class, null, false, false, false, new String[] { "styleSheetId", "text" },
                new Object[] { styleSheetId, text });
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<CSSStyle> setStyleTexts(List<StyleDeclarationEdit> edits) {
        return (List<CSSStyle>) handler.invoke("CSS", "setStyleTexts", "CSS.setStyleTexts", "styles", List.class,
                SET_STYLE_TEXTS.getType(), false, false, false, new String[] { "edits" }, new Object[] { edits });
    }

    @Override
    public void startRuleUsageTracking() {
        handler.invoke("CSS", "startRuleUsageTracking", "CSS.startRuleUsageTracking", null, void.class, null, true,
                false, false, EMPTY_ARGS, EMPTY_VALUES);
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<RuleUsage> stopRuleUsageTracking() {
        return (List<RuleUsage>) handler.invoke("CSS", "stopRuleUsageTracking", "CSS.stopRuleUsageTracking",
                "ruleUsage", List.class, STOP_RULE_USAGE_TRACKING.getType(), false, false, false, EMPTY_ARGS,
                EMPTY_VALUES);
    }

    @Override
    @java.lang.SuppressWarnings("unchecked")
    public List<RuleUsage> takeCoverageDelta() {
        return (List<RuleUsage>) handler.invoke("CSS", "takeCoverageDelta", "CSS.takeCoverageDelta", "coverage",
                List.class, TAKE_COVERAGE_DELTA.getType(), false, false, false, EMPTY_ARGS, EMPTY_VALUES);
    }
}