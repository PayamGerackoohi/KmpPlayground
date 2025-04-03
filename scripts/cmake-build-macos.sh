set -e
javaHome=/Library/Java/JavaVirtualMachines/amazon-corretto-17.jdk/Contents/Home/
echo Architecture: $(uname -m)

# Build
cd composeApp/src/cpp/
cmake -B build -S . -DCMAKE_CXX_FLAGS=\
-isystem\ "${javaHome}"include\ \
-isystem\ "${javaHome}"include/darwin
cmake --build build --config Release -j8

# Copy Binaries
mkdir -p ../desktopMain/resources/macos/
cp build/libKmpPlayground.dylib ../desktopMain/resources/macos/
mkdir -p ~/Library/Java/Extensions
cp build/libKmpPlayground.dylib ~/Library/Java/Extensions
