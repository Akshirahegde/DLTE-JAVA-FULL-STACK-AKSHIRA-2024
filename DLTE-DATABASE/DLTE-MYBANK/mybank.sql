CREATE TABLE mybank_app_customer (
    customer_id      NUMBER,
    customer_name    VARCHAR(255),
    customer_address VARCHAR(255),
    customer_status  VARCHAR(255),
    customer_contact NUMBER,
    username         VARCHAR(255),
    password         VARCHAR(255)
);

CREATE SEQUENCE my_bank_app_seq START WITH 2024001 INCREMENT BY 1;

ALTER TABLE mybank_app_customer ADD CONSTRAINT my_bank_app_seq PRIMARY KEY ( customer_id );

ALTER TABLE mybank_app_customer ADD UNIQUE ( username );

CREATE TABLE mybank_app_kyc (
    kyc_number  NUMBER,
    kyc_pan     NUMBER,
    kyc_aadhaar NUMBER,
    kyc_status  VARCHAR(255),
    customer_id NUMBER,
    UNIQUE ( kyc_pan ),
    UNIQUE ( kyc_aadhaar ),
    FOREIGN KEY ( customer_id )
        REFERENCES mybank_app_customer ( customer_id )
);

CREATE SEQUENCE kyc_seq START WITH 2025001 INCREMENT BY 1;

ALTER TABLE mybank_app_kyc ADD CONSTRAINT kyc_seq PRIMARY KEY ( kyc_number );

CREATE TABLE mybank_app_account (
    account_id     NUMBER,
    account_type   VARCHAR(255),
    account_number NUMBER,
    account_status VARCHAR(255),
    customer_id    NUMBER,
    UNIQUE ( account_number ),
    FOREIGN KEY ( customer_id )
        REFERENCES mybank_app_customer ( customer_id )
);

CREATE SEQUENCE account_seq START WITH 100 INCREMENT BY 1;

ALTER TABLE mybank_app_account ADD CONSTRAINT account_seq PRIMARY KEY ( account_id );

CREATE TABLE mybank_app_loanavailable (
    loan_id          NUMBER,
    loan_type        VARCHAR(255),
    loan_name        VARCHAR(255),
    loan_description VARCHAR(255),
    loan_roi         NUMBER
);

CREATE SEQUENCE loanavailable_seq START WITH 2026001 INCREMENT BY 1;

ALTER TABLE mybank_app_loanavailable ADD CONSTRAINT loanavailable_seq PRIMARY KEY ( loan_id );

CREATE TABLE mybank_app_insuranceavailable (
    insurance_id           NUMBER,
    insurance_type         VARCHAR(255),
    insurance_name         VARCHAR(255),
    insurance_key_benefits VARCHAR(255),
    insurance_lifetime     NUMBER
);

CREATE SEQUENCE insuranceavailable_seq START WITH 2027001 INCREMENT BY 1;

ALTER TABLE mybank_app_insuranceavailable ADD CONSTRAINT insuranceavailable_seq PRIMARY KEY ( insurance_id );

CREATE TABLE mybank_app_depositavailable (
    deposit_id          NUMBER,
    deposit_name        VARCHAR(255),
    deposit_roi         NUMBER,
    deposit_type        VARCHAR(255),
    deposit_description VARCHAR(255)
);

CREATE SEQUENCE depositavailable_seq START WITH 2028001 INCREMENT BY 1;

ALTER TABLE mybank_app_depositavailable ADD CONSTRAINT depositavailable_seq PRIMARY KEY ( deposit_id );

CREATE TABLE mybank_app_depositavailed (
    deposit_avail_id NUMBER,
    deposit_name     VARCHAR(255),
    deposit_roi      NUMBER,
    deposit_amount   VARCHAR(255),
    deposit_duration NUMBER,
    deposit_maturity NUMBER,
    deposit_id       NUMBER,
    customer_id      NUMBER,
    FOREIGN KEY ( customer_id )
        REFERENCES mybank_app_customer ( customer_id ),
    FOREIGN KEY ( deposit_id )
        REFERENCES mybank_app_depositavailable ( deposit_id )
            ON DELETE CASCADE
);

CREATE SEQUENCE depositavailed_seq START WITH 2029001 INCREMENT BY 1;

ALTER TABLE mybank_app_depositavailed ADD CONSTRAINT depositavailed_seq PRIMARY KEY ( deposit_avail_id );

