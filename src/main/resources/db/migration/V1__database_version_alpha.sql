-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.1
-- PostgreSQL version: 10.0
-- Project Site: pgmodeler.io
-- Model Author: ---


-- Database creation must be done outside a multicommand file.
-- These commands were put in this file only as a convenience.
-- -- object: new_database | type: DATABASE --
-- -- DROP DATABASE IF EXISTS new_database;
-- CREATE DATABASE new_database;
-- -- ddl-end --
--

-- object: public.base_model | type: TABLE --
-- DROP TABLE IF EXISTS public.base_model CASCADE;
CREATE TABLE public.base_model
(
    created_at     timestamp with time zone NOT NULL DEFAULT now(),
    updated_at     timestamp with time zone,
    softdeleted    bool                     NOT NULL DEFAULT false,
    softdeleted_at timestamp with time zone
);
-- ddl-end --
ALTER TABLE public.base_model
    OWNER TO postgres;
-- ddl-end --

-- object: public.base_model_with_ownership | type: TABLE --
-- DROP TABLE IF EXISTS public.base_model_with_ownership CASCADE;
CREATE TABLE public.base_model_with_ownership
(
    created_by uuid NOT NULL,
    owned_by   uuid NOT NULL-- 	created_at timestamp with time zone NOT NULL DEFAULT now(),
-- 	updated_at timestamp with time zone,
-- 	softdeleted bool NOT NULL DEFAULT false,
-- 	softdeleted_at timestamp with time zone,

) INHERITS (public.base_model)
;
-- ddl-end --
ALTER TABLE public.base_model_with_ownership
    OWNER TO postgres;
-- ddl-end --

-- object: public.application | type: TABLE --
-- DROP TABLE IF EXISTS public.application CASCADE;
CREATE TABLE public.application
(
    application_id      uuid                   NOT NULL,
    name                character varying(255) NOT NULL,
    project_id          uuid                   NOT NULL,
    is_publicly_visible bool                   NOT NULL DEFAULT false,
-- 	created_by uuid NOT NULL,
-- 	owned_by uuid NOT NULL,
-- 	created_at timestamp with time zone NOT NULL DEFAULT now(),
-- 	updated_at timestamp with time zone,
-- 	softdeleted bool NOT NULL DEFAULT false,
-- 	softdeleted_at timestamp with time zone,
    CONSTRAINT application_id_pk PRIMARY KEY (application_id)

) INHERITS (public.base_model_with_ownership)
;
-- ddl-end --
ALTER TABLE public.application
    OWNER TO postgres;
-- ddl-end --

-- object: public."projectVisibility" | type: TYPE --
-- DROP TYPE IF EXISTS public."projectVisibility" CASCADE;
CREATE TYPE public."projectVisibility" AS
    ENUM ('OWNER','MANAGER','DEVELOPER','STAKEHOLDER');
-- ddl-end --
ALTER TYPE public."projectVisibility" OWNER TO postgres;
-- ddl-end --

-- object: public.project_visibility | type: TABLE --
-- DROP TABLE IF EXISTS public.project_visibility CASCADE;
CREATE TABLE public.project_visibility
(
    project_visibility_id uuid                   NOT NULL,
    project_id            uuid                   NOT NULL,
    user_id               uuid                   NOT NULL,
    visibility            character varying(255) NOT NULL DEFAULT 'STAKEHOLDER',
-- 	created_at timestamp with time zone NOT NULL DEFAULT now(),
-- 	updated_at timestamp with time zone,
-- 	softdeleted bool NOT NULL DEFAULT false,
-- 	softdeleted_at timestamp with time zone,
    CONSTRAINT pk_project_visibility PRIMARY KEY (project_visibility_id)

) INHERITS (public.base_model)
;
-- ddl-end --
ALTER TABLE public.project_visibility
    OWNER TO postgres;
-- ddl-end --

-- object: public."applicationVisibility" | type: TYPE --
-- DROP TYPE IF EXISTS public."applicationVisibility" CASCADE;
CREATE TYPE public."applicationVisibility" AS
    ENUM ('STAKEHOLDER','OWNER','MANAGER','DEVELOPER');
