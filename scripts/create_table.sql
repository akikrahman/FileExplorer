/* ***************************************************** **
   create the table to store file information
** ***************************************************** */

create table files (
   id           integer GENERATED ALWAYS AS IDENTITY START WITH 1 INCREMENT BY 1
 , path         varchar2(400 char) not null
 , drive        char(1 char) not null
 , name         varchar2(256 char) not null
 , extension    varchar2(20 char) not null
 , file_size    long not null
 , modified_date date 
);

