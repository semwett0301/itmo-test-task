CREATE OR REPLACE FUNCTION ensure_company_has_departments() RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT COUNT(*) FROM departments WHERE company_id = OLD.id) <= 1 THEN
        RAISE EXCEPTION 'Cannot delete company with id %, because it must have at least one department', OLD.id;
    END IF;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER before_delete_company
    BEFORE DELETE ON companies
    FOR EACH ROW EXECUTE FUNCTION ensure_company_has_departments();

CREATE OR REPLACE FUNCTION ensure_department_has_employees() RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT COUNT(*) FROM employees WHERE department_id = OLD.id) <= 1 THEN
        RAISE EXCEPTION 'Cannot delete department with id %, because it must have at least one employee', OLD.id;
    END IF;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER before_delete_department
    BEFORE DELETE ON departments
    FOR EACH ROW EXECUTE FUNCTION ensure_department_has_employees();
