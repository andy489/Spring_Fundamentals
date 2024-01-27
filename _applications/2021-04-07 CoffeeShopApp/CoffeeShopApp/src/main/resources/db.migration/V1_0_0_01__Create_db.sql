CREATE TABLE public.categories
(
    id          bigint                 NOT NULL,
    name        character varying(255) NOT NULL,
    needed_time integer                NOT NULL
);
ALTER TABLE public.categories OWNER TO postgres;

CREATE SEQUENCE public.categories_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;
ALTER TABLE public.categories_id_seq OWNER TO postgres;
ALTER SEQUENCE public.categories_id_seq OWNED BY public.categories.id;

CREATE TABLE public.users
(
    id         bigint                 NOT NULL,
    email      character varying(255) NOT NULL,
    first_name character varying(255) NOT NULL,
    last_name  character varying(255) NOT NULL,
    password   character varying(255) NOT NULL,
    username   character varying(255) NOT NULL
);
ALTER TABLE public.users OWNER TO postgres;

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;

ALTER TABLE public.users_id_seq OWNER TO postgres;
ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;

CREATE TABLE public.orders
(
    id          bigint                 NOT NULL,
    description text                   NOT NULL,
    name        character varying(255) NOT NULL,
    order_time  timestamp(6) without time zone NOT NULL,
    price       numeric(38, 2)         NOT NULL,
    category_id bigint,
    employee_id bigint
);

ALTER TABLE public.orders OWNER TO postgres;

CREATE SEQUENCE public.orders_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE CACHE 1;

ALTER TABLE public.orders_id_seq OWNER TO postgres;
ALTER SEQUENCE public.orders_id_seq OWNED BY public.orders.id;


ALTER TABLE ONLY public.categories ALTER COLUMN id SET DEFAULT nextval('public.categories_id_seq'::regclass);
ALTER TABLE ONLY public.orders ALTER COLUMN id SET DEFAULT nextval('public.orders_id_seq'::regclass);
ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);

SELECT pg_catalog.setval('public.categories_id_seq', 1, false);
SELECT pg_catalog.setval('public.orders_id_seq', 1, false);
SELECT pg_catalog.setval('public.users_id_seq', 1, false);

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT orders_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_r43af9ap4edm43mmtq01oddj6 UNIQUE (username);

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT uk_t8o6pivur7nn124jehx7cygw5 UNIQUE (name);

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fk4w0qnfg1gsjox5tc0my7cflt FOREIGN KEY (category_id) REFERENCES public.categories(id);

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT fkgd67qo7p9pvyabrt03jamvni5 FOREIGN KEY (employee_id) REFERENCES public.users(id);