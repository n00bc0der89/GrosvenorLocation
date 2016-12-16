var express = require("express");
var app = express();

//***** Routing call ********
var router = require("./router/main");
router(app);
app.use(express.static('images'));
//****** Engine Logic Here ************* //
app.set('view engine','ejs');
app.engine('html', require('ejs').renderFile); //Specify view engine 

var server = app.listen(process.env.PORT || 3000, process.env.IP || "0.0.0.0",function(){
	console.log("Started server on port 3000 !!!");
});