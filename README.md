cdp4j - Chrome DevTools Protocol for Java
=================================================

cdp4j is a web-automation library for Java. It can be used for automating the use of web pages and for testing web pages.
It use Google Chrome DevTools Protocol to automate Chrome/Chromium based browsers.

Features
--------
* Supports full capabilities of the Chrome DevTools Protocol ([tip-of-tree](https://chromedevtools.github.io/debugger-protocol-viewer/tot/))
* Evaluate JavaScript
* Invoke JavaScript function
* Supports native CSS selector engine
* Supports XPath queries
* Incognito Browsing (private tab)
* Full page screen capture
* Trigger Mouse events (click etc...)
* Send keys (text, tab, enter etc...)
* Redirect log entries (javascript, network, storage etc...) from browser to slf4j
* Intercept Network (request & response)
* Upload file programmatically without third party solutions (does not requires AWT Robot etc...)
* get & set Element properties
* Supports Headless Chrome/Chromium
* Navigate back, forward, stop, reload
* clear cache, clear cookies, list cookies
* set & get values of form elements
* Supports event handling

Supported Java Versions
-----------------------

Oracle/OpenJDK & GraalVM.

_Note_: We only support LTS versions (8 & 11).

Both the JRE and the JDK are suitable for use with this library.

Stability
---------
This library is suitable for use in production systems.

Download
--------
[cdp4j-4.2.0.jar](https://github.com/webfolderio/cdp4j/releases/download/4.2.0/cdp4j-4.2.0.jar)

[cdp4j-4.2.0-sources.jar](https://github.com/webfolderio/cdp4j/releases/download/4.2.0/cdp4j-4.2.0-sources.jar)

Supported Platforms
-------------------
cdp4j has been tested under Windows 10 and Ubuntu, but should work on any platform where a Java & Chrome/Chromium available.

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

Design Principles
-----------------
* Avoid external dependencies as much as possible.
* Support only Chrome/Chromium based browsers.
* Supports full capabilities of the Chrome DevTools Protocol.
* Keep the API simple.
* Support GraalVM.

How it is tested
----------------
cdp4j is regularly built and tested on Windows 10 and Ubuntu.

Support
-------
Please report your bugs and new features by e-mail ([support@webfolder.io](mailto:support@webfolder.io)). github issues is only used by cdp4j developers.
