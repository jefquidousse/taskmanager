DROP TABLE IF EXISTS subtasks;
DROP TABLE IF EXISTS tasks;

CREATE TABLE tasks (
              taskid INT AUTO_INCREMENT PRIMARY KEY,
              taskname VARCHAR(250) NOT NULL,
              description VARCHAR(250),
              date datetime NOT NULL
);

INSERT INTO tasks (taskname, description, date) VALUES
('een', 'eerste taak', NOW()),
('twee', 'tweede taak', NOW()),
('drie', NULL, NOW());



CREATE TABLE subtasks (
       subtaskId int AUTO_INCREMENT primary key,
       subtaskName VARCHAR(250) NOT NULL,
       subtaskDescription VARCHAR(250),
       taskId INT NOT NULL,
       FOREIGN key (taskId) references tasks(taskid)
);

INSERT INTO subtasks(subtaskName, subtaskDescription, taskId) VALUES
  ('een', '1', 1),
  ('twee', '1',3),
  ('erzz', 'zerdz', 2);


