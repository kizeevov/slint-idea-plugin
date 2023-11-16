# Slint plugin for the IntelliJ Platform

[![Build](https://github.com/kizeevov/slint-idea-plugin/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/kizeevov/slint-idea-plugin/actions/workflows/build.yml)
[![Version](https://img.shields.io/jetbrains/plugin/v/23065.svg)](https://plugins.jetbrains.com/plugin/23065)
[![Downloads](https://img.shields.io/jetbrains/plugin/d/23065.svg)](https://plugins.jetbrains.com/plugin/23065)

## Description

<!-- Plugin description -->
[Slint](https://slint.dev) support for IDEs based on IntelliJ Platform. **Plugin is experimental and unofficial!**

The following features are supported:
- Syntax highlighting
- Slint-LSP support
- Preview support
<!-- Plugin description end -->

Tested with: 
- CLion 2023.2
- IDEA Ultimate 2023.2
- RustRover 2023.3 EAP

## Dependencies

Slint IntelliJ Plugin communicates with Slint-LSP. Install before you can use the IntelliJ Plugin. To install:
```sh
$ cargo install slint-lsp
```

## Installation

Using IDE built-in plugin system:
  
  <kbd>Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "slint"</kbd> >
  <kbd>Install Plugin</kbd>

Manually:

  Download the [latest release](https://github.com/kizeevov/slint-idea-plugin/releases) and install it manually using
  <kbd>Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

## Configuration

Go to <kbd>Settings</kbd> > <kbd>Languages & Frameworks</kbd> > <kbd>Slint</kbd> > <kbd>Slint-lsp path</kbd> selected path
