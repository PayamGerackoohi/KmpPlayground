cmake_minimum_required(VERSION 3.5)
project(KmpPlayground)
set(CMAKE_CXX_STANDARD 20)
include_directories(include)
file(GLOB_RECURSE sources "src/*.cpp")
add_library(KmpPlayground SHARED jvm-bridge.cpp ${sources})
add_subdirectory(test)
