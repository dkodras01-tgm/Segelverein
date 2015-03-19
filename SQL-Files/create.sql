CREATE TABLE Person
(
  "key" SERIAL,
  "name" character varying(250),
  geburtsdatum date,
  CONSTRAINT key PRIMARY KEY ("key")
);

CREATE TABLE Segler
(
  "key" SERIAL,
  personkey integer,
  PRIMARY KEY ("key"),
  FOREIGN KEY (personkey) REFERENCES Person ("key")
);

CREATE TABLE Trainer
(
  "key" SERIAL,
  personkey integer,
  PRIMARY KEY ("key"),
  FOREIGN KEY (personkey) REFERENCES Person ("key")
);

CREATE TABLE Boot
(
  id SERIAL,
  "name" character varying(250),
  personen integer,
  tiefgang real,
  PRIMARY KEY (id)
);

CREATE TABLE Tourenboot
(
  tourenbootid integer,
  bootsklasse character varying,
  FOREIGN KEY (tourenbootid) REFERENCES Boot (id)
);

CREATE TABLE Sportboot
(
  sportbootid integer,
  segelflaeche double precision,
  PRIMARY KEY (sportbootid),
  FOREIGN KEY (sportbootid) REFERENCES Boot (id)
);

CREATE TABLE Mannschaft
(
  "name" character varying(250),
  aklasse character varying,
  mannschaftkey integer,
  PRIMARY KEY ("name"),
  FOREIGN KEY (mannschaftkey) REFERENCES Trainer ("key")
);

CREATE TABLE Regatta
(
  "name" character varying(250),
  jahr int2,
  land character varying(250),
  PRIMARY KEY ("name",jahr)
);

CREATE TABLE Wettfahrt
(
  wettfahrtname character varying(250),
  wettfahrtjahr int2,
  datum date,
  laenge integer,
  PRIMARY KEY (wettfahrtname, wettfahrtjahr, datum),
  FOREIGN KEY (wettfahrtname, wettfahrtjahr) REFERENCES Regatta ("name", jahr)
);

CREATE TABLE bildet
(
  bildetkey integer,
  bildetname character varying(250),
  FOREIGN KEY (bildetkey) REFERENCES Segler ("key"),
  FOREIGN KEY (bildetname) REFERENCES Mannschaft ("name")
);

CREATE TABLE zugewiesen
(
  zugewiesenid integer,
  zugewiesenname character varying(250),
  FOREIGN KEY (zugewiesenid) REFERENCES Boot (id),
  FOREIGN KEY (zugewiesenname) REFERENCES Mannschaft ("name")
);

CREATE TABLE nimmt_teil
(
  mname character varying(250),
  rname character varying(250),
  rjahr int2,
  sportboot integer,
  startnr integer,
  FOREIGN KEY (mname) REFERENCES Mannschaft ("name"),
  FOREIGN KEY (rname, rjahr) REFERENCES Regatta ("name", jahr),
  FOREIGN KEY (sportboot) REFERENCES Sportboot (sportbootid)
);

CREATE TABLE erzielt
(
  mname character varying(250),
  wname character varying(250),
  wjahr int2,
  wdatum date,
  punkte integer,
  FOREIGN KEY (mname) REFERENCES Mannschaft ("name"),
  FOREIGN KEY (wname, wjahr, wdatum) REFERENCES Wettfahrt (wettfahrtname, wettfahrtjahr, datum)
);

CREATE OR REPLACE VIEW v_b AS SELECT max(boot.id) + 1 AS p FROM boot;