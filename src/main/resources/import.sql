/*DATOS TABLA ROLES*/
INSERT INTO public.roles(nombre) VALUES ('ADMINISTRADOR');
INSERT INTO public.roles(nombre) VALUES ('EMPLEADO');

/*DATOS TABLA TIPO VACUNAS*/
INSERT INTO public.tipo_vacunas(nombre) VALUES ('Sputnik');
INSERT INTO public.tipo_vacunas(nombre) VALUES ('AstraZeneca');
INSERT INTO public.tipo_vacunas(nombre) VALUES ('Pfizer');
INSERT INTO public.tipo_vacunas(nombre) VALUES ('Jhonson&Jhonson');

/*DATOS TABLA USUARIOS*/
INSERT INTO public.usuarios (estado, password, username) VALUES (true, '1719797324', '1719797324');
INSERT INTO public.usuarios (estado, password, username) VALUES (true, '1719712231', '1719712231');
INSERT INTO public.usuarios (estado, password, username) VALUES (true, '1719719923', '1719719923');
INSERT INTO public.usuarios (estado, password, username) VALUES (true, '1231219923', '1231219923');
INSERT INTO public.usuarios (estado, password, username) VALUES (true, '1825324123', '1825324123');

/*DATOS TABLA EMPLEADOS*/
INSERT INTO public.empleados ("apellidos", "cedula", "correo", "direccion_domicilio", "fecha_nacimiento", "nombres", "vacunado", "usuario_id") VALUES ('Chato', '1231219923', 'alison@hotmail.com', NULL, NULL, 'Alison', 'false', 4);
INSERT INTO public.empleados ("apellidos", "cedula", "correo", "direccion_domicilio", "fecha_nacimiento", "nombres", "vacunado", "usuario_id") VALUES ('Zambrano', '1825324123', 'pamela@hotmail.com', NULL, NULL, 'Pamela', 'false', 5);
INSERT INTO public.empleados ("apellidos", "cedula", "correo", "direccion_domicilio", "fecha_nacimiento", "nombres", "vacunado", "usuario_id") VALUES ('Vivanco', '1719797324', 'david@hotmail.com', 'Av. Republica y Av. Amazonas N12', '1990-08-21', 'David', 'true',1);
INSERT INTO public.empleados ("apellidos", "cedula", "correo", "direccion_domicilio", "fecha_nacimiento", "nombres", "vacunado", "usuario_id") VALUES ('Vivanco', '1719712231', 'sebastian@hotmail.com', 'Cdla Ejercito sector Sur S123', '2014-11-03', 'Sebastian','true', 2);
INSERT INTO public.empleados ("apellidos", "cedula", "correo", "direccion_domicilio", "fecha_nacimiento", "nombres", "vacunado", "usuario_id") VALUES ('Zapata', '1719719923', 'ximena@hotmail.com', 'Carcelen Bajo NO12', '2021-06-28', 'Ximena', 'true', 3);

/*DATOS TABLA ROLES_USUARIOS*/
INSERT INTO public.roles_usuarios ("estado", "rol_id", "usuario_id") VALUES ('true', 2, 1);
INSERT INTO public.roles_usuarios ("estado", "rol_id", "usuario_id") VALUES ('true', 2, 2);
INSERT INTO public.roles_usuarios ("estado", "rol_id", "usuario_id") VALUES ('true', 2, 3);
INSERT INTO public.roles_usuarios ("estado", "rol_id", "usuario_id") VALUES ('true', 2, 4);
INSERT INTO public.roles_usuarios ("estado", "rol_id", "usuario_id") VALUES ('true', 2, 5);

/*DATOS TABLA VACUNAS_USUARIOS*/
INSERT INTO public.vacunas_usuarios ("fecha_vacunacion", "nro_dosis", "empleado_id", "tipo_vacuna_id") VALUES ('2022-08-28', 1, 1, 1);
INSERT INTO public.vacunas_usuarios ("fecha_vacunacion", "nro_dosis", "empleado_id", "tipo_vacuna_id") VALUES ('2022-08-28', 2, 1, 2);
INSERT INTO public.vacunas_usuarios ("fecha_vacunacion", "nro_dosis", "empleado_id", "tipo_vacuna_id") VALUES ('2021-10-28', 1, 2, 2);
INSERT INTO public.vacunas_usuarios ("fecha_vacunacion", "nro_dosis", "empleado_id", "tipo_vacuna_id") VALUES ('2022-04-28', 1, 3, 1);


