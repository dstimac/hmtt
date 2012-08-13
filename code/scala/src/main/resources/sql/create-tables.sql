create CACHED table SKILLLEVEL(
  ID integer NOT NULL,
  NAME varchar(40) NOT NULL,
  PRIMARY KEY (ID));

create CACHED table SPECIALITY(
  ID integer NOT NULL,
  NAME varchar (40) NOT NULL,
  PRIMARY KEY (ID));

create CACHED table PLAYER(
  ID integer NOT NULL,
  FIRSTNAME varchar(40) NOT NULL,
  LASTNAME varchar(40) NOT NULL,
  AGE integer NOT NULL,
  AGEDAYS integer NOT NULL,
  TSI integer NOT NULL,
  FORM integer NOT NULL,
  STAMINA integer NOT NULL,
  EXPERIENCE integer NOT NULL,
  LEADERSHIP integer NOT NULL,
  SALARY integer NOT NULL,
  SPECIALITY integer NOT NULL,
  GOALKEEPING integer NOT NULL,
  PLAYMAKING integer NOT NULL,
  SCORING integer NOT NULL,
  PASSING integer NOT NULL,
  DEFENDING integer NOT NULL,
  WINGER integer NOT NULL,
  SETPIECES integer NOT NULL,
  INCLUB boolean  DEFAULT true NOT NULL,
  PRIMARY KEY (ID),
  FOREIGN KEY (FORM) REFERENCES SKILLLEVEL(ID),
  FOREIGN KEY (STAMINA) REFERENCES SKILLLEVEL(ID),
  FOREIGN KEY (EXPERIENCE) REFERENCES SKILLLEVEL(ID),
  FOREIGN KEY (LEADERSHIP) REFERENCES SKILLLEVEL(ID),
  FOREIGN KEY (GOALKEEPING) REFERENCES SKILLLEVEL(ID),
  FOREIGN KEY (PLAYMAKING) REFERENCES SKILLLEVEL(ID),
  FOREIGN KEY (SCORING) REFERENCES SKILLLEVEL(ID),
  FOREIGN KEY (PASSING) REFERENCES SKILLLEVEL(ID),
  FOREIGN KEY (DEFENDING) REFERENCES SKILLLEVEL(ID),
  FOREIGN KEY (WINGER) REFERENCES SKILLLEVEL(ID),
  FOREIGN KEY (SETPIECES) REFERENCES SKILLLEVEL(ID),
  FOREIGN KEY (SPECIALITY) REFERENCES SPECIALITY(ID)
);

create CACHED table PLAYERHISTORY(
  ID integer NOT NULL,
  TIMESTAMP timestamp NOT NULL,
  FIRSTNAME varchar(40) NOT NULL,
  LASTNAME varchar(40) NOT NULL,
  AGE integer NOT NULL,
  AGEDAYS integer NOT NULL,
  TSI integer NOT NULL,
  FORM integer NOT NULL,
  STAMINA integer NOT NULL,
  EXPERIENCE integer NOT NULL,
  LEADERSHIP integer NOT NULL,
  SALARY integer NOT NULL,
  SPECIALITY integer NOT NULL,
  GOALKEEPING integer NOT NULL,
  PLAYMAKING integer NOT NULL,
  SCORING integer NOT NULL,
  PASSING integer NOT NULL,
  DEFENDING integer NOT NULL,
  WINGER integer NOT NULL,
  SETPIECES integer NOT NULL,
  PRIMARY KEY (ID, TIMESTAMP),
  FOREIGN KEY (ID) REFERENCES PLAYER(ID),
  FOREIGN KEY (FORM) REFERENCES SKILLLEVEL(ID),
  FOREIGN KEY (STAMINA) REFERENCES SKILLLEVEL(ID),
  FOREIGN KEY (EXPERIENCE) REFERENCES SKILLLEVEL(ID),
  FOREIGN KEY (LEADERSHIP) REFERENCES SKILLLEVEL(ID),
  FOREIGN KEY (GOALKEEPING) REFERENCES SKILLLEVEL(ID),
  FOREIGN KEY (PLAYMAKING) REFERENCES SKILLLEVEL(ID),
  FOREIGN KEY (SCORING) REFERENCES SKILLLEVEL(ID),
  FOREIGN KEY (PASSING) REFERENCES SKILLLEVEL(ID),
  FOREIGN KEY (DEFENDING) REFERENCES SKILLLEVEL(ID),
  FOREIGN KEY (WINGER) REFERENCES SKILLLEVEL(ID),
  FOREIGN KEY (SETPIECES) REFERENCES SKILLLEVEL(ID),
  FOREIGN KEY (SPECIALITY) REFERENCES SPECIALITY(ID)
);

