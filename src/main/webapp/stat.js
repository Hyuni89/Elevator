var client = null;
var socket = new SockJS('/update');
client = Stomp.over(socket);
client.connect({}, function (frame) {
    console.log('by cho:: Connected: ' + frame);
    client.subscribe('/subscribe', function (obj) {
        console.log('by cho:: called subscribe');
        updateView(JSON.stringify(obj));
    });
});

function updateView(message) {
    $("#call").html(message);
    $("#call").innerText = message;
}
