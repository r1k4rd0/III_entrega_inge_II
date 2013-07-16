--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.4
-- Dumped by pg_dump version 9.2.4
-- Started on 2013-07-15 07:54:27 CST

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 182 (class 3079 OID 11935)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2228 (class 0 OID 0)
-- Dependencies: 182
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 174 (class 1259 OID 16438)
-- Name: eleccion; Type: TABLE; Schema: public; Owner: inge2_2013; Tablespace: 
--

CREATE TABLE eleccion (
    id_evento integer NOT NULL,
    motivo character varying(200),
    reg_tend_inicio date,
    reg_tend_fin date,
    requisitos_inicio date,
    requisitos_fin date,
    votacion_inicio date,
    votacion_fin date,
    padron integer,
    cedula_creador bigint
);


ALTER TABLE public.eleccion OWNER TO inge2_2013;

--
-- TOC entry 173 (class 1259 OID 16436)
-- Name: eleccion_id_evento_seq; Type: SEQUENCE; Schema: public; Owner: inge2_2013
--

CREATE SEQUENCE eleccion_id_evento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.eleccion_id_evento_seq OWNER TO inge2_2013;

--
-- TOC entry 2230 (class 0 OID 0)
-- Dependencies: 173
-- Name: eleccion_id_evento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: inge2_2013
--

ALTER SEQUENCE eleccion_id_evento_seq OWNED BY eleccion.id_evento;


--
-- TOC entry 170 (class 1259 OID 16422)
-- Name: padron; Type: TABLE; Schema: public; Owner: inge2_2013; Tablespace: 
--

CREATE TABLE padron (
    id_padron integer NOT NULL,
    ruta character varying(1000)
);


ALTER TABLE public.padron OWNER TO inge2_2013;

--
-- TOC entry 169 (class 1259 OID 16420)
-- Name: padron_id_padron_seq; Type: SEQUENCE; Schema: public; Owner: inge2_2013
--

CREATE SEQUENCE padron_id_padron_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.padron_id_padron_seq OWNER TO inge2_2013;

--
-- TOC entry 2233 (class 0 OID 0)
-- Dependencies: 169
-- Name: padron_id_padron_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: inge2_2013
--

ALTER SEQUENCE padron_id_padron_seq OWNED BY padron.id_padron;


--
-- TOC entry 168 (class 1259 OID 16406)
-- Name: persona; Type: TABLE; Schema: public; Owner: inge2_2013; Tablespace: 
--

CREATE TABLE persona (
    cedula bigint NOT NULL,
    nombre character varying(24),
    apellido1 character varying(24),
    apellido2 character varying(24),
    fecha_nacim date,
    correo character varying(48),
    clave character varying(48),
    foto character varying(250),
    llave_publica bytea,
    CONSTRAINT persona_cedula_check CHECK (((cedula > 99999999) AND (cedula < 10000000000000::bigint)))
);


ALTER TABLE public.persona OWNER TO inge2_2013;

--
-- TOC entry 177 (class 1259 OID 16471)
-- Name: persona_en_padron; Type: TABLE; Schema: public; Owner: inge2_2013; Tablespace: 
--

CREATE TABLE persona_en_padron (
    cedula bigint NOT NULL,
    id_padron integer NOT NULL
);


ALTER TABLE public.persona_en_padron OWNER TO inge2_2013;

--
-- TOC entry 172 (class 1259 OID 16430)
-- Name: plebiscito; Type: TABLE; Schema: public; Owner: inge2_2013; Tablespace: 
--

CREATE TABLE plebiscito (
    id_evento integer NOT NULL,
    nombre character varying(200),
    pregunta character varying(200),
    reg_tend_inicio date,
    reg_tend_fin date,
    requisitos_inicio date,
    requisitos_fin date,
    votacion_inicio date,
    votacion_fin date,
    padron integer,
    cedula_creador bigint
);


ALTER TABLE public.plebiscito OWNER TO inge2_2013;

--
-- TOC entry 171 (class 1259 OID 16428)
-- Name: plebiscito_id_evento_seq; Type: SEQUENCE; Schema: public; Owner: inge2_2013
--

CREATE SEQUENCE plebiscito_id_evento_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.plebiscito_id_evento_seq OWNER TO inge2_2013;

--
-- TOC entry 2238 (class 0 OID 0)
-- Dependencies: 171
-- Name: plebiscito_id_evento_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: inge2_2013
--

ALTER SEQUENCE plebiscito_id_evento_seq OWNED BY plebiscito.id_evento;


--
-- TOC entry 180 (class 1259 OID 16596)
-- Name: resultados_eleccion; Type: TABLE; Schema: public; Owner: inge2_2013; Tablespace: 
--

