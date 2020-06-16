cdp4j - Web automation libray for Java
======================================

**IMPORTANT**

master branch (v5.0) is not stable.
Prefer to use [v4.x](https://github.com/webfolderio/cdp4j/tree/4.x) branch.


cdp4j is Java library with a clear and concise API to automate Chrome/Chromium based browser. It use Google Chrome DevTools Protocol to automate Chrome/Chromium based browsers.

What can you do with cdp4j?
---------------------------
* Automatically fill-out forms. You can easily download and upload of text, images, handle PDF files, or take screenshots.
* Import or export data to and from web applications.
* Extract Any Kind of Data from the Web.
* Automate Web Upload/Download.

Main Features
-------------
* Supports full capabilities of the Chrome DevTools Protocol ([tip-of-tree](https://chromedevtools.github.io/debugger-protocol-viewer/tot/))
* PDF printer
* Evaluate JavaScript
* Invoke JavaScript function
* Invoke Java function from Browser (JavaScript)
* Supports native CSS selector engine
* Supports XPath queries
* Incognito Browsing (private tab)
* Full page screen capture
* Support parallel running
* Auto-Wait for DOM change completion
* Trigger Mouse events (click etc...)
* Send keys (text, tab, enter etc...)
* Redirect log entries (javascript, network, storage etc...) from browser to slf4j, log4j or console logger.
* Intercept Network traffic (request & response)
* Upload file programmatically without third party solutions (does not requires AWT Robot etc...)
* get & set Element properties
* Supports Headless Chrome/Chromium
* Navigate back, forward, stop, reload
* clear cache, clear cookies, list cookies
* set & get values of form elements
* Supports event handling
* Supports all well known Java WebSocket libraries.

Supported Java Versions
-----------------------

Oracle/OpenJDK, GraalVM & Substrate VM.

Both the JRE and the JDK are suitable for use with this library.

__Note__: We only support LTS versions (8 & 11).

Stability
---------
This library is suitable for use in production systems. Our library is used by many well known enterprise customers.

Download
--------
[cdp4j-4.2.2.jar](https://github.com/webfolderio/cdp4j/releases/download/4.2.2/cdp4j-4.2.2.jar)

[cdp4j-4.2.2-sources.jar](https://github.com/webfolderio/cdp4j/releases/download/4.2.2/cdp4j-4.2.2-sources.jar)

Supported Platforms
-------------------
cdp4j has been tested under Windows 10 and Ubuntu, but should work on any platform where a Java 8+/Graal VM/Substrate VM & Chrome/Chromium/Microsoft Edge available.

__Note__: Although we do not execute test suites on Mac, many customers run cdp4j without any problem. Please do not hesitate to report bugs related with Mac.

Release Notes
-------------
[CHANGELOG.md](https://github.com/webfolderio/cdp4j/blob/master/CHANGELOG.md)

Headless Mode
-------------
cdp4j can be run in "headless" mode using with `Options.headless(boolean)` option.

### Install Chrome on Debian/Ubuntu

```bash
# https://askubuntu.com/questions/79280/how-to-install-chrome-browser-properly-via-command-line
sudo apt-get install libxss1 libappindicator1 libappindicator3-1 libindicator7
wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
sudo dpkg -i google-chrome*.deb # Might show "errors", fixed by next line
sudo apt-get install -f
```

### Install Chrome on RHEL/CentOS/Fedora
```bash
wget https://dl.google.com/linux/direct/google-chrome-stable_current_x86_64.rpm
sudo yum install google-chrome-stable_current_*.rpm
```

JavaDoc
-------
[cdp4j api](https://webfolder.io/cdp4j/javadoc/index.html)

Logging
-------
slf4j 1.x, log4j 1.x and custom Console logger is supported.

__Note:__ Please let us know if your preferred logging library is not listed.

WebSocket Protocol
------------------
DevTools Protocol uses WebSocket protocol to automate Chromium based browser. We supports the following Java WebSocket libraries.

* [Jre WebSocket Library](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/JreWebSocketConnection.java) (requires Java 11+).
* [Jetty](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/JettyWebSocketConnection.java)
* [Undertow](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/UndertowWebSocketConnection.java)
* [Vertx](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/VertxWebSocketConnection.java)
* [Tyrus](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/TyrusWebSocketConnection.java)
* [TooTallNateWebSocket](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/TooTallNateWebSocketConnection.java)
* [Tomcat](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/TomcatWebSocketConnection.java)
* [Netty](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/NettyWebSocketConnection.java)
* [NvWebSocket](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/NvWebSocketConnection.java)

__Note:__ Please let us know if your preferred WebSocket library is not listed.

Examples
-------
We provide many [examples](https://github.com/webfolderio/cdp4j/tree/master/src/test/java/io/webfolder/cdp/sample) to show how to use cdp4j library. It's highly recommended to run these examples before starting to use cdp4j.

Design Principles
-----------------
* Avoid external dependencies as much as possible.
* Support only Chrome/Chromium based browsers.
* Supports full capabilities of the Chrome DevTools Protocol.
* Keep the API simple.
* Support GraalVM & Substrate VM.

How it is tested
----------------
cdp4j is regularly built and tested on Windows 10 and Ubuntu.

Support & Bug Report
--------------------
Please report your bugs and new features by e-mail ([support@webfolder.io](mailto:support@webfolder.io)). github issues is only used by cdp4j developers.

License
-------
cdp4j is a proprietary software which means that you or your company needs [commercial license](https://webfolder.io/license.html) to use cdp4j.

Trial
-----
You are free to use this library for only __development purpose__. It's not require to [buy](https://webfolder.io/buy.html) commercial for trial usage. Production systems requires commercial license without any exception.
