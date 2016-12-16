package com.mastek.storm.bolt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastek.storm.Topology;
import com.mastek.storm.TweetData;
import com.mastek.storm.TweetParseHelper;

import backtype.storm.task.OutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

/**
 * @author mastek
 * This class parses the incoming messages and decided which bolt the message has to be passed on to
 * There are two cases in this example, first if of solr type and second is of hdfs type.
 */
public class SinkTypeBolt extends BaseRichBolt {


	  String tweet_created_at;
	  String tweet_id;
	  String tweet_id_str;
	  String tweet_text;
	  String tweet_source;
	  
	private static final long serialVersionUID = 1L;
	private OutputCollector collector;
	

	public void execute(Tuple tuple) {
		String value = tuple.getString(0);
		System.out.println("Received in SinkType bolt : "+value);	
		String objectRecord="";		
		try{
			TweetData obj = new ObjectMapper().readValue(value, TweetData.class);
			objectRecord=TweetParseHelper.createObjectRecord(obj);
			if(!"".equals(objectRecord)){
				//Replacing all \n character with blank space
				String formattedString =objectRecord.replace("\n", "");
				collector.emit(Topology.HDFS_STREAM,new Values(formattedString));
				System.out.println("Emitted : "+formattedString);
				collector.ack(tuple);
				
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}			
	}

	public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
		this.collector = collector;
		
	}
	private static String readFile(String filePath){
		BufferedReader br = null;
		String sCurrentLine="";
		String out="";
		try {

			

			br = new BufferedReader(new FileReader(filePath));

			while ((sCurrentLine = br.readLine()) != null) {
				//System.out.println(sCurrentLine);
				out=out+sCurrentLine;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return out;	
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declareStream(Topology.HDFS_STREAM, new Fields( "tweetobject"));
	}

}
