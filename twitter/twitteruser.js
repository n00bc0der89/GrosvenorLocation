/*
https://code.google.com/p/gmaps-api-issues/issues/detail?can=2&start=0&num=100&q=&colspec=ID%20Type%20Status%20Introduced%20Fixed%20Summary%20Stars%20ApiType%20Internal&groupby=&sort=&id=8581
http://stackoverflow.com/questions/32462853/google-places-api-popular-times
*/

"use strict";

const Twitter = require('twitter');
const config = require('../config/config');
const _ = require('underscore');
const mysql      = require('mysql');
const members = config.members;
const constants = config.constants;
const maxMembers =constants.twitter_maxMembersFetchCount;
const businessHandles = [];

const client = new Twitter({
	consumer_key: constants.twitter_consumer_key,
	consumer_secret: constants.twitter_consumer_secret,
	access_token_key: constants.twitter_access_token_key,
	access_token_secret: constants.twitter_access_token_secret
});

const connection = mysql.createConnection({
  host     : constants.mysql_host,
  user     : constants.mysql_username,
  password : constants.mysql_password,
  database : constants.mysql_database
});
connection.connect();

function deduplicate(){
	let uniqueBusinessHandles = _.uniq(businessHandles);
	console.log(uniqueBusinessHandles.length);
	for(let k=0;k<uniqueBusinessHandles.length;k++){
		connection.query('INSERT INTO twitterlist (screen_name) values("'+uniqueBusinessHandles[k]+'")', function(err, rows, fields) {
			if (err){
				console.log(err);
			}
		});
	}
	setTimeout(function(){
		connection.end();
		process.exit(0);
	}, 30000);
}

for(let i=0;i<members.length;i++){
	client.get('lists/show', {slug:members[i].slug,owner_screen_name:members[i].owner_screen_name} ,function(error, tweets, response) {
        if(error){
			console.log(error);
		}
		client.get('lists/members', {count:maxMembers,list_id:JSON.parse(response.body).id,slug:members[i].slug,owner_screen_name:members[i].owner_screen_name} ,function(error, tweets, result) {
			if(error){
				console.log(error);
			}
			let res = JSON.parse(result.body);
			for(let j=0;j<res.users.length;j++){
				businessHandles.push(res.users[j].screen_name);
			};
		});
	});
}

setTimeout(deduplicate, 60000);
