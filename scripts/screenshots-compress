set -e

compressedDir=docs/screenshots/compressed
rm -rf ${compressedDir}
mkdir ${compressedDir}

for file in docs/screenshots/images/*.png; do
  filename=$(basename ${file})
  filename=${filename%.*}
  cwebp ${file} -o ${compressedDir}/${filename}.webp
done;

cd docs/screenshots
node make.js