CREATE TABLE mybank_app_insuranceavailed (
    insurance_avail_id     NUMBER,
    insurance_type         VARCHAR(255),
    insurance_name         VARCHAR(255),
    insurance_key_benefits VARCHAR(255),
    insurance_coverage     NUMBER,
    insurance_lifetime     NUMBER,
    insurance_premium      NUMBER,
    customer_id            NUMBER,
    insurance_id           NUMBER,
    FOREIGN KEY ( customer_id )
        REFERENCES mybank_app_customer ( customer_id ),
    FOREIGN KEY ( insurance_id )
        REFERENCES mybank_app_insuranceavailable ( insurance_id )
            ON DELETE CASCADE
);

CREATE SEQUENCE insuranceavailed_seq START WITH 2023001 INCREMENT BY 1;

ALTER TABLE mybank_app_insuranceavailed ADD CONSTRAINT insuranceavailed_seq PRIMARY KEY ( insurance_avail_id );

CREATE TABLE mybank_app_loanavailed (
    loan_avail_id NUMBER,
    loan_amount   NUMBER,
    loan_emi      NUMBER,
    loan_tenure   NUMBER,
    customer_id   NUMBER,
    loan_id       NUMBER,
    FOREIGN KEY ( customer_id )
        REFERENCES mybank_app_customer ( customer_id ),
    FOREIGN KEY ( loan_id )
        REFERENCES mybank_app_loanavailable ( loan_id )
            ON DELETE CASCADE
);

CREATE SEQUENCE loanavailed_seq START WITH 2022001 INCREMENT BY 1;

ALTER TABLE mybank_app_loanavailed ADD CONSTRAINT loanavailed_seq PRIMARY KEY ( loan_avail_id );




CREATE TABLE mybank_app_payee (
    payee_id    NUMBER,
    payee_name  VARCHAR(255),
    account_id  NUMBER,
    customer_id NUMBER,
    FOREIGN KEY ( account_id )
        REFERENCES mybank_app_account ( account_id ),
    FOREIGN KEY ( customer_id )
        REFERENCES mybank_app_customer ( customer_id )
);

CREATE SEQUENCE payee_seq START WITH 2019001 INCREMENT BY 1;

ALTER TABLE mybank_app_payee ADD CONSTRAINT payee_seq PRIMARY KEY ( payee_id );

DROP TABLE mybank_app_transaction;

CREATE TABLE mybank_app_transaction (
    transaction_id     NUMBER,
    transaction_type   VARCHAR(255),
    transaction_from   NUMBER,
    transaction_to     NUMBER,
    transaction_date   DATE,
    transaction_amount NUMBER,
    transaction_status VARCHAR(255),
    FOREIGN KEY ( transaction_to )
        REFERENCES mybank_app_account ( account_number ),
    FOREIGN KEY ( transaction_from )
        REFERENCES mybank_app_account ( account_number )
            ON DELETE CASCADE
);

CREATE SEQUENCE transaction_app_seq START WITH 2020001 INCREMENT BY 1;

ALTER TABLE mybank_app_transaction ADD CONSTRAINT transaction_app_seq PRIMARY KEY ( transaction_id );

drop table mybank_app_payee;

CREATE TABLE mybank_app_payee (
    payee_id    NUMBER,
    payee_name  VARCHAR(255),
    account_number  NUMBER,
    customer_id NUMBER,
    FOREIGN KEY ( account_number )
        REFERENCES mybank_app_account ( account_number ),
    FOREIGN KEY ( customer_id )
        REFERENCES mybank_app_customer ( customer_id )
        ON DELETE CASCADE
);

CREATE SEQUENCE payee_seq START WITH 2019001 INCREMENT BY 1;

ALTER TABLE mybank_app_payee ADD CONSTRAINT payee_seq PRIMARY KEY ( payee_id );

drop table mybank_app_debitcard;

CREATE TABLE mybank_app_debitcard (
    debitcard_number              NUMBER,
    debitcard_cvv                 NUMBER,
    debitcard_pin                 NUMBER,
    debitcard_expiry              DATE,
    debitcard_status              VARCHAR(255),
    debitcard_domestic_limit      NUMBER,
    debitcard_international_limit NUMBER,
    customer_id                   NUMBER,
    account_number                    NUMBER,
    UNIQUE ( debitcard_cvv ),
    FOREIGN KEY ( customer_id )
        REFERENCES mybank_app_customer ON DELETE CASCADE,
    FOREIGN KEY ( account_number )
        REFERENCES mybank_app_account
            ON DELETE CASCADE
);

CREATE SEQUENCE debitcard_seq START WITH 2021001 INCREMENT BY 1;

ALTER TABLE mybank_app_debitcard ADD CONSTRAINT debitcard_seq PRIMARY KEY ( debitcard_number );


