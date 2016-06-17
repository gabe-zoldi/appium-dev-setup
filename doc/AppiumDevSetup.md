# My Table of content
- [Section 1](#id-section1)
- [Section 2](#id-section2)

<div id='id-section1'/>
## Section 1
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d

<div id='id-section2'/>
## Section 2
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d
d

----

# Testing Mobile Apps using Appium

Summary: Tutorial on how to setup Appium for mobile automation testing.

## Development Setup

### Requirements
On your development macbook please install the following software:
	•	xcode - app store
	•	brew - latest
	•	java - jdk1.8
	•	git - latest
	•	appium - latest
	•	eclipse/intellij (recommend: intellij community edition free)

xcode:
$ xcode-select --install  # also to install get directly from the App Store

brew:
$ ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"
$ brew doctor
$ brew update
$ brew upgrade

java:
$ brew install java   # or download/install from oracle site

git:
$ brew install git

appium:
$ brew install Caskroom/cask/appium  # download from website install binaries

java ide:
eclipse
$ brew install Caskroom/cask/eclipse-java
	…or download directly from http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/marsr
intellij
$ brew install Caskroom/cask/intellij-java
	…or download directly from https://www.jetbrains.com/idea/downloads  (community edition free)
	
	
## Agenda
1. Download source code for a Sample iOS App called UICatalog.
	⁃  https://github.com/appium/ios-uicatalog
2. Build the sample app using XCode.
3. Run the sample app using Appium Server.
4. Finally run a test using Java IDE with Selenium WebDriver to hit the sample ios mobile app UICatalog.
	⁃  https://github.com/software-entomologist/symantec
