ALTER TABLE public.http_request
    DROP COLUMN method;

ALTER TABLE public.http_request
    ADD COLUMN method text;

ALTER TABLE public.http_request
    DROP COLUMN parameters;

ALTER TABLE public.http_request
    ADD COLUMN parameters text;

ALTER TABLE public.http_request
    DROP COLUMN request_bodies;

ALTER TABLE public.http_request
    ADD COLUMN request_bodies text;

ALTER TABLE public.http_request
    DROP COLUMN responses;

ALTER TABLE public.http_request
    ADD COLUMN responses text;

ALTER TABLE public.http_api_enviroment
    DROP COLUMN enviroment;

ALTER TABLE public.http_api_enviroment
    ADD COLUMN enviroment text;