{
  "env": {
    "global": [
      "MONGODB_FILE_NAME=mongodb-linux-x86_64-enterprise-ubuntu1404",
      "MONGODB=4.0.0"
    ]
  },
  "jdk": [
    "openjdk8"
  ],
  "dist": "trusty",
  "sudo": false,
  "scala": [
    "2.11.12"
  ],
  "addons": {
    "apt": {
      "packages": [
        "libsnmp-dev"
      ]
    }
  },
  "script": [
    "./sbt ++$TRAVIS_SCALA_VERSION check"
  ],
  "install": [
    "wget http://downloads.mongodb.com/linux/${MONGODB_FILE_NAME}-${MONGODB}.tgz",
    "tar xzf ${MONGODB_FILE_NAME}-${MONGODB}.tgz",
    "${PWD}/${MONGODB_FILE_NAME}-${MONGODB}/bin/mongod --version"
  ],
  "branches": {
    "only": [
      "master"
    ]
  },
  "language": "scala",
  "after_script": [
    "pkill mongod"
  ],
  "before_script": [
    "mkdir ${PWD}/${MONGODB_FILE_NAME}-${MONGODB}/data",
    "${PWD}/${MONGODB_FILE_NAME}-${MONGODB}/bin/mongod --dbpath ${PWD}/${MONGODB_FILE_NAME}-${MONGODB}/data --logpath ${PWD}/${MONGODB_FILE_NAME}-${MONGODB}/mongodb.log --setParameter enableTestCommands=1 --fork --smallfiles --nojournal"
  ],
  "notifications": {
    "email": {
      "on_failure": "always",
      "on_success": "change",
      "recipients": [
        "ross@mongodb.com"
      ]
    }
  }
}