create CACHED table LASTSKILLUPDATE(
  PLAYERID integer NOT NULL,
  STAMINA timestamp NOT NULL,
  EXPERIENCE timestamp NOT NULL,
  GOALKEEPING timestamp NOT NULL,
  PLAYMAKING timestamp NOT NULL,
  SCORING timestamp NOT NULL,
  PASSING timestamp NOT NULL,
  DEFENDING timestamp NOT NULL,
  WINGER timestamp NOT NULL,
  SETPIECES timestamp NOT NULL,
  PRIMARY KEY (PLAYERID),
  FOREIGN KEY (PLAYERID) REFERENCES PLAYER(ID)
  );

create CACHED table ACCESSTOKEN(
  KEY varchar(40) NOT NULL,
  SECRET varchar(40) NOT NULL);

create CACHED table BASETRAININGLENGHT(
  TRAININGTYPE varchar(40) NOT NULL,
  TRAININGLENGTH float NOT NULL,
  PRIMARY KEY (TRAININGTYPE));

create CACHED table NEXTSKILLFACTOR(
  SKILL integer NOT NULL,
  FACTOR float NOT NULL,
  PRIMARY KEY (SKILL));

create CACHED table TRAININGTYPE(
  ID integer NOT NULL,
  NAME varchar(40) NOT NULL,
  PRIMARY KEY (ID));

create CACHED table TRAINING(
  TRAININGLEVEL integer NOT NULL,
  TRAININGTYPE integer NOT NULL,
  STAMINAPART integer NOT NULL,
  LASTTRAININGTYPE integer NOT NULL,
  LASTTRAININGLEVEL integer NOT NULL,
  LASTSTAMINAPART integer NOT NULL,
  TRAINERID integer NOT NULL,
  FOREIGN KEY (TRAININGTYPE) REFERENCES TRAININGTYPE(ID),
  FOREIGN KEY (LASTTRAININGTYPE) REFERENCES TRAININGTYPE(ID),
  FOREIGN KEY (TRAINERID) REFERENCES PLAYER(ID)
);

create CACHED table TRAINERTYPE(
  ID integer NOT NULL,
  NAME varchar(40) NOT NULL,
  PRIMARY KEY (ID)
  );

create CACHED table TRAINERINFO(
  TRAINERID integer NOT NULL,
  TYPE integer NOT NULL,
  SKILL integer NOT NULL,
  PRIMARY KEY (TRAINERID),
  FOREIGN KEY (TRAINERID) REFERENCES PLAYER(ID),
  FOREIGN KEY (TYPE) REFERENCES TRAINERTYPE(ID)
  );

create CACHED table TEAM(
  ID integer NOT NULL,
  NAME varchar(40) NOT NULL,
  ASSISTANTTRAINERS integer NOT NULL,
  PSYCHOLOGISTS integer NOT NULL,
  PRESSSPOKESMEN integer NOT NULL,
  PHYSIOTHERAPISTS integer NOT NULL,
  DOCTORS integer NOT NULL,
  PRIMARY KEY (ID)
  );

