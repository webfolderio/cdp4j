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
package io.webfolder.cdp.type.accessibility;

import com.google.gson.annotations.SerializedName;

/**
 * Enum of possible property types
 */
public enum AXValueType {
    @SerializedName("boolean")
    Boolean("boolean"),

    @SerializedName("tristate")
    Tristate("tristate"),

    @SerializedName("booleanOrUndefined")
    BooleanOrUndefined("booleanOrUndefined"),

    @SerializedName("idref")
    Idref("idref"),

    @SerializedName("idrefList")
    IdrefList("idrefList"),

    @SerializedName("integer")
    Integer("integer"),

    @SerializedName("node")
    Node("node"),

    @SerializedName("nodeList")
    NodeList("nodeList"),

    @SerializedName("number")
    Number("number"),

    @SerializedName("string")
    String("string"),

    @SerializedName("computedString")
    ComputedString("computedString"),

    @SerializedName("token")
    Token("token"),

    @SerializedName("tokenList")
    TokenList("tokenList"),

    @SerializedName("domRelation")
    DomRelation("domRelation"),

    @SerializedName("role")
    Role("role"),

    @SerializedName("internalRole")
    InternalRole("internalRole"),

    @SerializedName("valueUndefined")
    ValueUndefined("valueUndefined");

    public final String value;

    AXValueType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
