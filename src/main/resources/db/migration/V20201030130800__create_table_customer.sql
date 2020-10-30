CREATE TABLE public.customer (
    id uuid NOT NULL,
    user_id bigint NOT NULL,
    name character varying NOT NULL,
    email character varying NOT NULL,
    mobile_phone character varying NOT NULL,
    phone character varying,
    birth_date DATE NOT NULL,
    document character varying,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);

ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id, user_id);

CREATE INDEX IF NOT EXISTS "customer_name_idx" ON public.customer USING btree("name");

CREATE INDEX IF NOT EXISTS "customer_email_idx" ON public.customer USING btree("email");

CREATE INDEX IF NOT EXISTS "customer_mobile_phone_idx" ON public.customer USING btree("mobile_phone");