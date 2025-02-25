const fs = require('fs')

// gen('core-in.txt', 'core-out.txt')
gen('ext-in.txt', 'ext-out.txt')

function gen(input, output) {
  fs.writeFileSync(
    output,
    fs.readFileSync(input)
      .toString()
      .split('\n')
      .map(it => it.replace(/Kt$/, ''))
      .map(it => `IconData("${it}", Icon.${it}),`)
      .join('\n')
  )
}
