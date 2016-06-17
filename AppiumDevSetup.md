# Testing Mobile Apps using Appium

<div id='summary' />
## Summary
Tutorial on how to setup Appium for mobile automation testing.  Click to see a <a href="https://vimeo.com/171041592">video</a>.

----

##### Table of Contents
- [Summary](#summary)
- [Development Setup](#dev-setup)
- [Requirements](#requirements)
- [Tutorial](#tutorial)

----

<div id='dev-setup' />
### Development Setup

<div id='requirements' />
#### Requirements
On your development macbook please install the following software:

| software | version                 |
| ---      | ---                     |
| xcode    | latest (from App Store) |
| brew     | latest                  |
| java     | 1.8                     |
| git      | latest                  |
| appium   | latest                  |
| eclipse or intellij | latest (recommend the free intellij community edition) |

##### xcode
```
$ xcode-select --install     # you can also install directly from the App Store
```

##### brew
```
$ ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
$ brew doctor
$ brew update
$ brew upgrade
```

##### java
```
$ brew install java          # or download/install from oracle site
```

##### git
```
$ brew install git
```

##### appium
```
$ brew install Caskroom/cask/appium            # download from website install binaries
```

##### java ide
* __eclipse__   (or click to <a href="http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/marsr">download</a>)
```
$ brew install Caskroom/cask/eclipse-java
```
* __intellij__   (or click to <a href="https://www.jetbrains.com/idea/downloads">download</a>)
```
$ brew install Caskroom/cask/intellij-java
```
	
<div id='tutorial' />
### Tutorial
1. Download source code for a Sample iOS App called UICatalog.
      * https://github.com/appium/ios-uicatalog
2. Build the sample app using XCode.
3. Run the sample app using Appium Server.
4. Run a test using Java IDE with Selenium WebDriver to hit the sample ios mobile app UICatalog.
      * https://github.com/software-entomologist/appium-dev-setup