CREATE TABLE resultados_eleccion (
    id_evento integer NOT NULL,
    id_tendencia integer NOT NULL,
    votos_recibidos integer NOT NULL
);


ALTER TABLE public.resultados_eleccion OWNER TO inge2_2013;

--
-- TOC entry 181 (class 1259 OID 16618)
-- Name: resultados_plebiscito; Type: TABLE; Schema: public; Owner: inge2_2013; Tablespace: 
--

CREATE TABLE resultados_plebiscito (
    id_evento integer NOT NULL,
    id_tendencia integer NOT NULL,
    votos_recibidos integer NOT NULL
);


ALTER TABLE public.resultados_plebiscito OWNER TO inge2_2013;

--
-- TOC entry 176 (class 1259 OID 16457)
-- Name: tendencia; Type: TABLE; Schema: public; Owner: inge2_2013; Tablespace: 
--

CREATE TABLE tendencia (
    id_tendencia integer NOT NULL,
    nombre character varying(200),
    informacion character varying(200),
    enlaces character varying(500)
);


ALTER TABLE public.tendencia OWNER TO inge2_2013;

--
-- TOC entry 179 (class 1259 OID 16548)
-- Name: tendencia_eleccion; Type: TABLE; Schema: public; Owner: inge2_2013; Tablespace: 
--

CREATE TABLE tendencia_eleccion (
    id_tendencia integer NOT NULL,
    id_evento integer NOT NULL,
    candidato bigint
);


ALTER TABLE public.tendencia_eleccion OWNER TO inge2_2013;

--
-- TOC entry 175 (class 1259 OID 16455)
-- Name: tendencia_id_tendencia_seq; Type: SEQUENCE; Schema: public; Owner: inge2_2013
--

CREATE SEQUENCE tendencia_id_tendencia_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tendencia_id_tendencia_seq OWNER TO inge2_2013;

--
-- TOC entry 2244 (class 0 OID 0)
-- Dependencies: 175
-- Name: tendencia_id_tendencia_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: inge2_2013
--

ALTER SEQUENCE tendencia_id_tendencia_seq OWNED BY tendencia.id_tendencia;


--
-- TOC entry 178 (class 1259 OID 16518)
-- Name: tendencia_plebiscito; Type: TABLE; Schema: public; Owner: inge2_2013; Tablespace: 
--

CREATE TABLE tendencia_plebiscito (
    id_tendencia integer NOT NULL,
    id_evento integer NOT NULL
);


ALTER TABLE public.tendencia_plebiscito OWNER TO inge2_2013;

--
-- TOC entry 2170 (class 2604 OID 16441)
-- Name: id_evento; Type: DEFAULT; Schema: public; Owner: inge2_2013
--

ALTER TABLE ONLY eleccion ALTER COLUMN id_evento SET DEFAULT nextval('eleccion_id_evento_seq'::regclass);


--
-- TOC entry 2168 (class 2604 OID 16425)
-- Name: id_padron; Type: DEFAULT; Schema: public; Owner: inge2_2013
--

ALTER TABLE ONLY padron ALTER COLUMN id_padron SET DEFAULT nextval('padron_id_padron_seq'::regclass);


--
-- TOC entry 2169 (class 2604 OID 16433)
-- Name: id_evento; Type: DEFAULT; Schema: public; Owner: inge2_2013
--

ALTER TABLE ONLY plebiscito ALTER COLUMN id_evento SET DEFAULT nextval('plebiscito_id_evento_seq'::regclass);


--
-- TOC entry 2171 (class 2604 OID 16460)
-- Name: id_tendencia; Type: DEFAULT; Schema: public; Owner: inge2_2013
--

ALTER TABLE ONLY tendencia ALTER COLUMN id_tendencia SET DEFAULT nextval('tendencia_id_tendencia_seq'::regclass);


--
-- TOC entry 2213 (class 0 OID 16438)
-- Dependencies: 174
-- Data for Name: eleccion; Type: TABLE DATA; Schema: public; Owner: inge2_2013
--

