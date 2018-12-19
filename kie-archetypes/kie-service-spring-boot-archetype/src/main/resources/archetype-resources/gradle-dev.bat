@echo off

set gradleInput="%*"

if "%*" == "" (
	echo No gradle arguments skipping gradle build
) else (
	echo Running with user input: %gradleInput%
	echo Running gradle build on available project

	call gradle -v >con

	cd ..

	for %%s in ("-model" "-kjar" "MYSERVICE_NAME_MARKER") do (

			cd *%%s
			echo ===============================================================================
            for %%I in (.) do echo %%~nxI
            echo ===============================================================================

			if exist "%GRADLE_HOME%\bin\gradle.bat" (
			    call gradle init >con
				call %GRADLE_HOME%\bin\gradle.bat %* >con
			) else (
			    call gradle init >con
				call gradle %* >con
			)

			cd ..

	)
)

goto :startapp

:startapp
	echo "Launching the application in development mode - requires connection to controller (workbench)"
    cd MYSERVICE_NAME_MARKER
    cd build\libs
    for /f "delims=" %%x in ('dir /od /b *.jar') do set latestjar=%%x
    cd ..
    cd ..
    call java -Dspring.profiles.active=dev -jar build\libs\%latestjar%


:end
