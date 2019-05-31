# Elevator Problem

### How to Run
1. pull it
2. `./gradlew bootrun`

### API
`url : localhost:8080`
##### Start
- Url  
POST `localhost:8080/start`
- Request
```json
{
  "buildingHeight": 5,  // or 10 or 20
  "elevatorCnt": 1      // or 2 or 3 or 4
}
```
- Response
```json
{
  "status": 200         // ok, -400 is not ok
}
```

##### call
- Url  
GET `localhost:8080/call`
- Request
```json
None
```
- Response
```json
{
  "status": 200,
  "end": false,                 // True is all done
  "timestamp": 14,              // increased by every querys
  "elevators": [
    {
      "id": 0,                  // elevator id
      "floor": 0,               // elevator current floor
      "passengers": [0, 3],     // passengers' id in the elevator
      "status": "UPWARD"        // elevator status (UPWARD, DOWNWARD, STOP, OPEN, CLOSE)
    },
    {
      "id": 1,
      "floor": 3,
      "passengers": [],
      "status": "STOP"
    }
  ],
  "calls": [                    // passengers call
    {
      "id": 0,                  // passenger id
      "timestamp": 2,           // passenger's call time
      "start": 3,               // passenger's initial floor
      "end": 0                  // passneger's target floor
    },
    {
      "id": 5,
      "timestamp": 13,
      "start": 2,
      "end": 3
    }
  ]
}
```

##### action
- Url  
POST `localhost:8080/action`
- Request
```json
{
  "commands": [
    {
      "elevatorId": 0,
      "command": "OPEN"         // elevator command (UP, DOWN, STOP, OPEN, CLOSE, ENTER, EXIT)
    },
    {
      "elevatorId": 1,
      "command": "ENTER",
      "callIds": [0, 3]         // passenger id, optional only ENTER, EXIT command
    }
  ]
}
```
- Response
```json
{
  "status": 200,
  "end": false,
  "timestamp": 14,
  "elevators": [
    {
      "id": 0,
      "floor": 0,
      "passengers": [0, 3],
      "status": "UPWARD"
    },
    {
      "id": 1,
      "floor": 3,
      "passengers": [],
      "status": "STOP"
    }
  ]
}
```
