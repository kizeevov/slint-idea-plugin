<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# Slint-Idea-Plugin Changelog

## [Unreleased]

### Attention

- The current version is not compatible with previous plugin settings!

### Added

- Binary executable files of lsp-server are embedded
- Automatic restart of the LSP-server if it crashed or shutdown
- New action for manually restarting the LSP-server
- Widget for indicating the LSP-server current status
- Pick elements with your mouse by enabling the "Design Mode"
- Drag and Drop on Live-Preview

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

[Unreleased]: https://github.com/kizeevov/slint-idea-plugin/compare/v0.3.1...HEAD
[0.3.1]: https://github.com/kizeevov/slint-idea-plugin/compare/v0.2.7...v0.3.1
[0.2.7]: https://github.com/kizeevov/slint-idea-plugin/compare/v0.2.6...v0.2.7
[0.2.6]: https://github.com/kizeevov/slint-idea-plugin/commits/v0.2.6
