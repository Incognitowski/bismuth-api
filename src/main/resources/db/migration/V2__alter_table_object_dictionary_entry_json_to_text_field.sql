ALTER TABLE public.object_dictionary_entry
    DROP COLUMN structure;

ALTER TABLE public.object_dictionary_entry
    ADD COLUMN structure text;