COPY eleccion (id_evento, motivo, reg_tend_inicio, reg_tend_fin, requisitos_inicio, requisitos_fin, votacion_inicio, votacion_fin, padron, cedula_creador) FROM stdin;
1	Eleccion Presidencial	2013-06-25	2013-07-01	\N	\N	\N	\N	\N	\N
2	Eleccion de director de escuela	2013-05-15	2013-05-17	\N	\N	\N	\N	\N	\N
4	Elecciones internas de candidato presidencial	2013-06-18	2013-06-19	\N	\N	\N	\N	\N	\N
3	Eleccion Ganador de feria	2013-06-25	2013-06-27	\N	\N	\N	\N	\N	\N
5	Elección de presidente del reino	2013-06-11	2013-06-11	2013-06-11	2013-06-11	2013-06-11	2013-06-11	\N	\N
6	Vote para el más corrupto en el Gobierno	2013-06-24	2013-06-24	2013-06-24	2013-06-24	2013-06-24	2013-06-24	\N	\N
7	Motivo de prueba	2013-06-12	2013-06-12	2013-06-19	2013-06-19	2013-06-11	2013-06-12	\N	\N
8	Motivo de prueba	2013-06-12	2013-06-12	2013-06-19	2013-06-19	2013-06-11	2013-06-12	\N	\N
9	Prueba de Eleccion	2013-06-06	2013-06-06	2013-06-06	2013-06-06	2013-06-06	2013-06-06	\N	\N
10	Hola mundo	2013-06-06	2013-06-06	2013-06-06	2013-06-06	2013-06-06	2013-06-06	\N	\N
12	Prueba de evento modificado	2013-07-07	2013-07-07	2013-07-07	2013-07-28	2013-08-08	2013-08-08	1	112345678
11	Hola	2013-06-04	2013-06-04	2013-06-04	2013-06-04	2013-06-04	2013-06-04	1	112345678
\.


--
-- TOC entry 2247 (class 0 OID 0)
-- Dependencies: 173
-- Name: eleccion_id_evento_seq; Type: SEQUENCE SET; Schema: public; Owner: inge2_2013
--

SELECT pg_catalog.setval('eleccion_id_evento_seq', 17, true);


--
-- TOC entry 2209 (class 0 OID 16422)
-- Dependencies: 170
-- Data for Name: padron; Type: TABLE DATA; Schema: public; Owner: inge2_2013
--

COPY padron (id_padron, ruta) FROM stdin;
1	padron de estudiantes ecci
2	Padron de junta cantonal Zarcero
3	Padron elección de alcalde Tarrazú
\.


--
-- TOC entry 2248 (class 0 OID 0)
-- Dependencies: 169
-- Name: padron_id_padron_seq; Type: SEQUENCE SET; Schema: public; Owner: inge2_2013
--

SELECT pg_catalog.setval('padron_id_padron_seq', 3, true);


--
-- TOC entry 2207 (class 0 OID 16406)
-- Dependencies: 168
-- Data for Name: persona; Type: TABLE DATA; Schema: public; Owner: inge2_2013
--

COPY persona (cedula, nombre, apellido1, apellido2, fecha_nacim, correo, clave, foto, llave_publica) FROM stdin;
912344567	Eduardo	Steiner	Garro	1990-01-01	eduard16sg@gmail.com	\N	\N	\N
545614561	Rebeca	Ramírez	Arroyo	1992-05-25	rivka.ra55@gmail.com	\N	\N	\N
987654321	María	Díaz	Vargas	\N	mary.2@hotmail.com	\N	\N	\N
113530880	Ricardo	Herrera	Vargas	1988-04-25	rahv88@gmail.com	\N	\N	\\x308201b83082012c06072a8648ce3804013082011f02818100fd7f53811d75122952df4a9c2eece4e7f611b7523cef4400c31e3f80b6512669455d402251fb593d8d58fabfc5f5ba30f6cb9b556cd7813b801d346ff26660b76b9950a5a49f9fe8047b1022c24fbba9d7feb7c61bf83b57e7c6a8a6150f04fb83f6d3c51ec3023554135a169132f675f3ae2b61d72aeff22203199dd14801c70215009760508f15230bccb292b982a2eb840bf0581cf502818100f7e1a085d69b3ddecbbcab5c36b857b97994afbbfa3aea82f9574c0b3d0782675159578ebad4594fe67107108180b449167123e84c281613b7cf09328cc8a6e13c167a8b547c8d28e0a3ae1e2bb3a675916ea37f0bfa213562f1fb627a01243bcca4f1bea8519089a883dfe15ae59f06928b665e807b552564014c3bfecf492a0381850002818100e48de0383e40f55517f58ebac674476c015802e78fc70a6b2761f8582c2aaf2115d3e89be1708a3f36df9917dc555908924b5e046e4e96ce1da43f68d42d68e4cb0b0fa3b42b9bc90796ee8e8debe3893169596a8023146d361c01a26b67202c9140bb84c00471baf6dbc096c58d30a919cb8de8f10496da11a0865c058b90e1
112345678	Juan	Gómez	Salas	1991-10-20	juan123@live.com	\N	\N	\N
888888888	Josué	Bonilla	Mora	\N	user@inge.com	password	\N	\N
\.


--
-- TOC entry 2216 (class 0 OID 16471)
-- Dependencies: 177
-- Data for Name: persona_en_padron; Type: TABLE DATA; Schema: public; Owner: inge2_2013
--

