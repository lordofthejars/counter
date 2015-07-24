var express = require('express');

var app = express();

app.get('/', function(req, res){
    res.send('<p id="message"><b>Hello</b> from inside a container!</p>');
});

app.listen(8080);