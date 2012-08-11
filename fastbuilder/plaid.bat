@ECHO OFF

SET FBPATH=%~dp0

IF [%1]==[] ( 
	ECHO Usage: plaid.bat CLASSPATH CLASS
) ELSE ( 
	IF [%2]==[] (
		ECHO Usage: plaid.bat CLASSPATH CLASS
	) ELSE (

		java -classpath "%~1;%FBPATH%\..\fastruntime\bin;%FBPATH%\..\generated\bin;%FBPATH%\..\fastruntime\lib\asm-debug-all-3.3.1.jar;%FBPATH%\..\faststdlib\bin" %2 %*
	)
)