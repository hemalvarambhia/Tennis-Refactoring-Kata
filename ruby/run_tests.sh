#!/usr/bin/env bash
set -uo pipefail

cd "$(dirname "$0")" || exit 1

ruby tennis_test.rb
