DROP TABLE AnOrder;
DROP TABLE Person;
DROP TABLE Project;
DROP TABLE Formulations;
DROP TABLE Downstream;
DROP TABLE Upstream;
DROP TABLE Preps;
DROP TABLE Synthesis;
DROP TABLE UnitOperation;
DROP TABLE Manager;
DROP TABLE Operator;
DROP TABLE Account;

CREATE TABLE Account (
AccountID DECIMAL(12) NOT NULL PRIMARY KEY,
APassword VARCHAR(12) NOT NULL,
Email VARCHAR(36) NOT NULL,
PersonID DECIMAL(12) NOT NULL,
UnitOperationID DECIMAL(2) NULL);

CREATE TABLE Operator (
AccountID DECIMAL(12) NOT NULL PRIMARY KEY,
Assignment VARCHAR(100) NOT NULL,
FOREIGN KEY (AccountID) REFERENCES Account(AccountID)); 

CREATE TABLE Manager (
AccountID DECIMAL(12) NOT NULL PRIMARY KEY,
Permission_Level DECIMAL(2) NOT NULL,
FOREIGN KEY (AccountID)  REFERENCES Account(AccountID)); 

CREATE TABLE UnitOperation (
UnitOperationID DECIMAL(2) NOT NULL PRIMARY KEY,
Attempt_Number DECIMAL(2) NOT NULL);

CREATE TABLE Synthesis (
UnitOperationID DECIMAL(2) NOT NULL PRIMARY KEY,
SynthesisYF DECIMAL(2,2) NOT NULL,
FOREIGN KEY (UnitOperationID) REFERENCES UnitOperation(UnitOperationID));

CREATE TABLE Preps (
UnitOperationID DECIMAL(2) NOT NULL PRIMARY KEY,
PrepsYF DECIMAL (2,2) NOT NULL,
FOREIGN KEY (UnitOperationID) REFERENCES UnitOperation(UnitOperationID));

CREATE TABLE Upstream (
UnitOperationID DECIMAL(2) NOT NULL PRIMARY KEY,
UpstreamYF DECIMAL (2,2) NOT NULL,
FOREIGN KEY (UnitOperationID) REFERENCES UnitOperation(UnitOperationID));

CREATE TABLE Downstream (
UnitOperationID DECIMAL(2) NOT NULL PRIMARY KEY,
DownstreamYF DECIMAL (2,2) NOT NULL,
FOREIGN KEY (UnitOperationID) REFERENCES UnitOperation(UnitOperationID));

CREATE TABLE Formulations (
UnitOperationID DECIMAL(2) NOT NULL PRIMARY KEY,
FormulationsYF DECIMAL(2,2) NOT NULL,
FOREIGN KEY (UnitOperationID) REFERENCES UnitOperation(UnitOperationID));

CREATE TABLE Project (
ProjectID DECIMAL(12) NOT NULL PRIMARY KEY,
ProjectName VARCHAR(36) NOT NULL,
Email VARCHAR(36) NOT NULL,
PersonID DECIMAL(12) NOT NULL)

CREATE TABLE Person (
PersonID DECIMAL (12) NOT NULL PRIMARY KEY,
Email VARCHAR(36) NOT NULL,
FirstName VARCHAR(16) NOT NULL,
LastName VARCHAR(16) NOT NULL,
AccountID DECIMAL(12) NULL,
ProjectID DECIMAL(12) NULL,
FOREIGN KEY (AccountID) REFERENCES Account (AccountID),
FOREIGN KEY (ProjectID) REFERENCES Project (ProjectID));

CREATE TABLE AnOrder (
OrderID DECIMAL(12) NOT NULL PRIMARY KEY,
ProjectID DECIMAL(12) NOT NULL,
UnitOperationID DECIMAL(2) NOT NULL,
TargetConcentration DECIMAL(6,2) NOT NULL,
TargetLength DECIMAL(4) NOT NULL,
FOREIGN KEY (ProjectID) REFERENCES Project (ProjectID));

CREATE INDEX OperatorAccountIDIdx
ON Operator(AccountID);

CREATE INDEX ManagerAccountIDIdx
ON Manager(AccountID);

CREATE INDEX SynthesisUnitOperationIDIdx
ON Synthesis(UnitOperationID);

CREATE INDEX PrepsUnitOperationIDidx
ON Preps(UnitOperationID);

CREATE INDEX UpstreamUnitOperationIDIdx
ON Upstream(UnitOperationID);

CREATE INDEX FormulationsUnitOperationIDIdx
ON Upstream(UnitOperationID);

CREATE INDEX PersonAccountIDIdx
ON Person(AccountID);

CREATE INDEX PersonProjectIDIdx
ON Person(ProjectID);

CREATE INDEX AnOrderUnitOperationIDIdx
ON AnOrder(UnitOperationID);

CREATE INDEX AnOrderTargetConcentrationIdx
ON AnOrder(TargetConcentration);

