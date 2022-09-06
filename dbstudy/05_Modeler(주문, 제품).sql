-- 생성자 Oracle SQL Developer Data Modeler 20.4.0.374.0801
--   위치:        2022-08-24 16:34:42 KST
--   사이트:      Oracle Database 11g
--   유형:      Oracle Database 11g



-- predefined type, no DDL - MDSYS.SDO_GEOMETRY

-- predefined type, no DDL - XMLTYPE

CREATE TABLE orders (
    ord_no    NUMBER(4) NOT NULL,
    ord_user  VARCHAR2(20 BYTE) NOT NULL,
    prod_no   NUMBER(3),
    ord_date  DATE
);

ALTER TABLE orders ADD CONSTRAINT pk_orders PRIMARY KEY ( ord_no );

CREATE TABLE product (
    prod_no    NUMBER(3) NOT NULL,
    prod_name  VARCHAR2(20 BYTE) NOT NULL,
    price      NUMBER(6) NOT NULL,
    stock      NUMBER
);

ALTER TABLE product ADD CONSTRAINT product_pk PRIMARY KEY ( prod_no );

ALTER TABLE orders
    ADD CONSTRAINT fk_orders_product FOREIGN KEY ( prod_no )
        REFERENCES product ( prod_no );

CREATE OR REPLACE VIEW V_ORDERS ( ORD_NO
   , ORD_USER
   , PROD_NO
   , ORD_DATE )
 AS SELECT
    ORD_NO
   , ORD_USER
   , PROD_NO
   , ORD_DATE
 FROM 
    ORDERS 
;

CREATE OR REPLACE VIEW V_PRODUCT ( PROD_NO
   , PROD_NAME
   , PRICE
   , STOCK )
 AS SELECT
    PROD_NO
   , PROD_NAME
   , PRICE
   , STOCK
 FROM 
    PRODUCT 
;



-- Oracle SQL Developer Data Modeler 요약 보고서: 
-- 
-- CREATE TABLE                             2
-- CREATE INDEX                             0
-- ALTER TABLE                              3
-- CREATE VIEW                              2
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
