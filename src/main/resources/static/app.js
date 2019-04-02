var stompClient = null;

function setConnected(connected) {
  $('#connect').prop('disabled', connected);
  $('#disconnect').prop('disabled', !connected);
  if (connected) {
    $('#conversation').show();
  } else {
    $('#conversation').hide();
  }
  $('#messages').html('');
}

function connect() {
  var socket = new SockJS('/messages/ws');
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function(frame) {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/queue/messages', function(greeting) {
      console.log(greeting);
      showMessages(JSON.parse(greeting.body));
    });
  });
}

function disconnect() {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  setConnected(false);
}

function sendMessage() {
  var formData = $('#messageForm').serializeArray();
  var loginFormObject = {};
  $.each(formData, function(i, v) {
    loginFormObject[v.name] = v.value;
  });

  $.ajax({
    type: 'POST',
    url: '/messages/message',
    data: JSON.stringify(loginFormObject),
    success: function() {},
    dataType: 'json',
    contentType: 'application/json',
  });
}

function showMessages(message) {
  $('#messages').append(
    '<tr><td>' +
      message.message +
      '</td> <td> ' +
      message.sender +
      '</td> <td>' +
      message.messageTime +
      '</td></tr>'
  );
}

$(function() {
  $('form').on('submit', function(e) {
    e.preventDefault();
  });
  $('#connect').click(function() {
    connect();
  });
  $('#disconnect').click(function() {
    disconnect();
  });
  $('#send').click(function() {
    sendMessage();
  });
});
