# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

### Added
- Gitflow workflow documentation
- Comprehensive authentication testing guide

### Changed
- Simplified authentication validation rules
- Removed password complexity requirements

### Fixed
- Database driver implementations for iOS and JS
- AlertDialog implementations for desktop and JS platforms

## [1.1.0] - 2024-12-19

### Added
- User authentication system (login/signup)
- Local SQLite database for user storage
- Multi-platform support (Android, iOS, Desktop, Web)
- Navigation between login, signup, and main screens
- Token-based session management
- Basic form validation

### Changed
- Refactored authentication flow to remove encryption complexity
- Simplified password validation to basic requirements
- Updated database drivers for better platform compatibility

### Fixed
- Resolved compilation issues across platforms
- Fixed AlertDialog implementations for all platforms
- Removed unused HashUtils implementations

### Security
- **Note**: Passwords are stored in plain text for development/testing purposes
- **Warning**: This implementation is not suitable for production use

## [1.0.0] - 2024-12-18

### Added
- Initial CatKMP project setup
- Kotlin Multiplatform configuration
- Basic cat listing functionality
- Favorite cats feature
- Multi-platform UI with Compose Multiplatform
- SQLDelight database integration
- Koin dependency injection setup

### Platforms
- Android support
- iOS support  
- Desktop support
- Web (JS) support

### Features
- Cat list display
- Cat details view
- Add/remove favorites
- Local database persistence
- Cross-platform navigation

---

## Version History

- **1.0.0**: Initial release with basic cat functionality
- **1.1.0**: Authentication system implementation
- **Unreleased**: Gitflow workflow and documentation improvements

## Contributing

When contributing to this project, please:

1. Follow the [Conventional Commits](https://www.conventionalcommits.org/) specification
2. Update this CHANGELOG.md file with your changes
3. Use semantic versioning for releases
4. Follow the Gitflow workflow

## Release Process

1. Create a release branch from `develop`
2. Update version numbers in build files
3. Update this CHANGELOG.md
4. Create a Pull Request to `main`
5. After merge, create a git tag
6. Merge back to `develop`
7. Delete the release branch 