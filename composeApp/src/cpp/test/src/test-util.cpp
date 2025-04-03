#include "test-util.hpp"
#include <cassert>
#include <iostream>

using std::cout;
using std::endl;
using std::string;

void assertWithMessage(bool condition, string message) {
  if (!condition) {
    cout << message << endl;
    assert(condition);
  }
}
