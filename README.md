#Cloud Foundry Hello World Demo
Simple Hello World demo for Cloud Foundry, with a client and server component communicating through REST.

The server component contains a simple Hello World REST service that is deployed both in an English and Italian version. The client can then be configured to communicate with either of the server components.

##Deployment Instructions
###Download Source Code from Repository
```bash
git clone https://github.com/bboe-pivotal/cf-hello-world-demo/
```
###Compile and Deploy Server Components
* Go to hello-world-server directory
```bash
cd cf-hello-world-demo/hello-world-server/
```
* Build application using Gradle
```bash
./gradlew clean assemble
```
* Push to Cloud Foundry using settings in local manifest.yml
```bash
cf push
```
Use the following command to see more information on the applications that were just deployed:
```bash
cf apps

Getting apps in org xxx / space yyy as zzz...
OK

name                requested state   instances   memory   disk   urls
hello-svc-english   started           1/1         512M     1G     hello-svc-eng-nonderisible-hydrolysation.cfapps.io
hello-svc-italian   started           1/1         512M     1G     hello-svc-ita-nondistorted-dominance.cfapps.io
```

The push command just uploaded two applications to Cloud Foundry: One Hello World service in English and one Hello World service in Italian. It's the same server components with a slightly different configuration.
The server end-points can be tested by accessing the server end-points. This will yield a simple test case that triggers a RESTful call to the Hello World service.

The last that needs to be done is to create two user provided services for the client that contains information about the server end-points just deployed:
```bash
cf cups hello-english -p '{"uri":"http://hello-svc-eng..."}'
cf cups hello-italian -p '{"uri":"http://hello-svc-ita..."}'
```
Enter the URL for each service as shown by the ```cf apps``` command. Run ```cf services``` to verify the services have been created as follows:
```bash
Getting services in org xxx / space yyy as zzz...
OK

name            service         plan   bound apps
hello-english   user-provided
hello-italian   user-provided
```
It's important that at least the hello-english service have been created as the client components requires this service in order to deploy.

###Compile and Deploy Client Component
* Go to hello-world-client directory
```bash
cd ../hello-world-client/
```
* Build application using Gradle
```bash
./gradlew clean assemble
```
* Push to Cloud Foundry using settings in local manifest.yml
```bash
cf push
```
Use the ```cf apps``` command to verfiy that all components have been successfully deployed:
```bash
Getting apps in org xxx / space yyy as zzz...
OK

name                requested state   instances   memory   disk   urls
hello-client        started           1/1         512M     1G     hello-client-interreligious-gibbousness.cfapps.io
hello-svc-english   started           1/1         512M     1G     hello-svc-eng-nonderisible-hydrolysation.cfapps.io
hello-svc-italian   started           1/1         512M     1G     hello-svc-ita-nondistorted-dominance.cfapps.io
```
The Hello Client is by default configured to use the English Hello World service. Use the following commands to reconfigure the Hello Client to use the Italian Hello World service:
```bash
cf unbind-service hello-client hello-english
cf bind-service hello-client hello-italian
cf restage hello-client
```