CREATE INDEX AnOrderTargetLengthIdx
ON AnOrder(TargetLength);

CREATE INDEX ProjectProjectNameIdx
ON Project(ProjectName);






-- Creating first procedure, AddOperatorAccount

CREATE PROCEDURE AddOperatorAccount @AccountID DECIMAL(12), @APassword VARCHAR(12),
				@Email VARCHAR(36), @PersonID DECIMAL(12), @UnitOperationID DECIMAL(2),
				@Assignment VARCHAR(100),@FirstName VARCHAR(16), @LastName VARCHAR(16)
AS
BEGIN
	INSERT INTO Account(AccountID, APassword, Email, PersonID, UnitOperationID)
	VALUES(@AccountID, @APassword, @Email, @PersonID, NULL);

	INSERT INTO Operator(AccountID, Assignment)
	VALUES(@AccountID, @Assignment);

	INSERT INTO Person(PersonID, Email, FirstName, LastName, AccountID, ProjectID)
	VALUES(@PersonID, @Email, @FirstName, @LastName, @AccountID, NULL);
END;
go




-- using AddOperatorAccount to add operator Bill Smith

BEGIN TRANSACTION AddOperatorAccount;
	EXECUTE AddOperatorAccount 200001, 'ExPassword', 
	'billsmith@work.com', 100001, NULL,
	'Run Synthesis tonight', 'Bill', 'Smith';
	COMMIT TRANSACTION AddOperatorAccount;


-- Creating second procedure, NewProject

CREATE PROCEDURE NewProject @ProjectID DECIMAL(12), @ProjectName VARCHAR(36),
							@Email VARCHAR(36), @PersonID DECIMAL(12),
							@FirstName VARCHAR(16), @LastName VARCHAR(16)
AS
BEGIN
	INSERT INTO Project(ProjectID, ProjectName, Email, PersonID)
	VALUES(@ProjectID, @ProjectName, @Email, @PersonID);

	INSERT INTO Person(PersonID, Email, FirstName, LastName, AccountID)
	VALUES(@PersonID, @Email, @FirstName, @LastName, NULL);

END;
go

-- Using NewProject to add the first project

BEGIN TRANSACTION NewProject;
EXECUTE NewProject 10001, 'First Cancer Vaccine', 'FirstCustomer@customer.com',
				   200002, 'Leesa', 'Burke';
COMMIT TRANSACTION NewProject;



-- Creating third procedure, AddOrder

CREATE PROCEDURE AddOrder @OrderID DECIMAL(12), @ProjectID DECIMAL(12), @UnitOperationID DECIMAL(2),
				 @TargetConcentration DECIMAL(6,2), @TargetLength DECIMAL (4)
AS
BEGIN
	INSERT INTO AnOrder(OrderId, ProjectId, UnitOperationId, TargetConcentration, TargetLength)
	VALUES(@OrderID, @ProjectID, @UnitOperationID, @TargetConcentration, @TargetLength);
END;
go

-- 

-- Using AddOrder to add the first order 

BEGIN TRANSACTION AddOrder;
EXECUTE AddOrder 10000001, 10001, 01, 200.00, 1482;
COMMIT TRANSACTION AddOrder;




-- Creating forth procedure, AssignUnitOp

CREATE PROCEDURE AssignUnitOp @UnitOperationID DECIMAL(2), @AccountID DECIMAL(12)
AS
BEGIN
	UPDATE Account
	SET UnitOperationID = @UnitOperationID
	WHERE AccountID = @AccountID;
END;
go


-- Using AssignUnitOp to assign Bill the unit op, synthesis

BEGIN TRANSACTION AssignUnitOp;
EXECUTE AssignUnitOp 1, 10001;
COMMIT TRANSACTION AssignUnitOp;


-- Creating a History table for changes to an order's target concentration
CREATE TABLE TCChange (
TCChangeID DECIMAL (12) NOT NULL PRIMARY KEY,
OldTC DECIMAL (6,2) NOT NULL,
NEWTC DECIMAL (6,2) NOT NULL,
OrderID DECIMAL(12) NOT NULL,
ChangeDate DATE NOT NULL,
FOREIGN KEY (OrderID) REFERENCES AnOrder(OrderID));


-- Creating a trigger to update the history table, TCChange

CREATE TRIGGER TCChangeTrigger
ON AnOrder
AFTER UPDATE
AS
BEGIN
	DECLARE @OldTC DECIMAL(6,2) = (SELECT TargetConcentration FROM DELETED);
	DECLARE @NewTC DECIMAL(6,2) = (SELECT TargetConcentration FROM INSERTED);

	IF (@OldTC <> @NewTC)
		INSERT INTO TCChange(TCChangeID, OldTC, NEWTC, OrderID, ChangeDate)
		VALUES(ISNULL((SELECT MAX(TCChangeID)+1 FROM TCChange), 1),
			@OldTC,
			@NewTC,
			(SELECT OrderID FROM INSERTED),
			GETDATE());
