#include "math-util-test.hpp"
#include "math-util.hpp"
#include "test-util.hpp"
#include <format>
#include <iostream>
#include <vector>

using MathUtil::primeFactors;
using std::cout;
using std::endl;
using std::format;
using std::string;
using std::vector;

int mathUtilTests() {
  TestManager manager;
  manager.count += primeFactors_Test();
  return manager.count;
}

namespace MathUtilTest {
template <typename T> struct Case {
  T input;
  string output;
};
} // namespace MathUtilTest

int primeFactors_Test() {
  vector<MathUtilTest::Case<string>> cases = {
      {"", "Not a Number"},
      {"-1", "Not a Number"},
      {"a1", "Not a Number"},
      {"2a3", "2"},
      {"0", "0"},
      {"1", "1"},
      {"2", "2"},
      {"3", "3"},
      {"4", "2^2"},
      {"5", "5"},
      {"6", "2 * 3"},
      {"7", "7"},
      {"8", "2^3"},
      {"9", "3^2"},
      {"10", "2 * 5"},
      {"11", "11"},
      {"12", "2^2 * 3"},
      {"13", "13"},
      {"14", "2 * 7"},
      {"15", "3 * 5"},
      {"16", "2^4"},
      {"17", "17"},
      {"18", "2 * 3^2"},
      {"19", "19"},
      {"20", "2^2 * 5"},
      {"21", "3 * 7"},
      {"22", "2 * 11"},
      {"23", "23"},
      {"24", "2^3 * 3"},
      {"25", "5^2"},
      {"26", "2 * 13"},
      {"27", "3^3"},
      {"28", "2^2 * 7"},
      {"29", "29"},
      {"30", "2 * 3 * 5"},
      {"31", "31"},
      {"32", "2^5"},
      {"100", "2^2 * 5^2"},
      {"1000", "2^3 * 5^3"},
      {"10000", "2^4 * 5^4"},
      {
          // UINT32_MAX - 1
          "4294967294",
          "2 * 2147483647",
      },
      {
          // UINT32_MAX
          "4294967295",
          "3 * 5 * 17 * 257 * 65537",
      },
      {
          // UINT32_MAX + 1
          "4294967296",
          "2^32",
      },
      {
          // UINT64_MAX - 1
          "18446744073709551614",
          "2 * 7^2 * 73 * 127 * 337 * 92737 * 649657",
      },
      {
          // UINT64_MAX
          "18446744073709551615",
          "3 * 5 * 17 * 257 * 641 * 65537 * 6700417",
      },
      {
          // UINT64_MAX + 1
          "18446744073709551616",
          "Too Big Number",
      },
      // {
      //     // UINT128_MAX  - 1
      //     "340282366920938463463374607431768211454",
      //     "?", // The calculation is infeasible, due to factorization
      //     deadend)
      //     t*do
      //     - Note: Perhaps it's better to make a database of primes and
      //     iterate over them to find the factors
      // },
      // {
      //     // UINT128_MAX
      //     "340282366920938463463374607431768211455",
      //     "3 * 5 * 17 * 257 * 641 * 65537 * 274177 * 6700417 *
      //     67280421310721",
      // },
      // {
      //     // UINT128_MAX + 1
      //     "340282366920938463463374607431768211456",
      //     "Too Big Number",
      // },
  };
  for (auto &c : cases) {
    auto result = primeFactors(c.input);
    assertWithMessage(
        result == c.output,
        format("primeFactors({})\nactual = '{}'\nexpected = '{}' ", c.input,
               result, c.output));
  }
  cout << "primeFactors - int - test passed" << endl;
  return 1;
}
