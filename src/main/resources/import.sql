/*CREAR BASE DE DATOS*/
DROP DATABASE IF EXISTS "db_reto_kruger";
CREATE DATABASE "db_reto_kruger" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Spanish_Ecuador.1252';
ALTER DATABASE "db_reto_kruger" OWNER TO postgres;

/*DATOS TABLA ROLES*/
INSERT INTO public.roles(nombre)VALUES ('ADMINISTRADOR');
INSERT INTO public.roles(nombre)VALUES ('EMPLEADO');

/*DATOS TABLA TIPO VACUNAS*/
INSERT INTO public.tipo_vacunas(nombre)VALUES ('Sputnik');
INSERT INTO public.tipo_vacunas(nombre)VALUES ('AstraZeneca');
INSERT INTO public.tipo_vacunas(nombre)VALUES ('Pfizer');
INSERT INTO public.tipo_vacunas(nombre)VALUES ('Jhonson&Jhonson');


