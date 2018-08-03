cdp4j - Chrome DevTools Protocol for Java
=================================================

[![License](https://img.shields.io/badge/license-AGPL-blue.svg)](https://github.com/webfolderio/cdp4j/blob/master/LICENSE)
[![circleci](https://img.shields.io/circleci/project/github/webfolderio/cdp4j.svg?label=linux)](https://circleci.com/gh/webfolderio/cdp4j) [![AppVeyor](https://img.shields.io/appveyor/ci/WebFolder/cdp4j.svg?label=windows)](https://ci.appveyor.com/project/WebFolder/cdp4j) [![Codacy Badge](https://api.codacy.com/project/badge/Grade/66358e2e3fc1495aaad32e3bf7dc99bd)](https://www.codacy.com/app/WebFolder/cdp4j?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=webfolderio/cdp4j&amp;utm_campaign=Badge_Grade)
[![CLA assistant](https://cla-assistant.io/readme/badge/webfolderio/cdp4j)](https://cla-assistant.io/webfolderio/cdp4j)
[![Gitter](https://img.shields.io/gitter/room/WebFolderIO/cdp4j-community.svg)](https://gitter.im/WebFolderIO/cdp4j-community)

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

Oracle & OpenJDK Java 8, 9 & 10.

Both the JRE and the JDK are suitable for use with this library.

Licensing
---------

cdp4j is licensed as [AGPL](https://github.com/webfolderio/cdp4j/blob/master/LICENSE) software.

__Open Source != Free Software__. The source may be viewable on GitHub but that doesn't mean anyone can use it for any purpose.

Buying a license is __mandatory__ as soon as you develop commercial activities distributing the
cdp4j software inside your product or deploying it on a network without disclosing the source code of your own applications under the AGPL license.

Stability
---------
This library is suitable for use in production systems.

Community
---------
Join our gitter chat at: https://gitter.im/WebFolderIO/cdp4j-community

Integration with Maven
----------------------

To use the official release of cdp4j, please use the following snippet in your `pom.xml` file.

Add the following to your POM's `<dependencies>` tag:

```xml
<dependency>
    <groupId>io.webfolder</groupId>
    <artifactId>cdp4j</artifactId>
    <version>3.0.6-SNAPSHOT</version>
</dependency>
```

Using development (SNAPSHOT) version:

```xml
<dependency>
    <groupId>io.webfolder</groupId>
    <artifactId>cdp4j</artifactId>
    <version>3.0.5-SNAPSHOT</version>
</dependency>
```

Download
--------
[cdp4j-3.0.5.jar](https://search.maven.org/remotecontent?filepath=io/webfolder/cdp4j/3.0.5/cdp4j-3.0.5.jar) - 842 KB

[cdp4j-3.0.5-sources.jar](https://search.maven.org/remotecontent?filepath=io/webfolder/cdp4j/3.0.5/cdp4j-3.0.5-sources.jar) - 556 KB

Supported Platforms
-------------------
cdp4j has been tested under Windows 10 and Ubuntu, but should work on any platform where a Java & Chrome/Chromium available.

Release Notes
-------------
[CHANGELOG.md](https://github.com/webfolderio/cdp4j/blob/master/CHANGELOG.md)

Headless Mode
-------------
cdp4j can be run in "headless" mode using with [Headless Chrome](https://chromium.googlesource.com/chromium/src/+/lkgr/headless/README.md).

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

Test headless Chrome

```bash
google-chrome --headless --remote-debugging-port=9222 --disable-gpu
```

JavaDoc
-------
[cdp4j api](https://webfolder.io/cdp4j/javadoc/index.html)

Logging
-------
Simple logger for Java (SLF4J) is supported.

Design Principles
-----------------
* Avoid external dependencies as much as possible.
* Support only Chrome/Chromium based browsers.
* Supports full capabilities of the Chrome DevTools Protocol.
* Keep the API simple.


Usage Examples
--------------

### Print text content with cdp4j

```java
try (Launcher launcher = new Launcher();
                    SessionFactory factory = launcher.launch();
                    Session session = factory.create()) {
    session.navigateAndWait("https://webfolder.io");
    String content = (String) session.getProperty("//body", "outerText");
    System.out.println(content);
}
```

### Full page screen capture with cdp4j

```java
Path file = createTempFile("screenshot", ".png");
try (Launcher launcher = new Launcher();
                    SessionFactory factory = launcher.launch();
                    Session session = factory.create()) {
    session.navigateAndWait("https://news.ycombinator.com");
    // activate the tab/session before capturing the screenshot
    session.activate();
    byte[] data = session.captureScreenshot();
    write(file, data);
}
if (isDesktopSupported()) {
    getDesktop().open(file.toFile());
}
```

### Print to PDF with cdp4j

```java
Path file = createTempFile("webfolder-linux-setup", ".pdf");
try (Launcher launcher = new Launcher();
                    SessionFactory factory = launcher.launch(asList("--headless", "--disable-gpu"))) {
    String context = factory.createBrowserContext();
    try (Session session = factory.create(context)) {
        session.navigateAndWait("https://webfolder.io?cdp4j");
        session.wait(1000);
        byte[] content = session
                            .getCommand()
                            .getPage()
                            .printToPDF();
        write(file, content);
        if (isDesktopSupported()) {
            getDesktop().open(file.toFile());
        }
    }
}
```

Samples
-------
| - | -  | -  | -  |
| ------- | -------- | -------- | -------- |
| [Attributes.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/Attributes.java) | [Bing.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/Bing.java) | [BingTranslator.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/BingTranslator.java) | [CheckBox.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/CheckBox.java) |
| [ExecuteJavascript.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/ExecuteJavascript.java) | [GoogleTranslate.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/GoogleTranslate.java) | [HelloWorld.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/HelloWorld.java) | [IncognitoBrowsing.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/IncognitoBrowsing.java) |
| [Logging.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/Logging.java) | [MultiSelect.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/MultiSelect.java) | [NetworkResponse.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/NetworkResponse.java) | [Screenshot.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/Screenshot.java) |
| [Select.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/Select.java) | [SendKeys.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/SendKeys.java) | [SharedSession.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/SharedSession.java) | [EvaluateOnNewDocument.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/EvaluateOnNewDocument.java) |
| [UserAgent.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/UserAgent.java) | [WaitUntil.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/WaitUntil.java) | [XPathSelector.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/XPathSelector.java) | [CodeCoverage.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/CodeCoverage.java) |
| [PrintToPDF.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/PrintToPDF.java) | [BasicAuthentication.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/BasicAuthentication.java) | [DownloadFile.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/DownloadFile.java) | [Crawler.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/Crawler.java) |
| [MultiProcess.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/MultiProcess.java) | [FollowRedirects.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/FollowRedirects.java) | [CloseSessionOnRedirect.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/CloseSessionOnRedirect.java) | [Readability.java](https://github.com/webfolderio/cdp4j/blob/master/src/test/java/io/webfolder/cdp/sample/Readability.java) |

Building cdp4j
--------------

```sh
# Assume that you have `google-chrome` in your $PATH
mvn install

# If you use different version of Google Chrome like Chromium, Chrome Canary,
# then you must explicitly use the `chrome_binary` property to make the code work.

mvn install -Dchrome_binary=/path/to/your/google-chrome

# e.g. For some Linux distribution
mvn install -Dchrome_binary=/usr/lib/chromium-dev/chromium-dev

# e.g. MacOS it may be something like
mvn install -Dchrome_binary=/Applications/Chromium.app/Contents/MacOS/Chromium

# To run the existing tests try
mvn test -Dchrome_binary=/usr/lic/chromium-dev/chromium-dev
```

How it is tested
----------------
cdp4j is regularly built and tested on [circleci](https://circleci.com/gh/webfolderio/cdp4j) and [AppVeyor](https://ci.appveyor.com/project/WebFolder/cdp4j).

Contribute to cdp4j Project
---------------------------
Please follow cdp4j Contributor's [Guide](https://github.com/webfolderio/cdp4j/blob/master/CONTRIBUTING.md).

Getting Help
--------------------------------------------------------------------------------------------

![WebFolder](https://raw.githubusercontent.com/webfolderio/cdp4j/master/images/logo.png)

You can support cdp4j development by **buying** support package. Please [contact us](https://webfolder.io/support.html) for support packages & pricing.
