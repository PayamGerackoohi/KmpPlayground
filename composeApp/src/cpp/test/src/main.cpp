#include "math-util-test.hpp"
#include "test-util.hpp"
#include <iostream>

using std::cout;
using std::endl;

void conclusion(int count) {
  cout << count << " Test" << (count > 1 ? "s" : "") << " passed." << endl;
}

int main() {
  TestManager manager;
  manager.count += mathUtilTests();
  conclusion(manager.count);
  return 0;
}