COPY persona_en_padron (cedula, id_padron) FROM stdin;
888888888	3
545614561	3
112345678	1
\.


--
-- TOC entry 2211 (class 0 OID 16430)
-- Dependencies: 172
-- Data for Name: plebiscito; Type: TABLE DATA; Schema: public; Owner: inge2_2013
--

COPY plebiscito (id_evento, nombre, pregunta, reg_tend_inicio, reg_tend_fin, requisitos_inicio, requisitos_fin, votacion_inicio, votacion_fin, padron, cedula_creador) FROM stdin;
1	Matrimonio humano-animal	¿Está usted de acuerdo en que se apruebe el matrimonio entre personas y animales?	2013-06-24	2013-06-30	2013-06-24	2013-06-30	2013-07-01	2013-07-01	\N	\N
2	Ganador mundial futbol	¿Quien va a ganar el mundial?	\N	\N	\N	\N	\N	\N	\N	\N
3	Buen o mal gobierno	¿Este último gobierno ha sido bueno?	\N	\N	\N	\N	\N	\N	\N	\N
4	Mejor mamá del mundo	¿Cuál es la mejor mamá del mundo?	\N	\N	\N	\N	\N	\N	\N	\N
5	Prueba de Plebiscito	Sabe usted que es un Plebiscito?	2013-06-11	2013-06-15	2013-06-17	2013-06-21	2013-06-24	2013-06-28	\N	\N
\.


--
-- TOC entry 2249 (class 0 OID 0)
-- Dependencies: 171
-- Name: plebiscito_id_evento_seq; Type: SEQUENCE SET; Schema: public; Owner: inge2_2013
--

SELECT pg_catalog.setval('plebiscito_id_evento_seq', 7, true);


--
-- TOC entry 2219 (class 0 OID 16596)
-- Dependencies: 180
-- Data for Name: resultados_eleccion; Type: TABLE DATA; Schema: public; Owner: inge2_2013
--

COPY resultados_eleccion (id_evento, id_tendencia, votos_recibidos) FROM stdin;
2	23	7
\.


--
-- TOC entry 2220 (class 0 OID 16618)
-- Dependencies: 181
-- Data for Name: resultados_plebiscito; Type: TABLE DATA; Schema: public; Owner: inge2_2013
--

COPY resultados_plebiscito (id_evento, id_tendencia, votos_recibidos) FROM stdin;
2	29	15
\.


--
-- TOC entry 2215 (class 0 OID 16457)
-- Dependencies: 176
-- Data for Name: tendencia; Type: TABLE DATA; Schema: public; Owner: inge2_2013
--

COPY tendencia (id_tendencia, nombre, informacion, enlaces) FROM stdin;
111	PLN	malos	www.pln.com
333	pac	amarillos	www; ; 
133	Fecundacion in vitro	Aprobacion de fecundacion in vitro	fecun.com, , 
166	Perros callejeros	Lucha por mejorar la vida de los callejeros	caniches.com, , 
177	Lineas 4g	Seguridad en lineas 4g	lineas4g.com, , 
199	Perros sanos	Salud animal	salubani.com, , 
229	Calidad de atencion	Lucha por mejora en atencion al publico	, , 
23	PUSC	Partido Unidad Social	www.pusc.com, , 
24	Años 80	Generacion 80	gen80.com, , 
29	Partido Latino	Latinos en politica	unionlat.com, , 
30	Equipo a		equipoa.com, , 
32	banderas	banderas de paises	, , 
33	equipo bueno	un buen equipo	equipobueno.com, , 
34	nuevogrupo	cualquiera	nuevogru.com, , 
35	Nuevopleb	asdasd	nuevop.com, , 
40	Donadores	donar sangre	, , 
44	Jirafas	jirafas grandes	, , 
45	gatos	gatos domesticos	, , 
46	vaca	vacas 	, , 
47	dsfsdf	sdfsdf	, , 
48	musica	mucha musica	, , 
49	perrogato	sdfdsf	, , 
50	lololo	sdfadf	, , 
51	loquesea	sdfsdf	, , 
52	cosa	vfsdfsdf	, , 
53	denuevo	sfdsdf	, , 
54	otravez	kksdjfkjsd	, , 
55	todo	sadsad	, , 
56	caballos	blancos	, , 
57	celular	afsdf	, , 
58	banda	banda musical	, , 
59	golden	raza	, , 
60	bases	asdsd	, , 
61	otrootro	sdfsdf	, , 
62	tendenciapleb	asddadsa	, , 
63	plebnuevo	sfddf	, , 
64	Partido Ecologico	Somos un partido que protege al medio ambiente	partieco.com, , 
65	Partido Del Sol	Somos el partido de el valle	partsol.com, , 
66	Oso	Lucha por los osos	oso.com, , 
67	TendenciaRebe	Tendencia para probar	tendreb.com, , 
68	Equipo brasil	Seleccion de Brasil	sebbr.com, , 
69	Equipo Costa Rica	equipo	, , 
70	EleccionProfesores	Eleccion para escoger profesor guia	, , 
71	Partido Unico	Partido de maestros	, , 
72	TendPleb	informacion de tendencia	tend.com, , 
73	TendPleb	info	info.com, , 
74	TendPruebaa		, , 
75	PruebaTendencia		, , 
76	Prueba2		, , 
77	tendenciadeerror		, , 
78	Plebiscito3	jsdsdf	, , 
\.


