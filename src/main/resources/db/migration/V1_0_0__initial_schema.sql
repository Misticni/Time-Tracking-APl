CREATE SCHEMA IF NOT EXISTS users;
CREATE SCHEMA IF NOT EXISTS projects;

CREATE TABLE users.u_role(
    "id" BIGSERIAL NOT NULL,
    "role" TEXT,
    "details" TEXT,
    PRIMARY KEY ("id"),
    UNIQUE ("role")
);

CREATE TABLE users.u_user(
  "id" BIGSERIAL NOT NULL,
  "date_created" TIMESTAMP WITH TIME ZONE,
  "date_modified" TIMESTAMP WITH TIME ZONE,
  "date_deleted" TIMESTAMP WITH TIME ZONE,
  "email" TEXT,
  "password" TEXT,
  "enabled" BOOLEAN NOT NULL,
  "u_person_id" BIGINT,
  PRIMARY KEY ("id"),
  UNIQUE ("email")
);

CREATE TABLE users.u_user_role (
  "id" BIGSERIAL NOT NULL,
  "u_user_id" BIGINT,
  "u_role_id" BIGINT,
  PRIMARY KEY ("id")
);

CREATE TABLE users.u_person(
  "id" BIGSERIAL NOT NULL,
  "first_name" TEXT,
  "last_name" TEXT,
  "phone_number" TEXT,
  PRIMARY KEY ("id")
);

CREATE TABLE projects.pr_project(
    "id" BIGSERIAL NOT NULL,
    "name" TEXT,
    "date_created" TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY ("id"),
    UNIQUE ("name")
);

CREATE TABLE projects.pr_assignment(
    "id" BIGSERIAL NOT NULL,
    "name" TEXT,
    "date_created" TIMESTAMP WITH TIME ZONE,
    "date_modified" TIMESTAMP WITH TIME ZONE,
    "start_time" TIMESTAMP WITH TIME ZONE,
    "end_time" TIMESTAMP WITH TIME ZONE,
    "pr_project_id" BIGINT,
    "creator_u_user_id" BIGINT,
    PRIMARY KEY ("id")
);

CREATE TABLE projects.pr_assigned_users(
   "id" BIGSERIAL NOT NULL,
   "assigned_user_u_id" BIGINT,
   "pr_assignment_id" BIGINT,
   PRIMARY KEY("id")
);

CREATE TABLE projects.pr_report(
    "id" BIGSERIAL NOT NULL,
    "pr_assignment_id" BIGINT NOT NULL,
    "report_details" TEXT,
    PRIMARY KEY ("id")
);

ALTER TABLE users.u_user ADD CONSTRAINT "u_user_users.u_person_fk1" FOREIGN KEY("u_person_id") REFERENCES users.u_person("id");
ALTER TABLE users.u_user_role ADD CONSTRAINT "u_user_role_users.u_user_fk1" FOREIGN KEY("u_user_id") REFERENCES users.u_user("id");
ALTER TABLE users.u_user_role ADD CONSTRAINT "u_user_role_users.u_role_fk2" FOREIGN KEY("u_role_id") REFERENCES users.u_role("id");
ALTER TABLE projects.pr_assignment ADD CONSTRAINT "pr_assignment_projects.pr_project_fk1" FOREIGN KEY("pr_project_id") REFERENCES projects.pr_project("id");
ALTER TABLE projects.pr_assignment ADD CONSTRAINT "pr_assignment_projects.creator_user_u_id_fk2" FOREIGN KEY("creator_u_user_id") REFERENCES users.u_user("id");
ALTER TABLE projects.pr_report ADD CONSTRAINT "pr_report_projects.pr_assignment_id" FOREIGN KEY("pr_assignment_id") REFERENCES projects.pr_assignment("id");
ALTER TABLE projects.pr_assigned_users ADD CONSTRAINT "pr_assigned_users_projects.pr_assigned_user_ud_id_fk1" FOREIGN KEY("assigned_user_u_id") REFERENCES users.u_user("id");
ALTER TABLE projects.pr_assigned_users ADD CONSTRAINT "pr_assigned_users_projects.pr_assignment_id_fk2" FOREIGN KEY("pr_assignment_id") REFERENCES projects.pr_assignment("id");

