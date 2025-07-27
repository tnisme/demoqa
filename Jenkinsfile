pipeline {
    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr: '10', artifactNumToKeepStr: '10'))
    }

    tools {
            maven 'maven'
    }

    stages {
        stage('Clone') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean test'
            }
        }
    }

    post {
        always {
            script {
                publishHTML([
                    allowMissing          : false,
                    alwaysLinkToLastBuild : true,
                    keepAll               : true,
                    useWrapperFileDirectly: true,
                    reportDir             : 'test-output/report',
                    reportFiles           : 'chrome.html, firefox.html',
                    reportName            : 'HTML Report',
                    reportTitles          : 'demo_qa'
                ])

                testNG(
                    failedFails      : 5,
                    failedSkips      : 10,
                    showFailedBuilds : true,
                    unstableFails    : 5,
                    unstableSkips    : 10
                )
            }
        }
    }
}