-- ddl-end --
ALTER TYPE public."applicationVisibility" OWNER TO postgres;
-- ddl-end --

-- object: public.application_visibility | type: TABLE --
-- DROP TABLE IF EXISTS public.application_visibility CASCADE;
CREATE TABLE public.application_visibility
(
    application_visibility_id uuid                   NOT NULL,
    application_id            uuid                   NOT NULL,
    user_id                   uuid                   NOT NULL,
    visibility                character varying(255) NOT NULL DEFAULT 'STAKEHOLDER',
-- 	created_at timestamp with time zone NOT NULL DEFAULT now(),
-- 	updated_at timestamp with time zone,
-- 	softdeleted bool NOT NULL DEFAULT false,
-- 	softdeleted_at timestamp with time zone,
    CONSTRAINT pk_application_visibility PRIMARY KEY (application_visibility_id)

) INHERITS (public.base_model)
;
-- ddl-end --
ALTER TABLE public.application_visibility
    OWNER TO postgres;
-- ddl-end --

-- object: public.project_event | type: TABLE --
-- DROP TABLE IF EXISTS public.project_event CASCADE;
CREATE TABLE public.project_event
(
    project_event_id uuid NOT NULL,
    event            text NOT NULL,
    project_id       uuid NOT NULL,
    user_id          uuid,
-- 	created_at timestamp with time zone NOT NULL DEFAULT now(),
-- 	updated_at timestamp with time zone,
-- 	softdeleted bool NOT NULL DEFAULT false,
-- 	softdeleted_at timestamp with time zone,
    CONSTRAINT pk_project_event_id PRIMARY KEY (project_event_id)

) INHERITS (public.base_model)
;
-- ddl-end --
ALTER TABLE public.project_event
    OWNER TO postgres;
-- ddl-end --

-- object: public.application_event | type: TABLE --
-- DROP TABLE IF EXISTS public.application_event CASCADE;
CREATE TABLE public.application_event
(
    application_event_id uuid NOT NULL,
    event                text NOT NULL,
    application_id       uuid NOT NULL,
    user_id              uuid,
-- 	created_at timestamp with time zone NOT NULL DEFAULT now(),
-- 	updated_at timestamp with time zone,
-- 	softdeleted bool NOT NULL DEFAULT false,
-- 	softdeleted_at timestamp with time zone,
    CONSTRAINT pk_application_event PRIMARY KEY (application_event_id)

) INHERITS (public.base_model)
;
-- ddl-end --
ALTER TABLE public.application_event
    OWNER TO postgres;
-- ddl-end --

-- object: public.project | type: TABLE --
-- DROP TABLE IF EXISTS public.project CASCADE;
CREATE TABLE public.project
(
    project_id          uuid                   NOT NULL,
    name                character varying(255) NOT NULL,
    active              bool                            DEFAULT true,
    is_publicly_visible bool                   NOT NULL DEFAULT false,
-- 	created_by uuid NOT NULL,
-- 	owned_by uuid NOT NULL,
-- 	created_at timestamp with time zone NOT NULL DEFAULT now(),
-- 	updated_at timestamp with time zone,
-- 	softdeleted bool NOT NULL DEFAULT false,
-- 	softdeleted_at timestamp with time zone,
    CONSTRAINT project_id_pk PRIMARY KEY (project_id)

) INHERITS (public.base_model_with_ownership)
;
-- ddl-end --
ALTER TABLE public.project
    OWNER TO postgres;
-- ddl-end --

-- object: public."user" | type: TABLE --
-- DROP TABLE IF EXISTS public."user" CASCADE;
CREATE TABLE public."user"
(
    user_id   uuid                   NOT NULL,
    username  character varying(100) NOT NULL,
    email     character varying(255) NOT NULL,
    password  character varying(500) NOT NULL,
    suspended bool                   NOT NULL DEFAULT false,
-- 	created_at timestamp with time zone NOT NULL DEFAULT now(),
-- 	updated_at timestamp with time zone,
-- 	softdeleted bool NOT NULL DEFAULT false,
-- 	softdeleted_at timestamp with time zone,
    CONSTRAINT pk_user PRIMARY KEY (user_id),
    CONSTRAINT unique_username UNIQUE (username),
    CONSTRAINT unique_email UNIQUE (email)

) INHERITS (public.base_model)
;
-- ddl-end --
ALTER TABLE public."user"
    OWNER TO postgres;