--
-- TOC entry 2218 (class 0 OID 16548)
-- Dependencies: 179
-- Data for Name: tendencia_eleccion; Type: TABLE DATA; Schema: public; Owner: inge2_2013
--

COPY tendencia_eleccion (id_tendencia, id_evento, candidato) FROM stdin;
23	2	\N
64	1	\N
65	4	\N
\.


--
-- TOC entry 2250 (class 0 OID 0)
-- Dependencies: 175
-- Name: tendencia_id_tendencia_seq; Type: SEQUENCE SET; Schema: public; Owner: inge2_2013
--

SELECT pg_catalog.setval('tendencia_id_tendencia_seq', 78, true);


--
-- TOC entry 2217 (class 0 OID 16518)
-- Dependencies: 178
-- Data for Name: tendencia_plebiscito; Type: TABLE DATA; Schema: public; Owner: inge2_2013
--

COPY tendencia_plebiscito (id_tendencia, id_evento) FROM stdin;
111	2
29	2
30	2
\.


--
-- TOC entry 2179 (class 2606 OID 16443)
-- Name: eleccion_pkey; Type: CONSTRAINT; Schema: public; Owner: inge2_2013; Tablespace: 
--

ALTER TABLE ONLY eleccion
    ADD CONSTRAINT eleccion_pkey PRIMARY KEY (id_evento);


--
-- TOC entry 2175 (class 2606 OID 16427)
-- Name: padron_pkey; Type: CONSTRAINT; Schema: public; Owner: inge2_2013; Tablespace: 
--

ALTER TABLE ONLY padron
    ADD CONSTRAINT padron_pkey PRIMARY KEY (id_padron);


--
-- TOC entry 2183 (class 2606 OID 16650)
-- Name: persona_en_padron_pkey; Type: CONSTRAINT; Schema: public; Owner: inge2_2013; Tablespace: 
--

ALTER TABLE ONLY persona_en_padron
    ADD CONSTRAINT persona_en_padron_pkey PRIMARY KEY (cedula, id_padron);


--
-- TOC entry 2173 (class 2606 OID 16411)
-- Name: persona_pkey; Type: CONSTRAINT; Schema: public; Owner: inge2_2013; Tablespace: 
--

ALTER TABLE ONLY persona
    ADD CONSTRAINT persona_pkey PRIMARY KEY (cedula);


--
-- TOC entry 2191 (class 2606 OID 16612)
-- Name: pk_eleccion_tendencia; Type: CONSTRAINT; Schema: public; Owner: inge2_2013; Tablespace: 
--

ALTER TABLE ONLY resultados_eleccion
    ADD CONSTRAINT pk_eleccion_tendencia PRIMARY KEY (id_evento, id_tendencia);


--
-- TOC entry 2193 (class 2606 OID 16622)
-- Name: pk_plebiscito_tendencia; Type: CONSTRAINT; Schema: public; Owner: inge2_2013; Tablespace: 
--

ALTER TABLE ONLY resultados_plebiscito
    ADD CONSTRAINT pk_plebiscito_tendencia PRIMARY KEY (id_evento, id_tendencia);


--
-- TOC entry 2187 (class 2606 OID 16554)
-- Name: pk_tendencia_eleccion; Type: CONSTRAINT; Schema: public; Owner: inge2_2013; Tablespace: 
--

ALTER TABLE ONLY tendencia_eleccion
    ADD CONSTRAINT pk_tendencia_eleccion PRIMARY KEY (id_tendencia, id_evento);


--
-- TOC entry 2185 (class 2606 OID 16524)
-- Name: pk_tendencia_plebiscito; Type: CONSTRAINT; Schema: public; Owner: inge2_2013; Tablespace: 
--

ALTER TABLE ONLY tendencia_plebiscito
    ADD CONSTRAINT pk_tendencia_plebiscito PRIMARY KEY (id_tendencia, id_evento);


