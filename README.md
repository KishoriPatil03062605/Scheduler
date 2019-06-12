# Scheduler

Scheduler is web service which provides API to decides which node is most suitable for a container to be placed on. Node and Container are two main Entities. A suitable node for a container is decided on 2 criteria, node which meets all the hints (configurations) of a container and the least busy node of the eligible nodes.
The is light weight and no external services (e.g. external databases) are required to build this project. 

## Features
* Accepts a GET request to list all nodes at http://{{host}}/nodes
> Endpoint : /nodes

> Response Sample :

>    [{
        "id": "96f1a41a-2215-3c41-a74a-98e458fc69b2",
        "name": "node-004-westus",
        "scheduler-hints": {
            "flavour": "gold",
            "os": "Linux",
            "CPU": "AMD",
            "hdd": "ssd",
            "region": "westus"
        },
        "capacity": {
            "total": 10,
            "used": 0
        }
    },
    {
    "id": "4c9aed59-4805-344c-80c5-d81b8de12f0a",
    "name": "node-018-ukwest",
    "scheduler-hints": {
        "flavour": "platinum",
        "region": "ukwest",
        "os": "Linux",
        "CPU": "AMD",
        "hdd": "nvme",
        "gpu": "AMD"
    },
    "capacity": {
        "total": 10,
        "used": 10
    }
}
 ]
* Accepts a GET request to fetch a single node at http://{{host}}/nodes/{{nodeID}}
> Endpoint : /node/4c9aed59-4805-344c-80c5-d81b8de12f0a

> Response Sample :

> {
    "id": "4c9aed59-4805-344c-80c5-d81b8de12f0a",
    "name": "node-018-ukwest",
    "scheduler-hints": {
        "flavour": "platinum",
        "region": "ukwest",
        "os": "Linux",
        "CPU": "AMD",
        "hdd": "nvme",
        "gpu": "AMD"
    },
    "capacity": {
        "total": 10,
        "used": 10
    }
}

* Accepts a POST request with a JSON encoded body that represents a container at http://{{host}}/container/schedule
> Endpoint : /container/schedule

> Request Sample: 

> {
  "id": "d290f1ee-6c54-4b01-90e6-d701748f8852",
  "name": "my-super-container",
  "status": "pending",
  "node": null,
  "image": "img",
  "scheduler-hints": {
            "flavour": "platinum",
            "region": "ukwest",
            "os": "Linux",
            "CPU": "AMD",
            "hdd": "nvme",
            "gpu": "AMD"
        }
}

> Response Sample:

> {
    "id": "d290f1ee-6c54-4b01-90e6-d701748f8852",
    "name": "my-super-container",
    "image": "img",
    "status": "SCHEDULED",
    "scheduler-hints": {
        "flavour": "platinum",
        "region": "ukwest",
        "os": "Linux",
        "CPU": "AMD",
        "hdd": "nvme",
        "gpu": "AMD"
    },
    "node": {
        "id": "4c9aed59-4805-344c-80c5-d81b8de12f0a",
        "name": "node-018-ukwest",
        "scheduler-hints": {
            "flavour": "platinum",
            "region": "ukwest",
            "os": "Linux",
            "CPU": "AMD",
            "hdd": "nvme",
            "gpu": "AMD"
        },
        "capacity": {
            "total": 10,
            "used": 10
        }
    }
}

  Validates the supplied JSON against the provided schema which is picked from the resources folder  **classpath:container_schema.json** and returns an error if it is not valid.
  Compares the list of scheduler hints in the container data structure to the list of nodes, and matches the container to a single       suitable node.
  If no nodes match the request, returns a meaningful error message.
  If multiple nodes match all hints, choose the node which has the fewest containers assigned to it.
  Once a node has been selected, it’s “used” capacity is incremented by 1.
  If a node’s used capacity is equal to the total capacity, it should not have anymore containers scheduled to it.
  Return the container JSON data with the “node” variable filled with the node that matched the best, and the status changed to “scheduled”.
  
* Accepts a DELETE request at http://<host>/container/<ContainerID>
> Endpoint : /container/d290f1ee-6c54-4b01-90e6-d701748f0852
  
> Response Sample : True
  Validates the supplied ContainerID is known
  Frees up the capacity consumed on the node to which this container was scheduled to earlier.

## Limitations
* No external DataBase is used, data is stored in memory.
* No APi to create a Node is provided. It is assumed that Nodes are already present in the data-store or loaded externally.

## Getting Started

To successfully get this project up and running one has to clone the repository. The project is a Spring Boot 2.0 application. Please follow all the instructions mentioned below:

### Prerequisites
* [Java 8](https://www.oracle.com/technetwork/java/javaee/downloads/jdk8-downloads-2133151.html) - Programming language
* [Spring](https://spring.io/) - Framework
* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Boot](https://spring.io/projects/spring-boot) - used for auto-configuring Spring Application


### Application deployment

#### Server side
* To Build the project with Maven: 
  > mvn clean install
* To run the project
  > mvn spring-boot:run
  
#### Resource file
 > classpath: container_schema.json -> used to validate the Container json which is used in the schedule rest api.
 > classpath: nodes.json -> used to load the initial nodes in the data store.

## Running the tests

The application uses Spring starter test APIs backed by JMockit for Junit tests. Jacoco is used to generating the test coverage report.

## Authors

* **Kishori Patil** - [GitHub](https://github.com/KishoriPatil03062605)

