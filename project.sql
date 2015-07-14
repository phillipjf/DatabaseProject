

--worker(first, last, globalID,ssn, jobID)
--jobTitle(jobID, description)
--room(number,pid)


drop table if exists jobTitle cascade;
drop table if exists worker cascade;
drop table if exists patient cascade;
drop table if exists room cascade;



create table jobTitle(
	jobID  integer,
	title   char(255),
	primary key (jobID)
);

create table worker(
	first char(255),
	last char(255),
	globalID integer,
	ssn char(9) not null unique,
	jobID integer not null,
	primary key (globalID),
	foreign key (jobID) references jobTitle
);


create table patient(
	pid integer AUTO_INCREMENT,
	first char(255),
	last char(255),
	ssn char(9) not null unique,
	eContact char(255),
	insurance char(255),
	primary key (pid)
) AUTO_INCREMENT=1000;

create table room(
	num  integer AUTO_INCREMENT,
	pid  integer ,
	primary key (num),
	foreign key (pid) references patient
)AUTO_INCREMENT=1000;

INSERT INTO jobTitle VALUES
(9,'Doctor'),
(8,'Nurse'),
(7,'Tech'),
(6,'Staff'),
(5,'Admin'),
(4,'Volunteer');

INSERT INTO room (num)
VALUES
(1002),
(1003),
(1004),
(1005),
(1006),
(1007),
(1008),
(1009),
(1010),
(1011),
(1012),
(1013),
(1014),
(1015),
(1016),
(1017),
(1018),
(1019),
(1020),
(1021),
(1022),
(1023),
(1024),
(1025),
(1026),
(1027),
(1028),
(1029);

















