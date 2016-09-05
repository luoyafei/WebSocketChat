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

/**
 * 需求功能
 * user创建一个room，可以选择是否加密，加密后，他人输入密码，则可以进入，反之不加密 可以直接进入
 */
create table chatRoom (
	chatRoomId varchar(32) primary key,
	chatRoomName varchar(32) not null,
	creatorId varchar(32),
	chatRoomCreateDate datetime default now(),
	chatRoomCover varchar(255),
	chatBrief varchar(255),
	chatRoomPassword varchar(6),
	needPassword varchar(1),
	constraint foreign key (creatorId) references user (userId)
)engine=innoDB default charset=utf8 

create table chatMessage (
	chatMessageId varchar(32) primary key,
	chatMessage text,
	chatImg varchar(255),
	chatVideo varchar(255),
	chatRadio varchar(255),
	chatPosition varchar(255),
	fromUserId varchar(32),
	toUserId varchar(32),
	toChatRoomId varchar(32),
	messageSendTime datetime default now(),
	needRead int default 0,
	constraint foreign key (fromUserId) references user (userId),
	constraint foreign key (toUserId) references user (userId)
)engine=innoDB default charset=utf8
--申请成为好友
create table applyAdd (
	applyAddId varchar(32) primary key,
	applyAddUser varchar(32),
	toUser varchar(32),
	applyAddReason varchar(255),
	applyAddCreateDate datetime default now(),
	constraint foreign key (applyAddUser) references User (userId),
	constraint foreign key (toUser) references User (userId)
)engine=innoDB default charset=utf8
--邀请加入聊天室
create table inviteJoin (
	inviteJoinId varchar(32) primary key,
	inviteJoinRoomId varchar(32),
	inviteJoinUserId varchar(32),
	inviteCreateDate datetime default now(),
	constraint foreign key (inviteJoinRoomId) references chatRoom (chatRoomId),
	constraint foreign key (inviteJoinUserId) references user (userId)
)engine=innoDB default charset=utf8

create table friendTab (
	friendTabId varchar(32) primary key,
	friendAId varchar(32),
	friendBId varchar(32),
	constraint foreign key (friendAId) references user (userId),
	constraint foreign key (friendBId) references user (userId)
)engine=innoDB default charset=utf8