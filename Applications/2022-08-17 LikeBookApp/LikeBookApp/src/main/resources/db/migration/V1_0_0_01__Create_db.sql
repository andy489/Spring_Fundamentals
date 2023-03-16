CREATE TABLE public.moods
(
    id          bigint                 NOT NULL,
    description character varying(255),
    name        character varying(255) NOT NULL
);
ALTER TABLE public.moods
    OWNER TO andreystoev;
CREATE SEQUENCE public.moods_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE public.moods_id_seq
    OWNER TO andreystoev;
ALTER SEQUENCE public.moods_id_seq OWNED BY public.moods.id;



CREATE TABLE public.users
(
    id       bigint                 NOT NULL,
    email    character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    username character varying(255) NOT NULL
);
ALTER TABLE public.users
    OWNER TO andreystoev;
CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE public.users_id_seq
    OWNER TO andreystoev;
ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;



CREATE TABLE public.posts
(
    id         bigint                 NOT NULL,
    content    character varying(255) NOT NULL,
    creator_id bigint,
    mood_id    bigint
);
ALTER TABLE public.posts
    OWNER TO andreystoev;
CREATE SEQUENCE public.posts_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
ALTER TABLE public.posts_id_seq
    OWNER TO andreystoev;
ALTER SEQUENCE public.posts_id_seq OWNED BY public.posts.id;



CREATE TABLE public.posts_likes
(
    posts_id bigint NOT NULL,
    likes_id bigint NOT NULL
);
ALTER TABLE public.posts_likes
    OWNER TO andreystoev;



ALTER TABLE ONLY public.moods
    ALTER COLUMN id SET DEFAULT nextval('public.moods_id_seq'::regclass);
ALTER TABLE ONLY public.posts
    ALTER COLUMN id SET DEFAULT nextval('public.posts_id_seq'::regclass);
ALTER TABLE ONLY public.users
    ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);



SELECT pg_catalog.setval('public.moods_id_seq', 1, false);
SELECT pg_catalog.setval('public.posts_id_seq', 1, false);
SELECT pg_catalog.setval('public.users_id_seq', 1, false);



ALTER TABLE ONLY public.moods
    ADD CONSTRAINT moods_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.posts_likes
    ADD CONSTRAINT posts_likes_pkey PRIMARY KEY (posts_id, likes_id);

ALTER TABLE ONLY public.posts
    ADD CONSTRAINT posts_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);



ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_6dotkott2kjsp8vw4d0m25fb7 UNIQUE (email);

ALTER TABLE ONLY public.moods
    ADD CONSTRAINT uk_irhuxolpm5ntmcuwy2vd6e6o5 UNIQUE (name);

ALTER TABLE ONLY public.users
    ADD CONSTRAINT uk_r43af9ap4edm43mmtq01oddj6 UNIQUE (username);



ALTER TABLE ONLY public.posts
    ADD CONSTRAINT fk4mmq5wakeo2iwjh6as3uglw7n FOREIGN KEY (mood_id) REFERENCES public.moods (id);

ALTER TABLE ONLY public.posts_likes
    ADD CONSTRAINT fkcmhmgcgo8189y0smlo2hy1sj1 FOREIGN KEY (likes_id) REFERENCES public.users (id);

ALTER TABLE ONLY public.posts_likes
    ADD CONSTRAINT fkikope7hmhbwatdc2ruhgo475q FOREIGN KEY (posts_id) REFERENCES public.posts (id);

ALTER TABLE ONLY public.posts
    ADD CONSTRAINT fkpbdq30fxpf8l0v3j2eyca7odb FOREIGN KEY (creator_id) REFERENCES public.users (id);