# demo
#Build module using Maven

cd Team

mvn clean install

#Build Docker image of application

sudo docker-compose build

#Run Docker image application

sudo docker-compose up

# REST API

Method: POST

URL: http://localhost:8080/ef/groups

Payload:
[
    {
        "name": "Pramod",
        "age": 44,
        "location": "Pune",
        "Role": "Marketing"
    },
    {
        "name": "Amit",
        "age": 38,
        "location": "Delhi",
        "Role": "Engineer"
    },
    {
        "name": "Priyesh",
        "age": 32,
        "location": "Banglore",
        "Role": "Marketing"
    }
]
