#!/usr/bin/env bash
set -euo pipefail
here="$(cd "$(dirname "$0")"/.. && pwd)"
cd "$here"
docker compose down -v || true
