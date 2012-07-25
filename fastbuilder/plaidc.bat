@ECHO OFF

SET FBPATH=%~dp0
SET USAGE=plaidc.bat SOURCEPATH TEMPPATH OUTPUTPATH

IF [%1]==[] ( 
	ECHO %USAGE%
) ELSE ( 
	IF [%2]==[] (
		ECHO %USAGE%
	) ELSE (
		IF [%3]==[] (
			ECHO %USAGE%
		) ELSE (
			ant compile -buildfile "%FBPATH%\build.xml" -q -Dsourcepath="%~1" -Dtemppath="%~2" -Doutputpath="%~3"
		)
	)
)
