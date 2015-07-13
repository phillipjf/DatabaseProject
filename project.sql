

--worker(first, last, globalID,ssn, jobID)
--jobTitle(jobID, description)
--room(number,pid)


create table jobTitle(
	jobID  integer,
	title   text,
	primary key (jobID)
);

create table worker(
	first text,
	last text,
	globalID integer,
	ssn char(9) not null unique,
	jobID integer not null,
	primary key (globalID),
	foreign key (jobID) references jobTitle
);


create table patient(
	pid integer AUTO_INCREMENT,
	first text,
	last text,
	ssn char(9) not null unique,
	eContact text,
	insurance text,
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