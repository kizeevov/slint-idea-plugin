# Slint plugin for the IntelliJ Platform

## Description

[Slint](https://slint.dev) support for IDEs based on IntelliJ Platform.

**Unofficial! Slint-plugin is currently experimental plugin!**

Tested with: 
- CLion 2023.2
- IDEA Ultimate 2023.2

## Dependencies

Slint IntelliJ Plugin communicates with Slint Language Server Protocol (LSP). Install Slint-LSP before you can use the IntelliJ Plugin. To install Slint-LSP:
```sh
$ cargo install slint-lsp
```

> need version 1.2.2 and above

## Installation

- Manually:

  Download the [latest release](https://github.com/kizeevov/slint-idea-plugin/releases) and install it manually using
  <kbd>Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

## Configuration

Go to <kbd>Settings</kbd> > <kbd>Languages & Frameworks</kbd> > <kbd>Slint</kbd> > <kbd>Slint-lsp path</kbd> selected path

## Features

- [ ] highlighting
- [x] support slint-lsp
- [ ] plugin configuration