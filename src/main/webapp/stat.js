var client = null;
var socket = new SockJS('/update');
client = Stomp.over(socket);
client.connect({}, function (frame) {
    client.subscribe('/subscribe', function (obj) {
        validate(obj)
    });
});

function validate(json) {
    var body = JSON.parse(json.body);

    if(body['buildingHeight'] !== undefined) {
        drawTable(body['buildingHeight'], body['elevatorCnt']);
    } else {
        update(body['elevators'], body['calls']);
    }
}

function drawTable(height, cnt) {
    var table = document.getElementById("building");
    if(table) {
        for(var i = table.rows.length - 1; i >= 0; i--) {
            table.deleteRow(i);
        }
    }

    for(var i = 0; i < height + 1; i++) {
        var row = table.insertRow();
        for(var j = 0; j < cnt + 2; j++) {
            var col = row.insertCell();
            if(j === 0) {
                col.innerHTML = i === height ? 'Status' : height - i;
            }
            if(i === height && j > 0 && j < cnt + 1) {
                col.innerHTML = 'CLOSE';
            } else if(i === height - 1 && j > 0 && j < cnt + 1) {
                col.className = 'elevator'
            }
        }
    }
}

function update(elevators, calls) {
    var table = document.getElementById("building");
    var height = table.rows.length - 1;
    var cnt = table.rows[0].cells.length - 2;

    elevators.forEach(function (it) {
        var id = it['id'] + 1;
        var floor = it['floor'] + 1;
        var pass = [];
        it['passengers'].forEach(function (p) {
            pass.push(p.toString());
        });
        for(var i = 0; i < height; i++) {
            if(i === height - floor) {
                table.rows[i].cells[id].className = 'elevator';
                table.rows[i].cells[id].innerHTML = pass.join(", ");
            } else {
                table.rows[i].cells[id].className = '';
                table.rows[i].cells[id].innerHTML = '';
            }
        }
        table.rows[height].cells[id].innerHTML = it['status'];
    });

    if(calls === undefined) return;

    var passenger = [];
    for(var i = 0; i < height; i++) {
        passenger[i] = [];
    }
    calls.forEach(function (it) {
        passenger[it['start']].push(it['id'].toString());
    });
    for(var i = 0; i < height; i++) {
        table.rows[height - i - 1].cells[cnt + 1].innerHTML = passenger[i].join(", ");
    }
}
