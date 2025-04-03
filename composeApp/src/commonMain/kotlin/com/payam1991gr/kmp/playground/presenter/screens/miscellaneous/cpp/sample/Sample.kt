package com.payam1991gr.kmp.playground.presenter.screens.miscellaneous.cpp.sample

import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*

fun CodeEditor.appendPresenter() {
    line { `class`; normal { " CppPresenter(" } }
    line(1) { normal { "randomULongGenerator: RandomULongGenerator," } }
    line(1) { orange { "private val" }; purple { " calculationCount" }; normal { ": Int = " }; cyan { "10" }; normal { "," } }
    line(1) { orange { "private val" }; purple { " bg" }; normal { ": CoroutineContext = Dispatchers." }; purple { "Default" }; normal { "," } }
    line { normal { ") : Presenter<State>, RandomULongGenerator " }; `by`; normal { " randomULongGenerator {" } }
    appendComposable(1)
    line(1) { orange { "override fun" }; blue { " present" }; normal { "(): State {" } }
    line(2) { `var`; normal { " canEdit " }; `by`; normal { " remember { mutableStateOf(" }; _true; normal { ") }" } }
    line(2) { `var`; normal { " canCalculate " }; `by`; normal { " remember { mutableStateOf(" }; _true; normal { ") }" } }
    line(2) { `var`; normal { " inputNumber " }; `by`; normal { " remember { mutableStateOf(" }; green { "\"\"" }; normal { ") }" } }
    line(2) { `var`; normal { " factors " }; `by`; normal { " remember { mutableStateOf<State.Number>(State.Number.Data()) }" } }
    line(2) { `var`; normal { " calculations " }; `by`; normal { " remember { mutableStateOf(persistentListOf<State.Number>()) }" } }
    line(2) { `val`; normal { " scope = rememberCoroutineScope()" } }
    line(2) { `return`; normal { " State(" } }
    line(3) { cyan { "canEdit =" }; normal { " canEdit," } }
    line(3) { cyan { "canCalculate =" }; normal { " canCalculate," } }
    line(3) { cyan { "inputNumber =" }; normal { " inputNumber," } }
    line(3) { cyan { "inputCalculation =" }; normal { " factors," } }
    line(3) { cyan { "calculations =" }; normal { " calculations," } }
    line(2) { normal { ") {" } }
    line(3) { `when`; normal { " (it) {" } }
    line(4) { `is`; normal { " Event.OnNumberChanged -> scope." }; blue { "launch" }; normal { "(" }; purple { "bg" }; normal { ") {" } }
    line(5) { normal { "canEdit = " }; _false }
    line(5) { normal { "it." }; purple { "number" }; normal { "." }; blue { "let" }; normal { " { n ->" } }
    line(6) { normal { "inputNumber = n" } }
    line(6) { normal { "factors = State.Number.Calculating(n)" } }
    line(6) { normal { "factors = State.Number.Data(n, Factor.parse(Native.primeFactors(n)))" } }
    line(5) { normal { "}" } }
    line(5) { normal { "canEdit = " }; _true }
    line(4) { normal { "}" } }
    line()
    line(4) { normal { "Event.PerformRandomCalculations -> scope." }; blue { "launch" }; normal { "(" }; purple { "bg" }; normal { ") {" } }
    line(5) { normal { "canCalculate = " }; _false }
    line(5) { `val`; normal { " list = mutableListOf<State.Number>()" } }
    line(5) { normal { "calculations = list." }; blue { "toPersistentList" }; normal { "()" } }
    line(5) { normal { "repeat(" }; purple { "calculationCount" }; normal { ") { index ->" } }
    line(6) { `val`; normal { " n = randomNumber()" } }
    line(6) { `val`; normal { " formattedNumber = n." }; blue { "decimalFormat" }; normal { "()" } }
    line(6) { normal { "list.add(State.Number.Calculating(formattedNumber))" } }
    line(6) { normal { "calculations = list." }; blue { "toPersistentList" }; normal { "()" } }
    line(6) { normal { "list[index] = State.Number.Data(" } }
    line(7) { normal { "formattedNumber," } }
    line(7) { normal { "Factor.parse(Native.primeFactors(n))," } }
    line(6) { normal { ")" } }
    line(6) { normal { "calculations = list." }; blue { "toPersistentList" }; normal { "()" } }
    line(5) { normal { "}" } }
    line(5) { normal { "canCalculate = " }; _true }
    line(4) { normal { "}" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendCpp() {
    appendMathUtilHpp()
    appendMathUtilCpp()
}

fun CodeEditor.appendMathUtilHpp() {
    line { gray { "// math-util.hpp" } }
    line { yellow { "#ifndef" }; olive { " MATH_UTIL_HPP" } }
    line { yellow { "#define" }; olive { " MATH_UTIL_HPP" } }
    line()
    line { yellow { "#include" }; green { " <string>" } }
    line()
    line { orange { "namespace" }; lavender { " MathUtil " }; normal { "{" } }
    line { lavender { "std" }; normal { "::string " }; blue { "primeFactors" }; normal { "(" }; lavender { "std" }; normal { "::string);" } }
    line { normal { "}" } }
    line()
    line { yellow { "#endif" } }
    line()
}

fun CodeEditor.appendMathUtilCpp() {
    line { gray { "// math-util.cpp" } }
    line { yellow { "#include" }; green { " \"math-util.hpp\"" } }
    line { yellow { "#include" }; green { " <charconv>" } }
    line { yellow { "#include" }; green { " <sstream>" } }
    line { yellow { "#include" }; green { " <vector>" } }
    line()
    line { orange { "using" }; lavender { " std" }; normal { "::" }; lavender { "errc" }; normal { ";" } }
    line { orange { "using" }; lavender { " std" }; normal { "::" }; blue { "from_chars" }; normal { ";" } }
    line { orange { "using" }; lavender { " std" }; normal { "::string;" } }
    line { orange { "using" }; lavender { " std" }; normal { "::stringstream;" } }
    line { orange { "using" }; lavender { " std" }; normal { "::" }; lavender { "vector" }; normal { ";" } }
    line()
    line { orange { "template" }; normal { " <" }; orange { "typename" }; normal { " T> " }; orange { "struct" }; lavender { " Factor" }; normal { " {" } }
    line(1) { normal { "T " }; purple { "base" }; normal { ";" } }
    line(1) { orange { "int" }; purple { " power" }; normal { " = " }; cyan { "1" }; normal { ";" } }
    line { normal { "};" } }
    line()
    line { orange { "template" }; normal { " <" }; orange { "typename" }; normal { " T> " }; orange { "struct" }; lavender { " Factors" }; normal { " {" } }
    line { orange { "public" }; normal { ":" } }
    line(1) { normal { "T " }; purple { "lastBase" }; normal { " = " }; cyan { "1" }; normal { ";" } }
    line(1) { lavender { "vector" }; normal { "<" }; lavender { "Factor" }; normal { "<T>> " }; purple { "values" }; normal { ";" } }
    line()
    line(1) { orange { "void" }; blue { " push" }; normal { "(T a) {" } }
    line(2) { orange { "if" }; normal { " (" }; purple { "lastBase" }; normal { " != a) {" } }
    line(3) { purple { "values" }; normal { ".emplace_back(" }; lavender { "Factor" }; normal { "<T>{a});" } }
    line(3) { purple { "lastBase" }; normal { " = a;" } }
    line(2) { normal { "} " }; orange { "else" } }
    line(3) { normal { "(" }; purple { "values" }; normal { ".end() - " }; cyan { "1" }; normal { ")->power++;" } }
    line(1) { normal { "}" } }
    line()
    line(1) { normal { "string " }; blue { "str" }; normal { "() {" } }
    line(2) { normal { "stringstream ss;" } }
    line(2) { orange { "for" }; normal { " (" }; orange { "int" }; normal { " i = " }; cyan { "0" }; normal { "; i < " }; purple { "values" }; normal { ".size(); i++) {" } }
    line(3) { orange { "if" }; normal { " (i != " }; cyan { "0" }; normal { ")" } }
    line(4) { normal { "ss" }; green { " << \"*\"" }; normal { ";" } }
    line(3) { orange { "auto" }; normal { " value = " }; purple { "values" }; normal { "[i];" } }
    line(3) { normal { "ss << value.base;" } }
    line(3) { orange { "if" }; normal { " (value.power > " }; cyan { "1" }; normal { ") {" } }
    line(4) { normal { "ss << " }; green { "'^'" }; normal { ";" } }
    line(4) { normal { "ss << value.power;" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(2) { orange { "return" }; normal { " ss.str();" } }
    line(1) { normal { "}" } }
    line { normal { "};" } }
    line()
    line { orange { "template" }; normal { " <" }; orange { "typename" }; normal { " T> string " }; blue { "factors" }; normal { "(T n) {" } }
    line(1) { orange { "if" }; normal { " (n == " }; cyan { "1" }; normal { ")" } }
    line(2) { orange { "return" }; green { " \"1\"" }; normal { ";" } }
    line(1) { lavender { "Factors" }; normal { "<T> factors;" } }
    line(1) { normal { "T last = sqrt(n);" } }
    line(1) { orange { "for" }; normal { " (T i = " }; cyan { "2" }; normal { "; i <= last; i++) {" } }
    line(2) { orange { "if" }; normal { " (n % i == " }; cyan { "0" }; normal { ") {" } }
    line(3) { orange { "do" }; normal { " {" } }
    line(4) { normal { "factors.push(i);" } }
    line(4) { normal { "n /= i;" } }
    line(3) { normal { "} " }; orange { "while" }; normal { " (n % i == 0);" } }
    line(3) { normal { "last = sqrt(n);" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line(1) { orange { "if" }; normal { " (n != " }; cyan { "1" }; normal { ")" } }
    line(2) { normal { "factors.push(n);" } }
    line(1) { orange { "return" }; normal { " factors.str();" } }
    line { normal { "}" } }
    line()
    line { normal { "string " }; lavender { "MathUtil" }; normal { "::" }; blue { "primeFactors" }; normal { "(string text) {" } }
    line(1) { orange { "const char" }; normal { " *cStr = text.c_str();" } }
    line(1) { orange { "auto" }; normal { " cStrEnd = cStr + text.size();" } }
    line(1) { normal { "uint32_t value32;" } }
    line(1) { orange { "auto" }; normal { " ec = from_chars(cStr, cStrEnd, value32)." }; purple { "ec" }; normal { ";" } }
    line(1) { orange { "if" }; normal { " (ec == " }; lavender { "errc" }; normal { "())" } }
    line(2) { orange { "return" }; normal { " factors(value32);" } }
    line(1) { orange { "else if" }; normal { " (ec == errc::" }; purple { "result_out_of_range" }; normal { ") {" } }
    line(2) { normal { "uint64_t value64;" } }
    line(2) { normal { "ec = from_chars(cStr, cStrEnd, value64)." }; purple { "ec" }; normal { ";" } }
    line(2) { orange { "if" }; normal { " (ec == " }; lavender { "errc" }; normal { "())" } }
    line(3) { orange { "return" }; normal { " factors(value64);" } }
    line(2) { orange { "else if" }; normal { " (ec == errc::" }; purple { "result_out_of_range" }; normal { ") {" } }
    line(3) { orange { "return" }; green { " \"Too Big Number\"" }; normal { ";" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line(1) { orange { "return" }; green { " \"Not a Number\"" }; normal { ";" } }
    line { normal { "}" } }
    line()
}
