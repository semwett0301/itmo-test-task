CREATE TABLE companies
(
    id   UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL
);

CREATE TABLE departments
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name       VARCHAR(255) NOT NULL,
    company_id UUID,
    CONSTRAINT fk_company
        FOREIGN KEY (company_id)
            REFERENCES companies (id)
            ON DELETE CASCADE
);

CREATE TABLE employees
(
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name          VARCHAR(255) NOT NULL,
    department_id UUID,
    CONSTRAINT fk_department
        FOREIGN KEY (department_id)
            REFERENCES departments (id)
            ON DELETE CASCADE
);

CREATE TABLE servers
(
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name        VARCHAR(255) NOT NULL,
    producer    VARCHAR(255) NOT NULL,
    ip          VARCHAR(255) NOT NULL,
    ram         INT          NOT NULL CHECK (ram >= 1),
    ssd         INT          NOT NULL CHECK (ssd >= 1),
    employee_id UUID,
    CONSTRAINT fk_employee
        FOREIGN KEY (employee_id)
            REFERENCES employees (id)
            ON DELETE CASCADE
);

INSERT INTO companies (name)
VALUES ('Company A'),
       ('Company B'),
       ('Company C');

INSERT INTO departments (name, company_id)
SELECT 'Department ' || chr(65 + n), id
FROM companies,
     generate_series(0, 2) n;

INSERT INTO employees (name, department_id)
SELECT 'Employee ' || chr(65 + n), id
FROM departments,
     generate_series(0, 4) n;

INSERT INTO servers (name, producer, ip, ram, ssd, employee_id)
SELECT 'Server ' || chr(65 + n),
       'Producer ' || chr(65 + n),
       '192.168.0.' || (10 + n),
       (8 + n * 2),
       (256 + n * 128),
       id
FROM employees,
     generate_series(0, 2) n;