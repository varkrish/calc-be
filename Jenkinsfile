node('maven-helm') {
    def tag;

    stage ('pull code') {
        sh "git clone https://github.com/varkrish/labs-enablement-spring-ocp source "
    }
    stage ('mvn build') {
        dir("source") {
            tag = sh(returnStdout: true, script: "git rev-parse --short=8 HEAD").trim();

            sh "mkdir build-folder"
            sh "mkdir properties-folder"
            sh "mvn -B clean package -Dmaven.repo.local=/tmp/source/m2 -f basic/simple-microservice/pom.xml"

            sh "cp basic/simple-microservice/Dockerfile build-folder/Dockerfile"
            sh "cp basic/simple-microservice/target/*.jar build-folder/app.jar"
        }
    }
    stage ('build and push') {
        dir("source") {
            sh "cat build-folder/Dockerfile | oc new-build -D - --name springbootapp || true"
            sh "oc start-build springbootapp --from-dir=build-folder/. --follow --wait "
            sh "oc tag cicd/springbootapp:latest dev-apps/springbootapp:${tag} "
            sh "oc project dev-apps"
        }
    }
        stage ('deploy application') {
        dir("source") {
            sh "oc new-app -n dev-apps --image=image-registry.openshift-image-registry.svc:5000/dev-apps/springbootapp:${tag} --name=springbootapp || true"
            }
        }
        stage ('deploy service') {
        dir("source") {
            sh "oc create service clusterip springbootapp -o yaml --dry-run=client --tcp=8100:8100 | oc set selector --local -f - 'deployment=springbootapp' -o yaml | oc create -f - || true"
           // sh "oc create route edge --service springbootapp --port=8100  || true"
        }
    }
}