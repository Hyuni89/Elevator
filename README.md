# Elevator Problem

### How to Run
1. clone it
2. `./gradlew bootrun`

### API
`url : http://localhost:8080`

### Start
- Url  
POST `http://localhost:8080/start`
- Request
```json
{
  "buildingHeight": 5,
  "elevatorCnt": 1
}
```
> `buildingHeight` : only available `5` or `10` or `20`  
> `elevatorCnt` : only available `1` or `2` or `3` or `4`  

- Response
```json
{
  "status": 200
}
```
> `status` : `200` is OK, `-400` is Not OK (Invalid request)  

### call
- Url  
GET `http://localhost:8080/call`
- Request
```
None
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
  ],
  "calls": [
    {
      "id": 0,
      "timestamp": 2,
      "start": 3,
      "end": 0
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
> `end` : `true` when all the calls are handled correctly  
> `timestamp` : increased by your query  
> `elevators`-`id` : elevator id  
> `elevators`-`floor` : elevator current floor  
> `elevators`-`passengers` : passengers' id in the elevator  
> `elevators`-`status` : elevator status (`UPWARD`, `DOWNWARD`, `STOP`, `OPEN`, `CLOSE`)
> `calls` : passengers call  
> `calls`-`id` : passenger's id  
> `calls`-`timestamp` : passenger's call time  
> `calls`-`start` : passenger's initial floor  
> `calls`-`end` : passenger's destination floor  

### action
- Url  
POST `http://localhost:8080/action`
- Request
```json
{
  "commands": [
    {
      "elevatorId": 0,
      "command": "UP"
    },
    {
      "elevatorId": 1,
      "command": "ENTER",
      "callIds": [0, 3]
    }
  ]
}
```
> `command` : elevator command (`UP`, `DOWN`, `STOP`, `OPEN`, `CLOSE`, `ENTER`, `EXIT`)  
> `callIds` : entered or exited passenger id (only for `ENTER`, `EXIT`)
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
      "passengers": [],
      "status": "UPWARD"
    },
    {
      "id": 1,
      "floor": 3,
      "passengers": [0, 3],
      "status": "OPEN"
    }
  ]
}
```

### Visualize
1. Open your browser
2. Enter the page `http://localhost:8080/stat`
