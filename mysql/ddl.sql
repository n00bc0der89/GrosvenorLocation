use grosvenor;

create table facebooklist
( id bigint primary key,
  name varchar(500),
  type varchar(50),
  location varchar(500),
  latitude float,
  longitude float,
  url varchar(200),
  epoch bigint
);

create table twitterlist
(
  screen_name varchar(50),
  twitterid varchar(50)
);
