#include "math-util.hpp"
#include <charconv>
#include <sstream>
#include <vector>

using std::errc;
using std::from_chars;
using std::string;
using std::stringstream;
using std::vector;

// t*do int128 issue
// using uint128_t = unsigned __int128;
// std::ostream &operator<<(std::ostream &os, uint128_t number) {
//   if (number == 0)
//     os << "0";
//   else {
//     std::stringstream ss;
//     while (number > 0) {
//       ss << static_cast<int>(number % 10);
//       number /= 10;
//     }
//     auto str = ss.str();
//     std::reverse(str.begin(), str.end());
//     os << str;
//   }
//   return os;
// }

template <typename T> struct Factor {
  T base;
  int power = 1;
};

template <typename T> struct Factors {
  T lastBase = 1;
  vector<Factor<T>> values;

  void push(T a) {
    if (lastBase != a) {
      values.emplace_back(Factor<T>{a});
      lastBase = a;
    } else
      (values.end() - 1)->power++;
  }

  string str() {
    stringstream ss;
    for (int i = 0; i < values.size(); i++) {
      if (i != 0)
        ss << "*";
      auto value = values[i];
      ss << value.base;
      if (value.power > 1) {
        ss << '^';
        ss << value.power;
      }
    }
    return ss.str();
  }
};

// t*do the algorithm is not efficient for int128 calculations, e.g. 2^128-2
template <typename T> string factors(T n) {
  if (n == 1)
    return "1";
  Factors<T> factors;
  T last = sqrt(n);
  for (T i = 2; i <= last; i++) {
    if (n % i == 0) {
      do {
        factors.push(i);
        n /= i;
      } while (n % i == 0);
      last = sqrt(n);
    }
  }
  if (n != 1)
    factors.push(n);
  return factors.str();
}

string MathUtil::primeFactors(string text) {
  const char *cStr = text.c_str();
  auto cStrEnd = cStr + text.size();
  uint32_t value32;
  auto ec = from_chars(cStr, cStrEnd, value32).ec;
  if (ec == errc())
    return factors(value32);
  // else if (ec == errc::invalid_argument)
  // return "Not a Number";
  else if (ec == errc::result_out_of_range) {
    uint64_t value64;
    ec = from_chars(cStr, cStrEnd, value64).ec;
    if (ec == errc())
      return factors(value64);
    else if (ec == errc::result_out_of_range) {
      // t*do where INT128 is available?
      // #ifdef BOOST_HAS_INT128
      // uint128_t value128;
      // ec = from_chars(cStr, cStrEnd, value128).ec;
      // if (ec == errc())
      //   return factors(value128);
      // else if (ec == errc::result_out_of_range)
      //   return "Too Big Number";
      // #else
      return "Too Big Number";
      // #endif
    }
  }
  return "Not a Number";
}