-- ddl-end --

-- object: public.http_api | type: TABLE --
-- DROP TABLE IF EXISTS public.http_api CASCADE;
CREATE TABLE public.http_api
(
    http_api_id         uuid                   NOT NULL,
    name                character varying(255) NOT NULL,
    application_id      uuid                   NOT NULL,
    is_publicly_visible bool                   NOT NULL DEFAULT false,
-- 	created_by uuid NOT NULL,
-- 	owned_by uuid NOT NULL,
-- 	created_at timestamp with time zone NOT NULL DEFAULT now(),
-- 	updated_at timestamp with time zone,
-- 	softdeleted bool NOT NULL DEFAULT false,
-- 	softdeleted_at timestamp with time zone,
    CONSTRAINT pk_http_api PRIMARY KEY (http_api_id)

) INHERITS (public.base_model_with_ownership)
;
-- ddl-end --
ALTER TABLE public.http_api
    OWNER TO postgres;
-- ddl-end --

-- object: public."httpRequestType" | type: TYPE --
-- DROP TYPE IF EXISTS public."httpRequestType" CASCADE;
CREATE TYPE public."httpRequestType" AS
    ENUM ('POST','GET','PUT','DELETE','PATCH','HEAD','OPTIONS','TRACE');
-- ddl-end --
ALTER TYPE public."httpRequestType" OWNER TO postgres;
-- ddl-end --

-- object: public.http_request | type: TABLE --
-- DROP TABLE IF EXISTS public.http_request CASCADE;
CREATE TABLE public.http_request
(
    http_request_id uuid                     NOT NULL,
    operation_id    character varying(255)   NOT NULL,
    path            character varying(500)   NOT NULL,
    method          public."httpRequestType" NOT NULL DEFAULT 'GET',
    description     text,
    parameters      json                     NOT NULL,
    request_bodies  json                     NOT NULL,
    responses       json                     NOT NULL,
    http_api_id     uuid                     NOT NULL,
    deprecated      bool                     NOT NULL DEFAULT false,
    deprecated_for  uuid,
    draft           bool                     NOT NULL DEFAULT false,
-- 	created_by uuid NOT NULL,
-- 	owned_by uuid NOT NULL,
-- 	created_at timestamp with time zone NOT NULL DEFAULT now(),
-- 	updated_at timestamp with time zone,
-- 	softdeleted bool NOT NULL DEFAULT false,
-- 	softdeleted_at timestamp with time zone,
    CONSTRAINT pk_http_request PRIMARY KEY (http_request_id),
    CONSTRAINT unique_operation_id UNIQUE (operation_id)

) INHERITS (public.base_model_with_ownership)
;
-- ddl-end --
ALTER TABLE public.http_request
    OWNER TO postgres;
-- ddl-end --

-- object: public.http_api_event | type: TABLE --
-- DROP TABLE IF EXISTS public.http_api_event CASCADE;
CREATE TABLE public.http_api_event
(
    http_api_event_id uuid NOT NULL,
    event             text NOT NULL,
    http_api_id       uuid NOT NULL,
    user_id           uuid,
-- 	created_at timestamp with time zone NOT NULL DEFAULT now(),
-- 	updated_at timestamp with time zone,
-- 	softdeleted bool NOT NULL DEFAULT false,
-- 	softdeleted_at timestamp with time zone,
    CONSTRAINT pk_http_api_event PRIMARY KEY (http_api_event_id)

) INHERITS (public.base_model)
;
-- ddl-end --
ALTER TABLE public.http_api_event
    OWNER TO postgres;
-- ddl-end --

