/*
https://code.google.com/p/gmaps-api-issues/issues/detail?can=2&start=0&num=100&q=&colspec=ID%20Type%20Status%20Introduced%20Fixed%20Summary%20Stars%20ApiType%20Internal&groupby=&sort=&id=8581
http://stackoverflow.com/questions/32462853/google-places-api-popular-times
*/

"use strict";

const mysql      = require('mysql');
const Twitter = require('twitter');
const config = require('../config/config');
const constants = config.constants;

const connection = mysql.createConnection({
  host     : constants.mysql_host,
  user     : constants.mysql_username,
  password : constants.mysql_password,
  database : constants.mysql_database
});

const client = new Twitter({
	consumer_key: constants.twitter_consumer_key,
	consumer_secret: constants.twitter_consumer_secret,
	access_token_key: constants.twitter_access_token_key,
	access_token_secret: constants.twitter_access_token_secret
});

connection.connect();

connection.query('SELECT screen_name FROM twitterlist WHERE twitterid IS NULL LIMIT 200', function(err, rows, fields) {
	if (err){
		throw err;
	}
	for(let i=0;i<rows.length;i++){
		client.get('users/show', {screen_name:rows[i].screen_name} ,function(error, tweets, response) {
			if(error){
				console.log(error);
			}else{
				connection.query('UPDATE twitterlist SET twitterid = "'+JSON.parse(response.body).id_str+'" WHERE screen_name = "'+rows[i].screen_name+'";', function(err, rows, fields) {
					if (err) throw err;
				});
			}
		});
	};
});

setTimeout(function(){
connection.end();
process.exit(0);
}, 30000);
