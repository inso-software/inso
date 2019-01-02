@echo off

if %1 == l goto :log
if %1 == n goto :npmstart
if %1 == r goto :reverse
if %1 == g goto :gotoproject
if %1 == i goto :reinstall
if %1 == inshow goto :inshow
echo Only support [l n r i inshow]
goto :eof

:log
echo start log
adb logcat | findstr ReactNativeJS
exit /b

:npmstart
echo npm start
npm start
exit /b

:reverse
echo start adb reverse tcp:8081 tcp:8081
adb reverse tcp:8081 tcp:8081
exit /b

:gotoproject
echo goto projects-ios
cd /d ./projects/com.inshow.watch.ios
exit /b

:reinstall
echo reinstall start
adb uninstall com.xiaomi.smarthome
echo uninstall end
adb -d  install %cd%/smarthome.apk
echo install end
exit /b

:inshow
echo adb reverse tcp:8081 tcp:8081
adb reverse tcp:8081 tcp:8081
echo npm start
npm start


