create database socketChat;
use socketChat;

create table user (
	userId varchar(32) primary key,
	nickName varchar(8) not null,
	password varchar(16) not null,
	remoteIp varchar(16),
	userPicture varchar(255),
	createDate datetime default now()
)engine=innoDB default charset=utf8

create table chatRoom (
	chatRoomId varchar(32) primary key,
	chatRoomName varchar(32) not null,
	chatRoomCreateDate datetime default now(),
	creatorId varchar(32) references user(userId)
)engine=innoDB default charset=utf8 

create table chatMessage (
	chatMessageId int primary key auto_increment,
	chatMessage text,
	fromUserId varchar(32) references user(userId),
	toUserId varchar(32) references user(userId),
	toChatRoomId varchar(32) references chatRoom(chatRoomId),
	messageSendTime datetime default now(),
	needRead int default 0
)engine=innoDB default charset=utf8

create table friendTab (
	
)