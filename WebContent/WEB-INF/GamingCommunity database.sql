create schema brigade;
create table MEMBER (
   member_id INT NOT NULL auto_increment,
   gamertag   VARCHAR(20) default NULL,
   first_name VARCHAR(20) default NULL,
   last_name  VARCHAR(20) default NULL,
   email_id   INT  default NULL,
   rank_id    INT  default NULL,
   rank_date  DATE default NULL,
   company_id INT  default NULL,
   recommend_id INT  default NULL,
   join_date  DATE default NULL,
   member_active TINYINT default 1,
   PRIMARY KEY (member_id)
);
select *
from brigade.member;
create table COMPANY (
   company_id     INT NOT NULL auto_increment,
   company_name   VARCHAR(20) default NULL,
   captain_id     INT  default NULL,
   lieutenant_id  INT  default NULL,
   company_active TINYINT default 1,
   PRIMARY KEY (company_id)
);
create table EMAIL (
   email_id     INT NOT NULL auto_increment,
   email_name   VARCHAR(20) default NULL,
   email_active TINYINT default 1,
   PRIMARY KEY (email_id)
);
create table MESSAGE (
   message_id 	   INT NOT NULL auto_increment,
   messageboard_id INT  default NULL,
   main_message    TINYINT default 1,
   main_id         INT  default NULL,	
   post_time       DATE default NULL,
   member_id       INT  default NULL,	
   message_title   VARCHAR(20) default NULL,	
   message_body    VARCHAR(80) default NULL,	
   PRIMARY KEY (message_id)
);
create table MESSAGEBOARD (
   messageboard_id     INT NOT NULL auto_increment,
   messageboard_name   VARCHAR(20) default NULL,
   messageboard_active TINYINT default 1,
   rank_access         TINYINT default 1,
   PRIMARY KEY (messageboard_id)
);
create table RANK (
   rank_id     INT NOT NULL auto_increment,
   rank_name   VARCHAR(20) default NULL,
   rank_active TINYINT default 1,
   PRIMARY KEY (rank_id)
);
create table MESSAGEBOARDACCESS (
   boardaccess_id  INT NOT NULL auto_increment,
   messageboard_id INT  default NULL,
   access_id       INT  default NULL,
   PRIMARY KEY (boardaccess_id)
);
