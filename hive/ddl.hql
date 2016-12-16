CREATE EXTERNAL TABLE twitterstream
( Tweet_Id bigint,
creeated_at timestamp,
Tweet varchar(8000),
Source varchar(8000),
InReplyToStatusId varchar(100),
InReplyToUserId varchar(100),
InReplyToUserName varchar(100),
UserId varchar(100),
UserFullName varchar(500),
UserScreenName varchar(100),
UserLocation varchar(100),
UserDescription varchar(1000),
UserURL varchar(1000),
UserProtected varchar(100),
UserVerified varchar(100),
UserFollowerCount varchar(100),
UserFollowingCount varchar(100),
UserStatusCount varchar(100),
UserFriendsCount varchar(100),
UserListedCount varchar(100),
UserFavouriteCount varchar(100),
UserCreateAt varchar(100),
UserGeoEnabled varchar(100),
UserLanguage varchar(100),
UserProfileImageURL varchar(1000),
UserDefaultProfile varchar(100),
UserDefaultProfileImage varchar(100),
Geo varchar(500),
Coordinates varchar(1000),
RetweetCount varchar(100),
FavouriteCount int,
Retweeted varchar(100),
Favourited varchar(100),
FilterLevel varchar(100),
Language varchar(100),
PlaceType varchar(100),
PlaceName varchar(100),
PlaceFullName varchar(100),
PlaceCountryCode varchar(100),
PlaceCountry varchar(100),
Place_coordinate1 varchar(100),
Place_coordinate2 varchar(100),
Place_coordinate3 varchar(100),
Place_coordinate4 varchar(100),
Hashtags varchar(1000),
user_mentions varchar(1000),
Urls varchar(1000)
)
PARTITIONED BY (create_date string)
row format delimited fields terminated by "|"
LOCATION '/grosvenor/twitter/grosvenorkafkaflume/';

ALTER TABLE twitterstream ADD PARTITION(create_date='16-11-28') location '/grosvenor/twitter/grosvenorkafkaflume/16-11-28';
ALTER TABLE twitterstream ADD PARTITION(create_date='16-11-29') location '/grosvenor/twitter/grosvenorkafkaflume/16-11-29';
ALTER TABLE twitterstream ADD PARTITION(create_date='16-11-30') location '/grosvenor/twitter/grosvenorkafkaflume/16-11-30';
ALTER TABLE twitterstream ADD PARTITION(create_date='16-12-01') location '/grosvenor/twitter/grosvenorkafkaflume/16-12-01';
ALTER TABLE twitterstream ADD PARTITION(create_date='16-12-02') location '/grosvenor/twitter/grosvenorkafkaflume/16-12-02';
ALTER TABLE twitterstream ADD PARTITION(create_date='16-12-03') location '/grosvenor/twitter/grosvenorkafkaflume/16-12-03';
ALTER TABLE twitterstream ADD PARTITION(create_date='16-12-04') location '/grosvenor/twitter/grosvenorkafkaflume/16-12-04';
ALTER TABLE twitterstream ADD PARTITION(create_date='16-12-05') location '/grosvenor/twitter/grosvenorkafkaflume/16-12-05';
ALTER TABLE twitterstream ADD PARTITION(create_date='16-12-06') location '/grosvenor/twitter/grosvenorkafkaflume/16-12-06';
ALTER TABLE twitterstream ADD PARTITION(create_date='16-12-07') location '/grosvenor/twitter/grosvenorkafkaflume/16-12-07';
ALTER TABLE twitterstream ADD PARTITION(create_date='16-12-08') location '/grosvenor/twitter/grosvenorkafkaflume/16-12-08';
ALTER TABLE twitterstream ADD PARTITION(create_date='16-12-09') location '/grosvenor/twitter/grosvenorkafkaflume/16-12-09';
ALTER TABLE twitterstream ADD PARTITION(create_date='16-12-10') location '/grosvenor/twitter/grosvenorkafkaflume/16-12-10';
ALTER TABLE twitterstream ADD PARTITION(create_date='16-12-11') location '/grosvenor/twitter/grosvenorkafkaflume/16-12-11';
ALTER TABLE twitterstream ADD PARTITION(create_date='16-12-12') location '/grosvenor/twitter/grosvenorkafkaflume/16-12-12';