--
-- TOC entry 2177 (class 2606 OID 16435)
-- Name: plebiscito_pkey; Type: CONSTRAINT; Schema: public; Owner: inge2_2013; Tablespace: 
--

ALTER TABLE ONLY plebiscito
    ADD CONSTRAINT plebiscito_pkey PRIMARY KEY (id_evento);


--
-- TOC entry 2189 (class 2606 OID 16556)
-- Name: tendencia_eleccion_candidato_key; Type: CONSTRAINT; Schema: public; Owner: inge2_2013; Tablespace: 
--

ALTER TABLE ONLY tendencia_eleccion
    ADD CONSTRAINT tendencia_eleccion_candidato_key UNIQUE (candidato);


--
-- TOC entry 2181 (class 2606 OID 16465)
-- Name: tendencia_pkey; Type: CONSTRAINT; Schema: public; Owner: inge2_2013; Tablespace: 
--

ALTER TABLE ONLY tendencia
    ADD CONSTRAINT tendencia_pkey PRIMARY KEY (id_tendencia);


--
-- TOC entry 2197 (class 2606 OID 16689)
-- Name: cedula_creador; Type: FK CONSTRAINT; Schema: public; Owner: inge2_2013
--

ALTER TABLE ONLY eleccion
    ADD CONSTRAINT cedula_creador FOREIGN KEY (cedula_creador) REFERENCES persona(cedula) ON UPDATE CASCADE;


--
-- TOC entry 2195 (class 2606 OID 16694)
-- Name: cedula_creadorfk; Type: FK CONSTRAINT; Schema: public; Owner: inge2_2013
--

ALTER TABLE ONLY plebiscito
    ADD CONSTRAINT cedula_creadorfk FOREIGN KEY (cedula_creador) REFERENCES persona(cedula) ON UPDATE CASCADE;


--
-- TOC entry 2196 (class 2606 OID 16669)
-- Name: eleccion_padron_fkey; Type: FK CONSTRAINT; Schema: public; Owner: inge2_2013
--

ALTER TABLE ONLY eleccion
    ADD CONSTRAINT eleccion_padron_fkey FOREIGN KEY (padron) REFERENCES padron(id_padron) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 2204 (class 2606 OID 16567)
-- Name: fk_candidato; Type: FK CONSTRAINT; Schema: public; Owner: inge2_2013
--

ALTER TABLE ONLY tendencia_eleccion
    ADD CONSTRAINT fk_candidato FOREIGN KEY (candidato) REFERENCES persona(cedula) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 2203 (class 2606 OID 16562)
-- Name: fk_eleccion; Type: FK CONSTRAINT; Schema: public; Owner: inge2_2013
--

ALTER TABLE ONLY tendencia_eleccion
    ADD CONSTRAINT fk_eleccion FOREIGN KEY (id_evento) REFERENCES eleccion(id_evento) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2205 (class 2606 OID 16613)
-- Name: fk_eleccion_tendencia; Type: FK CONSTRAINT; Schema: public; Owner: inge2_2013
--

ALTER TABLE ONLY resultados_eleccion
    ADD CONSTRAINT fk_eleccion_tendencia FOREIGN KEY (id_evento, id_tendencia) REFERENCES tendencia_eleccion(id_evento, id_tendencia) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2198 (class 2606 OID 16491)
-- Name: fk_persona; Type: FK CONSTRAINT; Schema: public; Owner: inge2_2013
--

ALTER TABLE ONLY persona_en_padron
    ADD CONSTRAINT fk_persona FOREIGN KEY (cedula) REFERENCES persona(cedula) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2201 (class 2606 OID 16530)
-- Name: fk_plebiscito; Type: FK CONSTRAINT; Schema: public; Owner: inge2_2013
--

ALTER TABLE ONLY tendencia_plebiscito
    ADD CONSTRAINT fk_plebiscito FOREIGN KEY (id_evento) REFERENCES plebiscito(id_evento) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2206 (class 2606 OID 16623)
-- Name: fk_plebiscito_tendencia; Type: FK CONSTRAINT; Schema: public; Owner: inge2_2013
--

ALTER TABLE ONLY resultados_plebiscito
    ADD CONSTRAINT fk_plebiscito_tendencia FOREIGN KEY (id_evento, id_tendencia) REFERENCES tendencia_plebiscito(id_evento, id_tendencia) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2200 (class 2606 OID 16525)
-- Name: fk_tendencia; Type: FK CONSTRAINT; Schema: public; Owner: inge2_2013
--

ALTER TABLE ONLY tendencia_plebiscito
    ADD CONSTRAINT fk_tendencia FOREIGN KEY (id_tendencia) REFERENCES tendencia(id_tendencia) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2202 (class 2606 OID 16557)
