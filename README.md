## Titulo: api-info-segrd-token-sso
***

Microservicio para obtener token de seguridad desde SSO Redhat para utilizar en las peticiones a realizar a través de 3scale
# 🚀 Comenzando 
***

[Instrucciones que permitirán obtener una copia del proyecto en funcionamiento en máquina local para propósitos de desarrollo y pruebas]
# 🛅 Tecnología 
***

GitLab - Repositorio Git

SonarQube – Calidad y seguridad de código

OpenShift - Plataforma para desarrollar contenedores

Nexus – Administrador de repositorios, almacena artefactos

Apicuro - Diseño, mocking y testing de APIs

3Scale - Gestión de API

# 📋 Pre-requisitos  
***

Acceso al repositorio Git
Debe de tener instalado

git-scm

openjdk 1.8 LTS

Spring Tool Suite

Apache Maven 3.x

Configuracion de variable de entorno %JAVA_HOME%

Configuracion de variable de entorno %M2_HOME%
# 🔧 Instalación 
***

1. Crear un carpeta local donde sera el area de trabajo del proyecto.

2. Ubicarse en la carpeta local

4. Importar carpeta del proyecto en Spring Tool Suite

5. Seleccionar el aplicativo y ejecutar la siguiente combinación de teclas en STS: Alt + Shift + X, B

6. Ingresar a la siguiente url: http://localhost:8080
# 📖 WIKI 
***
[Lineamientos de Base Datos]-
Las configuraciones de conexion a base de datos deben quedar definidas en variables de ambientes dentro del pod que se crea al momento de desplegar la aplicacion dentro de la plataforma de openshift.
