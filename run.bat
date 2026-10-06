@echo off
cd /d "%~dp0"

echo Compiling ScholarshipEligibilitySystem.java...
if not exist out mkdir out
javac -d out ScholarshipEligibilitySystem.java
if errorlevel 1 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo Running Student Scholarship Eligibility System...
java -cp out ScholarshipEligibilitySystem
pause