-- Name: fk_tendencia; Type: FK CONSTRAINT; Schema: public; Owner: inge2_2013
--

ALTER TABLE ONLY tendencia_eleccion
    ADD CONSTRAINT fk_tendencia FOREIGN KEY (id_tendencia) REFERENCES tendencia(id_tendencia) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2199 (class 2606 OID 16644)
-- Name: persona_en_padron_id_padron_fkey; Type: FK CONSTRAINT; Schema: public; Owner: inge2_2013
--

ALTER TABLE ONLY persona_en_padron
    ADD CONSTRAINT persona_en_padron_id_padron_fkey FOREIGN KEY (id_padron) REFERENCES padron(id_padron) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2194 (class 2606 OID 16679)
-- Name: plebiscito_padron_fkey; Type: FK CONSTRAINT; Schema: public; Owner: inge2_2013
--

ALTER TABLE ONLY plebiscito
    ADD CONSTRAINT plebiscito_padron_fkey FOREIGN KEY (padron) REFERENCES padron(id_padron) ON UPDATE CASCADE ON DELETE SET NULL;


--
-- TOC entry 2227 (class 0 OID 0)
-- Dependencies: 6
-- Name: public; Type: ACL; Schema: -; Owner: inge2_2013
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM inge2_2013;
GRANT ALL ON SCHEMA public TO inge2_2013;


--
-- TOC entry 2229 (class 0 OID 0)
-- Dependencies: 174
-- Name: eleccion; Type: ACL; Schema: public; Owner: inge2_2013
--

REVOKE ALL ON TABLE eleccion FROM PUBLIC;
REVOKE ALL ON TABLE eleccion FROM inge2_2013;
GRANT ALL ON TABLE eleccion TO inge2_2013;
GRANT ALL ON TABLE eleccion TO rebeca;
GRANT ALL ON TABLE eleccion TO eduardo;


--
-- TOC entry 2231 (class 0 OID 0)
-- Dependencies: 173
-- Name: eleccion_id_evento_seq; Type: ACL; Schema: public; Owner: inge2_2013
--

REVOKE ALL ON SEQUENCE eleccion_id_evento_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE eleccion_id_evento_seq FROM inge2_2013;
GRANT ALL ON SEQUENCE eleccion_id_evento_seq TO inge2_2013;
GRANT ALL ON SEQUENCE eleccion_id_evento_seq TO rebeca;
GRANT ALL ON SEQUENCE eleccion_id_evento_seq TO eduardo;


--
-- TOC entry 2232 (class 0 OID 0)
-- Dependencies: 170
-- Name: padron; Type: ACL; Schema: public; Owner: inge2_2013
--

REVOKE ALL ON TABLE padron FROM PUBLIC;
REVOKE ALL ON TABLE padron FROM inge2_2013;
GRANT ALL ON TABLE padron TO inge2_2013;
GRANT ALL ON TABLE padron TO rebeca;
GRANT ALL ON TABLE padron TO eduardo;


--
-- TOC entry 2234 (class 0 OID 0)
-- Dependencies: 169
-- Name: padron_id_padron_seq; Type: ACL; Schema: public; Owner: inge2_2013
--

REVOKE ALL ON SEQUENCE padron_id_padron_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE padron_id_padron_seq FROM inge2_2013;
GRANT ALL ON SEQUENCE padron_id_padron_seq TO inge2_2013;
GRANT ALL ON SEQUENCE padron_id_padron_seq TO rebeca;
GRANT ALL ON SEQUENCE padron_id_padron_seq TO eduardo;


--
-- TOC entry 2235 (class 0 OID 0)
-- Dependencies: 168
-- Name: persona; Type: ACL; Schema: public; Owner: inge2_2013
--

REVOKE ALL ON TABLE persona FROM PUBLIC;
REVOKE ALL ON TABLE persona FROM inge2_2013;
GRANT ALL ON TABLE persona TO inge2_2013;
GRANT ALL ON TABLE persona TO rebeca;
GRANT ALL ON TABLE persona TO eduardo;


--
-- TOC entry 2236 (class 0 OID 0)
-- Dependencies: 177
-- Name: persona_en_padron; Type: ACL; Schema: public; Owner: inge2_2013
--

REVOKE ALL ON TABLE persona_en_padron FROM PUBLIC;
REVOKE ALL ON TABLE persona_en_padron FROM inge2_2013;
GRANT ALL ON TABLE persona_en_padron TO inge2_2013;
GRANT ALL ON TABLE persona_en_padron TO rebeca;
GRANT ALL ON TABLE persona_en_padron TO eduardo;


--
-- TOC entry 2237 (class 0 OID 0)
-- Dependencies: 172
-- Name: plebiscito; Type: ACL; Schema: public; Owner: inge2_2013
--

