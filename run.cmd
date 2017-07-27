@echo off

title Instancia do SiGLa

if exist %cd%\target (
  echo Removendo compilacoes antigas
  del /f %cd%\target
)

echo Recompilando projeto
call mvn clean package

echo Executando projeto
java -cp target/dependency/jetty-runner.jar org.eclipse.jetty.runner.Runner --port 8080 target/*.war

:eof