END;


-- Adding rows to perform queries

-- Adding a second project
BEGIN TRANSACTION NewProject;
EXECUTE NewProject 10002, 'Zika Vaccine', 'SecondCustomer@customer.com',
				   200003, 'Marie', 'Keyes';
COMMIT TRANSACTION NewProject;



-- Adding some orders under this project


BEGIN TRANSACTION AddOrder;
EXECUTE AddOrder 10000002, 10002, 01, 300.00, 1182;
COMMIT TRANSACTION AddOrder;

BEGIN TRANSACTION AddOrder;
EXECUTE AddOrder 10000003, 10002, 02, 120.00, 312;
COMMIT TRANSACTION AddOrder;

BEGIN TRANSACTION AddOrder;
EXECUTE AddOrder 10000004, 10002, 01, 600.00, 892;
COMMIT TRANSACTION AddOrder;


--- adding some more orders to the first project

BEGIN TRANSACTION AddOrder;
EXECUTE AddOrder 10000005, 10001, 01, 800.00, 100;
COMMIT TRANSACTION AddOrder;

BEGIN TRANSACTION AddOrder;
EXECUTE AddOrder 10000006, 10001, 02, 660.00, 4331;
COMMIT TRANSACTION AddOrder;

BEGIN TRANSACTION AddOrder;
EXECUTE AddOrder 10000007, 10001, 01, 50.00, 120;
COMMIT TRANSACTION AddOrder;



-- Query 1: Largest length order submitted

SELECT 
	OrderID, 
	TargetLength
FROM 
	AnOrder
WHERE 
	TargetLength = (
					SELECT
					MAX(TargetLength)
					FROM AnOrder);


-- Added procedure to add managers

CREATE PROCEDURE AddManagerAccount @AccountID DECIMAL(12), @APassword VARCHAR(12),
				@Email VARCHAR(36), @PersonID DECIMAL(12), @Permission_Level DECIMAL(2),
				@FirstName VARCHAR(16), @LastName VARCHAR(16)
AS
BEGIN
	INSERT INTO Account(AccountID, APassword, Email, PersonID, UnitOperationID)
	VALUES(@AccountID, @APassword, @Email, @PersonID, NULL);

	INSERT INTO Manager(AccountID, Permission_Level)
	VALUES(@AccountID, @Permission_Level);

	INSERT INTO Person(PersonID, Email, FirstName, LastName, AccountID, ProjectID)
	VALUES(@PersonID, @Email, @FirstName, @LastName, @AccountID, NULL);
END;
go


-- Adding a few managers

BEGIN TRANSACTION AddManagerAccount;
	EXECUTE AddManagerAccount 200002, 'ExPassword2', 
	'edpominville@work.com', 100004, 3, 
	'Ed', 'Pominville';
	COMMIT TRANSACTION AddOperatorAccount;

BEGIN TRANSACTION AddManagerAccount;
	EXECUTE AddManagerAccount 200003, 'ExPassword2', 
	'erikbrizee@work.com', 100005, 4, 
	'Erik', 'Brizee';
	COMMIT TRANSACTION AddOperatorAccount;
	
	BEGIN TRANSACTION AddManagerAccount;
	EXECUTE AddManagerAccount 200004, 'ExPassword2', 
	'erikbrizee@work.com', 100006, 2, 
	'Richard', 'Gabriel';
	COMMIT TRANSACTION AddOperatorAccount;
	

-- Added another operator 

BEGIN TRANSACTION AddOperatorAccount;
	EXECUTE AddOperatorAccount 200005, 'ExPassword', 
	'erickfigueroa', 100007, NULL,
	'Run Upstream tonight', 'Erick', 'Figueroa';
	COMMIT TRANSACTION AddOperatorAccount;
	
	
--  Updating an order to populate history rows
UPDATE AnOrder
SET TargetConcentration = 20
WHERE OrderID = 10000001;

UPDATE AnOrder
SET TargetConcentration = 1000
WHERE OrderID = 1000000;


-- Query 2: Giving the customer's information for a recently updated order
SELECT TCChange.OrderID, Person.FirstName, Person.LastName, Person.Email
FROM TCChange
JOIN AnOrder ON AnOrder.OrderID = TCChange.OrderID
JOIN Project ON Project.ProjectID = AnOrder.ProjectID
JOIN Person ON Person.PersonID = Project.PersonID
WHERE TCChangeID = 2;




-- Query 3, providing last name, first name, account id of all operators
-- and managers in order of last name

SELECT LastName, FirstName, Account.AccountID
FROM Operator
JOIN Account ON Account.AccountID = Operator.AccountID
JOIN Person ON Person.PersonID = Account.PersonID
UNION
SELECT LastName, FirstName, Account.AccountID
FROM Manager
JOIN Account ON Account.AccountID = Manager.AccountID
JOIN Person ON Person.PersonID = Account.PersonID
ORDER BY LastName;


	
	
