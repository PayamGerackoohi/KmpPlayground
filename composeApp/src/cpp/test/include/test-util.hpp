#ifndef TEST_UTIL_HPP
#define TEST_UTIL_HPP

#include <string>

struct TestManager {
  int count = 0;
};

void assertWithMessage(bool condition, std::string message);

#endif
