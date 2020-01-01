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

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.annotation.Returns;
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
import java.util.List;

/**
 * This domain exposes CSS read/write operations
 * All CSS objects (stylesheets, rules, and styles)
 * have an associated <code>id</code> used in subsequent operations on the related object
 * Each object type has
 * a specific <code>id</code> structure, and those are not interchangeable between objects of different kinds
 * CSS objects can be loaded using the `get*ForNode()` calls (which accept a DOM node id)
 * A client
 * can also keep track of stylesheets via the <code>styleSheetAdded</code>/<code>styleSheetRemoved</code> events and
 * subsequently load the required stylesheet contents using the `getStyleSheet[Text]()` methods
 */
@Experimental
@Domain("CSS")
public interface CSS {
    /**
     * Inserts a new rule with the given <code>ruleText</code> in a stylesheet with given <code>styleSheetId</code>, at the
     * position specified by <code>location</code>.
     * 
     * @param styleSheetId The css style sheet identifier where a new rule should be inserted.
     * @param ruleText The text of a new rule.
     * @param location Text position of a new rule in the target style sheet.
     * 
     * @return The newly created rule.
     */
    @Returns("rule")
    CSSRule addRule(String styleSheetId, String ruleText, SourceRange location);

    /**
     * Returns all class names from specified stylesheet.
     * 
     * 
     * @return Class name list.
     */
    @Returns("classNames")
    List<String> collectClassNames(String styleSheetId);

    /**
     * Creates a new special "via-inspector" stylesheet in the frame with given <code>frameId</code>.
     * 
     * @param frameId Identifier of the frame where "via-inspector" stylesheet should be created.
     * 
     * @return Identifier of the created "via-inspector" stylesheet.
     */
    @Returns("styleSheetId")
    String createStyleSheet(String frameId);

    /**
     * Disables the CSS agent for the given page.
     */
    void disable();

    /**
     * Enables the CSS agent for the given page. Clients should not assume that the CSS agent has been
     * enabled until the result of this command is received.
     */
    void enable();

    /**
     * Ensures that the given node will have specified pseudo-classes whenever its style is computed by
     * the browser.
     * 
     * @param nodeId The element id for which to force the pseudo state.
     * @param forcedPseudoClasses Element pseudo classes to force when computing the element's style.
     */
    void forcePseudoState(Integer nodeId, List<String> forcedPseudoClasses);

    /**
     * 
     * @return GetBackgroundColorsResult
     */
    GetBackgroundColorsResult getBackgroundColors(Integer nodeId);

    /**
     * Returns the computed style for a DOM node identified by <code>nodeId</code>.
     * 
     * 
     * @return Computed style for the specified DOM node.
     */
    @Returns("computedStyle")
    List<CSSComputedStyleProperty> getComputedStyleForNode(Integer nodeId);

    /**
     * Returns the styles defined inline (explicitly in the "style" attribute and implicitly, using DOM
     * attributes) for a DOM node identified by <code>nodeId</code>.
     * 
     * 
     * @return GetInlineStylesForNodeResult
     */
    GetInlineStylesForNodeResult getInlineStylesForNode(Integer nodeId);

    /**
     * Returns requested styles for a DOM node identified by <code>nodeId</code>.
     * 
     * 
     * @return GetMatchedStylesForNodeResult
     */
    GetMatchedStylesForNodeResult getMatchedStylesForNode(Integer nodeId);

    /**
     * Returns all media queries parsed by the rendering engine.
     */
    @Returns("medias")
    List<CSSMedia> getMediaQueries();

    /**
     * Requests information about platform fonts which we used to render child TextNodes in the given
     * node.
     * 
     * 
     * @return Usage statistics for every employed platform font.
     */
    @Returns("fonts")
    List<PlatformFontUsage> getPlatformFontsForNode(Integer nodeId);

    /**
     * Returns the current textual content for a stylesheet.
     * 
     * 
     * @return The stylesheet text.
     */
    @Returns("text")
    String getStyleSheetText(String styleSheetId);

    /**
     * Find a rule with the given active property for the given node and set the new value for this
     * property
     * 
     * @param nodeId The element id for which to set property.
     */
    void setEffectivePropertyValueForNode(Integer nodeId, String propertyName, String value);

    /**
     * Modifies the keyframe rule key text.
     * 
     * 
     * @return The resulting key text after modification.
     */
    @Returns("keyText")
    Value setKeyframeKey(String styleSheetId, SourceRange range, String keyText);

    /**
     * Modifies the rule selector.
     * 
     * 
     * @return The resulting CSS media rule after modification.
     */
    @Returns("media")
    CSSMedia setMediaText(String styleSheetId, SourceRange range, String text);

    /**
     * Modifies the rule selector.
     * 
     * 
     * @return The resulting selector list after modification.
     */
    @Returns("selectorList")
    SelectorList setRuleSelector(String styleSheetId, SourceRange range, String selector);

    /**
     * Sets the new stylesheet text.
     * 
     * 
     * @return URL of source map associated with script (if any).
     */
    @Returns("sourceMapURL")
    String setStyleSheetText(String styleSheetId, String text);

    /**
     * Applies specified style edits one after another in the given order.
     * 
     * 
     * @return The resulting styles after modification.
     */
    @Returns("styles")
    List<CSSStyle> setStyleTexts(List<StyleDeclarationEdit> edits);

    /**
     * Enables the selector recording.
     */
    void startRuleUsageTracking();

    /**
     * Stop tracking rule usage and return the list of rules that were used since last call to
     * <code>takeCoverageDelta</code> (or since start of coverage instrumentation)
     */
    @Returns("ruleUsage")
    List<RuleUsage> stopRuleUsageTracking();

    /**
     * Obtain list of rules that became used since last call to this method (or since start of coverage
     * instrumentation)
     */
    @Returns("coverage")
    List<RuleUsage> takeCoverageDelta();
}
