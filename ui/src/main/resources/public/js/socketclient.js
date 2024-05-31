// Create WebSocket instance
var socket = new WebSocket('ws://localhost:4999/');

// Event listener for WebSocket connection open
socket.addEventListener('open', function (event) {
    console.log('WebSocket connection established');

    // Send message only after connection is open
    sendMessage('Hello, server!');
});

// Event listener for WebSocket message received
socket.addEventListener('message', function (event) {
    console.log('Message from server:', event.data);
});

// Function to send message
function sendMessage(message) {
    // Check if WebSocket connection is open before sending message
    if (socket.readyState === WebSocket.OPEN) {
        console.log('Sending message:', message);
        socket.send(message);
    } else {
        console.error('WebSocket connection is not open. Message not sent:', message);
    }
}
