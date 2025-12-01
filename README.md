<h2>Http requests:</h2>
<b>End point 3: return all employee records by department with pagination</b>
<p>http://localhost:8092/practicum2/api/employees/department?deptNo=d001&page=1
<b>End point 2: return full employee record given employee number</b>
<p>http://localhost:8092/practicum2/api/employees/id?empNo=110022</p>
<b>End point 1: getting all the names and department numbers</b>
<p>http://localhost:8092/practicum2/api/departments/list</p>
<b>Test connection:</b>
<p>http://localhost:8092/practicum2/api/departments/ping</p>
<p>http://localhost:8092/practicum2/api/employees/ping</p>

<h3>lastest updates:</h3>
<p> </p>
<p>15. completed end point 3</p>
<p>14. add EmployeeDto, added a query in employeeResource and created a new path</p>
<p>13. completed end point 2</p>
<p>12. add deptno and dept name to display with employee json</p>
<p>11. completed end point 1</p>
<p>10. show deptEmp and dept manager that the employee is in</p>
<p>9. created a composite key for title to be able to queried along with employee table</p>
<p>8. created a composite key for salary to be able to queried along with employee table</p>
<p>7. created a util class for entity manager creation and stop</p>
<p>6. Added employee query services, able to query employees table</p>
<p>5. Added entities with relationships mapped</p>
<p>4. Edited dependencies in pom file and test with demo_1, successful</p>
<p>3. Added more dependencies in pom file, annotated the use for each dependency and remove jersey xml dependency. Added maven plugins and java version property.</p>
<p>2. Added dependencies for RestFul Web Service (note: jersey-media v4.0.0).</p>
<p>1. Initial commit.</p>
