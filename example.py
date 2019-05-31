import requests

baseUrl = "http://localhost:8080"
header = {'Content-Type': 'application/json'}

def start(url, height, cnt):
    url += '/start'

    if height != 5 and height != 10 and height != 20:
        raise Exception
    if cnt <= 0 or cnt > 4:
        raise Exception

    body = {"buildingHeight": height, 'elevatorCnt': cnt}
    return requests.post(url, headers=header, json=body).json()

def call(url):
    url += '/call'

    return requests.get(url).json()

def action(url, action):
    url += '/action'

    return requests.post(url, json=action).json()

def main():
    height = 5
    cnt = 1
    try:
        res = start(baseUrl, height, cnt)
        if res['status'] != 200:
            return

        floor = 0
        passenger = [] 
        up = True

        while True:
            opened = False
            callres = call(baseUrl)
            print('call', callres)

            if callres['end'] is True:
                print('is end')
                break;

            getOut = []
            for i, s, e in passenger:
                if e == floor:
                    getOut.append((i, s, e))
            for i in getOut:
                passenger.remove(i)

            if len(getOut) != 0:
                res = action(baseUrl, {'commands': [{'elevatorId': 0, 'command': 'OPEN'}]})
                print('action open', res)

                res = action(baseUrl, {'commands': [{'elevatorId': 0, 'command': 'EXIT', 'callIds': [x[0] for x in getOut]}]})
                print('action exit', res)

                opened = True

            getIn = []
            for i in callres['calls']:
                if i['start'] == floor:
                    getIn.append((i['id'], i['start'], i['end']))

            if len(getIn) != 0:
                if opened is False:
                    res = action(baseUrl, {'commands': [{'elevatorId': 0, 'command': 'OPEN'}]})
                    print('action open', res)
                
                if len(passenger) + len(getIn) > 8:
                    getIn = getIn[:8 - len(passenger)]
                res = action(baseUrl, {'commands': [{'elevatorId': 0, 'command': 'ENTER', 'callIds': [x[0] for x in getIn]}]})
                print('action enter', res)

                passenger += getIn
                opened = True

            if opened is True:
                res = action(baseUrl, {'commands': [{'elevatorId': 0, 'command': 'CLOSE'}]})
                print('action close', res)

            if up is True:
                res = action(baseUrl, {'commands': [{'elevatorId': 0, 'command': 'UP'}]})
                print('action up', res)
                floor += 1

                if floor == height - 1:
                    up = False

            else:
                res = action(baseUrl, {'commands': [{'elevatorId': 0, 'command': 'DOWN'}]})
                print('action down', res)
                floor -= 1

                if floor == 0:
                    up = True

            res = action(baseUrl, {'commands': [{'elevatorId': 0, 'command': 'STOP'}]})
            print('action stop', res)

    except Exception as e:
        print(e)

    print('Success')

if __name__ == "__main__":
    main()
