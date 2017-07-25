@echo off

title Instancia do SiGLa - Sem SSL

IF [%1]==[] (
	set /p port=Porta: 
) ELSE (
	set port=%1
)

title Instancia do SiGLa (porta %port%) - Sem SSL

mvn package && cls && java -Dcom.sun.jndi.ldap.connect.pool.timeout=1800000 -cp target/dependency/jetty-runner.jar org.eclipse.jetty.runner.Runner --port %port% target/*.war