pipeline {
    agent any

    tools {
        jdk 'JDK 17'
        maven 'Maven 3.6.3'
    }

    environment {
        IMAGE_NAME = 'ticketapp:latest'
        DEPLOYMENT_YAML = 'k8s/deployment.yaml'
        SERVICE_YAML = 'k8s/service.yaml'
        KUBECONFIG = '/var/lib/jenkins/.kube/config'
        MINIKUBE_HOME = '/var/lib/jenkins'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }


        stage('Build Docker Image') {
            steps {
                script {
                    echo "🛠 Building Docker image: ${IMAGE_NAME}"
                    sh "eval \$(minikube docker-env) && docker build -t ${IMAGE_NAME} ."
                }
            }
        }


        stage('Deploy to Minikube') {
            steps {
                script {
                    echo "🚀 Deploying to Minikube..."

                    sh 'echo "Using kubeconfig:" && echo $KUBECONFIG && cat $KUBECONFIG'

                    sh "kubectl apply -f ${DEPLOYMENT_YAML}"
                    sh "kubectl apply -f ${SERVICE_YAML}"

                    timeout(time: 5, unit: 'MINUTES') {
                        sh 'kubectl rollout status deployment/ticketapp-deployment --watch=true'

                    }

                    echo "Application deployed"
                }
            }
        }
    }

    

    post {
        success {
            echo 'CI/CD pipeline completed successfully!'
        }
        failure {
            echo 'CI/CD pipeline failed'
        }
    }
}
