# How to run this project in local

## Prerequisites

* Docker
* Docker-compose

## How to run it

There is a docker-compose and dockerfile that manages:

* The creation of an `.jar` executable image which contains the Spring app.
* The run of a MySQL database in parallel with the Spring app.

In order to make it work in local:

1. Create an image with the dockerfile.
```
docker-compose build
```
2. Run MySQL service and Spring Service in parallel. We have a docker-compose file to make that for us.
```
docker-compose run
```