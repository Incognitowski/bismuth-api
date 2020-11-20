CREATE TABLE public.text_document
(
    text_document_id uuid NOT NULL,
    application_id   uuid NOT NULL,
    content          text NOT NULL,
    CONSTRAINT pk_text_document_id PRIMARY KEY (text_document_id)

) INHERITS (public.base_model_with_ownership);

ALTER TABLE public.text_document
    OWNER TO postgres;

ALTER TABLE public.text_document
    ADD CONSTRAINT fk_application_id FOREIGN KEY (application_id)
        REFERENCES public.application (application_id) MATCH FULL
        ON DELETE NO ACTION ON UPDATE NO ACTION;



