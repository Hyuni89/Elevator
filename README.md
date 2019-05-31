# Elevator Problem

### How to Run
1. pull it
2. `./gradlew bootrun`

### API
`url : localhost:8080`
##### Start
- Url  
POST `localhost:8080/start`
- Data
```json
{
  buildingHeight: (5 | 10 | 20),
  elevatorCnt: (1 | 2 | 3 | 4)
}
```

##### call
- Url  
GET `localhost:8080/call`

##### action
- Url  
POST `localhost:8080/action`
- Data
```json
{
  commands: [
    {
      elevatorId: 0,
      command: ("OPEN" | "CLOSE" | "STOP" | "UP" | "DOWN" | "ENTER" | "EXIT"),
      (optional, only ENTER, EXIT) callIds: [
        0, 1, 2
      ]
    }
  ]
}
```
