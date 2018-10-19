set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_161
set PATH=%JAVA_HOME%\bin;%PATH%

call mvn clean 
call mvn install -DskipTests=true
pause