@echo off

:: Based on: https://stackoverflow.com/a/36961021 and https://stackoverflow.com/a/56520746

@REM :: React-Native 0.62.1+
@REM mkdir "./android/app/src/main/assets/" 2> NUL
@REM type nul > "./android/app/src/main/assets/index.android.bundle"
@REM mkdir "./android/app/src/main/res" 2> NUL
@REM echo:
@REM echo Bundling React Native JavaScript application...
@REM echo:
@REM call npx react-native bundle --dev false --platform android --entry-file index.js --bundle-output "./android/app/src/main/assets/index.android.bundle" --assets-dest "./android/app/src/main/res/"
@REM echo:
@REM echo Done bundling!
@REM echo:

:: Create builds:
pushd android
:: Generated `apk` will be located at `android/app/build/outputs/apk`
echo:
echo Building Android application...
echo:
call ./gradlew assemble
echo:
echo Done building!
echo:
popd