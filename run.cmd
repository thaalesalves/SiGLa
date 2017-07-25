@echo off

title Instancia do SiGLa

IF [%1]==[] (
	set /p port=Porta: 
) ELSE (
	set port=%1
)

title Instancia do SiGLa (porta %port%)

mvn package && cls && java -Dcom.sun.jndi.ldap.connect.pool.timeout=1800000 -Djavax.net.ssl.trustStore=lib/security/cacerts -Djavax.net.ssl.keyStore=lib/security/cacerts -Djavax.net.ssl.keyStorePassword=changeit -agentlib:TakipiAgent -cp target/dependency/jetty-runner.jar org.eclipse.jetty.runner.Runner --port %port% target/*.war