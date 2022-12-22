CREATE TABLE Users(
                      Id_User INT AUTO_INCREMENT,
                      Name VARCHAR(42) NOT NULL,
                      Email VARCHAR(255) NOT NULL,
                      Password VARCHAR(255) NOT NULL,
                      PRIMARY KEY(Id_User),
                      UNIQUE(Email)
);

CREATE TABLE Messages(
                         Id_Message INT AUTO_INCREMENT,
                         Content TEXT NOT NULL,
                         Id_User INT NOT NULL,
                         Id_User_1 INT NOT NULL,
                         PRIMARY KEY(Id_Message),
                         FOREIGN KEY(Id_User) REFERENCES Users(Id_User),
                         FOREIGN KEY(Id_User_1) REFERENCES Users(Id_User)
);

CREATE TABLE Groups(
                       Id_Group INT AUTO_INCREMENT,
                       Name VARCHAR(255) NOT NULL,
                       PRIMARY KEY(Id_Group)
);

CREATE TABLE GroupMessages(
                              Id_GroupMessage INT AUTO_INCREMENT,
                              Content TEXT,
                              Id_User INT NOT NULL,
                              Id_Group INT NOT NULL,
                              PRIMARY KEY(Id_GroupMessage),
                              FOREIGN KEY(Id_User) REFERENCES Users(Id_User),
                              FOREIGN KEY(Id_Group) REFERENCES Groups(Id_Group)
);

CREATE TABLE GroupAttachements(
                                  Id_GroupAttachement INT AUTO_INCREMENT,
                                  Type VARCHAR(255),
                                  FileName VARCHAR(500) NOT NULL,
                                  Size BIGINT,
                                  Id_GroupMessage INT NOT NULL,
                                  PRIMARY KEY(Id_GroupAttachement),
                                  FOREIGN KEY(Id_GroupMessage) REFERENCES GroupMessages(Id_GroupMessage)
);

CREATE TABLE Tasks(
                      Id_Task INT AUTO_INCREMENT,
                      Title VARCHAR(255) NOT NULL,
                      Done BOOLEAN,
                      Id_User INT NOT NULL,
                      PRIMARY KEY(Id_Task),
                      FOREIGN KEY(Id_User) REFERENCES Users(Id_User)
);

CREATE TABLE Attachements(
                             Id_Attachement INT AUTO_INCREMENT,
                             Type VARCHAR(255),
                             FileName VARCHAR(500) NOT NULL,
                             Size TEXT,
                             Id_Message INT NOT NULL,
                             PRIMARY KEY(Id_Attachement),
                             FOREIGN KEY(Id_Message) REFERENCES Messages(Id_Message)
);

CREATE TABLE GroupMembers(
                             Id_Group INT,
                             Id_User INT,
                             Role VARCHAR(255),
                             IsOwner BOOLEAN NOT NULL,
                             PRIMARY KEY(Id_Group, Id_User),
                             FOREIGN KEY(Id_Group) REFERENCES Groups(Id_Group),
                             FOREIGN KEY(Id_User) REFERENCES Users(Id_User)
);


