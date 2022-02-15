# demo
#Build module using Maven

cd Team

mvn clean install

#Run application

./mvnw spring-boot:run

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