-- object: public.http_api_enviroment | type: TABLE --
-- DROP TABLE IF EXISTS public.http_api_enviroment CASCADE;
CREATE TABLE public.http_api_enviroment
(
    http_enviroment_id uuid NOT NULL,
    enviroment         json NOT NULL,
    template           bool NOT NULL DEFAULT false,
    http_api_id        uuid NOT NULL,
    user_id            uuid,
-- 	created_at timestamp with time zone NOT NULL DEFAULT now(),
-- 	updated_at timestamp with time zone,
-- 	softdeleted bool NOT NULL DEFAULT false,
-- 	softdeleted_at timestamp with time zone,
    CONSTRAINT pk_http_api_enviroment PRIMARY KEY (http_enviroment_id)

) INHERITS (public.base_model)
;
-- ddl-end --
ALTER TABLE public.http_api_enviroment
    OWNER TO postgres;
-- ddl-end --

-- object: public.object_dictionary | type: TABLE --
-- DROP TABLE IF EXISTS public.object_dictionary CASCADE;
CREATE TABLE public.object_dictionary
(
    object_dictionary_id uuid                   NOT NULL,
    name                 character varying(255) NOT NULL,
    application_id       uuid                   NOT NULL,
    is_publicly_visible  bool                   NOT NULL DEFAULT false,
-- 	created_by uuid NOT NULL,
-- 	owned_by uuid NOT NULL,
-- 	created_at timestamp with time zone NOT NULL DEFAULT now(),
-- 	updated_at timestamp with time zone,
-- 	softdeleted bool NOT NULL DEFAULT false,
-- 	softdeleted_at timestamp with time zone,
    CONSTRAINT pk_object_dictionary PRIMARY KEY (object_dictionary_id)

) INHERITS (public.base_model_with_ownership)
;
-- ddl-end --
ALTER TABLE public.object_dictionary
    OWNER TO postgres;
-- ddl-end --

-- object: public.object_dictionary_event | type: TABLE --
-- DROP TABLE IF EXISTS public.object_dictionary_event CASCADE;
CREATE TABLE public.object_dictionary_event
(
    object_dictionary_event_id uuid NOT NULL,
    event                      text NOT NULL,
    object_dictionary_id       uuid NOT NULL,
    user_id                    uuid,
-- 	created_at timestamp with time zone NOT NULL DEFAULT now(),
-- 	updated_at timestamp with time zone,
-- 	softdeleted bool NOT NULL DEFAULT false,
-- 	softdeleted_at timestamp with time zone,
    CONSTRAINT pk_object_dictionary_event PRIMARY KEY (object_dictionary_event_id)

) INHERITS (public.base_model)
;
-- ddl-end --
ALTER TABLE public.object_dictionary_event
    OWNER TO postgres;
-- ddl-end --

-- object: public.http_request_event | type: TABLE --
-- DROP TABLE IF EXISTS public.http_request_event CASCADE;
CREATE TABLE public.http_request_event
(
    http_request_event_id uuid NOT NULL,
    event                 text NOT NULL,
    http_request_id       uuid NOT NULL,
    user_id               uuid,
-- 	created_at timestamp with time zone NOT NULL DEFAULT now(),
-- 	updated_at timestamp with time zone,
-- 	softdeleted bool NOT NULL DEFAULT false,
-- 	softdeleted_at timestamp with time zone,
    CONSTRAINT pk_http_request_event PRIMARY KEY (http_request_event_id)

) INHERITS (public.base_model)
;
-- ddl-end --
ALTER TABLE public.http_request_event
    OWNER TO postgres;
-- ddl-end --

-- object: public.object_dicionary_entry | type: TABLE --
-- DROP TABLE IF EXISTS public.object_dicionary_entry CASCADE;
CREATE TABLE public.object_dicionary_entry
(
    object_dicionary_entry_id uuid                   NOT NULL,
    name                      character varying(255) NOT NULL,
    description               text,
    object_dictionary_id      uuid                   NOT NULL,
    structure                 json                   NOT NULL,
-- 	created_by uuid NOT NULL,
-- 	owned_by uuid NOT NULL,
-- 	created_at timestamp with time zone NOT NULL DEFAULT now(),
-- 	updated_at timestamp with time zone,
-- 	softdeleted bool NOT NULL DEFAULT false,
-- 	softdeleted_at timestamp with time zone,
    CONSTRAINT pk_object_dicionary_entry PRIMARY KEY (object_dicionary_entry_id)

) INHERITS (public.base_model_with_ownership)
;
-- ddl-end --
ALTER TABLE public.object_dicionary_entry
    OWNER TO postgres;
