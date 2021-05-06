/* ***************************************************** **
   
   Use at your own risk
   *****************************************************
   
   Creation of the user/schema duplicates
   Granting the necessary privileges to this schema
   
   To be executed as a DBA user
   
      make sure to be in the xepdb1 container
   
	SQL> SHOW CON_NAME
	CON_NAME
	------------------------------
	CDB$ROOT
	
	SQL> ALTER SESSION SET CONTAINER = XEPDB1;
	Session altered.
	
	SQL> SHOW CON_NAME
	CON_NAME
	------------------------------
	XEPDB1
   ******************************************************/

create user duplicates identified by duplicates default tablespace users temporary tablespace temp;

alter user duplicates quota unlimited on users;

grant create session    to duplicates;
grant create table      to duplicates;
grant create view       to duplicates;
grant create type       to duplicates;
grant create procedure  to duplicates;
grant create sequence   to duplicates;

/* ***************************************************** */
