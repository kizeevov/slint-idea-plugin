# Slint plugin for the IntelliJ Platform

## Description

[Slint](https://slint.dev) support for IDEs based on IntelliJ Platform. **Plugin is experimental and unofficial!**

Tested with: 
- CLion 2023.2
- IDEA Ultimate 2023.2
- RustRover 2023.3 EAP

## Dependencies

Slint IntelliJ Plugin communicates with Slint-LSP. Install before you can use the IntelliJ Plugin. To install:
```sh
$ cargo install slint-lsp
```

> need version 1.2.2 and above

## Installation

- Using IDE built-in plugin system:
  
  <kbd>Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "slint"</kbd> >
  <kbd>Install Plugin</kbd>

- Manually:

  Download the [latest release](https://github.com/kizeevov/slint-idea-plugin/releases) and install it manually using
  <kbd>Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>⚙️</kbd> > <kbd>Install plugin from disk...</kbd>

## Configuration

Go to <kbd>Settings</kbd> > <kbd>Languages & Frameworks</kbd> > <kbd>Slint</kbd> > <kbd>Slint-lsp path</kbd> selected path

## Features

- [x] highlighting
- [x] slint-lsp support
- [x] preview support
- [x] plugin configuration
