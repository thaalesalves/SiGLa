@echo off

title Instancia do SiGLa

IF [%1]==[] (
	set /p port=Porta: 
) ELSE (
	set port=%1
)

title Instancia do SiGLa (porta %port%)

echo [101;93m REFAZENDO BUILD DO MAVEN E INICIANDO JETTY [0m
mvn package && cls && java -Dcom.sun.jndi.ldap.connect.pool.timeout=1800000 -agentlib:TakipiAgent -cp target/dependency/jetty-runner.jar org.eclipse.jetty.runner.Runner --port %port% target/*.war