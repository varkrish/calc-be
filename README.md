## Step 1

Let's try to build and deploy the simple microservice we have created onto the openshift cluster **without requiring docker on developer's machine**

### 1. Build the app:

```
mvn  -DskipTests clean package
cp target/*.jar dist/app.jar
```
``dist`` directory contains the build jar executable


#### 2. Create Openshift Project

In order to deploy our app, we need to create a project/namespace on the ocp cluster. Let's call the project namespace as ```dev-apps`` 

```
oc login -server=https://api.<cluster-domain>:6443
oc new-project dev-apps
```

#### Sample output

```
Already on project "dev-apps" on server "".

You can add applications to this project with the 'new-app' command. For example, try:

    oc new-app rails-postgresql-example

to build a new example application in Ruby. Or use kubectl to deploy a simple Kubernetes application:

    kubectl create deployment hello-node --image=k8s.gcr.io/serve_hostname
```
 <i> Note:

User needs to have create project permissions on the cluster
</i>

#### 3. Create Openshift Build specification

Since we don't have a docker image in a container registry, we have to build the image using OCP's build system. This can be achieved either by using the ``BuildConfig`` CRD or the ``oc new-build`` command.

```
% cd calc-be
calc-be % cat Dockerfile | oc new-build -D - --name calc-beapp
```
Above step create as a new build config and dependent resources like imagestreams(to store the base & built images) on the name space. Output will look like the one below:

```
--> Found container image c644efd (25 hours old) from docker.io for "docker.io/openjdk:11"

    * An image stream tag will be created as "openjdk:11" that will track the source image
    * A Docker build using a predefined Dockerfile will be created
      * The resulting image will be pushed to image stream tag "calc-beapp:latest"
      * Every time "openjdk:11" changes a new build will be triggered

--> Creating resources with label build=calc-beapp ...
    buildconfig.build.openshift.io "calc-beapp" created
--> Success

```
#### 2.1 Resource limits 

Lets set the resource limits for the builds. 


```
oc patch bc calc-beapp -p '{"spec":{"resources": {"limits": {"cpu": "1500m", "memory": "900Mi"}, "requests": {"cpu": "800m", "memory": "500Mi"}}}}'
```
###### Sample output
```
buildconfig.build.openshift.io/calc-beapp patched
```

#### 3. Build the container image based on the local jar file

```
calc-beapp % oc start-build calc-beapp --from-dir ./dist --follow  
```

This step will **upload the local jar** to OCP cluster and build the container image based on the steps defined the ``Dockerfile`` we have provided in Step #3. 

#### Sample output
```
Uploading directory "dist" as binary input for the build ...
.
Uploading finished
build.build.openshift.io/calc-beapp-2 started
Receiving source from STDIN as archive ...
Replaced Dockerfile FROM image docker.io/openjdk:11
time="2022-05-12T06:56:08Z" level=info msg="Not using native diff for overlay, this may cause degraded performance for building images: kernel has CONFIG_OVERLAY_FS_REDIRECT_DIR enabled"
I0512 06:56:08.477431       1 defaults.go:102] Defaulting to storage driver "overlay" with options [mountopt=metacopy=on].
Caching blobs under "/var/cache/blobs".

Pulling image docker.io/openjdk@sha256:5aa4c81752fede6f6c8251a0f39f5ededa0e97b4418c9ff6e9720012509ae903 ...
Trying to pull docker.io/library/openjdk@sha256:5aa4c81752fede6f6c8251a0f39f5ededa0e97b4418c9ff6e9720012509ae903...
Getting image source signatures
Copying blob sha256:5a8b0e20be4b4a332bc3d90b9903a5f3c0664b440fd9f1d2a1db0d4b7e6e826b
Copying blob sha256:67e8aa6c8bbc76b1f2bccb3864b0887671833b8667dc1f6c965fcb0eac7e6402
Copying blob sha256:400f1e54bef04acd9219b02dbde4288c6cfde262f0c4daead2366a73e576a9dd
Copying blob sha256:0670968926f6461e3135c82ba2c0ad3ebdedc0d0f41b18bda4a1e41104b8be8a
Copying blob sha256:627e6c1e105548ea4a08354eea581f137cf368d91aeb0ad47dcb706fca54fd8b
Copying blob sha256:7a93fb4386075c94fe0fffb1d14b23550bb38cb89906b5819672fb1fb63766c0
Copying blob sha256:f0b65b53f1a48392c3de8641fa5a79836f4c38646d7a5929a73f5e7033d93922
Copying config sha256:c644efd2427366f29ed36c7320ad6a2b62d82b0f29691ed673b7d811f493cf99
Writing manifest to image destination
Storing signatures
Adding transient rw bind mount for /run/secrets/rhsm
STEP 1/6: FROM docker.io/openjdk@sha256:5aa4c81752fede6f6c8251a0f39f5ededa0e97b4418c9ff6e9720012509ae903
STEP 2/6: COPY app.jar /
--> d3a9962fdd7
STEP 3/6: EXPOSE 8100
--> c27d35aa13e
STEP 4/6: ENTRYPOINT ["java","-jar","/app.jar"]
--> 5e73cdc673b
STEP 5/6: ENV "OPENSHIFT_BUILD_NAME"="calc-beapp-2" "OPENSHIFT_BUILD_NAMESPACE"="dev-calc-apps"
--> d7d0b37abc2
STEP 6/6: LABEL "io.openshift.build.name"="calc-beapp-2" "io.openshift.build.namespace"="dev-calc-apps"
COMMIT temp.builder.openshift.io/dev-calc-apps/calc-beapp-2:57386125
--> 5ee3db94ff9
Successfully tagged temp.builder.openshift.io/dev-calc-apps/calc-beapp-2:57386125
5ee3db94ff9f8eaa7ff6284d265e1bdce60158447ef4369b24f5122a505ac94a

Pushing image image-registry.openshift-image-registry.svc:5000/dev-calc-apps/calc-beapp:latest ...
Getting image source signatures
Copying blob sha256:0670968926f6461e3135c82ba2c0ad3ebdedc0d0f41b18bda4a1e41104b8be8a
Copying blob sha256:627e6c1e105548ea4a08354eea581f137cf368d91aeb0ad47dcb706fca54fd8b
Copying blob sha256:5a8b0e20be4b4a332bc3d90b9903a5f3c0664b440fd9f1d2a1db0d4b7e6e826b
Copying blob sha256:7a93fb4386075c94fe0fffb1d14b23550bb38cb89906b5819672fb1fb63766c0
Copying blob sha256:400f1e54bef04acd9219b02dbde4288c6cfde262f0c4daead2366a73e576a9dd
Copying blob sha256:d70e9abf0746dedb46408ac7e943a700f178dfc7ea60f34ca221ae2747eb9556
Copying blob sha256:67e8aa6c8bbc76b1f2bccb3864b0887671833b8667dc1f6c965fcb0eac7e6402
Copying blob sha256:f0b65b53f1a48392c3de8641fa5a79836f4c38646d7a5929a73f5e7033d93922
Copying config sha256:5ee3db94ff9f8eaa7ff6284d265e1bdce60158447ef4369b24f5122a505ac94a
Writing manifest to image destination
Storing signatures
Successfully pushed image-registry.openshift-image-registry.svc:5000/dev-calc-apps/calc-beapp@sha256:4f205fcc590963fdd91334d421e89132ab0cba19e70884199bc34e8fb1dbe5eb
Push successful

```