-- ddl-end --

-- object: public.object_dicionary_entry_event | type: TABLE --
-- DROP TABLE IF EXISTS public.object_dicionary_entry_event CASCADE;
CREATE TABLE public.object_dicionary_entry_event
(
    object_dicionary_entry_event_id uuid NOT NULL,
    event                           text NOT NULL,
    object_dicionary_entry_id       uuid NOT NULL,
    user_id                         uuid,
-- 	created_at timestamp with time zone NOT NULL DEFAULT now(),
-- 	updated_at timestamp with time zone,
-- 	softdeleted bool NOT NULL DEFAULT false,
-- 	softdeleted_at timestamp with time zone,
    CONSTRAINT pk_object_dicionary_entry_event PRIMARY KEY (object_dicionary_entry_event_id)

) INHERITS (public.base_model)
;
-- ddl-end --
ALTER TABLE public.object_dicionary_entry_event
    OWNER TO postgres;
-- ddl-end --

-- object: public.notification | type: TABLE --
-- DROP TABLE IF EXISTS public.notification CASCADE;
CREATE TABLE public.notification
(
    notification_id uuid NOT NULL,
    "to"            uuid NOT NULL,
    "from"          uuid,
    payload         json NOT NULL,
    solved          bool NOT NULL DEFAULT false,
-- 	created_at timestamp with time zone NOT NULL DEFAULT now(),
-- 	updated_at timestamp with time zone,
-- 	softdeleted bool NOT NULL DEFAULT false,
-- 	softdeleted_at timestamp with time zone,
    CONSTRAINT pk_notification PRIMARY KEY (notification_id)

) INHERITS (public.base_model)
;
-- ddl-end --
ALTER TABLE public.notification
    OWNER TO postgres;
-- ddl-end --

-- object: public.object_dictionary_visibility | type: TABLE --
-- DROP TABLE IF EXISTS public.object_dictionary_visibility CASCADE;
CREATE TABLE public.object_dictionary_visibility
(
    object_dictionary_visibility_id uuid                   NOT NULL,
    object_dictionary_id            uuid                   NOT NULL,
    user_id                         uuid                   NOT NULL,
    visibility                      character varying(255) NOT NULL DEFAULT 'STAKEHOLDER',
-- 	created_at timestamp with time zone NOT NULL DEFAULT now(),
-- 	updated_at timestamp with time zone,
-- 	softdeleted bool NOT NULL DEFAULT false,
-- 	softdeleted_at timestamp with time zone,
    CONSTRAINT pk_object_dictionary_visibility PRIMARY KEY (object_dictionary_visibility_id)

) INHERITS (public.base_model)
;
-- ddl-end --
ALTER TABLE public.object_dictionary_visibility
    OWNER TO postgres;
-- ddl-end --

-- object: public.http_api_visibility | type: TABLE --
-- DROP TABLE IF EXISTS public.http_api_visibility CASCADE;
CREATE TABLE public.http_api_visibility
(
    http_api_visibility_id uuid                   NOT NULL,
    http_api_id            uuid                   NOT NULL,
    user_id                uuid                   NOT NULL,
    visibility             character varying(255) NOT NULL DEFAULT 'STAKEHOLDER',
-- 	created_at timestamp with time zone NOT NULL DEFAULT now(),
-- 	updated_at timestamp with time zone,
-- 	softdeleted bool NOT NULL DEFAULT false,
-- 	softdeleted_at timestamp with time zone,
    CONSTRAINT pk_http_api_visibility PRIMARY KEY (http_api_visibility_id)

) INHERITS (public.base_model)
;
-- ddl-end --
ALTER TABLE public.http_api_visibility
    OWNER TO postgres;
-- ddl-end --

