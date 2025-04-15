# [KMP Playground](https://payamgerackoohi.github.io/KmpPlayground/)

A sample Kotlin Multiplatform project to demonstrate different app features, targeted on Android, iOS and Desktop.

## [Screens](http://localhost:63342/KmpPlayground/docs/screenshots/screenshots.html)

### Home
<!--suppress CheckImageSize -->
<img alt='Home' src='docs/screenshots/compressed/0.Home-English-Dark-Landscape-Small.webp' width='99%'/>

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

<div>
  <img alt='Carousel Preview' src='docs/screenshots/compressed/2.Components_1.Carousel_0.Preview-German-Dark-Portrait-Foldable.webp' width='49%'/>
  <img alt='Carousel Code' src='docs/screenshots/compressed/2.Components_1.Carousel_1.Code-German-Dark-Portrait-Foldable.webp' width='49%'/>
</div>

### Graphics
- [x] Color Scheme
- [x] Icons
- [ ] Charts
- [ ] OpenGL

<img alt='Icons' src='docs/screenshots/compressed/3.Graphics_2.Icons_0.Preview-English-Dark-Landscape-Small.webp' width='99%'/>
<img alt='Color Scheme Preview' src='docs/screenshots/compressed/3.Graphics_1.ColorScheme_0.Preview-English-Dark-Landscape-Small.webp' width='99%'/>
<img alt='Color Scheme Code' src='docs/screenshots/compressed/3.Graphics_1.ColorScheme_1.Code-Hebrew-Dark-Landscape-Small.webp' width='99%'/>

### I/O
- [ ] Datastore
  - [x] Key-Value
  - [ ] Protobuf
- [ ] Database
  - [ ] Room
  - [ ] SqlDelight
- [ ] API
  - [x] REST (Ktor)
  - [ ] GraphQL
  - [ ] GRPC
- [ ] File

<div>
  <img alt='DataStore Preview' src='docs/screenshots/compressed/4.Io_2.Datastore_0.Preview-English-Light-Portrait-Small.webp' width='49%'/>
  <img alt='DataStore Code' src='docs/screenshots/compressed/4.Io_2.Datastore_1.Code-Hebrew-Light-Portrait-Small.webp' width='49%'/>
</div>
<img alt='C++' src='docs/screenshots/compressed/4.Io_1.Api_0.Preview-English-Dark-Landscape-Tablet.webp' width='99%'/>

### Miscellaneous
- [x] Date/Time
- [x] C++
- [ ] BLE
- [ ] PDF

<img alt='Date-Time' src='docs/screenshots/compressed/5.Miscellaneous_2.DateTime_0.Preview-English-Dark-Landscape-Tablet.webp' width='99%'/>
<img alt='C++' src='docs/screenshots/compressed/5.Miscellaneous_1.C++_0.Preview-English-Dark-Landscape-Tablet.webp' width='99%'/>

### Modules
- [x] Clock
- [x] Editor
- [x] Math Factors
- [x] Random Image

<img alt='Date-Time' src='docs/screenshots/compressed/6.Modules_0.RandomImage-English-Light-Landscape-Desktop.webp' width='99%'/>

## Languages
- [English](docs/resources/values/strings.xml)
- [German](docs/resources/values-de/strings.xml)
- [Hebrew](docs/resources/values-iw/strings.xml)

## Themes
- Light
- Dark

## Code Quality and Automation
- [x] [Unit Tests](docs/reports/test/unit/index.html)
- [x] [UI Tests](docs/reports/test/ui/index.html)
- [x] [Coverage (Kover)](docs/reports/test/kover/index.html)
- [ ] CI/CD

## Build
### Android
Android Studio :: Build | Build App Bundle(s) / APK(s) | Build APK(s)

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

### C++
#### Android
The C++ code compiles during the gradle build process using `android.externalNativeBuild` tool.

#### MacOS
```shell
./scripts/cmake-build-macos.sh
```

#### iOS
- The `cpp` source code is added as a group inside xcode.
- `ios-bridge.h` is added in iosApp.xcodeproj | Build Settings | Swift Compiler - General | Objective-C Bridging Header 
- cpp sources are added in Build Phases | Compile Sources
- `libBridge.def` is configured in gradle to build kotlin interfaces out of c headers using cinterop tool 

#### Info
- jpackage
- [jlink](https://openjdk.org/jeps/282)
```kotlin
compose.desktop {
  application {
    nativeDistributions {
      // In order to include runtime dependencies to the final package file
      modules("java.sql")
      // gradle suggestModules
      // peek inside createDistributable app runtime legal folder (macOS)
      // Terrible Alternative: includeAllModules = true (no minification, even bigger than debug app!)
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
