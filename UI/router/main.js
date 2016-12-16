module.exports = function(app){

// *********** Routing Logic Here ************** //
const config = require('./config');
const constants = config.constants;
var fs = require("fs");
const mysql = require('mysql');
var request = require("request");
var url = "http://10.80.2.4:8047/query.json";

 const connection = mysql.createConnection({
  host     : constants.mysql_host,
  port     : constants.mysql_port,
  user     : constants.mysql_username,
  password : constants.mysql_password,
  database : constants.mysql_database
});
 
 connection.connect();
 

app.get("/",function(req,res){
    var stationnames = [];
     connection.query("SELECT name FROM stationnames",function(ferr,frows,ffields)
    {
        if(ferr)
        {
            console.log(ferr);
        }
        else{
            if(frows.length > 0)
            {
                for(var j =0; j < frows.length; j++)
                {
                    console.log(frows[j]);
                 stationnames.push(frows[j].name);   
                }
                var data = {"stationnames" : stationnames }
                res.render("index",data);
            }
        }
        
    });
	
});

app.get("/callmaps",function(req,res){
    
    var dataarray = [];
    var stationnames = [];
   connection.query("SELECT * FROM stationnames",function(ferr,frows,ffields)
    {
        if(ferr)
        {
            console.log(ferr);
        }
        else
        {
            //var frows = data.split('\n');
            //console.log(frows.length);
            if(frows.length > 0)
            {
              function getdata(n)
              {
                  if(n < frows.length)
                  {
                     //var rows = frows[n].split(',');
                     //console.log(rows); 
                   var lat = frows[n].latitude;
                   var lon = frows[n].longitude;
                   var loc = frows[n].name;
                   
                  // console.log("Lat: " + lat);
                  // console.log("Long: " + lon);
                   
                   dataarray.push({"lat" : lat, "lng" : lon,"location": loc, "type": "station" });
                   stationnames.push(loc);
                  // console.log(dataarray);
                   getdata(n + 1);   
                  }
                  else
                  {
                      readRestaurantList(stationnames);
                      
                  }
              }
           getdata(0);
        }
       
        }
    });
        
   function readRestaurantList(stationnames){
       
     connection.query("SELECT * FROM restaurantnames",function(ferr,rrows,ffields)
    {
           if(ferr)
           {
               console.log(ferr);
           }
           else
           {
              // var frw = rdata.split('\n');
               if(rrows.length > 0)
               {
                   function getreslist(n)
                   {
                       if(n < rrows.length)
                       {
                        // var fd  = frw[n].split(',');
                         var name = rrows[n].name;
                         var lat = rrows[n].latitude;
                         var lon = rrows[n].longitude;
                         
                         dataarray.push({"lat" : lat, "lng" : lon,"location": name,"type":"restaurant" });
                         getreslist(n + 1);
                       }
                       else
                       {
                          // console.log(dataarray);
                          
                           res.send({"data" : dataarray, "stationnames" : stationnames});
                       }
                   }
                   getreslist(0);
               }
           }
       });
   }
});

app.get('/getPosition',function(req,res){
    var station = req.query.station;
    var q = "SELECT * FROM stationrest WHERE stationname = '" + station + "'";
    console.log(q);
    var restaurantlist = [];
    
    connection.query(q,function(ferr,frows,ffields)
    {
        if(ferr)
        {
            console.log(ferr);
        }
        else
        {
            if(frows.length > 0)
            {
                function getNearRest(n)
                {
                   if(n < frows.length)
                   {
                       var resname = frows[n].name;
                       restaurantlist.push(resname);
                      // dataarray.push({"restname" : resname, "restlat" : restlat, "restlong" : restlong});
                       getNearRest(n + 1);
                   }
                   else
                   {
                       
                        var stnlat = frows[0].latitude;
                       var stnlong = frows[0].longitude;
                       console.log(stnlat + "," + stnlong);
                       // console.log(restaurantlist);
                       getStationDetail(restaurantlist,stnlat,stnlong,station);
                      
                   }
                }
                getNearRest(0);
            }
        }
    });
    
    
     function getStationDetail(restaurantlist,stnlat,stnlong,station)
    {
        var querystr = "";
        var dataarray = [];
        
        for(var i = 0; i < restaurantlist.length; i++)
        {
	    var resname = restaurantlist[i].replace(/'/g,"''");
            querystr += "'" + resname + "',";
        }
        querystr = querystr.substr(0, querystr.length - 1);
        //querystr = querystr.replace(/'/g,"''");

        console.log("Query: " + querystr);
        
      //  var q = "SELECT name,latitude,longitude FROM restaurantnames where name IN(" + querystr + ")";
       
      /*  connection.query(q,function(ferr,rrows,ffields)
        {
            if(ferr)
            {
                console.log(ferr);
            }
            else
            {
                if(rrows.length > 0)
                {
                    function getRestroDetails(p)
                    {
                       if(p < rrows.length) 
                       {
                        var restname = rrows[p].name;
                        var restlat = rrows[p].latitude;
                        var restlong = rrows[p].longitude;
                        //var type = "restaurant";
                        
                        dataarray.push({"location": restname, "lat" : restlat, "lng" : restlong, "type" : "restaurant"});
                        getRestroDetails(p + 1);
                       }
                       else{
                           
                           console.log(station,stnlat,stnlong);
                        var stnname= station;
                       
                        dataarray.push({"location" : stnname, "lat" : stnlat,"lng" : stnlong, "type" : "station"});
                           res.send(dataarray);
                       }
                    }
                    getRestroDetails(0);
                }
                    
            }
            
        }); */
        
        //Query Drill and get back name,lat,long,Total Post count, Rating of the rest from HDFS. Create array of object and send back to the client.
        var q = "SELECT count(1) as TotalCount, name as RestaurantName,locationlatt as Latitude,locationlong as Longitude FROM `hive_test`.`default`.`restaurants` WHERE name IN (" + querystr + ") group by name,locationlatt,locationlong";
    	
    	var reqoptions = {
        	uri :url,
        	headers:{'Content-Type':'application/json'},
        	method : "POST",
        	body: JSON.stringify({queryType : 'SQL', query : q})
    	    
    	};
    	
    	 request(reqoptions, function(err, response, data){
        	//console.log(response + " " + err + " " + data);
        	if(err)
        	{
        	    console.log("Err: " + err);
        	}
        	if (!err && response.statusCode ==200){
        		console.log("Reached within query");
        		console.log(data);
			//console.log(data.length);
			var obj = JSON.parse(data);

        		if(obj.rows.length > 0)
        		{
        		    function getRestroDetails(p)
        		    {
        		        if(p < obj.rows.length)
        		        {
				try
			   {
        		        
            		        var restname = obj.rows[p].RestaurantName;
            		        var restlat = obj.rows[p].Latitude;
            		        var restlong = obj.rows[p].Longitude;
            		        var postcount = obj.rows[p].TotalCount;
            		        console.log(restname,restlat,restlong,postcount);
            		        
            		        dataarray.push({"location": restname, "lat" : restlat, "lng" : restlong,"postcount" : postcount ,"type" : "restaurant"});
			   }
			catch(ex)
				{ console.log("Error while parsiing :" + ex);}

                                getRestroDetails(p + 1);
        		        }
        		        else
        		        {
                            console.log(station,stnlat,stnlong);
                            var stnname= station;
                           
                            dataarray.push({"location" : stnname, "lat" : stnlat,"lng" : stnlong, "type" : "station"});
                            res.send(dataarray);        		            
        		        }
        		    }
        		    
        		    getRestroDetails(0);
        		}
        		
        	}
    });
    	
        
    }
});


/* app.get('/getPostsCount',function(req,res){
    var restname = req.query.restname;
    console.log("Inside PostCount");
    //Make req call to web api drill and pass query in body req.
    //var data = {"count"  : 30};
    
    	
    	var q = "SELECT count(1) as TotalCount FROM `hive_test`.`default`.`restaurants` WHERE name ='" + restname + "'";
    	
    	var reqoptions = {
        	uri :url,
        	headers:{'Content-Type':'application/json'},
        	method : "POST",
        	body: JSON.stringify({queryType : 'SQL', query : q})
    	    
    	};
    
    request(reqoptions, function(err, response, data){
	//console.log(response + " " + err + " " + data);
	if(err)
	{
	    console.log("Err: " + err);
	}
	if (!err && response.statusCode ==200){
		console.log("Reached");
		console.log(data);
		var obj = JSON.parse(data);
		console.log("Res: " + obj.rows[0].TotalCount);
		var count = obj.rows[0].TotalCount;
		res.send(count);
	}
        
        
    });
    
}); */

}