-- object: fk_ownership_created_by | type: CONSTRAINT --
-- ALTER TABLE public.base_model_with_ownership DROP CONSTRAINT IF EXISTS fk_ownership_created_by CASCADE;
ALTER TABLE public.base_model_with_ownership
    ADD CONSTRAINT fk_ownership_created_by FOREIGN KEY (created_by)
        REFERENCES public."user" (user_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_ownershop_owned_by | type: CONSTRAINT --
-- ALTER TABLE public.base_model_with_ownership DROP CONSTRAINT IF EXISTS fk_ownershop_owned_by CASCADE;
ALTER TABLE public.base_model_with_ownership
    ADD CONSTRAINT fk_ownershop_owned_by FOREIGN KEY (owned_by)
        REFERENCES public."user" (user_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: application_project_pk | type: CONSTRAINT --
-- ALTER TABLE public.application DROP CONSTRAINT IF EXISTS application_project_pk CASCADE;
ALTER TABLE public.application
    ADD CONSTRAINT application_project_pk FOREIGN KEY (project_id)
        REFERENCES public.project (project_id) MATCH FULL
        ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_project_visibility_user | type: CONSTRAINT --
-- ALTER TABLE public.project_visibility DROP CONSTRAINT IF EXISTS fk_project_visibility_user CASCADE;
ALTER TABLE public.project_visibility
    ADD CONSTRAINT fk_project_visibility_user FOREIGN KEY (user_id)
        REFERENCES public."user" (user_id) MATCH FULL
        ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_project_visibility_project | type: CONSTRAINT --
-- ALTER TABLE public.project_visibility DROP CONSTRAINT IF EXISTS fk_project_visibility_project CASCADE;
ALTER TABLE public.project_visibility
    ADD CONSTRAINT fk_project_visibility_project FOREIGN KEY (project_id)
        REFERENCES public.project (project_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_application_visibility_application | type: CONSTRAINT --
-- ALTER TABLE public.application_visibility DROP CONSTRAINT IF EXISTS fk_application_visibility_application CASCADE;
ALTER TABLE public.application_visibility
    ADD CONSTRAINT fk_application_visibility_application FOREIGN KEY (application_id)
        REFERENCES public.application (application_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_application_visibility_user | type: CONSTRAINT --
-- ALTER TABLE public.application_visibility DROP CONSTRAINT IF EXISTS fk_application_visibility_user CASCADE;
ALTER TABLE public.application_visibility
    ADD CONSTRAINT fk_application_visibility_user FOREIGN KEY (user_id)
        REFERENCES public."user" (user_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_project_event_project | type: CONSTRAINT --
-- ALTER TABLE public.project_event DROP CONSTRAINT IF EXISTS fk_project_event_project CASCADE;
ALTER TABLE public.project_event
    ADD CONSTRAINT fk_project_event_project FOREIGN KEY (project_id)
        REFERENCES public.project (project_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_project_event_user | type: CONSTRAINT --
-- ALTER TABLE public.project_event DROP CONSTRAINT IF EXISTS fk_project_event_user CASCADE;
ALTER TABLE public.project_event
    ADD CONSTRAINT fk_project_event_user FOREIGN KEY (user_id)
        REFERENCES public."user" (user_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_application_event_application | type: CONSTRAINT --
-- ALTER TABLE public.application_event DROP CONSTRAINT IF EXISTS fk_application_event_application CASCADE;
ALTER TABLE public.application_event
    ADD CONSTRAINT fk_application_event_application FOREIGN KEY (application_id)
        REFERENCES public.application (application_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_application_event_user | type: CONSTRAINT --
-- ALTER TABLE public.application_event DROP CONSTRAINT IF EXISTS fk_application_event_user CASCADE;
ALTER TABLE public.application_event
    ADD CONSTRAINT fk_application_event_user FOREIGN KEY (user_id)
        REFERENCES public."user" (user_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_http_api_application | type: CONSTRAINT --
-- ALTER TABLE public.http_api DROP CONSTRAINT IF EXISTS fk_http_api_application CASCADE;
ALTER TABLE public.http_api
    ADD CONSTRAINT fk_http_api_application FOREIGN KEY (application_id)
        REFERENCES public.application (application_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_http_request_http_api | type: CONSTRAINT --
-- ALTER TABLE public.http_request DROP CONSTRAINT IF EXISTS fk_http_request_http_api CASCADE;
ALTER TABLE public.http_request
    ADD CONSTRAINT fk_http_request_http_api FOREIGN KEY (http_api_id)
        REFERENCES public.http_api (http_api_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_deprecated_for | type: CONSTRAINT --
-- ALTER TABLE public.http_request DROP CONSTRAINT IF EXISTS fk_deprecated_for CASCADE;
ALTER TABLE public.http_request
    ADD CONSTRAINT fk_deprecated_for FOREIGN KEY (deprecated_for)
        REFERENCES public.http_request (http_request_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_http_api_event_http_api | type: CONSTRAINT --
-- ALTER TABLE public.http_api_event DROP CONSTRAINT IF EXISTS fk_http_api_event_http_api CASCADE;
ALTER TABLE public.http_api_event
    ADD CONSTRAINT fk_http_api_event_http_api FOREIGN KEY (http_api_id)
        REFERENCES public.http_api (http_api_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_http_api_user | type: CONSTRAINT --
-- ALTER TABLE public.http_api_event DROP CONSTRAINT IF EXISTS fk_http_api_user CASCADE;
ALTER TABLE public.http_api_event
    ADD CONSTRAINT fk_http_api_user FOREIGN KEY (user_id)
        REFERENCES public."user" (user_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_http_api_enviroment_http_api | type: CONSTRAINT --
-- ALTER TABLE public.http_api_enviroment DROP CONSTRAINT IF EXISTS fk_http_api_enviroment_http_api CASCADE;
ALTER TABLE public.http_api_enviroment
    ADD CONSTRAINT fk_http_api_enviroment_http_api FOREIGN KEY (http_api_id)
        REFERENCES public.http_api (http_api_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_http_api_enviroment_user | type: CONSTRAINT --
-- ALTER TABLE public.http_api_enviroment DROP CONSTRAINT IF EXISTS fk_http_api_enviroment_user CASCADE;
ALTER TABLE public.http_api_enviroment
    ADD CONSTRAINT fk_http_api_enviroment_user FOREIGN KEY (user_id)
        REFERENCES public."user" (user_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_object_dictionary_application | type: CONSTRAINT --
-- ALTER TABLE public.object_dictionary DROP CONSTRAINT IF EXISTS fk_object_dictionary_application CASCADE;
ALTER TABLE public.object_dictionary
    ADD CONSTRAINT fk_object_dictionary_application FOREIGN KEY (application_id)
        REFERENCES public.application (application_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_object_dictionary_event_object_dictionary | type: CONSTRAINT --
-- ALTER TABLE public.object_dictionary_event DROP CONSTRAINT IF EXISTS fk_object_dictionary_event_object_dictionary CASCADE;
ALTER TABLE public.object_dictionary_event
    ADD CONSTRAINT fk_object_dictionary_event_object_dictionary FOREIGN KEY (object_dictionary_id)
        REFERENCES public.object_dictionary (object_dictionary_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_object_dictionary_event_user | type: CONSTRAINT --
-- ALTER TABLE public.object_dictionary_event DROP CONSTRAINT IF EXISTS fk_object_dictionary_event_user CASCADE;
ALTER TABLE public.object_dictionary_event
    ADD CONSTRAINT fk_object_dictionary_event_user FOREIGN KEY (user_id)
        REFERENCES public."user" (user_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_http_request_event_http_request | type: CONSTRAINT --
-- ALTER TABLE public.http_request_event DROP CONSTRAINT IF EXISTS fk_http_request_event_http_request CASCADE;
ALTER TABLE public.http_request_event
    ADD CONSTRAINT fk_http_request_event_http_request FOREIGN KEY (http_request_id)
        REFERENCES public.http_request (http_request_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_http_request_event_user | type: CONSTRAINT --
-- ALTER TABLE public.http_request_event DROP CONSTRAINT IF EXISTS fk_http_request_event_user CASCADE;
ALTER TABLE public.http_request_event
    ADD CONSTRAINT fk_http_request_event_user FOREIGN KEY (user_id)
        REFERENCES public."user" (user_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_object_dictionary_entry_object_dictionary | type: CONSTRAINT --
-- ALTER TABLE public.object_dicionary_entry DROP CONSTRAINT IF EXISTS fk_object_dictionary_entry_object_dictionary CASCADE;
ALTER TABLE public.object_dicionary_entry
    ADD CONSTRAINT fk_object_dictionary_entry_object_dictionary FOREIGN KEY (object_dictionary_id)
        REFERENCES public.object_dictionary (object_dictionary_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_object_dicionary_entry_event_object_dicionary_entry | type: CONSTRAINT --
-- ALTER TABLE public.object_dicionary_entry_event DROP CONSTRAINT IF EXISTS fk_object_dicionary_entry_event_object_dicionary_entry CASCADE;
ALTER TABLE public.object_dicionary_entry_event
    ADD CONSTRAINT fk_object_dicionary_entry_event_object_dicionary_entry FOREIGN KEY (object_dicionary_entry_id)
        REFERENCES public.object_dicionary_entry (object_dicionary_entry_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_object_dicionary_entry_event_user | type: CONSTRAINT --
-- ALTER TABLE public.object_dicionary_entry_event DROP CONSTRAINT IF EXISTS fk_object_dicionary_entry_event_user CASCADE;
ALTER TABLE public.object_dicionary_entry_event
    ADD CONSTRAINT fk_object_dicionary_entry_event_user FOREIGN KEY (user_id)
        REFERENCES public."user" (user_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_notification_to | type: CONSTRAINT --
-- ALTER TABLE public.notification DROP CONSTRAINT IF EXISTS fk_notification_to CASCADE;
ALTER TABLE public.notification
    ADD CONSTRAINT fk_notification_to FOREIGN KEY ("to")
        REFERENCES public."user" (user_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_notification_from | type: CONSTRAINT --
-- ALTER TABLE public.notification DROP CONSTRAINT IF EXISTS fk_notification_from CASCADE;
ALTER TABLE public.notification
    ADD CONSTRAINT fk_notification_from FOREIGN KEY ("from")
        REFERENCES public."user" (user_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_object_dictionary_visibility_user | type: CONSTRAINT --
-- ALTER TABLE public.object_dictionary_visibility DROP CONSTRAINT IF EXISTS fk_object_dictionary_visibility_user CASCADE;
ALTER TABLE public.object_dictionary_visibility
    ADD CONSTRAINT fk_object_dictionary_visibility_user FOREIGN KEY (user_id)
        REFERENCES public."user" (user_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_object_dictionary_visibility_object_dictionary | type: CONSTRAINT --
-- ALTER TABLE public.object_dictionary_visibility DROP CONSTRAINT IF EXISTS fk_object_dictionary_visibility_object_dictionary CASCADE;
ALTER TABLE public.object_dictionary_visibility
    ADD CONSTRAINT fk_object_dictionary_visibility_object_dictionary FOREIGN KEY (object_dictionary_id)
        REFERENCES public.object_dictionary (object_dictionary_id) MATCH FULL
        ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_http_api_visibility_http_api | type: CONSTRAINT --
-- ALTER TABLE public.http_api_visibility DROP CONSTRAINT IF EXISTS fk_http_api_visibility_http_api CASCADE;
ALTER TABLE public.http_api_visibility
    ADD CONSTRAINT fk_http_api_visibility_http_api FOREIGN KEY (http_api_id)
        REFERENCES public.http_api (http_api_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: fk_http_api_visibility_user | type: CONSTRAINT --
-- ALTER TABLE public.http_api_visibility DROP CONSTRAINT IF EXISTS fk_http_api_visibility_user CASCADE;
ALTER TABLE public.http_api_visibility
    ADD CONSTRAINT fk_http_api_visibility_user FOREIGN KEY (user_id)
        REFERENCES public."user" (user_id) MATCH FULL
        ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --


