#!/usr/bin/env bash
set -uo pipefail

cd "$(dirname "$0")" || exit 1

if ruby tennis_test.rb; then
  git add -A
  git commit -m "tcr" --quiet
  echo "TESTS PASSED, changes committed"
else
  git checkout -- .
  git clean -fd --quiet
  echo "TESTS FAILED, changes reverted"
fi
