#!/bin/sh
set -eu

cd "$(dirname "$0")"

echo "Compiling ScholarshipEligibilitySystem.java..."
mkdir -p out
javac -d out ScholarshipEligibilitySystem.java

echo "Running Student Scholarship Eligibility System..."
java -cp out ScholarshipEligibilitySystem
