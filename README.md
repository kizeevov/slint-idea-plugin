# Slint plugin for the IntelliJ Platform

[![Build](https://github.com/kizeevov/slint-idea-plugin/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/kizeevov/slint-idea-plugin/actions/workflows/build.yml)
[![Version](https://img.shields.io/jetbrains/plugin/v/23065.svg)](https://plugins.jetbrains.com/plugin/23065)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/23065.svg)](https://plugins.jetbrains.com/plugin/23065)

## Description

<!-- Plugin description -->
[Slint](https://slint.dev) support for IDEs based on IntelliJ Platform. **Plugin is unofficial!**

The following features are supported:
- Syntax highlighting
- Preview support
- Code completion
- Document formatting
- Pick elements
- Drag and Drop elements on Live-Preview
<!-- Plugin description end -->

## Dependencies

Slint IntelliJ Plugin communicates with Slint LSP. Language server are included in plugin assembly (in version 1.0.0 and later).

But you can use an external dependency. Go to <kbd>Settings</kbd> > <kbd>Languages & Frameworks</kbd> > <kbd>Slint</kbd> > <kbd>Slint-lsp path</kbd> selected path

## Installation

Using IDE built-in plugin system:
  
  <kbd>Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "slint"</kbd> >
  <kbd>Install Plugin</kbd>

Manually:

  Download the [latest release](https://github.com/kizeevov/slint-idea-plugin/releases) and install it manually using
  <kbd>Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>
