CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE SCHEMA challenge
    AUTHORIZATION postgres;

CREATE TABLE challenge.customer
(
    id uuid NOT NULL DEFAULT uuid_generate_v4(),
    name character varying(255) NOT NULL,
    email character varying(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT "EMAIL_UK01" UNIQUE (email)
)
WITH (
    OIDS = FALSE
);

ALTER TABLE challenge.customer
    OWNER to postgres;

CREATE TABLE challenge.wishlist_product (
	id uuid NOT NULL DEFAULT uuid_generate_v4(),
    product_id character varying(255) NOT NULL,
    customer_id uuid NOT NULL,
	PRIMARY KEY (id),
    CONSTRAINT PRODUCT_CLIENT_UK01 UNIQUE (customer_id, product_id)
);
ALTER TABLE challenge.wishlist_product ADD FOREIGN KEY (customer_id) REFERENCES challenge.customer(id);
ALTER TABLE challenge.customer
    OWNER to postgres;