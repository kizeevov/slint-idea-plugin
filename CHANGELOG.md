<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# Slint-Idea-Plugin Changelog

## [Unreleased]

### Features

- Slint-LSP binary v1.9.2

## [1.4.0] - 2024-12-29

### Features

- Slint-LSP binary v1.9.1

### Fixed

- Fixed `@tr` syntax ([issue 65](https://github.com/kizeevov/slint-idea-plugin/issues/65))
- Fixed `gradient-steps()` array syntax ([issue 65](https://github.com/kizeevov/slint-idea-plugin/issues/65))

## [1.3.0] - 2024-11-22

### Features

- Slint-LSP binary v1.8.0

### Fixed

- Fixed `ActionUpdateThread.OLD_EDT` error ([issue 61](https://github.com/kizeevov/slint-idea-plugin/issues/61))
- Fixed `@tr` syntax ([issue 65](https://github.com/kizeevov/slint-idea-plugin/issues/65))

## [1.2.0] - 2024-07-30

### Features

- Embedded preview by editor from the vscode plugin (experimental and only preview is supported)
- Slint-LSP binary v1.7.1

## [1.1.0] - 2024-06-01

### Features

- Support for IDEA 2024
- Syntax highlighting support in IDEA Community Edition and Android Studio (2024 and above)
- Slint-LSP binary v1.6.0

## [1.0.2] - 2024-03-23

### Fixed

- Fixed ternary expressions again
- Fixed starting of lsp-server

## [1.0.1] - 2024-03-20

### Attention

- The current version is not compatible with previous plugin settings!

### Added

- Binary executable files of lsp-server are embedded
- Automatic restart of the LSP-server if it crashed or shutdown
- New action for manually restarting the LSP-server
- Widget for indicating the LSP-server current status
- Pick elements with your mouse by enabling the "Design Mode"
- Drag and Drop on Live-Preview
- Document formatting support

### Fixed

- Fixed ternary expressions

## [0.3.1] - 2023-12-16

### Added

- Added an action to create a new empty file
- Added folding imports
- Added a handler for brackets and buckets
- Added property autocomplete
- Support relative-font-size (rem)
- Added settings for import paths
- Added lsp-server settings
- Restarting the lsp-server after changing the settings

### Fixed

- Fixed linear marker preview
- Fixed line marker performance warning

## [0.2.7] - 2023-11-15

### Fixed

- export error; 
- repetition error; 
- conditional element error; 
- model declaration error;

## [0.2.6] - 2023-11-13

### Fixed

- color error for hex colors with alpha channel;
- state declaration error;
- export error;
- error declaring global singletons;
- property binding error;
- element declaration error;
- error declaring an animation block;
- image declaration error;
- gradient declaration error;

### Added

- added highlighting of numeric values with units;

[Unreleased]: https://github.com/kizeevov/slint-idea-plugin/compare/v1.4.0...HEAD
[1.4.0]: https://github.com/kizeevov/slint-idea-plugin/compare/v1.3.0...v1.4.0
[1.3.0]: https://github.com/kizeevov/slint-idea-plugin/compare/v1.2.0...v1.3.0
[1.2.0]: https://github.com/kizeevov/slint-idea-plugin/compare/v1.1.0...v1.2.0
[1.1.0]: https://github.com/kizeevov/slint-idea-plugin/compare/v1.0.2...v1.1.0
[1.0.2]: https://github.com/kizeevov/slint-idea-plugin/compare/v1.0.1...v1.0.2
[1.0.1]: https://github.com/kizeevov/slint-idea-plugin/compare/v0.3.1...v1.0.1
[0.3.1]: https://github.com/kizeevov/slint-idea-plugin/compare/v0.2.7...v0.3.1
[0.2.7]: https://github.com/kizeevov/slint-idea-plugin/compare/v0.2.6...v0.2.7
[0.2.6]: https://github.com/kizeevov/slint-idea-plugin/commits/v0.2.6