Hurray, we have created the application container image without the need for having docker/podman locally. Its time to deploy the image as an application

#### 4. Deploy the image as app

To create an application based on existing image, we will the simplest method i.e. using ``oc new-app`` command.
Now let's try to deploy the application by passing the required configuration by:

Passing -e environment option during app creation

```
oc new-app  --name calc-beapp-add calc-beapp -e spring.profiles.active=add -l deployment=calc-beapp-add

```
**Where:**

  <i> -l options attaches a label (/ a tag) to the application</i>

##### Sample output
```
--> Found image 5ee3db9 (40 minutes old) in image stream "dev-calc-apps/calc-beapp" under tag "latest" for "calc-beapp"


--> Creating resources with label deployment=calc-beapp-add ...
    deployment.apps "calc-beapp-add" created
    service "calc-beapp-add" created
--> Success
    Application is not exposed. You can expose services to the outside world by executing one or more of the commands below:
     'oc expose service/calc-beapp-add' 
    Run 'oc status' to view your app.

```

##### Let's verify the deployment:

```
% oc get pods -l deployment=calc-beapp-add

NAME                              READY   STATUS    RESTARTS   AGE
calc-beapp-add-8548fc9884-7glmm   1/1     Running   0          3m56s
```

We can specify additional labels to the deployment even after we create them. For example:

```
% oc label deployment calc-beapp deployment=calc-beapp
deployment.apps/calc-beapp labeled
```

**We need to define labels in order to select/search our deployments to perform certain operation like attaching a service etc.,.**

#### 5. Testing the configuration
Now lets test the new application using the below endpoint. This endpoint must return the value we had set in the api params which is operand_1 & 
operand_2

Test:

% oc get pods -l deployment=calc-beapp-add
NAME                              READY   STATUS    RESTARTS   AGE
calc-beapp-add-8548fc9884-7glmm   1/1     Running   0          11m

% oc port-forward pod/calc-beapp-add-8548fc9884-7glmm 9000:8100

Forwarding from 127.0.0.1:9000 -> 8100
Forwarding from [::1]:9000 -> 8100

#### In a new window

% curl "localhost:9000/add?operand_1=5&operand_2=5"
{"result":"10"}

#### 6. Create additional applications/deployments for sub/multiply/divide api using the step 4

Using the container image we had created earlier, it is possible to deploy any number of applications under different name and activate the profile via environment variable

For example:

``` 
oc new-app  --name calc-beapp-sub calc-beapp -e spring.profiles.active=sub -l deployment=calc-beapp-sub

oc new-app  --name calc-beapp-mul calc-beapp -e spring.profiles.active=mul -l deployment=calc-beapp-mul

oc new-app  --name calc-beapp-div calc-beapp -e spring.profiles.active=div -l deployment=calc-beapp-div
```

Above command will create another ``deployment`` on openshift with the respective names with the image we have specified.