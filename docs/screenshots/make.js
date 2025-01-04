const fs = require('fs')

let dimensions = {
  'Language': [],
  'UiMode': [],
  'Orientation': [],
  'Screen': [],
}
let groups = {}
const groupRegex = /.*\.(?<group>.*)/

fs.readdirSync('compressed')
  .filter(it => it !== '.DS_Store')
  .forEach(it => extract(it))

fs.writeFileSync('data.js', `const dimensions = ${JSON.stringify(dimensions, null, 2)}
const groups = ${JSON.stringify(groups, null, 2)}`)

function extract(file) {
  const name = file
  const attributes = file.replace('.webp', '').split('-')
  const group = attributes.splice(0, 1)[0].split('_')
    .map(g => splitWords(g.match(groupRegex)?.groups?.group || g))
  attributes.forEach((it, index) => {
    const key = Object.keys(dimensions)[index]
    if (!dimensions[key].includes(it))
      dimensions[key].push(it)
  })
  addItem(groups, group, { name, attributes })
}
function splitWords(str) {
  return str.replace(/([A-Z])/g, " $1").trim()
}
function addItem(groups, group, item, index = 0) {
  if (index < group.length) {
    const g = group[index]
    if (g in groups)
      addItem(groups[g], group, item, index + 1)
    else {
      let obj = index === group.length - 1 ? [] : {}
      groups[g] = obj
      addItem(obj, group, item, index + 1)
    }
  } else
    groups.push(item)
}
