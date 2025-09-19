#!/usr/bin/env bash
set -euo pipefail
here="$(cd "$(dirname "$0")"/.. && pwd)"
cd "$here"

mvn -q clean install
docker compose build
docker compose up -d

echo "Waiting for GlassFish to start..."
sleep 8

(
  cd calculator-client

  mvn -q exec:exec
)
