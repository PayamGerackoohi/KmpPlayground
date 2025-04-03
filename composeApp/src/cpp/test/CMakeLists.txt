include_directories(include)
file(GLOB_RECURSE sources "src/*.cpp")
add_executable(KmpPlaygroundTest ${sources})
target_link_libraries(KmpPlaygroundTest KmpPlayground)
