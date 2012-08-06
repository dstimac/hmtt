CREATE TRIGGER INSERTPLAYERTRIGG AFTER INSERT on PLAYER
  REFERENCING NEW ROW AS newrow
  FOR EACH ROW
     INSERT INTO PLAYERHISTORY VALUES(
       newrow.ID,
       CURRENT_TIMESTAMP,
       newrow.FIRSTNAME,
       newrow.LASTNAME,
       newrow.AGE,
       newrow.AGEDAYS,
       newrow.TSI,
       newrow.FORM,
       newrow.STAMINA,
       newrow.EXPERIENCE,
       newrow.LEADERSHIP,
       newrow.SALARY,
       newrow.SPECIALITY,
       newrow.GOALKEEPING,
       newrow.PLAYMAKING,
       newrow.SCORING,
       newrow.PASSING,
       newrow.DEFENDING,
       newrow.WINGER,
       newrow.SETPIECES);

CREATE TRIGGER UPDATEPLAYERTRIGG AFTER UPDATE on PLAYER
  REFERENCING NEW ROW AS newrow
  FOR EACH ROW
     INSERT INTO PLAYERHISTORY VALUES(
       newrow.ID,
       CURRENT_TIMESTAMP,
       newrow.FIRSTNAME,
       newrow.LASTNAME,
       newrow.AGE,
       newrow.AGEDAYS,
       newrow.TSI,
       newrow.FORM,
       newrow.STAMINA,
       newrow.EXPERIENCE,
       newrow.LEADERSHIP,
       newrow.SALARY,
       newrow.SPECIALITY,
       newrow.GOALKEEPING,
       newrow.PLAYMAKING,
       newrow.SCORING,
       newrow.PASSING,
       newrow.DEFENDING,
       newrow.WINGER,
       newrow.SETPIECES);

