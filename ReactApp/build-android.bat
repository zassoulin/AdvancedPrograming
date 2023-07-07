@echo off

:: Based on: https://stackoverflow.com/a/36961021 and https://stackoverflow.com/a/56520746

:: React-Native 0.62.1+
@REM mkdir "./android/app/src/main/assets/" 2> NUL
@REM type nul > "./android/app/src/main/assets/index.android.bundle"
@REM mkdir "./android/app/src/main/res" 2> NUL
echo:
@REM echo Bundling React Native JavaScript application...
echo echo Building Android application...
echo:
call npx react-native build-android --mode=release
echo:
@REM echo Done bundling!
echo Done building!
echo:

@REM :: Create builds:
@REM pushd android
@REM :: Generated `apk` will be located at `android/app/build/outputs/apk`
@REM echo:
@REM echo Building Android application...
@REM echo:
@REM call ./gradlew assemble
@REM echo:
@REM echo Done building!
@REM echo:
@REM popd