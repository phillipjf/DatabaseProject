drop table if exists Patient;
drop table if exists Record;
drop table if exists DiagnosisGiven;
drop table if exists Ordered;
drop table if exists TreatmentsGiven;
drop table if exists Worker;
drop table if exists JobTitle;
drop table if exists Treatments;
drop table if exists Diagnoses;
drop table if exists Rooms;

create table Patient(pID INT AUTO_INCREMENT PRIMARY KEY, firstName TEXT, lastName TEXT, emergCont TEXT, insurance INT);
create table Record(tNum INT AUTO_INCREMENT PRIMARY KEY, pID INT, dID INT, time TIMESTAMP DEFAULT CURRENT_TIMESTAMP); --what is a timestamp???
create table DiagnosisGiven(tNum INT, dID INT, empID INT, time TIMESTAMP DEFAULT CURRENT_TIMESTAMP);
create table Ordered(orID INT AUTO_INCREMENT PRIMARY KEY, tNum INT, empID INT, tID INT, time TIMESTAMP DEFAULT CURRENT_TIMESTAMP);
create table TreatmentsGiven(orID INT, empID INT, time TIMESTAMP DEFAULT CURRENT_TIMESTAMP);
create table Worker(ssn INT PRIMARY KEY, globID INT, jobID INT, firstName TEXT, lastName TEXT);
create table JobTitle(jobID INT AUTO_INCREMENT PRIMARY KEY, description TEXT);
create table Treatments(tID INT AUTO_INCREMENT PRIMARY KEY, description TEXT);
create table Diagnoses(dID INT AUTO_INCREMENT PRIMARY KEY, description TEXT);
create table Rooms(pID INT, roomNum INT);


insert into Patient(firstName, lastName, emergCont, insurance) values ('John', 'Doe', 'My Mom', 1234);
insert into Patient(firstName, lastName, emergCont, insurance) values ('Mary','Smith','Your Mom',5678);
insert into Patient(firstName, lastName, emergCont, insurance) values ('Jane','Doe','John',9101);
insert into Patient(firstName, lastName, emergCont, insurance) values ('Johnny','Walker','Steve',1121);
insert into Patient(firstName, lastName, emergCont, insurance) values ('Kim','Kardashian','Kanye',3141);
insert into Patient(firstName, lastName, emergCont, insurance) values ('Alice','Cooper','KISS',5161);
insert into Patient(firstName, lastName, emergCont, insurance) values ('John','Locke','Man In Black',4815162342);

insert into Record(pID, dID) values(1,6);
insert into Record(pID, dID) values(2,5);
insert into Record(pID, dID) values(3,4);
insert into Record(pID, dID) values(4,3);
insert into Record(pID, dID) values(5,2);
insert into Record(pID, dID) values(7,7);


insert into DiagnosisGiven(tNum, dID, empID) values(1,1,1);
insert into DiagnosisGiven(tNum, dID, empID) values(2,2,2);
insert into DiagnosisGiven(tNum, dID, empID) values(3,3,3);
insert into DiagnosisGiven(tNum, dID, empID) values(4,4,4);
insert into DiagnosisGiven(tNum, dID, empID) values(5,5,5);
insert into DiagnosisGiven(tNum, dID, empID) values(6,6,6);
insert into DiagnosisGiven(tNum, dID, empID) values(7,7,7);

insert into TreatmentsGiven(orID, empID) values(1,7);
insert into TreatmentsGiven(orID, empID) values(2,6);
insert into TreatmentsGiven(orID, empID) values(3,5);
insert into TreatmentsGiven(orID, empID) values(4,4);
insert into TreatmentsGiven(orID, empID) values(5,3);
insert into TreatmentsGiven(orID, empID) values(6,2);
insert into TreatmentsGiven(orID, empID) values(7,1);

insert into Worker values(111111111,7,1,'Micheal','Scott');
insert into Worker values(222222222,6,2,'Dwight','Schrute');
insert into Worker values(333333333,5,3,'Jim','Halpert');
insert into Worker values(444444444,4,4,'Pam','Beesly');
insert into Worker values(555555555,3,5,'Stanley','Hudson');
insert into Worker values(666666666,2,6,'Kevin','Malone');
insert into Worker values(777777777,1,7,'Phyllis','Vance');

insert into JobTitle(description) values('Doctor');
insert into JobTitle(description) values('Staff');
insert into JobTitle(description) values('Nurse');
insert into JobTitle(description) values('Administrator');
insert into JobTitle(description) values('Technician');
insert into JobTitle(description) values('Janitor');
insert into JobTitle(description) values('Other');

insert into Treatments(description) values('Advil');
insert into Treatments(description) values('Brain Surgery');
insert into Treatments(description) values('Stitches');
insert into Treatments(description) values('Amputation');
insert into Treatments(description) values('Swift kick');
insert into Treatments(description) values('Band-aid');
insert into Treatments(description) values('EOL');

insert into Diagnoses(description) values('Herpes');
insert into Diagnoses(description) values('Cancer');
insert into Diagnoses(description) values('Headache');
insert into Diagnoses(description) values('Missing Arm');
insert into Diagnoses(description) values('Stick in Eye');
insert into Diagnoses(description) values('Stupidity');
insert into Diagnoses(description) values('something else');

insert into Rooms values(1,2);
insert into Rooms values(2,3);
insert into Rooms values(3,5);
insert into Rooms values(4,10);
insert into Rooms values(5,20);
insert into Rooms values(6,40);
insert into Rooms values(7,80);

