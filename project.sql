

--human(ssn,first,last)
--worker(globalID, jobID)
--jobTitle(jobID, description)
--room(number,pid)

create table human(
	ssn  char[9],
	first text,
	last text,
	primary key (ssn)
);


create table jobTitle(
	jobID  integer,
	title   text,
	primary key (jobID)
);

create table worker(
	globalID integer,
	ssn char[9] not null,
	jobID integer not null,
	primary key (globalID),
	foreign key (ssn) references patient
	foreign key (jobID) references jobTitle
);

create table room(
	num  integer,
	pid  integer , --NULL = unoccupied
	primary key (num),
	foreign key (pid) references patient
);