# Aplicación WEB Cliente de OAUTH2

Proyecto MAVEN que compone una aplicación Web que esta construida usando Java Server Faces, esta aplicación es capaz de integrarse al mecanismo de autenticación de la [Clave Unica del Estado](https://www.claveunica.cl/)

## pasos parar desplegar esta aplicación son:

1. Tener preparado un JBoss AS 7.1.1 ó algún AS que implemente JEE, para Tomcat deben añadir las implementaciones necesarias al proyecto,
1. Tener creada una cuenta en la [Consola de Desarrollo](https://www.claveunica.cl/consola)
1. Indicar en las contantes el Client_ID y Client_Secret.

## Librerias utilizadas

1. org.apache.oltu.oauth2.client, Uso del Protocolo de OAUTH2
1. httpclient, Intercambio de mensages via HTTP con el servidor
1. jackson-databind, Mapeo de JSON a POJO
1. jsf-api, Capa de Presentación
1. javax.servlet-api, Servlet que recibe los datos desde el servidor oauth
