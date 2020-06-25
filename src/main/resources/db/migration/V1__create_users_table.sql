-- object: public."user" | type: TABLE --
-- DROP TABLE IF EXISTS public."user" CASCADE;
CREATE TABLE public."user"
(
    user_id    uuid                     NOT NULL,
    username   character varying(100)   NOT NULL,
    email      character varying(255)   NOT NULL,
    created_at timestamp with time zone NOT NULL DEFAULT now(),
    updated_at timestamp with time zone,
    CONSTRAINT pk_user PRIMARY KEY (user_id),
    CONSTRAINT unique_username UNIQUE (username),
    CONSTRAINT unique_email UNIQUE (email)

);
-- ddl-end --
ALTER TABLE public."user"
    OWNER TO postgres;
-- ddl-end --

