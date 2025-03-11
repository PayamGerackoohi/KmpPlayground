# [KMP Playground](https://payamgerackoohi.github.io/KmpPlayground/)

A sample Kotlin Multi-platform project to demonstrate different app features, targeted on Android, iOS and Desktop.

## Screens

### [Screenshots](http://localhost:63342/KmpPlayground/docs/screenshots/screenshots.html)

### Home

### Compose
- [x] Components
  - [x] Carousel
  - [x] Dialog
  - [x] Date/Time Picker
  - [x] Pull to Refresh
- [x] Animations
  - [x] animate*As
  - [x] Animated Content
  - [x] Animated Visibility
  - [x] Crossfade

### Graphics
- [ ] Charts
- [x] Color Scheme
- [x] Icons
- [ ] OpenGL

### I/O
- [ ] Datastore
  - [x] Key-Value
  - [ ] Protobuf
- [ ] Database
  - [ ] Room
  - [ ] SqlDelight
- [ ] API
  - [ ] REST
  - [ ] GraphQL
  - [ ] GRPC
- [ ] File

### Miscellaneous
- [x] Date/Time
- [ ] C++
- [ ] BLE
- [ ] PDF

## Build
### Android
Android Studio :: Build >> Build App Bundle(s) / APK(s) >> Build APK(s)

### MacOS
Install Java Coretto 17
#### Application Image (.app)
```sh
gradle createDistributable
gradle createReleaseDistributable
```
#### Installer Package (.dmg)
```sh
gradle packageDmg
gradle packageReleaseDmg
```

#### Info
- jpackage
- [jlink](https://openjdk.org/jeps/282)
```kotlin
compose.desktop {
  application {
    nativeDistributions {
      // In order to include runtime dependencies to the final package file
      modules("java.sql")
      // Alternatively: includeAllModules = true (no minification)
      macOS {
        signing {
          sign.set(true)
          identity.set("John Doe")
          // keychain.set("/path/to/keychain") 
        }
      }
    }
  }
}
// inaccurate: suggestModules?
```
- [MacOS Signing](https://github.com/JetBrains/compose-multiplatform/blob/master/tutorials/Signing_and_notarization_on_macOS/README.md)
- [conveyor](https://www.hydraulic.software/)
