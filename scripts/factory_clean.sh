#!/bin/bash

echo "🧹 Factory v2 Clean Starting..."

./gradlew clean

rm -rf .gradle
rm -rf build
rm -rf app/build

echo "✅ Cache cleared safely"
echo "🚀 Ready for build"
