#include "ios-bridge.h"
#include "include/math-util.hpp"
#include <iostream>
#include <cstring>

const char *nativePrimeFactors(const char * text) {
    auto result = MathUtil::primeFactors(text);
    auto size = result.size() + 1;
    char *cString = new char[size];
    std::strncpy(cString, result.c_str(), size);
    return cString;
}
