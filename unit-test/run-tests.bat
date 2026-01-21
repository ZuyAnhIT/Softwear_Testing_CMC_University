@echo off
REM Thiết lập JAVA_HOME
set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-25.0.0.36-hotspot

REM In thông báo
echo.
echo ========================================
echo    Student Analyzer - Test Runner
echo ========================================
echo.
echo JAVA_HOME: %JAVA_HOME%
echo Project: %cd%
echo.

REM Chạy Maven
mvn clean test

REM Pause để xem kết quả
pause
