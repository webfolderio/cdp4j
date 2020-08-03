const cdp4j = { };

/**
 * Copyright (c) Microsoft Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * https://github.com/microsoft/playwright/blob/master/src/common/selectorParser.ts
 * 
 */

// This file can't have dependencies, it is a part of the utility script
cdp4j.parseSelector = function(selector) {
    let index = 0;
    let quote;
    let start = 0;
    const result = { parts: [] };
    const append = () => {
        const part = selector.substring(start, index).trim();
        const eqIndex = part.indexOf('=');
        let name;
        let body;
        if (eqIndex !== -1 && part.substring(0, eqIndex).trim().match(/^[a-zA-Z_0-9-+:*]+$/)) {
            name = part.substring(0, eqIndex).trim();
            body = part.substring(eqIndex + 1);
        }
        else if (part.length > 1 && part[0] === '"' && part[part.length - 1] === '"') {
            name = 'text';
            body = part;
        }
        else if (part.length > 1 && part[0] === "'" && part[part.length - 1] === "'") {
            name = 'text';
            body = part;
        }
        else if (/^\(*\/\//.test(part) || part.startsWith('..')) {
            // If selector starts with '//' or '//' prefixed with multiple opening
            // parenthesis, consider xpath. @see https://github.com/microsoft/playwright/issues/817
            // If selector starts with '..', consider xpath as well.
            name = 'xpath';
            body = part;
        }
        else {
            name = 'css';
            body = part;
        }
        let capture = false;
        if (name[0] === '*') {
            capture = true;
            name = name.substring(1);
        }
        result.parts.push({ name, body });
        if (capture) {
            if (result.capture !== undefined)
                throw new Error(`Only one of the selectors can capture using * modifier`);
            result.capture = result.parts.length - 1;
        }
    };
    while (index < selector.length) {
        const c = selector[index];
        if (c === '\\' && index + 1 < selector.length) {
            index += 2;
        }
        else if (c === quote) {
            quote = undefined;
            index++;
        }
        else if (!quote && (c === '"' || c === '\'' || c === '`')) {
            quote = c;
            index++;
        }
        else if (!quote && c === '>' && selector[index + 1] === '>') {
            append();
            index += 2;
            start = index;
        }
        else {
            index++;
        }
    }
    append();
    return result;
};


/**
 * Copyright (c) Microsoft Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * https://github.com/microsoft/playwright/blob/master/src/injected/injectedScript.ts
 *
 */
cdp4j.engine = (function () {
	'use strict';

	var commonjsGlobal = typeof globalThis !== 'undefined' ? globalThis : typeof window !== 'undefined' ? window : typeof global !== 'undefined' ? global : typeof self !== 'undefined' ? self : {};

	function createCommonjsModule(fn, basedir, module) {
		return module = {
		  path: basedir,
		  exports: {},
		  require: function (path, base) {
	      return commonjsRequire(path, (base === undefined || base === null) ? module.path : base);
	    }
		}, fn(module, module.exports), module.exports;
	}

	function getCjsExportFromNamespace (n) {
		return n && n['default'] || n;
	}

	function commonjsRequire () {
		throw new Error('Dynamic requires are not currently supported by @rollup/plugin-commonjs');
	}

	var attributeSelectorEngine = createCommonjsModule(function (module, exports) {
	"use strict";
	/**
	 * Copyright (c) Microsoft Corporation.
	 *
	 * Licensed under the Apache License, Version 2.0 (the "License");
	 * you may not use this file except in compliance with the License.
	 * You may obtain a copy of the License at
	 *
	 * http://www.apache.org/licenses/LICENSE-2.0
	 *
	 * Unless required by applicable law or agreed to in writing, software
	 * distributed under the License is distributed on an "AS IS" BASIS,
	 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	 * See the License for the specific language governing permissions and
	 * limitations under the License.
	 */
	Object.defineProperty(exports, "__esModule", { value: true });
	exports.createAttributeEngine = void 0;
	function createAttributeEngine(attribute, shadow) {
	    const engine = {
	        create(root, target) {
	            const value = target.getAttribute(attribute);
	            if (!value)
	                return;
	            if (engine.query(root, value) === target)
	                return value;
	        },
	        query(root, selector) {
	            if (!shadow)
	                return root.querySelector(`[${attribute}=${JSON.stringify(selector)}]`) || undefined;
	            return queryShadowInternal(root, attribute, selector);
	        },
	        queryAll(root, selector) {
	            if (!shadow)
	                return Array.from(root.querySelectorAll(`[${attribute}=${JSON.stringify(selector)}]`));
	            const result = [];
	            queryShadowAllInternal(root, attribute, selector, result);
	            return result;
	        }
	    };
	    return engine;
	}
	exports.createAttributeEngine = createAttributeEngine;
	function queryShadowInternal(root, attribute, value) {
	    const single = root.querySelector(`[${attribute}=${JSON.stringify(value)}]`);
	    if (single)
	        return single;
	    const all = root.querySelectorAll('*');
	    for (let i = 0; i < all.length; i++) {
	        const shadowRoot = all[i].shadowRoot;
	        if (shadowRoot) {
	            const single = queryShadowInternal(shadowRoot, attribute, value);
	            if (single)
	                return single;
	        }
	    }
	}
	function queryShadowAllInternal(root, attribute, value, result) {
	    const document = root instanceof Document ? root : root.ownerDocument;
	    const walker = document.createTreeWalker(root, NodeFilter.SHOW_ELEMENT);
	    const shadowRoots = [];
	    while (walker.nextNode()) {
	        const element = walker.currentNode;
	        if (element.getAttribute(attribute) === value)
	            result.push(element);
	        if (element.shadowRoot)
	            shadowRoots.push(element.shadowRoot);
	    }
	    for (const shadowRoot of shadowRoots)
	        queryShadowAllInternal(shadowRoot, attribute, value, result);
	}

	});

	var cssSelectorEngine = createCommonjsModule(function (module, exports) {
	"use strict";
	/**
	 * Copyright (c) Microsoft Corporation.
	 *
	 * Licensed under the Apache License, Version 2.0 (the "License");
	 * you may not use this file except in compliance with the License.
	 * You may obtain a copy of the License at
	 *
	 * http://www.apache.org/licenses/LICENSE-2.0
	 *
	 * Unless required by applicable law or agreed to in writing, software
	 * distributed under the License is distributed on an "AS IS" BASIS,
	 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	 * See the License for the specific language governing permissions and
	 * limitations under the License.
	 */
	Object.defineProperty(exports, "__esModule", { value: true });
	exports.createCSSEngine = void 0;
	function createCSSEngine(shadow) {
	    const engine = {
	        create(root, targetElement) {
	            if (shadow)
	                return;
	            const tokens = [];
	            function uniqueCSSSelector(prefix) {
	                const path = tokens.slice();
	                if (prefix)
	                    path.unshift(prefix);
	                const selector = path.join(' > ');
	                const nodes = Array.from(root.querySelectorAll(selector));
	                return nodes[0] === targetElement ? selector : undefined;
	            }
	            for (let element = targetElement; element && element !== root; element = element.parentElement) {
	                const nodeName = element.nodeName.toLowerCase();
	                // Element ID is the strongest signal, use it.
	                let bestTokenForLevel = '';
	                if (element.id) {
	                    const token = /^[a-zA-Z][a-zA-Z0-9\-\_]+$/.test(element.id) ? '#' + element.id : `[id="${element.id}"]`;
	                    const selector = uniqueCSSSelector(token);
	                    if (selector)
	                        return selector;
	                    bestTokenForLevel = token;
	                }
	                const parent = element.parentElement;
	                // Combine class names until unique.
	                const classes = Array.from(element.classList);
	                for (let i = 0; i < classes.length; ++i) {
	                    const token = '.' + classes.slice(0, i + 1).join('.');
	                    const selector = uniqueCSSSelector(token);
	                    if (selector)
	                        return selector;
	                    // Even if not unique, does this subset of classes uniquely identify node as a child?
	                    if (!bestTokenForLevel && parent) {
	                        const sameClassSiblings = parent.querySelectorAll(token);
	                        if (sameClassSiblings.length === 1)
	                            bestTokenForLevel = token;
	                    }
	                }
	                // Ordinal is the weakest signal.
	                if (parent) {
	                    const siblings = Array.from(parent.children);
	                    const sameTagSiblings = siblings.filter(sibling => (sibling).nodeName.toLowerCase() === nodeName);
	                    const token = sameTagSiblings.length === 1 ? nodeName : `${nodeName}:nth-child(${1 + siblings.indexOf(element)})`;
	                    const selector = uniqueCSSSelector(token);
	                    if (selector)
	                        return selector;
	                    if (!bestTokenForLevel)
	                        bestTokenForLevel = token;
	                }
	                else if (!bestTokenForLevel) {
	                    bestTokenForLevel = nodeName;
	                }
	                tokens.unshift(bestTokenForLevel);
	            }
	            return uniqueCSSSelector();
	        },
	        query(root, selector) {
	            // TODO: uncomment for performance.
	            // const simple = root.querySelector(selector);
	            // if (simple)
	            //   return simple;
	            // if (!shadow)
	            //   return;
	            const selectors = split(selector);
	            // Note: we do not just merge results produced by each selector, as that
	            // will not return them in the tree traversal order, but rather in the selectors
	            // matching order.
	            if (!selectors.length)
	                return;
	            return queryShadowInternal(root, root, selectors, shadow);
	        },
	        queryAll(root, selector) {
	            // TODO: uncomment for performance.
	            // if (!shadow)
	            //   return Array.from(root.querySelectorAll(selector));
	            const result = [];
	            const selectors = split(selector);
	            // Note: we do not just merge results produced by each selector, as that
	            // will not return them in the tree traversal order, but rather in the selectors
	            // matching order.
	            if (selectors.length)
	                queryShadowAllInternal(root, root, selectors, shadow, result);
	            return result;
	        }
	    };
	    engine._test = () => test(engine);
	    return engine;
	}
	exports.createCSSEngine = createCSSEngine;
	function queryShadowInternal(boundary, root, selectors, shadow) {
	    let elements;
	    if (selectors.length === 1) {
	        // Fast path for a single selector - query only matching elements, not all.
	        const parts = selectors[0];
	        const matching = root.querySelectorAll(parts[0]);
	        for (const element of matching) {
	            // If there is a single part, there are no ancestors to match.
	            if (parts.length === 1 || ancestorsMatch(element, parts, boundary))
	                return element;
	        }
	    }
	    else {
	        // Multiple selectors: visit each element in tree-traversal order and check whether it matches.
	        elements = root.querySelectorAll('*');
	        for (const element of elements) {
	            for (const parts of selectors) {
	                if (!element.matches(parts[0]))
	                    continue;
	                // If there is a single part, there are no ancestors to match.
	                if (parts.length === 1 || ancestorsMatch(element, parts, boundary))
	                    return element;
	            }
	        }
	    }
	    // Visit shadow dom after the light dom to preserve the tree-traversal order.
	    if (!shadow)
	        return;
	    if (root.shadowRoot) {
	        const child = queryShadowInternal(boundary, root.shadowRoot, selectors, shadow);
	        if (child)
	            return child;
	    }
	    if (!elements)
	        elements = root.querySelectorAll('*');
	    for (const element of elements) {
	        if (element.shadowRoot) {
	            const child = queryShadowInternal(boundary, element.shadowRoot, selectors, shadow);
	            if (child)
	                return child;
	        }
	    }
	}
	function queryShadowAllInternal(boundary, root, selectors, shadow, result) {
	    let elements;
	    if (selectors.length === 1) {
	        // Fast path for a single selector - query only matching elements, not all.
	        const parts = selectors[0];
	        const matching = root.querySelectorAll(parts[0]);
	        for (const element of matching) {
	            // If there is a single part, there are no ancestors to match.
	            if (parts.length === 1 || ancestorsMatch(element, parts, boundary))
	                result.push(element);
	        }
	    }
	    else {
	        // Multiple selectors: visit each element in tree-traversal order and check whether it matches.
	        elements = root.querySelectorAll('*');
	        for (const element of elements) {
	            for (const parts of selectors) {
	                if (!element.matches(parts[0]))
	                    continue;
	                // If there is a single part, there are no ancestors to match.
	                if (parts.length === 1 || ancestorsMatch(element, parts, boundary))
	                    result.push(element);
	            }
	        }
	    }
	    // Visit shadow dom after the light dom to preserve the tree-traversal order.
	    if (!shadow)
	        return;
	    if (root.shadowRoot)
	        queryShadowAllInternal(boundary, root.shadowRoot, selectors, shadow, result);
	    if (!elements)
	        elements = root.querySelectorAll('*');
	    for (const element of elements) {
	        if (element.shadowRoot)
	            queryShadowAllInternal(boundary, element.shadowRoot, selectors, shadow, result);
	    }
	}
	function ancestorsMatch(element, parts, boundary) {
	    let i = 1;
	    while (i < parts.length && (element = parentElementOrShadowHost(element)) && element !== boundary) {
	        if (element.matches(parts[i]))
	            i++;
	    }
	    return i === parts.length;
	}
	function parentElementOrShadowHost(element) {
	    if (element.parentElement)
	        return element.parentElement;
	    if (!element.parentNode)
	        return;
	    if (element.parentNode.nodeType === Node.DOCUMENT_FRAGMENT_NODE && element.parentNode.host)
	        return element.parentNode.host;
	}
	// Splits the string into separate selectors by comma, and then each selector by the descendant combinator (space).
	// Parts of each selector are reversed, so that the first one matches the target element.
	function split(selector) {
	    let index = 0;
	    let quote;
	    let insideAttr = false;
	    let start = 0;
	    const result = [];
	    let current = [];
	    const appendToCurrent = () => {
	        const part = selector.substring(start, index).trim();
	        if (part.length)
	            current.push(part);
	    };
	    const appendToResult = () => {
	        appendToCurrent();
	        result.push(current);
	        current = [];
	    };
	    const isCombinator = (char) => {
	        return char === '>' || char === '+' || char === '~';
	    };
	    const peekForward = () => {
	        return selector.substring(index).trim()[0];
	    };
	    const peekBackward = () => {
	        const s = selector.substring(0, index).trim();
	        return s[s.length - 1];
	    };
	    while (index < selector.length) {
	        const c = selector[index];
	        if (!quote && !insideAttr && c === ' ' && !isCombinator(peekForward()) && !isCombinator(peekBackward())) {
	            appendToCurrent();
	            start = index;
	            index++;
	        }
	        else {
	            if (c === '\\' && index + 1 < selector.length) {
	                index += 2;
	            }
	            else if (c === quote) {
	                quote = undefined;
	                index++;
	            }
	            else if (!quote && (c === '\'' || c === '"')) {
	                quote = c;
	                index++;
	            }
	            else if (!quote && c === '[') {
	                insideAttr = true;
	                index++;
	            }
	            else if (!quote && insideAttr && c === ']') {
	                insideAttr = false;
	                index++;
	            }
	            else if (!quote && !insideAttr && c === ',') {
	                appendToResult();
	                index++;
	                start = index;
	            }
	            else {
	                index++;
	            }
	        }
	    }
	    appendToResult();
	    return result.filter(parts => !!parts.length).map(parts => parts.reverse());
	}
	function test(engine) {
	    let id = 0;
	    function createShadow(level) {
	        const root = document.createElement('div');
	        root.id = 'id' + id;
	        root.textContent = 'root #id' + id;
	        id++;
	        const shadow = root.attachShadow({ mode: 'open' });
	        for (let i = 0; i < 9; i++) {
	            const div = document.createElement('div');
	            div.id = 'id' + id;
	            div.textContent = '#id' + id;
	            id++;
	            shadow.appendChild(div);
	        }
	        if (level) {
	            shadow.appendChild(createShadow(level - 1));
	            shadow.appendChild(createShadow(level - 1));
	        }
	        return root;
	    }
	    const { query, queryAll } = engine;
	    document.body.textContent = '';
	    document.body.appendChild(createShadow(10));
	    console.time('found');
	    for (let i = 0; i < id; i += 17) {
	        const e = query(document, `div #id${i}`);
	        if (!e || e.id !== 'id' + i)
	            console.log(`div #id${i}`); // eslint-disable-line no-console
	    }
	    console.timeEnd('found');
	    console.time('not found');
	    for (let i = 0; i < id; i += 17) {
	        const e = query(document, `div div div div div #d${i}`);
	        if (e)
	            console.log(`div div div div div #d${i}`); // eslint-disable-line no-console
	    }
	    console.timeEnd('not found');
	    console.log(query(document, '#id543 + #id544')); // eslint-disable-line no-console
	    console.log(query(document, '#id542 ~ #id545')); // eslint-disable-line no-console
	    console.time('all');
	    queryAll(document, 'div div div + div');
	    console.timeEnd('all');
	}

	});

	var textSelectorEngine = createCommonjsModule(function (module, exports) {
	"use strict";
	/**
	 * Copyright (c) Microsoft Corporation.
	 *
	 * Licensed under the Apache License, Version 2.0 (the "License");
	 * you may not use this file except in compliance with the License.
	 * You may obtain a copy of the License at
	 *
	 * http://www.apache.org/licenses/LICENSE-2.0
	 *
	 * Unless required by applicable law or agreed to in writing, software
	 * distributed under the License is distributed on an "AS IS" BASIS,
	 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	 * See the License for the specific language governing permissions and
	 * limitations under the License.
	 */
	Object.defineProperty(exports, "__esModule", { value: true });
	exports.createTextSelector = void 0;
	function createTextSelector(shadow) {
	    const engine = {
	        create(root, targetElement, type) {
	            const document = root instanceof Document ? root : root.ownerDocument;
	            if (!document)
	                return;
	            for (let child = targetElement.firstChild; child; child = child.nextSibling) {
	                if (child.nodeType === 3 /* Node.TEXT_NODE */) {
	                    const text = child.nodeValue;
	                    if (!text)
	                        continue;
	                    if (text.match(/^\s*[a-zA-Z0-9]+\s*$/) && engine.query(root, text.trim()) === targetElement)
	                        return text.trim();
	                    if (queryInternal(root, createMatcher(JSON.stringify(text)), shadow) === targetElement)
	                        return JSON.stringify(text);
	                }
	            }
	        },
	        query(root, selector) {
	            return queryInternal(root, createMatcher(selector), shadow);
	        },
	        queryAll(root, selector) {
	            const result = [];
	            queryAllInternal(root, createMatcher(selector), shadow, result);
	            return result;
	        }
	    };
	    return engine;
	}
	exports.createTextSelector = createTextSelector;
	function unescape(s) {
	    if (!s.includes('\\'))
	        return s;
	    const r = [];
	    let i = 0;
	    while (i < s.length) {
	        if (s[i] === '\\' && i + 1 < s.length)
	            i++;
	        r.push(s[i++]);
	    }
	    return r.join('');
	}
	function createMatcher(selector) {
	    if (selector.length > 1 && selector[0] === '"' && selector[selector.length - 1] === '"') {
	        const parsed = unescape(selector.substring(1, selector.length - 1));
	        return text => text === parsed;
	    }
	    if (selector.length > 1 && selector[0] === "'" && selector[selector.length - 1] === "'") {
	        const parsed = unescape(selector.substring(1, selector.length - 1));
	        return text => text === parsed;
	    }
	    if (selector[0] === '/' && selector.lastIndexOf('/') > 0) {
	        const lastSlash = selector.lastIndexOf('/');
	        const re = new RegExp(selector.substring(1, lastSlash), selector.substring(lastSlash + 1));
	        return text => re.test(text);
	    }
	    selector = selector.trim().toLowerCase();
	    return text => text.toLowerCase().includes(selector);
	}
	// Skips <head>, <script> and <style> elements and all their children.
	const nodeFilter = {
	    acceptNode: node => {
	        return node.nodeName === 'HEAD' || node.nodeName === 'SCRIPT' || node.nodeName === 'STYLE' ?
	            NodeFilter.FILTER_REJECT : NodeFilter.FILTER_ACCEPT;
	    }
	};
	// If we are querying inside a filtered element, nodeFilter is never called, so we need a separate check.
	function isFilteredNode(root, document) {
	    return root.nodeName === 'SCRIPT' || root.nodeName === 'STYLE' || document.head && document.head.contains(root);
	}
	function queryInternal(root, matcher, shadow) {
	    const document = root instanceof Document ? root : root.ownerDocument;
	    if (isFilteredNode(root, document))
	        return;
	    const walker = document.createTreeWalker(root, NodeFilter.SHOW_TEXT | NodeFilter.SHOW_ELEMENT, nodeFilter);
	    const shadowRoots = [];
	    if (shadow && root.shadowRoot)
	        shadowRoots.push(root.shadowRoot);
	    let lastTextParent = null;
	    let lastText = '';
	    while (true) {
	        const node = walker.nextNode();
	        const textParent = (node && node.nodeType === Node.TEXT_NODE) ? node.parentElement : null;
	        if (lastTextParent && textParent !== lastTextParent) {
	            if (matcher(lastText))
	                return lastTextParent;
	            lastText = '';
	        }
	        lastTextParent = textParent;
	        if (!node)
	            break;
	        if (node.nodeType === Node.TEXT_NODE) {
	            lastText += node.nodeValue;
	        }
	        else {
	            const element = node;
	            if ((element instanceof HTMLInputElement) && (element.type === 'submit' || element.type === 'button') && matcher(element.value))
	                return element;
	            if (shadow && element.shadowRoot)
	                shadowRoots.push(element.shadowRoot);
	        }
	    }
	    for (const shadowRoot of shadowRoots) {
	        const element = queryInternal(shadowRoot, matcher, shadow);
	        if (element)
	            return element;
	    }
	}
	function queryAllInternal(root, matcher, shadow, result) {
	    const document = root instanceof Document ? root : root.ownerDocument;
	    if (isFilteredNode(root, document))
	        return;
	    const walker = document.createTreeWalker(root, NodeFilter.SHOW_TEXT | NodeFilter.SHOW_ELEMENT, nodeFilter);
	    const shadowRoots = [];
	    if (shadow && root.shadowRoot)
	        shadowRoots.push(root.shadowRoot);
	    let lastTextParent = null;
	    let lastText = '';
	    while (true) {
	        const node = walker.nextNode();
	        const textParent = (node && node.nodeType === Node.TEXT_NODE) ? node.parentElement : null;
	        if (lastTextParent && textParent !== lastTextParent) {
	            if (matcher(lastText))
	                result.push(lastTextParent);
	            lastText = '';
	        }
	        lastTextParent = textParent;
	        if (!node)
	            break;
	        if (node.nodeType === Node.TEXT_NODE) {
	            lastText += node.nodeValue;
	        }
	        else {
	            const element = node;
	            if ((element instanceof HTMLInputElement) && (element.type === 'submit' || element.type === 'button') && matcher(element.value))
	                result.push(element);
	            if (shadow && element.shadowRoot)
	                shadowRoots.push(element.shadowRoot);
	        }
	    }
	    for (const shadowRoot of shadowRoots)
	        queryAllInternal(shadowRoot, matcher, shadow, result);
	}

	});

	var xpathSelectorEngine = createCommonjsModule(function (module, exports) {
	"use strict";
	/**
	 * Copyright (c) Microsoft Corporation.
	 *
	 * Licensed under the Apache License, Version 2.0 (the "License");
	 * you may not use this file except in compliance with the License.
	 * You may obtain a copy of the License at
	 *
	 * http://www.apache.org/licenses/LICENSE-2.0
	 *
	 * Unless required by applicable law or agreed to in writing, software
	 * distributed under the License is distributed on an "AS IS" BASIS,
	 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	 * See the License for the specific language governing permissions and
	 * limitations under the License.
	 */
	Object.defineProperty(exports, "__esModule", { value: true });
	exports.XPathEngine = void 0;
	const maxTextLength = 80;
	const minMeaningfulSelectorLegth = 100;
	exports.XPathEngine = {
	    create(root, targetElement, type) {
	        const maybeDocument = root instanceof Document ? root : root.ownerDocument;
	        if (!maybeDocument)
	            return;
	        const document = maybeDocument;
	        const xpathCache = new Map();
	        if (type === 'notext')
	            return createNoText(root, targetElement);
	        const tokens = [];
	        function evaluateXPath(expression) {
	            let nodes = xpathCache.get(expression);
	            if (!nodes) {
	                nodes = [];
	                try {
	                    const result = document.evaluate(expression, root, null, XPathResult.ORDERED_NODE_ITERATOR_TYPE);
	                    for (let node = result.iterateNext(); node; node = result.iterateNext()) {
	                        if (node.nodeType === Node.ELEMENT_NODE)
	                            nodes.push(node);
	                    }
	                }
	                catch (e) {
	                }
	                xpathCache.set(expression, nodes);
	            }
	            return nodes;
	        }
	        function uniqueXPathSelector(prefix) {
	            const path = tokens.slice();
	            if (prefix)
	                path.unshift(prefix);
	            let selector = '//' + path.join('/');
	            while (selector.includes('///'))
	                selector = selector.replace('///', '//');
	            if (selector.endsWith('/'))
	                selector = selector.substring(0, selector.length - 1);
	            const nodes = evaluateXPath(selector);
	            if (nodes[nodes.length - 1] === targetElement)
	                return selector;
	            // If we are looking at a small set of elements with long selector, fall back to ordinal.
	            if (nodes.length < 5 && selector.length > minMeaningfulSelectorLegth) {
	                const index = nodes.indexOf(targetElement);
	                if (index !== -1)
	                    return `(${selector})[${index + 1}]`;
	            }
	            return undefined;
	        }
	        function escapeAndCap(text) {
	            text = text.substring(0, maxTextLength);
	            // XPath 1.0 does not support quote escaping.
	            // 1. If there are no single quotes - use them.
	            if (text.indexOf(`'`) === -1)
	                return `'${text}'`;
	            // 2. If there are no double quotes - use them to enclose text.
	            if (text.indexOf(`"`) === -1)
	                return `"${text}"`;
	            // 3. Otherwise, use popular |concat| trick.
	            const Q = `'`;
	            return `concat(${text.split(Q).map(token => Q + token + Q).join(`, "'", `)})`;
	        }
	        const defaultAttributes = new Set(['title', 'aria-label', 'disabled', 'role']);
	        const importantAttributes = new Map([
	            ['form', ['action']],
	            ['img', ['alt']],
	            ['input', ['placeholder', 'type', 'name', 'value']],
	        ]);
	        let usedTextConditions = false;
	        for (let element = targetElement; element && element !== root; element = element.parentElement) {
	            const nodeName = element.nodeName.toLowerCase();
	            const tag = nodeName === 'svg' ? '*' : nodeName;
	            const tagConditions = [];
	            if (nodeName === 'svg')
	                tagConditions.push('local-name()="svg"');
	            const attrConditions = [];
	            const importantAttrs = [...defaultAttributes, ...(importantAttributes.get(tag) || [])];
	            for (const attr of importantAttrs) {
	                const value = element.getAttribute(attr);
	                if (value && value.length < maxTextLength)
	                    attrConditions.push(`normalize-space(@${attr})=${escapeAndCap(value)}`);
	                else if (value)
	                    attrConditions.push(`starts-with(normalize-space(@${attr}), ${escapeAndCap(value)})`);
	            }
	            const text = document.evaluate('normalize-space(.)', element).stringValue;
	            const textConditions = [];
	            if (tag !== 'select' && text.length && !usedTextConditions) {
	                if (text.length < maxTextLength)
	                    textConditions.push(`normalize-space(.)=${escapeAndCap(text)}`);
	                else
	                    textConditions.push(`starts-with(normalize-space(.), ${escapeAndCap(text)})`);
	                usedTextConditions = true;
	            }
	            // Always retain the last tag.
	            const conditions = [...tagConditions, ...textConditions, ...attrConditions];
	            const token = conditions.length ? `${tag}[${conditions.join(' and ')}]` : (tokens.length ? '' : tag);
	            const selector = uniqueXPathSelector(token);
	            if (selector)
	                return selector;
	            // Ordinal is the weakest signal.
	            const parent = element.parentElement;
	            let tagWithOrdinal = tag;
	            if (parent) {
	                const siblings = Array.from(parent.children);
	                const sameTagSiblings = siblings.filter(sibling => (sibling).nodeName.toLowerCase() === nodeName);
	                if (sameTagSiblings.length > 1)
	                    tagWithOrdinal += `[${1 + siblings.indexOf(element)}]`;
	            }
	            // Do not include text into this token, only tag / attributes.
	            // Topmost node will get all the text.
	            const nonTextConditions = [...tagConditions, ...attrConditions];
	            const levelToken = nonTextConditions.length ? `${tagWithOrdinal}[${nonTextConditions.join(' and ')}]` : tokens.length ? '' : tagWithOrdinal;
	            tokens.unshift(levelToken);
	        }
	        return uniqueXPathSelector();
	    },
	    query(root, selector) {
	        const document = root instanceof Document ? root : root.ownerDocument;
	        if (!document)
	            return;
	        const it = document.evaluate(selector, root, null, XPathResult.ORDERED_NODE_ITERATOR_TYPE);
	        for (let node = it.iterateNext(); node; node = it.iterateNext()) {
	            if (node.nodeType === Node.ELEMENT_NODE)
	                return node;
	        }
	    },
	    queryAll(root, selector) {
	        const result = [];
	        const document = root instanceof Document ? root : root.ownerDocument;
	        if (!document)
	            return result;
	        const it = document.evaluate(selector, root, null, XPathResult.ORDERED_NODE_ITERATOR_TYPE);
	        for (let node = it.iterateNext(); node; node = it.iterateNext()) {
	            if (node.nodeType === Node.ELEMENT_NODE)
	                result.push(node);
	        }
	        return result;
	    }
	};
	function createNoText(root, targetElement) {
	    const steps = [];
	    for (let element = targetElement; element && element !== root; element = element.parentElement) {
	        if (element.getAttribute('id')) {
	            steps.unshift(`//*[@id="${element.getAttribute('id')}"]`);
	            return steps.join('/');
	        }
	        const siblings = element.parentElement ? Array.from(element.parentElement.children) : [];
	        const similarElements = siblings.filter(sibling => element.nodeName === sibling.nodeName);
	        const index = similarElements.length === 1 ? 0 : similarElements.indexOf(element) + 1;
	        steps.unshift(index ? `${element.nodeName}[${index}]` : element.nodeName);
	    }
	    return '/' + steps.join('/');
	}

	});

	var injectedScript = createCommonjsModule(function (module, exports) {
	"use strict";
	/**
	 * Copyright (c) Microsoft Corporation.
	 *
	 * Licensed under the Apache License, Version 2.0 (the "License");
	 * you may not use this file except in compliance with the License.
	 * You may obtain a copy of the License at
	 *
	 * http://www.apache.org/licenses/LICENSE-2.0
	 *
	 * Unless required by applicable law or agreed to in writing, software
	 * distributed under the License is distributed on an "AS IS" BASIS,
	 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	 * See the License for the specific language governing permissions and
	 * limitations under the License.
	 */
	Object.defineProperty(exports, "__esModule", { value: true });




	class InjectedScript {
	    constructor(customEngines) {
	        this.engines = new Map();
	        // Note: keep predefined names in sync with Selectors class.
	        this.engines.set('css', cssSelectorEngine.createCSSEngine(true));
	        this.engines.set('css:light', cssSelectorEngine.createCSSEngine(false));
	        this.engines.set('xpath', xpathSelectorEngine.XPathEngine);
	        this.engines.set('xpath:light', xpathSelectorEngine.XPathEngine);
	        this.engines.set('text', textSelectorEngine.createTextSelector(true));
	        this.engines.set('text:light', textSelectorEngine.createTextSelector(false));
	        this.engines.set('id', attributeSelectorEngine.createAttributeEngine('id', true));
	        this.engines.set('id:light', attributeSelectorEngine.createAttributeEngine('id', false));
	        this.engines.set('data-testid', attributeSelectorEngine.createAttributeEngine('data-testid', true));
	        this.engines.set('data-testid:light', attributeSelectorEngine.createAttributeEngine('data-testid', false));
	        this.engines.set('data-test-id', attributeSelectorEngine.createAttributeEngine('data-test-id', true));
	        this.engines.set('data-test-id:light', attributeSelectorEngine.createAttributeEngine('data-test-id', false));
	        this.engines.set('data-test', attributeSelectorEngine.createAttributeEngine('data-test', true));
	        this.engines.set('data-test:light', attributeSelectorEngine.createAttributeEngine('data-test', false));
	        for (const { name, engine } of customEngines)
	            this.engines.set(name, engine);
	    }
	    querySelector(selector, root) {
	        if (!root['querySelector'])
	            throw new Error('Node is not queryable.');
	        return this._querySelectorRecursively(root, selector, 0);
	    }
	    _querySelectorRecursively(root, selector, index) {
	        const current = selector.parts[index];
	        if (index === selector.parts.length - 1)
	            return this.engines.get(current.name).query(root, current.body);
	        const all = this.engines.get(current.name).queryAll(root, current.body);
	        for (const next of all) {
	            const result = this._querySelectorRecursively(next, selector, index + 1);
	            if (result)
	                return selector.capture === index ? next : result;
	        }
	    }
	}
	exports.default = InjectedScript;

	});

	return injectedScript;

}());

cdp4j.engine = new cdp4j.engine.default([]);

cdp4j.querySelector = function(doc, selector) {
  let parsedSelector = cdp4j.parseSelector(selector);
  return cdp4j.engine.querySelector(parsedSelector, doc);
};
