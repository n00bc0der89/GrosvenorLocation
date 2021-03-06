var graph = require('fbgraph');
const config = require('./config');
const constants = config.constants;
const mysql      = require('mysql');
var fs = require('fs');
var jsonparsing = require('./jsonparsing');
var completedata = ""; 
 graph.setAccessToken(constants.access_token);
 
 const connection = mysql.createConnection({
  host     : constants.mysql_host,
  port     : constants.mysql_port,
  user     : constants.mysql_username,
  password : constants.mysql_password,
  database : constants.mysql_database
});
 connection.connect();
 graph.setVersion("2.8");
 
 
 
 connection.query("SELECT * FROM facebooklist",function(ferr,frows,ffields)
    {
        if(frows.length > 0)
        {
            console.log("Fetching data from mysql");
            function getDetails(n)
            {
                
                if(n < frows.length)
                {
                    var ep = frows[n].epoch;
                    var date = new Date().toISOString();
                    var params = "";
                    completedata = "";
                    
                    if(ep == null)
                    {
                      params = { fields: "id,about,bio,business,category,category_list,cover,description,engagement,fan_count,general_info,hours,is_always_open,is_verified,is_permanently_closed,is_unclaimed,link,location,name,overall_star_rating,place_type,price_range,rating_count,username,verification_status,website,feed.since(01-01-2016){message,place,link,picture,source,actions,message_tags,scheduled_publish_time,created_time}" }; 
                    }
                    else
                    {
                         params = { fields: "id,about,bio,business,category,category_list,cover,description,engagement,fan_count,general_info,hours,is_always_open,is_verified,is_permanently_closed,is_unclaimed,link,location,name,overall_star_rating,place_type,price_range,rating_count,username,verification_status,website,feed.since("+ ep +"){message,place,link,picture,source,actions,message_tags,scheduled_publish_time,created_time}" }; 
                    }
                    
                    
                    graph.get(frows[n].id.toString(), params,  function(err, res) {
                      //console.log(res); // { picture: "http://profile.ak.fbcdn.net/..." } 
                      if(err)
                      {
                          console.log(err);
                      }
			console.log("Fetching data from api");
                      if(res != null)
                      {
                          var data = res;
                         completedata = data;
                         
                      } 
                      
                      if(data.feed != undefined)
                      {
                          if(data.feed.paging && data.feed.paging.next) {
                                recursivecall(data.feed,n);
                          }
                      }
                      else
                      {
                          //For Pages which does not have feeds spanned across pages.
                          jsonparsing.getParsedString(JSON.stringify(completedata));     
 			connection.query('UPDATE facebooklist SET epoch = "'+ new Date().toISOString() +'" WHERE id = "'+ frows[n].id +'";', function(err, rows, fields) {
        			if (err) 
        			 {
        				console.log(err);   
        				}
        			else
        			{
        				//console.log("Updated");
        			}
        		});
                          getDetails(n + 1);
                      }
                    });
                }
            }
            
            getDetails(0);
            
            
            //Recursive function call
            
        function recursivecall(res,n)
          {
              
              if(res.paging && res.paging.next)
              {
                  graph.get(res.paging.next, function(nerr, nres) {
                      if(nerr)
                      {
                          console.log(nerr);
                      }
                        var ndata = nres;
          
                         for(var d = 0; d < ndata.data.length; d++) //Push all paginated feed data into single feed data array
                         {
                           completedata.feed.data.push(ndata.data[d]);
                         }
                          
                          recursivecall(nres,n); //Recursive call
                  });
              }
              else
              {
    			   jsonparsing.getParsedString(JSON.stringify(completedata));
    			      
    			
                  //Update sql table : epoch field with todays date time.
                  connection.query('UPDATE facebooklist SET epoch = "'+ new Date().toISOString() +'" WHERE id = "'+ frows[n].id +'";', function(err, rows, fields) {
					if (err) 
					{
					 console.log(err);   
					}
					else
					{
					    //console.log("Updated");
					}
				});
                  
                  getDetails(n + 1); 
              }
             
          }
  
        }
    });
                      
        
    
