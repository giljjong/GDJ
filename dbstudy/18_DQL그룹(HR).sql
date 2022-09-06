-- 그룹(GROUP BY)
-- 1. GROUP BY 절에서 지정한 칼럼의 데이터는 하나로 모아서 한 번만 조회가 된다
-- 2. SELECT 절에서 조회 할 칼럼은 "반드시" GROUP BY 절에 존재해야 한다.

-- EMPLOYEE 테이블

-- 1. 동일한 부서번호(DEPARTMENT_ID)로 그룹화하여 조회
SELECT DEPARTMENT_ID
  FROM EMPLOYEES
 GROUP BY DEPARTMENT_ID;
 
-- 2. 그룹화 실패
-- EMPLOYEE_ID를 조회하려면 GROUP BY 절에 EMPLOYEE_ID가 있어야 한다
SELECT EMPLOYEE_ID
  FROM EMPLOYEES
 GROUP BY DEPARTMENT_ID;
 
 -- 3. 그룹화 함수 사용
 -- 집계함수(그룹함수)는 GROUP BY 절에 해당 칼럼이 없어도 SELECT 절에서 조회할 수 있다.
 