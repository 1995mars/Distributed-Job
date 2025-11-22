-- Schedule
INSERT INTO schedule (name, description) VALUES ('Regular', 'Demo schedule');
COMMIT;

-- Task definitions
INSERT INTO task (name, description, class_name) VALUES
    ('Task 1', 'Step 1', 'com.mars.OneCV.batch.Task1Runner');
INSERT INTO task (name, description, class_name) VALUES
    ('Task 2', 'Step 2', 'com.mars.OneCV.batch.Task2Runner');
INSERT INTO task (name, description, class_name) VALUES
    ('Task 3', 'Step 3', 'com.mars.OneCV.batch.Task3Runner');
INSERT INTO task (name, description, class_name) VALUES
    ('Task 4', 'Step 4', 'com.mars.OneCV.batch.Task4Runner');
INSERT INTO task (name, description, class_name) VALUES
    ('Task 5', 'Step 5', 'com.mars.OneCV.batch.Task5Runner');
COMMIT;

-- Map schedule to tasks
INSERT INTO schedule_task (schedule_id, task_id)
SELECT 1, id FROM task;

-- Define dependency
-- Task 2 phụ thuộc Task 1
INSERT INTO task_dependency (schedule_id, task_id, depends_on_task_id) VALUES (1, 2, 1);
-- Task 3 và 4 phụ thuộc Task 2
INSERT INTO task_dependency (schedule_id, task_id, depends_on_task_id) VALUES (1, 3, 2);
INSERT INTO task_dependency (schedule_id, task_id, depends_on_task_id) VALUES (1, 4, 2);
-- Task 5 phụ thuộc Task 4
INSERT INTO task_dependency (schedule_id, task_id, depends_on_task_id) VALUES (1, 5, 4);

COMMIT;