REVOKE ALL ON TABLE plebiscito FROM PUBLIC;
REVOKE ALL ON TABLE plebiscito FROM inge2_2013;
GRANT ALL ON TABLE plebiscito TO inge2_2013;
GRANT ALL ON TABLE plebiscito TO rebeca;
GRANT ALL ON TABLE plebiscito TO eduardo;


--
-- TOC entry 2239 (class 0 OID 0)
-- Dependencies: 171
-- Name: plebiscito_id_evento_seq; Type: ACL; Schema: public; Owner: inge2_2013
--

REVOKE ALL ON SEQUENCE plebiscito_id_evento_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE plebiscito_id_evento_seq FROM inge2_2013;
GRANT ALL ON SEQUENCE plebiscito_id_evento_seq TO inge2_2013;
GRANT ALL ON SEQUENCE plebiscito_id_evento_seq TO rebeca;
GRANT ALL ON SEQUENCE plebiscito_id_evento_seq TO eduardo;


--
-- TOC entry 2240 (class 0 OID 0)
-- Dependencies: 180
-- Name: resultados_eleccion; Type: ACL; Schema: public; Owner: inge2_2013
--

REVOKE ALL ON TABLE resultados_eleccion FROM PUBLIC;
REVOKE ALL ON TABLE resultados_eleccion FROM inge2_2013;
GRANT ALL ON TABLE resultados_eleccion TO inge2_2013;
GRANT ALL ON TABLE resultados_eleccion TO rebeca;
GRANT ALL ON TABLE resultados_eleccion TO eduardo;


--
-- TOC entry 2241 (class 0 OID 0)
-- Dependencies: 181
-- Name: resultados_plebiscito; Type: ACL; Schema: public; Owner: inge2_2013
--

REVOKE ALL ON TABLE resultados_plebiscito FROM PUBLIC;
REVOKE ALL ON TABLE resultados_plebiscito FROM inge2_2013;
GRANT ALL ON TABLE resultados_plebiscito TO inge2_2013;
GRANT ALL ON TABLE resultados_plebiscito TO rebeca;
GRANT ALL ON TABLE resultados_plebiscito TO eduardo;


--
-- TOC entry 2242 (class 0 OID 0)
-- Dependencies: 176
-- Name: tendencia; Type: ACL; Schema: public; Owner: inge2_2013
--

REVOKE ALL ON TABLE tendencia FROM PUBLIC;
REVOKE ALL ON TABLE tendencia FROM inge2_2013;
GRANT ALL ON TABLE tendencia TO inge2_2013;
GRANT ALL ON TABLE tendencia TO rebeca;
GRANT ALL ON TABLE tendencia TO eduardo;


--
-- TOC entry 2243 (class 0 OID 0)
-- Dependencies: 179
-- Name: tendencia_eleccion; Type: ACL; Schema: public; Owner: inge2_2013
--

REVOKE ALL ON TABLE tendencia_eleccion FROM PUBLIC;
REVOKE ALL ON TABLE tendencia_eleccion FROM inge2_2013;
GRANT ALL ON TABLE tendencia_eleccion TO inge2_2013;
GRANT ALL ON TABLE tendencia_eleccion TO rebeca;
GRANT ALL ON TABLE tendencia_eleccion TO eduardo;


--
-- TOC entry 2245 (class 0 OID 0)
-- Dependencies: 175
-- Name: tendencia_id_tendencia_seq; Type: ACL; Schema: public; Owner: inge2_2013
--

REVOKE ALL ON SEQUENCE tendencia_id_tendencia_seq FROM PUBLIC;
REVOKE ALL ON SEQUENCE tendencia_id_tendencia_seq FROM inge2_2013;
GRANT ALL ON SEQUENCE tendencia_id_tendencia_seq TO inge2_2013;
GRANT ALL ON SEQUENCE tendencia_id_tendencia_seq TO rebeca;
GRANT ALL ON SEQUENCE tendencia_id_tendencia_seq TO eduardo;


--
-- TOC entry 2246 (class 0 OID 0)
-- Dependencies: 178
-- Name: tendencia_plebiscito; Type: ACL; Schema: public; Owner: inge2_2013
--

REVOKE ALL ON TABLE tendencia_plebiscito FROM PUBLIC;
REVOKE ALL ON TABLE tendencia_plebiscito FROM inge2_2013;
GRANT ALL ON TABLE tendencia_plebiscito TO inge2_2013;
GRANT ALL ON TABLE tendencia_plebiscito TO rebeca;
GRANT ALL ON TABLE tendencia_plebiscito TO eduardo;


-- Completed on 2013-07-15 07:54:28 CST

--
-- PostgreSQL database dump complete
--

