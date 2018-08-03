cdp4j Release Notes
-------------------------------------------------------------------------------

### 3.0.5 - August 31, 2018

* Fixed NullPointerException

### 3.0.4 - July 31, 2018

* Added Added a new Constructor to Launcher class
* Fixed ClassNotFoundException (org.jvnet.winp.WinProcess)

### 3.0.3 - July 26, 2018

* Added MacOsProcessManager
* Roll devtools protocol to r574367
* Added Dom.getClickablePoint() and Dom.scrollIntoViewIfNeeded() method
* Fixed Mouse.click() does not trigger click event if the element is not visible.

### 3.0.2 - July 9, 2018

* Fixed ElementNotFoundException for xpath expressions
* Fixed the wrong OS detection code and added human-readable error in case OS cannot be detected (ProcessManager)

### 3.0.1 - June 16, 2018

* Removed sizzle support
* Fixed concurrency bug (incognito mode)
* Added Session.navigateAndWait() method
* Added guard for Launcher.launch #95
* Removed NullProcessManager (AdaptiveProcessManager is the new default ProcessManager)
* Added Session.captureScreenshot(boolean hideScrollbar) method
